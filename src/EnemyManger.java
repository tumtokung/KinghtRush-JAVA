package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManger {
    private List<Enemy> enemies;
    private Random random;

    private BufferedImage imageBlock1,imageBlock2,imageBlock3,imageBlock4;
    private Character character;
    private GameScreen gameScreen;

    public EnemyManger(Character character, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.character = character;
        enemies = new ArrayList<Enemy>();
        imageBlock1 = Resource.getResourceImage("Data/cactus1.png");
        imageBlock2 = Resource.getResourceImage("Data/stone.png");
        imageBlock3 = Resource.getResourceImage("Data/cactus3.png");
        imageBlock4 = Resource.getResourceImage("Data/landbr.png");

        random = new Random();

        enemies.add(getRandomBlock());

    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
            if(e.getBound().intersects(character.getBound())){
                gameScreen.health -=0.4f;
                character.setState(character.crash);
            }
        }
        Enemy  firstEnemy = enemies.get(0);
        if (enemies.get(0).isOutOfScreen()){
            enemies.remove(firstEnemy);
            enemies.add(getRandomBlock());
        }

    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }
    public void reset(){
        enemies.clear();
        enemies.add(getRandomBlock());
    }

    private Block getRandomBlock() {
        Block block;
        block = new Block(character);
        block.setX(600);
        int i = random.nextInt(4);
        switch (i){
            case 0:
                block.setY(240);
                block.setImage(imageBlock1);
                break;
            case 1:
                block.setY(240);
                block.setImage(imageBlock2);
                break;
            case 2:
                block.setY(210);
                block.setImage(imageBlock3);
                break;
            default:
                block.setY(210);
                block.setImage(imageBlock4);
                break;
        }
        return block;
    }
}