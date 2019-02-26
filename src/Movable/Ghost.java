package Movable;

import Board.Game;
import Still.Wall;

import java.awt.*;

public abstract class Ghost extends Opponent{
    //-------------------fields-----------------------
    boolean chase;
    private String[] _path;
    private int _step;
    private static final double _delay = 2;
    private double _delayTicks;



    //-------------------constructor------------------
    Ghost(Game master, Point position, double velocity, String[] path) {
        super(master, position, velocity);
        chase = false;
        _path = path;
        _step = 0;
        _delayTicks = 0;
    }

    //-------------------methods----------------------
    private void Chase(){
        chase = true;
    }

    @Override
    public void Tick(double tick){
        if (is_initiated()) {
            IncreaseTicksBy(tick);
            if (get_ticks() >= get_velocity()) {
                Move();
                ClearTicks();
            }
        }
    }

    @Override
    public void ChooseDirection(){
        if (!chase){
            if (_step == _path.length) {
                TickDelay(get_timer()._tick);
                if (_delayTicks >= _delay)
                    Chase();
            }
            else {
                set_direction(_path[_step].charAt(0));
                _step++;
            }
        }
        else{
            while (!CanMove()){
                switch ((int) (Math.random()*4)){
                    case (0)://up
                        set_direction('n');
                        break;
                    case (1)://right
                        set_direction('e');
                        break;
                    case (2)://down
                        set_direction('s');
                        break;
                    case (3)://left
                        set_direction('w');
                        break;
                }
            }
        }
    }

    @Override
    public void Move() {
        ChooseDirection();
        if (CanMove()) {
            super.Move();
        }
    }

    @Override
    public boolean CanMove() {
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        switch (get_direction()) {
            case ( 'n' ): {
                if (y > 0 && get_board()[y - 1][x] instanceof Wall)
                    return false;
                else if (y > 0 && get_master().getInteractionBoard()[y-1][x] != null) {
                    if (get_master().getInteractionBoard()[y-1][x] instanceof Pacman)
                        impact((Pacman) get_board()[y - 1][x]);
                    return false;
                }
                return true;
            }
            case ( 's' ): {
                if (y < get_board().length && get_board()[y + 1][x] instanceof Wall)
                    return false;
                else if (y < get_board().length && get_master().getInteractionBoard()[y + 1][x] != null) {
                    if (get_master().getInteractionBoard()[y + 1][x] instanceof Pacman)
                        impact((Pacman) get_board()[y + 1][x]);
                    return false;
                }
                return true;
            }
            case ( 'e' ): {
                if (x < get_board().length && get_board()[y][x + 1] instanceof Wall)
                    return false;
                else if (x < get_board().length && get_master().getInteractionBoard()[y][x + 1] != null) {
                    if (get_master().getInteractionBoard()[y][x + 1] instanceof Pacman)
                        impact((Pacman) get_board()[y][x + 1]);
                    return false;
                }
                return true;
            }
            case ( 'w' ): {
                if (x > 0 && get_board()[y][x - 1] instanceof Wall)
                    return false;
                else if (x > 0 && get_master().getInteractionBoard()[y][x - 1] != null) {
                    if (get_master().getInteractionBoard()[y][x - 1] instanceof Pacman)
                        impact((Pacman) get_board()[y][x - 1]);
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void Killed(){
        get_master().RemoveFromGame(this);
    }

    private void TickDelay(int tick) {
        _delayTicks +=tick;
    }

    @Override
    public abstract void impact(Visitor v);
}


