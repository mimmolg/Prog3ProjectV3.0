/**
 * La classe Veicolo implementa l'interfaccia 'GestoreColli' e rappresenta un veicolo utilizzato per il trasporto dei colli.
 * Contiene informazioni come il codice del veicolo, il tipo, la capienza del container, il carico corrente del veicolo e il
 * successivo gestore di colli della catena della responsabilità {@link GestoreColli}.
 * @see GestoreColli
 * @author Mimmo
 */
public class Veicolo implements GestoreColli {
    private String codice;
    private TipoVeicolo tipo;
    private int capienzaContainer;
    private int caricoCorrente;
    private GestoreColli successivo;
    Corriere corriere;

    /**
     * Inizializza un nuovo oggetto 'Veicolo' con il codice, il tipo, la capienza del container, e un riferimento al
     * Corriere associato, e inizializza il carico corrente uguale a zero.
     * @param codice
     * @param tipo
     * @param capienzaContainer
     * @param corriere
     */

    public Veicolo(String codice, TipoVeicolo tipo, int capienzaContainer, Corriere corriere) {
        this.codice = codice;
        this.tipo = tipo;
        this.capienzaContainer = capienzaContainer;
        this.corriere = corriere;
        this.caricoCorrente = 0;
    }

    /**
     * Imposta il prossimo gestore nella catena di responsabilità.

     * Questo metodo permette di creare una catena di gestori (veicoli) utilizzando il pattern Chain of Responsibility.
     * Ogni gestore (veicolo) nella catena ha la possibilità di gestire una richiesta (inserire un collo) o di
     * passarla al prossimo gestore nella catena.
     *
     * @param gestoreColli Il prossimo gestore (veicolo) da aggiungere alla catena di responsabilità.
     */

    @Override
    public void setNext(GestoreColli gestoreColli) {
        this.successivo = gestoreColli;
    }

    /**
     * Restituisce la capienza del container del Veicolo.
     * @return capienzaContainer
     */
    public int getCapienzaContainer() {
        return capienzaContainer;
    }

    /**
     * Determina se un dato collo può essere inserito nel veicolo corrente.
     *
     *
     * Questo metodo verifica se c'è abbastanza spazio nel veicolo per inserire il collo fornito.
     * Se il veicolo ha abbastanza spazio, il collo viene inserito e la funzione ritorna true.
     * Se il veicolo non ha spazio sufficiente ed esiste un veicolo successivo nella catena di responsabilità,
     * la richiesta viene passata al veicolo successivo.
     * Se non esistono altri veicoli nella catena e il veicolo corrente non può accettare il collo,
     * la funzione ritorna false.
     *
     *
     * @param collo Il collo da inserire.
     * @return  true se il collo può essere inserito nel veicolo corrente o in un veicolo successivo nella catena;
     *          false altrimenti.
     */

    @Override
    public boolean puoEssereInserito(Collo collo) {
        if (collo.getPeso() + caricoCorrente <= capienzaContainer) {
            caricoCorrente += collo.getPeso();
            corriere.inserisciInMappa(collo, this);
            return true;
        } else if (successivo != null) {
            return successivo.puoEssereInserito(collo);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Veicolo{" +
                "codice='" + codice + '\'' +
                ", tipo=" + tipo +
                ", capienzaContainer=" + capienzaContainer +
                ", caricoCorrente=" + caricoCorrente +
                ", successivo=" + successivo +
                '}';
    }

    /**
     * Restituisce il codice del veicolo.
     * @return codice
     */

    public String getCodice() {
        return codice;
    }
}
