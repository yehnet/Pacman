package Board;

import Movable.MovableObject;
import Still.Fruit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class GameTimer implements ActionListener {
    private Game _master;
    private List<MovableObject> _characters;
    private List<Fruit> _fruits;
    private Timer _timer;
    public int _tick;
    private boolean _dTime;
    private List<MovableObject> _charToAdd = new LinkedList<>();
    private List<Fruit> _fruitToAdd = new LinkedList<>();
    private List<MovableObject> _charToRemove = new LinkedList<>();
    private List<Fruit> _fruitToRemove = new LinkedList<>();
    private double _second;


    GameTimer(Game master){
        _master = master;
        _characters = new Vector<>(6);
        _fruits = new Vector<>();
        _tick = 167;
        _second = 0;
        _timer = new Timer(_tick,this);
        _dTime = false;
    }

    public void AddCharacter(MovableObject object){
        _charToAdd.add(object);
    }

    void AddFruit(Fruit fruit){
        _fruitToAdd.add(fruit);
    }

    void RemoveCharacter(MovableObject object){
        _charToRemove.add(object);
    }

    void RemoveFruit(Fruit fruit){
        _fruitToRemove.add(fruit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==_timer){
            if (!_master.isPaused()) {
                for (MovableObject m : _characters)
                    m.Tick(( (double) _tick ) / 1000);
                for (Fruit fruit : _fruits)
                    fruit.Tick(( (double) _tick ) / 1000);
            }
            _master.Tick(((double)_tick)/1000);
            _characters.addAll(_charToAdd);
            _fruits.addAll(_fruitToAdd);
            _charToAdd.clear();
            _fruitToAdd.clear();
            _characters.removeAll(_charToRemove);
            _fruits.removeAll(_fruitToRemove);
            _charToRemove.clear();
            _fruitToRemove.clear();
            _second += ((double)_tick)/1000;
            if (_second >= 1){
                _master.UpdateTime();
                _second -= 1;
            }
        }
    }

    void DoubleTime(){
        if (_dTime) {
            _tick = _tick / 2;
            _dTime = false;
        }
        else {
            _tick = _tick * 2;
            _dTime = true;
        }
    }

    void Pause() {
        _timer.stop();
        for (MovableObject character : _characters)
            character.Pause();
    }

    void Resume() {
        _timer.start();
    }

}
