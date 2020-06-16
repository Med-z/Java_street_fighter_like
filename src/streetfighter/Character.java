package streetfighter;

import javafx.scene.shape.Rectangle;
import streetfighter.other.GameObject;
import streetfighter.other.Hitbox;
import streetfighter.other.Hurtbox;

public abstract class Character extends GameObject {
    protected double speed;
    protected Hitbox hitbox;
    private double healthPoint = 100;
    protected boolean canMove = true;
    protected int roundWon = 0;

    public Character(double x, double y, double width, double height, double speed) {
        super(x, y, width, height);
        this.hitbox = new Hitbox(x, y, width, height);

        this.speed = speed;
    }

    public double getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(double healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void takeDamage(double damage){
        setHealthPoint(getHealthPoint()-damage);
    }

    public void heal(int heal){
        setHealthPoint(getHealthPoint() + heal);
        if(healthPoint>100)
            setHealthPoint(100);
    }
    
    
    public void onCollision(GameObject go) {
        if(go instanceof Hurtbox) {
            if(!((Hurtbox) go).getOwner().equals(this)) {
                this.takeDamage(((Hurtbox) go).getDamage());
                FightManager.getGoGarbage().add(go);
            }
        }
    }
    
   
    
    public abstract void Win();
    public abstract void setOtherPlayer(Character otherPlayer);      
            

    @Override
    public void update() {
        this.hitbox.getRectangle().setX(x);
    }
}
