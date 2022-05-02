package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * ogólne zmienne dla obiektów w grze
 *
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x,y;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public Color color;


    public void draw(Graphics2D g2d, GamePanel gp) {

        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);


    }


}
