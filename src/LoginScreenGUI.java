import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
/**
 * La classe {@code LoginScreenGUI} rappresenta la schermata di login dell'applicazione.
 * Gli utenti possono inserire le loro credenziali (email e password) per accedere alle
 * funzionalità dell'applicazione. Questa schermata fornisce campi di testo per l'inserimento
 * delle credenziali e un pulsante per effettuare l'accesso.
 * <p>Se le credenziali fornite sono valide, l'utente viene reindirizzato alla schermata di
 * tracking. In caso contrario, viene mostrato un messaggio di errore.
 * @see JFrame
 * @see FileManager
 * @see DataSingleton
 * @see TrackingScreenGUI
 */
public class LoginScreenGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginScreenGUI() {
        getContentPane().setBackground(new Color(218, 224, 224));
        setTitle("LOGIN");
        ImageIcon icon = new ImageIcon("resource/icon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        setSize(400, 200);

        //Etichetta e campo per l'email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
        emailLabel.setForeground(new Color(4, 18, 64));
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBackground(new Color(218, 224, 224));
        emailField.setFont(new Font("Arial", Font.BOLD, 15));
        add(emailField);

        //Etichetta e campo per la password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 25));
        passwordLabel.setForeground(new Color(4, 18, 64));
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(218, 224, 224));
        passwordField.setFont(new Font("Arial", Font.BOLD, 15));
        add(passwordField);

        //pulsante di login
        JButton loginButton = new JButton("LOGIN");
        loginButton.addActionListener(e -> onLogin());
        add(loginButton);
        loginButton.setBackground(new Color(218, 224, 224));
        loginButton.setForeground((new Color(4, 18, 64)));
        loginButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 20));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onLogin() {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();
        if (validaCredenziali(email, password)) {
            dispose();
            new TrackingScreenGUI(email);
        } else {
            JOptionPane.showMessageDialog(this, "Credenziali Non Valide. Riprova. ");
        }
    }
    /**
     * Valida le credenziali fornite confrontandole con quelle presenti nel sistema.
     *
     * @param email L'indirizzo email fornito dall'utente.
     * @param password La password fornita dall'utente.
     * @return true se le credenziali sono valide, altrimenti false.
     */
    private boolean validaCredenziali(String email, char[] password) {
        //ottengo oggetto filemanager dal singleton
        FileManager fileManager = DataSingleton.getInstance().getFileManager();
        //ottengo la mappa dei destinatari ad ogni email è associato un destinatario dato che l'email è univoca
        Map<String, Destinatario> destinatariMap = fileManager.getDestinatarioMap();
        //Ottengo il destinatario corrispondente all email
        Destinatario destinatario = destinatariMap.get(email);
        //se è null significa che non esiste nessun destinatario con quell email quindi o non esiste o credenziali errate
        if (destinatario == null) {
            return false;
        }
        //ora mi basta confrontare solo la password inserita dato che ho gia fatto sopra il controllo sull username
        String myPassword = destinatario.getPassword();
        return myPassword.equals(new String(password));
    }


}

