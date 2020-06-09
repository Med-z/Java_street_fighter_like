import interfaces.Collidable;
import interfaces.Renderable;
import java.net.URL;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import managers.InputManager;
import other.GameObject;
import other.Hitbox;

public class Alex extends Character implements Collidable, Renderable {

    final Image idleRight = new Image("Alex/AlexIdleRight.gif");
    final Image idle = new Image("Alex/AlexIdle.gif");
    final Image forwardRight = new Image("Alex/AlexForward.gif"); 
    final Image forwardLeft = new Image("Alex/AlexForwardRight.gif"); 
    final Image backwardRight = new Image("Alex/AlexBackward.gif");
    final Image backwardLeft = new Image("Alex/AlexBackwardRight.gif");
    
    Ryu ryu;
    
    ImageView renderer;
    
//    Rectangle renderer;

    public Alex(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
//        this.renderer = new Rectangle(x, y, width, height);
        renderer = new ImageView(idleRight);
        renderer.setX(x);
        renderer.setY(y);
//        renderer.setFitWidth(224);
//        renderer.setFitHeight(226);
    }

    @Override
    public void update() {
        Image leftDirectionAnimation, rightDirectionAnimation, idleAnimation;

        if(ryu.getX() > x)
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

        if(InputManager.getKey(KeyCode.NUMPAD6)) {
            this.x += speed;
            renderer.setImage(rightDirectionAnimation);
        } else if(InputManager.getKey(KeyCode.NUMPAD4)) {
            this.x += -speed;
            renderer.setImage(leftDirectionAnimation);
        } else {
            renderer.setImage(idleAnimation);
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
