package main;

import java.awt.*;

public class Circle extends Shape{
    private float radius;


    public float getRadius() {
        return radius;
    }


    public Circle(float _posX, float _posY, float _radius) {
        position[0] = _posX;
        position[1] = _posY;
        radius = _radius;
        type = Type.CIRCLE;
    }


    @Override
    public boolean isColliding(Shape shape) {
        switch(shape.type) {
            case RECTANGLE:
                return collidingWithRect((Rectangle) shape);
            case CIRCLE:
                return collidingWithCircle((Circle) shape);
            default:
                return false;
        }
    }


    public boolean collidingWithRect(Rectangle rect) {
        // checks if circle origin is in rectangle
        if (rect.pointInRect(position))
            return true;

        double[] direction = new double[2];
        double[] closestPoint = new double[2];
        double[] secondPoint = new double[2];

        // direction vector from circle origin to rectangle origin
        direction[0] = rect.position[0] - this.position[0];
        direction[1] = rect.position[1] - this.position[1];

        // closest rectangle vertex to circle
        closestPoint[0] = rect.position[0] + rect.getDimensions()[0] / 2 * Math.signum(direction[0]);
        closestPoint[1] = rect.position[1] + rect.getDimensions()[1] / 2 * Math.signum(direction[1]);

        double distanceToClosestPoint = Math.sqrt( Math.pow(closestPoint[0] - this.position[0], 2) +
                        Math.pow(direction[1] - this.position[1], 2));

        // if distance is smaller than radius, then rectangle is withing the circle
        if (distanceToClosestPoint < radius)
            return true;

        // finding second point, that is making closest edge to the circle
        if (direction[0] < rect.getDimensions()[0] / 2 * Math.signum(direction[0])) {
            secondPoint[0] = closestPoint[0] + rect.getDimensions()[0] + -Math.signum(direction[0]);
            secondPoint[1] = closestPoint[1];
        }
        else {
            secondPoint[1] = closestPoint[1] + rect.getDimensions()[1] + -Math.signum(direction[1]);
            secondPoint[0] = closestPoint[0];
        }

        return isIntersectingWithLine(closestPoint, secondPoint);
    }


    private boolean isIntersectingWithLine(double[] a, double[] b) {
        double slope = (a[1] - b[1]) / (a[0] - b[0]);

        // shortest distance from circle origin to the line
        double distance = Math.abs( (-this.position[0]) + (this.position[1] / slope) + (a[0] - a[1] / slope) ) /
                        Math.sqrt(1 + Math.pow(1 / slope, 2));

        if (distance < radius)
            return true;

        return false;
    }


    private boolean collidingWithCircle(Circle circle) {
        // distance between origins has to be less then or equal to addition od radii
        double originDistance = Math.sqrt( Math.pow(this.position[0] - circle.position[0], 2) +
                        Math.pow(this.position[1] - circle.position[1], 2) );

        if (originDistance <= this.radius + circle.radius)
            return true;
        return false;
    }
}
