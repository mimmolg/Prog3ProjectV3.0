/**
 * Interfaccia che rappresenta il pattern State per la gestione degli stati di un {@link Collo}.
 * Questa interfaccia definisce i comportamenti che ogni stato specifico del collo deve implementare.
 * @see Collo
 */
public interface StatoCollo {
    /**
     * Avanza il collo fornito al prossimo stato nel suo ciclo di spedizione.
     * @param collo collo il cui stato deve essere avanzato.
     */
    void prossimoStato(Collo collo);

    /**
     * Restituisce una descrizione stringa dello stato corrente del collo fornito.
     * @param collo il collo di cui è richiesta la descrizione dello stato corrente.
     * @return Una stringa che descrive lo stato corrente del collo.
     */
    String stampaStato(Collo collo);
}
