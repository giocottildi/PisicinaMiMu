/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author salvatore
 */
public class IstruttoreTest {
    
    private Istruttore is;
    private DescrizioneCorso cd;
    
    @BeforeEach
    void setUp(){
    
        is = new Istruttore("Franco", "Colapinto", "IS1");
        cd = new DescrizioneCorso("NuotoSincronizzato", Vasca.TipoVasca.DONNE, 10, 5, 0);
    
    }
    
    @Test
    void isDisponibile_ok() throws Exception{
        assertTrue(is.isDisponibile());
    }
    
    @Test
    void isDisponibile_not_ok() throws Exception{
        is.AssegnaCorso(new Corso("C1", cd));
        is.AssegnaCorso(new Corso("C2", cd));
        is.AssegnaCorso(new Corso("C3", cd));
        assertThrows(IstruttoreNonDisponibileException.class, () -> is.isDisponibile());
    }
    
    @Test
    public void toString_test(){
        String s = is.toString();
        assertTrue(s.contains(("Istruttore [Nome= Franco Colapinto, ID= IS1]")));
    }
    @Test
    public void assegnaCorso_ok() throws Exception {
        Corso c = new Corso("C1", cd);

        is.AssegnaCorso(c);

        assertEquals("C1", is.getCorsoInsegnato("C1").getIdCorso());
    }
    
}
