/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import DBAccess.NavegacionDAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Navegacion;
import model.Session;

/**
 * FXML Controller class
 *
 * @author AORTGAR1
 */
public class ListasesionesController implements Initializable {

    @FXML
    private ListView<?> listsesiones;
    private List<Session> sesiones;
    private Stage primaryStage;
    private Scene escenaPrincipal;
    @FXML
    private DatePicker datesince;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void initStage(Stage stage)
    {
        datesince.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) > 0 );
                }
            };
        });
        primaryStage = stage;
	escenaPrincipal = stage.getScene();            
        Navegacion minav = null;
        try {
            minav = Navegacion.getSingletonNavegacion();
        } catch (NavegacionDAOException e) {
            e.printStackTrace();
        }
        sesiones = FXMLMainController.activeUser.getSessions();
        listsesiones = (ListView<?>) sesiones.stream().map(Session::toString).collect(Collectors.toList());               
    }  


    @FXML
    private void ListSalirClick(ActionEvent event) {
        System.out.println("Cerrando lista de problemas");
        primaryStage.setTitle("Navs");
        primaryStage.setScene(escenaPrincipal);
    }  

    @FXML
    private void cerrarListSesiones(ActionEvent event) {
        try {
            primaryStage.setTitle("Elegir Tipo de Seleccion de Problema");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("elegirproblema.fxml"));
            Parent root = miCargador.load();
         // acceso al controlador de ventana 1
            ElegirproblemaController ventanaini = miCargador.getController();
            ventanaini.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            } catch (IOException e) {e.printStackTrace();}
    }
}
