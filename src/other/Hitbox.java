package other;

import javafx.scene.shape.Rectangle;

public class Hitbox {
    Rectangle rectangle;

    public Hitbox(double x, double y, double width, double height) {
        rectangle = new Rectangle(x, y, width, height);
    }


}
