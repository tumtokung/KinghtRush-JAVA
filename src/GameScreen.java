package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    public static final int Game_First_State =  0;
    public static final int Game_play_State =  1;
    public static final int Game_Over_State =  2;
    private Thread thread;
    private Character mainCharacter;
    private Character tutorialCharSlide,tutorialCharJumping;
    private Land land;
    private Clounds clounds;
    private EnemyManger enemyManger;
    private Item item;
    private int score = 0;


    public float health=100;
    private int gameState = Game_First_State;
    private BufferedImage background;
    private String ShowScore;
    private Font font,bigFont;



    public GameScreen(){
        font = new Font("Courier",Font.PLAIN,17);
        bigFont =  new Font("Courier",Font.BOLD,24);
        mainCharacter = new Character();
        tutorialCharSlide = new Character();
        tutorialCharJumping = new Character();
        thread = new Thread(this);
        land = new Land(this);
        mainCharacter.setX(70);
        mainCharacter.setY(240);
        clounds = new Clounds();
        enemyManger = new EnemyManger(mainCharacter,this);
        item = new Item(mainCharacter,this);
        background = Resource.getResourceImage("Data/background.png");

    }

    public void startGame(){
        thread.start();
    }
    @Override
    public void run() {
        while (true) {
            try {
                update();
                repaint();
                if (gameState == Game_play_State){
                    health-=0.005;
                    plusScore(1);
                }
                if(score <= 2000){
                    Thread.sleep(8);
                }else if(score > 2000 && score <= 10000){
                    Thread.sleep(5);
                }else if(score > 10000 && score <=20000  ){
                    Thread.sleep(3);
                }else {
                    Thread.sleep(2);
                }
                if(health <=0 ){
                    mainCharacter.setAlive(false);
                    mainCharacter.setState(mainCharacter.Dead);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void update(){
        switch (gameState){
            case Game_play_State:
                mainCharacter.update();
                clounds.update();
                land.update();
                enemyManger.update();
                item.update();
                if(mainCharacter.getAlive() == false){
                    gameState = Game_Over_State;
                }
                break;
            case Game_First_State:
                mainCharacter.update();
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void plusScore(int score) {
        this.score += score;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.decode("660000"));
        g.fillRect(0,300,getWidth(),getHeight());
        g.setColor(Color.decode("#8fdfeb"));
        g.fillRect(0,0,getWidth(),200);
        g.drawImage(background,0,0,null);

        switch (gameState){
            case Game_First_State:
                land.draw(g);
                g.setColor(Color.decode("#4a4266"));
                g.fillRect(150,30,500,270);
                g.setColor(Color.white);
                g.setFont(font);
                g.drawString("Please Enter For Play Game" ,getWidth()/2-110,80);
                tutorialCharJumping.setX(230);
                tutorialCharJumping.setY(140);
                tutorialCharJumping.state = tutorialCharJumping.Jumping;
                tutorialCharJumping.draw(g);
                g.drawString("Space for Jump" ,200,260);
                tutorialCharSlide.setX(470);
                tutorialCharSlide.setY(120);
                tutorialCharSlide.state = tutorialCharSlide.Dash;
                tutorialCharSlide.draw(g);
                g.drawString("Ctrl for Slide" ,455,260);
                break;
            case Game_play_State:
                clounds.draw(g);
                land.draw(g);
                enemyManger.draw(g);
                item.draw(g);
                mainCharacter.draw(g);
                ShowScore = "Score: ";
                g.setFont(font);
                g.setColor(Color.white);
                g.drawString(ShowScore+String.valueOf(score) ,680,25);
                g.setColor(Color.RED);
                g.fillRect(220,10,(int)health*4,22);
                g.setColor(Color.black);
                g.drawRect(219,9,400,23);
                break;
            case Game_Over_State:
                clounds.draw(g);
                land.draw(g);
                enemyManger.draw(g);
                mainCharacter.draw(g);
                g.setColor(Color.decode("#4a4266"));
                g.fillRect(150,30,500,270);
                g.setColor(Color.white);
                g.setFont(bigFont);
                g.drawString("G A M E O V E R",getWidth()/2-85,80);
                g.setFont(font);
                ShowScore = "Your Score ";
                g.drawString(ShowScore+String.valueOf(score),getWidth()/2-100+40,150);
                g.drawString("Enter for Restart Game",getWidth()/2-80,200);
                break;
        }

    }
    private void resetGame(){
        setScore(0);
        health = 100;
        mainCharacter.setState(mainCharacter.Run);
        mainCharacter.setAlive(true);
        mainCharacter.setX(70);
        mainCharacter.setY(240);
        enemyManger.reset();
        item.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if (gameState == Game_play_State){
                    mainCharacter.jump();

                }
                break;
                case  KeyEvent.VK_CONTROL:
                    if (gameState == Game_play_State){
                            mainCharacter.down(true);
                    }
                    break;
                    case KeyEvent.VK_ENTER:
                        if(gameState == Game_First_State){
                            gameState = Game_play_State;
                        }else if (gameState == Game_Over_State) {
                            resetGame();
                            gameState = Game_play_State;
                        }

        }
    }
}
