/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import DBAccess.NavegacionDAOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Navegacion;
import model.User;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class RegistrarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage primaryStage;
    private Scene escenaPrincipal; 
    @FXML
    private TextField nickname;
    @FXML
    private Label errorNick;
    @FXML
    private TextField correo;
    @FXML
    private Label errorCorreo;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorPass;
    @FXML
    private DatePicker fecha;
    private Date dia;
    @FXML
    private Label errorFecha;
    @FXML
    private ChoiceBox<?> avatar;
    @FXML
    private Label errorAvatar;
    private Navegacion minav = null;
    @FXML
    private Button siguiente;
	
    public void initStage(Stage stage)
    {
        try {
            minav = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException e) {
            e.printStackTrace();
        }
        List boxList = new LinkedList<Image>();
        try {
            boxList.add(new Image(new FileInputStream(File.separator+"images"+File.separator+"avatar1.PNG")));
            boxList.add(new Image(new FileInputStream(File.separator+"images"+File.separator+"avatar2.PNG")));
            boxList.add(new Image(new FileInputStream(File.separator+"images"+File.separator+"avatar3.PNG")));
            boxList.add(new Image(new FileInputStream(File.separator+"images"+File.separator+"avatar4.PNG")));
            boxList.add(new Image(new FileInputStream(File.separator+"images"+File.separator+"default.PNG")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boxList.add("No");
        avatar = new ChoiceBox(FXCollections.observableList(boxList));
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(nickname.textProperty(), correo.textProperty(), password.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return (nickname.getText().isEmpty() || correo.getText().isEmpty() || password.getText().isEmpty() || fecha.toString().isEmpty());
            }       
        };
        errorNick.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            if(User.checkNickName(nickname.getText()) || !minav.exitsNickName(nickname.getText()))
                errorNick.setText("");
            else {
                errorNick.setText("El usuario no es valido o se encuentra ya registrado");
                siguiente.setDisable(true);
            }
        });
        errorCorreo.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            if(User.checkEmail(correo.getText())) 
                errorCorreo.setText("");
            else {
                errorCorreo.setText("El correo no es valido");
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
        errorFecha.textProperty().addListener((ObservableValue<? extends String> ov, String t, String t1) -> {
            if(fecha.getValue()!=null || fecha.getValue().minusYears(16).toEpochDay() <= 0)
                errorPass.setText("");
            else {
                errorPass.setText("Debes tener mas de 16 años");
                siguiente.setDisable(true);
            }
        });
        siguiente.disableProperty().bind(bb);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegSalirClick(ActionEvent event) {
        System.out.println("Cerrando registro");
        primaryStage.setTitle("Navs");
        primaryStage.setScene(escenaPrincipal);
    }

    @FXML
    private void registroSiguiente(ActionEvent event) {
        try {
            if(avatar.getValue()==null) minav.registerUser(nickname.getText(), correo.getText(), password.getText(), fecha.getValue());
            else minav.registerUser(nickname.getText(), correo.getText(), password.getText(), (Image) avatar.getSelectionModel().getSelectedItem(), fecha.getValue());
            primaryStage.setTitle("Iniciar sesion");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("iniciar.fxml"));
            Parent root = null;
            try {
                root = miCargador.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // acceso al controlador de ventana 1
            IniciarController ventana1 = miCargador.getController();
            ventana1.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (NavegacionDAOException e) {
            e.printStackTrace();
        }
    }
    
}
