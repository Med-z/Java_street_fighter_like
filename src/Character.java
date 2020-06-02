import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import other.GameObject;
import other.Hitbox;

public class Character extends GameObject implements Renderable {
    private double speed;
    private Rectangle renderer;
    private Hitbox hitbox;

    public Character(double x, double y, double width, double height, double speed) {
        super(x, y, width, height);
        this.hitbox = new Hitbox(x, y, width, height);
        this.renderer = new Rectangle(x, y, width, height);

        this.speed = speed;
    }

    @Override
    public Node getRenderer() {
        return renderer;
    }

    @Override
    public void draw() {
        this.renderer.resizeRelocate(x, y, width, height);
    }

    @Override
    public void update() {
        // TODO: bouger si on doit bouger
        // Probablement à réécrire dans Ryu/Ken.java
    }
}
