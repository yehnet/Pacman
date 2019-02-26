package Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class EndGame extends JFrame  implements KeyListener{


    EndGame(InfoPanel infoPanel) {
        JLabel background = new JLabel();
        JPanel panel = new JPanel();
        JLabel winOrLose = new JLabel ();
        if ( infoPanel.get_lives() > 0 ) {
            winOrLose.setText("Congratulations! you won!");
            winOrLose.setForeground(Color.black);
        }
        else {
            winOrLose.setText("GAME OVER!");
            winOrLose.setForeground(Color.black);
        }
        JLabel level = new JLabel("you reached level: " + infoPanel.get_level());
        level.setForeground(Color.black);
        JLabel score = new JLabel("with the score : " + infoPanel.get_score());
        score.setForeground(Color.black);
        JLabel lives = new JLabel("with the score : " + infoPanel.get_score());
        lives.setForeground(Color.black);
        JLabel time = new JLabel ("in time" + infoPanel.get_time());
        time.setForeground(Color.black);
        JLabel end = new JLabel("press any key to continue");
        end.setForeground(Color.black);

        try{
            Image tImg = ImageIO.read(new File("Icons/menu/endbackground.jpg"));
            background.setIcon(new ImageIcon(tImg));
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Error:" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setContentPane(background);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(winOrLose);
        panel.add(level);
        panel.add(score);
        panel.add(lives);
        panel.add(time);
        panel.add(end);
        setLayout(new GridBagLayout());
        add(panel);
        repaint();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
            dispose();
            new WelcomeWindow();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
