package streetfighter;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import streetfighter.interfaces.Renderable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import streetfighter.managers.CollisionManager;
import streetfighter.managers.InputManager;
import streetfighter.other.GameObject;

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
    private static List<GameObject> goWaitList;
    private static List<GameObject> goGarbage;
    private Background background;
    AnchorPane root;
    private Timer timer;
    private Timer deleteLater;
    int interval;
    private final int WIDTH = 1306, HEIGHT = 560;
    HealthBar HBryu,HBken;
    
    public List<Timer> listTimer;
   
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
       listTimer = new ArrayList<>();
   }
   
   public void initializeFight()
   {
        gameObjects = new ArrayList<>();
        goWaitList = new ArrayList<>();
        goGarbage = new ArrayList<>();
        gameObjects.add(background);
        player1.setOtherPlayer(player2);
        player2.setOtherPlayer(player1);
        gameObjects.add(player1);
        gameObjects.add(player2);
        HBryu = new HealthBar(0, 0, player1.getHealthPoint(), 50, player1, false);
        HBken = new HealthBar(806, 0, player2.getHealthPoint(), 50, player2, true);   
        gameObjects.add(HBken);
        gameObjects.add(HBryu);
        root.getScene().setOnKeyPressed(new InputManager.KeyPressed());
        root.getScene().setOnKeyReleased(new InputManager.KeyReleased());
         for(GameObject go : gameObjects) {
            if (go instanceof Renderable) {
                root.getChildren().add(((Renderable) go).getRenderer());
            }
        }
        Label counterLabel = new Label();
        counterLabel.setTranslateX(WIDTH/2);
        counterLabel.setTranslateZ(100);
        counterLabel.setTextFill(Color.RED);
        root.getChildren().add(counterLabel);
        countDown = new CountDown(counterLabel,100,player1,player2);        
        startRound();
        
   }
   
   public void startRound()
   {
        
        player1.resetPosition();
        player2.resetPosition();
        player1.canMove = true;
        player2.canMove = true;
        player1.setHealthPoint(100);
        player2.setHealthPoint(100);
        HBryu.update();
        countDown.startTimer();
        // Boucle pour ajouter au AnchorPane les gameObjects "Renderable"
       
        //Set the timer fight
        

        // Initialiser l'InputManager
       

        // Game Loop
        timer = new Timer();
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
            Platform.runLater(() -> {
                // Ajouter les objets de la waitlist
                gameObjects.addAll(goWaitList);
                goWaitList.removeAll(goWaitList);

                for (GameObject go : gameObjects) {
                    go.update();
                    Rectangle rect = new Rectangle(go.getX(), go.getY(), go.getWidth(), go.getHeight());
                    rect.setOpacity(0.4);
                    if (go instanceof Renderable) {
                        ((Renderable) go).draw();
                    }

                    // Permet d'afficher les hurtbox en noir, ça ne s'en va pas mais osef, c'est pour un intérêt temporaire
                    if(!(go instanceof Background)) {
                        root.getChildren().add(rect);
                        deleteLater = new Timer();
                        
                        deleteLater.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    root.getChildren().remove(rect);
                                });
                            }
                        }, 16);
                    }
                }

                CollisionManager.checkForCollisions(gameObjects);

                gameObjects.removeAll(goGarbage);
                goGarbage.removeAll(goGarbage);

                InputManager.resetTempKeys();
            });
            }
        };
        timer.schedule(gameLoop, 0, 16);
    }
   
   public void finishRound()
   {
       checkWinner();
       stopAllTimer();
       interval = 5;
       Timer timerEndRound = new Timer();
       FightManager.instance.listTimer.add(timer);
       timerEndRound.scheduleAtFixedRate(new TimerTask(){

           @Override
           public void run()
           {
                Platform.runLater(() -> {
                    interval--;
                    if(interval <= 1) {
                        System.out.println("Round fini mon pote");
                        startRound();
                        timerEndRound.cancel();
                    }
                });
           }
       }, 0, 1000);
    }
       
   
   
   public void stopAllTimer()
   {
      for(Timer timer : listTimer)
       {
           if(timer != null)
           {
               System.out.println("Coupé ! ");
              timer.cancel();
           }
           
       } 
   }
   
   public void checkWinner()
   {
       if(player1.getHealthPoint() < player2.getHealthPoint())
        {
            System.out.println("Player 2 won ! ");
            player2.win();
            player1.ko();
            player2.roundWon++;
        }
        else if (player2.getHealthPoint() < player1.getHealthPoint())
        {
            System.out.println("Player 1 won ! ");
            player1.win();
            player2.ko();
            player1.roundWon++;
        }
        else if (player2.getHealthPoint() ==  player1.getHealthPoint())
        {
            System.out.println("Egalité ! "); //Je sais pas le dire en anglais
            player2.win();
            player1.win();
            player2.roundWon++;
            player1.roundWon++;
        }
        player1.canMove = false;
        player2.canMove = false;
   }
           
   
    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }
    public static List<GameObject> getGoWaitList() {
        return goWaitList;
    }
    public static List<GameObject> getGoGarbage() {
        return goGarbage;
    }

    public void stop()
    {  
        if(timer != null)
        {
            timer.cancel();
        }
        if(deleteLater != null)
        {
            deleteLater.cancel();
        }
        stopAllTimer();
    }



}
