package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

/**
 * odczytywanie danych z klawiatury
 *
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed,spacePressed;
    GamePanel gp;
    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    /**
     * odczytanie wcisniêtego klawisza i nadanie mu wartoœci 
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        
        if(gp.gameState == gp.titleState) {
        	if(code == KeyEvent.VK_ENTER){
        	  gp.stopwatch.timer.start();
              gp.gameState = gp.playState;
            }if(code == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
            if(code == KeyEvent.VK_I){
                gp.gameState = gp.instructionState;
            }
        }
        
        else if(gp.gameState == gp.instructionState) {
        	if(code == KeyEvent.VK_ESCAPE){
              gp.gameState = gp.titleState;
            }
        }

        
        if(gp.gameState == gp.playState) {
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }  if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }  if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }  if(code == KeyEvent.VK_RIGHT){
            rightPressed =  true;
        } if(code == KeyEvent.VK_SPACE){
            spacePressed =  true;
        } if(code == KeyEvent.VK_ESCAPE){
            System.exit(0);
        } if(code == KeyEvent.VK_N){
        	gp.level = 1;
            gp.gameState = gp.titleState;
            gp.resetLevel();
        	}
        }
        
        

    }
    
    /**
     * odczytanie odciœniêtego klawisza które powoduje zmianê wartoœci zmiennych sterowania
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }  if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }  if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }  if(code == KeyEvent.VK_RIGHT){
            rightPressed =  false;
        } if(code == KeyEvent.VK_SPACE){
            spacePressed =  false;
        }

    }
}
