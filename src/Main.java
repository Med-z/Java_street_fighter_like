import interfaces.Renderable;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import managers.InputManager;
import other.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Main extends Application {
    private Timer timer; // Timer déclaré ici pour l'arrêter dans stop()
    private final int WIDTH = 1306, HEIGHT = 560;
    CountDown countDown;

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
        List<GameObject> gameObjects = new ArrayList<>();

       
        final Image backgroundImage = new Image("Background/Background0.gif"); // Ici est créée l'image (à partir de l'URL) afin de l'utiliser dans Background
        Background background = new Background(1,1, WIDTH, HEIGHT, backgroundImage);
        gameObjects.add(background);
        
        Ryu ryu = new Ryu(20, 340, 30, 120, 7);
        Alex alex = new Alex(600, 340 , 30, 120, 7);
        alex.setOtherPlayer(ryu);
        ryu.setOtherPlayer(alex);
        gameObjects.add(ryu);
        gameObjects.add(alex);
        
        


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
        countDown = new CountDown(counterLabel,100);
        countDown.startTimer();

        // Initialiser l'InputManager
        root.getScene().setOnKeyPressed(new InputManager.KeyPressed());
        root.getScene().setOnKeyReleased(new InputManager.KeyReleased());

        // Game Loop
        timer = new Timer();
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                for(GameObject go : gameObjects) {
                    go.update();

                    if (go instanceof Renderable) {
                        ((Renderable) go).draw();
                    }
                }
            }
        };
        timer.schedule(gameLoop, 0, 16);
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