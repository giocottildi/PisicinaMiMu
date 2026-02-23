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

public class Corso {
    
    private String idCorso;
    private DescrizioneCorso descrizione;
    private Map<String, Lezione> elencoLezioni;
    private Map<String, Istruttore> elencoIstruttori;
    
    // Costruttore

    public Corso(String idCorso, DescrizioneCorso descrizione) {
        this.idCorso = idCorso;
        this.descrizione = descrizione;
        this.elencoLezioni = new HashMap<>();
        this.elencoIstruttori = new HashMap<>();
    }

    public String getIdCorso() {
        return idCorso;
    }

    public DescrizioneCorso getDescrizione() {
        return descrizione;
    }

    public Map<String, Lezione> getElencoLezioni() {
        return elencoLezioni;
    }
    
    public Map<String, Istruttore> getElencoIstruttori(){
        return this.elencoIstruttori;
    }
    
    public void aggiungiLezione(String idLezione, String oraInizio, String oraFine) throws ProgrammazionePienaException, LezioneGiaPresenteException {
        
        Lezione l;
        
        if((this.elencoLezioni.size() >= this.descrizione.getDurata())){
            throw new ProgrammazionePienaException(descrizione.getNome());
        }
        
        if (elencoLezioni.containsKey(idLezione)) {
            throw new LezioneGiaPresenteException(idLezione);
        }
        l = new Lezione(idLezione, oraInizio, oraFine);
        this.elencoLezioni.put(idLezione, l);
    }
    
    public void AggiungiIstruttore(Istruttore is){
        this.elencoIstruttori.put(is.getIdIstruttore(), is);
    }
    public boolean isSenzaLezioni() {
        return elencoLezioni.isEmpty();
    }

    public void eliminaLezione(String idLezione) throws LezioneNonPresenteException {
        Lezione rimossa;

        rimossa = elencoLezioni.remove(idLezione);

        if (rimossa == null) {
            throw new LezioneNonPresenteException(idLezione);
        }
    }
    
    public Lezione cercaLezione(String idLezione) throws LezioneNonPresenteException {
        Lezione l = elencoLezioni.get(idLezione);
        if (l == null) {
            throw new LezioneNonPresenteException(idLezione);
        }
        return l;
    }
    public boolean isLezionePresente(String idLezione) {
        return elencoLezioni.containsKey(idLezione);
    }
    
    public double calcolaPercentualePienezza() {
        int numPosti = descrizione.getNumPosti();
        int occupati = descrizione.getNumPostiOccupati();

        return (numPosti > 0) ? (occupati * 100.0 / numPosti) : 0;
    }
    // Formatto direttamente come una stringa
    public String getPercentualePienezzaFormattata() {
        return String.format("%.2f", calcolaPercentualePienezza());
    }
    
    public Vasca.TipoVasca getTipoCorso() {
        return this.descrizione.getTipologiaClienti();
    }
    
    
    public void stampaDettagli() {

        System.out.println("=================================");
        System.out.println("CORSO");
        System.out.println("ID: " + idCorso);
        System.out.println(descrizione);

        // LEZIONI
        System.out.println("\nLezioni:");
        if (elencoLezioni == null || elencoLezioni.isEmpty()) {
            System.out.println("  Nessuna lezione presente");
        } else {
            for (Lezione l : elencoLezioni.values()) {
                if(l != null)
                    System.out.println("  - " + l.stampaBreve());
            }
        }

        // ISTRUTTORI
        System.out.println("\nIstruttori:");
        if (elencoIstruttori == null || elencoIstruttori.isEmpty()) {
            System.out.println("  Nessun istruttore assegnato");
        } else {
            for (Istruttore is : elencoIstruttori.values()) {
                System.out.println("  - " + is.toString());
            }
        }

        System.out.println("=================================\n");
    }
    
    
    @Override
    public String toString() {
        return "Corso [ID=" + idCorso +
               ", Nome=" + descrizione.getNome() + "]";
    }
    
    
}
