package Board;

import Movable.*;
import Still.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class MazePanel extends JPanel{
    //-------------------fields-----------------------
    private Game _master;
    private String[][] _tunnelsAndWalls;
    private Object[][] _board;
    private String[][] _paths;
    private MovableObject[][] _interaction;
    private Pacman _pacman;
    private ImageIcon _pacmanIcon;
    private int _level;

    //-------------------constructor------------------
    MazePanel(Game master, Maze maze, int level) {
        _master = master;
        _tunnelsAndWalls = maze.get_board();
        _paths = maze.get_paths();
        _level = level;
        _board = new Object[32][32];
        _interaction = new MovableObject[32][32];
        setPreferredSize(new Dimension(800,800));
        InitBoard();
    }

    private void InitBoard() {
        for (int  i = 0; i < 32 ; i ++){
            for (int j = 0 ; j < 32 ; j ++){
                switch (_tunnelsAndWalls[j][i]) {
                    case ("0"): {//tunnel
                        _board[j][i] = null;
                        _interaction[j][i] = null;
                        break;
                    }
                    case ("1"):{//wall
                        _board[j][i] = new Wall();
                        _interaction[j][i] = null;
                        break;
                    }
                    case ("2"):{//cage door
                        _board[j][i] = null;
                        _interaction[j][i] = null;
                        break;
                    }
                    case ("3"):{//pill
                        _board[j][i] = new Pill();
                        _interaction[j][i] = null;
                        break;
                    }
                    case ("4"):{//energy pill
                        _board[j][i] = new EnergyPill();
                        _interaction[j][i] = null;
                        break;
                    }
                    case ("5"):{//pacman
                        switch (_level){
                            case (1):
                                _pacman = new YellowPacman(_master, new Point(i, j));
                                _pacmanIcon = YellowPacman.get_icon();
                                break;
                            case (2):
                                _pacman = new BluePacman(_master, new Point(i, j));
                                _pacmanIcon = BluePacman.get_icon();
                                break;
                            case (3):
                                _pacman = new RedPacman(_master, new Point(i, j));
                                _pacmanIcon = RedPacman.get_icon();
                                break;
                        }
                        _master.set_pacman(_pacman);
                        _board[j][i] = _pacman;
                        _interaction[j][i] = _pacman;
                        break;
                    }
                    case ("6"):{//O ghost
                        OrangeGhost _og = new OrangeGhost(_master, new Point(i, j), _paths[1]);
                        _master.set_og(_og);
                        _board[j][i] = null;
                        _interaction[j][i] = _og;
                        break;
                    }
                    case ("7"):{//G ghost
                        GreenGhost _gg = new GreenGhost(_master, new Point(i, j), _paths[0]);
                        _master.set_gg(_gg);
                        _board[j][i] = null;
                        _interaction[j][i] = _gg;
                        break;
                    }
                    case ("8"):{//R ghost
                        RedGhost _rg = new RedGhost(_master, new Point(i, j), _paths[2]);
                        _master.set_rg(_rg);
                        _board[j][i] = null;
                        _interaction[j][i] = _rg;
                    }
                }
            }
        }
    }

    //-------------------methods----------------------
    public void paint(Graphics g){
        super.paint(g);
        int blockSize = 18;// resolution issues on laptop - CHANGE TO 25
        for (int  i = 0; i < 32 ; i ++){
            for (int j = 0 ; j < 32 ; j ++){
                Object obj = _board[j][i];
                Object character = _interaction[j][i];
                if (obj == null) {//tunnel
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                }
                else if (obj instanceof Wall){//wall
                    g.setColor(Color.BLUE);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                }
                else if (obj instanceof Pill) {//pills
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    Pill.getIcon().paintIcon(this, g, i * blockSize + 2, j * blockSize + 2);
                }
                else if (obj instanceof EnergyPill) {//Energy pill
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    EnergyPill.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (obj instanceof Pacman) {//pacman start location
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    getPacmanIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (obj instanceof Apple) {//Energy pill
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    Apple.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (obj instanceof Pineapple) {//Energy pill
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    Pineapple.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (obj instanceof Strawberry) {//Energy pill
                    g.setColor(Color.black);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    Strawberry.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                if (character instanceof OrangeGhost) {//orange ghost start location
                    g.setColor(Color.BLACK);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    OrangeGhost.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (character instanceof GreenGhost) {//green ghost start location
                    g.setColor(Color.BLACK);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    GreenGhost.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (character instanceof RedGhost) {//red ghost start location
                    g.setColor(Color.BLACK);
                    g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);
                    RedGhost.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                if (character instanceof WaterSplash) {//WaterSplash power
                    WaterSplash.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
                else if (character instanceof FireBall) {//FireBall power
                    FireBall.getIcon().paintIcon(this, g, i * blockSize, j * blockSize);
                }
            }
        }
    }

    private ImageIcon getPacmanIcon(){
        return _pacmanIcon;
    }

    public Object[][] get_board() {
        return _board;
    }

    MovableObject[][] get_interaction() {
        return _interaction;
    }

    List<Pill> get_pills() {
        List<Pill> pills = new Vector<>(240);
        for (int  i = 0; i < 32 ; i ++)
            for (int j = 0; j < 32; j++)
                if (get_board()[i][j] instanceof Pill)
                    pills.add((Pill)get_board()[i][j]);
        return pills;
    }

    void InitPacman(Pacman pacman) {
        int x = (int) pacman.get_position().getX();
        int y = (int) pacman.get_position().getY();
        get_board()[y][x] = null;
        get_interaction()[y][x] = null;
        x = 1;
        y = 30;
        pacman.set_position(x,y);
        get_board()[y][x] = pacman;
        get_interaction()[y][x] = pacman;
    }

    void AddFruit(Fruit fruit) {
        int x = 0;
        int y = 0;
        while (get_board()[y][x]!=null | get_interaction()[y][x] != null){
            x = (int) (Math.random()*32);
            y = (int) (Math.random()*32);
        }
        get_board()[y][x] = fruit;
        fruit.set_position(new Point(x,y));
    }

    void RemoveFruit(Fruit fruit){
        int x = (int) fruit.get_position().getX();
        int y = (int) fruit.get_position().getY();
        get_board()[y][x] = null;
    }
}
