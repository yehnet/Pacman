package Movable;

import Board.Game;
import Still.Edible;

import javax.swing.*;
import java.awt.*;

public abstract class Pacman extends MovableObject implements Visitor{
    //-------------------fields-----------------------
    //private boolean _alive;
    private static final double _velocity = 0.667;

    //-------------------constructor------------------
    public Pacman(Game master, Point position) {
        super(master, position, _velocity);
    }

    //-------------------methods----------------------
    @Override
    public void ChooseDirection(){
        if (!CanMove())
            set_direction('c');
    }


    @Override
    public void Move(){
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        if (CanMove()) {
            switch (get_direction()) {
                case ( 'n' ): {
                    get_board()[y - 1][x] = this;
                    get_master().getInteractionBoard()[y - 1][x] = this;
                    set_position(x, y - 1);
                    get_board()[y][x] = null;
                    get_master().getInteractionBoard()[y][x] = null;
                    break;
                }
                case ( 's' ): {
                    get_board()[y + 1][x] = this;
                    get_master().getInteractionBoard()[y + 1][x] = this;
                    set_position(x, y + 1);
                    get_board()[y][x] = null;
                    get_master().getInteractionBoard()[y][x] = null;
                    break;
                }
                case ( 'e' ): {
                    get_board()[y][x + 1] = this;
                    get_master().getInteractionBoard()[y][x + 1] = this;
                    set_position(x + 1, y);
                    get_board()[y][x] = null;
                    get_master().getInteractionBoard()[y][x] = null;
                    break;
                }
                case ( 'w' ): {
                    get_board()[y][x - 1] = this;
                    get_master().getInteractionBoard()[y][x - 1] = this;
                    set_position(x - 1, y);
                    get_board()[y][x] = null;
                    get_master().getInteractionBoard()[y][x] = null;
                    break;
                }
            }
            set_direction('c');
        }
    }

    @Override
    public boolean CanMove(){
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        switch (get_direction()){
            case ('n'): {
                if (y > 0 && get_board()[y - 1][x] == null)
                    if(get_master().getInteractionBoard()[y - 1][x]!=null) {
                        if (get_master().getInteractionBoard()[y][x-1] instanceof Visited) {
                            Visited o = (Visited) get_master().getInteractionBoard()[y][x-1];
                            o.impact(this);
                        }
                        return false;
                    }
                    else
                        return true;
                else if (y > 0 && get_board()[y - 1][x] instanceof Edible) {
                    Eat((Edible) get_board()[y - 1][x]);
                    return true;
                }
                break;
            }
            case ('s'):{
                if (y > 0 && get_board()[y + 1][x] == null)
                    if(get_master().getInteractionBoard()[y + 1][x]!=null) {
                        if (get_master().getInteractionBoard()[y][x-1] instanceof Visited) {
                            Visited o = (Visited) get_master().getInteractionBoard()[y][x-1];
                            o.impact(this);
                        }
                        return false;
                    }
                    else
                        return true;
                else if (y > 0 && get_board()[y + 1][x] instanceof Edible) {
                    Eat((Edible) get_board()[y + 1][x]);
                    return true;
                }
                break;
            }
            case ('e'): {
                if (x < get_board().length && get_board()[y][x+1] == null)
                    if(get_master().getInteractionBoard()[y][x+1]!=null) {
                        if (get_master().getInteractionBoard()[y][x-1] instanceof Visited) {
                            Visited o = (Visited) get_master().getInteractionBoard()[y][x-1];
                            o.impact(this);
                        }
                        return false;
                    }
                    else
                        return true;
                else if (x<get_board().length && get_board()[y][x+1] instanceof Edible) {
                    Eat((Edible) get_board()[y][x+1]);
                    return true;
                }
                break;
            }
            case ('w'):{
                if (x >0 && get_board()[y][x-1] == null)
                    if(get_master().getInteractionBoard()[y][x-1]!=null) {
                        if (get_master().getInteractionBoard()[y][x-1] instanceof Visited) {
                            Visited o = (Visited) get_master().getInteractionBoard()[y][x-1];
                            o.impact(this);
                        }
                        return false;
                    }
                    else
                        return true;
                else if (x>0 && get_board()[y][x-1] instanceof Edible) {
                    Eat((Edible) get_board()[y][x-1]);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private void Eat(Edible edible) {
        get_master().Eat(edible);
    }

    @Override
    public void Killed(){
        get_master().PacmanDied();
    }
}
