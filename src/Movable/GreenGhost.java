package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class GreenGhost extends Ghost {
    //-------------------fields-----------------------
    public static ImageIcon icon = new ImageIcon("Icons/Ghosts/GhostGreen.png");

    private static final double speed = 0.334;

    //-------------------constructor------------------
    public GreenGhost(Game master, Point position, String[] path) {
        super(master, position, speed, path);
    }

    //-------------------methods----------------------
    public static ImageIcon getIcon() {
        return icon;
    }

    @Override
    public void impact(Visitor v) {
        v.visit(this);
    }
}

