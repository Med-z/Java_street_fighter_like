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

public class Ryu extends Character implements Collidable, Renderable {

    final Image idle = new Image("Ryu/RyuIdle.gif");
    final Image idleRight = new Image("Ryu/RyuIdleRight.gif");
    final Image forwardRight = new Image("Ryu/RyuForward.gif"); 
    final Image forwardLeft = new Image("Ryu/RyuForwardRight.gif"); 
    final Image backwardRight = new Image("Ryu/RyuBackward.gif");
    final Image backwardLeft = new Image("Ryu/RyuBackwardR.gif");
    
    Alex alex;
    ImageView renderer;
    
//    Rectangle renderer;

    public Ryu(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
//        this.renderer = new Rectangle(x, y, width, height);
        renderer = new ImageView(idle);
        renderer.setX(x);
        renderer.setY(y);
//        renderer.setFitWidth(224);
//        renderer.setFitHeight(226);
    }
    
    @Override
    public void update() {
        if(alex.getX() > x)
        {
            MoveRight();
        }
        else
        {
            MoveLeft();
        }
    }
    
    private void MoveRight()
    {
        if(InputManager.getKey(KeyCode.D))
        {
            this.x += speed;
            renderer.setImage(forwardRight);
        } 
        else if(InputManager.getKey(KeyCode.Q)) 
        {
            this.x += -speed;
            renderer.setImage(backwardRight);
        }
        else
        {
            renderer.setImage(idle);
        }
    }
    
    private void MoveLeft()
    {
         if(InputManager.getKey(KeyCode.D))
        {
            this.x += speed;
            renderer.setImage(backwardLeft);
        } 
        else if(InputManager.getKey(KeyCode.Q)) 
        {
            this.x += -speed;
            renderer.setImage(forwardLeft);
        }
        else
        {
            renderer.setImage(idleRight);
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
