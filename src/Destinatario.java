import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Rappresenta un destinatario all'interno del sistema di spedizione.
 *
 * <p>
 * Il destinatario è l'entità finale che riceve un collo. Questa classe mantiene informazioni
 * personali come nome, cognome, indirizzo ed email, oltre alle informazioni relative al tracciamento
 * dei colli associati a un particolare destinatario.
 * </p>
 *
 * <p>
 * Per tracciare un collo, il destinatario utilizza un {@link Mediator} per ottenere informazioni
 * sullo stato di spedizione senza dover interagire direttamente con la classe corriere. Questo
 * approccio semplifica la logica di tracciamento e riduce le dipendenze tra le classi.
 * </p>
 *
 * @see Mediator
 * @see Collo
 */
public class Destinatario {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String emaiL;
    private String password;
    private List<Collo> colliAssociati;
    private Mediator mediator;

    /**
     * Crea un nuovo destinatario con i dettagli specificati.
     * @param nome  Il nome del destinatario.
     * @param cognome Il cognome del destinatario.
     * @param indirizzo indirizzo L'indirizzo del destinatario.
     * @param emaiL L'indirizzo email del destinatario.
     * @param password  La password del destinatario per eventuali operazioni autenticate.
     * @param mediator Il mediatore utilizzato per tracciare i colli.
     */
    public Destinatario(String nome, String cognome, String indirizzo, String emaiL, String password, Mediator mediator) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.emaiL = emaiL;
        this.password = password;
        this.mediator = mediator;
        this.colliAssociati = new ArrayList<>();
    }

    /**
     * Restituisce la lista dei colli associati a questo destinatario.
     * @return Una lista di {@link Collo} associati a questo destinatario.
     */
    public List<Collo> getColliAssociati() {
        return colliAssociati;
    }

    /**
     * Aggiunge un nuovo collo alla lista dei colli associati a questo destinatario.
     * @param collo Il {@link Collo} da associare a questo destinatario.
     *
     */
    public void aggiungiColliAssociati(Collo collo) {
        colliAssociati.add(collo);
    }

    /**
     * Ritorna la password del Destinatario.
     * @return password la password del Destinatario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Traccia un collo specifico per questo destinatario utilizzando il mediatore associato.
     * @param codice Il codice di spedizione univoco del collo da tracciare.
     * @return Una stringa che descrive lo stato attuale del collo.
     */
    public String tracciaCollo(String codice) {
        String stato = mediator.tracciaCollo(this, codice);
        return stato;
    }

    /**
     * Ritorna il nome del destinatario.
     * @return nome il nome del destinatario.
     */
    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destinatario that)) return false;
        return Objects.equals(nome, that.nome) && Objects.equals(cognome, that.cognome) && Objects.equals(indirizzo, that.indirizzo) && Objects.equals(emaiL, that.emaiL) && Objects.equals(mediator, that.mediator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cognome, indirizzo, emaiL, mediator);
    }
}
