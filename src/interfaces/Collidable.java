package interfaces;

import other.GameObject;
import other.Hitbox;

public interface Collidable {
    void onCollision(GameObject go);
    Hitbox getHitbox();
}
