import interfaces.Renderable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import other.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    Timer timer; // Timer déclaré ici pour l'arrêter dans stop()

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Déclarations JavaFX
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Street Fighter");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        // Initialisation des gameObjects
        List<GameObject> gameObjects = new ArrayList<>();
        // Ajouter ici le background (doit extend de GameObect)

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
                System.out.println("Game running");
            }
        };
        timer.schedule(gameLoop, 0, 16);
    }

    // Je réécris la méthode stop() pour pouvoir arrêter le timer
    @Override
    public void stop() throws Exception {
        super.stop();
        timer.cancel();
    }

    public static void main(String[] args) {
        launch(args);

        //test
        
        // String URL = "https://www.google.com/search?q=background+street+fighter+gif&sxsrf=ALeKk03zVLsD32E_DMyJjXCnivPli9rNpw:1591088481236&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjrtOu94uLpAhWlDGMBHU4PB1QQ_AUoAXoECAsQAw&biw=1604&bih=792#imgrc=ecQJz6dGNhbgWM";
        // Background fond = new Background(URL);
        // fond.pack();
        // fond.setVisible(true);
    }
}
