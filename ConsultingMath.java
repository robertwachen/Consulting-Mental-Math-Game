import javax.swing.*;
import java.awt.*;

public class ConsultingMath extends JFrame {
    private JMenuBar mb_menu;
    private JMenu m_file, m_help;
    private JMenuItem mi_file_exit, mi_return_title;
    private JMenuItem mi_help_about;

    private JPanel currentPanel;
    private JPanel titlePanel;

    private final String ABOUT = "hello!!!";

    private State state;

    private static JFrame mathFrame = new JFrame();
    private static JPanel mathPanel = new JPanel();
    private static JLabel problem = new JLabel();
    private static JTextField textField = new JTextField();
    private static JLabel answer = new JLabel("");
    private static String[] units = {"K", "M", "B", "T"};
    private static JButton nextQuestion = new JButton("Next Question");


    public static void main(String[] args) {
        new ConsultingMath();
    }

    public ConsultingMath() {
        //JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Consulting Math Tool");
        setSize(750, 750);
        setLocationRelativeTo(null);
        setResizable(false);

        //Menu
        mb_menu = new JMenuBar();
        setJMenuBar(mb_menu);
        m_file = new JMenu("File");
        mb_menu.add(m_file);
        m_help = new JMenu("Help");
        mb_menu.add(m_help);
        mi_return_title = new JMenuItem("New Game");
        mi_return_title.addActionListener(e -> setContentPane(titlePanel));
        mi_file_exit = new JMenuItem("Exit");
        mi_file_exit.addActionListener(e -> System.exit(0));
        m_file.add(mi_return_title);
        m_file.add(new JSeparator());
        m_file.add(mi_file_exit);
        mi_help_about = new JMenuItem("About");
        mi_help_about.addActionListener(e -> JOptionPane.showMessageDialog(null, ABOUT, "About", JOptionPane.PLAIN_MESSAGE));
        m_help.add(mi_help_about);

        titlePanel = createTitlePanel();
        setContentPane(titlePanel);
        revalidate();
        setVisible(true);
        System.out.println("all good");
    }

    public JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JLabel titleText = new JLabel("Consulting Mental Math Tool", JLabel.CENTER);
        JLabel subtitleText = new JLabel("By: Robert Wachen", SwingConstants.CENTER);
        JLabel operations = new JLabel("Pick which operation you'd like to practice:", SwingConstants.CENTER);

        panel.add(titleText);
        panel.add(subtitleText);
        panel.add(operations);
        panel.add(buttonPanel);

        //panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        State[] states = State.values();
        JButton[] options = new JButton[states.length];
        for (int i = 0; i < states.length; i++) {
            State state = states[i];
            options[i] = new JButton(state.toString());
            options[i].setFont(buttonFont);
            options[i].setVerticalAlignment(SwingConstants.CENTER);
            options[i].addActionListener(e -> newMath(state));
            buttonPanel.add(options[i]);
        }

        //setting design
        panel.setBackground(Color.green);
        buttonPanel.setBackground(panel.getBackground());

        Font font = new Font("SansSerif", Font.BOLD, 30);
        titleText.setFont(font);
        titleText.setForeground(Color.blue);
        titleText.setAlignmentX(Container.CENTER_ALIGNMENT);
        //titleText.setHorizontalAlignment(SwingConstants.CENTER);

        Font subtitleFont = new Font("SansSerif", Font.BOLD, 20);
        subtitleText.setFont(subtitleFont);
        subtitleText.setForeground(Color.blue);
       // subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        operations.setFont(new Font("SansSerif", Font.BOLD, 20));
        operations.setForeground(Color.blue);
        //operations.setHorizontalAlignment(SwingConstants.CENTER);
        //operations.setAlignmentY(SwingConstants.CENTER);

        return panel;
    }

    private void newMath(State state) {
        setContentPane(new MathPanel(state));
        revalidate();
    }


    public State getStateOfThis() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static void fitLabel(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        float fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        label.setFont(labelFont.deriveFont(fontSizeToUse));
    }
}
