import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * Classe che rappresenta un corriere responsabile della gestione dei colli e dei veicoli.
 * Il corriere mantiene una lista dei colli da gestire, una lista dei veicoli disponibili
 * e una mappa che traccia in quali veicoli sono stati inseriti i colli.
 * Utilizza il pattern Chain of Responsibility per inserire i colli nei veicoli. Inoltre si occupa
 * dell'aggiornamento dello stato dei colli.
 */
public class Corriere {
    private String nome; //nome del corriere
    private List<Collo> colli; //lista dei colli da gestire
    private List<Veicolo> veicoli; //lista dei veicoli del corriere
    private Map<Collo, List<Veicolo>> mappaColliInVecoli; //mappa che associa ciascun collo alla lista di veicoli in cui è stato inserito
    private ScheduledExecutorService scheduledExecutorService; //può programmare comandi da eseguire dopo un determinato ritardo o per essere eseguiti periodicamente

    /**
     * Costruisce un nuovo oggetto Corriere con un dato nome e pianifica gli aggiornamenti periodici dello stato dei colli.
     * @param nome il nome del Corriere.
     */
    public Corriere(String nome) {
        this.nome = nome;
        this.colli = new ArrayList<>();
        this.veicoli = new ArrayList<>();
        this.mappaColliInVecoli = new HashMap<>();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduleUpdate();
    }

    /**
     * Pianifica un task periodico per aggiornare lo stato dei colli gestiti dal corriere.
     */
    private void scheduleUpdate() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            aggiornaStatoColli();
        }, 20, 60, TimeUnit.SECONDS);
    }

    /**
     * Aggiorna lo stato di ogni collo nella mappa dei colli gestiti dal corriere.
     * Questo metodo sincronizza l'accesso alla mappa dei colli e avanza lo stato di
     * ogni collo alla sua prossima fase, seguendo il pattern State.
     */
    public void aggiornaStatoColli() {
        synchronized (mappaColliInVecoli) {
            for (Collo c : mappaColliInVecoli.keySet()) {
                c.prossimoStato();
            }
        }
    }

    /**
     * Ritorna il nome del corriere.
     * @return nome del corriere.
     */

    public String getNome() {
        return nome;
    }

    /**
     *Termina l'esecuzione del ScheduledExecutorService e interrompe tutti i task pianificati.
     */
    public void shutdown() {
        if (scheduledExecutorService != null) {
            this.scheduledExecutorService.shutdown();
        }
    }

    /**
     * Inserisce un collo nella mappa, associandolo a una lista di veicoli.
     * @param c Il collo da inserire.
     * @param v Il veicolo in cui inserire il collo.
     */
    public void inserisciInMappa(Collo c, Veicolo v) {
        /*
        Mappa in cui più veicoli possono essere associati allo stesso collo
        la funziona lambda viene eseguita solo se la chiave non è già presente nella mappa, questa funzione crea una nuova
        istanza di ArrayList<Veicolo> e l'aggiunge come valore associato alla chiave c nella mappa.
         */
        mappaColliInVecoli.computeIfAbsent(c, k -> new ArrayList<>()).add(v);
    }

    /**
     * Stampa la mappa dei colli e dei veicoli associati.
     */

    public void stampaMappaColliVeicoli() {
        for (Map.Entry<Collo, List<Veicolo>> entry : mappaColliInVecoli.entrySet()) {
            Collo collo = entry.getKey();
            List<Veicolo> veicoli = entry.getValue();

            System.out.println("Il Collo: " + collo.getCodice() + "destinato a: " + collo.getDestinatario().getNome() + " si trova:");
            for (Veicolo veicolo : veicoli) {
                System.out.println("\tVeicolo: " + veicolo.getCodice());
            }
        }
    }

    /**
     * Aggiunge un veicolo alla lista dei veicoli gestita dal Corriere.
     * Se nella lista sono già presenti dei veicoli allora imposta il nuovo veicolo come successivo dell'ultimo
     * veicolo già presente nella lista, seguendo la logica del pattern chain of responsibility.
     * @param v Veicolo da aggiungere.
     */

    public void aggiungiVeicolo(Veicolo v) {
        if (!veicoli.isEmpty()) {
            veicoli.get(veicoli.size() - 1).setNext(v);
        }
        veicoli.add(v);
    }

    /**
     * Aggiunge un collo alla lista dei colli gestita dal Corriere e imposta il suo stato iniziale. Inoltre associa il
     * collo al suo destinatario.
     * @param c Collo da aggiungere.
     */
    public void aggiungiCollo(Collo c) {
        colli.add(c);
        c.getDestinatario().aggiungiColliAssociati(c);
        c.setStato(new Ritiro());
    }

    // Metodo per generare un tipo di veicolo casuale dalla enum TipoVeicolo
    private TipoVeicolo generaTipoVeicoloCasuale() {
        Random random = new Random();
        TipoVeicolo[] tipiVeicolo = TipoVeicolo.values();
        return tipiVeicolo[random.nextInt(tipiVeicolo.length)];
    }

    // Metodo per generare una targa casuale
    private String generaTargaCasuale() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Ritorna la mappa dei colli e dei veicoli associati.
     * @return mappaColliInVeicoli mappa dei colli e dei veicoli associati.
     */
    public Map<Collo, List<Veicolo>> getMappaColliInVecoli() {
        return mappaColliInVecoli;
    }

    /**
     * Carica i colli nei veicoli disponibili seguendo una strategia ottimizzata.
     * Questo metodo:
     * Odina la lista dei veicoli e dei colli in maniera decrescente in base alla capienza e al peso, rispettivamente.
     * Prova a inserire ogni collo in un veicolo disponibile utilizzando il pattern chain of responsibility e la tecnica dell'algoritmo next fit.
     * Se un collo non può essere gestito da nessuno dei veicoli esistenti, viene creato un nuovo veicolo.
     * La strategia mira a garantire che i colli siano caricati in modo efficiente.
     */
    public void CaricaColli() {
        int indiceCorrente = 0;
        veicoli.sort(Comparator.comparingInt(Veicolo::getCapienzaContainer).reversed()); //ordino in maniera descrescente per ottimizzare
        colli.sort(Comparator.comparingInt(Collo::getPeso).reversed());
        List<Collo> colliNonGestiti = new ArrayList<>();
        //ottengo l'iteratore della collezione
        Iterator<Collo> iteratorColli = colli.iterator();
        while (iteratorColli.hasNext()) { //restituisce true se la lista ha ancora elementi
            Collo collo = iteratorColli.next(); //restituisce il prossimo elemento
            boolean colloInserito = false; //controllo se sono riuscito a gestire o meno il collo
            for (int i = 0; i < veicoli.size(); i++) {
                Veicolo v = veicoli.get((indiceCorrente + i) % veicoli.size()); //ottengo ciclicamente il prossimo indice
                if (v.puoEssereInserito(collo)) {
                    colloInserito = true;
                    indiceCorrente = (indiceCorrente + i) % veicoli.size();
                    break;
                }
            }
            if (colloInserito) {
                iteratorColli.remove(); //il collo è stato inserito e lo rimuoviamo dalla lista
            } else {
                colliNonGestiti.add(collo);
                iteratorColli.remove();
            }
        }
        if (!colliNonGestiti.isEmpty()) {
            for (Collo c : colliNonGestiti) {
                String targaCasuale = this.generaTargaCasuale();
                TipoVeicolo tipoCasuale = this.generaTipoVeicoloCasuale();
                Veicolo nuovoVeicolo = new Veicolo(targaCasuale, tipoCasuale, c.getPeso(), this);
                veicoli.add(nuovoVeicolo);
                this.inserisciInMappa(c, nuovoVeicolo);
            }
        }

    }
}

