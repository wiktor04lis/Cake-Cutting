public class Agent{
    private int id;
    private double mark;
    private double leftBound;
    private double rightBound;

    public Agent(int id){
        this.id=id;
    }

    public void givePiece(double leftBound, double rightBound){
        setLeftBound(leftBound);
        setRightBound(rightBound);
    }

    public int getId() {
        return id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(double leftBound) {
        this.leftBound = leftBound;
    }

    public double getRightBound() {
        return rightBound;
    }

    public void setRightBound(double rightBound) {
        this.rightBound = rightBound;
    }

    @Override 
    public String toString(){
        return "Agent: " + id + ", has piece on interval: [" + leftBound + ", " + rightBound +"]";
    }
    
}