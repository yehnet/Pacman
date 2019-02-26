package Still;

import Board.Game;

import javax.swing.*;

public class Pineapple extends Fruit{

    private final int _points = 100;
    private static final ImageIcon icon = new ImageIcon("Icons/Pickupable/Pineapple.png");

    public Pineapple(Game master) {
        super(master);
    }

    public static ImageIcon getIcon() {
        return icon;
    }

    public int getPoints() {
        return _points;
    }
}
