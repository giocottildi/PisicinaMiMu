/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.io.*;
/**
 *
 * @author pmilo
 */
public class InterazioneUtente{
    
    
    private static final String FILE_CORSI = "elencoCorsi.txt";
    private static final String FILE_LEZIONI = "elencoLezioni.txt";
    private static final String FILE_ISTRUTTORI = "elencoIstruttori.txt";
    private static final String FILE_CLIENTI = "elencoClienti.txt";
    private static final String FILE_VASCHE = "elencoVasche.txt";
    private static final String FILE_CORSIE = "elencoCorsie.txt";
    
    private GestoreCorsi gc;
    private GestoreIstruttori gi;
    private GestoreClienti gcl;
    private GestoreVasche gv;
      
    public InterazioneUtente(GestoreCorsi gc, GestoreIstruttori gi, GestoreClienti gcl,  GestoreVasche gv){
        this.gc = gc;
        this.gi = gi;
        this.gcl = gcl;
        this.gv = gv;
    }
    
    
    // VARI MENU
    
    public int menu(BufferedReader bf){
        try{
            //System.out.println("1. Carica corsi da file.");
            System.out.println("=== MENU PRINCIPALE ===\n");
            System.out.println("1. Inserisci nuovo corso");
            System.out.println("2. Modifica un corso");
            System.out.println("3. Inserisci nuova vasca");
            System.out.println("4. Iscrizione cliente alla piscina");
            System.out.println("6. Associa una lezione ad una corsia");
            System.out.println("5. Iscrivi un cliente ad un corso");            
            System.out.println("7. Visualizza Report");
            System.out.println("8. Assegna istruttore a un corso");
            System.out.println("9. Valuta pienezza corsi");
            System.out.println("10. Assumi istruttore");
            System.out.println("11. Modifica Vasca");


            System.out.println("\n** Operazioni Speciali **");
            System.out.println("20. EXIT");
            System.out.println("30. CARICA TUTTI DA FILE");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
    
    public int menuModificaCorso(BufferedReader bf){
        try{
            System.out.println("1. Elimina Lezione.");
            System.out.println("2. Elimina Corso.");
            System.out.println("3. Aggiungi Lezione.");
            System.out.println("4. Modifica Attributi Corso");            
            System.out.println("10 Stampa Corsi.");           

            System.out.println("20. Torna al menu principale");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
        
    public int menuModificaAttributi(BufferedReader bf){
        try{
            System.out.println("1. Modifica nome.");
            System.out.println("2. Modifica Tipologia Clienti.");
            System.out.println("3. Modifica Numero Posti Totali.");
            System.out.println("4. Modifica Durata.");
            System.out.println("5. Modifica Numero Posti Occupati");
            System.out.println("6. Modifica Attributi Delle Lezioni Del Corso");

            System.out.println("20. Torna al menu principale");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }  
        
    public int menuModificaAttributiLezione(BufferedReader bf){
        try{
            System.out.println("1. Modifica Ora Inizio.");
            System.out.println("2. Modifica Ora Fine.");
            
            System.out.println("20. Torna al menu principale");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
    
    public int menuModificaVasca(BufferedReader bf){
        try{
            System.out.println("1. Elimina Corsia.");
            System.out.println("2. Aggiungi Corsia.");
            System.out.println("20. Torna al menu principale");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
    
    // FUNZIONE CHE VERRA UTILIZZATA COME MENU DEL REPORT NEL CASE DEL MENU PRINCIPALE
    
    
    private void gestisciMenuVisualizzazioni(BufferedReader bf) throws IOException {
        int scelta = -1;

        do {
            System.out.println("===== MENU VISUALIZZAZIONI =====");
            System.out.println("1. Visualizza Corsi");
            System.out.println("2. Visualizza Clienti");
            System.out.println("3. Visualizza Istruttori");
            System.out.println("4. Visualizza Vasche");
            System.out.println("5. Visualizza Lezioni");
            System.out.println("20. Torna al menu principale");
            System.out.print("Scelta: ");

            scelta = Integer.parseInt(bf.readLine());

            switch (scelta) {
                case 1: gestisciSottoCorsi(bf); break;
                case 2: gestisciSottoClienti(bf); break;
                case 3: gestisciSottoIstruttori(bf); break;
                case 4: gestisciSottoVasche(bf); break;
                case 5: gc.mostraTutteLezioni(); break;
                case 20: System.out.println("Torno al menu principale..."); break;
                default: System.out.println("Scelta non valida!");
            }

        } while (scelta != 20);
    }
    
    // FUNZIONI E SOTTOMENU REPORT
    
    private void gestisciSottoCorsi(BufferedReader bf) throws IOException {
        System.out.println("---- VISUALIZZA CORSI ----");
        System.out.println("1. Mostra Tutti");
        System.out.println("2. Mostra singolo corso (idCorso)");
        System.out.println("3. Mostra corsi (tipologiaClienti)");
        System.out.println("4. Mostra Corsi con percentuale pienezza");
        System.out.print("Scelta: ");
        int scelta = Integer.parseInt(bf.readLine());

        switch (scelta) {
            case 1: gc.mostraTuttiCorsi(); break;
            case 2:
                System.out.print("Inserisci idCorso: ");
                String idCorso = bf.readLine();
                gc.mostraCorso(idCorso);
                break;
        case 3:
            System.out.print("Inserisci tipologiaClienti: ");
            String tipoClientiStr = bf.readLine();
            try {
                Vasca.TipoVasca tipoClientiEnum = Vasca.TipoVasca.fromString(tipoClientiStr);
                gc.mostraCorsiPerTipologiaClienti(tipoClientiEnum);
            } catch (TipologiaVascaNonEsistenteException e) {
                System.out.println(e.getMessage());
            }
            break;
            case 4: gc.mostraCorsiPerPercentualePienezza(); break;
            default: System.out.println("Scelta non valida");
        }
    }

    
    private void gestisciSottoClienti(BufferedReader bf) throws IOException {
        System.out.println("---- VISUALIZZA CLIENTI ----");
        System.out.println("1. Mostra Tutti");
        System.out.println("2. Mostra singolo cliente (idCliente)");
        System.out.println("3. Mostra clienti (tipologia)");
        System.out.print("Scelta: ");
        int scelta = Integer.parseInt(bf.readLine());

        switch (scelta) {
            case 1: gcl.mostraTuttiClienti(); break;
            case 2:
               System.out.print("Inserisci idCliente: ");
               String idCliente = bf.readLine();
               gcl.mostraCliente(idCliente); // usa il metodo già presente
               break;
            case 3:
                System.out.println("Tipologie disponibili: Donne, Uomini, Bambini, Riabilitazione");
                System.out.print("Inserisci tipologia: ");
                try {
                    Cliente.TipologiaCliente tipologia = Cliente.TipologiaCliente.valueOf(bf.readLine());
                    gcl.mostraClientiPerTipologia(tipologia);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipologia non valida!");
                }
                break;
            default: System.out.println("Scelta non valida");
        }
    }
    private void gestisciSottoIstruttori(BufferedReader bf) throws IOException {
        System.out.println("---- VISUALIZZA ISTRUTTORI ----");
        System.out.println("1. Mostra Tutti");
        System.out.println("2. Mostra Disponibili");
        System.out.println("3. Mostra singolo istruttore (idIstruttore)");
        System.out.println("4. Mostra Occupati (almeno 1 corso)");
        System.out.print("Scelta: ");
        int scelta = Integer.parseInt(bf.readLine());

        switch (scelta) {
            case 1: gi.mostraTuttiIstruttori(); break;
            case 2: gi.mostraIstruttoriPerDisponibilita(true); break;
            case 3:
                System.out.print("Inserisci idIstruttore: ");
                String idIstruttore = bf.readLine();
                gi.mostraIstruttore(idIstruttore);
                break;
            case 4: gi.mostraIstruttoriPerDisponibilita(false); break;
            default: System.out.println("Scelta non valida");
        }
    }
    
    private void gestisciSottoVasche(BufferedReader bf) throws IOException {
        System.out.println("---- VISUALIZZA VASCHE ----");
        System.out.println("1. Mostra Tutte");
        System.out.println("2. Mostra Vasche (tipologia)");
        System.out.print("Scelta: ");
        int scelta = Integer.parseInt(bf.readLine());

        switch (scelta) {
            case 1: gv.mostraTutteVasche(); break;
            case 2:
                System.out.print("Inserisci tipo vasca: ");
                String tipoVasca = bf.readLine();
                try{
                gv.mostraVaschePerTipologia(tipoVasca);
                }catch(TipologiaVascaNonEsistenteException e){
                    System.out.println(e.getMessage());
                }
                break;
            default: System.out.println("Scelta non valida");
        }
    }
    
    
    
    
    // FUNZIONI CHE VERRANNO UTILIZZATE NEI CASE DEL MENU PRINCIPALE
    
    // UC1
    
    private void gestisciNuovoCorso(BufferedReader bf) throws IOException {
        try {
            String idCorso, risposta; 
            idCorso = gc.aggiungiCorso();
            do {
                System.out.println("Vuoi aggiungere una lezione? (Si/No)");
                risposta = bf.readLine();
                if (risposta.equalsIgnoreCase("Si")) 
                    gc.aggiungiLezione(idCorso);
                
            } while (risposta.equalsIgnoreCase("Si"));
        } catch (Exception e) {
            System.out.println("OPS: qualcosa è andato storto!");
        }
    }
    
    // UC2
    
    private void gestisciModificaCorso(BufferedReader bf) throws IOException {
        String idCorso;
        
        System.out.print("Inserire ID Corso da modificare: ");
        idCorso = bf.readLine();
        try {
            gc.cercaCorso(idCorso);

            int sceltaModifica;
            do {
                sceltaModifica = menuModificaCorso(bf);
                switch (sceltaModifica) {
                    case 1: 
                            gestisciEliminaLezione(bf, idCorso);
                            break;
                    case 2: // Da pulire
                            try {
                                 gc.eliminaCorso(idCorso);
                                 System.out.println("Corso eliminato con successo.");
                             } catch (CorsoNonPresenteException e) {
                                 System.out.println("Errore: " + e.getMessage());
                             } catch (Exception e) {
                                 System.out.println("OPS: qualcosa è andato storto!");
                             }
                            break;
                    case 3: 
                            gestisciAggiungiLezione(bf, idCorso);
                            break;
                    case 4:     
                            gestisciModificaAttributiCorso(bf, idCorso);
                            break;
                    case 10:
                            gc.mostraTuttiCorsi();
                            break;
                }
            } while (sceltaModifica != 20);

        } catch (CorsoNonPresenteException e) {
            System.out.println("Errore: " + e.getMessage() + ". Torna al menu principale.");
        }
    }
    
    
    private void gestisciEliminaLezione(BufferedReader bf, String idCorso) throws IOException {
        String risposta;
        do {
            try {
                gc.eliminaLezione(idCorso);
            }catch(Exception e) {
                System.out.println("OPS: qualcosa è andato storto!");
            }
            System.out.println("Vuoi eliminare un'altra lezione? (Si/No)");
            risposta = bf.readLine();
        } while (risposta.equalsIgnoreCase("Si"));
    }
    
    
    private void gestisciAggiungiLezione(BufferedReader bf, String idCorso) throws IOException {
        String risposta;
        do {
            try {
                gc.aggiungiLezione(idCorso);
            } catch (Exception e) {
                System.out.println("OPS: qualcosa è andato storto!");
            }
            System.out.println("Vuoi aggiungere un'altra lezione? (Si/No)");
            risposta = bf.readLine();
        } while (risposta.equalsIgnoreCase("Si"));
    }
    
    private void gestisciModificaAttributiCorso(BufferedReader bf, String idCorso) throws IOException {
        int scelta;
        do {
            scelta = menuModificaAttributi(bf);
            switch (scelta) {
                case 1:
                        try { gc.ModificaNome(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                case 2:
                        try { gc.ModificaTipologiaClienti(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                case 3:
                        try { gc.ModificaNumeroPosti(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                case 4:
                        try { gc.ModificaDurata(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                
                case 5:
                        try { gc.ModificaPostiOccupati(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                
                case 6:
                        gestisciModificaLezione(bf, idCorso);
                        break;

            }
        } while (scelta != 20);
    }
    
    private void gestisciModificaLezione(BufferedReader bf, String idCorso) throws IOException {
        String idLezione; 
        
        System.out.print("Inserire ID Lezione: ");
        idLezione = bf.readLine();

        int scelta;
        do {
            scelta = menuModificaAttributiLezione(bf);
            switch (scelta) {
                case 1:
                    try { gc.ModificaOraInizioLezione(idCorso, idLezione); }
                    catch(LezioneNonPresenteException e){System.out.println(e.getMessage());
                    scelta = 20;}
                    catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                    catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                    break;
                
                case 2:
                    try { gc.ModificaOraFineLezione(idCorso, idLezione); }
                    catch(LezioneNonPresenteException e){System.out.println(e.getMessage());
                    scelta = 20;}
                    catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                    catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                    break;
                
            }
        } while (scelta != 20);
    }
    
    
    // UC3
    
    private void gestisciNuovaVasca(BufferedReader bf) throws IOException{
        try{
            System.out.println("Inserisci id nuova vasca --->");
            String id_vasca = bf.readLine();
            System.out.println("Inserisci tipologia nuova vasca --->");
            String tipologia_vasca = bf.readLine();

            gv.aggiungiVasca(tipologia_vasca, id_vasca);
        }
        catch(TipologiaVascaNonEsistenteException e){System.out.println(e);}
        catch(VascaGiaPresenteException e){
            System.out.println(e.getMessage());
        }
    }
    
    // UC4
    
    private void gestisciIscrizionePiscina(BufferedReader bf){
        try {
            String idCliente, nome, cognome, tipologia;
            Cliente.TipologiaCliente tipo;

            System.out.print("Inserisci ID Cliente: ");
            idCliente = bf.readLine();
            System.out.print("Inserisci Nome Cliente: ");
            nome = bf.readLine();
            System.out.print("Inserisci Cognome Cliente: ");
            cognome = bf.readLine();
            System.out.print("Inserisci Tipologia cliente (Donne, Uomini, Bambini, Riabilitazione): ");
            tipologia = bf.readLine();
            tipo = Cliente.TipologiaCliente.valueOf(tipologia);

            Cliente nuovoCliente = new Cliente(nome, cognome, idCliente, tipo);
            try {
                gcl.aggiungiCliente(nuovoCliente);
                System.out.println("Cliente aggiunto correttamente!");
            } catch (ClienteGiaPresenteException e) {
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("OPS: errore in lettura input!");
        }
    }
    
    // UC5
    public void gestisciIscrizioneAlCorso(BufferedReader bf){
        String id_cliente;
        String id_corso;
        
        try{
            System.out.println("Inseririsci ID Corso: ");
            id_corso = bf.readLine();
            Corso c = gc.cercaCorso(id_corso);
            
            System.out.println("Inserisci ID Cliente: ");
            id_cliente = bf.readLine();
            gcl.IscrizioneClienteCorso(id_cliente, c);
        }
        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
        catch(ClienteNonPresenteException e){System.out.println(e.getMessage());}
        catch(PostiPieniException e){System.out.println(e.getMessage());}
        catch(TipologiaNonCorrispondente e){System.out.println(e.getMessage());}
        catch(ClienteGiaIscrittoException e){System.out.println(e.getMessage());}
        catch(IOException e){System.out.println("OPS: errore in lettura input!");}
    } 
    
    // UC6
    
    private void gestisciAssegnamentoLezioneCorsia(BufferedReader bf) throws IOException{
        try{
            System.out.println("Inserisci id corso --->");
            String id_corso = bf.readLine();
            System.out.println("Inserisci id lezione --->");
            String id_lezione = bf.readLine();
            System.out.println("Inserisci id corsia --->");
            String id_corsia = bf.readLine();

            Corsia cr = gv.CercaCorsia(id_corsia);
            gc.AggiungiCorsia(id_corso, id_lezione, cr);

        }catch(CorsoNonPresenteException e){
            System.out.println(e);
        }catch(TipologiaNonCompatibileException e){
            System.out.println(e);
        }catch(LezioneNonPresenteException e){
            System.out.println(e);
        }catch(CorsiaGiaPresenteException e){
            System.out.println(e);
        }catch(CorsiaNonEsistenteException e){
            System.out.println(e);
        }
    }
    
        
    // UC8
    
    private void gestisciAssegnaIstruttore(BufferedReader bf){
        try{
            System.out.println("Scegli Corso A Cui Assegnare Istruttore");
            String id_corso = bf.readLine();

            gi.mostraIstruttoriDisponibili();

            System.out.println("Scegli ID Istruttore Da Assegnare al corso: " + id_corso + " --->");
            String id_istruttore = bf.readLine();

            Istruttore is = gi.getIstruttore(id_istruttore);

            gc.AggiungiIstruttore(is, id_corso);

        }catch(IstruttoreGiaAssegnatoAlCorsoException e){
            System.out.println(e.getMessage());
        }catch(IstruttoreNonDisponibileException e){
            System.out.println(e.getMessage());
        }    
        catch(Exception e){
            System.out.println("OPS: qualcosa è andato storto!");
        }
    }
    
    
    // UC9
    
    private void gestisciValutazionePienezza(BufferedReader bf){

        gc.mostraCorsiPerPercentualePienezza();
        gc.visualizzaAnalisiCorsi();
        
        
    }   
    

    // UC10
    
    private void gestisciAssumiIstruttore(BufferedReader bf){
        String id_istruttore, nome, cognome;
        try {
            System.out.println("Inserisci ID istruttore --->");
            id_istruttore = bf.readLine();
            System.out.println("Inserisci Nome istruttore --->");
            nome = bf.readLine();
            System.out.println("Inserisci Cognome istruttore --->");
            cognome = bf.readLine();
            gi.AssumiIstruttore(nome, cognome, id_istruttore);
        } catch (IstruttoreGiaAssuntoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("OPS: qualcosa è andato storto!");
        }
    }
    
    // UC11
    
    private void gestisciModificaVasca(BufferedReader bf) throws IOException {
        String idVasca;
        
        System.out.print("Inserire ID Vasca da modificare: ");
        idVasca = bf.readLine();
        try {
            gv.cercaVasca(idVasca);

            int sceltaModifica;
            do {
                sceltaModifica = menuModificaVasca(bf);
                switch (sceltaModifica) {
                    case 1: 
                            gestisciRimuoviCorsia(bf, idVasca);
                            break;
                    case 2: 
                            gestisciAggiungiCorsia(bf, idVasca);
                            break;
                }
            } while (sceltaModifica != 20);

        } catch (VascaNonPresenteException e) {
            System.out.println("Errore: " + e.getMessage() + ". Torna al menu principale.");
        }
    }
    
    
    private void gestisciAggiungiCorsia(BufferedReader bf, String idVasca) throws IOException{
        try{
            
            System.out.println("Inserire nuova corsia --->");
            String id_corsia = bf.readLine();

            Corsia cr = new Corsia(id_corsia);
            gv.AssegnaCorsiaToVasca(idVasca, cr);
        }catch(CorsiaGiaEsistenteException e){
            System.out.println(e);
        }catch(VascaNonPresenteException e){
            System.out.println(e);
        }catch(CorsiaGiaPresenteException e){
            System.out.println(e);
        } 
    }
    
    private void gestisciRimuoviCorsia(BufferedReader bf, String idVasca) throws IOException{
        try{

            System.out.println("Inserisci Id Corsia da rimuovere --->");
            String id_corsia = bf.readLine();

            gv.RemoveCorsiaToVasca(idVasca, id_corsia);
            
        }catch(VascaNonPresenteException e){
            System.out.println(e);
        }catch(CorsiaNonPresenteNellaVascaException e){
            System.out.println(e);
        }
    } 
    
    
    // 30. CARICAMENTO FILE
    
    // MOMENTANEAMENTE SI PREFERISCE QUESTA PER NON INSERIRE MANUALMENTE IL NOME DI OGNI FILE (CHE VERRANNO COMUNQUE FORNITI)
    
    private void caricaDaFile() {
        try {
            gc.caricaCorsi(FILE_CORSI);
            gc.caricaLezioni(FILE_LEZIONI);
            gi.caricaIstruttori(FILE_ISTRUTTORI);
            gcl.caricaClienti(FILE_CLIENTI);
            gv.CaricaVasche(FILE_VASCHE);
            gv.CaricaCorsie(FILE_CORSIE);

            System.out.println("Caricamento completato!");

        }
        catch(VascaNonPresenteException e){System.out.println(e);}
        catch(CorsiaGiaPresenteException e){System.out.println(e);}
        catch (Exception e) {
            System.out.println("ERRORE IN FASE DEL CARICAMENTO DA FILE");
            System.exit(-1);
        }
    }
    
        /* Se si vogliono inserire i nomi dei file manualmente
    
    private void caricaDaFile(){
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            try{
                String nomeFileCorsi, nomeFileLezioni, nomeFileIstruttori, nomeFileClienti;

                System.out.print("Nome file corsi: ");
                nomeFileCorsi = bf.readLine();
                gc.caricaCorsi(nomeFileCorsi);

                System.out.print("Nome file lezioni: ");
                nomeFileLezioni = bf.readLine();
                gc.caricaLezioni(nomeFileLezioni);

                System.out.print("Nome file Istruttori: ");
                nomeFileIstruttori = bf.readLine();
                gi.caricaIstruttori(nomeFileIstruttori);
                
                System.out.print("Nome file Clienti: ");
                nomeFileClienti = bf.readLine();
                gcl.caricaClienti(nomeFileClienti);
                
            }catch (IOException e) {
                System.out.println("ERRORE IN FASE DI I/O nel caricamento da file");
                System.exit(-1);
            }

    }
    */    
    
    public void avvia() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            
            int scelta;
            do {
                scelta = menu(bf); 
                switch (scelta) {
                    case 1: // NUOVO CORSO
                            gestisciNuovoCorso(bf);  
                            break;
                    case 2: // MODIFICA CORSO
                            gestisciModificaCorso(bf);       
                            break;
                    case 3: // NUOVA VASCA
                            gestisciNuovaVasca(bf);
                            break;
                    case 4: // ISCRIZIONE CLIENTE IN PISCINA
                            gestisciIscrizionePiscina(bf);
                            break;
                    case 5: // ISCRIZIONE CLIENTE AL CORSO
                            gestisciIscrizioneAlCorso(bf);
                            break;                            
                    case 6: // ASSEGNAMENTO LEZIONE A CORSIA
                            gestisciAssegnamentoLezioneCorsia(bf);
                            break;
                    case 7: // REPORT --> MENU VISUALIZZAZIONI
                            gestisciMenuVisualizzazioni(bf);
                            break;
                    case 8: // ASSEGNA ISTRUTTORE
                            gestisciAssegnaIstruttore(bf);
                            break;
                    case 9: // VALUTA PIENEZZA CORSI
                            gestisciValutazionePienezza(bf);
                            break;
                    case 10: // ASSUMI ISTRUTTORE                     
                            gestisciAssumiIstruttore(bf);
                            break;
                    case 11: // MODIFICA VASCA
                            gestisciModificaVasca(bf);
                            break;
                            
                    case 30: // CARICA DA FILE (Al momento carica tutti i file)
                            caricaDaFile();
                            break;
                }
            } while (scelta != 20);

        } catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
}
