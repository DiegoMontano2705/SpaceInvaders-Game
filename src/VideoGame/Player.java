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

    private int direction;                  // to store the direction of the player
    private int width;                      // to store the width of the player
    private int height;                     // to store the height of the player
    private int speed;                      // to store the speed of the player
    private Game game;                      // to store the game where is the player
    private int lifes;                      // to store the lifes of the player
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
        this.lifes = 5;
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
     * Set the width of the player
     * @param width <b>width</b> value with the width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Set the height of the player
     * @param height <b>height</b> value with the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * To get the lifes of the player
     * @return an <code>int</code> value with the lifes
     */
    public int getLifes() {
        return lifes;
    }
    /**
     * Set the lifes of the player
     * @param lifes <b>lifes</b> value with the lifes
     */
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
            //shoots bullet if space bar is pressed and bullet is destroyed
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
        //Draw player animation
        g.drawImage(animationPlayer.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
            
    }
}
