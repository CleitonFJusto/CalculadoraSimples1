import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
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
    public JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setContentPane(new Calculadora().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    public Calculadora() {
        // Inicializando os componentes
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30)); // Fundo escuro

        // Tela de entrada
        txtCalculadora = new JTextField();
        txtCalculadora.setFont(new Font("Arial", Font.BOLD, 30));
        txtCalculadora.setHorizontalAlignment(JTextField.RIGHT);
        txtCalculadora.setEditable(false);
        txtCalculadora.setBackground(Color.BLACK);
        txtCalculadora.setForeground(Color.GREEN); // Texto verde para contraste
        txtCalculadora.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Borda ao redor
        mainPanel.add(txtCalculadora, BorderLayout.NORTH);

        // Painel de botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        panel.setBackground(new Color(45, 45, 45)); // Fundo para botões
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem interna
        mainPanel.add(panel, BorderLayout.CENTER);

        // Lista de botões
        String[] botoes = {
                "7", "8", "9", "-",
                "4", "5", "6", "+",
                "1", "2", "3", "/",
                "C", "0", "=", "*  "
        };

        // Cores para botões
        Color numeroColor = new Color(50, 205, 50); // Verde claro
        Color operadorColor = new Color(70, 130, 180); // Azul médio
        Color igualColor = new Color(255, 69, 0); // Laranja escuro
        Color clearColor = new Color(220, 20, 60); // Vermelho

        // Criando e adicionando os botões
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.PLAIN, 20));
            botao.setForeground(Color.WHITE);
            botao.setFocusPainted(false);

            // Aplicando cores aos botões
            if (texto.matches("\\d")) { // Botões numéricos
                botao.setBackground(numeroColor);
            } else if ("C".equals(texto)) { // Botão Clear
                botao.setBackground(clearColor);
            } else if ("=".equals(texto)) { // Botão Igual
                botao.setBackground(igualColor);
            } else { // Operadores
                botao.setBackground(operadorColor);
            }

            botao.addActionListener(new BotaoListener());
            panel.add(botao);
        }
    }

    private class BotaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            if ("C".equals(comando)) {
                // Limpa o display e reinicia as variáveis
                currentInput.setLength(0);
                txtCalculadora.setText("");
                firstOperand = 0;
                operator = "";
            } else if ("=".equals(comando)) {
                // Realiza o cálculo com o primeiro operando e o valor atual
                try {
                    double secondOperand = Double.parseDouble(currentInput.toString());
                    double resultado = calcular(firstOperand, secondOperand, operator);
                    txtCalculadora.setText(String.valueOf(resultado));
                    currentInput.setLength(0);
                    currentInput.append(resultado);
                    firstOperand = resultado;
                    operator = "";
                } catch (Exception ex) {
                    txtCalculadora.setText("Erro");
                    currentInput.setLength(0);
                }
            } else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
                // Se um operador for pressionado
                if (currentInput.length() > 0) {
                    firstOperand = Double.parseDouble(currentInput.toString()); // Armazena o primeiro operando
                    currentInput.setLength(0); // Limpa a tela para o próximo número
                }
                operator = comando; // Armazena o operador
            } else {
                // Se um número for pressionado, adiciona ao display
                currentInput.append(comando);
                txtCalculadora.setText(currentInput.toString());
            }
        }
    }

    private double calcular(double a, double b, String operador) throws ArithmeticException {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Operação desconhecida");
        }
    }
}
