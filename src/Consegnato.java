/**
 * Rappresenta lo stato "Consegnato" per un {@link Collo} all'interno del suo ciclo di spedizione.
 * Quando un {@link Collo} si trova in questo stato, significa che è stato consegnato con successo al destinatario.
 * Implementando il pattern State attraverso l'interfaccia {@link StatoCollo}, questa classe definisce il comportamento
 * specifico del {@link Collo} quando si trova nello stato di "Consegnato".
 * Dato che il "Consegnato" rappresenta l'ultimo stato possibile nel ciclo di spedizione, il metodo {@code prossimoStato}
 * non ha ulteriori transizioni di stato da effettuare.
 * @see Collo
 * @see StatoCollo
 */
public class Consegnato implements StatoCollo {
    /**
     * Poiché "Consegnato" rappresenta l'ultimo stato possibile nel ciclo di spedizione di un {@link Collo},
     * questo metodo non effettua ulteriori transizioni di stato.
     * @param collo Il collo per il quale potrebbe essere necessaria una transizione di stato.
     */
    @Override
    public void prossimoStato(Collo collo) {
        //nessuna transizione
    }
    /**
     * Restituisce una descrizione stringa dello stato "Consegnato" del collo fornito.
     * @param collo l collo di cui è richiesta la descrizione dello stato corrente.
     * @return Una stringa che indica che il collo è stato consegnato con successo al destinatario.
     */
    @Override
    public String stampaStato(Collo collo) {
        return "Il collo " + collo.getCodice() + " è stato correttamente consegnato al cliente";
    }

}
