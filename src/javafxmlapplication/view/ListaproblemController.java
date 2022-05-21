/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.*;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class ListaproblemController implements Initializable {
    /**
     * Initializes the controller class.
     */
    private Stage primaryStage;
    private Scene escenaPrincipal;
    @FXML
    private ListView<?> listproblem;
    private List<Problem> problemas;
    private Problem problem;
    
    public void initStage(Stage stage)
    {
        primaryStage = stage;
	escenaPrincipal = stage.getScene();            
        Navegacion minav = null;
        try {
            minav = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException ex) {
            ex.printStackTrace();
        }
        problemas = minav.getProblems();
        listproblem = (ListView<?>) problemas.stream().map(Problem::getText).collect(Collectors.toList());               
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    @FXML
    private void initProblemQuiz(ActionEvent event) {
        try {
		primaryStage.setTitle("Quiz Problema");
		FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("problemquiz.fxml"));
		Parent root = miCargador.load();
         // acceso al controlador de ventana 1
		ProblemquizController ventanaini = miCargador.getController();
                ventanaini.setProblema(problem);
                ventanaini.initStage(primaryStage);
		Scene scene = new Scene(root);
                primaryStage.setScene(scene);
		primaryStage.show();
            } catch (IOException e) {e.printStackTrace();}
    }
    @FXML
    private void ListSalirClick(ActionEvent event) {
        System.out.println("Cerrando lista de problemas");
        primaryStage.setTitle("Navs");
        primaryStage.setScene(escenaPrincipal);
    }
}