package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * wczytywanie map z plików tekstowych oraz rysowanie ich na ekranie, wczytywanie grafik kafelków
 *
 */
public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
/**
 * wczytanie map
 */
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int [gp.screenCol][gp.screenRow];
        getTileImage();
        //System.out.println(this.gp.level+"lvl");
        switch (this.gp.level){
            case 1:
                loadMap("/maps/map01.txt");
                //System.out.println(true);
                break;
            case 2:
                loadMap("/maps/map02.txt");
                
                break;
            case 3:
                loadMap("/maps/map03.txt");
                break;

        }

    }
/**
 * za³adowanie mapy
 */
    public void loadMap(String path){
        try {
            InputStream is = getClass().getResourceAsStream(path);     //to sluzy zeby wczytac plik
            BufferedReader br = new BufferedReader(new InputStreamReader(is));        //a tym zczytujemy zawartosc -- a moze nie xD?

            int col = 0;
            int row = 0;

            while (col<gp.screenCol && row < gp.screenRow){
                String line = br.readLine(); //READ A LINE OF A TEX - zczyta to cala linie i wrzuci do stringa line

                while(col < gp.screenCol){
                    String numbers[] = line.split(",");      //funkcja .split podzieli nam stringa w zaleznosci od tego co podamy w nawiasach - jezeli bedzie tam X to gdy znajdzie X to podzieli stringa
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.screenCol){
                    col = 0;
                    row++;
                }

            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

/**
 * za³adowanie grafiki kafelków
 */
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image  = ImageIO.read(getClass().getResourceAsStream("/tiles/plank.png"));

            tile[1] = new Tile();
            tile[1].image  = ImageIO.read(getClass().getResourceAsStream("/tiles/blok.png"));
            tile[1].collision = true;



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * rysowanie map na ekranie
     */
    public void draw(Graphics2D g2d){

        int col =0;
        int row =0;
        int x = 0;
        int y = 0;

        while(col < gp.screenCol && row < gp.screenRow) {

            int tileNum = mapTileNum[col][row];

            if(tileNum != 0){
                g2d.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            }

            col++;
            x = x + gp.tileSize;

            if (col == gp.screenCol) {
                col = 0;
                x = 0;
                row++;
                y = y + gp.tileSize;
            }

        }

    }

}
