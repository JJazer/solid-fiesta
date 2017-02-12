package com.best.dex.endllessgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by matti on 13.10.2016.
 */
public class Ground {


    private GameView gameview;
    private Bitmap bmp;
    private int x;
    private int y;
    public static int width;
    public static int height;

    public Ground(GameView gameview, Bitmap bmp, int x, int y)
    {
        this.gameview = gameview;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
    }

    public void update()
    {
        x -= gameview.globalxSpeed;
    }

    public int returnX()
    {
        return x;
    }

    public void onDraw(Canvas canvas)
    {
        update();
        canvas.drawBitmap(bmp, x, y + gameview.getHeight()-bmp.getHeight(), null);
    }
}
