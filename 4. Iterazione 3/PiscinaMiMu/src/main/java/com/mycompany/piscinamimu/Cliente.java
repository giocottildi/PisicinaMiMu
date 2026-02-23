/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.util.*;

/**
 *
 * @author pmilo
 */
public class Cliente {
    
    public enum TipologiaCliente {
    DONNE,
    UOMINI,
    BAMBINI,
    RIABILITAZIONE;
    
    public static TipologiaCliente fromString(String str) throws TipologiaClienteNonEsistenteException {
        try {
            return TipologiaCliente.valueOf(str.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TipologiaClienteNonEsistenteException(str);
        }
    }
}
    
    
    private String nome, cognome, idCliente;
    private Map<String, Corso> corsiIscritti;
    private TipologiaCliente tipologia;


    public Cliente(String nome, String cognome, String idCliente, TipologiaCliente tipologia) {
        this.nome = nome;
        this.cognome = cognome;
        this.idCliente = idCliente;
        this.corsiIscritti = new HashMap<>();
        this.tipologia = tipologia;
    }
    
    public int numCorsi(){
        return corsiIscritti.size();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public Map<String, Corso> getCorsiIscritti() {
        return corsiIscritti;
    }

    public TipologiaCliente getTipologia() {
        return tipologia;
    }
    
    public void AggiungiCorso(Corso c){
        this.corsiIscritti.put(c.getIdCorso(), c);
    }
    
    
    public void stampaDettagli() {

        System.out.println("=================================");
        System.out.println(this);

        System.out.println("Corsi iscritti:");
        if (corsiIscritti == null || corsiIscritti.isEmpty()) {
            System.out.println("  Nessun corso");
        } else {
            for (Corso c : corsiIscritti.values()) {
                System.out.println("  - " + c.getIdCorso() +
                                   " (" + c.getDescrizione().getNome() + ")");
            }
        }

        System.out.println("=================================\n");
    }

    @Override
    public String toString() {
        return "Cliente[ID=" + idCliente + 
               ", Nome=" + nome + " " + cognome + 
               ", tipologia=" + tipologia + 
               ", numeroCorsi=" + numCorsi() + "]";
    }
    
    
}
