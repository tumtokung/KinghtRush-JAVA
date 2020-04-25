package com.company;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
public class Main extends JFrame{


    private GameScreen gameScreen;

    public Main(){
        super("Knight Rush");
        setSize(800,360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);

    }
    public void startGame(){
        gameScreen.startGame();
    }
    public static void main(String[] args) {
	    Main GameWin = new Main();
	    GameWin.setVisible(true);
	    GameWin.startGame();
    }

}
