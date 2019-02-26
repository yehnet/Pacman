package Still;

import Board.Game;

import javax.swing.*;

public class Apple extends Fruit{

    private final int points = 200;
    private static final ImageIcon icon = new ImageIcon("Icons/Pickupable/Apple.png");

    public Apple(Game master) {
        super(master);
    }

    public static ImageIcon getIcon() {
        return icon;
    }

    public int getPoints() {
        return points;
    }
}
