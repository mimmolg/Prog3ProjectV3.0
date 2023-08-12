import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * La classe `FileManager` gestisce le operazioni di lettura e interpretazione dei dati
 * da un file.
 * Questa classe utilizza diverse mappe per associare e recuperare informazioni sui corrieri,
 * veicoli, colli e destinatari in base alle loro chiavi univoche.
 */
public class FileManager {
    private String percorsoFile;
    private CentroDiSmistamento centroDiSmistamento;
    private Map<String, Corriere> corriereMap; //nome
    private Map<String, Veicolo> veicoloMap; //codice
    private Map<String, Collo> colloMap; //codice
    private Map<String, Destinatario> destinatarioMap; //chiave email

    /**
     * Costruttore che inizializza un nuovo oggetto FileManager.
     *
     * @param percorsoFile Il percorso del file da cui leggere i dati.
     * @param centroDiSmistamento Il centro di smistamento (mediator).
     */
    public FileManager(String percorsoFile, CentroDiSmistamento centroDiSmistamento) {
        this.percorsoFile = percorsoFile;
        this.centroDiSmistamento = centroDiSmistamento;
        this.corriereMap = new HashMap<>();
        this.veicoloMap = new HashMap<>();
        this.colloMap = new HashMap<>();
        this.destinatarioMap = new HashMap<>();
    }
    /**
     * Carica i dati dal file specificato nel percorsoFile.
     * Questo metodo legge dai file le varie informazioni sui corrieri, veicoli, destinatari e colli.
     */
    public void caricaDatiDaFile() {
        try (FileReader fileReader = new FileReader(percorsoFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            Corriere corriereCorrente = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parte = line.split(",");
                String tipoInfo = parte[0];
                if (tipoInfo.equals("corriere")) {
                    corriereCorrente = leggiCorriere(parte);
                } else if (tipoInfo.equals("veicolo")) {
                    leggiVeicolo(parte, corriereCorrente);
                } else if (tipoInfo.equals("destinatario")) {
                    leggiDestinatario(parte);
                } else if (tipoInfo.equals("collo")) {
                    leggiCollo(parte, corriereCorrente);
                }
            }
            if (corriereCorrente != null) {
                corriereCorrente.CaricaColli();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Corriere leggiCorriere(String[] parte) {
        String nomeCorriere = parte[1];
        Corriere corriere = new Corriere(nomeCorriere);
        corriereMap.put(nomeCorriere, corriere);
        centroDiSmistamento.aggiungiCorriere(corriere);
        return corriere;
    }

    private void leggiVeicolo(String[] parte, Corriere corriere) {
        String codice = parte[1];
        TipoVeicolo tipoVeicolo = TipoVeicolo.valueOf(parte[2]);
        int capacitaConteiner = Integer.parseInt(parte[3]);
        Veicolo veicolo = new Veicolo(codice, tipoVeicolo, capacitaConteiner, corriere);
        veicoloMap.put(codice, veicolo);
        corriere.aggiungiVeicolo(veicolo);
    }

    private void leggiDestinatario(String[] parte) {
        String nome = parte[1];
        String cognnome = parte[2];
        String indirizzo = parte[3];
        String email = parte[4];
        String password = parte[5];
        Destinatario destinatario = new Destinatario(nome, cognnome, indirizzo, email, password, centroDiSmistamento);
        destinatarioMap.put(email, destinatario);
    }

    private void leggiCollo(String[] parte, Corriere corriere) {
        String codice = parte[1];
        String mittente = parte[2];
        String email = parte[3]; //per indentificare il destinatario
        int peso = Integer.parseInt(parte[4]);
        String codiceSpedizione = parte[5];
        Destinatario destinatario = destinatarioMap.get(email);
        if (destinatario == null) {
            return;
            //destinatario non trovato
        }
        Collo collo = new Collo(codice, mittente, destinatario, peso, codiceSpedizione);
        colloMap.put(codice, collo);
        corriere.aggiungiCollo(collo);
    }
    /**
     * Restituisce una mappa dei corrieri presenti nel sistema.
     * La chiave è il nome del corriere e il valore è l'oggetto {@link Corriere} associato.
     *
     * @return Una mappa dei corrieri.
     */
    public Map<String, Corriere> getCorriereMap() {
        return corriereMap;
    }
    /**
     * Restituisce una mappa dei destinatari presenti nel sistema.
     * La chiave è l'email del destinatario e il valore è l'oggetto {@link Destinatario} associato.
     * @return Una mappa dei destinatari.
     */
    public Map<String, Destinatario> getDestinatarioMap() {
        return destinatarioMap;
    }

}
