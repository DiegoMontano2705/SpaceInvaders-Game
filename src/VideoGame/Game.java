/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Patricio y Diego
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private Bullet bullet;          // to use a bullet
    private ArrayList<Alien> alien;    // to use the aliens
    private KeyManager keyManager;  // to manage the keyboard
    private int score;                 // to manage score
    private boolean gameOver;           //to manage if the game is ended and you lose
    private boolean win;            // to manage if the game is ended and you win
    private Resources resources;    //To load and save the information of the game
    private int aliensOnGame;       // To store the number of aliens in the game
    private int alienDirection;     //To store alien direction
    private LinkedList<PowerUp> powerUps;        //To store a powerUp

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        resources = new Resources(this);

    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * To get the score of the game
     *
     * @return an <code>int</code> value with the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score
     *
     * @param score <b>score</b> value with the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * To get the bullet object
     *
     * @return an <code>bullet</code> value with the Bullet
     */
    public Bullet getBullet() {
        return bullet;
    }

    /**
     * To get the Powerups objects
     *
     * @return an <code>LinkedList</code> value with powerUps
     */
    public LinkedList<PowerUp> getPowerUps() {
        return powerUps;
    }

    /**
     * Set the powerUps objects
     *
     * @param powerUps <b>PowerUp</b> value with powerUps
     */
    public void setPowerUps(LinkedList<PowerUp> powerUps) {
        this.powerUps = powerUps;
    }

    /**
     * To get the bricks object
     *
     * @return an <code>ArrayList</code> value with bricks object
     */
    public ArrayList<Alien> getalien() {
        return alien;
    }

    public int getAliensOnGame() {
        return aliensOnGame;
    }

    public void setAliensOnGame(int aliensOnGame) {
        this.aliensOnGame = aliensOnGame;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public int getAlienDirection() {
        return alienDirection;
    }

    public void setAlienDirection(int alienDirection) {
        this.alienDirection = alienDirection;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2 - 75, getHeight() - 50, 1, 100, 50, this);
        bullet = new Bullet(player.getX() + (player.getWidth() / 2), player.getY() - 20, 20, 20, this);
        bullet.setDead(true);
        powerUps = new LinkedList<PowerUp>();
        alienDirection = 1;
        int iPosX;
        int iPosY;
        int iRen;
        int iCol;
        int iInd;
        gameOver = false;
        win = false;
        // creating my bricks list
        alien = new ArrayList<Alien>();
        score = 0;
        // Se escoje una mitad con direccion izquierda y la otra a la derecha
        for (int j = 0; j < ((getHeight() * 4 / 7) / 30) - 1; j++) {
            for (int i = 0; i < (getWidth() / 60) - 1; i++) {
                iInd = (j) % 4;
                iPosX = (i + 1) * 60;
                iPosY = (j + 1) * 30;
                alien.add(new Alien(iPosX, iPosY, 60, 30, this));
            }
        }

        display.getJframe().addKeyListener(keyManager);

    }

    private void reset() {
        //Restart every value and object in the game 
        player.setX(getWidth() / 2 - 75);
        player.setY(getHeight() - 50);
        player.setLifes(3);
        bullet.setDead(true);
        alienDirection = 1;
        gameOver = false;
        win = false;
        int iPosX;
        int iPosY;
        int iRen;
        int iCol;
        int iInd;
        // creating my bricks list
        alien = new ArrayList<Alien>();
        powerUps = new LinkedList<PowerUp>();
        score = 0;
        // Se escoje una mitad con direccion izquierda y la otra a la derecha
        for (int j = 0; j < ((getHeight() * 4 / 7) / 30) - 1; j++) {
            for (int i = 0; i < (getWidth() / 60) - 1; i++) {
                iInd = (j) % 4;
                iPosX = (i + 1) * 60;
                iPosY = (j + 1) * 30;
                alien.add(new Alien(iPosX, iPosY, 60, 30, this));
            }
        }
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        //Resets game if you press any key in GameOver or You Win screen
        //saves and load game tick
        keyManager.tick();
        if (keyManager.save) {
            resources.saveGame();
        }
        if (keyManager.load) {
            resources.loadGame();
        }
        if (keyManager.reset) {
            this.reset();
        }
        if (!(gameOver || win)) {

            // avancing player and bricks and check collisions 
            if (!keyManager.pause) {
                player.tick();
                bullet.tick();
                aliensOnGame = alien.size();
                for (int i = 0; i < alien.size(); i++) {
                    Alien aliens = alien.get(i);
                    
                    
                    if ((aliens.getX() + aliens.getWidth() >= width && alienDirection != -1) || (aliens.getX() <= 0 && alienDirection != 1)) {
                        alienDirection = alienDirection * -1;
                        alien.forEach((alien2) -> {
                            alien2.setY(alien2.getY() + 30);
                        });

                    }
                         aliens.tick();
                         
                    if (aliens.isDead()) {
                        aliensOnGame = aliensOnGame - 1;
                    } else {
                        if (((int) (Math.random() * 1000)) == 1) {
                            aliens.getBomb().setDead(false);
                            aliens.getBomb().setX(aliens.getX());
                            aliens.getBomb().setY(aliens.getY());
                        }
                        aliens.getBomb().tick();
                        if(aliens.getBomb().intersecta(player) && !aliens.getBomb().isDead()){
                            player.setLifes(player.getLifes()-1);
                            aliens.getBomb().setDead(true);
                        }

                        //checks if bullet intersects brick
                        if (bullet.intersecta(aliens) && !aliens.isDead()) {
                            bullet.setDead(true);
                            //Add points to score
                            score = score + 10;
                            //Plays sound everytime bullet hits brick
                            Assets.bounce.play();
                            aliens.setDead(true);
                            aliens.getBomb().setDead(true);

                        }
                        if (aliens.getY() > height - 30 && !aliens.isDead()) {
                            gameOver = true;
                        }
                    }

                    // If there are no more aliens in the game. EndGame to show You Win image
                   if(player.getLifes()<0){
                       gameOver=true;
                   }
                    
                    if (aliensOnGame <= 0) {
                        win = true;
                        Assets.applause.play();
                    }
                    if (gameOver) {
                        Assets.boo.play();
                    }
                }
            }

        }
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);

        } else {
            g = bs.getDrawGraphics();

            //Shows Game Over image and stop  the game if the bullet get to the bottom of the display
            if (gameOver) {
                g.drawImage(Assets.gameOver, 0, 0, width, height, null);
                //Set font color to white for the text of Lifes Left:
                g.setColor(Color.white);
                g.drawString("Press R button to restart", getWidth() / 2 - 90, getHeight() - 50);
                //Shows You Win image and stop the game if there are not more bricks in the game
            } else if (win) {
                g.drawImage(Assets.youWin, 0, 0, width, height, null);
                g.setColor(Color.white);
                g.drawString("Press R button to restart", getWidth() / 2 - 90, getHeight() - 50);
                //If the game hasn´t end, the game is painted
            } else {
                g.drawImage(Assets.background, 0, 0, width, height, null);
                player.render(g);
                bullet.render(g);
                for (int i = 0; i < alien.size(); i++) {
                    Alien aliens = alien.get(i);
                    if (!aliens.isDead()) {
                        aliens.render(g);
                        if(!aliens.getBomb().isDead()){
                            aliens.getBomb().render(g);
                        }
                    }
                }
                for (int i = 0; i < powerUps.size(); i++) {
                    PowerUp power = powerUps.get(i);
                    power.render(g);
                }
                //Set font color to white for the text of Lifes Left:
                Font small = new Font("Helvetica", Font.BOLD, 14);
                g.setFont(small);
                g.setColor(Color.white);
                g.drawString("Score:" + this.getScore(), getWidth() - 100, getHeight() - 20);
                g.drawString("Lifes left: " + player.getLifes(), 10, getHeight() - 20);
                if (keyManager.pause) {
                    g.setColor(new Color(0, 32, 48));
                    g.fillRect(50, width / 2 - 30, width - 100, 50);
                    g.setColor(Color.white);
                    g.drawRect(50, width / 2 - 30, width - 100, 50);
                    g.setColor(Color.white);
                    String message = "Game Paused (Press P to continue)";
                    g.drawString(message, (width - ((small.getSize() / 2) * message.length())) / 2, width / 2);
                }
            }
            bs.show();

            g.dispose();

        }

    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
