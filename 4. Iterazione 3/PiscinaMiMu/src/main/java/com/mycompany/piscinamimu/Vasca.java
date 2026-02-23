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
public abstract class Vasca {
    
    private String id_vasca;
    private Map<String, Corsia> elenco_corsie;

    // Costruttore

    
    public Vasca(String id_vasca) {
        this.id_vasca = id_vasca;
        elenco_corsie = new HashMap<>();
    }
    
    public enum TipoVasca {
        DONNE,
        UOMINI,
        BAMBINI,
        MISTA,
        RIABILITAZIONE;
        
    public static TipoVasca fromMenuChoice(int scelta) {
        switch (scelta) {
            case 1: return DONNE;
            case 2: return UOMINI;
            case 3: return BAMBINI;
            case 4: return MISTA;
            case 5: return RIABILITAZIONE;
            default:
                throw new IllegalArgumentException("Scelta non valida: " + scelta);
        }
    }

        public static TipoVasca fromString(String tipo)
                throws TipologiaVascaNonEsistenteException {
            try {
                return TipoVasca.valueOf(tipo.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new TipologiaVascaNonEsistenteException(tipo);
            }
        }
    }

    public abstract TipoVasca getTipo();
    
    
    public String getIdVasca(){
        return this.id_vasca;
    }
    
    public Map<String, Corsia> getElencoCorsie(){
        return this.elenco_corsie;
    }
    
    public Corsia cercaCorsia(String id_corsia) throws CorsiaNonPresenteNellaVascaException{
        Corsia cr = this.elenco_corsie.get(id_corsia);
        
        if(cr == null) throw new CorsiaNonPresenteNellaVascaException(id_corsia);
        
        return cr;
    }
    
    public void AggiungiCorsia(Corsia cr) throws CorsiaGiaPresenteException{
        String id_corsia = ""+cr.getIdCorsia();
        if(this.elenco_corsie.get(id_corsia) != null) throw new CorsiaGiaPresenteException(cr.getIdCorsia());
        this.elenco_corsie.put(id_corsia, cr);
        cr.setVasca(this);
        System.out.println("Numero di corsie caricate " + this.elenco_corsie.size() + " nella vasca: " + getIdVasca());
    }
    
    public void rimuoviCorsia(Corsia cr) throws CorsiaNonPresenteNellaVascaException{
        String id_corsia = ""+cr.getIdCorsia();
        if(!this.elenco_corsie.containsKey(id_corsia)) throw new CorsiaNonPresenteNellaVascaException(id_corsia);
        
        this.elenco_corsie.remove(id_corsia);
        cr.RemoveVasca();
        cr = null;
    }
    
  
    public void stampaDettagli() {

        System.out.println("=================================");
        System.out.println(this);

        System.out.println("Corsie:");
        if (elenco_corsie == null || elenco_corsie.isEmpty()) {
            System.out.println("  Nessuna corsia presente");
        } else {
            for (Corsia c : elenco_corsie.values()) {
                System.out.println("  - Corsia " + c.getIdCorsia());
            }
        }

        System.out.println("=================================\n");
    }
        
    
    @Override
    public String toString() {
        return "Vasca [ID=" + id_vasca + ", Tipo=" + getTipo() + "]";
    }
    
    
        
}
