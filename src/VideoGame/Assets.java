/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoGame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Patricio y Diego
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage spritesBullet;        // to store the sprites
    public static BufferedImage spritesPlayer;        // to store the sprites
    public static BufferedImage bullet[];      // to store pictures of the bullet changing color
    public static BufferedImage brick[];       // to store the diferent bricks
    public static BufferedImage gameOver;      // to store gameover image
    public static BufferedImage youWin;        // to store youwin image
    public static BufferedImage powerUp;        // to store PowerUp image
    public static SoundClip boo;                // to store the boo sound
    public static SoundClip applause;           // to store the applause sound
    public static SoundClip bounce;             // to store the bounce sound 
    public static BufferedImage player[];       //to store the player images
    

    /**
     * initializing the images of the game
     */
    public static void init() {
        //getting the four diferente images that brick can have
        brick = new BufferedImage[5];
        background = ImageLoader.loadImage("/images/space.jpg");
        brick[0] = ImageLoader.loadImage("/images/alien.png");
        brick[1] = ImageLoader.loadImage("/images/alien.png");
        brick[2] = ImageLoader.loadImage("/images/alien.png");
        brick[3] = ImageLoader.loadImage("/images/alien.png");       
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
        youWin = ImageLoader.loadImage("/images/youwin.jpg");
        powerUp = ImageLoader.loadImage("/images/PowerUp.png");
        
        boo = new SoundClip ("/sounds/Boo.wav");
        applause = new SoundClip ("/sounds/Applause.wav");
        bounce = new SoundClip ("/sounds/bouncing.wav");
        //getting the sprites from the picture
        spritesBullet = ImageLoader.loadImage("/images/bullet_enemy.png");
        spritesPlayer = ImageLoader.loadImage("/images/ship.png");
        //creating array of images before animations
        SpreadSheet spritesheetBullet = new SpreadSheet(spritesBullet);
        SpreadSheet spritesheetPlayer = new SpreadSheet(spritesPlayer);
        bullet = new BufferedImage[8];
        player = new BufferedImage[3];
        //croping the pictures from the sheet into the array
        for(int i = 0; i < 8 ; i++){
            bullet[i] = spritesheetBullet.crop(i*64,0,64,64);
        }
        for(int i = 0 ; i < 3; i++){
            player[i] = spritesheetPlayer.crop(i*64,0,64,64);
        }
       
        

    }

}
