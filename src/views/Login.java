package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.DAO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// Evento para verificar a conexão
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/favicon.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAcessar = new JButton("Acessar ");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnAcessar.setBounds(10, 206, 89, 23);
		contentPane.add(btnAcessar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(352, 181, 48, 48);
		contentPane.add(lblStatus);

		txtLogin = new JTextField();
		txtLogin.setBounds(78, 12, 86, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(22, 15, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(22, 67, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(78, 64, 125, 20);
		contentPane.add(txtSenha);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnAcessar);

	}// Fim do Construtor

	DAO dao = new DAO();

	private void status() {
		try {

			Connection con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de Conexão!");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/doff.png")));
			} else {
				System.out.println("Banco Conectado com Sucesso!");
				lblStatus.setIcon(new ImageIcon(Usuarios.class.getResource("/img/dbon.png")));
			}
			// Encerrar a conexão (não esquecer)
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}// Fim Do Status

	private void logar() {

		// Validação da senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());

		// Validação de campos obrigatórios
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o seu Login");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {

			JOptionPane.showMessageDialog(null, "Informe sua senha");
			txtSenha.requestFocus();

		} else {
			// Lógica Principal (pesquisar login e senha correspondente)
			String read = "select * from usuarios where login= ? and senha = md5(?)";

			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar os argumentos (?)
				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				// Executar a query e executar o login se existir o login e senha correspondente
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					// System.out.println("Teste Logar");

					Main main = new Main();
					// A linha Abaixo captura o perfil do usuário
					String perfil = rs.getString(5);

					// comportamento de login em funçao do perfil
					if (perfil.equals("admin")) {

						main.setVisible(true);

						// Altera a label da tela principal (inserir o nome de usuário no rodapé)
						// apoio ao entendimento da lógica
						// System.out.println(rs.getString(2));
						main.lblUsuario.setText(rs.getString(2));
						// Habilitar os botões
						main.btnRelatorios.setEnabled(true);
						main.btnUsuarios.setEnabled(true);
						// alterar a cor do rodapé
						main.panelUsuarios.setBackground(Color.RED);
						// fechar o JFrame
						this.dispose();

					} else {
						main.setVisible(true);

						// Altera a label da tela principal (inserir o nome de usuário no rodapé)
						// apoio ao entendimento da lógica
						// System.out.println(rs.getString(2));
						main.lblUsuario.setText(rs.getString(2));

						// Habilitar os botões
						//main.btnRelatorios.setEnabled(true);
						//main.btnUsuarios.setEnabled(true);
						// alterar a cor do rodapé
						main.panelUsuarios.setBackground(Color.BLACK);

						// Fechar o Jframe
						this.dispose();

					}

				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)");
				}
				// Encerrar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}
}
// Fim do Código
