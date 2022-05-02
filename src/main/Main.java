package main;

import javax.swing.*;
import javax.swing.ImageIcon;



/**
 *  tworzenia okna aplikacji
 *
 */
public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
        GamePanel panel = new GamePanel();
        panel.setupGame();

        window.add(panel);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("ColorSplash");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        ImageIcon ikonka = new ImageIcon("/icon/heart.png"); //create an ImageIcon
        window.setIconImage(ikonka.getImage()); //change icon of frame
        

	}

}
