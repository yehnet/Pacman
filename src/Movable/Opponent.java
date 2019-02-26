package Movable;

import Board.Game;

import java.awt.*;

public abstract class Opponent extends MovableObject implements Visited{

    Opponent(Game master, Point position, double velocity){
        super(master, position, velocity);
    }

    @Override
    public void Move(){
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        switch (get_direction()) {
            case ( 'n' ): {
                get_master().getInteractionBoard()[y - 1][x] = this;
                set_position(x, y - 1);
                get_master().getInteractionBoard()[y][x] = null;
                break;
            }
            case ( 's' ): {
                get_master().getInteractionBoard()[y + 1][x] = this;
                set_position(x, y + 1);
                get_master().getInteractionBoard()[y][x] = null;
                break;
            }
            case ( 'e' ): {
                get_master().getInteractionBoard()[y][x + 1] = this;
                set_position(x + 1, y);
                get_master().getInteractionBoard()[y][x] = null;
                break;
            }
            case ( 'w' ): {
                get_master().getInteractionBoard()[y][x - 1] = this;
                set_position(x - 1, y);
                get_master().getInteractionBoard()[y][x] = null;
                break;
            }
        }
    }
}
