package main;

public abstract class Shape {
    protected double[] position = new double[2];
    protected Type type;

    public abstract boolean isColliding(Shape shape);
}
