import interfaces.Collidable;
import interfaces.Renderable;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import managers.InputManager;
import other.GameObject;
import other.Hitbox;

public class Ryu extends Character implements Collidable, Renderable {

    final Image iStance = new Image("Ryu/Stance.gif", 156, 222, true, false);
    final Image iWalkForward = new Image("Ryu/WalkForward.gif", 224, 226, true, false);
    final Image iWalkBackward = new Image("Ryu/WalkBackward.gif", 224, 226, true, false);
    
    Ken ken;
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
    }
    
    @Override
    public void update() {
        boolean rightIsForward = true;

        if(ken.getX() > x) {
            rightIsForward = true;
            renderer.setScaleX(1);
        } else {
            rightIsForward = false;
            renderer.setScaleX(-1);
        }

        if(InputManager.getKey(KeyCode.D)) {
            this.x += speed;
            if(rightIsForward) {
                renderer.setImage(iWalkForward);
            } else {
                renderer.setImage(iWalkBackward);
            }

        } else if(InputManager.getKey(KeyCode.Q)) {
            this.x += -speed;
            if(rightIsForward) {
                renderer.setImage(iWalkBackward);
            } else {
                renderer.setImage(iWalkForward);
            }
        } else {
            renderer.setImage(iStance);
        }

        if(InputManager.getTempKey(KeyCode.A)) {
            System.out.println("Appui sur A");
        }
        if(InputManager.getTempKey(KeyCode.W)) {
            System.out.println("Appui sur W");
        }
        if(InputManager.getTempKey(KeyCode.E)) {
            System.out.println("Appui sur E");
        }
        if(InputManager.getTempKey(KeyCode.C)) {
            System.out.println("Appui sur C");
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
    
     public void setOtherPlayer(Ken ken)
    {
        this.ken = ken;
    }
}
