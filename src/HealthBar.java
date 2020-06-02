import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar {
    private Rectangle renderer;
    private Character character;

    public HealthBar(double posX, double posY, double width, double height, Character character) {
        renderer = new Rectangle(width, height, Color.RED);
        renderer.setX(posX);
        renderer.setY(posY);
        this.character = character;
    }

    public Rectangle getHealthBar() {
        return renderer;
    }

    public void update() {
        this.renderer.setWidth(character.getHealthPoint());
    }
}
