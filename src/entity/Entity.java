package entity;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * ogólne zmienne dotycz¹ce postaci w grze
 *
 */
public class Entity {
    public int x,y;
    public int speed;

    public BufferedImage dol, gora, lewy, prawy;
    public String direction;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}