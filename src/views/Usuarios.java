package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// objetos abaixos serão
	private JTextField txtUsuario;
	private JTextField txtId;
	private JTextField txtLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/favicon.png")));
		setResizable(false);
		setTitle("Usuários");
		setBounds(100, 100, 569, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setBounds(362, 11, 46, 14);
		getContentPane().add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(430, 8, 86, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(362, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(430, 55, 86, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(10, 43, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtLogin = new JTextField();
		txtLogin.setBounds(46, 40, 86, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		btnRead = new JButton("");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnRead.setToolTipText("Pesquisar");
		btnRead.setContentAreaFilled(false);
		btnRead.setBorderPainted(false);
		btnRead.setIcon(new ImageIcon(Usuarios.class.getResource("/img/search_icon.png")));
		btnRead.setBounds(141, 21, 64, 64);
		getContentPane().add(btnRead);

		btnCreate = new JButton("");
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarUsuario();
			}
		});
		btnCreate.setToolTipText("Adicionar");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/icon-add.png")));
		btnCreate.setBounds(265, 170, 64, 64);
		getContentPane().add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarUsuario();
			}
		});
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setIcon(new ImageIcon(Usuarios.class.getResource("/img/icon-excluir.png")));
		btnDelete.setToolTipText("Deletar");
		btnDelete.setBounds(466, 170, 64, 64);
		getContentPane().add(btnDelete);

		btnUpdate = new JButton("");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
				// Verificar se o check está selecionado
				// Para verificar se não está selecionado use NOT
				if (chckSenha.isSelected()) {
					alterarUsuario();

				} else {
					alterarUsuarioSenha();

				}

			}
		});
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setIcon(new ImageIcon(Usuarios.class.getResource("/img/icon-atualizar.png")));
		btnUpdate.setToolTipText("Atualizar");
		btnUpdate.setBounds(367, 170, 64, 64);
		getContentPane().add(btnUpdate);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(362, 98, 46, 14);
		getContentPane().add(lblNewLabel_3);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnRead);

		// uso da biblioteca atx2k para restringir o máximo de caracteres
		// Usuario
		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);
		usuario.setOnlyText(true);
		usuario.setLimit(50);
		usuario.setAcceptSpace(true);

		// Login
		RestrictedTextField login = new RestrictedTextField(txtLogin);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(430, 95, 86, 20);
		getContentPane().add(txtPassword);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(10, 129, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboPerfil.setBounds(46, 125, 86, 22);
		getContentPane().add(cboPerfil);

		chckSenha = new JCheckBox("Alterar senha");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Fazer o Check na caixa JCheckbox
				txtPassword.setEditable(true);
				txtPassword.setText(null);
				txtPassword.requestFocus();
				txtPassword.setBackground(Color.ORANGE);
			}
		});
		chckSenha.setVisible(false);
		chckSenha.setBounds(362, 122, 165, 23);
		getContentPane().add(chckSenha);
		login.setLimit(20);
		// senha.setLimit(250);

	}// Fim do Construtor

	/**
	 * 
	 * Objeto para accesar o método conectar da Classe DAO
	 */
	DAO dao = new DAO();
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCreate;
	private JButton btnRead;
	private JPasswordField txtPassword;
	private JComboBox cboPerfil;
	private JCheckBox chckSenha;
	// Fim objeto

	/**
	 *
	 * Método Pesquisar Usuário por ID (Select)
	 */

	private void pesquisarUsuario() {
		// Validação
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login");
			txtLogin.requestFocus();

		} else {
			String read = "select * from usuarios where login = ?";

			try {
				// Conexão
				Connection con = dao.conectar();
				// Execução
				PreparedStatement pst = con.prepareStatement(read);
				// Preparar o código SQL para a execução
				pst.setString(1, txtLogin.getText());
				// Obter resultados do usuário
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtUsuario.setText(rs.getString(2));
					// txtLogin.setText(rs.getString(3));
					txtPassword.setText(rs.getString(4));
					cboPerfil.setSelectedItem(rs.getString(5));

					// Exibir a caixa chckbox
					chckSenha.setVisible(true);

					// Dersativar a edição da senha
					txtPassword.setEditable(false);

					//// habilitar botões atualizar e deletar
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(null, "Usuario inexistente");
					// Setar campos e botões (UX)
					limpar();
					txtUsuario.setText(null);
					txtLogin.requestFocus(null);
					// txtId.requestFocus();
					btnCreate.setEnabled(true);
					btnRead.setEnabled(false);
					btnDelete.setEnabled(false);

				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}// Fim Método Pesquisar Contato

	void cadastrarUsuario() {
		// Validação
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome");
			txtUsuario.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtLogin.requestFocus();

		} else {
			// Lógica
			String create = "insert into usuarios (usuario,login,senha,perfil) values (?, ?, md5(?),?)";

			try {
				// Abrir Conexão
				Connection con = dao.conectar();

				// Preparar a query e susbstituir os parâmetros (setar as informações)
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				

				// Captura de senha segura
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());

				// Executar a query e confirmar a inserção no banco
				int executa = pst.executeUpdate();

				// apoio ao entendimento da lógica
				// System.out.println(executa);

				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");

				} else {
					System.out.println("Erro: usuário não cadastrado");
				}

				limpar();
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Usuário não adicionado - Login existente");
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}// Fim Cadastrar usuário

	private void alterarUsuario() {
		// System.out.println("Teste botão update usuario");

		// Validação
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login");
			txtLogin.requestFocus();

		} else {

			// Lógica principal
			String update = "update usuarios set usuario = ?, login = ?, perfil = ? where id = ?";

			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// Preparar a query (instrução SQL)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				// pst.setString(3, txtPassword.getText()); (obsoleto)

				pst.setString(3, cboPerfil.getSelectedItem().toString());

				pst.setString(4, txtId.getText());

				// Executar a query e confirmar as alterações no banco
				int executa = pst.executeUpdate();
				// System.out.println(executa);
				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Usuário Atualizado com Sucesso!");
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "Erro: Usuário não cadastrado!");
				}
				// Encerrar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}// Fim Alterar Usuário

	private void alterarUsuarioSenha() {
		// System.out.println("Teste botão update usuario");

		// Validação
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login");
			txtLogin.requestFocus();

		} else {

			// Lógica principal
			String update = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where id = ?";

			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// Preparar a query (instrução SQL)
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				// pst.setString(3, txtPassword.getText()); (obsoleto)

				// Captura de senha segura
				String capturaSenha = new String(txtPassword.getPassword());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboPerfil.getSelectedItem().toString());

				pst.setString(5, txtId.getText());

				// Executar a query e confirmar as alterações no banco
				int executa = pst.executeUpdate();
				// System.out.println(executa);
				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Usuário Atualizado com Sucesso!");
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "Erro: Usuário não cadastrado!");
				}
				// Encerrar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}// Fim Alterar Usuário Senha

	private void deletarUsuario() {

		// System.out.println("Teste deletar usuário");
		// validção
		int executa = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (executa == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where id = ?";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// executar o comando SQL para confirmar a exclusão
				int executaExcluir = pst.executeUpdate();
				if (executaExcluir == 1) {
					limpar();

					JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
					// encerrar a conexão
					con.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}// Fim método Deletar Usuário

	private void limpar() {
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtPassword.setText(null);
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setEditable(false);
		txtId.setText(null);
		chckSenha.setSelected(false); // Desmarcar a caixa check
		chckSenha.setVisible(true);
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);

	}
}// Fim do Código
