package com.tp.src.gui;

/**
 *
 * @author Quentin
 */
import static com.tp.src.gui.SeConnecterController.infoBox;
import static com.tp.src.server.AES.encrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.ConnectionUtil;

public class CreateAccountController {

    @FXML
    private Button btn_validation;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_name;

    @FXML
    private PasswordField tf_password;

    @FXML
    private PasswordField tf_passwordConf;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public CreateAccountController() {
        connection = ConnectionUtil.connectdb();
    }

    @FXML
    void onClickBtnValidation(ActionEvent event) {

        String username = tf_username.getText().toString();
        String firstname = tf_password.getText().toString();
        String password = tf_password.getText().toString();
        String passwordConf = tf_passwordConf.getText().toString();

        String encryptedPassword = encrypt(password, "super");

        if (passwordConf.equals(password)) {

            String sql = "INSERT INTO user (username, firstname, password) VALUES (?, ?, ?)";

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, firstname);
                preparedStatement.setString(3, encryptedPassword);
                preparedStatement.executeUpdate();

                infoBox("Compte créé !", null, "Succès");
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                infoBox("Erreur ! Username ou mot de passe incorrect" + e, null, "Echec");
                e.printStackTrace();
            }
        } else {
            infoBox("Vous n'avez pas saisi les même mot de passe", null, "Failed");
        }
    }

}
