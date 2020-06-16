import interfaces.Collidable;
import interfaces.Renderable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import managers.InputManager;
import other.GameObject;
import other.Hitbox;

public class Ken extends Character implements Collidable, Renderable {

    final Image iStance = new Image("Ken/Stance.gif", 156, 222, true, false);
    final Image iWalkForward = new Image("Ken/WalkForward.gif", 224, 226, true, false);
    final Image iWalkBackward = new Image("Ken/WalkBackward.gif", 224, 226, true, false);
    
    Ryu ryu;
    ImageView renderer;
    final List<KeyCode> specialAttack = new ArrayList<>();
    
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
        boolean leftIsForward = true;

        if(ryu.getX() > x) {
            leftIsForward = true;
            renderer.setScaleX(1);
        } else {
            leftIsForward = false;
            renderer.setScaleX(-1);
        }

        if(InputManager.getKey(KeyCode.NUMPAD6)) {
            this.x += speed;
            if(leftIsForward) {
                renderer.setImage(iWalkForward);
            } else {
                renderer.setImage(iWalkBackward);
            }

        } else if(InputManager.getKey(KeyCode.NUMPAD4)) {
            this.x += -speed;
            if(leftIsForward) {
                renderer.setImage(iWalkBackward);
            } else {
                renderer.setImage(iWalkForward);
            }
        } else {
            renderer.setImage(iStance);
        }

        if(InputManager.getTempKey(KeyCode.NUMPAD7)) {
            System.out.println("Appui sur 7");
        }
        if(InputManager.getTempKey(KeyCode.NUMPAD1)) {
            System.out.println("Appui sur 1");
        }
        if(InputManager.getTempKey(KeyCode.NUMPAD9)) {
            System.out.println("Appui sur 9");
        }
        if(InputManager.getTempKey(KeyCode.NUMPAD3)) {
            System.out.println("Appui sur 3");
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
        renderer.resizeRelocate(x, y, width, height);
    }
    
    public void setOtherPlayer(Ryu ryu)
    {
        this.ryu = ryu;
    }
}
