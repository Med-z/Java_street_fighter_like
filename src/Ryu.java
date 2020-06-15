import interfaces.Collidable;
import interfaces.Renderable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import managers.InputManager;
import other.GameObject;
import other.Hitbox;
import other.Hurtbox;

public class Ryu extends Character implements Collidable, Renderable {

    private CharacterState state;

    final Image iStance = new Image("Ryu/Stance.gif", 156, 222, true, false);
    final Image iWalkForward = new Image("Ryu/WalkForward.gif", 224, 226, true, false);
    final Image iWalkBackward = new Image("Ryu/WalkBackward.gif", 224, 226, true, false);
    
    final Image iWin  = new Image("Ryu/Win.gif",224, 226, true, false);

    final Attack atkLightPunch = new Attack(400, 6, "PunchLight", width, height, 40, 20);
    final Attack atkHeavyPunch = new Attack(840, 6, "PunchHeavy", width, height, 40, 20);
    final Attack atkLightKick = new Attack(420, 6, "KickLight", width, height, 40, 20);
    final Attack atkHeavyKick = new Attack(590, 6, "KickHeavy", width, height, 40, 20);

    Character ken;
    ImageView renderer;
    final List<KeyCode> specialAttack = new ArrayList<>();
//    Rectangle renderer;

    public Ryu(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
        renderer = new ImageView(iStance);
        renderer.setX(x);
        renderer.setY(y);
        specialAttack.add(KeyCode.A);
        specialAttack.add(KeyCode.E);
        specialAttack.add(KeyCode.C);
        state = CharacterState.STANCE;
    }

    public void attack(Attack attack) {
        Hurtbox hurtbox = new Hurtbox(x + attack.getXOff(), y + attack.getYOff(), attack.getWidth(), attack.getHeight());
        Main.getGameObjects().add(hurtbox);
        state = CharacterState.ATTACKING;
        renderer.setImage(attack.getSprite("Ryu"));
        Timer timer = new Timer();
        TimerTask decay = new TimerTask() {
            @Override
            public void run() {
                renderer.setImage(iStance);
                state = CharacterState.STANCE;
                Main.getGameObjects().remove(hurtbox);
            }
        };
        timer.schedule(decay, attack.getDuration());
    }

    @Override
    public void update() {
        if(canMove)
        {
             if (state != CharacterState.ATTACKING) {
            if (InputManager.getKey(KeyCode.D)) {
                state = CharacterState.MOVING_RIGHT;
                this.x += speed;
            } else if (InputManager.getKey(KeyCode.Q)) {
                state = CharacterState.MOVING_LEFT;
                this.x += -speed;
            } else {
                state = CharacterState.STANCE;
            }

            if (InputManager.getTempKey(KeyCode.A)) {
                attack(atkLightPunch);
            }
            if (InputManager.getTempKey(KeyCode.W)) {
                attack(atkHeavyPunch);
            }
            if (InputManager.getTempKey(KeyCode.E)) {
                attack(atkLightKick);
            }
            if (InputManager.getTempKey(KeyCode.C)) {
                attack(atkHeavyKick);
            }
        }
        }
       
    }

    @Override
    public void onCollision(GameObject go) {

    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Node getRenderer() {
        return renderer;
    }

    @Override
    public void draw() {
        if(canMove)
        {
            renderer.resizeRelocate(x, y, width, height);

        boolean rightIsForward = true;
        if(ken.getX() > x) {
            rightIsForward = true;
            renderer.setScaleX(1);
        } else {
            rightIsForward = false;
            renderer.setScaleX(-1);
        }


        switch (state) {
            case STANCE:
                renderer.setImage(iStance);
                break;
            case MOVING_LEFT:
                if(rightIsForward) {
                    renderer.setImage(iWalkBackward);
                } else {
                    renderer.setImage(iWalkForward);
                }
                break;
            case MOVING_RIGHT:
                if(rightIsForward) {
                    renderer.setImage(iWalkForward);
                } else {
                    renderer.setImage(iWalkBackward);
                }
                break;
        }
        }
        
    }
    
    @Override
     public void setOtherPlayer(Character ken)
    {
        this.ken = ken;
    }
     
      @Override
    public void Win() {
        roundWon++;
        renderer.setImage(iWin); //To change body of generated methods, choose Tools | Templates.
    }
}
