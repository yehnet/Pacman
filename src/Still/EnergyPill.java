package Still;

import javax.swing.*;

public class EnergyPill implements Edible{

    private final int points = 50;
    public static ImageIcon icon = new ImageIcon("Icons/Pickupable/EnergyPill.png");

    public EnergyPill() {}

    public int getPoints() {
        return points;
    }

    public static ImageIcon getIcon() {
        return icon;
    }
}
