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

public class Ryu extends Character implements Collidable, Renderable {

    final Image idle = new Image("Ryu/RyuIdle.gif");
    final Image idleRight = new Image("Ryu/RyuIdleRight.gif");
    final Image forwardRight = new Image("Ryu/RyuForward.gif");
    final Image forwardLeft = new Image("Ryu/RyuForwardRight.gif"); 
    final Image backwardRight = new Image("Ryu/RyuBackward.gif");
    final Image backwardLeft = new Image("Ryu/RyuBackwardR.gif");
    
    Alex alex;
    ImageView renderer;
    final List<KeyCode> specialAttack = new ArrayList<>();
//    Rectangle renderer;

    public Ryu(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
//        this.renderer = new Rectangle(x, y, width, height);
        renderer = new ImageView(idle);
        renderer.setX(x);
        renderer.setY(y);
//        renderer.setFitWidth(224);
//        renderer.setFitHeight(226);
        specialAttack.add(KeyCode.A);
        specialAttack.add(KeyCode.E);
        specialAttack.add(KeyCode.C);
    }
    
    @Override
    public void update() {
        Image leftDirectionAnimation, rightDirectionAnimation, idleAnimation;

        if(alex.getX() > x)
        {
            leftDirectionAnimation = backwardRight;
            rightDirectionAnimation = forwardRight;
            idleAnimation = idle;
        }
        else
        {
            leftDirectionAnimation = forwardLeft;
            rightDirectionAnimation = backwardLeft;
            idleAnimation = idleRight;
        }

        if(InputManager.getKey(KeyCode.D)) {
            this.x += speed;
            renderer.setImage(rightDirectionAnimation);
        } else if(InputManager.getKey(KeyCode.Q)) {
            this.x += -speed;
            renderer.setImage(leftDirectionAnimation);
        } else {
            renderer.setImage(idleAnimation);
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
    
     public void setOtherPlayer(Alex alex)
    {
        this.alex = alex;
    }
}
