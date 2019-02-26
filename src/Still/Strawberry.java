package Still;

import Board.Game;

import javax.swing.*;

public class Strawberry extends Fruit{

    private final int points = 300;
    private static final ImageIcon icon = new ImageIcon("Icons/Pickupable/Strawberry.png");

    public Strawberry(Game master) {
        super(master);
    }

    public static ImageIcon getIcon() {
        return icon;
    }

    public int getPoints() {
        return points;
    }
}
