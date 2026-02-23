/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class DescrizioneCorso {
 
    private String nome;
    private Vasca.TipoVasca tipologiaClienti;
    private int numPosti;
    private int durata;
    private int numPostiOccupati;

    // Costruttore
    
    public DescrizioneCorso(String nome, Vasca.TipoVasca tipologiaClienti, int num_posti, int durata, int num_posti_occupati) {
        this.nome = nome;
        this.tipologiaClienti = tipologiaClienti;
        this.numPosti = num_posti;
        this.durata = durata;
        this.numPostiOccupati = num_posti_occupati;
    }

    public String getNome() {
        return nome;
    }

    public Vasca.TipoVasca getTipologiaClienti() {
        return tipologiaClienti;
    }

    public int getNumPosti() {
        return numPosti;
    }

    public int getDurata() {
        return durata;
    }

    public int getNumPostiOccupati() {
        return numPostiOccupati;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setTipologiaClienti(Vasca.TipoVasca tipologiaClienti) {
        this.tipologiaClienti = tipologiaClienti;
    }
    public void setNumPosti(int num_posti){
        this.numPosti = num_posti;
    }
    public void setDurata(int durata){
        this.durata = durata;
    }
    public void setNumPostiOccupati(int num_posti_occupati){
        this.numPostiOccupati = num_posti_occupati;
    }
    
    public void aumentaPostiOccupati() throws PostiPieniException{
        if(this.numPostiOccupati == this.numPosti) throw new PostiPieniException(this.nome);
        this.numPostiOccupati += 1;
    }
    
    @Override
    public String toString() {
    return "Nome: " + nome +
           ", Tipologia clienti: " + tipologiaClienti +
           ", Posti totali: " + numPosti +
           ", Durata: " + durata + " lezioni" +
           ", Posti occupati: " + numPostiOccupati;
    }
    
    public boolean isCompatibileCon(Cliente.TipologiaCliente tipoCliente) {
        if (this.tipologiaClienti == Vasca.TipoVasca.MISTA) return true;

        switch (this.tipologiaClienti) {
            case DONNE:
                return tipoCliente == Cliente.TipologiaCliente.DONNE;
            case UOMINI:
                return tipoCliente == Cliente.TipologiaCliente.UOMINI;
            case BAMBINI:
                return tipoCliente == Cliente.TipologiaCliente.BAMBINI;
            case RIABILITAZIONE:
                return tipoCliente == Cliente.TipologiaCliente.RIABILITAZIONE;
            default:
                return false;
        }
    }
}
