/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Navegacion;
import model.Session;
import model.User;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class IniciarController implements Initializable {
    private Stage primaryStage;
    private Scene escenaPrincipal; 
    @FXML
    private Button siguiente;
    @FXML
    private TextField nickname;
    @FXML
    private Label errorNick;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorPass;
    private Navegacion minav = null;
    
    public void initStage(Stage stage){
	primaryStage = stage;
	escenaPrincipal = stage.getScene();
        try {
            minav = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException e) {
            e.printStackTrace();
        }
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(nickname.textProperty(), password.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (nickname.getText().isEmpty() || password.getText().isEmpty());
            }       
        };
        errorNick.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            if(minav.exitsNickName(nickname.getText()))
                errorNick.setText("");
            else {
                errorNick.setText("El usuario no esta registrado");
                siguiente.setDisable(true);
            }
        });
        errorPass.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            if(User.checkPassword(password.getText()))
                errorPass.setText("");
            else {
                errorPass.setText("La contraseña utilizada no es valida");
                siguiente.setDisable(true);
            }
        });
        siguiente.disableProperty().bind(bb);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void IniSalirClick(ActionEvent event) {
        try {
            System.out.println("Cerrando inicio de sesión");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = miCargador.load();
            primaryStage.setTitle("Navs");
            primaryStage.setScene(escenaPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void IniregistrarseClick(ActionEvent event) {
        try {
            primaryStage.setTitle("Registrarse");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("registrar.fxml"));
            Parent root = miCargador.load();
         // acceso al controlador de ventana 1
            RegistrarController ventanaini = miCargador.getController();
            ventanaini.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    private void IniSiguienteClick(ActionEvent event) {
        User usuario = minav.getUser(nickname.getText());
        if(!usuario.checkCredentials(nickname.getText(),password.getText())){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            ButtonType buttonTypeCancel = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeCancel);
            alert.setContentText("El usuario y la contraseña no coinciden");
            alert.showAndWait();
            return;
        }
        FXMLMainController.activeUser=usuario;
        primaryStage.setTitle("Elegir Tipo de Seleccion de Problema");
        FXMLLoader Cargador  = new FXMLLoader(getClass().getResource("elegirproblema.fxml"));
        Parent raiz = null;
        try {
            raiz = Cargador.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ElegirproblemaController ventanaini = Cargador.getController();
        ventanaini.initStage(primaryStage);
        Scene scene = new Scene(raiz);
        primaryStage.setScene(scene);
        primaryStage.show();          
    }
}    

