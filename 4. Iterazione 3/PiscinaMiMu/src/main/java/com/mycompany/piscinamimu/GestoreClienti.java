/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.io.*;
import java.util.*;
/**
 *
 * @author pmilo
 */
public class GestoreClienti {

    private Map<String, Cliente> elencoClienti;

    public GestoreClienti() {
        this.elencoClienti = new HashMap<>();
    }
    
    
    public Cliente getCliente(String id_cliente) throws ClienteNonPresenteException {
        Cliente cl = elencoClienti.get(id_cliente);
        if (cl == null) {
            throw new ClienteNonPresenteException(id_cliente);
        }
        return cl;
    }

    public Collection<Cliente> getElencoClienti() {
        return elencoClienti.values();
    }
    

    public void aggiungiCliente(Cliente cliente) throws ClienteGiaPresenteException {
        if (elencoClienti.containsKey(cliente.getIdCliente())) {
            throw new ClienteGiaPresenteException(cliente.getIdCliente());
        }
        elencoClienti.put(cliente.getIdCliente(), cliente);
    }
    
    

    // Caricamento clienti da file
    public synchronized void caricaClienti(String nomeFile) {
        try (BufferedReader bf = new BufferedReader(new FileReader(nomeFile))) {

            String idCliente, nome, cognome, tipologia;
            Cliente.TipologiaCliente tipo;

            while ((idCliente = bf.readLine()) != null) {
                nome = bf.readLine();
                cognome = bf.readLine();
                tipologia = bf.readLine();
                try {
                    tipo = Cliente.TipologiaCliente.fromString(tipologia);
                    Cliente cliente = new Cliente(nome, cognome, idCliente, tipo);
                    aggiungiCliente(cliente);
                    System.out.println("Cliente caricato: " + cliente);
                } catch (ClienteGiaPresenteException | TipologiaClienteNonEsistenteException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Numero clienti caricati: " + elencoClienti.size());

        } catch (IOException e) {
            System.out.println("ERRORE in fase di I/O: " + e.getMessage());
            System.exit(-1);
        }
    }
    
    public synchronized void mostraTuttiClienti() {
        for (Cliente c : elencoClienti.values()) {
            c.stampaDettagli();
        }
    }
    public void mostraCliente(String idCliente) {
        Cliente c = elencoClienti.get(idCliente);
        if(c == null) {
            System.out.println("Cliente non trovato.");
            return;
        }
        c.stampaDettagli();
    }
    public void mostraClientiPerTipologia(Cliente.TipologiaCliente tipologia) {
        boolean trovato = false;
        for (Cliente c : elencoClienti.values()) {
            if (c.getTipologia() == tipologia) {
                c.stampaDettagli();
                trovato = true;
            }
        }
        if (!trovato) {
            System.out.println("Nessun cliente trovato per la tipologia " + tipologia);
        }
    }
    
    public void IscrizioneClienteCorso(String id_cliente, Corso c) 
            throws PostiPieniException, ClienteNonPresenteException,
                   TipologiaNonCorrispondente, ClienteGiaIscrittoException {

        try {
            Cliente cl = this.getCliente(id_cliente);
            Map<String, Corso> corsi_iscritti = cl.getCorsiIscritti();
            if (corsi_iscritti.get(c.getIdCorso()) != null) {
                throw new ClienteGiaIscrittoException(id_cliente, c.getIdCorso());
            }


            DescrizioneCorso cd = c.getDescrizione();

            if (cd.isCompatibileCon(cl.getTipologia())) {
                cd.aumentaPostiOccupati();
                cl.AggiungiCorso(c);
                System.out.println("Cliente: " + id_cliente +
                                   " aggiunto correttamente al Corso: " + c.getIdCorso() +
                                   " con tipo: " + cd.getTipologiaClienti());
            } else {
                throw new TipologiaNonCorrispondente(id_cliente, c);
            }
        } catch (ClienteNonPresenteException e) {
            System.out.println(e.getMessage());
        }        
    }
}
