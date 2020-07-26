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

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JLabel titleText = new JLabel("Consulting Mental Math Tool");
        JLabel subtitleText = new JLabel("By: Robert Wachen");
        JLabel operations = new JLabel("Pick which operation you'd like to practice:");

        panel.add(titleText);
        panel.add(subtitleText);
        panel.add(operations);
        panel.add(buttonPanel);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        //titleText.setAlignmentX(Container.CENTER_ALIGNMENT);
        //titleText.setHorizontalAlignment(JLabel.CENTER);

        Font subtitleFont = new Font("SansSerif", Font.BOLD, 20);
        subtitleText.setFont(subtitleFont);
        subtitleText.setForeground(Color.blue);
       // subtitleText.setHorizontalAlignment(SwingConstants.CENTER);

        operations.setFont(new Font("SansSerif", Font.BOLD, 20));
        operations.setForeground(Color.blue);
        //operations.setHorizontalAlignment(SwingConstants.CENTER);
        //operations.setAlignmentY(SwingConstants.CENTER);

        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private JPanel createTitlePanel2() {
        JPanel mainPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel subtitlePanel = new JPanel();
        JPanel operationPanel = new JPanel();
        JPanel operationButtons = new JPanel();
        JLabel titleText = new JLabel("Consulting Mental Math Tool", SwingConstants.CENTER);
        JLabel subtitleText = new JLabel("By: Robert Wachen", SwingConstants.CENTER);
        JLabel operations = new JLabel("Pick which operation you'd like to practice:", SwingConstants.CENTER);

        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        State[] states = State.values();
        JButton[] options = new JButton[states.length];
        for (int i = 0; i < states.length; i++) {
            State state = states[i];
            options[i] = new JButton(state.toString());
            options[i].setFont(buttonFont);
            options[i].setVerticalAlignment(SwingConstants.CENTER);
            options[i].addActionListener(e -> newMath(state));
            operationButtons.add(options[i]);
        }

        //setting design
        mainPanel.setBackground(Color.darkGray);
        operationButtons.setBackground(mainPanel.getBackground());

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); //change eventually
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        subtitlePanel.setLayout(new BoxLayout(subtitlePanel, BoxLayout.Y_AXIS));
        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.Y_AXIS));
        operationButtons.setLayout(new FlowLayout());

        Font font = new Font("SansSerif", Font.BOLD, 30);
        titleText.setFont(font);
        titleText.setForeground(Color.blue);
        titleText.setVerticalAlignment(SwingConstants.CENTER);

        Font subtitleFont = new Font("SansSerif", Font.BOLD, 20);
        subtitleText.setFont(subtitleFont);
        subtitleText.setForeground(Color.blue);
        subtitleText.setVerticalAlignment(SwingConstants.CENTER);

        operations.setFont(new Font("SansSerif", Font.BOLD, 20));
        operations.setForeground(Color.blue);
        operations.setVerticalAlignment(SwingConstants.CENTER);
        operations.setAlignmentY(SwingConstants.CENTER);


        //add to layout
        titlePanel.add(titleText);
        subtitlePanel.add(subtitleText, SwingConstants.CENTER);
        operationPanel.add(operations, SwingConstants.CENTER);

        //add to main panel
        mainPanel.add(titlePanel);
        mainPanel.add(subtitlePanel);
        mainPanel.add(operationPanel);
        mainPanel.add(operationButtons);

        return mainPanel;
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
