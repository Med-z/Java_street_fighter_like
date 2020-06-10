package other;

import interfaces.Collidable;

public class Hurtbox extends GameObject implements Collidable {
    Hitbox hitbox;
    double damage;

    public Hurtbox(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.hitbox = new Hitbox(x, y, width, height);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(GameObject go) {

    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }
}
