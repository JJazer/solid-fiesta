package com.best.dex.endllessgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class  Coin {

    private int x;
    private int y;
    private int y2;
    private Bitmap bmp;
    private GameView gameview;
    private int count = 0;

    private int xSpeed =-GameView.globalxSpeed;

    private int mColumnWidth = 4;
    private int mColumnHeight = 1;

    private int width;
    private int height;

    private int mcurrentFrame = 0;
    private Rect playerr;
    private Rect coinr;

    public Coin(GameView gameview, Bitmap bmp, int x, int y)
    {
        this.x = x;
        this.y2 = y;
        this.gameview = gameview;
        this.bmp = bmp;
        this.width = bmp.getWidth()/mColumnWidth;
        this.height = bmp.getHeight()/mColumnHeight;
    }

    public void update()
    {
        x += xSpeed;
        y = gameview.getHeight()-Ground.height-y2-bmp.getHeight();
        count += 1;

        if(count == 4) {
            if (mcurrentFrame >= (mColumnWidth - 1)) {
                mcurrentFrame = 0;
                count = 0;
            } else {
                mcurrentFrame += 1;
                count = 0;
            }

        }


    }

    public int returnX()
    {
        return x;
    }


    public boolean checkCollision(Rect playerr, Rect coinr){

        this.playerr = playerr;
        this.coinr = coinr;

        return Rect.intersects(playerr, coinr);
    }
    public Rect GetBounds()
    {
        return new Rect(this.x,this.y,this.x+width,this.y+height);
    }



    public void onDraw(Canvas canvas){
        update();
        int srcX = mcurrentFrame*width;
        Rect src = new Rect(mcurrentFrame*width,0,srcX + width,height);
        Rect dst = new Rect(x,y,x+width,y+height);
        canvas.drawBitmap(bmp,src,dst,null);
    }
}