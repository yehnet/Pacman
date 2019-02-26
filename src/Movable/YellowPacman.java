package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class YellowPacman extends Pacman {
    //-------------------fields-----------------------
    public static ImageIcon icon = new ImageIcon("Icons/Pacmans/YellowPacman.png");

    //-------------------constructor------------------
    public YellowPacman(Game master, Point position) {
        super(master, position);
    }

    //-------------------methods----------------------
    @Override
    public void visit(GreenGhost g) {
        get_master().PacmanDied();
    }

    @Override
    public void visit(OrangeGhost g) {
        //nothing happens
    }

    @Override
    public void visit(RedGhost g) {
        //nothing happens
    }

    @Override
    public void visit(FireBall p) {
        //nothing happens
    }

    @Override
    public void visit(WaterSplash p) {
        //nothing happens
    }

    public static ImageIcon get_icon() {
        return icon;
    }
}

