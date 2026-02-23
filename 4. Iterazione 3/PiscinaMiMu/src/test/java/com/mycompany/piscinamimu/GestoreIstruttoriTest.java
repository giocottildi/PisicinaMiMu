/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author salvatore
 */
public class GestoreIstruttoriTest {
    
    private GestoreIstruttori g;
    
    @BeforeEach
    void setUp(){
        g = new GestoreIstruttori();
    }

    @Test
    public void AssumiIstruttore_ok() throws Exception{
        String nome = "Piero";
        String cognome = "Angela";
        String id_corso = "IS1";

        g.AssumiIstruttore(nome, cognome, id_corso); 
    
        Istruttore is = new Istruttore("Piero", "Angela", "IS1");
        assertEquals(is.getIdIstruttore(), g.getIstruttore(is.getIdIstruttore()).getIdIstruttore());
    }
    
    @Test
    public void AssumiIstruttore_not_ok() throws Exception{
        String id_istruttore = "IS1";
        String nome = "Franco";
        String cognome = "Colapinto";
        g.AssumiIstruttore(nome, cognome, id_istruttore);
        
        String id_istruttore2 = "IS1";
        String nome2 = "Gianni";
        String cognome2 = "Morandi";

        assertThrows(IstruttoreGiaAssuntoException.class, () -> g.AssumiIstruttore(nome2, cognome2, id_istruttore2));       
    }
    
    @Test
    public void stampaTutto_nonLanciaEccezioni() throws Exception {
        
        String id_istruttore = "IS1";
        String nome = "Piero";
        String cognome = "Angela";
        g.AssumiIstruttore(nome, cognome, id_istruttore);
        assertDoesNotThrow(() -> g.mostraTuttiIstruttori());
        
    }
    @Test
    public void getIstruttore_not_ok() {
        assertThrows(IstruttoreNonDisponibileException.class, () -> g.getIstruttore("NON_ESISTE"));
    }
    @Test
    public void visualizzaIstruttoriDisponibili_ok() throws Exception {
        g.AssumiIstruttore("Piero", "Angela", "IS1");
        g.AssumiIstruttore("Franco", "Colapinto","IS2");

        // Recupera i2 dal gestore
        Istruttore i2 = g.getIstruttore("IS2");

        // Rendi i2 non disponibile
        DescrizioneCorso cd = new DescrizioneCorso("Nuoto", Vasca.TipoVasca.DONNE, 10, 5, 0);
        i2.AssegnaCorso(new Corso("C1", cd));
        i2.AssegnaCorso(new Corso("C2", cd));
        i2.AssegnaCorso(new Corso("C3", cd));

        Map<String, Istruttore> disponibili = g.mostraIstruttoriDisponibili();

        assertTrue(disponibili.containsKey("IS1"));
        assertFalse(disponibili.containsKey("IS2"));
    }
    
    // ITERAZIONE 2
    
    @Test
    public void mostraIstruttoriPerDisponibilita_conDisponibili() throws Exception {
        g.AssumiIstruttore("Piero", "Angela", "IS1");
        g.AssumiIstruttore("Franco", "Colapinto", "IS2");

        // i1 disponibile, i2 reso non disponibile
        Istruttore i2 = g.getIstruttore("IS2");
        DescrizioneCorso cd = new DescrizioneCorso("Nuoto", Vasca.TipoVasca.DONNE, 10, 5, 0);
        i2.AssegnaCorso(new Corso("C1", cd));
        i2.AssegnaCorso(new Corso("C2", cd));
        i2.AssegnaCorso(new Corso("C3", cd));

        assertDoesNotThrow(() -> g.mostraIstruttoriPerDisponibilita(true));
    }

    @Test
    public void mostraIstruttoriPerDisponibilita_senzaDisponibili() throws Exception {
        g.AssumiIstruttore("Piero", "Angela", "IS1");

        Istruttore i1 = g.getIstruttore("IS1");
        DescrizioneCorso cd = new DescrizioneCorso("Nuoto", Vasca.TipoVasca.DONNE, 10, 5, 0);
        i1.AssegnaCorso(new Corso("C1", cd));
        i1.AssegnaCorso(new Corso("C2", cd));
        i1.AssegnaCorso(new Corso("C3", cd));

        assertDoesNotThrow(() -> g.mostraIstruttoriPerDisponibilita(true));
    }

    @Test
    public void mostraIstruttore_esistente() throws Exception {
        g.AssumiIstruttore("Piero", "Angela", "IS1");

        assertDoesNotThrow(() -> g.mostraIstruttore("IS1"));
    }

    @Test
    public void mostraIstruttore_nonEsistente() {
        assertDoesNotThrow(() -> g.mostraIstruttore("NON_ESISTE"));
    }
}
