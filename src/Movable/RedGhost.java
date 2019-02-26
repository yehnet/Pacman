package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class RedGhost extends ArmedGhost {
    public static ImageIcon icon = new ImageIcon("Icons/Ghosts/GhostRed.png");
    private static final double speed = 1.0;

    public RedGhost(Game master, Point position, String[] path){
        super(master, position,speed, path);
    }

    public static ImageIcon getIcon() {
        return icon;
    }

    @Override
    public void impact(Visitor v) {
        v.visit(this);
    }

    @Override
    public void Shoot() {
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        FireBall fireBall;
        switch (get_direction()){
            case ('n'):
                fireBall = new FireBall(get_master(), get_direction(), new Point(x,y-1)) ;
                get_master().getInteractionBoard()[y-1][x] = fireBall;
                get_timer().AddCharacter(fireBall);
                break;
            case ('e'):
                fireBall = new FireBall(get_master(), get_direction(), new Point(x+1,y)) ;
                get_master().getInteractionBoard()[y][x+1] = fireBall;
                get_timer().AddCharacter(fireBall);
                break;
            case ('s'):
                fireBall = new FireBall(get_master(), get_direction(), new Point(x,y+1)) ;
                get_master().getInteractionBoard()[y+1][x] = fireBall;
                get_timer().AddCharacter(fireBall);
                break;
            case ('w'):
                fireBall = new FireBall(get_master(), get_direction(), new Point(x-1,y)) ;
                get_master().getInteractionBoard()[y][x-1] = fireBall;
                get_timer().AddCharacter(fireBall);
                break;
        }
    }
}

