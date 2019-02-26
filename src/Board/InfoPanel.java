package Board;

import Movable.BluePacman;
import Movable.RedPacman;
import Movable.YellowPacman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InfoPanel extends JPanel implements ActionListener {
    //-------------------fields-----------------------
        private Game _master;
        private int _score;
        private int _lives;
        private int _level;
        private int _time;
        private JLabel scoreLabel,levelLabel,timeLabel;
        private JPanel livesLabel;
        private JButton doubleTime;
        private BufferedImage background;
    //-------------------constructor------------------
    InfoPanel(Game master){
        _master = master;
        _score = 0;
        _lives = 3;
        _level = 1;
        _time = 0;

        try {
            background = ImageIO.read(new File("Icons/menu/infobackgroundorg2.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

        scoreLabel = new JLabel("SCORE : " + get_score() + "    ");
        scoreLabel.setSize(50,100);
        //scoreLabel.setForeground(Color.white);
        livesLabel = new JPanel();
        setLives();
        //livesLabel.setForeground(Color.white);
        timeLabel = new JLabel(getMinuteString(get_time()));
        timeLabel.setSize(50,10);
        //timeLabel.setForeground(Color.white);
        levelLabel = new JLabel("Level: "  + get_level());
        levelLabel.setSize(50,10);
        //levelLabel.setForeground(Color.white);
        doubleTime = new JButton(new ImageIcon("Icons/menu/doublespeed.jpg"));
        doubleTime.addActionListener(this);
        //doubleTime.setIcon(new ImageIcon("Icons/menu/doublespeed.jpg"));

        //JLabel background = new JLabel(new ImageIcon("Icons/menu/infobackground.jpg"));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scoreLabel);
        add(levelLabel);
        add(timeLabel);
        add(livesLabel);
        add(doubleTime);
        doubleTime.setFocusable(false);
    }
    //-------------------methods----------------------
    public void paint (Graphics g){
        super.paint(g);
        //g.drawImage(background, 0, 0, this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }

    void IncreaseScore(int points) {
        _score += points;
        scoreLabel.setText("SCORE : " + get_score());
    }

    void AdvanceLevel() {
        _level++;
        levelLabel.setText("Level : " + get_level());
    }

    void LostLife() {
        _lives--;
        if (_lives > -1)
            livesLabel.remove(_lives);
    }

    void UpdateTime(){
        timeLabel.setText(getMinuteString(++_time));
    }

    int get_score() {
        return _score;
    }

    int get_lives() {
        return _lives;
    }

    int get_level() {
        return _level;
    }

    int get_time() {
        return _time;
    }

    private String getMinuteString( int seconds ) {
        StringBuilder timerText = new StringBuilder() ;
        int min = seconds / 60 ;
        int sec = seconds - ( min * 60 ) ;
        timerText.append("Time: ");
        if( min < 1 )
            timerText.append( 0 ) ;
        timerText.append( min ) ;
        timerText.append( ":") ;
        if( sec < 10 )
            timerText.append( 0 ) ;
        timerText.append(sec).append(" ");
        return timerText.toString() ;
    }

    private void setLives(){
        ImageIcon icon;
        switch(_level){
            case(1):
                icon = YellowPacman.get_icon();
                livesLabel.setLayout(new FlowLayout());
                livesLabel.add(new JLabel(icon));
                livesLabel.add(new JLabel(icon));
                livesLabel.add(new JLabel(icon));
                break;
            case(2):
                icon = BluePacman.get_icon();
                livesLabel.setLayout(new FlowLayout());
                livesLabel.add(new JLabel(icon));
                livesLabel.add(new JLabel(icon));
                livesLabel.add(new JLabel(icon));
                break;
            case(3):
                icon = RedPacman.get_icon();
                livesLabel.setLayout(new FlowLayout());
                livesLabel.add(new JLabel(icon));
                livesLabel.add(new JLabel(icon));
                livesLabel.add(new JLabel(icon));
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doubleTime)
            _master.DoubleTime();
    }
}
