import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomInputDialog extends JDialog {
    private JTextField textField;
    private JButton okButton;
    private JButton quitButton;
    private String inputText = null;

    public CustomInputDialog(JFrame parent, String title, String message) {
        super(parent, title, true);
        setLayout(new BorderLayout());

        // Message
        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel(message));
        add(messagePanel, BorderLayout.NORTH);

        // Text field
        textField = new JTextField(20);
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textField);
        add(textFieldPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputText = textField.getText();
                setVisible(false);
            }
        });
        buttonPanel.add(okButton);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quit the application
            }
        });
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
    }

    public String getInputText() {
        return inputText;
    }
}
