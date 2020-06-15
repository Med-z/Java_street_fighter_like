import interfaces.Renderable;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import managers.InputManager;
import other.GameObject;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Main extends Application {
    private Timer timer; // Timer déclaré ici pour l'arrêter dans stop()
    private final int WIDTH = 1306, HEIGHT = 560;
    private static List<GameObject> gameObjects;
    private CountDown countDown;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Déclarations JavaFX
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Street Fighter");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        // Affichage du splashscreen
        HBox hbox_center = new HBox(); // La HBox permet de centrer le Label (ou pas mdr faut fix ça) TODO: Fix lbl_startHint's alignment
        root.getChildren().add(hbox_center);
        Label lbl_startHint = new Label("Press any key to start the game");
        hbox_center.getChildren().add(lbl_startHint);
        hbox_center.setAlignment(Pos.CENTER);

        // Appuyer pour démarrer
        primaryStage.getScene().setOnKeyPressed(event -> {
            root.getChildren().removeAll(root.getChildren()); // Retire tous les éléments de la collection, peu importe l'allure du splash-screen
            startGame(root);
        });
    }

    // startGame : démarrer le Timer de la boucle principale du jeu
    // root : AnchorPane : l'élement parent principal, créé dans start()
    public void startGame(AnchorPane root) {
        // Initialisations et ajouts des gameObjects
        gameObjects = new ArrayList<>();

       
        final Image backgroundImage = new Image("Background/Background1.gif"); // Ici est créée l'image (à partir de l'URL) afin de l'utiliser dans Background
        Background background = new Background(0,0, WIDTH, HEIGHT, backgroundImage);
        gameObjects.add(background);
        
        Ryu ryu = new Ryu(20, 340, 30, 120, 7);
        Ken ken = new Ken(600, 340 , 30, 120, 7);
        ken.setOtherPlayer(ryu);
        ryu.setOtherPlayer(ken);
        gameObjects.add(ryu);
        gameObjects.add(ken);

        HealthBar HBryu = new HealthBar(0, 0, ryu.getHealthPoint()*5, 50, ryu);
        HealthBar HBken = new HealthBar(806, 0, ken.getHealthPoint()*5, 50, ken);
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
        countDown = new CountDown(counterLabel,5,ryu,ken);
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

    // Je réécris la méthode stop() pour pouvoir arrêter le timer
    @Override
    public void stop() throws Exception {
        super.stop();
        try {
            timer.cancel();
            countDown.stopTimer();
        } catch(NullPointerException e) {
            System.out.println("Impossible d'arrêter le Timer, il n'est pas démarré.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}