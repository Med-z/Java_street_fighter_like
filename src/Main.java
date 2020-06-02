import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        // Ajoutez un commentaire ci-dessous !
        // cbuchmul: ok! test git pull!
        // ldorn : coucou les zamis ! 
        // mpontoiz : salut les ptits copaing !
        // zkaddour : saluuuuuuuuuut !!!!!!
        // rderouel : yop les loustiks !
        //
        //

    }


    public static void main(String[] args) {
        launch(args);
        
        String URL = "https://www.google.com/search?q=background+street+fighter+gif&sxsrf=ALeKk03zVLsD32E_DMyJjXCnivPli9rNpw:1591088481236&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjrtOu94uLpAhWlDGMBHU4PB1QQ_AUoAXoECAsQAw&biw=1604&bih=792#imgrc=ecQJz6dGNhbgWM";
        Background fond = new Background(URL);
        fond.pack();
        fond.setVisible(true);
    }
}
