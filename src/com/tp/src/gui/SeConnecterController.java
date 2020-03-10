/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp.src.gui;

/**
 *
 * @author Quentin
 */
import static com.tp.src.server.AES.*;
import util.ConnectionUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Bushan Sirgur
 */
public class SeConnecterController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public SeConnecterController() {
        connection = ConnectionUtil.connectdb();
    }

    public void loginAction(ActionEvent event) {
        String username = userName.getText().toString();
        String password = userPassword.getText().toString();
        
        String decryptedPassword = encrypt(password,"super");

        String sql = "SELECT * FROM user WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, decryptedPassword);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Erreur ! Username ou mot de passe incorrect", null, "Failed");
            } else {
                infoBox("Connexion réussi !", null, "Success");
                Node node = (Node) event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("Chat.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClickBtn_createAccount(ActionEvent event) {
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createAccount.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
