package tradutor;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaTradutor {

	private JFrame frame;
	private JTextField textField;
	private JButton button;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JButton button_2;
	private JButton button_3;
	private JLabel label_5;
	private JLabel label_6;
	private JComboBox<String> comboBox;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTradutor window = new TelaTradutor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaTradutor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tradutor");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBounds(74, -7, 84, 89);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(269, -7, 84, 89);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("Digite aqui para traduzir:");
		label_2.setBounds(7, 143, 149, 15);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("");
		label_3.setBounds(196, 12, 41, 60);
		frame.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(153, 142, 159, 18);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("Traduzir");
		button.setBounds(322, 140, 84, 20);
		frame.getContentPane().add(button);
		button.setEnabled(false);

		// Configura os ícones das bandeiras e seta
		ImageIcon iconBR = new ImageIcon(Tradutor.class.getResource("/Imagens/brasil.png"));
		ImageIcon iconEUA = new ImageIcon(Tradutor.class.getResource("/Imagens/EUA.png"));
		ImageIcon iconSeta = new ImageIcon(Tradutor.class.getResource("/Imagens/seta-direita.png"));

		iconBR.setImage(iconBR.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH));
		iconEUA.setImage(iconEUA.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH));
		iconSeta.setImage(iconSeta.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		label.setIcon(iconBR);
		label_1.setIcon(iconEUA);
		label_3.setIcon(iconSeta);
		
		button_2 = new JButton("Clique");
		button_2.setBounds(23, 195, 84, 20);
		frame.getContentPane().add(button_2);
		
		button_3 = new JButton("Clique");
		button_3.setBounds(23, 233, 84, 20);
		frame.getContentPane().add(button_3);
		
		label_5 = new JLabel("Lista de palavras em português");
		label_5.setBounds(7, 176, 206, 15);
		frame.getContentPane().add(label_5);
		
		label_6 = new JLabel("Lista de palavras em inglês");
		label_6.setBounds(8, 217, 155, 15);
		frame.getContentPane().add(label_6);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(165, 84, 104, 23);
		frame.getContentPane().add(comboBox);

		comboBox.addItem("BR  -> EUA");
		comboBox.addItem("EUA -> BR");

		textArea = new JTextArea();
		textArea.setBounds(229, 170, 159, 60);
		frame.getContentPane().add(textArea);
		textArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(229, 170, 159, 60); 

		frame.getContentPane().add(scroll);
		
		// Habilita/desabilita o botão de tradução baseado no conteúdo do campo de texto
		textField.addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
		        if (textField.getText().trim().isEmpty()) {
		            button.setEnabled(false);
		        } else {
		            button.setEnabled(true);
		        }
		    }
		});
		
		// Alterna as bandeiras quando a direção da tradução é alterada
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       String selecionado = (String) comboBox.getSelectedItem(); 
		       
		       if (selecionado.equals("EUA -> BR")) {
		    	   label.setIcon(iconEUA);
		    	   label_1.setIcon(iconBR);
		       	} else {
		   		  label.setIcon(iconBR);
		   		  label_1.setIcon(iconEUA);
		   		}
		   	}
		});

		// Botão de tradução
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tradutor tradutor = new Tradutor();
				String [] partes = textField.getText().trim().split(" "); 
				ArrayList<String> tradução = new ArrayList<>();
				String resultadoTraducao = ""; 
				Boolean temUmaTraducao = false; 
				Boolean temMultiplaTraducao = false; 
				String direcao = (String) comboBox.getSelectedItem();
				boolean isBrParaEua = direcao.equals("BR  -> EUA");
				
				for (String s : partes) {
					ArrayList<String> resultado;
					if (isBrParaEua) {
						resultado = tradutor.toIngles(s);
					} else {
						resultado = tradutor.toPortugues(s);
					}
					if (!resultado.isEmpty()) {
						// Processa palavras com múltiplas traduções
						if (resultado.size() >= 2){
							temMultiplaTraducao = true;
							for (int i = 0; i < resultado.size(); i++) {
								if (i == 0) {
									if (!temUmaTraducao) {
										resultadoTraducao = resultado.get(i);
									} else {
										resultadoTraducao = resultadoTraducao + "/" + resultado.get(i);
									}
								}
								else {
									resultadoTraducao = resultadoTraducao + "/" + resultado.get(i);
								}
							}
						} else {
							if (!temMultiplaTraducao && !temUmaTraducao) {
								resultadoTraducao = resultado.get(0);
								temUmaTraducao = true;
							} else {
								resultadoTraducao = resultadoTraducao + "/" + resultado.get(0);
								temUmaTraducao = true;
							}
						}
					}
				}
				
				if (!resultadoTraducao.isEmpty()) {
					String[] pt2 = resultadoTraducao.split("/");

					if (temUmaTraducao) { 
						ArrayList<String> usadas = new ArrayList<>(); 
						for (int i = 0; i < pt2.length; i++) {
							if (!(i % 2 == 0)) { 
								if (usadas.contains(pt2[i - 1])) { 
									continue;
								}
								usadas.add(pt2[i - 1]);
								tradução.add(pt2[i] + " ");
							}
						}

					} else if (temMultiplaTraducao) { 
						for (int i = 0; i < pt2.length; i++) {
							if (!(i % 2 == 0)) {
								tradução.add(pt2[i] + "\n"); 
							}
						}
					}

						if ((!temUmaTraducao && temMultiplaTraducao) || (temUmaTraducao && tradução.size() == 1)) {
							int n = tradução.size();
							tradução.add(0, "Resultados da pesquisa: " + n + "\n");
							textArea.setText(String.join("", tradução));
						} else {
							tradução.add(0, "Tradução da frase: " + "\n");
							textArea.setText(String.join("", tradução));
						}

				} else {
					textArea.setText("Palavra inválida");
				}
			}
		});
		
		// Botão para listar todas as palavras em português
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tradutor tradutor = new Tradutor ();
	            ArrayList<String> n = new ArrayList<>();
	            n = tradutor.getPortugues();
	            n.add(0, "Resultados da pesquisa: " + n.size());
				textArea.setText(String.join("\n", n));				
			}
		});
		
		// Botão para listar todas as palavras em inglês
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tradutor tradutor = new Tradutor ();
	            ArrayList<String> n = new ArrayList<>();
	            n = tradutor.getIngles();
	            n.add(0, "Resultados da pesquisa: " + n.size());
				textArea.setText(String.join("\n", n));			
			}
		});		
	}
}