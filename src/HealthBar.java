import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import other.GameObject;

public class HealthBar extends GameObject implements Renderable {
    private Rectangle healthBar;

    public HealthBar(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        healthBar = new Rectangle(width, height, Color.RED);
        healthBar.setX(posX);
        healthBar.setY(posY);
    }

    public Rectangle getHealthBar() {
        return healthBar;
    }

    @Override
    public Node getRenderer() {
        return healthBar;
    }

    @Override
    public void draw() {
        this.healthBar.resizeRelocate(x, y, width, height);
    }

    @Override
    public void update() {
        this.healthBar.resizeRelocate(x, y, width, height);
    }
}
