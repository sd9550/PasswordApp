import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    public final static int WIDTH = 450, HEIGHT = 300;
    private final String[] LETTERS_AND_NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private final String[] SPECIAL_CHARACTERS = {"?", "!", "#", "$", "&"};
    private final int PASSWORD_SIZE = 16;
    private JLabel websiteLabel, usernameLabel, passwordLabel, errorLabel;
    private JTextField websiteTextField, usernameTextField, passwordTextField;
    private JButton generateButton, submitButton;

    public Board() {
        loadValues();
        loadGUI();
    }

    private void loadValues() {
        websiteLabel = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        websiteTextField = new JTextField(15);
        usernameTextField = new JTextField(15);
        passwordTextField = new JTextField(15);
        generateButton = new JButton("Generate");
        submitButton = new JButton("Submit");
        websiteLabel.setText("Website");
        usernameLabel.setText("Username");
        passwordLabel.setText("Password");
        generateButton.addActionListener(this);
        submitButton.addActionListener(this);
    }

    private void loadGUI() {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        setFocusable(true);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(websiteLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        add(websiteTextField,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 10;
        add(usernameTextField,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 10;
        add(passwordTextField,gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(generateButton,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(submitButton,gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        add(errorLabel,gbc);
    }

    private void generatePassword() {
        StringBuilder randomPassword = new StringBuilder();
        Random random = new Random();
        int randomIndex;
        for (int i = 0; i < PASSWORD_SIZE; i++) {
            if (i == 4 || i == 8) {
                randomIndex = random.nextInt(SPECIAL_CHARACTERS.length);
                randomPassword.append(SPECIAL_CHARACTERS[randomIndex]);
            } else {
                randomIndex = random.nextInt(LETTERS_AND_NUMBERS.length);
                randomPassword.append(LETTERS_AND_NUMBERS[randomIndex]);
            }
        }
        passwordTextField.setText(randomPassword.toString());
    }

    private void submitData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true));
            writer.append(websiteTextField.getText()).append("\n");
            writer.append(usernameTextField.getText()).append("\n");
            writer.append(passwordTextField.getText()).append("\n");
            writer.close();

            JOptionPane.showMessageDialog(this,
                    websiteTextField.getText() + " was added to data.txt");

            websiteTextField.setText("");
            usernameTextField.setText("");
            passwordTextField.setText("");
        } catch (IOException e) {
            errorLabel.setText("Error: Data file not found");
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == generateButton) {
            generatePassword();
        } else if (source == submitButton) {
            if (websiteTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
                errorLabel.setText("Please enter a value for every field!");
            } else {
                errorLabel.setText("");
                submitData();
            }
        }
    }
}
