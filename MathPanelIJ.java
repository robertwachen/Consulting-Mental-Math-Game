import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class MathPanelIJ extends JPanel {
    private static final Font font = new Font("SansSerif", Font.BOLD, 50);

    private State state;
    private final String stateInQuestion;

    private String currentQuestion, currentAnswer;

    private JPanel utilPanel1, utilPanel2;

    private JLabel problem, answer, explanation, status;
    private JTextField textField;
    private JProgressBar progressBar;
    private JButton nextQuestionButton, checkAnswerButton;

    private int correct, max;

    private boolean canIncrement;

    public MathPanelIJ(State state, int max) {
        this.max = max;
        this.correct = 0;
        this.state = state;
        stateInQuestion = state.getInQuestion();
        canIncrement = false;

        this.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        problem = new JLabel();
        this.add(problem, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(589, 16), null, 0, false));

        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        this.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        utilPanel1 = new JPanel();
        utilPanel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        this.add(utilPanel1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

        checkAnswerButton = new JButton("Check Answer");
        utilPanel1.add(checkAnswerButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nextQuestionButton = new JButton("Next Question");
        utilPanel1.add(nextQuestionButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        progressBar = new JProgressBar(0, max);
        this.add(progressBar, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        textField = new JTextField();
        this.add(textField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        utilPanel2 = new JPanel();
        utilPanel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.add(utilPanel2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

        answer = new JLabel();
        utilPanel2.add(answer, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        explanation = new JLabel();
        utilPanel2.add(explanation, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        status = new JLabel();
        this.add(status, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        //action events
        textField.addActionListener(e -> checkAnswer(e.getActionCommand()));
        checkAnswerButton.addActionListener(e -> checkAnswer(e.getActionCommand()));
        nextQuestionButton.addActionListener(e -> nextQuestion());

        registerKeyboardAction(e -> nextQuestion(), KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.META_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        //look and feel


        nextQuestion();
    }

    public MathPanelIJ(State state) {
        this(state, 10);
    }

    private void nextQuestion() {
        long num1, num2;
        switch (state) {
            case MULTIPLY:
                num1 = (int) (((int) (Math.random() * 9.4 + 1) * 5) * (Math.pow(10, (int) (Math.random() * 5) + 1)));
                num2 = (int) (((int) (Math.random() * 9.4 + 1) * 5) * (Math.pow(10, (int) (Math.random() * 7) + 1)));
                currentAnswer = formatNumber((double) num1 * num2);
                break;
            case DIV:
                num1 = (long) (((int) (Math.random() * 98) + 1) * (Math.pow(10, (int) (Math.random() * 4) + 5)));
                num2 = (int) (((int) (Math.random() * 9.4 + 1) * 5) * (Math.pow(10, (int) (Math.random() * 5) + 1)));
                currentAnswer = formatNumber((double) num1 / num2);
                break;
            case PERCENT:
                num1 = (int) (Math.random() * 10) * 5;
                num2 = (long) (((int) (Math.random() * 98) + 1) * (Math.pow(10, (int) (Math.random() * 4) + 5)));
                currentAnswer = formatNumber((double) Math.round((num2 * ((double) num1 / 100)) * 100) / 100);
                break;
            default:
                num1 = 0;
                num2 = 0;
        }
        currentQuestion = "What is " + formatNumber(num1) + " " + stateInQuestion + " " + formatNumber(num2) + "?";
        problem.setText(currentQuestion);
        textField.setText("");
        textField.setEditable(true);
        answer.setText("");
        explanation.setText("");
        progressBar.setValue(correct);
        canIncrement = true;
        status.setText(correct + "/" + max + " CORRECT");
        updateBackground(Color.lightGray);
    }

    private static String formatNumber(double count) {
        if (count < 1000) return String.valueOf(count);
        long num = (long) count;
        int exp = (int) (Math.log(num) / Math.log(1000));
        DecimalFormat format = new DecimalFormat("0.##");
        String value = format.format(num / Math.pow(1000, exp));
        String number = String.format("%s%c", value, "KMBTPE".charAt(exp - 1));
        return number;
    }

    private void updateBackground(Color color) {
        setBackground(color);
        utilPanel1.setBackground(color);
        utilPanel2.setBackground(color);
    }

    public void checkAnswer(String input) {
        if (input.length() > 0 && (isNumeric(input.substring(input.length() - 1))
                || !isNumeric(input.substring(0, input.length() - 1)))) {
            answer.setText("Invalid format");
            explanation.setText("Please try again. Make sure to use units.");
            updateBackground(Color.yellow);
        } else {
            textField.setEditable(false);
            if (input.equals(currentAnswer)) {
                answer.setText("Correct!");
                if (canIncrement) correct++;
                updateBackground(Color.green);
            } else {
                answer.setText("Incorrect.");
                explanation.setText("Problem: " + currentQuestion +
                        "\n Your Answer: " + input +
                        "\n Correct Answer: " + currentAnswer);
                updateBackground(Color.red);
            }
            canIncrement = false;
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
