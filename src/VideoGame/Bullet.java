/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoGame;

import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.abs;

/**
 *
 * @author Patricio y Diego
 */
public class Bullet extends Item {
    private int velY;                       //to store the direction in Y
    private int width;                      //to store the width of the bullet
    private int height;                     //to store the height of the bullet
    private Game game;                      //to store the Game
    private Animation animationBullet;      //to store the animation of the bullet
    private boolean dead;                   //to store the status of the bullet
    
     /**
     * to create bullet with every attribute it have
     *
     * @param x to set the x position
     * @param y to set the y position
     * @param width to set the width of the bullet
     * @param height to set the height of the bullet
     * @param Game to set the game where the bullet is created
     */
    public Bullet(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        velY = -10;
        dead=false;
        this.animationBullet = new Animation(Assets.bullet,100);
    }
    

    
    /**
     * To get the direction in Y of the bullet
     * @return an <code>int</code> value with the direction
     */
    public int getVelY() {
        return velY;
    }
    
    /**
     * To get the width of the bullet
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    
     /**
     * To get the height of the bullet
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    
     /**
     * Set the direction in Y of the bullet
     * @param velY <b>velY</b> value with the direction
     */
    public void setVelY(int velY) {
        this.velY = velY;
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
    
    /**
     * To get the status the bullet
     * @return an <code>boolean</code> value with the status
     */
    public boolean isDead() {
        return dead;
    }
    
    /**
     * Set the status of the bullet
     * @param dead <b>dead</b> value with the bullet
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    
    
    @Override
    public void tick() {
        //change animation of the bullet
        if(!dead){
        this.animationBullet.tick();
                if (getY() < 0) {
                dead=true;
            }
            
            //set direction of movement of the bullet
            setY(getY() + velY);
        }
    }
    
     public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
     
     
      public boolean intersecta(Object obj) {
        return obj instanceof Alien && getPerimetro().intersects(((Alien) obj).getPerimetro());
    }
    
     @Override
    public void render(Graphics g) {
        if(!dead)
        g.drawImage(animationBullet.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
