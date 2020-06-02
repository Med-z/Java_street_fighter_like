package other;

import javafx.scene.Node;

public abstract class GameObject {
    double x, y, width, height;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
