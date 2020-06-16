package streetfighter;

import streetfighter.interfaces.Collidable;
import streetfighter.interfaces.Renderable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import streetfighter.managers.InputManager;
import streetfighter.other.GameObject;
import streetfighter.other.Hitbox;
import streetfighter.other.Hurtbox;

public class Ken extends Character implements Collidable, Renderable {

    final Image iStance = new Image("streetfighter/Ken/Stance.gif", 156, 222, true, false);
    final Image iWalkForward = new Image("streetfighter/Ken/WalkForward.gif", 224, 226, true, false);
    final Image iWalkBackward = new Image("streetfighter/Ken/WalkBackward.gif", 224, 226, true, false);
    final Image iWin = new Image("streetfighter/Ken/Win.gif",224, 226, true, false);
    
    final Attack atkLightPunch = new Attack(400, 6, "PunchLight", 180, 30, 60, 20);
    final Attack atkHeavyPunch = new Attack(840, 6, "PunchHeavy", 170, 20, 80, 30);
    final Attack atkLightKick = new Attack(420, 6, "KickLight", width, height, 40, 20);
    final Attack atkHeavyKick = new Attack(590, 6, "KickHeavy", width, height, 40, 20);

    final Attack specialAtk =new Attack(500, 0, "SpecialAttack", width, height, 40, 20);
    
    Character ryu;
    ImageView renderer;
    private CharacterState state;
    final List<KeyCode> specialAttack = new ArrayList<>();
     final List<KeyCode> specialAttackFinal = new ArrayList<>();
    
//    Rectangle renderer;

    public Ken(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
        renderer = new ImageView(iStance);
        renderer.setX(x);
        renderer.setY(y);
        specialAttack.add(KeyCode.NUMPAD7);
        specialAttack.add(KeyCode.NUMPAD9);
        specialAttack.add(KeyCode.NUMPAD3);
    }

    @Override
    public void update() {
        super.update();
        if (canMove) {
            if (state != CharacterState.ATTACKING) {
                if (InputManager.getKey(KeyCode.NUMPAD6)) {
                    state = CharacterState.MOVING_RIGHT;
                    this.x += speed;
                } else if (InputManager.getKey(KeyCode.NUMPAD4)) {
                    state = CharacterState.MOVING_LEFT;
                    this.x += -speed;
                } else {
                    state = CharacterState.STANCE;
                }

                if (InputManager.getTempKey(KeyCode.NUMPAD7)) {
                    attack(atkLightPunch);
                    setSpecialAttack(KeyCode.NUMPAD7);
                }
                if (InputManager.getTempKey(KeyCode.NUMPAD1)) {
                    attack(atkHeavyPunch);
                    setSpecialAttack(KeyCode.NUMPAD1);
                }
                if (InputManager.getTempKey(KeyCode.NUMPAD9)) {
                    attack(atkLightKick);
                    setSpecialAttack(KeyCode.NUMPAD9);
                }
                if (InputManager.getTempKey(KeyCode.NUMPAD3)) {
                    attack(atkHeavyKick);
                    setSpecialAttack(KeyCode.NUMPAD3);
                }
                if (specialAttack.equals(specialAttackFinal)) {
                    attack(specialAtk);
                    specialAttack.clear();
                }
            }
        }
    }
    
     public void attack(Attack attack) {
        Hurtbox hurtbox = new Hurtbox(x + attack.getXOff(), y + attack.getYOff(), attack.getWidth(), attack.getHeight(), attack.getDamage(), (Character) this);
        FightManager.getGoWaitList().add(hurtbox);
        state = CharacterState.ATTACKING;
        renderer.setImage(attack.getSprite("streetfighter/Ken"));
        Timer timer = new Timer();
        FightManager.instance.listTimer.add(timer);
        TimerTask decay = new TimerTask() {
            @Override
            public void run() {
                renderer.setImage(iStance);
                state = CharacterState.STANCE;
                FightManager.getGoGarbage().add(hurtbox);
            }
        };
        timer.schedule(decay, attack.getDuration());
    }
     
    public void setSpecialAttack(KeyCode KC){
        if (specialAttack.size() >= 3) {
            specialAttack.remove(0);
        }
        specialAttack.add(KC);
    }
    
    @Override
    public void draw() {
        if(canMove)
        {
            renderer.resizeRelocate(x, y, width, height);

            boolean rightIsForward = true;
            if(ryu.getX() > x) {
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
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public Node getRenderer() {
        return renderer;
    }

    
    
    @Override
    public void setOtherPlayer(Character ryu)
    {
        this.ryu = ryu;
    }

    @Override
    public void Win() {
        roundWon++;
        renderer.setImage(iWin); 
    }

   
    

            
}
