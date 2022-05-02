package main;

import object.Door;
import object.Gem;

import java.awt.*;
import java.util.Random;


/**
 * losowanie gemów oraz umieszczenie drzwi na mapie
 *
 */
public class AssetSetter {
    GamePanel gp;
    int x, y; 
    Random random = new Random();


    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    /**
     * wczytywanie grafiki gema oraz i przydzielenie mu po³o¿enia na mapie
     */
    public void setObject(){

        losowanie();
        gp.obj[0] = new Gem("/objects/gemred.png", Color.RED, "RED");
        gp.obj[0].x = x* gp.tileSize;
        gp.obj[0].y = y* gp.tileSize;

        losowanie();
        gp.obj[1] = new Gem("/objects/gemblue.png", Color.BLUE, "BLUE");
        gp.obj[1].x = x* gp.tileSize;
        gp.obj[1].y = y* gp.tileSize;


        losowanie();
        gp.obj[2] = new Gem("/objects/gemyellow.png", Color.yellow, "YELLOW");
        gp.obj[2].x = x* gp.tileSize;
        gp.obj[2].y = y* gp.tileSize;

        if(gp.level == 1){  // w zaleznosci od levelu beda wygenerowane inne drzwi
            gp.obj[3] = new Door("purple", Color.pink);
            gp.obj[3].x = 22 * gp.tileSize;
            gp.obj[3].y = 15 * gp.tileSize;
        }else if(gp.level == 2){
            gp.obj[3] = new Door("orange", Color.orange);
            gp.obj[3].x = 22 * gp.tileSize;
            gp.obj[3].y = 15 * gp.tileSize;
        }else if(gp.level == 3){
            gp.obj[3] = new Door("green", Color.green);
            gp.obj[3].x = 22 * gp.tileSize;
            gp.obj[3].y = 15 * gp.tileSize;
        }
    }
    
/**
 * funkcja losuj¹ca kolumnê i wiersz gdzie ma siê pojawiæ gem, je¿eli pod tymi koordynatami znajduje siê ju¿ blok to losuje ponownie
 */
    public void losowanie(){ 

        x = random.nextInt(16);
        y = random.nextInt(16);
        if(gp.tileManager.mapTileNum[x][y] == 1){
            losowanie();
        }
        //System.out.println(gp.tileManager.mapTileNum[x][y]);


    }

}
