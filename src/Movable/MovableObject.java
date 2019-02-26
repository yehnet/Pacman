package Movable;

import Board.Game;
import Board.GameTimer;

import java.awt.*;

public abstract class MovableObject implements Movable{
    //-------------------fields-----------------------
    private Game _master;
    private Point _position;
    private double _velocity;
    private boolean _initiated;
    private char _direction;//N- up, E- right, S- down, W- left, C- center
    private double _ticks;


    //-------------------constructor------------------
    MovableObject(Game master, Point position, double velocity) {
        _master = master;
        _position = position;
        _velocity = velocity;
        _initiated = false;
        _direction = 'C';
        _ticks = 0;
    }

    //-------------------methods----------------------
    public void Init(){
        _initiated = true;
    }

    public boolean is_initiated() {
        return _initiated;
    }

    void IncreaseTicksBy(double tick){
        _ticks +=tick;
    }

    void ClearTicks(){
        _ticks -= get_velocity();
    }

    @Override
    public void Tick(double tick){
        IncreaseTicksBy(tick);
        if (is_initiated()& get_ticks() >=get_velocity()) {
            this.Move();
            ClearTicks();
        }
    }

    @Override
    public void Move(){
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        if (CanMove()) {
            switch (get_direction()) {
                case ( 'n' ): {
                    get_board()[y - 1][x] = this;
                    set_position(x, y - 1);
                    get_board()[y][x] = null;
                    break;
                }
                case ( 's' ): {
                    get_board()[y + 1][x] = this;
                    set_position(x, y + 1);
                    get_board()[y][x] = null;
                    break;
                }
                case ( 'e' ): {
                    get_board()[y][x + 1] = this;
                    set_position(x + 1, y);
                    get_board()[y][x] = null;
                    break;
                }
                case ( 'w' ): {
                    get_board()[y][x - 1] = this;
                    set_position(x - 1, y);
                    get_board()[y][x] = null;
                    break;
                }
            }
            ChooseDirection();
        }
    }

    public Object[][] get_board() {
        return get_master().getGameBoard();
    }

    public abstract void Killed();

    public void set_direction(char direction){
        this._direction = direction;
    }

    public Point get_position() {
        return _position;
    }

    public void set_position(int x, int y) {
        get_position().setLocation(x,y);
    }

    char get_direction() {
        return _direction;
    }

    double get_ticks() {
        return _ticks;
    }

    double get_velocity() {
        return _velocity;
    }

    public Game get_master() {
        return _master;
    }

    GameTimer get_timer() {
        return get_master().get_timer();
    }

    public abstract void ChooseDirection();

    public void Pause(){
        _initiated = false;
    }
}
