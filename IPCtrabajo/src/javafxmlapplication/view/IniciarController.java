/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class IniciarController implements Initializable {
    private Stage primaryStage;
    private Scene escenaPrincipal; 
    
    public void initStage(Stage stage){
	primaryStage = stage;
	escenaPrincipal = stage.getScene();
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
        System.out.println("Cerrando inicio de sesión");
        primaryStage.setTitle("Navs");
        primaryStage.setScene(escenaPrincipal);
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
    }

    
    
}
