/**
 * Rappresenta lo stato "InTransito" per un {@link Collo} all'interno del suo ciclo di spedizione.
 * Quando un {@link Collo} si trova in questo stato, significa che è in Viaggio verso l'HUB più vicino al punto di consegna.
 * Implementando il pattern State attraverso l'interfaccia {@link StatoCollo}, questa classe definisce il comportamento
 * specifico del {@link Collo} quando si trova nello stato di "InTransito".
 * @see Collo
 * @see StatoCollo
 */
public class InTransito implements StatoCollo {
    /**
     * Avanza il {@link Collo} fornito al prossimo stato, che in questo caso è "InEntrata".
     * @param collo collo il cui stato deve essere avanzato.
     */
    @Override
    public void prossimoStato(Collo collo) {
        collo.setStato(new InEntrata());
    }

    /**
     * Restituisce una descrizione stringa dello stato "InTransito" del collo fornito.
     * @param collo il collo di cui è richiesta la descrizione dello stato corrente.
     * @return Una stringa che indica che il collo è in Viaggio verso l'HUB più vicino al punto di consegna.
     */
    @Override
    public String stampaStato(Collo collo) {
        return "Il collo " + collo.getCodice() + " è in Viaggio verso l'HUB più vicino al punto di consegna";
    }

}
