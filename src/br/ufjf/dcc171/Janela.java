package br.ufjf.dcc171;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Janela extends JFrame {
    
        private final JTextField valor;
        private final JTextField cotacao;
        private final JCheckBox chkICMS = new JCheckBox("ICMS");
        private final JCheckBox chkTributo = new JCheckBox("Tributo");
        private final JLabel etiqueta = new JLabel("Digite o valor do produto em dólar:");
        private final JLabel etiqueta2 = new JLabel("Cotação do dólar em relação ao real");
        private JLabel etiqueta3 = new JLabel();
        private final JButton calcula = new JButton("Valor do produto");
        
    public Janela() throws HeadlessException {
        
        super ("Cálculo de valor do produto");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(etiqueta);
        valor = new JTextField(10);
        add(valor);
        add(etiqueta2);
        cotacao = new JTextField(10);
        add(cotacao);
        add(chkICMS);
        add(chkTributo);
        add(calcula);
        add(etiqueta3);
        
        insereValor novoValor = new insereValor();
        chkTributo.addItemListener(novoValor);
        chkICMS.addItemListener(novoValor);
        calcula.addActionListener(novoValor);
    }

    private class insereValor implements ItemListener, ActionListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            calcular();
 
            
          
        }

        private void calcular() throws HeadlessException, NumberFormatException {
            Double valorDolar = Double.parseDouble(valor.getText());
            Double valorCotacao = Double.parseDouble(cotacao.getText());
            Double valorFinal = valorDolar * valorCotacao;
            
            if (chkTributo.isSelected())
            {
                valorFinal = valorFinal + valorFinal * 0.6;
                if (chkICMS.isSelected())
                {
                    valorFinal = valorFinal + valorFinal * 0.18;
                }
            }
            etiqueta3.setText(valorFinal.toString());
            
           // JOptionPane.showMessageDialog(null, "O valor é " + valorFinal, "Valor", JOptionPane.INFORMATION_MESSAGE);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcular();
        }

        
    }
    
}
