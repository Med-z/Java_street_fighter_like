import interfaces.Collidable;
import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import managers.InputManager;
import other.GameObject;
import other.Hitbox;

public class Ryu extends Character implements Collidable, Renderable {

    Rectangle renderer;

    public Ryu(double x, double y, double width, double height, double speed) {
        super(x, y, width, height, speed);
        this.renderer = new Rectangle(x, y, width, height);
    }

    @Override
    public void update() {
        if(InputManager.getKey(KeyCode.D)) {
            this.x += speed;
        } else if(InputManager.getKey(KeyCode.Q)) {
            this.x += -speed;
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
}
