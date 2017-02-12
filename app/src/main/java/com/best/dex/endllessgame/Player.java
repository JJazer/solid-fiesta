package com.best.dex.endllessgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {
    static int x;
    static int y;
    static int gravity = (int) 1.2;
    static int vspeed = 1;
    static int playerheight;
    static int playerwidth;
    static int jumppower = -20;

    private int width,height;

    private int PlayerColumnWidth = 4;
    private int PlayerAnimationColumn = 0;
    private int PlayerColumnHeight = 2;
    private int CurrentFrame = 0;
    private int PlayerAnimationPosy = 0;
    private int PlayerAnimationState = 0;

    private int jumpCounter = 0;

    Rect playerr;
    Bitmap bmp;
    GameView gameview;
    private int i;
    private Coin coin;
    public Player(GameView gameview, Bitmap bmp, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.gameview = gameview;
        this.bmp = bmp;

        this.width = bmp.getWidth()/PlayerColumnWidth;
        this.height = bmp.getHeight()/PlayerColumnHeight;

        playerheight=bmp.getHeight()/2;
    }
    public void update(){
        checkground();
        CheckAnimation();

        switchanimations();
    }
    public void CheckAnimation(){
        if (vspeed < 0){
            PlayerAnimationState = 2;
        }
        else if(vspeed > 0){
            PlayerAnimationState = 1;
        }
        else{
            PlayerAnimationState = 0;
        }
    }
    public void switchanimations(){

        if(PlayerAnimationState == 0){
            PlayerAnimationColumn = 4;
            PlayerAnimationPosy = 0;
            if (CurrentFrame >= (PlayerAnimationColumn - 1))
            {
                CurrentFrame = 0;
            }
            else
                CurrentFrame += 1;}
        else if(PlayerAnimationState == 1){
            CurrentFrame = 0;
            PlayerAnimationColumn = 2;
            PlayerAnimationPosy = 3;
        }
        else if(PlayerAnimationState == 2){
            CurrentFrame = 1;
            PlayerAnimationPosy = 3;
            PlayerAnimationColumn = 2;
        }
    }
    public void checkground(){
        if (y < gameview.getHeight()-Ground.height-playerheight){
            vspeed+=gravity;
            if (y > gameview.getHeight()-Ground.height-playerheight-vspeed)
            {
                vspeed = gameview.getHeight()-Ground.height-playerheight;
            }
        }
        else if (vspeed>0)
        {

            vspeed = 0;
            y = gameview.getHeight()-Ground.height-playerheight;
        }
        y += vspeed;
    }

    public void ontouch(int doublejump){
        if (doublejump > 0 && jumpCounter == 1)
        {
            vspeed = jumppower;
            jumpCounter = 0;

        }
        else
        {
            if (y>= gameview.getHeight()-Ground.height-playerheight)
            {
                vspeed = jumppower;
                jumpCounter = 1;
            }
        }
    }

    public Rect GetBounds()
    {
        return new Rect(this.x,this.y,this.x+width,this.y+height);
    }

    public void onDraw(Canvas canvas){
        update();
        int srcX = CurrentFrame * width;
        int srcY = PlayerAnimationPosy * 32; // 1.5 of your sprite height
        Rect src = new Rect(srcX,srcY,srcX + width,srcY+height);
        Rect dst = new Rect(x,y,x+(width),y+(height));
        canvas.drawBitmap(bmp,src,dst,null);

    }


    // TODO Auto-generated method stub

}