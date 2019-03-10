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
public class Bomb extends Item {
    private int velY;                       //to store the direction in Y
    private int width;                      //to store the width of the bomb
    private int height;                     //to store the height of the bomb
    private Game game;                      //to store the Game
    private Animation animationBomb;      //to store the animation of the bomb
    private boolean dead;                   //to store the status of the bomb
    
     /**
     * to create bullet with every attribute it have
     *
     * @param x to set the x position
     * @param y to set the y position
     * @param width to set the width of the bomb
     * @param height to set the height of the bomb
     * @param Game to set the game where the bomb is created
     */
    public Bomb(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        velY = -2;
        dead=true;
        this.animationBomb = new Animation(Assets.bomb,100);
    }
    

    
    /**
     * To get the direction in Y of the bomb
     * @return an <code>int</code> value with the direction
     */
    public int getVelY() {
        return velY;
    }
    
    /**
     * To get the width of the bomb
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    
     /**
     * To get the height of the bomb
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    
     /**
     * Set the direction in Y of the bomb
     * @param velY <b>velY</b> value with the direction
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }
     /**
     * Set the width of the bomb
     * @param width <b>width</b> value with the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

     /**
     * Set the height of the bomb
     * @param height <b>height</b> value with the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * To get the status of the bomb
     * @return an <code>boolean</code> value with the status
     */
    public boolean isDead() {
        return dead;
    }
    
     /**
     * Set the status of the bomb
     * @param dead <b>dead</b> value with the status
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    
    
    @Override
    public void tick() {
        //change animation of the bomb
        if(!dead){
        this.animationBomb.tick();

            if(y>game.getHeight()){
                dead=true;
            }
            //set direction of movement of the bomb
            setY(getY() - velY);
        }
    }
    
     public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
     
     
      public boolean intersecta(Object obj) {
        return obj instanceof Player && getPerimetro().intersects(((Player) obj).getPerimetro());
    }
    
     @Override
    public void render(Graphics g) {
        if(!dead)
        g.drawImage(animationBomb.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

    
}
