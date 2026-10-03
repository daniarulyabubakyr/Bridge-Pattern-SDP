public class Circle extends Shape {

    private final int radius;



    public Circle(String id, Renderer renderer, int radius) {
        super(id, renderer);
        this.radius=radius;
    }

    @Override
    public String execute(){
        return renderer.renderCircle(radius);
    }

    public int getRadius(){
        return radius;
    }
}