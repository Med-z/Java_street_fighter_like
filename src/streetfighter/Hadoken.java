package streetfighter;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import streetfighter.interfaces.Renderable;
import streetfighter.other.GameObject;
import streetfighter.other.Hurtbox;

public class Hadoken extends GameObject implements Renderable{
    int duration;
    private double speed;
    private Image hadoken = new Image("streetfighter/Ryu/Hadoken.gif");
    private ImageView renderer;
    Hurtbox hurtbox;

    public Hadoken(double x, double y, double width, double height, double speed, int duration) {
        super(x, y, width, height);
        this.speed = speed;
        renderer = new ImageView(hadoken);
        renderer.setX(x);
        renderer.setY(y);
        this.duration=duration;
    }

    @Override
    public void update() {
        this.x += speed;
        hurtbox.getHitbox().getRectangle().setX(x);
    }

    @Override
    public Node getRenderer() {
        return renderer;
    }

    @Override
    public void draw() {
        renderer.setX(x);
    }
}
