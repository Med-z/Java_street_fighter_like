package streetfighter;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
    private final int WIDTH = 1306, HEIGHT = 560;
    Music music = new Music();
    FightManager fightManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        music.playMusic();
        // Déclarations JavaFX
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Street Fighter");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
        // Affichage du logo
        Image IconeImage = new Image("streetfighter/Background/Icone.png");
        primaryStage.getIcons().add(IconeImage);
        
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
            startMenu(root);
        });
    }
    // startMenu : lancer le menu
    public void startMenu(AnchorPane root) {
        // Fond du Menu
        final Image menuBackgroundImageSC = new Image("streetfighter/Background/SplashScreenBackground.gif");
        Background background = new Background(0,0, WIDTH, HEIGHT, menuBackgroundImageSC);
        root.getChildren().add(background.renderer);
        
        // Image Texte du menu
        final Image menuImage = new Image("streetfighter/Menu/Menu.png"); 
        ImageView menuImageView = new ImageView(menuImage);
        AnchorPane.setTopAnchor(menuImageView, 10.0); 
        AnchorPane.setLeftAnchor(menuImageView, 653-(menuImage.getWidth())/2); 
        root.getChildren().add(menuImageView);
        
        // Image Texte play orange
        final Image playImageO = new Image("streetfighter/Menu/PlayOrange.png"); 
        ImageView playImageViewO = new ImageView(playImageO);
        AnchorPane.setTopAnchor(playImageViewO, 200.0); 
        AnchorPane.setLeftAnchor(playImageViewO, 653-(playImageO.getWidth())/2);
        root.getChildren().add(playImageViewO);
        // Image Texte play jaune
        final Image playImageY = new Image("streetfighter/Menu/PlayYellow.png"); 
    
        // Switch de couleur lorque l'on passe la souris sur l'image
        playImageViewO.setOnMouseEntered(e->    { playImageViewO.setImage(playImageY); }  );
        playImageViewO.setOnMouseExited(e->     { playImageViewO.setImage(playImageO); }  );
        // Lorsque l'on clique sur play, le combat commence
        playImageViewO.setOnMouseClicked(e-> {
                root.getChildren().removeAll(root.getChildren()); 
                startGame(root);
            });
        
        // Image Texte Music OFF orange
        final Image musicOFFOImage = new Image("streetfighter/Menu/MusicOFFO.png", 194.5, 46.5, false, false);
        ImageView musicOFFOImageView = new ImageView(musicOFFOImage);
        AnchorPane.setTopAnchor(musicOFFOImageView, 500.0); 
        AnchorPane.setLeftAnchor(musicOFFOImageView, (653-(musicOFFOImage.getWidth())/2)-150); 
        root.getChildren().add(musicOFFOImageView);
        // Image Texte Music OFF jaune
        final Image musicOFFYImage = new Image("streetfighter/Menu/MusicOFFY.png", 194.5, 46.5, false, false);
        
        // Switch de couleur lorque l'on passe la souris sur l'image
        musicOFFOImageView.setOnMouseEntered(e->    { musicOFFOImageView.setImage(musicOFFYImage);} );
        musicOFFOImageView.setOnMouseExited(e->     { musicOFFOImageView.setImage(musicOFFOImage);} );
        // Lorsque l'on clique sur Music OFF, la musique se met en pause
        musicOFFOImageView.setOnMouseClicked(e-> {
                music.getMediaPlayer().pause();
            });
        // Image Texte Music ON orange
        final Image musicONOImage = new Image("streetfighter/Menu/MusicONO.png", 194.5, 46.5, false, false);
        ImageView musicONOImageView = new ImageView(musicONOImage);
        AnchorPane.setTopAnchor(musicONOImageView, 500.0); 
        AnchorPane.setLeftAnchor(musicONOImageView, (653-(musicONOImage.getWidth())/2)+150); 
        root.getChildren().add(musicONOImageView);
        // Image Texte Music ON jaune
        final Image musicONYImage = new Image("streetfighter/Menu/MusicONY.png", 194.5, 46.5, false, false);
        
        // Switch de couleur lorque l'on passe la souris sur l'image
        musicONOImageView.setOnMouseEntered(e->     { musicONOImageView.setImage(musicONYImage);} );
        musicONOImageView.setOnMouseExited(e->      { musicONOImageView.setImage(musicONOImage);} );
        // Lorsque l'on clique sur Music ON, la musique se met en play
        musicONOImageView.setOnMouseClicked(e-> {
                music.getMediaPlayer().play();
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
        
        fightManager = new FightManager(ryu,ken,background,root);
        fightManager.initializeFight();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        fightManager.instance.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}