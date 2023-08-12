import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
/**
 * La classe {@code TrackingScreenGUI} rappresenta la schermata di tracking dell'applicazione.
 * Gli utenti autenticati possono visualizzare la lista dei pacchi associati a loro e
 * tracciare lo stato attuale di una specifica spedizione fornendo un codice di spedizione.
 *
 * <p>La schermata mostra una lista di pacchi associati all'utente e un pannello che consente
 * all'utente di inserire un codice di spedizione e visualizzare lo stato corrente di quella spedizione.
 *
 * <p>La schermata di tracking si interfaccia con il {@link Mediator} attraverso la classe {@link Destinatario}
 * per recuperare lo stato attuale di una spedizione.
 *
 * @see JFrame
 * @see Destinatario
 * @see Mediator
 */
public class TrackingScreenGUI extends JFrame {
    private Destinatario destinatario;
    private JTextField trackingField;
    private JTextArea statusArea;

    /**
     * Costruisce una nuova schermata di tracking. Configura l'aspetto e la disposizione
     * degli elementi all'interno della finestra e imposta le azioni da eseguire in risposta
     * agli eventi dell'utente.
     * @param email L'indirizzo email dell'utente autenticato. Viene utilizzato per recuperare i dati
     * relativi alle spedizioni associate all'utente.
     */
    public TrackingScreenGUI(String email) {
        FileManager fileManager = DataSingleton.getInstance().getFileManager();

        Map<String, Destinatario> destinatariMap = fileManager.getDestinatarioMap();

        destinatario = destinatariMap.get(email);
        if (destinatario == null) {
            JOptionPane.showMessageDialog(this, "Errore nel recuperare i dati del destinatario.");
            return;
        }
        setTitle("Tracking Dei Pacchi");
        ImageIcon icon = new ImageIcon("resource/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        JList<Collo> colloList = new JList<>();
        DefaultListModel<Collo> listModel = new DefaultListModel<>();

        List<Collo> colli = destinatario.getColliAssociati();
        for (Collo collo : colli) {
            listModel.addElement(collo);
        }

        colloList.setModel(listModel);
        JScrollPane scrollPane = new JScrollPane(colloList);
        colloList.setBackground((new Color(218, 224, 224)));
        colloList.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        colloList.setForeground(new Color(4, 18, 64));
        add(scrollPane);

        JPanel trackingPanel = new JPanel();
        trackingField = new JTextField(20);
        trackingField.setFont(new Font("Arial", Font.BOLD, 15));
        trackingPanel.setBackground(new Color(218, 224, 224));
        JButton trackingButton = new JButton("TRACCIA SPEDIZIONE");
        trackingButton.addActionListener(e -> tracciaCollo());
        JLabel codiceSpedizione = new JLabel("INSERISCI CODICE SPEDIZIONE : ");
        trackingPanel.add(codiceSpedizione);
        codiceSpedizione.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
        codiceSpedizione.setForeground(new Color(4, 18, 64));
        trackingButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 13));
        trackingButton.setForeground(new Color(4, 18, 64));
        trackingPanel.add(trackingField);
        trackingPanel.add(trackingButton);

        add(trackingPanel, BorderLayout.NORTH);
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        statusArea.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
        statusArea.setForeground(new Color(212, 5, 17));


        JScrollPane statusScrollPane = new JScrollPane(statusArea);
        add(statusScrollPane, BorderLayout.SOUTH);
        scrollPane.setPreferredSize(new Dimension(1000, 200)); // Imposta la dimensione preferita del tuo JScrollPane
        add(scrollPane, BorderLayout.CENTER); // Aggiungi al centro così riempirà lo spazio disponibile
        statusArea.setPreferredSize(new Dimension(statusArea.getPreferredSize().width, 50)); // Imposta un'altezza preferita
        statusScrollPane.setPreferredSize(new Dimension(statusScrollPane.getPreferredSize().width, 50)); // Imposta un'altezza preferita per lo JScrollPane
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true); // Facoltativo, fa sì che le parole intere vadano a capo invece di spezzarsi

        trackingPanel.setLayout(new FlowLayout()); // Imposta un layout manager per il pannello di tracciamento

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
                System.exit(0);
            }
        });


        pack(); // Chiamata a pack() per adattare la finestra al suo contenuto
        setVisible(true); // Rendi visibile

        setLocationRelativeTo(null);

        setVisible(true);
    }
    /**
     * Questo metodo viene invocato quando l'utente fa clic sul pulsante "TRACCIA SPEDIZIONE" nella
     * schermata di tracking. Recupera lo stato attuale della spedizione e lo visualizza
     * nell'area di stato.
     */
    private void tracciaCollo() {
        String trackingId = trackingField.getText();
        statusArea.setText(destinatario.tracciaCollo(trackingId));
    }
    /**
     * Questo metodo viene invocato quando la finestra di tracking viene chiusa. Si occupa
     * di spegnere (shutdown) tutti i corrieri prima di terminare l'applicazione.
     */
    private void shutdown() {
        FileManager fileManager = DataSingleton.getInstance().getFileManager();
        Map<String, Corriere> corriereMap = fileManager.getCorriereMap();
        for (Corriere c : corriereMap.values()) c.shutdown();
    }
}
