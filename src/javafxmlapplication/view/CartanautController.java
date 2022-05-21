/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.view;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    boolean extremos = false;
    double inicioXtrans;
    double inicioYtrans;
    double baseX;
    double baseY;
    double inicioXArc;
    private Group zoomGroup;
    int childrens = 0;
    Line linePainting;
    Line extremoX;
    Line extremoY;
    Circle punto1;
    Circle arcoPainting;
    TextField texto;

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
        try {
            primaryStage.setTitle("Navs");
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root = miCargador.load();
         // acceso al controlador de ventana 1
            FXMLMainController ventanaini = miCargador.getController();
            ventanaini.initStage(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {e.printStackTrace();}
        
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
        extremos=false;
        buttonextremos.setDisable(false);
        
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
        extremos=false;
        buttonextremos.setDisable(false);
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
        extremos=false;
        buttonextremos.setDisable(false);
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
        extremos=false;
        buttonextremos.setDisable(false);
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
            punto=false;
            buttonpunto.setDisable(false);
            linea=false;
            buttonlinea.setDisable(false);
            arco=false;
            buttonarco.setDisable(false);
            text=false;
            buttontexto.setDisable(false);
            extremos=false;
            buttonextremos.setDisable(false);
            buttontransportador.setTextFill(Color.GRAY);
            transportador=true;
            transportadormapa.setVisible(true);
        }
        
        
    }

    @FXML
    private void extremosclick(ActionEvent event) {
        punto=false;
        buttonpunto.setDisable(false);
        linea=false;
        buttonlinea.setDisable(false);
        arco=false;
        buttonarco.setDisable(false);
        text=false;
        buttontexto.setDisable(false);
        extremos=true;
        buttonextremos.setDisable(true);
    }

    @FXML
    private void limpiarclick(ActionEvent event) {
        try {
		 primaryStage.setTitle("Carta Náutica");
		 FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("cartanaut.fxml"));
		 Parent root = miCargador.load();
         // acceso al controlador de ventana 1
		 CartanautController ventanacart = miCargador.getController();
                 ventanacart.initStage(primaryStage);
		 Scene scene = new Scene(root);
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 } catch (IOException e) {e.printStackTrace();}
    }

    @FXML
    private void draggedpane(MouseEvent event) {
        if(linea){
            linePainting.setEndX(event.getX());
            linePainting.setEndY(event.getY());
            event.consume();
        }
        else if(arco){
            double radio = Math.abs(event.getX()- inicioXArc);
            arcoPainting.setRadius(radio);
            event.consume();
        }
    }

    @FXML
    private void clickpane(MouseEvent event) {
        //la función de eliminar los onjetos mediante un contextmenu no funciona de ninguna forma
        //ni colocandola en initialize ni en pressed ni en clicked
        
        //arcoPainting.setOnContextMenuRequested(e -> {
          // ContextMenu menuContext = new ContextMenu();
          //  MenuItem borrarItem = new MenuItem("eliminar");
           // menuContext.getItems().add(borrarItem);
          //  borrarItem.setOnAction(ev -> {
          //      zoomGroup.getChildren().remove((Node)e.getSource());
          //      ev.consume();
          //  });
         //   menuContext.show(arcoPainting,e.getSceneX(),e.getSceneY());
          //  e.consume();
       // });
        //texto.setOnContextMenuRequested(e -> {
          // ContextMenu menuContext = new ContextMenu();
          //  MenuItem borrarItem = new MenuItem("eliminar");
           // menuContext.getItems().add(borrarItem);
          //  borrarItem.setOnAction(ev -> {
          //      zoomGroup.getChildren().remove((Node)e.getSource());
          //      ev.consume();
          //  });
         //   menuContext.show(texto,e.getSceneX(),e.getSceneY());
          //  e.consume();
       // });
        //extremos.setOnContextMenuRequested(e -> {
          // ContextMenu menuContext = new ContextMenu();
          //  MenuItem borrarItem = new MenuItem("eliminar");
           // menuContext.getItems().add(borrarItem);
          //  borrarItem.setOnAction(ev -> {
          //      zoomGroup.getChildren().remove((Node)e.getSource());
          //      ev.consume();
          //  });
         //   menuContext.show(extremos,e.getSceneX(),e.getSceneY());
          //  e.consume();
       // });
        //linePainting.setOnContextMenuRequested(e -> {
          // ContextMenu menuContext = new ContextMenu();
          //  MenuItem borrarItem = new MenuItem("eliminar");
           // menuContext.getItems().add(borrarItem);
          //  borrarItem.setOnAction(ev -> {
          //      zoomGroup.getChildren().remove((Node)e.getSource());
          //      ev.consume();
          //  });
         //   menuContext.show(linePainting,e.getSceneX(),e.getSceneY());
          //  e.consume();
       // });
       // punto1.setOnContextMenuRequested(e -> {
          //  ContextMenu menuContext = new ContextMenu();
         //   MenuItem borrarItem = new MenuItem("eliminar");
          //  menuContext.getItems().add(borrarItem);
          //  borrarItem.setOnAction(ev -> {
           //     zoomGroup.getChildren().remove((Node)e.getSource());
           //     ev.consume();
          //  });
          //  menuContext.show(punto1,e.getSceneX(),e.getSceneY());
         //   e.consume();
       // });
        if(punto){
            double x = zoomGroup.getLayoutX();
            double y = zoomGroup.getLayoutY();
            punto1 = new Circle();
            punto1.setCenterX(event.getX());
            punto1.setCenterY(event.getY());
            punto1.setRadius(grosorslider.getValue());
            punto1.setFill(colorpick.getValue());
            zoomGroup.getChildren().add(punto1);
            childrens++;
        }
        else if(text){
            texto = new TextField();
            zoomGroup.getChildren().add(texto);
            texto.setLayoutX(event.getX());
            texto.setLayoutY(event.getY());
            texto.requestFocus();
            texto.setOnAction(e-> {
                Text textoT = new Text( texto.getText());
                textoT.setX(texto.getLayoutX());
                textoT.setY(texto.getLayoutY());
                textoT.setFont(Font.font("Gafata",FontWeight.NORMAL,FontPosture.REGULAR,(grosorslider.getValue()+40)/2));
                textoT.setFill(colorpick.getValue());
                zoomGroup.getChildren().add(textoT);
                childrens++;
                zoomGroup.getChildren().remove(texto);
                e.consume();
            });
        }
        else if(extremos){
            zoomGroup.setCursor(Cursor.CROSSHAIR);
            extremoX = new Line(0, event.getY(),8960,event.getY());
            extremoY= new Line(event.getX(),0,event.getX(),5760);
            extremoX.setStroke(colorpick.getValue());
            extremoX.setStrokeWidth(Math.sqrt(grosorslider.getValue()));
            extremoY.setStroke(colorpick.getValue());
            extremoY.setStrokeWidth(Math.sqrt(grosorslider.getValue()));
            zoomGroup.getChildren().add(extremoX);
            zoomGroup.getChildren().add(extremoY);
        }
    }

    @FXML
    private void pressedpane(MouseEvent event) {
        
        if(linea){
            
            linePainting = new Line(event.getX(), event.getY(),event.getX(),event.getY());
            linePainting.setStrokeWidth(grosorslider.getValue());
            linePainting.setStroke(colorpick.getValue());
            zoomGroup.getChildren().add(linePainting);
            childrens++;

        }
        else if(arco){
            arcoPainting = new Circle(1);
            arcoPainting.setStroke(colorpick.getValue());
            arcoPainting.setStrokeWidth(grosorslider.getValue());
            arcoPainting.setFill(Color.TRANSPARENT);
            zoomGroup.getChildren().add(arcoPainting);
            childrens++;
            arcoPainting.setCenterX(event.getX());
            arcoPainting.setCenterY(event.getY());
            inicioXArc = event.getX();
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
        transportadormapa.setOnContextMenuRequested(e -> {
            ContextMenu menuContext = new ContextMenu();
            MenuItem borrarItem = new MenuItem("eliminar");
            menuContext.getItems().add(borrarItem);
            borrarItem.setOnAction(ev -> {
               transportadormapa.setVisible(false);
                ev.consume();
            });
            menuContext.show(transportadormapa,e.getSceneX(),e.getSceneY());
            e.consume();
        });
        inicioXtrans = event.getSceneX();
        inicioYtrans = event.getSceneY();
        baseX = transportadormapa.getTranslateX();
        baseY = transportadormapa.getTranslateY();
        event.consume();
    }

   
}
