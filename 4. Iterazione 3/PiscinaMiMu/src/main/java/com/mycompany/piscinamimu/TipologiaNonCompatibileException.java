/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class TipologiaNonCompatibileException extends Exception{
    public TipologiaNonCompatibileException(Vasca.TipoVasca tipoCorso, Vasca.TipoVasca tipoVasca) {
        super("Corso tipo " + tipoCorso + " incompatibile con vasca tipo " + tipoVasca);
    }
}
