package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


/**
 * informacje o drzwiach
 */
public class Door extends SuperObject{
/**
 * odczytanie grafiki drzwi ze œcie¿ki
 */
    public Door(String kolor, Color color){
        this.name = "DOOR";
        this.color = color;
        switch (kolor){
            case "orange":
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/objects/orangedoor.png"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case "purple":
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/objects/purpledoor.png"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case "green":
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/objects/greendoor.png"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;

        }

        collision = true;

    }
}
