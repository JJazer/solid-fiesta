package com.best.dex.endllessgame;

/**
 * Created by matti on 16.11.2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class AchiV {

    private int x;
    private int y;
    private int y2;
    private Bitmap bmp;
    private GameView gameview;


    private int mColumnWidth = 4;
    private int mColumnHeight = 1;

    private int width;
    private int height;

    private int mcurrentFrame = 1;
    private Rect playerr;
    private Rect buttr;

    public AchiV(GameView gameview, Bitmap bmp, int x, int y)
    {
        this.x = x;;
        this.y=y;
        this.gameview = gameview;
        this.bmp = bmp;
        this.width = bmp.getWidth()/mColumnWidth;
        this.height = bmp.getHeight()/mColumnHeight;
        //this.mcurrentFrame = state;

    }
    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void update()
    {

    }
    public int getState(){
        return mcurrentFrame;
    }
    public boolean checkCollision(Rect playerr, Rect buttr){

        return Rect.intersects(playerr, buttr);
    }
    public Rect GetBounds()
    {
        return new Rect(this.getX(),this.y,this.getX()+width,this.y+height);
    }

    public void onDraw(Canvas canvas){
        update();
        //int srcX = mcurrentFrame*width;

        canvas.drawBitmap(bmp,x,y,null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
