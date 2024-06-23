import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AgeCalculatorGUI extends JFrame {
    private JPanel panel;
    private JTextField birthDateField;
    private JButton calculateButton;
    private JLabel ageLabel;

    public AgeCalculatorGUI() {
        setTitle("Age Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create JPanel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create birth date input field
        birthDateField = new JTextField(20);
        birthDateField.setToolTipText("Enter your birth date (YYYY-MM-DD)");

        // Create calculate button
        calculateButton = new JButton("Calculate Age");
        calculateButton.addActionListener(new CalculateButtonListener());

        // Create age display label
        ageLabel = new JLabel();

        // Add components to the panel
        panel.add(new JLabel("Birth Date:"));
        panel.add(birthDateField);
        panel.add(calculateButton);
        panel.add(ageLabel);

        // Add panel to the frame
        add(panel);
        pack();
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String birthDateStr = birthDateField.getText().trim();
            if (!birthDateStr.isEmpty()) {
                try {
                    LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalDate currentDate = LocalDate.now();
                    Period period = Period.between(birthDate, currentDate);
                    int age = period.getYears();
                    ageLabel.setText("Your age is: " + age + " years");
                } catch (Exception ex) {
                    ageLabel.setText("Invalid birth date format");
                }
            } else {
                ageLabel.setText("Please enter your birth date");
            }
        }
    }

    public static void main(String[] args) {
        new AgeCalculatorGUI();
    }
}
