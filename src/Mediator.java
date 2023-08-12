/**
 * Fornisce una definizione per il pattern Mediator, permettendo la tracciabilità dei colli.
 * Questa interfaccia rappresenta il componente Mediator del pattern Mediator. Il suo scopo principale è
 * di offrire un punto centrale attraverso il quale i diversi componenti del sistema possono interagire,
 * in particolare per la funzionalità di tracciamento dei colli.
 * Nel contesto del sistema di spedizione, l'interfaccia è utilizzata per permettere ai {@link Destinatario}
 * di tracciare un {@link Collo} senza dover interagire direttamente con le classi che implementano la logica.
 * @see Destinatario
 * @see Collo
 */public interface Mediator {
    /**
     * Permette al destinatario di tracciare uno specifico collo basato sul suo codice di spedizione.
     * @param destinatario destinatario L'oggetto {@link Destinatario} che richiede la tracciatura del pacco.
     * @param codice Il codice di spedizione univoco associato al {@link Collo} da tracciare.
     * @return Una stringa contenente le informazioni di tracciamento relative al collo specificato.
     */
    String tracciaCollo(Destinatario destinatario, String codice); // Metodo usato dal Destinatario per tracciare un Collo
}
