/**
 * Rappresenta lo stato "InConsegna" per un {@link Collo} all'interno del suo ciclo di spedizione.
 * Quando un {@link Collo} si trova in questo stato, significa che Il Corriere prevede di consegnare il collo nel corso della giornata.
 * Implementando il pattern State attraverso l'interfaccia {@link StatoCollo}, questa classe definisce il comportamento
 * specifico del {@link Collo} quando si trova nello stato di "InConsegna".
 * @see Collo
 * @see StatoCollo
 */
public class InConsegna implements StatoCollo {
    /**
     * Avanza il {@link Collo} fornito al prossimo stato, che in questo caso è "Consegnato"
     * @param collo collo il cui stato deve essere avanzato.
     */
    @Override
    public void prossimoStato(Collo collo) {
        collo.setStato(new Consegnato());
    }

    /**
     * Restituisce una descrizione stringa dello stato "InConsegna" del collo fornito.
     * @param collo il collo di cui è richiesta la descrizione dello stato corrente.
     * @return Una stringa che indica che Il Corriere prevede di consegnare il collo nel corso della giornata.
     */
    @Override
    public String stampaStato(Collo collo) {
        return "Il Corriere prevede di consegnare il collo : " + collo.getCodice() + " nel corso della giornata";
    }
}
