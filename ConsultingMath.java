import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.spi.NumberFormatProvider;
import java.util.Locale;
import java.util.Random;

public class ConsultingMath {
    private static JFrame mathFrame = new JFrame();
    private static JPanel mathPanel = new JPanel();
    private static JLabel problem = new JLabel();
    private static JTextField textField = new JTextField();
    private static JLabel answer = new JLabel("");
    private static String[] units = {"K", "M", "B", "T"};
    private static JButton nextQuestion = new JButton("Next Question");
    private static String correctAnswer = "";
    private static String question = "";
    private static String game = "";


    public static void main(String[] args) {
       createTitleFrame();
    }

    public static void createTitleFrame() {
        //defining elements
        JFrame titleFrame = new JFrame("Consulting Math Tool");
        JPanel mainPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel subtitlePanel = new JPanel();
        JPanel operationPanel = new JPanel();
        JPanel operationButtons = new JPanel();
        JLabel titleText = new JLabel("Consulting Mental Math Tool", SwingConstants.CENTER);
        JLabel subtitleText = new JLabel("By: Robert Wachen", SwingConstants.CENTER);
        JLabel operations = new JLabel("Pick which operation you'd like to practice:", SwingConstants.CENTER);
        JButton multiply = new JButton();
        JButton divide = new JButton();
        JButton percent = new JButton();

        //adding action listeners
        multiply.addActionListener(e -> {
            game = "multiply";
            createMathFrame();
            multiply();
        });
        divide.addActionListener(e -> {
            game = "divide";
            createMathFrame();
            divide();
        });
        percent.addActionListener(e -> {
            game = "percent";
            createMathFrame();
            percent();
        });

        //adding to layout
        titleFrame.setSize(750, 750);
        mainPanel.setLayout(new FlowLayout()); //change eventually
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        subtitlePanel.setLayout(new BoxLayout(subtitlePanel, BoxLayout.Y_AXIS));
        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.Y_AXIS));
        operationButtons.setLayout(new FlowLayout());

        //setting design
        titleFrame.setBackground(Color.darkGray);

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

        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        multiply.setText("Multiplication");
        multiply.setFont(buttonFont);
        multiply.setVerticalAlignment(SwingConstants.CENTER);
        divide.setText("Division");
        divide.setFont(buttonFont);
        divide.setVerticalAlignment(SwingConstants.CENTER);
        percent.setText("Percents");
        percent.setFont(buttonFont);
        percent.setVerticalAlignment(SwingConstants.CENTER);

        titleFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                int a=JOptionPane.showConfirmDialog(titleFrame,"Are you sure you want to leave?");
                if(a==JOptionPane.YES_OPTION){
                    titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(69);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        //add to layout
        titlePanel.add(titleText);
        subtitlePanel.add(subtitleText, SwingConstants.CENTER);
        operationPanel.add(operations, SwingConstants.CENTER);
        operationButtons.add(multiply);
        operationButtons.add(divide);
        operationButtons.add(percent);

        //add to main panel
        mainPanel.add(titlePanel);
        mainPanel.add(subtitlePanel);
        mainPanel.add(operationPanel);
        mainPanel.add(operationButtons);

        //add to title frame and finish
        titleFrame.add(mainPanel);
        titleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        titleFrame.setVisible(true);
    }

    public static void createMathFrame() {
        Font font = new Font("SansSerif", Font.BOLD, 50);
        problem.setFont(font);
        problem.setForeground(Color.blue);
        problem.setVerticalAlignment(SwingConstants.CENTER);

        textField.setFont(font);
        textField.setForeground(Color.blue);

        answer.setFont(font);
        answer.setForeground(Color.blue);
        answer.setVerticalAlignment(SwingConstants.CENTER);

        nextQuestion.setFont(font);
        nextQuestion.setForeground(Color.blue);

        problem.setPreferredSize(new Dimension(750, 150));
        textField.setPreferredSize(new Dimension(750, 150));
        answer.setPreferredSize(new Dimension(750, 150));
        nextQuestion.setPreferredSize(new Dimension(750, 150));

        mathPanel.add(problem);
        mathPanel.add(textField);
        mathPanel.add(answer);
        mathPanel.add(nextQuestion);

        mathPanel.setLayout(new BoxLayout(mathPanel, BoxLayout.Y_AXIS));
        mathFrame.setLayout(new BoxLayout(mathFrame, BoxLayout.Y_AXIS));
        mathFrame.setBackground(Color.darkGray);
        mathFrame.setContentPane(mathPanel);
        mathFrame.setSize(2500, 750);
        mathFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textField.addActionListener(e -> checkAnswer(e));
        nextQuestion.addActionListener(e -> updateMathFrame(true));

        mathFrame.setVisible(true);
    }

    public static void updateMathFrame(boolean nextProblem) {
        if (nextProblem) {
            if (game.equals("multiply")) { multiply(); }
            else if (game.equals("divide")) { divide(); }
            else if (game.equals("percent")) { percent(); }
        }
        problem.setText(question);
        textField.setText("");
        answer.setText("");
    }

    public static void checkAnswer(ActionEvent e) {
        String theirAnswer = e.getActionCommand();
        if (isNumeric(theirAnswer.substring(theirAnswer.length()-1))
                || !isNumeric(theirAnswer.substring(0, theirAnswer.length()-1))) {
            answer.setText("Invalid format. Please try again. Make sure to use units.");
        } else {
            if (theirAnswer.equals(correctAnswer)) {
                answer.setText("Correct!");
            } else {
                answer.setText("Incorrect.\n Problem: " + question +
                        "\n Your Answer: " + theirAnswer +
                        "\n Correct Answer: " + correctAnswer);
            }
        }
    }

    public static void multiply() {
        int number1 = (int) (((int) (Math.random() * 9.4 + 1) * 5) * (Math.pow(10, (int)(Math.random() * 5) + 1)));
        int number2 = (int) (((int) (Math.random() * 9.4 + 1) * 5) * (Math.pow(10, (int)(Math.random() * 7) + 1)));
        correctAnswer = formatNumber((double) number1 * number2);
        question = "What is " + formatNumber(number1) + " times " + formatNumber(number2) + "?";
        updateMathFrame(false);
    }

    public static void divide() {
        long bigNumber = (long)(((int)(Math.random() * 98) + 1) * (Math.pow(10, (int)(Math.random() * 4) + 5)));
        int littleNumber = (int) (((int) (Math.random() * 9.4 + 1) * 5) * (Math.pow(10, (int)(Math.random() * 5) + 1)));
        correctAnswer = formatNumber((double) bigNumber / littleNumber);
        question = "What is " + formatNumber(bigNumber) + " divided by " + formatNumber(littleNumber) + "?";
        updateMathFrame(false);
    }

    public static void percent() {
        int percent = (int)(Math.random() * 10) * 5;
        long bigNumber = (long)(((int)(Math.random() * 98) + 1) * (Math.pow(10, (int)(Math.random() * 4) + 5)));
        correctAnswer = formatNumber((double) Math.round((bigNumber * ((double) percent/100)) * 100) / 100);
        question = "What is " + percent + " percent of " + formatNumber(bigNumber) + "?";
        updateMathFrame(false);
    }

    public static String formatNumber(double count) {
        if (count < 1000) return "" + count;
        long num = (long) count;
        int exp = (int) (Math.log(num) / Math.log(1000));
        DecimalFormat format = new DecimalFormat("0.##");
        String value = format.format(num / Math.pow(1000, exp));
        String number = String.format("%s%c", value, "KMBTPE".charAt(exp - 1));
        return number;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
