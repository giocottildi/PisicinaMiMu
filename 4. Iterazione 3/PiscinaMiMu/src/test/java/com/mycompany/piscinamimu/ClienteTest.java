/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import java.util.Map;
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
public class ClienteTest {
    
        private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Mario", "Rossi", "001", Cliente.TipologiaCliente.UOMINI);
    }

    @Test
    public void testCostruttoreEGetters() {
        assertEquals("Mario", cliente.getNome());
        assertEquals("Rossi", cliente.getCognome());
        assertEquals("001", cliente.getIdCliente());
        assertNotNull(cliente.getCorsiIscritti());
        assertTrue(cliente.getCorsiIscritti().isEmpty());
    }

    @Test
    public void testNumCorsiIniziale() {
        assertEquals(0, cliente.numCorsi());
    }

    // Momentaneo, quando si farà l'assegna corso potrebbe dover cambiare
    @Test
    public void testAggiuntaCorso() {
        Corso corso = new Corso("C001", new DescrizioneCorso("Nuoto Base",Vasca.TipoVasca.UOMINI, 4, 10, 2));
        cliente.getCorsiIscritti().put(corso.getIdCorso(), corso);

        assertEquals(1, cliente.numCorsi());
        assertTrue(cliente.getCorsiIscritti().containsKey("C001"));
        assertEquals(corso, cliente.getCorsiIscritti().get("C001"));
    }
    
    // Momentaneo, quando si farà l'assegna corso potrebbe dover cambiare
    @Test
    public void testToStringContieneInfoCliente() {
        String output = cliente.toString();
        Cliente cliente = new Cliente("001", "Mario", "Rossi", Cliente.TipologiaCliente.UOMINI);
        assertTrue(output.contains("Mario"));
        assertTrue(output.contains("Rossi"));
        assertTrue(output.contains("001"));
        assertTrue(output.contains("tipologia=UOMINI"));
        assertTrue(output.contains("numeroCorsi=0"));

        // Ritesto dopo aver aggiunto un corso
        Corso corso = new Corso("C001", new DescrizioneCorso("Nuoto Base", Vasca.TipoVasca.UOMINI, 4, 10, 2));
        cliente.getCorsiIscritti().put(corso.getIdCorso(), corso);
        output = cliente.toString();
        assertTrue(output.contains("numeroCorsi=1"));
    }
    
}
