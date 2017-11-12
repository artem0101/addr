package Controllers;


import Objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DialogManager;

/**
 * Created by artem on 08.07.2017.
 */
public class EditDialogConroller {

    @FXML
    private TextField txtFIO;

    @FXML
    private TextField txtPhone;

    private Person person;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setPerson(Person person) {
        if (person == null) return;
        this.person = person;
        txtFIO.setText(person.getFio());
        txtPhone.setText(person.getPhone());
    }

    public void actionSave(ActionEvent actionEvent) {
        if (txtPhone.getText().equals("") || txtFIO.getText().equals("")) {
            DialogManager.showInfoDialog("Ошибка", "Введены не все данные");
        } else {
            person.setPhone(txtPhone.getText());
            person.setFio(txtFIO.getText());
            actionClose(actionEvent);
        }
    }

    public Person getPerson() {
        return person;
    }
}
