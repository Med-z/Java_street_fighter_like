import interfaces.Renderable;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import other.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;

public class Main extends Application {
    Timer timer; // Timer déclaré ici pour l'arrêter dans stop()

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Déclarations JavaFX
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Street Fighter");
        primaryStage.setScene(new Scene(root, 1306, 560));
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
        // Initialisation des gameObjects
        List<GameObject> gameObjects = new ArrayList<>();

        final URL backgroundURL = getClass().getResource("Background0.gif");  
        final Image backgroundImage = new Image(backgroundURL.toExternalForm()); // Ici est créée l'image (à partir de l'URL) afin de l'utiliser dans Background
        Background background = new Background(1,1,1306,560,backgroundImage);
        gameObjects.add(background);
        
        
        // Boucle pour ajouter au AnchorPane les gameObjects "Renderable"
        for(GameObject go : gameObjects) {
            if (go instanceof Renderable) {
                root.getChildren().add(((Renderable) go).getRenderer());
            }
        }

        // Game Loop
        timer = new Timer();
        TimerTask gameLoop = new TimerTask() {
            @Override
            public void run() {
                System.out.println("lunette de soleil même la nuit");
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
        } catch(NullPointerException e) {
            System.out.println("Impossible d'arrêter le Timer, il n'est pas démarré.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
