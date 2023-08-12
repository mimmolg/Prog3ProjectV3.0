/**
 * Interfaccia per la gestione dei colli attraverso il pattern Chain of Responsibility.
 *
 * Questa interfaccia definisce metodi per implementare una catena di gestori (veicoli),
 * che determinano se un collo può essere inserito in uno specifico gestore (veicolo) o se la richiesta dovrebbe
 * essere passata al prossimo gestore nella catena.
 *
 */
public interface GestoreColli {
    /**
     * Imposta il prossimo gestore nella catena di responsabilità.
     *
     * @param gestoreColli Il prossimo gestore da aggiungere alla catena di responsabilità.
     */
    void setNext(GestoreColli gestoreColli);


    /**
     * Determina se un dato collo può essere inserito nel gestore corrente.

     * Se il gestore corrente può inserire il collo, ritorna {@code true}.
     * Altrimenti, se esiste un gestore successivo nella catena, la richiesta viene passata
     * al gestore successivo.
     *
     * @param collo Il collo da inserire.
     * @return true se il collo può essere inserito nel gestore corrente o in un gestore successivo;
     *         false altrimenti.
     */
    boolean puoEssereInserito(Collo collo); //next fit
}
