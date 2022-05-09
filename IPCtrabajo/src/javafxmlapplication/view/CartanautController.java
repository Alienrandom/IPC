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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class CartanautController implements Initializable {
    double inicioXtrans;
    double inicioYtrans;
    double baseX;
    double baseY;
    private Group zoomGroup;

    @FXML
    private MenuButton map_pin;
    @FXML
    private MenuItem pin_info;
    private Stage primaryStage;
	private Scene escenaPrincipal; 
    @FXML
    private Slider ZoomSlider;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private Circle punto1;
	
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
        ZoomSlider.setMin(0.1);
        ZoomSlider.setMax(1.0);
        ZoomSlider.setValue(1.0);
        ZoomSlider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
        
        //=========================================================================
        //Envuelva el contenido de scrollpane en un grupo para que 
        //ScrollPane vuelva a calcular las barras de desplazamiento tras el escalado
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);
        // TODO
    }    

    @FXML
    private void CartSalirClick(ActionEvent event) {
        System.out.println("Cerrando carta náutica");
        primaryStage.setTitle("Navs");
        primaryStage.setScene(escenaPrincipal);
    }

    @FXML
    private void ZoomIn(ActionEvent event) {
         double sliderVal = ZoomSlider.getValue();
        ZoomSlider.setValue(sliderVal += 0.1);
    }

    @FXML
    private void ZoomOut(ActionEvent event) {
        double sliderVal = ZoomSlider.getValue();
        ZoomSlider.setValue(sliderVal + -0.1);
    }
    private void zoom(double scaleValue) {
        //===================================================
        //guardamos los valores del scroll antes del escalado
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        //===================================================
        // escalamos el zoomGroup en X e Y con el valor de entrada
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        //===================================================
        // recuperamos el valor del scroll antes del escalado
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }

    @FXML
    private void puntoClick(ActionEvent event) {
        punto1.setTranslateX(200/ZoomSlider.getValue());
        punto1.setTranslateY(100/ZoomSlider.getValue());
        if(punto1.isVisible()){
            punto1.visibleProperty().set(false);}
        else{
            punto1.visibleProperty().set(true);}
    }

    @FXML
    private void Punto1Released(MouseEvent event) {
        
    }

    @FXML
    private void Punto1Dragged(MouseEvent event) {
        double despX = event.getSceneX() - inicioXtrans;
        double despY = event.getSceneY() - inicioYtrans;
        punto1.setTranslateX(baseX + despX*(1/ZoomSlider.getValue()));
        punto1.setTranslateY(baseY + despY*(1/ZoomSlider.getValue()));
        event.consume();
        
    }

    @FXML
    private void Punto1Pressed(MouseEvent event) {
        inicioXtrans = event.getSceneX();
        inicioYtrans = event.getSceneY();
        baseX = punto1.getTranslateX();
        baseY = punto1.getTranslateY();
        event.consume();
    }

    @FXML
    private void PuntoButtonDragged(MouseEvent event) {
    }

    @FXML
    private void LineaButtonDragged(MouseEvent event) {
    }

    @FXML
    private void lineaClick(ActionEvent event) {
    }
    
}
