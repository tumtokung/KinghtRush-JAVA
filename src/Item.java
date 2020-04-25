package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Item {
    private List<Enemy> enemyList;
    private Character character;
    private BufferedImage potionImage;
    private GameScreen gameScreen;
    private Random random;


    public Item(Character character,GameScreen gameScreen){
        this.character = character;
        this.gameScreen = gameScreen;
        enemyList = new ArrayList<Enemy>();
        potionImage = Resource.getResourceImage("Data/potion.png");

        random = new Random();
        enemyList.add(getRandomBlock());
    }
    public void update(){
        for (Enemy e : enemyList) {
            e.update();
            if(e.getBound().intersects(character.getBound())){
                if(gameScreen.health <= 100){
                    enemyList.remove(enemyList.get(0));
                    enemyList.add(getRandomBlock());
                    gameScreen.health +=5;
                }
            }
        }
        Enemy  firstEnemy = enemyList.get(0);
        if (enemyList.get(0).isOutOfScreen()){
            enemyList.remove(firstEnemy);
            enemyList.add(getRandomBlock());
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemyList) {
            e.draw(g);
        }
    }
    public void reset(){
        enemyList.clear();
        enemyList.add(getRandomBlock());
    }

    private Block getRandomBlock() {
        Block block;
        block = new Block(character);
        block.setX(4000);
        block.setImage(potionImage);
        int i = random.nextInt(4);
        switch (i){
            case 0:
                block.setY(100);
                break;
            case 1:
                block.setY(150);
                break;
            case 2:
                block.setY(200);
                break;
            default:
                block.setY(250);
                break;
        }
        return block;
    }
}
