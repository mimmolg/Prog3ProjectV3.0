import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Map;
/**
 * La classe {@code WelcomeScreenGUI} rappresenta la schermata di benvenuto
 * dell'applicazione. Questa schermata fornisce un'interfaccia grafica iniziale
 * all'utente con un pulsante per continuare e accedere all'applicazione.
 *
 * <p>Quando l'utente fa clic sul pulsante per continuare, la schermata viene chiusa
 * e viene mostrata la schermata di login. Durante questa transizione, lo stato dei
 * colli associati a ciascun corriere viene anche aggiornato.
 *
 * @see JFrame
 * @see FileManager
 * @see DataSingleton
 * @see LoginScreenGUI
 */
public class WelcomeScreenGUI extends JFrame {
    public WelcomeScreenGUI() {
        setTitle("BENVENUTO!");
        setSize(600, 550);
        ImageIcon icon = new ImageIcon("resource/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Carica l'immagine di sfondo
        ImageIcon backgroundIcon = new ImageIcon("resource/Welcome.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        JButton continueButton = new JButton("CLICCA QUI PER CONTINUARE . . .");
        continueButton.addActionListener(e -> onContinue());
        backgroundLabel.add(continueButton, BorderLayout.SOUTH);
        continueButton.setBackground(new Color(218, 224, 224));
        continueButton.setForeground(Color.BLACK);
        continueButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        // Aggiungi l'etichetta con l'immagine di sfondo alla finestra
        add(backgroundLabel);
        // Mostra la finestra
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onContinue() {
        FileManager fileManager = DataSingleton.getInstance().getFileManager();
        Map<String, Corriere> corriereMap = fileManager.getCorriereMap();
        for (Corriere c : corriereMap.values()) {
            c.scheduleUpdate();
        }
        dispose();
        new LoginScreenGUI();
    }

}
