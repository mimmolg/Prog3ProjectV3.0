/**
 * Rappresenta lo stato "Ritiro" per un {@link Collo} all'interno del suo ciclo di spedizione.
 * Quando un {@link Collo} si trova in questo stato, significa che è stato ritirato e pronto per essere spedito.
 * Implementando il pattern State attraverso l'interfaccia {@link StatoCollo}, questa classe definisce il comportamento
 * specifico del {@link Collo} quando si trova nello stato di "Ritiro".
 * @see Collo
 * @see StatoCollo
 */
public class Ritiro implements StatoCollo {
    /**
     * Avanza il {@link Collo} fornito al prossimo stato, che in questo caso è "InTransito".
     * @param collo collo il cui stato deve essere avanzato.
     */
    @Override
    public void prossimoStato(Collo collo) {
        collo.setStato(new InTransito());
    }

    /**
     * Restituisce una descrizione stringa dello stato "Ritiro" del collo fornito.
     * @param collo il collo di cui è richiesta la descrizione dello stato corrente.
     * @return Una stringa che indica che il collo è stato ritirato con successo.
     */
    @Override
    public String stampaStato(Collo collo) {
        return "Il collo: " + collo.getCodice() + " è stato ritirato con successo dal Corriere";
    }

}
