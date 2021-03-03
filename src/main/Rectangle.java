package main;

public class Rectangle extends Shape {
    private final double[] dimensions = new double[2];


    public double[] getDimensions() {
        return dimensions;
    }


    public Rectangle(float _posX, float _posY, float _width, float _height) {
        position[0] = _posX;
        position[1] = _posY;
        dimensions[0] = _width;
        dimensions[1] = _height;
        type = Type.RECTANGLE;
    }


    public boolean pointInRect(double[] point) {
        // first cycle checks horizontal bound of rectangle, if it doesn't cross the point false is returned
        // first cycle checks vertical bound of rectangle, if it doesn't cross the point false is returned
        for (int i = 0; i < 2; i ++) {
            if (this.position[i] - point[i] > 0) {
                if (this.position[i] - this.dimensions[i] / 2 >= point[i])
                    return false;
            } else if (this.position[i] + this.dimensions[i] / 2 <= point[i])
                return false;
        }

        return true;
    }


    @Override
    public boolean isColliding(Shape shape) {
        return switch (shape.type) {
            case RECTANGLE -> collidingWithRect((Rectangle) shape);
            case CIRCLE -> collidingWithCircle((Circle) shape);
        };
    }


    private boolean collidingWithRect(Rectangle rect){
        // first cycle checks horizontal bounds of both rectangles, if they don't cross false is returned
        // second cycle checks vertical bounds of both rectangles, if they don't cross false is returned
        for (int i = 0; i < 2; i ++) {
            if (this.position[i] - rect.position[i] > 0) {
                if (this.position[i] - this.dimensions[i] / 2 >= rect.position[i] + rect.dimensions[i] / 2)
                    return false;
            } else if (this.position[i] + this.dimensions[i] / 2 <= rect.position[i] - rect.dimensions[i] / 2)
                return false;
        }

        return true;
    }


    private boolean collidingWithCircle(Circle circle) {
        return circle.collidingWithRect(this);
    }
}
