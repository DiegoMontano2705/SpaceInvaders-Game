/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VideoGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Patricio y Diego
 */
public class KeyManager implements KeyListener {

    public boolean left;      // flag to move left the player
    public boolean right;    // flag to move right the player
    public boolean pause = false;   //flag to pause the game
    public boolean save;        //flag to save
    public boolean load;        // flag to load
    public boolean shoot;       //flag to shoot a bullet
    public boolean reset;       //flag to reset game

    private boolean keys[];  // to store all the flags for every key

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true or false depending on the pause botton
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pause = !pause;
        }
            
                keys[e.getKeyCode()] = true;
            
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        save = keys[KeyEvent.VK_G];
        load = keys[KeyEvent.VK_C];
        shoot = keys[KeyEvent.VK_SPACE];
        reset = keys[KeyEvent.VK_R];
    }
}
