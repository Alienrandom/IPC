/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Answer;
import model.Problem;

/**
 * FXML Controller class
 *
 * @author AORTGAR1
 */
public class ProblemquizController implements Initializable {

    @FXML
    private Text problem;
    @FXML
    private RadioButton a;
    @FXML
    private RadioButton b;
    @FXML
    private RadioButton c;
    @FXML
    private RadioButton d;
    private Problem problema;
    private Stage primaryStage;
    private Scene escenaPrincipal;
    private int random;
    private ToggleGroup tg;
    private Toggle respuesta;
    @FXML
    private ToggleGroup ans;
    private boolean[] validez;
    private String[] respuestas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void initStage(Stage stage)
    {
        primaryStage = stage;
	escenaPrincipal = stage.getScene();
        problem.setText(problema.getText());
        random = (int) System.currentTimeMillis()%24;
        List<Answer> datosans = problema.getAnswers();
        respuestas = new String[] {datosans.get(0).getText(),datosans.get(1).getText(),datosans.get(2).getText(),datosans.get(3).getText()};
        validez = new boolean[] {datosans.get(0).getValidity(),datosans.get(1).getValidity(),datosans.get(2).getValidity(),datosans.get(3).getValidity()};
        switch(random){
            case 0: a.setText(respuestas[0]); b.setText(respuestas[1]); c.setText(respuestas[2]); d.setText(respuestas[3]);
            case 1: a.setText(respuestas[0]); b.setText(respuestas[1]); c.setText(respuestas[3]); d.setText(respuestas[2]);
            case 2: a.setText(respuestas[0]); b.setText(respuestas[2]); c.setText(respuestas[1]); d.setText(respuestas[3]);
            case 3: a.setText(respuestas[0]); b.setText(respuestas[2]); c.setText(respuestas[3]); d.setText(respuestas[1]);
            case 4: a.setText(respuestas[0]); b.setText(respuestas[3]); c.setText(respuestas[1]); d.setText(respuestas[2]);
            case 5: a.setText(respuestas[0]); b.setText(respuestas[4]); c.setText(respuestas[2]); d.setText(respuestas[1]);
            case 6: a.setText(respuestas[1]); b.setText(respuestas[0]); c.setText(respuestas[2]); d.setText(respuestas[3]);
            case 7: a.setText(respuestas[1]); b.setText(respuestas[0]); c.setText(respuestas[3]); d.setText(respuestas[2]);
            case 8: a.setText(respuestas[1]); b.setText(respuestas[2]); c.setText(respuestas[0]); d.setText(respuestas[3]);
            case 9: a.setText(respuestas[1]); b.setText(respuestas[2]); c.setText(respuestas[3]); d.setText(respuestas[0]);
            case 10: a.setText(respuestas[1]); b.setText(respuestas[3]); c.setText(respuestas[0]); d.setText(respuestas[2]);
            case 11: a.setText(respuestas[1]); b.setText(respuestas[3]); c.setText(respuestas[2]); d.setText(respuestas[0]);
            case 12: a.setText(respuestas[2]); b.setText(respuestas[0]); c.setText(respuestas[1]); d.setText(respuestas[3]);
            case 13: a.setText(respuestas[2]); b.setText(respuestas[0]); c.setText(respuestas[3]); d.setText(respuestas[1]);
            case 14: a.setText(respuestas[2]); b.setText(respuestas[1]); c.setText(respuestas[0]); d.setText(respuestas[3]);
            case 15: a.setText(respuestas[2]); b.setText(respuestas[1]); c.setText(respuestas[4]); d.setText(respuestas[0]);
            case 16: a.setText(respuestas[2]); b.setText(respuestas[3]); c.setText(respuestas[0]); d.setText(respuestas[2]);
            case 17: a.setText(respuestas[2]); b.setText(respuestas[3]); c.setText(respuestas[1]); d.setText(respuestas[0]);
            case 18: a.setText(respuestas[3]); b.setText(respuestas[0]); c.setText(respuestas[1]); d.setText(respuestas[2]);
            case 19: a.setText(respuestas[3]); b.setText(respuestas[0]); c.setText(respuestas[2]); d.setText(respuestas[1]);
            case 20: a.setText(respuestas[3]); b.setText(respuestas[1]); c.setText(respuestas[0]); d.setText(respuestas[2]);
            case 21: a.setText(respuestas[3]); b.setText(respuestas[1]); c.setText(respuestas[2]); d.setText(respuestas[0]);
            case 22: a.setText(respuestas[3]); b.setText(respuestas[2]); c.setText(respuestas[0]); d.setText(respuestas[1]);
            case 23: a.setText(respuestas[3]); b.setText(respuestas[2]); c.setText(respuestas[1]); d.setText(respuestas[0]);
        }
    }
    
    public void setProblema(Problem x){this.problema = x;}
    
    @FXML
    private void QuizSalirClick(ActionEvent event) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmar salida");
        alert.setHeaderText("Cuidado");
        alert.setContentText("¿Seguro que quieres salir del problema? Esto hara que cuente como un error, independientemente de la respuesta seleccionada.");        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            FXMLMainController.fallos++;
            System.out.println("OK, cerrando problema");
            primaryStage.setTitle("Navs");
            primaryStage.setScene(escenaPrincipal);
        }
    }

    private void registerQuiz(ActionEvent event) {
        try {
            if(this.getSelectedValidity()){
                FXMLMainController.aciertos++;
            }
            else {
                FXMLMainController.fallos++;
            }
            primaryStage.setTitle("Elegir Tipo de Seleccion de Problema");
            FXMLLoader cargador  = new FXMLLoader(getClass().getResource("elegirproblema.fxml"));
            Parent raiz = cargador.load();
         // acceso al controlador de ventana 1
            ListaproblemController ventanaini = cargador.getController();
            ventanaini.initStage(primaryStage);
            Scene scene = new Scene(raiz);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {e.printStackTrace();}
    }
    private boolean getSelectedValidity(){
        int i;
        respuesta = ans.getSelectedToggle();
        for(i = 0 ; i < validez.length ; i++){
            if(validez[i]){break;}
        }
        return java.util.Objects.equals(respuesta.isSelected(), validez[i]);
    }   
}
