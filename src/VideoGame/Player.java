/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoGame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Patricio y Diego
 */
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private int speed;
    private Game game;
    private int lifes;
    private Animation animationPlayer;      //to store the animation of the player
     /**
     * to create player with every attribute it have
     *
     * @param x to set the x position
     * @param y to set the y position
     * @param direction to set the player direction
     * @param width to set the width of the player
     * @param height to set the height of the player
     * @param Game to set the game where the player is created
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 5;
        this.lifes = 3;
        this.animationPlayer = new Animation(Assets.player,100);
    }
    /**
     * To get the direction of the player
     * @return an <code>int</code> value with the direction
     */
    public int getDirection() {
        return direction;
    }
   /**
     * To get the width of the player
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * To get the height of the player
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
     /**
     * Set the direction of the player
     * @param direction <b>direction</b> value with the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * Set the width of the bullet
     * @param width <b>width</b> value with the width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Set the height of the bullet
     * @param height <b>height</b> value with the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
    
    
    
    @Override
    public void tick() {
         this.animationPlayer.tick();
        // moving player depending on flags
            if (game.getKeyManager().left) {
                setX(getX() - speed);//Left
            }
            
            if (game.getKeyManager().right) {
                setX(getX() + speed);//Right
            }
            if(game.getKeyManager().shoot && game.getBullet().isDead()){
                game.setBullet(new Bullet(x-10+width/2, y, 20, 20, game));
            }
        
            
            // reset x position and y position if colision
            if (getX() + width/2 > game.getWidth()) {
                setX(game.getWidth() - width/2);
            } else if (getX() < -width/2) {
                setX(-width/2);
            }
    }
    
     
    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth() , getHeight());
    }
    
    public boolean intersecta(Object obj) {

        return obj instanceof Alien && getPerimetro().intersects(((Alien) obj).getPerimetro());
    }

    @Override
    public void render(Graphics g) {
        //Draw van image depending of the direction of the movement
      
        g.drawImage(animationPlayer.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            
    }
}
