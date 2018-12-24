/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmorsagui;

import java.math.BigInteger;

/**
 *
 * @author Gustafox
 */
public class Clave {
    
    private BigInteger componente;
    private BigInteger n;
    
    public Clave(BigInteger componente, BigInteger n){
        this.componente = componente;
        this.n = n;
    }

    public BigInteger getComponente() {
        return componente;
    }

    public BigInteger getN() {
        return n;
    }
    
}

