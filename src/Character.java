import interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import other.GameObject;
import other.Hitbox;

public abstract class Character extends GameObject {
    protected double speed;
    protected Hitbox hitbox;
    private int healthPoint = 100;
    protected boolean canMove = true;
    protected int roundWon = 0;

    public Character(double x, double y, double width, double height, double speed) {
        super(x, y, width, height);
        this.hitbox = new Hitbox(x, y, width, height);

        this.speed = speed;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void takeDamage(int damage){
        setHealthPoint(getHealthPoint()-damage);
    }

    public void heal(int heal){
        setHealthPoint(getHealthPoint() + heal);
        if(healthPoint>100)
            setHealthPoint(100);
    }
    
    public abstract void Win();
    public abstract void setOtherPlayer(Character otherPlayer);      
            

    @Override
    public void update() {

    }
}
