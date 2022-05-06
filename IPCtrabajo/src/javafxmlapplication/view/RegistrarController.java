/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
	
	public void initStage(Stage stage)
	{
		primaryStage = stage;
		escenaPrincipal = stage.getScene();
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegSalirClick(ActionEvent event) {
        System.out.println("Cerrando registro");
        primaryStage.setTitle("Iniciar sesión");
        primaryStage.setScene(escenaPrincipal);
    }
    
}
