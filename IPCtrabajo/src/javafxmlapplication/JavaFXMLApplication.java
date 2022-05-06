/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafxmlapplication.view.FXMLMainController;


public class JavaFXMLApplication extends Application {
    
    @Override
    
        public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Main.fxml"));
			Parent root = loader.load(); 
			Scene scene = new Scene(root);
			primaryStage.setTitle("Navs");
			primaryStage.setScene(scene);
			// acceso al controlador
			FXMLMainController controladorPrincipal = loader.getController();
			controladorPrincipal.initStage(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

   
    public static void main(String[] args) {
        launch(args);
        
    }


    
}
