package Still;

import javax.swing.*;

public class Pill implements Edible{

    private static final int points = 10;
    public static ImageIcon icon = new ImageIcon("Icons/Pickupable/NormalPill.png");

    public Pill() {}

    public int getPoints() {
        return points;
    }

    public static ImageIcon getIcon() {
        return icon;
    }
}
