package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * utworzenie timera
 *
 */
public class Stopwatch implements ActionListener {
    JFrame frame = new JFrame();
    JLabel timeLabel = new JLabel();
    int elapsedTime = 60;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    public String seconds_string = String.format("%02d", seconds);
    public String minutes_string = String.format("%02d", minutes);
    public String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });


    Stopwatch() {
    	
     

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}