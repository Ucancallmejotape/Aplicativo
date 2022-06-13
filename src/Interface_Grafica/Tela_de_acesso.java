package Interface_Grafica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Classe_conexão.Conexao;
import javax.swing.ImageIcon;

public class Tela_de_acesso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_acesso frame = new Tela_de_acesso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_de_acesso() {
		setResizable(false);
		setTitle("Tela de acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 32, 75, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.ORANGE);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSenha.setBounds(22, 144, 75, 39);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsuario.setBounds(147, 42, 86, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfSenha.setBounds(147, 154, 86, 20);
		contentPane.add(pfSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(Color.ORANGE);
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Connection conn = Conexao.faz_conexao();
					
					String sql = "select * from dados_senhas where usuario = ? and senha = ?";
					 
					PreparedStatement stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String(pfSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						Tela_cadastro exibir = new Tela_cadastro();
						exibir.setVisible(true);
						
						setVisible(false);
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Usuário ou Senha incorreto");
						
					}
					
					stmt.close();
					conn.close();
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnEntrar.setBounds(22, 212, 89, 23);
		contentPane.add(btnEntrar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				
				
			}

			private void limparCampos() {
				tfUsuario.setText("");
				pfSenha.setText("");
				
			}
			
		
		});
		btnLimpar.setForeground(Color.ORANGE);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpar.setBounds(144, 212, 89, 23);
		contentPane.add(btnLimpar);
		
		JLabel lbBackGroundTelaAcesso = new JLabel("");
		lbBackGroundTelaAcesso.setIcon(new ImageIcon("C:\\Users\\msjoe\\OneDrive\\Área de Trabalho\\acesso.png"));
		lbBackGroundTelaAcesso.setBounds(0, 0, 384, 261);
		contentPane.add(lbBackGroundTelaAcesso);
	}
}
