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
	private JComboBox comboBox;
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
		
		comboBox = new JComboBox();
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
		
		textField.addKeyListener(new KeyAdapter() {
		    public void keyReleased(KeyEvent e) {
		        if (textField.getText().trim().isEmpty()) {
		            button.setEnabled(false);
		        } else {
		            button.setEnabled(true);
		        }
		    }
		});
		
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

		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (label.getIcon() == iconBR) {
		            Tradutor tradutor = new Tradutor();
		            String [] partes = textField.getText().trim().split(" ");
		            ArrayList<String> tradução = new ArrayList<>();
		            String sf = "";
					Boolean sfB = false;
					Boolean sfBB = false;
		            
		            
		            for (String s : partes) {
		            	ArrayList<String> resultado = tradutor.toIngles(s);
						System.out.println("Partes 0: " + partes[0]);
						System.out.println("Partes LENGTH: " + partes.length);
						System.out.println("Resultado: " + resultado);
		            	if (!resultado.isEmpty()) {
		            		if (resultado.size() >= 2){
								sfBB = true;
		            			for (int i = 0; i < resultado.size(); i++) {
		            				if (i == 0) {
										if (!sfB) {
		            						sf = resultado.get(i);
										} else {
											sf = sf + "/" + resultado.get(i);
										}
		            				}
		            				else {
		            					sf = sf + "/" + resultado.get(i);
										System.out.println("SF: " + sf);
		            				}
		            			}
		            		} else {
								if (!sfBB && !sfB) {
									sf = resultado.get(0);
								    sfB = true;
								} else {
									sf = sf + "/" + resultado.get(0);
									sfB = true;
								}
		            		}
		            	}
					}
		            
		            if (!sf.isEmpty()) {
						if (sfB) {
							ArrayList<String> usadas = new ArrayList<>();					
							String [] pt2 = sf.split("/");
							for (int i = 0; i < pt2.length; i++) {
								if (!(i % 2 == 0)) {
									if (usadas.contains(pt2[i-1])) {
										continue;
									}
									usadas.add(pt2[i-1]);
									tradução.add(pt2[i] + " ");
									}
								}
						} 
						if (tradução.size() == 1 || !sfB && sfBB){
							String [] pt2 = sf.split("/");
							int n = 0;
							for (int i = 0; i < pt2.length; i++) {
								if (!(i % 2 == 0)) {
									n += 1;
									tradução.add(pt2[i] + "\n");
								}
							}
							tradução.add(0, "Resultados da pesquisa: " + n + "\n");
							textArea.setText(String.join("", tradução));
							} else {
								tradução.add(0, "Tradução da frase: " + "\n");
								textArea.setText(String.join("", tradução));
							}
		            } else {
		            	textArea.setText("Palavra inválida");
		            }


				} else {
		            Tradutor tradutor = new Tradutor();
		            String [] partes = textField.getText().trim().split(" ");
		            ArrayList<String> tradução = new ArrayList<>();
	            	String sf = "";
					Boolean sfB = false;
					Boolean sfBB = false;

		            
		            for (String s : partes) {
		            	ArrayList<String> resultado = tradutor.toPortugues(s);
						System.out.println("Partes 0: " + partes[0]);
						System.out.println("Partes LENGTH: " + partes.length);
						System.out.println("Resultado: " + resultado);
		            	if (!resultado.isEmpty()) {
		            		if (resultado.size() >= 2){
								sfBB = true;
		            			for (int i = 0; i < resultado.size(); i++) {
		            				if (i == 0) {
										if (!sfB) {
		            						sf = resultado.get(i);
										} else {
											sf = sf + "/" + resultado.get(i);
										}
		            				}
		            				else {
		            					sf = sf + "/" + resultado.get(i);
										System.out.println("SF: " + sf);
		            				}
		            			}
		            		} else {
								if (!sfBB && !sfB) {
									sf = resultado.get(0);
									sfB = true;
								} else {
									sf = sf + "/" + resultado.get(0);
									sfB = true;
								}
								System.out.println("S: " + s);
								System.out.println("SF: " + sf);
		            		}
		            	}
					}
		            
		            if (!sf.isEmpty()) {
						if (sfB) {
							ArrayList<String> usadas = new ArrayList<>();					
							String [] pt2 = sf.split("/");
							for (int i = 0; i < pt2.length; i++) {
								if (!(i % 2 == 0)) {
									if (usadas.contains(pt2[i-1])) {
										continue;
									}
									usadas.add(pt2[i-1]);
									tradução.add(pt2[i] + " ");
									}
								}
						} 
						if (tradução.size() == 1 || !sfB && sfBB){
							String [] pt2 = sf.split("/");
							int n = 0;
							for (int i = 0; i < pt2.length; i++) {
								if (!(i % 2 == 0)) {
									n += 1;
									tradução.add(pt2[i] + "\n");
								}
							}
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
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tradutor tradutor = new Tradutor ();
	            ArrayList<String> n = new ArrayList<>();
	            n = tradutor.getPortugues();
	            n.add(0, "Resultados da pesquisa: " + n.size());
				textArea.setText(String.join("\n", n));				
			}
		});
		
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