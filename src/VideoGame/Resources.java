package VideoGame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patricio y Diego
 */
public class Resources {

    private Game game;
    private int size;
    public Resources(Game game) {
        this.game = game;
    }

    void saveGame() {
        try {
            //Creates new file to save the game data
            FileWriter fw = new FileWriter("save.txt");
            //Saves every value of every object in the game
            fw.write(String.valueOf(game.getAlienDirection() + "\n"));
            fw.write(String.valueOf(game.getScore()) + "\n");
            fw.write(String.valueOf(game.getPlayer().getX()) + "\n");
            fw.write(String.valueOf(game.getPlayer().getY()) + "\n");
             fw.write(String.valueOf(game.getPlayer().getLifes()) + "\n");   
            fw.write(String.valueOf(game.getBullet().getX()) + "\n");
            fw.write(String.valueOf(game.getBullet().getY()) + "\n");
            fw.write(String.valueOf(game.getBullet().getVelY()) + "\n");
            fw.write(String.valueOf(game.getBullet().isDead()) + "\n");
            for (int i = 0; i < game.getalien().size(); i++) {
                fw.write(String.valueOf(game.getalien().get(i).getX()) + "\n");
                fw.write(String.valueOf(game.getalien().get(i).getY()) + "\n");
                fw.write(String.valueOf(game.getalien().get(i).isDead()) + "\n");
                fw.write(String.valueOf(game.getalien().get(i).getBomb().isDead()) + "\n");
                fw.write(String.valueOf(game.getalien().get(i).getBomb().getX()) + "\n");
                fw.write(String.valueOf(game.getalien().get(i).getBomb().getY()) + "\n");
                
            }
           
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void loadGame() {
        try {
            //Loads file where every value of the game was saved
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            //Read every value in the file so it can be loaded
            game.setAlienDirection(Integer.parseInt(br.readLine()));
            game.setScore(Integer.parseInt(br.readLine()));
            game.getPlayer().setX(Integer.parseInt(br.readLine()));
            game.getPlayer().setY(Integer.parseInt(br.readLine()));
            game.getPlayer().setLifes(Integer.parseInt(br.readLine()));
            game.getBullet().setX(Integer.parseInt(br.readLine()));
            game.getBullet().setY(Integer.parseInt(br.readLine()));
            game.getBullet().setVelY(Integer.parseInt(br.readLine()));
            game.getBullet().setDead(Boolean.parseBoolean(br.readLine()));
            
            for (int i = 0; i < game.getalien().size(); i++) {
                game.getalien().get(i).setX(Integer.parseInt(br.readLine()));
                game.getalien().get(i).setY(Integer.parseInt(br.readLine()));
                game.getalien().get(i).setDead(Boolean.parseBoolean(br.readLine()));
                game.getalien().get(i).getBomb().setDead(Boolean.parseBoolean(br.readLine()));
                game.getalien().get(i).getBomb().setX(Integer.parseInt(br.readLine()));
                game.getalien().get(i).getBomb().setY(Integer.parseInt(br.readLine()));
                
            }
          
            br.close();

        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
