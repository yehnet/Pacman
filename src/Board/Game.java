package Board;

import Movable.*;
import Still.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class Game extends JFrame implements KeyListener{
    private MazePanel _mazePanel;
    private InfoPanel _infoPanel;
    private JPanel _rightSide;
    //private JLabel _ready;

    private Maze[] _mazes;
    private Pacman _pacman;
    private GreenGhost _gg;
    private RedGhost _rg;
    private OrangeGhost _og;
    private List<Pill> _pills;
    private List<Fruit> _fruits;
    private double _fruitTicks;
    private final double _fruitDelay = 10;
    private GameTimer _timer;
    private double _freezeTicks;
    private double _cooldown;
    private MovableObject _frozen;
    private boolean _paused = false;
    private double _ghostTicks;

    public Game(Maze[] mazes) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        addKeyListener(this);

        _mazes = mazes;
        _fruits = new LinkedList<>();

        //_ready = new JLabel(new ImageIcon("Icons/menu/ready.png"));

        _rightSide = new JPanel();
        _rightSide.setLayout(new BorderLayout());

        _infoPanel = new InfoPanel(this);
        add(_rightSide);
        _rightSide.add(_infoPanel, BorderLayout.NORTH);

        InitLevel();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private void InitLevel() {
        _timer = new GameTimer(this);
        _frozen = null;
        _cooldown = 0;
        _freezeTicks = 0;
        CreateFruitList(getLevel());
        _mazePanel = new MazePanel(this, _mazes[getLevel()-1],getLevel());
        _timer.AddCharacter(_pacman);
        _timer.AddCharacter(_gg);
        _timer.AddCharacter(_og);
        _timer.AddCharacter(_rg);
        _pills = _mazePanel.get_pills();
        add(_mazePanel,BorderLayout.WEST);
        repaint();
        pack();
        setPreferredSize(getPreferredSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void CreateFruitList(int level) {
        get_fruits().clear();
        switch (level){
            case (3):
                get_fruits().add(new Pineapple(this));
                get_fruits().add(new Apple(this));
                get_fruits().add(new Strawberry(this));
            case (2):
                get_fruits().add(new Pineapple(this));
                get_fruits().add(new Pineapple(this));
                get_fruits().add(new Apple(this));
                get_fruits().add(new Apple(this));
                get_fruits().add(new Strawberry(this));
            case (1):
                get_fruits().add(new Pineapple(this));
                get_fruits().add(new Pineapple(this));
                get_fruits().add(new Apple(this));
                get_fruits().add(new Apple(this));
        }
    }

    void Tick(double tick){
        if (_pills.isEmpty())
            AdvanceLevel();
        else if (!_paused)
            _ghostTicks += tick;
            InitGhosts();
            if (_cooldown != 0 ) {
                _freezeTicks += tick;
                if (_cooldown >= _freezeTicks)
                    UnFreeze();
            }
            _fruitTicks += tick;
            if (_fruitTicks >= _fruitDelay){
                if (!get_fruits().isEmpty())
                    AddFruit(get_fruits().remove(0));
                _fruitTicks -= _fruitDelay;
            }
        repaint();
    }

    private void AddFruit(Fruit fruit) {
        _mazePanel.AddFruit(fruit);
        get_timer().AddFruit(fruit);
    }

    private void AdvanceLevel() {
        if (getLevel() == 3){
            dispose();
            new EndGame(_infoPanel);
        }
        else {
            _infoPanel.AdvanceLevel();
            remove(_mazePanel);
            InitLevel();
            repaint();
            pack();
        }
    }

    private int getLevel() {
        return _infoPanel.get_level();
    }

    private void UnFreeze() {
        _cooldown = 0;
        _freezeTicks = 0;
        _timer.AddCharacter(_frozen);
    }

    public void RemoveFromGame(Power power){
        _timer.RemoveCharacter(power);
    }

    public void PacmanDied(){
        _infoPanel.LostLife();
        if(getLives() == 0){
            dispose();
            new EndGame(_infoPanel);
        }
        else{
            Pause();
            _mazePanel.InitPacman(_pacman);
            _cooldown = 0;
            _fruitTicks = 0;
            _ghostTicks = 0;
            _freezeTicks = 0;
        }
    }

    private void Pause() {
        get_timer().Pause();
        _paused = true;
    }

    private void Resume() {
        get_timer().Resume();
        if (_paused){
            _paused = false;
            _gg.Init();
        }
        _pacman.Init();
    }

    private int getLives() {
        return _infoPanel.get_lives();
    }

    public void RemoveFromGame(Ghost ghost){
        _timer.RemoveCharacter(ghost);

    }

    public void RemoveFromGame(Ghost ghost, double cooldown) {
        _timer.RemoveCharacter(ghost);
        _cooldown = cooldown;
        _frozen = ghost;
    }

    public void RemoveFromGame(Fruit fruit) {
        _mazePanel.RemoveFruit(fruit);
        get_timer().RemoveFruit(fruit);
        get_fruits().add(fruit);
    }

    public void FreezePacman(){
        _timer.RemoveCharacter(_pacman);
        _cooldown = 3;
        _frozen = _pacman;
    }

    public void FreezeGhost() {
        _timer.RemoveCharacter(_og);
        _cooldown = 5;
        _frozen = _og;
    }

    private void InitGhosts() {
        if (!_gg.is_initiated() & _ghostTicks >= 7)
            _gg.Init();
        if (!_og.is_initiated() & _infoPanel.get_score() >= 500)
            _og.Init();
        if (!_rg.is_initiated() &_infoPanel.get_score() >= 800)
            _rg.Init();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Resume();
        }
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            Pause();
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            _pacman.set_direction('n');
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            _pacman.set_direction('e');
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            _pacman.set_direction('s');
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            _pacman.set_direction('w');
    }

    public GameTimer get_timer() { return _timer;}

    public Object[][] getGameBoard(){ return _mazePanel.get_board();}

    public MovableObject[][] getInteractionBoard() {return _mazePanel.get_interaction();}

    private void IncreaseScore(int points) {
        _infoPanel.IncreaseScore(points);
    }

    public void Eat(Edible edible) {
        IncreaseScore(edible.getPoints());
        if (edible instanceof Pill)
            _pills.remove(edible);
        else if (edible instanceof Fruit)
            RemoveFromGame((Fruit) edible);
    }

    void set_pacman(Pacman _pacman) {
        this._pacman = _pacman;
    }

    void set_gg(GreenGhost _gg) {
        this._gg = _gg;
    }

    void set_rg(RedGhost _rg) {
        this._rg = _rg;
    }

    void set_og(OrangeGhost _og) {
        this._og = _og;
    }

    boolean isPaused() {
        return _paused;
    }

    private List<Fruit> get_fruits() {
        return _fruits;
    }

    void DoubleTime() {
        get_timer().DoubleTime();
    }

    void UpdateTime() {
        _infoPanel.UpdateTime();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
