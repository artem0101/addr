package Controllers;

import Interfaces.CollectionAddressBook;
import Objects.Person;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import utils.DialogManager;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by artem on 08.07.2017.
 */
public class MainController {
    private CollectionAddressBook addressBook = new CollectionAddressBook();
    private Stage mainStage;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader(); // для динамичной загрузки
    private EditDialogConroller editDialogConroller;
    private Stage editDialogStage;
    private ObservableList<Person> backupList;

    @FXML
    private CustomTextField tfSearch;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Person, String> tableFIO;

    @FXML
    private TableColumn<Person, String> tablePhone;

    @FXML
    private Label labelCount;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableFIO.setCellValueFactory(new PropertyValueFactory<>("fio"));
        tablePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        initListener();

        addressBook.fillTestData();
        backupList = FXCollections.observableArrayList();
        backupList.addAll(addressBook.getPersonList());
        table.setItems(addressBook.getPersonList());

        initLoader();
        setupClearButtonField(tfSearch);
    }

    private void initListener() {
        addressBook.getPersonList().addListener((ListChangeListener<Person>) c -> updateCountLabel());

        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editDialogConroller.setPerson((Person) table.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("../dialog.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogConroller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCountLabel() {
        labelCount.setText("Количество записей: " + addressBook.getPersonList().size());
    }

//    public void showDialog(ActionEvent actionEvent) {
//        try {
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("dialog.fxml")); // нужно для того чтобы заново создавать
//            stage.setTitle("Редактирование записи");
//            stage.setMinHeight(92);
//            stage.setMinWidth(300);
//            stage.setResizable(false);
//            stage.setScene(new Scene(root));
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow()); // для определения родительского окна
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) return;
        Button clickedButton = (Button) source;
        Person selectedPerson = (Person) table.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editDialogConroller.setPerson(selectedPerson);

        switch (clickedButton.getId()) {
            case "btnAdd":
                System.out.println("add " + selectedPerson);
                editDialogConroller.setPerson(new Person());
                showDialog();
                if (!editDialogConroller.getPerson().getFio().equals("") || !editDialogConroller.getPerson().getPhone().equals(""))
                    addressBook.add(editDialogConroller.getPerson());
                break;
            case "btnChange":
//                System.out.println("edit " + selectedPerson);
//                editDialogConroller.setPerson((Person) table.getSelectionModel().getSelectedItem());

                if (!personIsSelected(selectedPerson)) return;
                showDialog();
//                System.out.println("\n\n" + selectedPerson.getFio() + "\n" + selectedPerson.getPhone() + "\n\n");
//                if (selectedPerson.getFio().equals("") || selectedPerson.getPhone().equals("")) {
//                    DialogManager.showInfoDialog("Ошибка", "Введены не все данные");
//                    break;
//                } else
                    editDialogConroller.setPerson(selectedPerson);


                break;
            case "btnDel":
                System.out.println("delete " + selectedPerson);
//                addressBook.delete((Person) table.getSelectionModel().getSelectedItem());

                if (!personIsSelected(selectedPerson)) return;
                addressBook.delete(selectedPerson);

                break;
        }
    }

    private boolean personIsSelected(Person selectedPerson) {
        String name = "";
        String phone = "";
//        if (t)
        if (selectedPerson == null) {
            DialogManager.showInfoDialog("Ошибка", "Выберите запись");
            return false;
        }
        return true;
    }

    private void showDialog() {

//        try {
//            Stage stage = new Stage();
//            Parent root = FXMLLoader.load(getClass().getResource("../dialog.fxml")); // нужно для того чтобы заново создавать
//            stage.setTitle("Редактирование записи");
//            stage.setMinHeight(92);
//            stage.setMinWidth(300);
//            stage.setResizable(false);
//            stage.setScene(new Scene(root));
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow()); // для определения родительского окна
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(92);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        editDialogStage.showAndWait();
    }

    public void actionSearch(ActionEvent actionEvent) {
        addressBook.getPersonList().clear();
        for (Person person : backupList) {
            if (person.getFio().toLowerCase().contains(tfSearch.getText().toLowerCase()) ||
                    person.getPhone().toLowerCase().contains(tfSearch.getText().toLowerCase())) {
                addressBook.getPersonList().add(person);
            }
        }
    }
}
