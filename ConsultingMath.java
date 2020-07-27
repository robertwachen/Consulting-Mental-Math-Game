import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class ConsultingMath extends JFrame {
    private JMenuBar mb_menu;
    private JMenu m_file, m_help;
    private JMenuItem mi_file_exit, mi_return_title;
    private JMenuItem mi_help_about, mi_help_how;

    private JPanel titlePanel;

    private static final String ABOUT = "hello!!!";
    private static final String HOWTOUSE = "hello!!!";

    public static void main(String[] args) {
        new ConsultingMath();
    }

    public ConsultingMath() {
        System.out.println(Arrays.toString(UIManager.getInstalledLookAndFeels()));
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("CDE/Motif".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            // If Nimbus is not available, you can set the GUI to another look and feel.
//        }
        //UIManager.setLookAndFeel(UIManager.getLookAndFeelDefaults());

        //JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Consulting Math Tool");
        setSize(500, 500);
        setLocationRelativeTo(null);

        //Menu
        mb_menu = new JMenuBar();
        setJMenuBar(mb_menu);
        m_file = new JMenu("File");
        mb_menu.add(m_file);
        m_help = new JMenu("Help");
        mb_menu.add(m_help);
        mi_return_title = new JMenuItem("Return To Title");
        mi_return_title.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.META_DOWN_MASK));
        mi_return_title.addActionListener(e -> setContentPane(titlePanel));
        mi_file_exit = new JMenuItem("Exit");
        mi_file_exit.addActionListener(e -> System.exit(0));
        m_file.add(mi_return_title);
        m_file.add(new JSeparator());
        m_file.add(mi_file_exit);
        mi_help_about = new JMenuItem("About");
        mi_help_about.addActionListener(e -> JOptionPane.showMessageDialog(null, ABOUT, "About", JOptionPane.PLAIN_MESSAGE));
        m_help.add(mi_help_about);
        mi_help_how = new JMenuItem("How To Use");
        mi_help_how.addActionListener(e -> JOptionPane.showMessageDialog(null, HOWTOUSE, "How To Use", JOptionPane.PLAIN_MESSAGE));
        m_help.add(mi_help_how);


        //Title
        titlePanel = createTitlePanel();
        setContentPane(titlePanel);

        //Show
        revalidate();
        setVisible(true);
    }

    private void newMath(State state) {
        setContentPane(new MathPanelIJ(state));
        revalidate();
    }

    private JPanel createTitlePanel() {
        final JPanel panel = new JPanel();

        //look and feel
        final Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        final Font titleFont = new JLabel().getFont().deriveFont(30f);
        final Font subtitleFont = new JLabel().getFont().deriveFont(20f);
        final Font optionsFont = new JLabel().getFont().deriveFont(20f);
        //panel.setBackground(Color.lightGray);

        int totalRows = 6;
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(totalRows, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel titleText = new JLabel("Consulting Mental Math Tool");
        //Font label1Font = this.$$$getFont$$$(null, -1, 24, titleText.getFont());
        //if (label1Font != null) titleText.setFont(label1Font);
        titleText.setFont(titleFont);
        panel.add(titleText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel optionText = new JLabel("Pick which operation you'd like to practice:");
        optionText.setFont(optionsFont);
        panel.add(optionText, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel creditText = new JLabel("Por: Roberto y Noah");
        panel.add(creditText, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        creditText.setFont(subtitleFont);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

        final JPanel buttonPanel = new JPanel();
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
        buttonPanel.setBackground(panel.getBackground());
        //buttonPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 9, new Insets(0, 0, 0, 0), -1, -1));

        //panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 9, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(buttonPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        return panel;
    }

    public static void fitLabel(JLabel label) {
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();

// Pick a new font size so it will not be larger than the height of label.
        float fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the label's font size to the newly determined size.
        label.setFont(labelFont.deriveFont(fontSizeToUse));
    }
}
