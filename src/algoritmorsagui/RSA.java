/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmorsagui;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Gustafox
 */
public class RSA {
    
    private BigInteger n;
    private BigInteger fi;
    private BigInteger e;
    private BigInteger d;
    
    private BigInteger p;
    private BigInteger q;
    
    private final int tamaño = 10;
    
    
    public RSA(){

        
        this.p = new BigInteger(tamaño, 10, new Random());
        
        this.q = new BigInteger(tamaño, 10, new Random());
        while (this.p.compareTo(this.q) == 0){
            this.q = new BigInteger(tamaño, 10, new Random());
        }
        
        this.n = this.p.multiply(this.q);
        
        this.fi = this.p.subtract(new BigInteger("1")).multiply(this.q.subtract(new BigInteger("1")));
        
        do{
            this.e = new BigInteger(2*tamaño,new Random());
        }
        while (this.e.compareTo(this.fi) != -1 || this.e.gcd(this.fi).compareTo(new BigInteger("1")) != 0);
            this.d = this.e.modInverse(this.fi);
        

        
    }
    public BigInteger obtenerP(){
        return this.p;
    }
    
    public BigInteger obtenerQ(){
        return this.q;
    }
    
    public BigInteger obtenerN(){
        return this.n;
    }
    
    public BigInteger obtenerE(){
        return this.e;
    }
    public BigInteger obtenerD(){
        return this.d;
    }

    
    public BigInteger[] encriptar(String mensaje, Clave publica){
        
        BigInteger e = publica.getComponente();
        BigInteger n = publica.getN();
        
        byte temp[] = new byte[1];
        byte mensajeByte[] = mensaje.getBytes();
        BigInteger digitos[] = new BigInteger[mensajeByte.length];
        
        for (int i = 0; i < mensajeByte.length; i++) {
            temp[0] = mensajeByte[i];
            digitos[i] = new BigInteger(temp);
        }
        
        BigInteger encriptado[] = new BigInteger[digitos.length];
        for (int i = 0; i < encriptado.length; i++) {
            encriptado[i] = digitos[i].modPow(e,n);
        }
        return encriptado;
    }
    
    public String desencriptar(BigInteger mensajeEncriptado[], Clave privada){
        
        BigInteger d = privada.getComponente();
        BigInteger n = privada.getN();
        
        BigInteger digitos[] = new BigInteger[mensajeEncriptado.length];
        for (int i = 0; i < digitos.length; i++) {
            digitos[i] = mensajeEncriptado[i].modPow(d,n);
        }
        
        char arregloChar[] = new char[digitos.length];
        
        for (int i = 0; i < arregloChar.length; i++) {
            arregloChar[i] = (char)(digitos[i].intValue());
        }
        return new String(arregloChar);
    }
    
}
