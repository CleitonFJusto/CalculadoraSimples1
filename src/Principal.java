import javax.swing.*;

public class Principal {
    private JButton btn8;
    private JButton btn7;
    private JButton btn4;
    private JButton btn1;
    private JButton btnClear;
    private JButton btn5;
    private JButton btn2;
    private JButton btn0;
    private JButton btn9;
    private JButton btn6;
    private JButton btn3;
    private JButton btnIgual;
    private JButton btnSubtracao;
    private JButton btnAdicao;
    private JButton btnDivisao;
    private JButton btnMultiplicar;
    private JTextField txtCalculadora;
    private StringBuilder currentInput = new StringBuilder();
    private String operator = "";
    private double firstOperand = 0;
    public JPanel mainPanel;  // Variável de instância para o painel principal

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setContentPane(new Principal().mainPanel);  // Usa o painel correto
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
}
