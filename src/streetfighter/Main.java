package streetfighter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import streetfighter.other.GameObject;

import java.util.*;

import javafx.scene.image.Image;

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
        final Image backgroundImageSC = new Image("streetfighter/Background/SplashScreenBackground.gif"); // Créer le background du Splash Screen
        Background background = new Background(0,0, WIDTH, HEIGHT, backgroundImageSC);
        root.getChildren().add(background.renderer);

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

       
        final Image backgroundImage = new Image("streetfighter/Background/Background1.gif"); // Ici est créée l'image (à partir de l'URL) afin de l'utiliser dans streetfighter.streetfighter.Background
        Background background = new Background(0,0, WIDTH, HEIGHT, backgroundImage);
        
        
        Ryu ryu = new Ryu(20, 340, 30, 120, 7);
        Ken ken = new Ken(1000, 340 , 30, 120, 7);
        
        FightManager fightManager = new FightManager(ryu,ken,background,root);
        FightManager.instance.startRound();
        

        
       


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