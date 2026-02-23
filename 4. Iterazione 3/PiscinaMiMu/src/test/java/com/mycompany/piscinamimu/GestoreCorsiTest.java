/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;

/**
 *
 * @author salvatore
 */
public class GestoreCorsiTest {
    
    private GestoreCorsi gestore;
    private DescrizioneCorso descr;

    @BeforeEach
    void setUp() {
        gestore = new GestoreCorsi();
        descr = new DescrizioneCorso("NuotoSincronizzato", Vasca.TipoVasca.DONNE, 10, 5, 0);
    }

    @Test
    public void aggiungiCorso_ok() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        assertEquals("C1", gestore.cercaCorso("C1").getIdCorso());
    }

    @Test
    public void aggiungiCorso_giaPresente() throws Exception {
        gestore.aggiungiCorso("C1", descr);

        assertThrows(CorsoGiaPresenteException.class, () -> 
            gestore.aggiungiCorso("C1", descr)
        );
    }

    @Test
    public void cercaCorso_nonPresente() {
        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.cercaCorso("NON_ESISTE")
        );
    }

    @Test
    public void eliminaCorso_ok() throws Exception {
        gestore.aggiungiCorso("C3", descr);
        gestore.eliminaCorso("C3");

        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.cercaCorso("C3")
        );
    }

    @Test
    public void eliminaCorso_nonPresente() {
        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.eliminaCorso("nessuno")
        );
    }

    @Test
    public void aggiungiLezione_alCorsoEsistente() throws Exception {
        gestore.aggiungiCorso("C4", descr);
        Corso corso = gestore.cercaCorso("C4");
        corso.aggiungiLezione("L1", "10:00", "11:00");
        Lezione l = corso.cercaLezione("L1");

        assertTrue(corso.isLezionePresente("L1"));
        assertEquals("L1", l.getIdLezione());
        assertEquals("10:00", l.getOraInizio());
        assertEquals("11:00", l.getOraFine());
    }

    @Test
    public void aggiungiLezione_giaPresente() throws Exception {
        gestore.aggiungiCorso("C5", descr);
        Corso corso = gestore.cercaCorso("C5");
        Lezione l1 = new Lezione("L1", "08:00", "09:00");
        corso.aggiungiLezione("L1", "08:00", "09:00");

        assertThrows(LezioneGiaPresenteException.class, () -> corso.aggiungiLezione("L1", "08:00", "09:00"));
    }

    @Test
    public void aggiungiLezione_programmazionePiena() throws Exception {
        // Corso con durata = 2, provo ad aggiungere 3 lezioni
        DescrizioneCorso descPiccolo = new DescrizioneCorso("MiniCorso", Vasca.TipoVasca.UOMINI, 5, 2, 0);
        gestore.aggiungiCorso("C6", descPiccolo);
        Corso corso = gestore.cercaCorso("C6");

        corso.aggiungiLezione("L1", "08:00", "09:00");
        corso.aggiungiLezione("L2", "09:00", "10:00");

        assertThrows(ProgrammazionePienaException.class, () ->
            corso.aggiungiLezione("L3", "10:00", "11:00")
        );
    }


    @Test
    public void eliminaLezione_nonPresente() throws Exception {
        gestore.aggiungiCorso("C8", descr);
        Corso corso = gestore.cercaCorso("C8");

        assertThrows(LezioneNonPresenteException.class, () -> corso.eliminaLezione("L1"));
    }
    
    
    @Test
    public void ModificaNome_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        
        String input = "Acqua gym";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaNome("C1");
        Corso c = gestore.cercaCorso("C1");
        
        assertEquals("Acqua gym", c.getDescrizione().getNome());
        
    }
    
    @Test
    public void ModificaNome_not_ok() throws Exception{
        String input = "Acqua gym";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaNome("nessuno"));
    }
    

    
    @Test
    public void ModificaOraInizioLezione_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "10:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaOraInizioLezione("C1", "L1");
        
        assertEquals("10:00", c.cercaLezione("L1").getOraInizio());
        
    }
    
    @Test
    public void ModificaOraInizioLezione_corso_not_ok() throws Exception{
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaOraInizioLezione("C1", "L1"));
    }
    
    @Test 
    public void ModificaOraInizioLezione_lezione_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        assertThrows(LezioneNonPresenteException.class, () -> gestore.ModificaOraInizioLezione("C1", "nessuna"));
    }
    
    @Test
    public void ModificaOraInizioLezione_lezione_orari_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "12:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(LezioniConOrariNonValidiException.class, () -> gestore.ModificaOraInizioLezione("C1", "L1"));
    }
    
    @Test
    public void ModificaOraFineLezione_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "12:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaOraFineLezione("C1", "L1");
        
        assertEquals("12:00", c.cercaLezione("L1").getOraFine());
        
    }

    
    @Test
    public void ModificaOraFineLezione_lezione_orari_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "9:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(LezioniConOrariNonValidiException.class, () -> gestore.ModificaOraFineLezione("C1", "L1"));
    }
    @Test
    public void AssegnaCorso_ok() throws Exception {
        Istruttore istr = new Istruttore("I1", "Mario", "Rossi");
        gestore.aggiungiCorso("C10", descr);
        Corso c = gestore.cercaCorso("C10");

        istr.AssegnaCorso(c);

        assertTrue(istr.getCorsi().containsKey("C10"));
    }

    @Test
    public void AssegnaCorso_nonDisponibile() throws Exception {
        Istruttore istr = new Istruttore("I2", "Luca", "Russo");
        Corso c1 = new Corso("C1", descr);
        Corso c2 = new Corso("C2", descr);
        Corso c3 = new Corso("C3", descr);
        istr.AssegnaCorso(c1);
        istr.AssegnaCorso(c2);
        istr.AssegnaCorso(c3);

        Corso c4 = new Corso("C4", descr);

        assertThrows(IstruttoreNonDisponibileException.class, () -> 
        istr.AssegnaCorso(c4));
    }
    
    // ITERAZIONE 2
    
    
    @Test
    void testAggiungiCorsia_OK() throws Exception {
        
        // Sottoclasse concreta di Vasca per i test
        class VascaTest extends Vasca {
            private TipoVasca tipo;

            public VascaTest(String id, TipoVasca tipo) {
                super(id);        // chiama il costruttore della classe astratta
                this.tipo = tipo;  // assegna il tipo desiderato
            }

            @Override
            public TipoVasca getTipo() {
                return tipo;
            }
        }
        Vasca vascaDonne = new VascaTest("V01", Vasca.TipoVasca.DONNE);

        DescrizioneCorso descr = new DescrizioneCorso("Nuoto Base", Vasca.TipoVasca.DONNE, 4, 10, 2);
        gestore.aggiungiCorso("C1", descr);
        Corso corso = gestore.cercaCorso("C1");

        corso.aggiungiLezione("L1", "10:00", "11:00");
        Lezione lezione = corso.cercaLezione("L1");

        Corsia corsia = new Corsia("CR1");
        corsia.setVasca(vascaDonne);  // importante assegnare la vasca

        gestore.AggiungiCorsia("C1", "L1", corsia);

        assertEquals(lezione, corsia.getElencoLezioni().get("L1"));
        assertEquals(corsia, lezione.getCorsia());
    }

    @Test
    void testAggiungiCorsia_CorsoNonPresente() {
        Corsia corsia = new Corsia("CR1");
        assertThrows(CorsoNonPresenteException.class,
                () -> gestore.AggiungiCorsia("C99", "L1", corsia));
    }

    @Test
    void testAggiungiCorsia_LezioneNonPresente() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        Corsia corsia = new Corsia("CR1");

        assertThrows(LezioneNonPresenteException.class,
                () -> gestore.AggiungiCorsia("C1", "L99", corsia));
    }

    @Test
    void testMostraTuttiCorsi() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        assertDoesNotThrow(() -> gestore.mostraTuttiCorsi());
    }

    @Test
    void testMostraCorso() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        assertDoesNotThrow(() -> gestore.mostraCorso("C1"));
    }

    @Test
    void testMostraCorsiPerTipologiaClienti() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        assertDoesNotThrow(() -> gestore.mostraCorsiPerTipologiaClienti(Vasca.TipoVasca.DONNE));
    }


    @Test
    void testMostraTutteLezioni() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        Corso corso = gestore.cercaCorso("C1");
        corso.aggiungiLezione("L1", "10:00", "11:00");

        assertDoesNotThrow(() -> gestore.mostraTutteLezioni());
    }
    
    
    // ITERAZIONE 3


    @Test
    void testGetCorsiDaEliminare() throws Exception {
        // Corso con pochi occupati → da eliminare
        DescrizioneCorso descrLow = new DescrizioneCorso("AcquaGym", Vasca.TipoVasca.UOMINI, 10, 5, 2); // 20%
        gestore.aggiungiCorso("C1", descrLow);

        // Corso normale → non da eliminare
        DescrizioneCorso descrNorm = new DescrizioneCorso("Yoga", Vasca.TipoVasca.DONNE, 10, 5, 5); // 50%
        gestore.aggiungiCorso("C2", descrNorm);

        List<Corso> daEliminare = gestore.getCorsiDaEliminare();
        assertEquals(1, daEliminare.size());
        assertEquals("C1", daEliminare.get(0).getIdCorso());
    }

    @Test
    void testGetCorsiDaAmpliare() throws Exception {
        // Corso quasi pieno → da ampliare
        DescrizioneCorso descrHigh = new DescrizioneCorso("Pilates", Vasca.TipoVasca.UOMINI, 10, 5, 9); // 90%
        gestore.aggiungiCorso("C3", descrHigh);

        // Corso normale → non da ampliare
        DescrizioneCorso descrNorm = new DescrizioneCorso("Nuoto", Vasca.TipoVasca.DONNE, 10, 5, 5); // 50%
        gestore.aggiungiCorso("C4", descrNorm);

        List<Corso> daAmpliare = gestore.getCorsiDaAmpliare();
        assertEquals(1, daAmpliare.size());
        assertEquals("C3", daAmpliare.get(0).getIdCorso());
    }
    

}
