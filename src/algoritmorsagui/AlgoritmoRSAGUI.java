/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmorsagui;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Gustafox
 */
public class AlgoritmoRSAGUI extends Application {
    
    private BigInteger encriptadoCliente[];
        
    private BigInteger encriptadoServidor[];
    
    @Override
    public void start(Stage primaryStage) {
        
        RSA rsaServidor = new RSA();
        
        BigInteger eS = rsaServidor.obtenerE();
        BigInteger nS = rsaServidor.obtenerN();
        BigInteger dS = rsaServidor.obtenerD();
        
        RSA rsaCliente = new RSA();
        
        BigInteger eC = rsaCliente.obtenerE();
        BigInteger nC = rsaCliente.obtenerN();
        BigInteger dC = rsaCliente.obtenerD();
        
        Clave clavePublicaServidor = new Clave(eS,nS);
        Clave clavePrivadaServidor = new Clave(dS,nS);
        
        Clave clavePublicaCliente = new Clave(eC,nC);
        Clave clavePrivadaCliente = new Clave(dC,nC);
        
        Stage stageServidor = new Stage();
        
        PanelChat panelChatCliente = new PanelChat(clavePublicaServidor,clavePrivadaCliente,"cliente");
        
        BorderPane rootCliente = panelChatCliente.getPanel();
        
        PanelChat panelChatServidor = new PanelChat(clavePublicaCliente,clavePrivadaServidor,"servidor");
        
        BorderPane rootServidor = panelChatServidor.getPanel();
        
        Scene sceneCliente = new Scene(rootCliente, 600, 300);
        
        Scene sceneServidor = new Scene(rootServidor, 600, 300);
        
        primaryStage.setX(400);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Chat Cliente");
        primaryStage.setScene(sceneCliente);
        primaryStage.show();
        
        stageServidor.setX(800);
        stageServidor.setResizable(false);
        stageServidor.setTitle("Chat Servidor");
        stageServidor.setScene(sceneServidor);
        stageServidor.show();
        
        
        
        panelChatCliente.buttonEncriptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                
                String textoSinEncriptar = panelChatCliente.fieldTexto.getText();
                
                Clave publicaServidor = panelChatCliente.getClavePublica();
                
                encriptadoCliente = rsaCliente.encriptar(textoSinEncriptar,publicaServidor);
                
                for (int i = 0; i < encriptadoCliente.length; i++) {
                    String texto = panelChatCliente.fieldTextoEncriptado.getText();
                    panelChatCliente.fieldTextoEncriptado.setText(texto+String.valueOf(encriptadoCliente[i].intValue())+" ");
                }
                
                panelChatCliente.buttonEnviar.setDisable(false);
                
            }
        });
        
        panelChatCliente.buttonEnviar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                panelChatCliente.fieldTextoEncriptado.setText("");
                
                Clave privadaServidor = panelChatServidor.getClavePrivada();
                
                String mensajeDescriptado = rsaServidor.desencriptar(encriptadoCliente, privadaServidor);
                                
                Date dNow = new Date( );
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss");
                panelChatCliente.areaChat.appendText("Yo ("+ft.format(dNow)+"): "+mensajeDescriptado+"\n");
                panelChatServidor.areaChat.appendText("Cliente ("+ft.format(dNow)+"): "+mensajeDescriptado+"\n");
                
                panelChatCliente.fieldTexto.setText("");
                panelChatCliente.buttonEnviar.setDisable(true);
            }
        });
        
        panelChatServidor.buttonEncriptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                
                
                String textoSinEncriptar = panelChatServidor.fieldTexto.getText();
                
                Clave clavePublicaCliente = panelChatServidor.getClavePublica();
                
                encriptadoServidor = rsaServidor.encriptar(textoSinEncriptar, clavePublicaCliente);
                
                for (int i = 0; i < encriptadoServidor.length; i++) {
                    String texto = panelChatServidor.fieldTextoEncriptado.getText();
                    panelChatServidor.fieldTextoEncriptado.setText(texto+String.valueOf(encriptadoServidor[i].intValue())+" ");
                }
                
                panelChatServidor.buttonEnviar.setDisable(false);
           }
            
        });
        
        
        panelChatServidor.buttonEnviar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                panelChatServidor.fieldTextoEncriptado.setText("");
                
                Clave privadaCliente = panelChatCliente.getClavePrivada();
                
                String mensajeDescriptado = rsaServidor.desencriptar(encriptadoServidor, privadaCliente);
                                
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss");
                panelChatCliente.areaChat.appendText("Servidor ("+ft.format(dNow)+"): "+mensajeDescriptado+"\n");
                panelChatServidor.areaChat.appendText("Yo ("+ft.format(dNow)+"): "+mensajeDescriptado+"\n");
                
                panelChatServidor.fieldTexto.setText("");
                panelChatServidor.buttonEnviar.setDisable(true);
                
            }
        });
        
        panelChatCliente.buttonBorrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelChatCliente.fieldTextoEncriptado.setText("");
                panelChatCliente.fieldTexto.setText("");
                
            }
        });
        
        panelChatServidor.buttonBorrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panelChatServidor.fieldTextoEncriptado.setText("");
                panelChatServidor.fieldTexto.setText("");
                
            }
        });
        
        
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stageServidor.close();
            }
        });
        
        
        stageServidor.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
            }
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
