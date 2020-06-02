import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import other.GameObject;

public class HealthBar extends GameObject implements Renderable {
    private Rectangle healthBar;
    public int healthPoint;
    private final double originalWidth;

    public HealthBar(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        this.originalWidth = width;
        this.healthPoint = 100;
    }


    public void DamageTaken(int damage){
        this.healthBar.setWidth((1 - damage) * this.originalWidth);
    }

    public void Heal(int heal){
        if(healthPoint!=100)
            this.healthBar.setWidth(this.originalWidth*(1+heal));
    }

    @Override
    public Node getRenderer() {
        return healthBar;
    }

    @Override
    public void draw() {

    }

    @Override
    public void update() {
        this.healthBar.resizeRelocate(x, y, width, height);
    }
}
