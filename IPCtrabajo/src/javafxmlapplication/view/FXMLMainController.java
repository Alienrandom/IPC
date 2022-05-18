/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafxmlapplication.view.IniciarController;
import javafxmlapplication.view.CartanautController;


/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class FXMLMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage primaryStage;
	
    public void initStage(Stage stage){
        primaryStage = stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MainSalirClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("¿Salir?");
        alert.setHeaderText("Salir");
        alert.setContentText("¿Seguro que quieres salir?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){System.out.println("OK");
        Platform.exit();
        } else {
        System.out.println("CANCEL");
        }
    }

    @FXML
    private void MainEjerciciosClick(ActionEvent event) {
        try {
		 primaryStage.setTitle("Iniciar sesion");
		 FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("iniciar.fxml"));
		 Parent root = miCargador.load();
         // acceso al controlador de ventana 1
		 IniciarController ventana1 = miCargador.getController();
                ventana1.initStage(primaryStage);
		 Scene scene = new Scene(root);
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    private void MainCartaClick(ActionEvent event) {
        try {
		 primaryStage.setTitle("Carta Náutica");
		 FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("cartanaut.fxml"));
		 Parent root = miCargador.load();
         // acceso al controlador de ventana 1
		 CartanautController ventanacart = miCargador.getController();
                 ventanacart.initStage(primaryStage);
		 Scene scene = new Scene(root);
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 } catch (IOException e) {e.printStackTrace();}
    }
    
}
