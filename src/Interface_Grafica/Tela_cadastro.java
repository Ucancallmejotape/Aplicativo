package Interface_Grafica;

import java.sql.*;
import java.sql.SQLException;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classe_conexão.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_cadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMatricula;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
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
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Tela de cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Matrícula");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setBounds(10, 163, 91, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 193, 74, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 218, 74, 20);
		contentPane.add(lblNewLabel_2);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(104, 162, 86, 20);
		contentPane.add(tfMatricula);
		tfMatricula.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(104, 193, 117, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(104, 218, 117, 20);
		contentPane.add(pfSenha);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Connection conn = Conexao.faz_conexao();
					String sql = "insert into dados_senhas(matricula, usuario, senha) values(?, ?, ?)";
					
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, tfMatricula.getText());
					stmt.setString(2, tfUsuario.getText());
					stmt.setString(3, new String(pfSenha.getPassword()));
					
					stmt.execute();
					stmt.close();
					conn.close();
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
					tfMatricula.setText("");
					tfUsuario.setText("");
					pfSenha.setText("");
					
					
					
					
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setBounds(248, 227, 117, 23);
		contentPane.add(btnNewButton);
		
		JLabel lbBackGroundTelaCadastro = new JLabel("");
		lbBackGroundTelaCadastro.setIcon(new ImageIcon("C:\\Users\\msjoe\\OneDrive\\Área de Trabalho\\cadastro.png"));
		lbBackGroundTelaCadastro.setBounds(0, 0, 365, 259);
		contentPane.add(lbBackGroundTelaCadastro);
	}
}
