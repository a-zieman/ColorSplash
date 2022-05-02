package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;


/**
 * sprawdzanie czy bohater dotyka siê z elementem sta³ym
 *
 */
public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
/** 
 * sprawdzenie czy bohater dotyka bloku
 *
 */
    public void checkTile(Entity entity){

        int leftX = entity.x + entity.solidArea.x;
        int rightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int topY = entity.y + entity.solidArea.y;
        int bottomY = entity.y + entity.solidArea.y + entity.solidArea.height+8;

        int leftCol = leftX/gp.tileSize;
        int rightCol = rightX/gp.tileSize;
        int topRow = topY/gp.tileSize;
        int bottomRow = bottomY/gp.tileSize;

        int tileNum1, tileNum2;


        switch (entity.direction){
            case "gora":
                topRow = (topY-entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileManager.mapTileNum[rightCol][topRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "dol":
                if(bottomY/ gp.tileSize<gp.screenRow-1) {
                    bottomRow = (bottomY + entity.speed) / gp.tileSize;
                    tileNum1 = gp.tileManager.mapTileNum[leftCol][bottomRow];
                    tileNum2 = gp.tileManager.mapTileNum[rightCol][bottomRow];
                    if (gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }else{

                    gp.reLoad();
                }
                break;
            case "lewy":
                leftCol = (leftX-entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileManager.mapTileNum[leftCol][bottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "prawy":
                rightCol = (rightX+entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileManager.mapTileNum[rightCol][bottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }


                break;
        }

    }
    
    /**
     * sprawdzenie czy bohater dotyka obiektu
     * 
     */
    public int checkObject(Entity entity, boolean player){
        int index = 999;


        for(int i =0; i <gp.obj.length; i++){
            if(gp.obj[i] != null){


                gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "gora":
                        gp.player.solidArea.y -= gp.player.speed;
                        if(gp.player.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){

                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }

                        }
                        break;
                    case "dol":
                        gp.player.solidArea.y += gp.player.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "lewy":
                        gp.player.solidArea.x -= gp.player.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "prawy":
                        gp.player.solidArea.x += gp.player.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "pickUp":  //tutaj wykyrwam ze gracz kliknal spacje
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }

                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }

        return index;
    }


}
