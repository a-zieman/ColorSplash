package main;

import entity.Player;
import object.Gem;
import object.SuperObject;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



/**
 * inicjalizowanie gry
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	public final int tileSize = 48;
    public final int screenCol = 24;
    public final int screenRow = 17;
    public final int screenWidth = tileSize * screenCol;
    public final int screenHeight = tileSize * screenRow;
    
    //FPS
    int FPS = 60;
    public int level = 1;
    
    Stopwatch stopwatch = new Stopwatch();


    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);
    Sound sound = new Sound(); ///
    public TileManager tileManager;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public SuperObject[] obj = new SuperObject[5];
    
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int instructionState = 3;




    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //komponenty beda sie buforowac przed wyswietleniem
        startGameThread();
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setOpaque(true);
        tileManager  = new TileManager(this);


    }
    
    /**
     * wywo³anie funkcji umieszczenia elementów, odtwarzania muzyki
     */
    public void setupGame(){
        assetSetter.setObject();
        playMusic(0);
        gameState = titleState;
    }

    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * gameloop
     */
    @Override
    public void run() {     

        double drawInterval =  1000000000/FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread !=null){ //dopóki gameThread istnieje, while siê wykonuje

            // update character position
            update();
            // draw screen
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) (remainingTime));
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

/**
 * aktualizacja po³o¿enia bohatera
 */
    public void update(){
        player.update();


    }
    /**
     *  wyœwietlanie gry w oknie
     */
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        //gst
        if (gameState == titleState) {
        	paintTitle(g2d);
        }else if (gameState == instructionState) {
        	paintInstruction(g2d);
        }else {
            if(win()){
                stopwatch.timer.stop();
                paintTimer(g2d,true);

            }
            else {
                tileManager.draw(g2d);

                for(int i = 0; i <obj.length; i++){
                    if(obj[i] != null){
                        obj[i].draw(g2d,this);
                    }
                }
                player.draw(g2d);
                paintPoziom(g2d);
                paintTimer(g2d,false);

            }
        }
        
    
        g2d.dispose();
    }

    /**
     * wyœwietla w lewym dolnym rogu na jakim poziomie jest aktualnie gracz
     */
    public void paintPoziom(Graphics g2d){
        g2d.setFont(new Font("Mv Boli", Font.BOLD, 32));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Poziom: "+level,10,screenHeight-13);
    }

    /**
     * wyœwietlanie timera
     */
    public void paintTimer(Graphics g2d, boolean czyWygrana){

        g2d.setColor(Color.WHITE);
        if (czyWygrana == true){
            g2d.setFont(new Font("Mv boli", Font.BOLD, 155));
            g2d.drawString(stopwatch.hours_string + ":" + stopwatch.minutes_string + ":" + stopwatch.seconds_string,screenWidth/6,screenHeight/2);
            g2d.setFont(new Font("Arial", Font.PLAIN, 55));
            g2d.drawString("Twój czas gry",425,500);
        }else
        {
            g2d.setFont(new Font("Mv boli", Font.BOLD, 32));
            g2d.drawString(stopwatch.hours_string + ":" + stopwatch.minutes_string + ":" + stopwatch.seconds_string,screenWidth-185,screenHeight-13);
        }

    }
    
    /**
     * wyœwietlanie grafiki na pocz¹tku gry
     */
    public void paintTitle(Graphics2D g2d) {
    	
    	final BufferedImage titleScreen;
    	try {
			titleScreen = ImageIO.read(getClass().getResourceAsStream("/icon/title.png"));
			g2d.drawImage(titleScreen,1,1,tileSize*24,tileSize*17,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    /**
     * wyœwietlenie instrukcji
     */
 public void paintInstruction(Graphics2D g2d) {
    	
    	final BufferedImage instructionScreen;
    	try {
    		instructionScreen = ImageIO.read(getClass().getResourceAsStream("/icon/inst.png"));
			g2d.drawImage(instructionScreen,1,1,tileSize*24,tileSize*17,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * nastêpny poziom, resetowanie ustawieñ
     */
    public void reLoad(){
        level++;
        tileManager  = new TileManager(this);
        //System.out.println("next level");
        player.x = tileSize;
        player.y = tileSize;
        assetSetter.setObject();
        player.hasYellow = false;
        player.hasBlue = false;
        player.hasRed = false;
    }
    
    /**
     * jeœli poziom wiêkszy ni¿ 3 pokazuje siê informacja o wygranej
     */
    public boolean win(){
        if(level>3){
            return true;
        }else {
            return  false;
        }

    }
    
    /**
     * uruchomienie muzyki (otworzenie pliku, uruchomienie go i odtwaranie w zapêtleniu)
     */
    public void playMusic(int i) {
    	sound.setFile(i);
    	sound.play();
    	sound.loop();
    	
    }

    /**
     * wy³¹czenie muzyki
     */
    public void stopMusic() {
    	sound.stop();
    }
    
    /**
     * specjalne efekty dzwiêkowe (otworzenie pliku i jednorazowe odtworzenie)
     */
    public void playSE(int i) {
    	sound.setFile(i);
    	sound.play();
    }
    
   
    public void resetLevel() {
    	  stopwatch.elapsedTime = 0;  
    	  level = 1;
    	  tileManager  = new TileManager(this);
          player.x = tileSize;
          player.y = tileSize;
          assetSetter.setObject();
          player.hasYellow = false;
          player.hasBlue = false;
          player.hasRed = false;
    }



}
