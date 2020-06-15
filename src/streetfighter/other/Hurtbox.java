package streetfighter.other;

import streetfighter.Character;
import streetfighter.interfaces.Collidable;

public class Hurtbox extends streetfighter.other.GameObject implements Collidable {
    Hitbox hitbox;
    Character owner;
    double damage;

    public Hurtbox(double x, double y, double width, double height, double damage, Character owner) {
        super(x, y, width, height);
        this.hitbox = new Hitbox(x, y, width, height);
        this.damage = damage;
        this.owner = owner;
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(streetfighter.other.GameObject go) {

    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    public Character getOwner() {
        return owner;
    }

    public double getDamage() {
        return damage;
    }
}
