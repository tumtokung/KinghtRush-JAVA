package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Clounds {
    private BufferedImage cloudimage;
    private List<Cloud> cloudList;

    public Clounds(){
        cloudimage = Resource.getResourceImage("Data/cloud.png");
        cloudList = new ArrayList<Cloud>();

        Cloud cloud1 = new Cloud();
        cloud1.posX = 100;
        cloud1.posY = 50;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 120;
        cloud1.posY = 180;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 200;
        cloud1.posY = 30;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 220;
        cloud1.posY = 140;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 300;
        cloud1.posY = 80;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 470;
        cloud1.posY = 190;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 450;
        cloud1.posY = 100;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 600;
        cloud1.posY = 60;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 670;
        cloud1.posY = 150;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 720;
        cloud1.posY = 200;
        cloudList.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 750;
        cloud1.posY = 30;
        cloudList.add(cloud1);
    }

    public void update(){
        for (Cloud cloud : cloudList){
            cloud.posX --;
        }
        Cloud firstCloud = cloudList.get(0);
        if (firstCloud.posX  + cloudimage.getWidth() < 0){
            firstCloud.posX = 700;
            cloudList.remove(firstCloud);
            cloudList.add(firstCloud);
        }
    }
    public void draw(Graphics g){
        for (Cloud cloud:cloudList){
            g.drawImage(cloudimage,(int) cloud.posX,(int) cloud.posY,null);
        }
    }
    private class Cloud{
        float posX,posY;
    }


}
