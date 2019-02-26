package Movable;

public interface Movable {

    void Tick(double tick);

    void Move();

    boolean CanMove();

    void ChooseDirection();
}
