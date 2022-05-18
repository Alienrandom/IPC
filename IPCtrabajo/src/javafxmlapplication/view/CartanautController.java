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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SROCSEG
 */
public class CartanautController implements Initializable {
    private Stage primaryStage;
    private Scene escenaPrincipal; 
    boolean linea = false;
    boolean punto = false;
    boolean arco = false;
    boolean text = false;
    boolean transportador = false;
    double inicioXtrans;
    double inicioYtrans;
    double baseX;
    double baseY;
    private Group zoomGroup;
    Line linePainting;
    Circle punto1;

    @FXML
    private MenuButton map_pin;
    @FXML
    private MenuItem pin_info;
   
    @FXML
    private Slider ZoomSlider;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private ImageView cartaimgv;
    @FXML
    private Pane panecarta;
    @FXML
    private Button buttonpunto;
    @FXML
    private Button buttonlinea;
    @FXML
    private Slider grosorslider;
    @FXML
    private ColorPicker colorpick;
    @FXML
    private Button buttonarco;
    @FXML
    private Button buttontexto;
    @FXML
    private Button buttontransportador;
    @FXML
    private Button buttonextremos;
    @FXML
    private Button buttonlimpiar;
    @FXML
    private Button transportadormapa;
    
    
	
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
        punto=true;
        buttonpunto.setDisable(true);
        linea=false;
        buttonlinea.setDisable(false);
        arco=false;
        buttonarco.setDisable(false);
        text=false;
        buttontexto.setDisable(false);
        
    }




    @FXML
    private void lineaClick(ActionEvent event) {
        linea=true;
        buttonlinea.setDisable(true);
        punto=false;
        buttonpunto.setDisable(false);
        arco=false;
        buttonarco.setDisable(false);
        text=false;
        buttontexto.setDisable(false);
    }


    

    private void paneclick(MouseEvent event) {
        

        
    }


    @FXML
    private void arcoclick(ActionEvent event) {
        linea=false;
        buttonlinea.setDisable(false);
        punto=false;
        buttonpunto.setDisable(false);
        arco=true;
        buttonarco.setDisable(true);
        text=false;
        buttontexto.setDisable(false);
    }

    @FXML
    private void textoclick(ActionEvent event) {
        linea=false;
        buttonlinea.setDisable(false);
        punto=false;
        buttonpunto.setDisable(false);
        arco=false;
        buttonarco.setDisable(false);
        text=true;
        buttontexto.setDisable(true);
    }

    @FXML
    private void transportadorclick(ActionEvent event) {
        //transportadormapa.setTranslateX(panecarta);
        //transportadormapa.setTranslateY(panecarta.getWidth());
        if(transportador){
            buttontransportador.setTextFill(Color.web("#0069ff"));
            transportador=false;
            transportadormapa.setVisible(false);
        }
        else{
            buttontransportador.setTextFill(Color.GRAY);
            transportador=true;
            transportadormapa.setVisible(true);
        }
        
    }

    @FXML
    private void extremosclick(ActionEvent event) {
    }

    @FXML
    private void limpiarclick(ActionEvent event) {
        zoomGroup.getChildren().removeAll();
    }

    @FXML
    private void draggedpane(MouseEvent event) {
        if(linea){
            linePainting.setEndX(event.getX());
            linePainting.setEndY(event.getY());
            event.consume();
        }
    }

    @FXML
    private void clickpane(MouseEvent event) {
        if(punto){
            double x = zoomGroup.getLayoutX();
            double y = zoomGroup.getLayoutY();
            punto1 = new Circle();
            punto1.setCenterX(event.getX());
            punto1.setCenterY(event.getY());
            punto1.setRadius(grosorslider.getValue());
            punto1.setFill(colorpick.getValue());
            zoomGroup.getChildren().add(punto1);
        }
    }

    @FXML
    private void pressedpane(MouseEvent event) {
        if(linea){
            linePainting = new Line(event.getX(), event.getY(),event.getX(),event.getY());
            linePainting.setStrokeWidth(grosorslider.getValue());
            linePainting.setStroke(colorpick.getValue());
            zoomGroup.getChildren().add(linePainting);

        }
    }

    @FXML
    private void transdragged(MouseEvent event) {
        double despX = event.getSceneX() - inicioXtrans;
        double despY = event.getSceneY() - inicioYtrans;
        transportadormapa.setTranslateX(baseX + despX*(1/ZoomSlider.getValue()));
        transportadormapa.setTranslateY(baseY + despY*(1/ZoomSlider.getValue()));
    }

    @FXML
    private void transpressed(MouseEvent event) {
        inicioXtrans = event.getSceneX();
        inicioYtrans = event.getSceneY();
        baseX = transportadormapa.getTranslateX();
        baseY = transportadormapa.getTranslateY();
        event.consume();
    }

   
}
