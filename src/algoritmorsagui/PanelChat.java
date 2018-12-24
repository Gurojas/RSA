/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmorsagui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Gustafox
 */
public class PanelChat {
    
    private final int padding = 10;
    
    private BorderPane panelPrincipal;
    private GridPane panelComponentes;
    
    public TextArea areaChat;
    
    public TextField fieldTexto;
    public TextField fieldTextoEncriptado;
    
    public Button buttonEnviar;
    public Button buttonEncriptar;
    public Button buttonBorrar;
    
    private Text textClavePublica;
    private Text textClavePrivada;
    
    private Clave publica;
    private Clave privada;
    
    public PanelChat(Clave publica, Clave privada, String tag){
        
        this.publica = publica;
        this.privada = privada;
        
        
        this.panelPrincipal = new BorderPane();
        this.panelPrincipal.setPadding(new Insets(this.padding));
        
        this.panelComponentes = new GridPane();
        this.panelComponentes.setGridLinesVisible(false);
        this.panelComponentes.setAlignment(Pos.CENTER);
        this.panelComponentes.setVgap(10);
        this.panelComponentes.setHgap(10);
        
        this.panelPrincipal.setTop(this.panelComponentes);
        
        this.areaChat = new TextArea();
        this.areaChat.setEditable(false);
        this.areaChat.setPrefWidth(580);
        
        this.buttonEnviar = new Button("Enviar");
        this.buttonEnviar.setPrefWidth(100);
        this.buttonEnviar.setDisable(true);
        
        this.buttonEncriptar = new Button("Encriptar");
        this.buttonEncriptar.setPrefWidth(this.buttonEnviar.getPrefWidth());
        
        this.buttonBorrar = new Button("Borrar");
        this.buttonBorrar.setPrefWidth(this.buttonEnviar.getPrefWidth());
        
        
        this.fieldTexto = new TextField();
        this.fieldTexto.setPromptText("Escriba mensaje...");
        this.fieldTexto.setPrefWidth(this.areaChat.getPrefWidth() - this.buttonEnviar.getPrefWidth());
        
        this.fieldTextoEncriptado = new TextField();
        this.fieldTextoEncriptado.setEditable(false);
        this.fieldTextoEncriptado.setDisable(true);
        this.fieldTextoEncriptado.setPrefWidth(this.areaChat.getPrefWidth() - this.buttonEnviar.getPrefWidth());
        
        
        
        
        this.panelComponentes.add(this.areaChat, 0, 0);
        GridPane.setColumnSpan(this.areaChat,3);
        
        this.panelComponentes.add(this.fieldTexto, 0, 1);
        this.panelComponentes.add(this.buttonBorrar,1,1);
        this.panelComponentes.add(this.buttonEncriptar, 2, 1);
        this.panelComponentes.add(this.fieldTextoEncriptado, 0, 2);
        this.panelComponentes.add(this.buttonEnviar, 1, 2);
        
        this.textClavePublica = new Text();
        this.textClavePrivada = new Text();
        
        String textPublica;
        String textPrivada;
        if (tag.equals("cliente")){
            textPublica = "Clave publica servidor (e,n): ("+this.publica.getComponente()+","+this.publica.getN()+")";
            textPrivada = "Clave privada "+tag+" (d,n): ("+this.privada.getComponente()+","+this.privada.getN()+")";
            
            this.textClavePublica.setText(textPublica);
            this.textClavePrivada.setText(textPrivada);
            
        }
        else{
            textPublica = "Clave publica cliente (e,n): ("+this.publica.getComponente()+","+this.publica.getN()+")";
            textPrivada = "Clave privada "+tag+" (d,n): ("+this.privada.getComponente()+","+this.privada.getN()+")";
            
            this.textClavePublica.setText(textPublica);
            this.textClavePrivada.setText(textPrivada);
        }
        
        HBox panelClaves = new HBox(this.padding);
        panelClaves.getChildren().addAll(this.textClavePublica,this.textClavePrivada);
        
        this.panelPrincipal.setBottom(panelClaves);
        
        
        
    }

    public Clave getClavePublica() {
        return publica;
    }

    public Clave getClavePrivada() {
        return privada;
    }
    
    public BorderPane getPanel(){
        return this.panelPrincipal;
    }
    
}
