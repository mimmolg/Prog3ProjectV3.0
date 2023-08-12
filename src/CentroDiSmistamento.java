import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Rappresenta un centro di smistamento all'interno del sistema di spedizione.
 *
 * <p>
 * Il CentroDiSmistamento agisce come mediatore tra i diversi corrieri e i destinatari.
 * Implementando l'interfaccia {@link Mediator}, offre un punto di accesso centralizzato
 * per ottenere informazioni sullo stato di spedizione dei colli.
 * </p>
 *
 * <p>
 * Nello specifico, quando un destinatario vuole tracciare un collo, invece di comunicare
 * direttamente con un singolo corriere, si interfaccia con il CentroDiSmistamento. Questo
 * approccio elimina la necessità per il destinatario di conoscere direttamente il corriere,
 * riducendo le dipendenze e centralizzando la logica di tracciamento.
 * </p>
 *
 * <p>
 * All'interno del centro di smistamento, i corrieri sono rappresentati come una mappa, permettendo
 * una facile ricerca e tracciamento dei colli attraverso i corrieri associati.
 * </p>
 *
 * @see Mediator
 * @see Corriere
 * @see Collo
 */

public class CentroDiSmistamento implements Mediator {

    private Map<String, Corriere> corrieriInCentro;

    /**
     *Inizializza un nuovo centro di smistamento senza corrieri.
     */
    public CentroDiSmistamento() {
        this.corrieriInCentro = new HashMap<>();
    }

    /**
     * Aggiunge un corriere al centro di smistamento.
     * @param corriere  L'oggetto {@link Corriere} da aggiungere al centro di smistamento.
     */
    public void aggiungiCorriere(Corriere corriere) {
        corrieriInCentro.put(corriere.getNome(), corriere);
    }

    /**
     * Traccia un collo specifico basandosi sul destinatario e sul codice di spedizione.
     *
     * <p>
     * Questo metodo cerca il collo attraverso tutti i corrieri presenti nel centro di smistamento.
     * Se il collo viene trovato, restituisce lo stato corrente del collo.
     * </p>
     *
     * @param destinatario L'oggetto {@link Destinatario} associato al collo.
     * @param codice Il codice di spedizione univoco del collo.
     * @return  Una stringa che rappresenta lo stato del collo o un messaggio di errore se il collo non viene trovato.
     */
    @Override
    public String tracciaCollo(Destinatario destinatario, String codice) {
        for (Corriere corriere : corrieriInCentro.values()) {
            Map<Collo, List<Veicolo>> mappaColliInVecoli = corriere.getMappaColliInVecoli();
            for (Map.Entry<Collo, List<Veicolo>> entry : mappaColliInVecoli.entrySet()) {
                Collo collo = entry.getKey();
                if (collo.getDestinatario().equals(destinatario)) {
                    if (collo.getCodiceSpedizione().equals(codice)) {
                        return collo.stampaStato();
                    }
                }
            }
        }
        return "Collo non trovato per il destinatario ";
    }
}
