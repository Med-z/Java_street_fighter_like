package streetfighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
    private final int WIDTH = 1306, HEIGHT = 560;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Déclarations JavaFX
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Street Fighter");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        // Affichage du splashscreen
        final Image backgroundImageSC = new Image("streetfighter/Background/SplashScreenBackground.gif"); // Créer le background du Splash Screen
        Background background = new Background(0,0, WIDTH, HEIGHT, backgroundImageSC);
        root.getChildren().add(background.renderer);
        final Image logoImageSC = new Image("streetfighter/Background/StreetFighterLogo.png"); // Créer le Logo du Splash Screen
        ImageView logoImageViewSC = new ImageView(logoImageSC);
        AnchorPane.setTopAnchor(logoImageViewSC, 10.0); 
        AnchorPane.setLeftAnchor(logoImageViewSC, 653-(logoImageSC.getWidth())/2); // Positionne l'image au centre
        root.getChildren().add(logoImageViewSC);
        final Image PressAnyKeyImageSC = new Image("streetfighter/Background/PressAnyKey.png", 425, 50, false, false); // Créer le texte du Splash Screen
        ImageView PressAnyKeyImageViewSC = new ImageView(PressAnyKeyImageSC);
        AnchorPane.setTopAnchor(PressAnyKeyImageViewSC, 500.0); 
        AnchorPane.setLeftAnchor(PressAnyKeyImageViewSC, 653-(PressAnyKeyImageSC.getWidth())/2); // Positionne l'image au centre
        root.getChildren().add(PressAnyKeyImageViewSC);
     

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
        
        Ryu ryu = new Ryu(20, HEIGHT-222, 156, 222, 7);
        Ken ken = new Ken(1000, HEIGHT-222 , 156, 222, 7);
        
        FightManager fightManager = new FightManager(ryu,ken,background,root);
        FightManager.instance.startRound();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        FightManager.instance.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}