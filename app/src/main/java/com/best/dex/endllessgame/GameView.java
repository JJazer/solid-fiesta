package com.best.dex.endllessgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//import android.app.Activity;
//import android.os;


public class GameView extends SurfaceView {
    GameLoopThread gameLoopThread;
    SurfaceHolder holder;
    //GodOfSound god;
    EndlessRunningGameActivity endless;
    Intent PlaySound;

    public boolean counterer = false;
    Bitmap playerbmp;
    private List<Player> player = new ArrayList<Player>();

    Bitmap coinbmp;

    private List<Coin> coins = new ArrayList<Coin>();

    private String saveScore = "HighScore";
    private String achivment = "Doublejump";

    Bitmap groundbmp;

    private int spaceCounter = 0;
    private List<Ground> ground = new ArrayList<Ground>();
    private List<Spikes> spike = new ArrayList<Spikes>();
    private List<PowerupShield> powerShield = new ArrayList<PowerupShield>();
    private List<Buttons> button = new ArrayList<Buttons>();
    private List<AchiV> achi = new ArrayList<AchiV>();

    Bitmap spikesbmp;
    Bitmap achibmp;
    private Bitmap backG;

    Bitmap Shieldbmp;
    Bitmap buttonsbmp;
    public boolean CanWeJump;
    public boolean CantWeJump;

    //public boolean ex = false;


    public static int globalxSpeed = 8 * 2;

    private int counter;
    private int currentcoin;

    public static int coinColected = 0;
    public static int Score = 0;
    public static int HighScore = 0;

    private int achicounter;
    private boolean PlayerGotPowerShield = false;

    private int PlayerPowerupShieldTime = 120;

    private static SharedPreferences prefs;
    private int jumpCounter = 0;
    private static SharedPreferences NowWeCanJump;

    private int timerCoins = 0;
    private int timerSpikes = 0;
    private int timerShield = 0;
    private int RandomTimerSpikes = 1;
    private int RandomTimerShield = 1;

    private String Menu = "Mainmenu";

    private int xx = 0;

    public GameView(Context context) {
        super(context);

        prefs = context.getSharedPreferences("com.best.dex.endlessgame", context.MODE_PRIVATE);
        NowWeCanJump = context.getSharedPreferences("com.best.dex.endlessgame", context.MODE_PRIVATE);

        String spackage = "com.best.dex.endlessgame";

        HighScore = prefs.getInt(saveScore, 0);
        CanWeJump = NowWeCanJump.getBoolean(achivment, false);
        CantWeJump = NowWeCanJump.getBoolean(achivment, false);

        gameLoopThread = new GameLoopThread(this);

        //endless.startService(endless.PlaySound);





        holder = getHolder();
        holder.addCallback(new Callback() { // CTRL + Space tar farm allt

            public void surfaceDestroyed(SurfaceHolder arg0) {
                // TODO Auto-generated method stub
                Score = 0;
                coinColected = 0;
                prefs.edit().putInt(saveScore, HighScore).commit();
                NowWeCanJump.edit().putBoolean(achivment, CanWeJump).commit();
                gameLoopThread.running = false;


            }

            public void surfaceCreated(SurfaceHolder arg0) {
                // TODO Auto-generated method stub
                gameLoopThread.setRunning();
                gameLoopThread.start();


            }

            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

        });
        playerbmp = BitmapFactory.decodeResource(getResources(), R.drawable.hero2);
        coinbmp = BitmapFactory.decodeResource(getResources(), R.drawable.coinpic);
        groundbmp = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        Shieldbmp = BitmapFactory.decodeResource(getResources(), R.drawable.shield);
        spikesbmp = BitmapFactory.decodeResource(getResources(), R.drawable.spike);
        buttonsbmp = BitmapFactory.decodeResource(getResources(), R.drawable.buttons1);
        achibmp = BitmapFactory.decodeResource(getResources(), R.drawable.achi);
        backG = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        player.add(new Player(this, playerbmp, 50, 50));
        //coins.add(new Coin(this, coinbmp, 120, 32));
        //coins.add(new Coin(this, coinbmp, 180, 0));
//        spike.add(new Spikes(this, spikesbmp, 1000, 0));
        //spike.add(new Spikes(this, spikesbmp, 800, 100));
        // TODO Auto-generated constructor stub
        //powerShield.add(new PowerupShield(this, Shieldbmp, 500, 0));

      //Butt();
       // button.add(new Buttons(this, buttonsbmp, 1980 / 2 - 128, 1080 / 2 + 128, 1));
       //button.add(new Buttons(this, buttonsbmp, 1980 / 2 - 128, 1080 / 2 + 224, 2));
       //button.add(new Buttons(this, buttonsbmp, 1980 / 2 - 128, 1080 / 2 + 320, 3));
       //button.add(new Buttons(this, buttonsbmp, 1980 / 2 - 128, 1080 / 2 + 32, 0));
       // startGame();




    }

    public void Butt()
    {
        button.add(new Buttons(this, buttonsbmp, this.getWidth()/ 2 - 64, this.getHeight()/ 2 + 32, 0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        for (Player pplayer : player) {
            int doublejump = 0;
            if(CanWeJump)
            {
                doublejump = 1;
            }
            pplayer.ontouch(doublejump);
        }
        if (Menu == "Achi") {
            //Menu = "Running";
            //startGame();
            for (int i = 0; i < button.size(); i++) {
                if (button.get(i).getState() == 1)//restart
                {
                    if (e.getX() < button.get(i).getX() + button.get(i).getWidth() && e.getX() > button.get(i).getX()) {
                        if (e.getY() < button.get(i).getY() + button.get(i).getHeight() && e.getY() > button.get(i).getY()) {

                            Menu = "Mainmenu";
                            //startGame();

                        }
                    }


                }
            }
        }

        if (Menu == "Mainmenu") {
            //Menu = "Running";
            //startGame();
            for (int i = 0; i < button.size(); i++) {
                if (button.get(i).getState() == 0)//restart
                {
                    if (e.getX() < button.get(i).getX() + button.get(i).getWidth() && e.getX() > button.get(i).getX()) {
                        if (e.getY() < button.get(i).getY() + button.get(i).getHeight() && e.getY() > button.get(i).getY()) {

                            Menu = "Running";
                            startGame();

                        }
                    }


                }
                else if(button.get(i).getState() == 3)
                {
                    if (e.getX() < button.get(i).getX() + button.get(i).getWidth() && e.getX() > button.get(i).getX()) {
                        if (e.getY() < button.get(i).getY() + button.get(i).getHeight() && e.getY() > button.get(i).getY()) {

                            int pid = android.os.Process.myPid();

                            gameLoopThread.running = false;
                            //endless.counter ++;
                            //endless.stopService(endless.PlaySound);
                            //android.os.Process.killProcess(pid);
                            counterer = true;




                        }
                    }
                }
                else if(button.get(i).getState() == 2)
                {
                    if (e.getX() < button.get(i).getX() + button.get(i).getWidth() && e.getX() > button.get(i).getX()) {
                        if (e.getY() < button.get(i).getY() + button.get(i).getHeight() && e.getY() > button.get(i).getY()) {

                            Menu = "Achi";

                        }
                    }
                }
            }
        }
        return false;

    }


    public void gettingDoubleJump()
    {
        if (coinColected > 10)
        {
            CanWeJump = true;
        }
    }


    public void update() {
        if(Menu == "Running")
        {
            Score += 5;
            updateTimer();
            deleteGround();
            if (Score > HighScore) {
                HighScore = Score;
            }
            gettingDoubleJump();
            if(achicounter > 0)
            {
                achicounter++;
            }
            if (achicounter >= 50)
            {
                achicounter = 0;
            }
            gettingAchi();
        }


    }

    public void updateTimer() {
        if (Menu == "Mainmenu")
        {
            timerCoins = 0;
            timerSpikes = 0;
            timerShield = 0;
        }
        if (Menu == "Running") {
            timerCoins++;
            timerSpikes++;
            timerShield++;
            if (PlayerGotPowerShield) {
                PlayerPowerupShieldTime--;
                if (PlayerPowerupShieldTime == 0) {
                    PlayerGotPowerShield = false;
                }
            }

            if (timerSpikes >= 50)
            {
                spike.add(new Spikes(this, spikesbmp, this.getWidth() + 24, 0));
                timerSpikes = 0;
            }
            if (timerShield >= 500)
            {
                powerShield.add(new PowerupShield(this, Shieldbmp, this.getWidth() + 24, 0));
                timerShield = 0;
            }
            /*switch (RandomTimerSpikes) {
                case 1:
                    if (timerSpikes >= 75) {

                        Random randomSpikes = new Random();
                        RandomTimerSpikes = randomSpikes.nextInt(3);
                        timerSpikes = 0;
                    }
                    break;
                case 2:
                    if (timerSpikes >= 125) {
                        spike.add(new Spikes(this, spikesbmp, this.getWidth() + 24, 0));
                        Random randomSpikes = new Random();
                        RandomTimerSpikes = randomSpikes.nextInt(3);
                        timerSpikes = 0;
                    }
                    break;
                case 3:
                    if (timerSpikes >= 50) {
                        spike.add(new Spikes(this, spikesbmp, this.getWidth() + 24, 0));
                        Random randomSpikes = new Random();
                        RandomTimerSpikes = randomSpikes.nextInt(3);
                        timerSpikes = 0;
                    }
                    break;
            }*/

            /*switch (RandomTimerShield) {
                case 1:
                    if (timerShield >= 20) {
                        powerShield.add(new PowerupShield(this, Shieldbmp, this.getWidth() + 24, 0));
                        Random randomShield = new Random();
                        RandomTimerShield = randomShield.nextInt(3);
                        timerShield = 0;
                    }
                    break;
                case 2:
                    if (timerSpikes >= 100) {
                        powerShield.add(new PowerupShield(this, Shieldbmp, this.getWidth() + 24, 0));
                        Random randomShield = new Random();
                        RandomTimerShield = randomShield.nextInt(3);
                        timerShield = 0;
                    }
                    break;
                case 3:
                    if (timerSpikes >= 150) {
                        powerShield.add(new PowerupShield(this, Shieldbmp, this.getWidth() + 24, 0));
                        Random randomShield = new Random();
                        RandomTimerShield = randomShield.nextInt(3);
                        timerShield = 0;
                    }
                    break;
            }
        /*if (timerSpikes >= 75)
        {
            spike.add(new Spikes(this, spikesbmp, this.getWidth()+24, 0));
            Random randomSpikes = new Random();
            RandomTimerSpikes = randomSpikes.nextInt(3);
            timerSpikes = 0;
        }*/

            if (timerCoins >= 100) {
                Random randomCoin = new Random();
                int random;
                random = randomCoin.nextInt(3);

                switch (random) {
                    case 1:
                        currentcoin = 0;
                        int xx = 1;
                        while (currentcoin <= 5) {

                            coins.add(new Coin(this, coinbmp, this.getWidth() + (32 * xx), 48));

                            currentcoin++;
                            xx += 2;
                        }
                        break;
                    case 2:


                        coins.add(new Coin(this, coinbmp, this.getWidth() + 32, 64));
                        coins.add(new Coin(this, coinbmp, this.getWidth() + 64 + 32, 48));
                        coins.add(new Coin(this, coinbmp, this.getWidth() + 96 + 64, 64));
                        coins.add(new Coin(this, coinbmp, this.getWidth() + 128 + 96, 48));
                        coins.add(new Coin(this, coinbmp, this.getWidth() + 160 + 128, 64));

                        break;


                }
                timerCoins = 0;
            }
        }
    }


    public void addground() {
        //int xx = 0;

        while (xx < this.getWidth() + Ground.width) {
            ground.add(new Ground(this, groundbmp, xx, 0));

            xx += groundbmp.getWidth();
        }
    }

    public void deleteGround() {
        for (int i = ground.size() - 1; i >= 0; i--) {
            int groundx = ground.get(i).returnX();

            if (groundx <= -Ground.width) {
                ground.remove(i);
                ground.add(new Ground(this, groundbmp, groundx + this.getWidth() + Ground.width, 0));

            }

        }
    }

    public void endGame() {

        PlayerGotPowerShield = false;
        timerShield = 0;
        timerSpikes = 0;
        timerCoins = 0;
        Menu = "Mainmenu";
        coins.clear();
        spike.clear();
        powerShield.clear();

        player.remove(0);



    }

    public void startGame() {

        player.add(new Player(this, playerbmp, 50, 50));
        button.clear();
        timerCoins = 0;
        timerSpikes = 0;
        timerShield = 0;


    }
    public void gettingAchi()
    {
        if (CantWeJump == false)
        {
            if(CanWeJump == true)
            {
                achicounter = 1;
                CantWeJump = CanWeJump;
            }
        }

        if (HighScore == 10000)
        {
            achicounter = 1;
        }
        if (HighScore == 50000)
        {
            achicounter = 1;
        }
        if (HighScore == 1000000)
        {
            achicounter = 1;
        }
        if (HighScore == 999999999)
        {
            achicounter = 1;
        }
    }

    
    @Override
    protected void onDraw(Canvas canvas) {

        update();
        canvas.drawBitmap(backG, 0, 0, null);

        if (Menu == "Achi")
        {
            achi.clear();
            Paint textpaint1 = new Paint();
            textpaint1.setTextSize(32);
            textpaint1.setColor(Color.BLACK);
            button.clear();
            button.add(new Buttons(this, buttonsbmp, 0, 0, 1));
            canvas.drawText("HighScore Achivments: ", 0, canvas.getHeight()/4-64, textpaint1);
            canvas.drawText("10000", 0, canvas.getHeight()/4, textpaint1);
            canvas.drawText("50000", canvas.getWidth()/4, canvas.getHeight()/4, textpaint1);
            canvas.drawText("1000000", canvas.getWidth()/2, canvas.getHeight()/4, textpaint1);
            canvas.drawText("Over and over", (canvas.getWidth()/2)+(canvas.getWidth()/4), canvas.getHeight()/4, textpaint1);
            canvas.drawText("Welcome", 0, canvas.getHeight()/2 + achibmp.getHeight() + 64, textpaint1);
            achi.add(new AchiV(this, achibmp, 0, canvas.getHeight()/2 + 96 + achibmp.getHeight()));
            if (CanWeJump == true)
            {
                canvas.drawText("Now we can Jump", 0, canvas.getHeight()/2, textpaint1);
                achi.add(new AchiV(this, achibmp, 0, canvas.getHeight()/2 + 32));
            }

            if (HighScore > 10000)
            {
                achi.add(new AchiV(this, achibmp, 0, canvas.getHeight()/4 + 32));
            }
            if (HighScore > 50000)
            {
                achi.add(new AchiV(this, achibmp, canvas.getWidth()/4, canvas.getHeight()/4 + 32));
            }
            if (HighScore > 1000000)
            {
                achi.add(new AchiV(this, achibmp, canvas.getWidth()/2, canvas.getHeight()/4 + 32));
            }
            if (HighScore > 999999999)
            {
                achi.add(new AchiV(this, achibmp, (canvas.getWidth()/2)+(canvas.getWidth()/4), canvas.getHeight()/4 + 32));
            }

            for (AchiV aachivs : achi) {
                aachivs.onDraw(canvas);

            }

            for (Buttons bbuttons : button) {
                bbuttons.onDraw(canvas);

            }
        }

        if (Menu=="Mainmenu") {
            button.clear();
            button.add(new Buttons(this, buttonsbmp, this.getWidth() / 2 - buttonsbmp.getWidth()/8, this.getHeight() / 2 , 0));
            //button.add(new Buttons(this, buttonsbmp, 0, 0, 1));
            button.add(new Buttons(this, buttonsbmp, this.getWidth() / 2 - (buttonsbmp.getWidth()/8), this.getHeight() / 2 + (buttonsbmp.getHeight())*2, 3));
            button.add(new Buttons(this, buttonsbmp, this.getWidth() / 2 - (buttonsbmp.getWidth()/8), this.getHeight() / 2 + buttonsbmp.getHeight(), 2));

            for (Buttons bbuttons : button) {
                bbuttons.onDraw(canvas);

            }
        }


            if (Menu == "Mainmenu") {
                Paint textpaint1 = new Paint();
                textpaint1.setTextSize(32);
                textpaint1.setColor(Color.BLACK);

                canvas.drawText("HighScore: " + String.valueOf(HighScore), (canvas.getWidth() / 2) -128, (canvas.getHeight() / 2) - 64, textpaint1);
                //canvas.drawText("Tap to start", (canvas.getWidth() / 2) - 128, (canvas.getHeight() / 2) + 0, textpaint1);

            }

            else if (Menu == "Running") {


                addground();
                Paint textpaint = new Paint();
                textpaint.setTextSize(32);
                textpaint.setColor(Color.BLACK);

                canvas.drawText("Score: " + String.valueOf(Score), 0, 32, textpaint);
                canvas.drawText("High Score: " + String.valueOf(HighScore), 0, 64, textpaint);
                canvas.drawText("Coins: " + String.valueOf(coinColected), 0, 96, textpaint);
                if (achicounter > 0)
                {
                    canvas.drawText("Achivment unlock", canvas.getWidth()/2, 0, textpaint);
                }
                if (PlayerGotPowerShield == true)
                {
                    canvas.drawText("You have shield on you", 0, 128, textpaint);
                }

                for (Ground gground : ground) {
                    gground.onDraw(canvas);
                }

                for (Player pplayer : player) {
                    pplayer.onDraw(canvas);
                }
                for (int i = 0; i < coins.size(); i++) {
                    coins.get(i).onDraw(canvas);
                    Rect playerr = player.get(0).GetBounds();
                    Rect coinr = coins.get(i).GetBounds();

                    if (coins.get(i).returnX() < -32) {
                        coins.remove(i);

                    } else if (coins.get(i).checkCollision(playerr, coinr)) {
                        coins.remove(i);
                        Score += 500;
                        coinColected += 1;

                    }
                }
                    for (int j = 0; j < spike.size(); j++) {
                        spike.get(j).onDraw(canvas);
                        Rect playerr = player.get(0).GetBounds();
                        Rect spikesr = spike.get(j).GetBounds();

                        if (spike.get(0).returnX() < -32) {
                            spike.remove(0);

                        } else if (spike.get(j).checkCollision(playerr, spikesr)) {
                            //spike.remove(j);
                            if (!PlayerGotPowerShield) {
                                Score = 0;
                                coinColected = 0;
                                endGame();
                            } else {
                                spike.remove(j);
                                PlayerGotPowerShield = false;
                            }

                        }
                    }
                    for (int k = 0; k < powerShield.size(); k++) {
                        powerShield.get(k).onDraw(canvas);
                        Rect shielder = powerShield.get(k).GetBounds();
                        Rect playerr = player.get(0).GetBounds();

                        if (powerShield.get(k).returnX() < -32) {
                            powerShield.remove(k);

                        } else if (powerShield.get(k).checkCollision(playerr, shielder)) {
                            powerShield.remove(k);
                            PlayerGotPowerShield = true;
                            PlayerPowerupShieldTime = 120;

                        }
                    }


                }


            }
        }


