package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends Enemy {

    private BufferedImage blockImage;
    public int posX,posY;
    private Rectangle rectangle;
    private Character character;
    private boolean isScoreGot = false;

    public Block(Character character){
        this.character = character;
        blockImage = Resource.getResourceImage("Data/cactus1.png");
        posX = 500;
        posY = 260;
        rectangle = new Rectangle();
    }
    public void update(){
        posX -=2;
        rectangle.x = posX;
        rectangle.y = posY;
        rectangle.width = blockImage.getWidth();
        rectangle.height = blockImage.getHeight();
    }
    @Override
    public Rectangle getBound() {
        return rectangle;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(blockImage,posX,posY,null);
    }

    public void setX(int x){
        posX = x;
    }
    public void setY(int y){
        posY = y;
    }

    public void setImage(BufferedImage image){
        this.blockImage = image;
    }

    @Override
    public boolean isOutOfScreen(){
        return (posX + blockImage.getWidth() < 0);
    }

}
