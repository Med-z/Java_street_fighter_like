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
        
        String imagePath = "D:\\_Documents\\_MateoPontoizeau\\_DUTInformatique\\SEMESTRE-2\\_PTS2\\_StreetFighter";
        ImageFond fond = new ImageFond(imagePath);
        fond.pack();
        fond.setVisible(true);
    }
}
