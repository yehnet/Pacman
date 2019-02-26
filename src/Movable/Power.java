package Movable;

import Board.Game;

import java.awt.*;

public abstract class Power extends Opponent{

    Power(Game master, char direction, Point p, double velocity) {
        super(master, p, velocity);
        set_direction(direction);
        Init();
    }

    @Override
    public void ChooseDirection() {
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        if (x<=0 | y<=0 | x>=get_board().length-1 | y>=get_board().length-1)
            Killed();
    }

    @Override
    public void Move() {
        if (CanMove()) {
            super.Move();
        }
        else
            Killed();
        ChooseDirection();
    }

    @Override
    public void Killed(){
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        get_master().getInteractionBoard()[y][x] = null;
        get_master().RemoveFromGame(this);
    }

}
