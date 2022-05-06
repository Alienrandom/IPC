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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class CartanautController implements Initializable {

    @FXML
    private MenuButton map_pin;
    @FXML
    private MenuItem pin_info;
    private Stage primaryStage;
	private Scene escenaPrincipal; 
	
	public void initStage(Stage stage)
	{
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
    private void CartSalirClick(ActionEvent event) {
        System.out.println("Cerrando carta náutica");
        primaryStage.setTitle("Navs");
        primaryStage.setScene(escenaPrincipal);
    }
    
}
