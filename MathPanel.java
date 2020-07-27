import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * @deprecated
 */
public class MathPanel extends JPanel {

    //fonts
    private static final Font font = new Font("SansSerif", Font.BOLD, 50);

    private State state;
    private final String stateInQuestion;

    private String currentQuestion;
    private String currentAnswer;

    private JLabel problem, answer, explanation;
    private JTextField textField;
    private JButton next;
    private JButton check;

    public MathPanel(State state) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.state = state;
        stateInQuestion = state.getInQuestion();

        problem = new JLabel("",JLabel.CENTER);
        problem.setFont(font);
        problem.setForeground(Color.blue);

        textField = new JTextField();
        textField.setFont(font);
        textField.setForeground(Color.blue);

        answer = new JLabel("",JLabel.CENTER);
        answer.setForeground(Color.blue);
        answer.setFont(font);
        answer.setVerticalAlignment(SwingConstants.CENTER);

        explanation = new JLabel("",JLabel.CENTER);
        explanation.setForeground(Color.blue);
        explanation.setFont(font);
        explanation.setVerticalAlignment(SwingConstants.CENTER);

        next = new JButton("Next Question");
        next.setFont(font);
        next.setForeground(Color.blue);
        check = new JButton("Check Answer");
        check.setFont(font);
        check.setForeground(Color.blue);

        problem.setSize(new Dimension(750, 150));
        textField.setPreferredSize(new Dimension(750, 150));
        answer.setPreferredSize(new Dimension(750, 150));
        explanation.setPreferredSize(new Dimension(750, 150));
        next.setPreferredSize(new Dimension(750, 150));

        add(problem);
        add(textField);
        add(answer);
        add(explanation);
        add(next);

        textField.addActionListener(e -> checkAnswer(e.getActionCommand()));
        check.addActionListener(e -> checkAnswer(e.getActionCommand()));
        next.addActionListener(e -> nextQuestion());

        nextQuestion();
        ConsultingMath.fitLabel(problem);
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
        answer.setText("");
        explanation.setText("");
        setBackground(Color.lightGray);
        ConsultingMath.fitLabel(problem);
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

    public void checkAnswer(String input) {
        if (isNumeric(input.substring(input.length() - 1))
                || !isNumeric(input.substring(0, input.length() - 1))) {
            answer.setText("Invalid format");
            explanation.setText("Please try again. Make sure to use units.");
            setBackground(Color.yellow);
        } else {
            if (input.equals(currentAnswer)) {
                answer.setText("Correct!");
                setBackground(Color.green);
            } else {
                answer.setText("Incorrect.");
                explanation.setText("Problem: " + currentQuestion +
                        "\n Your Answer: " + input +
                        "\n Correct Answer: " + currentAnswer);
                setBackground(Color.red);
            }
        }
        //ConsultingMath.fitLabel(answer);
        System.out.println(answer.getSize());
        System.out.println(answer.getFont());
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
