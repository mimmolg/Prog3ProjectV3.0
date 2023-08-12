/**
 * Rappresenta lo stato "InEntrata" per un {@link Collo} all'interno del suo ciclo di spedizione.
 * Quando un {@link Collo} si trova in questo stato, significa che è arrivato all'HUB e dovrebbe andare presto in consegna.
 * Implementando il pattern State attraverso l'interfaccia {@link StatoCollo}, questa classe definisce il comportamento
 * specifico del {@link Collo} quando si trova nello stato di "InEntrata".
 * @see Collo
 * @see StatoCollo
 */
public class InEntrata implements StatoCollo {
    /**
     * Avanza il {@link Collo} fornito al prossimo stato, che in questo caso è "InConsegna"
     * @param collo collo il cui stato deve essere avanzato.
     */
    @Override
    public void prossimoStato(Collo collo) {
        collo.setStato(new InConsegna());

    }

    /**
     * Restituisce una descrizione stringa dello stato "InEntrata" del collo fornito.
     * @param collo il collo di cui è richiesta la descrizione dello stato corrente.
     * @return Una stringa che indica che il collo è arrivato all'HUB e dovrebbe andare presto in consegna.
     */
    @Override
    public String stampaStato(Collo collo) {
        return "Il collo " + collo.getCodice() + " è arrivato all'HUB e dovrebbe andare presto in consegna";
    }
}
