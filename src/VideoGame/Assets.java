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

    public static BufferedImage background;            // to store background image
    public static BufferedImage spritesBullet;        // to store the  bullet sprites
    public static BufferedImage spritesPlayer;        // to store the player sprites
    public static BufferedImage spritesBomb;           // to store the bomb sprites
    public static BufferedImage spritesAlien;          // to store the alien sprites
    public static BufferedImage bullet[];               // to store pictures of the bullet changing color
    public static BufferedImage alien[];                // to store the alien image
    public static BufferedImage gameOver;               // to store gameover image
    public static BufferedImage youWin;                 // to store youwin image
    public static SoundClip boo;                         // to store the boo sound
    public static SoundClip applause;                   // to store the applause sound
    public static SoundClip bounce;                     // to store the bounce sound 
    public static SoundClip soundtrack;                 // to store the game music
    public static SoundClip playerDamage;               // to store sound when player is damaged    
    public static BufferedImage player[];               //to store the player images
    public static BufferedImage bomb[];                 // to store the bomb images
    
    

    /**
     * initializing the images of the game
     */
    public static void init() {
        int x = 0;
        //getting the imges paths
        background = ImageLoader.loadImage("/images/space.jpg");     
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
        youWin = ImageLoader.loadImage("/images/youwin.jpg");      
        //getting the sounds
        boo = new SoundClip ("/sounds/Boo.wav");
        applause = new SoundClip ("/sounds/Applause.wav");
        bounce = new SoundClip ("/sounds/laser.aiff");
        soundtrack = new SoundClip ("/sounds/music.wav");
        playerDamage = new SoundClip ("/sounds/ouch.wav");
        //getting the sprites from the picture
        spritesBullet = ImageLoader.loadImage("/images/bullet_enemy.png");
        spritesPlayer = ImageLoader.loadImage("/images/player.png");
        spritesBomb = ImageLoader.loadImage("/images/bomb.png");
        spritesAlien = ImageLoader.loadImage("/images/player.png");
        //creating array of images before animations
        SpreadSheet spritesheetBullet = new SpreadSheet(spritesBullet);
        SpreadSheet spritesheetPlayer = new SpreadSheet(spritesPlayer);
        SpreadSheet spritesheetBomb = new SpreadSheet(spritesBomb);
        SpreadSheet spritesheetAlien = new SpreadSheet(spritesAlien);
        //getting the images paths
        bullet = new BufferedImage[8];
        player = new BufferedImage[8];
        bomb = new BufferedImage[16];
        alien = new BufferedImage[8];
        //croping the pictures from the sheet into the array
        // loops to get images in diferents colums and rows
        for(int i = 0; i < 8 ; i++){
            bullet[i] = spritesheetBullet.crop(i*64,0,64,64);
        }
        for(int i = 0 ; i < 4; i++){
            player[i] = spritesheetPlayer.crop(i*48,0,48,56);
        }
        for(int j = 4; j < 8; j++){
            player[j] = spritesheetPlayer.crop(x*48,56,48,56);
            x++;
        }
        x = 4;
         for(int i = 0 ; i < 4; i++){
            alien[i] = spritesheetAlien.crop(x*48,0,48,56);
            x++;
        }
        x = 4;
        for(int j = 4; j < 8; j++){
            alien[j] = spritesheetAlien.crop(x*48,56,48,56);
            x++;
        }      
        for(int i = 0; i < 4; i++){
            bomb[i] = spritesheetBomb.crop(i*128,0,128,128);
        }
        x = 0;
        for(int i = 4; i < 8; i++){
            bomb[i] = spritesheetBomb.crop(x*128,128,128,128);
        }
        x= 0;
        for(int i = 8; i < 12; i++){
            bomb[i] = spritesheetBomb.crop(x*128,256,128,128);
        }
        x = 0;
        for(int i = 12; i < 16; i++){
            bomb[i] = spritesheetBomb.crop(x*128,384,128,128);
        }
       
    }

}
