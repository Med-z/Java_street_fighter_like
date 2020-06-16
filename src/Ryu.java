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

    final Attack atkLightPunch = new Attack(400, 6, "PunchLight", width, height, 40, 20);
    final Attack atkHeavyPunch = new Attack(840, 6, "PunchHeavy", width, height, 40, 20);
    final Attack atkLightKick = new Attack(420, 6, "KickLight", width, height, 40, 20);
    final Attack atkHeavyKick = new Attack(590, 6, "KickHeavy", width, height, 40, 20);

    final Attack specialAtk =new Attack(500, 20, "SpecialAttack", width, height, 40, 20);

    Ken ken;
    ImageView renderer;
    final List<KeyCode> specialAttackFinal = new ArrayList<>();
    List<KeyCode> specialAttack = new ArrayList<>();
//    Rectangle renderer;

    public Ryu(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
        renderer = new ImageView(iStance);
        renderer.setX(x);
        renderer.setY(y);
        specialAttackFinal.add(KeyCode.A);
        specialAttackFinal.add(KeyCode.E);
        specialAttackFinal.add(KeyCode.C);
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
                setSpecialAttack(KeyCode.A);
            }
            if (InputManager.getTempKey(KeyCode.W)) {
                attack(atkHeavyPunch);
                setSpecialAttack(KeyCode.W);

            }
            if (InputManager.getTempKey(KeyCode.E)) {
                attack(atkLightKick);
                setSpecialAttack(KeyCode.E);
            }
            if (InputManager.getTempKey(KeyCode.C)) {
                attack(atkHeavyKick);
                setSpecialAttack(KeyCode.C);
            }

            if(specialAttack==specialAttackFinal){
                attack(specialAtk);
                System.out.println("atkSpe");
                specialAttack=null;
            }
        }
    }

    public void setSpecialAttack(KeyCode KC){
        if (specialAttack.size() >= 3) {
            specialAttack.remove(0);
        }
        specialAttack.add(KC);
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
    
     public void setOtherPlayer(Ken ken)
    {
        this.ken = ken;
    }
}
