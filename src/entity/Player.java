package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Reader;


/**
 * wczytywanie grafiki bohatera w zale¿noœci od sterowania, zbieranie przez niego gemów
 *
 */
public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public boolean hasRed, hasYellow, hasBlue, hasAll;


/**
 * zawiera imnformacje o wymiarze sta³ego obszaru ikony bohatera
 */
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle(8,12,32,24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    /**
     * domyœlne po³o¿enie pocz¹tkowe bohatera
     */
    public void setDefaultValues(){
        x= gp.tileSize;
        y= gp.tileSize;
        speed = 3;
        direction = "prawy";
    }

    /**
     * odczytanie grafiki bohatera z œcie¿ki
     */
    public void getPlayerImage(){
        try {
            dol = ImageIO.read(getClass().getResourceAsStream("/player/dol.png"));
            gora = ImageIO.read(getClass().getResourceAsStream("/player/gora.png"));
            lewy = ImageIO.read(getClass().getResourceAsStream("/player/lewy.png"));
            prawy = ImageIO.read(getClass().getResourceAsStream("/player/prawy.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * zmiana kierunku bohatera oraz jego polo¿enia w zale¿noœci od wciœniêtego klawisza
     */
    public void update(){
        if (keyH.upPressed == true || keyH.rightPressed==true|| keyH.leftPressed==true || keyH.downPressed == true || keyH.spacePressed == true){
            if(keyH.upPressed == true){
                direction = "gora";
            }
            else if(keyH.downPressed == true){
                direction = "dol";
            }
            else if(keyH.leftPressed == true){
                direction = "lewy";
            }
            else if(keyH.rightPressed == true){
                direction = "prawy";
            }
            else if(keyH.spacePressed == true){
                direction = "pickUp";
            }



        //check collison
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        int objIndex = gp.collisionChecker.checkObject(this, true);
        pickUpObject(objIndex);

        if(collisionOn == false){

            switch (direction){
                case "gora":
                    y = y - speed;
                    break;
                case "dol":
                    y = y + speed;
                    break;
                case "lewy":
                    x = x - speed;
                    break;
                case "prawy":
                    x = x + speed;
                    break;
            }

        }
        }

    }
    
    /**
     * wybór odpowiedniej grafiki w zale¿noœci od kierunku
     */
    public void draw(Graphics2D g2d){

        BufferedImage image = prawy;
        switch (direction){
            case "gora":
                image = gora;
                break;
            case "dol":
                image = dol;
                break;
            case "lewy":
                image = lewy;
                break;
            case "prawy":
                image = prawy;
                break;
        }

        g2d.drawImage(image, x,y,gp.tileSize,gp.tileSize,null);

    }
    
    /**
     * zbieranie gemów, uruchamianie muzyki gdy gem zostanie podniesiony, otworzenie drzwi gdy odpowiednie gemy bêd¹ zebrane
     */
    public void pickUpObject(int i){
        if(i != 999 ){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "RED":
                    if(keyH.spacePressed==true){
                        hasRed = true;
                        gp.obj[i] = null;
                        gp.playSE(1);
                        checkIfAll();
                    }
                    break;
                case "BLUE":
                    if(keyH.spacePressed==true){
                        hasBlue = true;
                        gp.obj[i] = null;
                        gp.playSE(1);
                        checkIfAll();
                    }
                    break;
                case "YELLOW":
                    if(keyH.spacePressed==true){
                        hasYellow = true;
                        gp.obj[i] = null;
                        gp.playSE(1);
                        checkIfAll();
                    }
                    break;

                case "DOOR":
                    switch (gp.level){
                        case 1:
                            if(hasYellow == false && hasRed == true && hasBlue == true){
                                gp.obj[i] = null;
                                checkIfAll();
                            }
                            break;
                        case 2:
                            if(hasYellow == true && hasRed == true && hasBlue == false) {
                                gp.obj[i] = null;
                                checkIfAll();
                            }
                            break;
                        case 3:
                            if(hasYellow == true && hasRed == false && hasBlue == true) {
                                gp.obj[i] = null;

                            }
                            break;

                    }
                    break;
            }

        }

    }
    
    /**
     * jesli gracz ma wszystkie gemy poziom siê resetuje
     */
    public void checkIfAll(){
        if(hasRed == true && hasBlue == true && hasYellow == true){
            gp.level--;
            System.out.println("checkd");
            gp.reLoad();

        }


    }

}
