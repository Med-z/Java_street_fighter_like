import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import other.GameObject;

public class HealthBar extends GameObject implements Renderable {
    private Rectangle healthBar;
    public int healthPoint;
    private final double originalWidth;

    public HealthBar(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        healthBar = new Rectangle(width, height, Color.RED);
        healthBar.setX(posX);
        healthBar.setY(posY);
        this.originalWidth = width;
    }


    public void takeDamage(int damage, Character character){
        character.setHealthPoint(character.getHealthPoint()-damage);
        this.healthBar.setWidth(character.getHealthPoint());
    }

    public void heal(int heal, Character character){
        if(healthPoint!=100) {
            character.setHealthPoint(character.getHealthPoint() + heal);
            this.healthBar.setWidth(character.getHealthPoint());
        }
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
