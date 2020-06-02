import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import other.GameObject;
import other.Hitbox;

public class Character extends GameObject implements Renderable {
    private double speed;
    private Rectangle renderer;
    private Hitbox hitbox;
    private int healthPoint = 100;

    public Character(double x, double y, double width, double height, double speed) {
        super(x, y, width, height);
        this.hitbox = new Hitbox(x, y, width, height);
        this.renderer = new Rectangle(x, y, width, height);

        this.speed = speed;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void takeDamage(int damage, HealthBar HB){
        setHealthPoint(getHealthPoint()-damage);
        HB.getHealthBar().setWidth(getHealthPoint());
    }

    public void heal(int heal, HealthBar HB){
        setHealthPoint(getHealthPoint() + heal);
        if(healthPoint>100)
            setHealthPoint(100);
        HB.getHealthBar().setWidth(getHealthPoint());
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
