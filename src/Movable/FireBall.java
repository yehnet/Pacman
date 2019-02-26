package Movable;

import Board.Game;

import javax.swing.*;
import java.awt.*;

public class FireBall extends Power {
    public static ImageIcon icon = new ImageIcon("Icons/GhostsWeapons/FireBall2.png");

    private static final double speed = 0.2;


    FireBall(Game master, char direction, Point p) {
        super(master, direction, p, speed);
    }

    @Override
    public boolean CanMove() {
        int x = (int) get_position().getX();
        int y = (int) get_position().getY();
        switch (get_direction()) {
            case ( 'n' ): {
                if (y >= 0 && get_master().getInteractionBoard()[y-1][x] != null) {
                    if (get_master().getInteractionBoard()[y-1][x] instanceof Pacman)
                        impact((Pacman) get_board()[y - 1][x]);
                    Killed();
                    return false;
                }
                return true;
            }
            case ( 's' ): {
                if (y <= get_board().length && get_master().getInteractionBoard()[y + 1][x] != null) {
                    if (get_master().getInteractionBoard()[y + 1][x] instanceof Pacman)
                        impact((Pacman) get_board()[y + 1][x]);
                    Killed();
                    return false;
                }
                return true;
            }
            case ( 'e' ): {
                if (x <= get_board().length && get_master().getInteractionBoard()[y][x + 1] != null) {
                    if (get_master().getInteractionBoard()[y][x + 1] instanceof Pacman)
                        impact((Pacman) get_board()[y][x + 1]);
                    Killed();
                    return false;
                }
                return true;
            }
            case ( 'w' ): {
                if (x >= 0 && get_master().getInteractionBoard()[y][x - 1] != null) {
                    if (get_master().getInteractionBoard()[y][x - 1] instanceof Pacman)
                        impact((Pacman) get_board()[y][x - 1]);
                    Killed();
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void impact(Visitor v) {
        v.visit(this);
    }

    public static ImageIcon getIcon() {
        return icon;
    }

}
