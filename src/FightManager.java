
import interfaces.Renderable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import managers.InputManager;
import other.GameObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leodo_000
 */
public class FightManager {
    
   public static FightManager instance;
   int round = 0;
   Character player1,player2;
   private CountDown countDown;
   private static List<GameObject> gameObjects;
   private Background background;
   AnchorPane root;
   private Timer timer;
   private final int WIDTH = 1306, HEIGHT = 560;
   
   public FightManager(Character player1,Character player2,Background background,AnchorPane root)
   {
       if(instance == null)
       {
           instance = this;
       }
       this.player1 = player1;
       this.player2 = player2;
       this.background = background;
       this.root = root;
   }
   
   public void startRound()
   {
        gameObjects = new ArrayList<>();
        gameObjects.add(background);
        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);
        gameObjects.add(player1);
        gameObjects.add(player2);
        HealthBar HBryu = new HealthBar(0, 0, player1.getHealthPoint()*5, 50, player1);
        HealthBar HBken = new HealthBar(806, 0, player2.getHealthPoint()*5, 50, player2);
        gameObjects.add(HBken);
        gameObjects.add(HBryu);
        // Boucle pour ajouter au AnchorPane les gameObjects "Renderable"
        for(GameObject go : gameObjects) {
            if (go instanceof Renderable) {
                root.getChildren().add(((Renderable) go).getRenderer());
            }
        }
        //Set the timer fight
        Label counterLabel = new Label("Yo la team");
        counterLabel.setTranslateX(WIDTH/2);
        counterLabel.setTranslateZ(100);
        counterLabel.setTextFill(Color.RED);
        root.getChildren().add(counterLabel);
        countDown = new CountDown(counterLabel,100,player1,player2);
        countDown.startTimer();

        // Initialiser l'InputManager
        root.getScene().setOnKeyPressed(new InputManager.KeyPressed());
        root.getScene().setOnKeyReleased(new InputManager.KeyReleased());

        // Game Loop
        timer = new Timer();
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                try {
                    for (GameObject go : gameObjects) {
                        go.update();

                        if (go instanceof Renderable) {
                            ((Renderable) go).draw();
                        }
                    }

                } catch (ConcurrentModificationException exception) {
                    // TODO: un jour faudra changer le type de boucle ou autre chose psk sinon tout le jeu va être paralysé lol
                }
                InputManager.resetTempKeys();
            }
        };
        timer.schedule(gameLoop, 0, 16);
    }
   
    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }  
   
    
}
