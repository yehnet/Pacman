package Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WelcomeWindow extends JFrame implements MouseListener,ActionListener {

    private Maze[] _mazes = new Maze[3];

    private JButton  mazeOne,mazeTwo,mazeThree;


    WelcomeWindow(){
        super("Pacman");
        loadMazes();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JLabel background = new JLabel();

        mazeOne = new JButton();
        mazeOne.addActionListener(this);
        mazeTwo = new JButton();
        mazeTwo.addActionListener(this);
        mazeThree = new JButton();
        mazeThree.addActionListener(this);

        try{
            Image tImg = ImageIO.read(new File("Icons/Maze/one140.png"));
            mazeOne.setIcon(new ImageIcon(tImg));
            Image tImg2 = ImageIO.read(new File("Icons/Maze/two140.png"));
            mazeTwo.setIcon(new ImageIcon(tImg2));
            Image tImg3 = ImageIO.read(new File("Icons/Maze/three140.png"));
            mazeThree.setIcon(new ImageIcon(tImg3));
            Image tImg4 = ImageIO.read(new File("Icons/menu/welcomebackground3.jpeg"));
            background.setIcon(new ImageIcon(tImg4));
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Error:" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        panel1.add(mazeOne,BorderLayout.WEST);
        panel1.add(mazeTwo,BorderLayout.CENTER);
        panel1.add(mazeThree,BorderLayout.EAST);
        setContentPane(background);
        setLayout(new GridBagLayout());
        add(panel1);
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadMazes(){
        String line;
        String[][] temp ;
        String[][] paths ;
        int m=0;
        try {
            Scanner inputCSV = new Scanner(new File("Maze.csv"));
            while (inputCSV.hasNextLine()) {
                line = inputCSV.nextLine();
                paths = new String[3][];
                temp = new String[32][32];
                for ( int i = 0 ; i < 32 ; i++){
                    temp[i] = line.split(",");
                    if (inputCSV.hasNextLine() )
                        line = inputCSV.nextLine();
                    else throw new RuntimeException();
                }

                for ( int i = 0 ; i < 3 ; i++){
                    paths[i] = line.split(",");
                    if (inputCSV.hasNextLine() )
                        line = inputCSV.nextLine();
                    else throw new RuntimeException();
                }
                _mazes[m] = new Maze(temp,paths);
                m++;
            }
        } catch (FileNotFoundException e) {//exception handling
            JOptionPane.showMessageDialog(this, "Error:" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    public static void main(String[] args){
        new WelcomeWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mazeOne){
            dispose();
            new Game(_mazes);
        }
        if (e.getSource() == mazeTwo){
            dispose();
            Maze temp = _mazes[0];
            _mazes[0] = _mazes[1];
            _mazes[1] = temp;
            new Game(_mazes);
        }
        if (e.getSource() == mazeThree){
            dispose();
            Maze temp = _mazes[0];
            _mazes[0] = _mazes[2];
            _mazes[2] = temp;
            new Game(_mazes);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

