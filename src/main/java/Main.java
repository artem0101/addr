import Controllers.MainController;
import Interfaces.CollectionAddressBook;
import Objects.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by artem on 08.07.2017.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Address book");
//        primaryStage.setMinWidth(370);
//        primaryStage.setMinHeight(313);
//        primaryStage.setScene(new Scene(root, 313, 370));
//        primaryStage.setMaxHeight(370);
//        primaryStage.setMaxWidth(313);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//
//        testData();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));

        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Address book");
        primaryStage.setMinWidth(370);
        primaryStage.setMinHeight(313);
        primaryStage.setScene(new Scene(fxmlMain, 313, 370));
        primaryStage.setMaxHeight(370);
        primaryStage.setMaxWidth(313);
        primaryStage.setResizable(false);
        primaryStage.show();

        testData();
    }

    private void testData() {
        CollectionAddressBook addressBook = new CollectionAddressBook();
        addressBook.fillTestData();
        addressBook.print();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
