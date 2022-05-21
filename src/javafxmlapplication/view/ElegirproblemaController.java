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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Navegacion;
import model.Problem;
import model.Session;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class ElegirproblemaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage primaryStage;
    private Scene escenaPrincipal;
    private List<Problem> problemas;
    private Problem problem;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void initStage(Stage stage) {
	primaryStage = stage;
	escenaPrincipal = stage.getScene();
    }
    @FXML
    private void initEdit(ActionEvent event) {
            primaryStage.setTitle("Editar Usuario");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("editaruser.fxml"));
            Parent root = null;
            try {
                root = miCargador.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
         // acceso al controlador de ventana 1
            EditaruserController ventana1 = miCargador.getController();
            ventana1.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    @FXML
    private void initResultList(ActionEvent event) {
        try {
            primaryStage.setTitle("Lista de sesiones");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("listasesiones.fxml"));
            Parent root = miCargador.load();
         // acceso al controlador de ventana 1
            ListasesionesController ventanaini = miCargador.getController();
            ventanaini.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        try {
            Session sesion = new Session(LocalDateTime.now(), FXMLMainController.aciertos, FXMLMainController.fallos);
            FXMLMainController.activeUser.addSession(sesion);
        } catch (NavegacionDAOException e) {
            e.printStackTrace();
        }
        try {
            FXMLMainController.activeUser = null;
            System.out.println("Cerrando sesion");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = miCargador.load();
            primaryStage.setTitle("Navs");
            primaryStage.setScene(escenaPrincipal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initProblemList(ActionEvent event) {
            try {
		primaryStage.setTitle("Lista Problemas");
		FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("listaproblem.fxml"));
		Parent root = miCargador.load();
         // acceso al controlador de ventana 1
		ListaproblemController ventanaini = miCargador.getController();
                ventanaini.initStage(primaryStage);
		Scene scene = new Scene(root);
                primaryStage.setScene(scene);
		primaryStage.show();
            } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    private void initRandomProblem(ActionEvent event) {
        try {
		primaryStage.setTitle("Quiz Problema");
		FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("problemquiz.fxml"));
		Parent root = miCargador.load();
         // acceso al controlador de ventana 1
		ProblemquizController ventanaini = miCargador.getController();
                Navegacion minav = null;
                try {
                    minav = Navegacion.getSingletonNavegacion();
                } catch (NavegacionDAOException ex) {
                    ex.printStackTrace();
                }
                problemas = minav.getProblems();
                problem = problemas.get((int)(System.currentTimeMillis()%problemas.size()));
                ventanaini.setProblema(problem);
                ventanaini.initStage(primaryStage);
		Scene scene = new Scene(root);
                primaryStage.setScene(scene);
		primaryStage.show();
        } catch (IOException e) {e.printStackTrace();}
    }
}
