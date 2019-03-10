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
public class Alien extends Item {

    private int width;                      // to store the width of the alien 
    private int height;                     // to store the height of the alien
    private Game game;                      // to store the game
    private boolean dead;                   // to store the status of the alien
    private int direction;                  // to store the direction of the alien
    private Bomb bomb;                      // to store the bombs of the alien
    private Animation animationAlien;       //  to store the animation of the alien
    
     /**
     * to create brick with every attribute it have
     *
     * @param x to set the x position
     * @param y to set the y position
     * @param width to set the width of the brick
     * @param height to set the height of the brick
     * @param Game to set the game where the brick is created
     */
    public Alien(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        dead= false;
        this.animationAlien = new Animation(Assets.alien,100);
        bomb = new Bomb(x,y,width/2,height/2,game);
    }
     /**
     * Set the the status of dead
     * @param dead <b>dead</b> value with the dead status
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }
     /**
     * To get the value of dead
     * @return an <code>boolean</code> value with the dead status
     */ 
    public boolean isDead() {
        return dead;
    }
   /**
     * To get the width of the alien
     * @return an <code>int</code> value with the width
     */ 
    public int getWidth() {
        return width;
    }
     /**
     * To get the height of the alien
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Set the width of the alien
     * @param width <b>width</b> value with the width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * Set the height of the alien
     * @param height <b>height</b> value with the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * To get the direction of the alien
     * @return an <code>int</code> value with the direction
     */ 
    public int getDirection() {
        return direction;
    }
     /**
     * Set the direction of the alien
     * @param direction <b>direction</b> value with the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
/**
     * To get the bomb of the alien
     * @return an <code>Bomb</code> value with the bomb
     */ 
    public Bomb getBomb() {
        return bomb;
    }
     /**
     * Set the bomb of the alien
     * @param bomb <b>bomb</b> value with the bomb
     */
    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    

    @Override
    public void tick() {
        //create movement of alien
        direction= game.getAlienDirection();
        x= x+ direction;
    
    }

    public Rectangle getPerimetro() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void render(Graphics g) {
        if(!dead)
        g.drawImage(animationAlien.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
