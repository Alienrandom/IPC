/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static javafxmlapplication.Utils.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;


public class FXMLDocumentController implements Initializable {

    private Label labelMessage;
    //========================================================
    // objects defined into FXML file with fx:id 
    @FXML
    private GridPane tabla;
    @FXML
    private Circle puntero;

    int X_ini = 0;
    int Y_ini = 0;
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
  
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void pulsar (KeyEvent event){
        if (event.getCode() == KeyCode.LEFT) { 
            int column = tabla.getColumnIndex(puntero);
            tabla.setColumnIndex(puntero , columnNorm(tabla, column - 1));
        }
        if (event.getCode() == KeyCode.UP){
            int row = tabla.getRowIndex(puntero);
            tabla.setRowIndex(puntero , rowNorm(tabla, row - 1));
        }
        if (event.getCode() == KeyCode.DOWN){
            int row = tabla.getRowIndex(puntero);
            tabla.setRowIndex(puntero , rowNorm(tabla, row + 1));
        }
        if (event.getCode() == KeyCode.RIGHT) { 
            int column = tabla.getColumnIndex(puntero);
            tabla.setColumnIndex(puntero , columnNorm(tabla, column + 1));
        }
    }

    @FXML
    private void ratonpuls(MouseEvent event) {
        X_ini =(int)event.getSceneX();
        Y_ini =(int)event.getSceneY(); 
    }

    @FXML
    private void ratonarras(MouseEvent event) {
        puntero.setTranslateX(event.getSceneX() - X_ini);
        puntero.setTranslateY(event.getSceneY() - Y_ini);
    }

    @FXML
    private void rtonclick(MouseEvent event) {
        int column = columnCalc(tabla,(int)event.getSceneX());
        int row = rowCalc(tabla,(int)event.getSceneY());
        tabla.setColumnIndex(puntero , columnNorm(tabla, column));
        tabla.setRowIndex(puntero, rowNorm(tabla,row));
    }

    @FXML
    private void ratonsolt(MouseEvent event) {
        puntero.setTranslateX(0);
        puntero.setTranslateY(0);
        
        event.consume();
    }
}

                
        
            

                
                
     

    
        
    
                
                

