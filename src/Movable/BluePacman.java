package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class BluePacman extends Pacman {
    public static ImageIcon icon = new ImageIcon("Icons/Pacmans/BluePacman.png");

    public BluePacman(Game master, Point position) {
        super(master, position);
    }


    @Override
    public void visit(GreenGhost g) {
        get_master().RemoveFromGame(g,5);
    }

    @Override
    public void visit(OrangeGhost g) {
        get_master().FreezePacman();
    }

    @Override
    public void visit(RedGhost g) {
        //nothing happens
    }

    @Override
    public void visit(WaterSplash p) {
        get_master().FreezePacman();
    }

    @Override
    public void visit(FireBall p) {
        //nothing happens
    }

    public static ImageIcon get_icon() {
        return icon;
    }
}

