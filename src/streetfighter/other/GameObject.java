package streetfighter.other;

public abstract class GameObject {
    protected double x, y, width, height;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public double getX()
    {
        return x;
    }

    public abstract void update();
}
