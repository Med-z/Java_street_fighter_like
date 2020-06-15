package streetfighter;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leodo_000
 */
public class CountDown {
    
    int interval;
    static Timer timer;
    SimpleIntegerProperty integer;
    Character player1,player2;
    
    public CountDown(Label label,int interval,Character player1,Character player2)
    {
        this.interval = interval;
        integer = new SimpleIntegerProperty(this.interval);
        label.textProperty().bind(integer.asString());
        this.player1 = player1;
        this.player2 = player2;
}
   
       
        
        
        
    public void startTimer()
    {
        /* -------------------- Timer ------------------- */
        
       timer = new Timer();
       timer.scheduleAtFixedRate(new TimerTask(){

           @Override
           public void run()
           {
                Platform.runLater(() -> {
                    interval--;
                    integer.set(interval);
                    if(interval <= 1) {
                        if(player1.getHealthPoint() < player2.getHealthPoint())
                        {
                            System.out.println("Player 2 won ! ");
                            player2.Win();
                            player2.roundWon++;
                        }
                        else if (player2.getHealthPoint() < player1.getHealthPoint())
                        {
                            System.out.println("Player 1 won ! ");
                            player1.Win();
                            player1.roundWon++;
                        }
                        else if (player2.getHealthPoint() ==  player1.getHealthPoint())
                        {
                            System.out.println("Egalité ! "); //Je sais pas le dire en anglais
                            player2.Win();
                            player1.Win();
                            player2.roundWon++;
                            player1.roundWon++;
                        }
                        player1.canMove = false;
                        player2.canMove = false;
                        timer.cancel();
                    }
                });
           }
       }, 0, 1000);
    }
    
    public void stopTimer()
    {
        timer.cancel();
    }

}
