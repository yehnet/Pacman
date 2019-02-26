package Movable;

public interface Visitor {

    public void visit(GreenGhost g);
    public void visit(OrangeGhost g);
    public void visit(RedGhost g);
    public void visit(WaterSplash p);
    public void visit(FireBall p);


}

