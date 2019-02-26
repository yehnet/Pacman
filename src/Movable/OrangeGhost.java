package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class OrangeGhost extends ArmedGhost {
    public static ImageIcon icon = new ImageIcon("Icons/Ghosts/GhostOrange.png");
    private static final double _speed = 0.667;


    public OrangeGhost(Game master, Point position, String[] path) {
        super(master, position, _speed, path);
    }

    public static ImageIcon getIcon() {
        return icon;
    }

    @Override
    public void impact(Visitor v){
        v.visit(this);
    }

    @Override
    public void Shoot() {
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        WaterSplash splash;
        switch (get_direction()){
            case ('n'):
                splash = new WaterSplash(get_master(), get_direction(), new Point(x,y-1));
                get_master().getInteractionBoard()[y-1][x] = splash;
                get_timer().AddCharacter(splash);
                break;
            case ('e'):
                splash = new WaterSplash(get_master(), get_direction(), new Point(x+1,y));
                get_master().getInteractionBoard()[y][x+1] = splash;
                get_timer().AddCharacter(splash);
                break;
            case ('s'):
                splash = new WaterSplash(get_master(), get_direction(), new Point(x,y+1));
                get_master().getInteractionBoard()[y+1][x] = splash;
                get_timer().AddCharacter(splash);
                break;
            case ('w'):
                splash = new WaterSplash(get_master(), get_direction(), new Point(x-1,y));
                get_master().getInteractionBoard()[y][x-1] = splash;
                get_timer().AddCharacter(splash);
                break;
        }
    }
}

