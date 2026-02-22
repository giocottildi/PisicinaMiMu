/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pmilo
 */
public class GestoreClientiTest {
     
    private GestoreClienti gestore;

    @BeforeEach
    public void setUp() {
        gestore = new GestoreClienti();
    }

    @Test
    public void testAggiungiClienteNuovo() throws ClienteGiaPresenteException {
        Cliente c = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c);
        assertEquals(c, gestore.getCliente("001"));
    }

    @Test
    public void testAggiungiClienteDuplicato() throws ClienteGiaPresenteException {
        Cliente c = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c);
        Cliente duplicato = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);

        ClienteGiaPresenteException ex = assertThrows(
            ClienteGiaPresenteException.class,
            () -> gestore.aggiungiCliente(duplicato)
        );
        assertTrue(ex.getMessage().contains("001"));
    }

    @Test
    void testGetClienteNonEsistente() throws ClienteGiaPresenteException {
        Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        Cliente c2 = new Cliente("Luigi", "Bianchi", "002", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c1);
        gestore.aggiungiCliente(c2);

        String idNonPresente = "003"; 
        assertNull(gestore.getCliente(idNonPresente));
    }

    @Test
    public void testGetElencoClienti() throws ClienteGiaPresenteException {
        Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        Cliente c2 = new Cliente("Luigi", "Bianchi", "002", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c1);
        gestore.aggiungiCliente(c2);

        Collection<Cliente> clienti = gestore.getElencoClienti();
        assertEquals(2, clienti.size());
        assertTrue(clienti.contains(c1));
        assertTrue(clienti.contains(c2));
    }

@Test
void testMostraTuttiClienti() throws ClienteGiaPresenteException {
    Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
    Cliente c2 = new Cliente("Luigi", "Bianchi", "002", Cliente.TipologiaCliente.Donne);
    gestore.aggiungiCliente(c1);
    gestore.aggiungiCliente(c2);

    gestore.mostraTuttiClienti();
}

    @Test
    void testMostraClientiPerTipologiaConClienti() throws ClienteGiaPresenteException {
        Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        Cliente c2 = new Cliente("Luigi", "Bianchi", "002", Cliente.TipologiaCliente.Donne);
        gestore.aggiungiCliente(c1);
        gestore.aggiungiCliente(c2);

        gestore.mostraClientiPerTipologia(Cliente.TipologiaCliente.Uomini);
    }

    @Test
    void testMostraClientiPerTipologiaSenzaClienti() throws ClienteGiaPresenteException {
        Cliente c = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c);

        gestore.mostraClientiPerTipologia(Cliente.TipologiaCliente.Donne);
    }
    
    @Test
    void testIscrizioneClienteCorso_ok() throws Exception{
        Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c1);
        DescrizioneCorso cd = new DescrizioneCorso("Corso1", "Uomini", 2, 2, 0);
        Corso c = new Corso("C1", cd);
        
        assertDoesNotThrow(() -> gestore.IscrizioneClienteCorso("001", c));
    }
    
    @Test
    void testiIscrizioneClienteCorso_TipologiaNonCorrispondente() throws Exception{
        Cliente c1 = new Cliente("Maria", "Rossi", "001", Cliente.TipologiaCliente.Donne);
        gestore.aggiungiCliente(c1);
        DescrizioneCorso cd = new DescrizioneCorso("Corso1", "Uomini", 2, 2, 0);
        Corso c = new Corso("C1", cd);
        
        assertThrows(TipologiaNonCorrispondente.class, ()->gestore.IscrizioneClienteCorso("001", c));
    }
    
    @Test
    void testiIscrizioneClienteCorso_ClienteGiaIscrittoException() throws Exception{
        Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c1);
        DescrizioneCorso cd = new DescrizioneCorso("Corso1", "Uomini", 2, 2, 0);
        Corso c = new Corso("C1", cd);
        gestore.IscrizioneClienteCorso("001", c);
        assertThrows(ClienteGiaIscrittoException.class, ()->gestore.IscrizioneClienteCorso("001", c));
    }
    
    @Test
    void testiIscrizioneClienteCorso_PostiPieniException() throws Exception{
        Cliente c1 = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.Uomini);
        gestore.aggiungiCliente(c1);
        DescrizioneCorso cd = new DescrizioneCorso("Corso1", "Uomini", 2, 2, 2);
        Corso c = new Corso("C1", cd);

        assertThrows(PostiPieniException.class, ()->gestore.IscrizioneClienteCorso("001", c));
    }
}
