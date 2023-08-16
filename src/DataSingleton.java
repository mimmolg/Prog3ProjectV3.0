/**
 * La classe `DataSingleton` implementa il pattern Singleton per garantire che ci sia
 * una sola istanza della classe in tutto il programma e fornisce un punto di accesso
 * globale a questa istanza.
 * Questa classe è responsabile per inizializzare e gestire il {@link FileManager},
 * che si occupa di leggere e interpretare i dati da un file di testo e popolare il
 * centro di smistamento con corrieri, veicoli, destinatari e colli.
 *
 * @see FileManager
 * @see CentroDiSmistamento
 */
public class DataSingleton {
    private static DataSingleton instance;
    private FileManager fileManager;

    /**
     * Costruttore privato per impedire l'istanziazione diretta di oggetti.
     * Inizializza il {@link FileManager} e carica i dati dal file.
     */
    private DataSingleton() {
        CentroDiSmistamento centroDiSmistamento = new CentroDiSmistamento();
        fileManager = new FileManager("resource/DatiLogistica.txt", centroDiSmistamento);
        fileManager.caricaDatiDaFile();
    }

    /**
     * Fornisce l'unico punto di accesso per ottenere l'istanza del Singleton.
     * Se l'istanza non è stata ancora creata, la crea e la restituisce.
     * Altrimenti, restituisce l'istanza esistente.
     *
     * @return L'unica istanza di {@link DataSingleton}.
     */
    public static DataSingleton getInstance() {
        if (instance == null) {
            instance = new DataSingleton();
        }
        return instance;
    }
    /**
     * Restituisce l'oggetto {@link FileManager} associato al Singleton.
     * Il {@link FileManager} è utilizzato per leggere e gestire i dati da un file.
     *
     * @return L'oggetto {@link FileManager}.
     */
    public FileManager getFileManager() {
        return fileManager;
    }
}
