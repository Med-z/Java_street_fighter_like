package streetfighter;

import streetfighter.interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import streetfighter.other.GameObject;

public class HealthBar extends GameObject implements Renderable {
    private Rectangle renderer;
    private Character character;

    public HealthBar(double posX, double posY, double width, double height, Character character) {
        super(posX, posY, width, height);
        renderer = new Rectangle(width, height, Color.GREEN);
        renderer.setX(posX);
        renderer.setY(posY);
        this.character = character;
    }

    @Override
    public void update() {
        this.width = character.getHealthPoint();
    }

    @Override
    public Node getRenderer() {
        return this.renderer;
    }

    @Override
    public void draw() {
        renderer.setWidth(width * 5);
    }
}
