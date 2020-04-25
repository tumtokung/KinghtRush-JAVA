package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Character {
    public static final float ground=300;
    public static final float gravity = 0.1f;
    private float x = 0 ,y=0;
    private float speedY=0;
    public static final int Run=0,Dash=1,Jumping=2,Dead=3,crash=4;
    public int state = Run;
    private int jumpCheck=0;

    private Rectangle rectBound;
    public Animation charRun;
    public Animation charDash;
    private BufferedImage jumpingImage;
    private BufferedImage deadImage;
    private BufferedImage crashImage;
    private boolean isAlive = true;

    public Character(){
        charRun = new Animation(80);
        charRun.addFrame(Resource.getResourceImage("Data/Knight_walk_01.png"));
        charRun.addFrame(Resource.getResourceImage("Data/Knight_walk_02.png"));
        charRun.addFrame(Resource.getResourceImage("Data/Knight_walk_03.png"));
        charRun.addFrame(Resource.getResourceImage("Data/Knight_walk_04.png"));
        charRun.addFrame(Resource.getResourceImage("Data/Knight_walk_05.png"));
        charRun.addFrame(Resource.getResourceImage("Data/Knight_walk_06.png"));
        charDash = new Animation(80);
        charDash.addFrame(Resource.getResourceImage("Data/Knight_dash_01.png"));
        charDash.addFrame(Resource.getResourceImage("Data/Knight_dash_02.png"));
        deadImage =Resource.getResourceImage("Data/Knight_die.png");
        jumpingImage = Resource.getResourceImage("Data/Knight_jump.png");
        crashImage = Resource.getResourceImage("Data/Knight_crouch_0.png");

    }

    public void update(){
        charRun.update();
        charDash.update();
        if(y >= ground - charRun.getFrame().getHeight()) {
            speedY = 0;
            y = ground - charRun.getFrame().getHeight();
            if (state != Dash){
                state = Run;

            }
        }
        else{
            speedY+=gravity;
            y+=speedY;
        }
    }

    public Rectangle getBound() {
        rectBound = new Rectangle();
        if (state == 1){
            rectBound.x = (int) x + 5;
            rectBound.y = (int) y + 20;
            rectBound.width = charDash.getFrame().getWidth() - 10;
            rectBound.height = charDash.getFrame().getHeight();
        }else {
            rectBound.x = (int) x + 5;
            rectBound.y = (int) y;
            rectBound.width = charRun.getFrame().getWidth() - 10;
            rectBound.height = charRun.getFrame().getHeight();
        }
        return rectBound;

    }
    public void draw(Graphics g){
        switch (state){
            case Run:
                g.drawImage(charRun.getFrame(),(int)x,(int)y,null);
                jumpCheck = 0;
                break;
            case Dash:
                g.drawImage(charDash.getFrame(),(int)x,(int)(y+20),null);
                break;
            case Jumping:
                g.drawImage(jumpingImage,(int)x,(int)y,null);
                break;
            case Dead:
                g.drawImage(deadImage,(int)x,(int)y,null);
                break;
            case crash:
                g.drawImage(crashImage,(int)x,(int)y,null);
                break;


        }

    }

    public void jump(){
        if(y >= ground - (charRun.getFrame().getHeight()*3) && jumpCheck<2) {
            jumpCheck +=1;
            speedY = -4;
            y += speedY;
            state = Jumping;
        }
    }
    public void down(boolean isDown){
            if (state == Jumping ){
                return;
            }
            if (isDown){
                state = Dash;
            }
            else {
                state = Run;
            }
    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAlive(boolean alive){
        isAlive = alive;
    }
    public boolean getAlive(){
        return isAlive;
    }
}
