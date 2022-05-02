package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * informacje o gemie
 *
 */
public class Gem extends SuperObject{


    public Gem(String path, Color color, String name){
        this.color = color;
        this.name = name;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        }catch (IOException e){
            e.printStackTrace();
        }
        solidArea.x = 5;
        solidArea.y = 5;

    }
}