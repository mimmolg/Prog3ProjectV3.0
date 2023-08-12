/**
  * Rappresenta i diversi tipi di veicoli utilizzati nel sistema di spedizione.
  * Questa enumerazione definisce i vari tipi di veicoli che possono essere utilizzati per trasportare i {@link Collo} nel sistema.
  * Ogni tipo di veicolo può avere proprietà e capacità di carico diverse, e questa enumerazione serve a categorizzare tali veicoli.
  * Attualmente, sono supportati i seguenti tipi di veicoli: CAMION, FURGONE, e AUTOCARRO.
  * @see Collo
  * @see Veicolo
 */
public enum TipoVeicolo {
    CAMION("CAMION"),
    FURGONE("FURGONE"),
    AUTOCARRO("AUTOCARRO");
    private final String tipo;

    /**
     * Costruttore dell'enumerazione che inizializza il tipo di veicolo con una stringa fornita.
     * @param tipo La rappresentazione stringa del tipo di veicolo.
     */
    TipoVeicolo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Restituisce la rappresentazione stringa del tipo di veicolo.
     * @return tipo Una stringa che rappresenta il tipo di veicolo.
     */
    public String getTipo() {
        return tipo;
    }
}
