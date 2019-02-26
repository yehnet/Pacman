package Still;

import Board.Game;

import java.awt.*;

public abstract class Fruit implements Edible{
    private Game _master;
    private Point _position;
    private final double _vanish = 5.0;
    private double _ticks;

    Fruit(Game master) {
        _master = master;
        _position = new Point(-1,-1);
    }

    public void Tick(double tick) {
        _ticks += tick;
        if (_ticks >= _vanish) {
            _master.RemoveFromGame(this);
            _ticks -= _vanish;
        }
    }

    public Point get_position() {
        return _position;
    }

    public void set_position(Point _position) {
        this._position = _position;
    }
}
