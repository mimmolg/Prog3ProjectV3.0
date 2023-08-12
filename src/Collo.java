/**
 * La classe 'Collo' rappresenta un collo all'interno del sistema di spedizione.
 * Ogni collo ha un codice univoco, mittente, destinatario, peso, un codice di spedizione associato e uno stato
 * che determina la fase corrente del ciclo di spedizione del collo.
 * Questa classe utilizza il utilizza il pattern State per gestire il suo stato corrente, permettendo una transizione
 * flessibile tra vari stati durante il ciclo di spedizione.
 * Il pattern State consente a un oggetto di cambiare il suo comportamento quando il suo stato interno cambia.
 * In questo contesto, il 'Collo' può trovarsi in vari stati durante il suo ciclo di spedizione (es. Ritiro, InTransito, Consegnato, ecc.)
 * e il comportamento associato a ciascuno stato è definito nelle implementazioni concrete dell'interfaccia {@link StatoCollo}.
 * @see StatoCollo
 */
public class Collo {
    private String codice;
    private String mittente;
    private Destinatario destinatario;
    private int peso;
    private StatoCollo stato;
    private String codiceSpedizione;

    /**
     * Inizializza un nuovo oggetto Collo con i dettagli specificati.
     * @param codice il codice univoco del collo.
     * @param mittente il mittente del collo.
     * @param destinatario un oggetto 'Destinatario' che rappresenta il destinatario del collo.
     * @param peso il peso del collo.
     * @param codiceSpedizione il codice di spedizione univoco associato al collo.
     */

    public Collo(String codice, String mittente, Destinatario destinatario, int peso, String codiceSpedizione) {
        this.codice = codice;
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.peso = peso;
        this.codiceSpedizione = codiceSpedizione;
    }

    /**
     *  Imposta lo stato corrente del collo.
     *  Questo metodo consente di cambiare dinamicamente il comportamento del collo
     *  in base allo stato in cui si trova durante il suo ciclo di spedizione.
     * @param stato Il nuovo stato da impostare per il collo.
     */
    public void setStato(StatoCollo stato) {
        this.stato = stato;
    }

    /**
     *
     * @return codiceSpedizione il codice di spedizione associato al collo.
     */
    public String getCodiceSpedizione() {
        return codiceSpedizione;
    }

    /**
     * Avanza il collo al prossimo stato nel suo ciclo di spedizione.
     *
     */
    public void prossimoStato() {
        stato.prossimoStato(this);
    }

    /**
     *  Restituisce una descrizione stringa dello stato corrente del collo.
     * @return Una stringa che descrive lo stato corrente del collo.
     */
    public String stampaStato() {
        return stato.stampaStato(this);
    }

    /**
     *
     * @return peso il peso associato al collo.
     */
    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "\nCollo{" +
                "codice='" + codice + '\'' +
                ", mittente='" + mittente + '\'' +
                '}' + "   ->" + "Codice Spedizione :" + codiceSpedizione;
    }

    /**
     *
     * @return codice il codice associato al collo.
     */
    public String getCodice() {
        return codice;
    }

    /**
     *
     * @return destinatario il destinatario associato al collo.
     */
    public Destinatario getDestinatario() {
        return destinatario;
    }

}
