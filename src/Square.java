public class Square extends Shape{

    private final int side;



    public Square(String id, Renderer renderer, int side) {
        super(id, renderer);
        this.side=side;
    }


    @Override
    public String execute(){
        return renderer.renderSquare(side);
    }


    public int getSide(){
        return side;
    }
}