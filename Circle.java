public class Circle extends Shape {
    protected double radius;

    public Circle(){
        this.radius = 1.0;
    }

    public Circle(String color, boolean filled, double radius){
        super(color,filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 3.14 * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * 3.14;
    }

    @Override
    public String toString(){
        return "A Circle with radius "+radius + ",which is a subclass of "+ super.toString();
    }
}
