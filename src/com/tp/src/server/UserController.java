package com.tp.src.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.tp.src.bdd.*;

public class UserController {

    Model model = new Model();
    final String secretKey = "secret";

    public UserController() {

    }
    //Verifie si l'utilisateur existe, revoie un objet UserCheck 

    public UserCon CheckUser(UserCon user) {
        UserCon conUser = new UserCon();
        conUser.login = user.login;
        conUser.password = user.password;

        String username = "";

        ResultSet rs = null;
        String password = AES.encrypt(user.password, secretKey);
        System.out.print(password + "ici");
        String query = "SELECT login FROM user WHERE login='" + user.login + "' AND password='" + password + "' ";
        // String query = "SELECT login FROM user WHERE login='rom' AND password='Rom123' ";  //for testing
        rs = model.bddConnection(query);
        try {
            while (rs.next()) {

                username = rs.getString(1);
                System.out.print(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Objects.equals(username, user.login)) {
            System.out.print("can connect");
            conUser.canConnect = true;
            return conUser;
        } else {
            System.out.print("can not connect");
            conUser.canConnect = false;
            return conUser;
        }
    }

    // Ajoute un utilisateur
    public UserNew UserAdd(UserNew user) {
        UserNew newUser = new UserNew();
        newUser.email = user.email;
        newUser.login = user.login;
        newUser.password = user.password;
        String queryLogin = "SELECT login FROM user WHERE login='" + user.login + "'";
        String queryEmail = "SELECT email FROM user WHERE email='" + user.email + "' ";
        ResultSet rsLogin = null;
        ResultSet rsEmail = null;
        rsLogin = model.bddConnection(queryLogin);
        rsEmail = model.bddConnection(queryEmail);

        try {
            if (!rsLogin.next() && !rsEmail.next()) // !rs.next() renvoie true si la requete est vide
            {
                //Ajoute k'utilisateur s'il n'hexiste pas 
                newUser.done = "yes";
                model.AddUser(newUser, secretKey);
                return newUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        };

        try {
            if (!rsLogin.next() && rsEmail.next()) {
                newUser.done = "email";
            }
            return newUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rsLogin.next() && !rsEmail.next()) {
                newUser.done = "login";
            }
            return newUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
