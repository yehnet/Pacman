package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class RedPacman extends Pacman {
    public static ImageIcon icon = new ImageIcon("Icons/Pacmans/RedPacman.png");

    public RedPacman(Game master, Point position) {
        super(master, position);
    }

    @Override
    public void visit(GreenGhost g) {
        get_master().RemoveFromGame(g);
    }

    @Override
    public void visit(OrangeGhost g) {
        get_master().FreezeGhost();
    }

    @Override
    public void visit(WaterSplash p) {
        get_master().FreezeGhost();
    }

    @Override
    public void visit(RedGhost g) {
        get_master().PacmanDied();
    }

    @Override
    public void visit(FireBall p) {
        get_master().PacmanDied();
    }

    public static ImageIcon get_icon() {
        return icon;
    }
}
