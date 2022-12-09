package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Clientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCliente;
	private JTextField txtTelefone;
	private JTextField txtIdC;
	private JTable table;
	private JTextField txtBuscaC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/favicon.png")));
		setBounds(100, 100, 793, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nome");
			lblNewLabel.setBounds(347, 234, 46, 14);
			contentPanel.add(lblNewLabel);
		}

		txtCliente = new JTextField();
		txtCliente.setBounds(421, 231, 189, 20);
		contentPanel.add(txtCliente);
		txtCliente.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Telefone");
		lblNewLabel_1.setBounds(235, 414, 86, 14);
		contentPanel.add(lblNewLabel_1);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(341, 411, 111, 20);
		contentPanel.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Id");
		lblNewLabel_2.setBounds(27, 234, 46, 14);
		contentPanel.add(lblNewLabel_2);

		txtIdC = new JTextField();
		txtIdC.setBounds(114, 231, 86, 20);
		contentPanel.add(txtIdC);
		txtIdC.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Busca Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 757, 194);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 737, 69);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_3 = new JLabel("Cliente");
		lblNewLabel_3.setBounds(10, 29, 128, 14);
		panel.add(lblNewLabel_3);

		txtBuscaC = new JTextField();
		txtBuscaC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarClientes();
			}
		});
		txtBuscaC.setBounds(94, 26, 134, 20);
		panel.add(txtBuscaC);
		txtBuscaC.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("CPF");
		lblNewLabel_4.setBounds(27, 282, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtCpf = new JTextField();
		txtCpf.setBounds(89, 279, 111, 20);
		contentPanel.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("CEP");
		lblNewLabel_5.setBounds(492, 282, 46, 14);
		contentPanel.add(lblNewLabel_5);

		txtCep = new JTextField();
		txtCep.setBounds(527, 279, 108, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);

		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else {
					pesquisarCep();
				}
			}
		});
		btnNewButton.setBounds(645, 278, 111, 23);
		contentPanel.add(btnNewButton);

		JLabel lblNewLabel_6 = new JLabel("Endereço");
		lblNewLabel_6.setBounds(27, 329, 77, 14);
		contentPanel.add(lblNewLabel_6);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(114, 326, 151, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(331, 326, 86, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Número");
		lblNewLabel_7.setBounds(275, 329, 46, 14);
		contentPanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setBounds(448, 329, 110, 14);
		contentPanel.add(lblNewLabel_8);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(597, 326, 159, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Bairro");
		lblNewLabel_9.setBounds(27, 374, 46, 14);
		contentPanel.add(lblNewLabel_9);

		txtBairro = new JTextField();
		txtBairro.setBounds(89, 371, 111, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Cidade");
		lblNewLabel_10.setBounds(250, 374, 46, 14);
		contentPanel.add(lblNewLabel_10);

		txtCidade = new JTextField();
		txtCidade.setBounds(331, 371, 96, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Profissão");
		lblNewLabel_11.setBounds(222, 282, 99, 14);
		contentPanel.add(lblNewLabel_11);

		txtProfissao = new JTextField();
		txtProfissao.setBounds(331, 279, 129, 20);
		contentPanel.add(txtProfissao);
		txtProfissao.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("E-Mail");
		lblNewLabel_12.setBounds(27, 414, 46, 14);
		contentPanel.add(lblNewLabel_12);

		txtEmail = new JTextField();
		txtEmail.setBounds(89, 411, 136, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarCliente();
			}
		});
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Clientes.class.getResource("/img/search-24.png")));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setBounds(222, 231, 24, 24);
		contentPanel.add(btnPesquisar);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnPesquisar);

		// uso da biblioteca atx2k para restringir o máximo de caracteres
		// Nome do clinete
		RestrictedTextField cliente = new RestrictedTextField(txtCliente);
		cliente.setOnlyText(true);
		cliente.setAcceptSpace(true);
		cliente.setLimit(50);

		// Telefone
		RestrictedTextField telefone = new RestrictedTextField(txtTelefone);
		telefone.setOnlyNums(true);
		telefone.setAcceptSpace(true);
		telefone.setLimit(15);

		// CPF
		RestrictedTextField cpf = new RestrictedTextField(txtCpf);
		cpf.setOnlyNums(true);
		cpf.setLimit(20);

		// CEP
		RestrictedTextField ie = new RestrictedTextField(txtCep);
		ie.setOnlyNums(true);
		ie.setLimit(10);

		// Endereço
		RestrictedTextField endereco = new RestrictedTextField(txtEndereco);
		endereco.setAcceptSpace(true);
		endereco.setLimit(50);

		// Numero
		RestrictedTextField numero = new RestrictedTextField(txtNumero);
		numero.setOnlyNums(true);
		numero.setLimit(6);

		// Complemento
		RestrictedTextField complemento = new RestrictedTextField(txtComplemento);
		complemento.setAcceptSpace(true);
		complemento.setLimit(20);

		// Bairro
		RestrictedTextField bairro = new RestrictedTextField(txtBairro);
		bairro.setAcceptSpace(true);
		bairro.setLimit(50);

		// Cidade
		RestrictedTextField cidade = new RestrictedTextField(txtCidade);
		cidade.setAcceptSpace(true);
		cidade.setLimit(50);

		// Email
		RestrictedTextField email = new RestrictedTextField(txtEmail);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/add_icon.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarCliente();
			}
		});
		btnAdicionar.setBounds(494, 414, 64, 64);
		contentPanel.add(btnAdicionar);

		btnAtualizar = new JButton("");
		btnAtualizar.setIcon(new ImageIcon(Clientes.class.getResource("/img/icon-atualizar.png")));
		btnAtualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtualizar.setBorderPainted(false);
		btnAtualizar.setContentAreaFilled(false);
		btnAtualizar.setEnabled(false);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCliente();
			}
		});
		btnAtualizar.setBounds(593, 414, 64, 64);
		contentPanel.add(btnAtualizar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletar.setContentAreaFilled(false);
		btnDeletar.setBorderPainted(false);
		btnDeletar.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete_icon.png")));
		btnDeletar.setEnabled(false);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarCliente();
			}
		});
		btnDeletar.setBounds(692, 414, 64, 64);
		contentPanel.add(btnDeletar);

	}// Fim do Construtor

	DAO dao = new DAO();
	private JTextField txtCpf;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtProfissao;
	private JTextField txtEmail;
	private JButton btnAdicionar;
	private JButton btnAtualizar;
	private JButton btnDeletar;

	private void buscarClientes() {
		String read = "select idCli as ID, nome as Nome, telefone, cpf as CPF from clientes where nome like ?";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtBuscaC.getText() + "%");
			ResultSet rs = pst.executeQuery();

			// Uso da biblioteca rs2xml para popular a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void pesquisarCliente() {

		// Validação
		if (txtIdC.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do Cliente");
			txtIdC.requestFocus();

		} else {
			String read = "select * from clientes where IdCli = ?";

			try {

				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(read);

				pst.setString(1, txtIdC.getText());

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					txtIdC.setText(rs.getString(1));
					txtCliente.setText(rs.getString(2));
					txtTelefone.setText(rs.getString(3));
					txtCpf.setText(rs.getString(4));
					txtProfissao.setText(rs.getString(5));
					txtCep.setText(rs.getString(6));
					txtEndereco.setText(rs.getString(7));
					txtNumero.setText(rs.getString(8));
					txtComplemento.setText(rs.getString(9));
					txtBairro.setText(rs.getString(10));
					txtCidade.setText(rs.getString(11));
					txtEmail.setText(rs.getString(12));

					btnDeletar.setEnabled(true);
					btnAtualizar.setEnabled(true);
					btnAdicionar.setEnabled(false);

				} else {
					JOptionPane.showMessageDialog(null, "Cliente inexistente");

					btnAdicionar.setEnabled(true);
					
				

					//limparCliente();
				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void cadastrarCliente() {
		// Validaçao

		// Validação
		if (txtCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
			txtCliente.requestFocus();

		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CPF");
			txtCpf.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número");
			txtNumero.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CEP");
			txtCep.requestFocus();

		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Telefone");
			txtTelefone.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Endereço");
			txtEndereco.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Bairro");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Cidade");
			txtCidade.requestFocus();

		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o E-mail");
			txtEmail.requestFocus();

		} else if (txtProfissao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a A profissão");
			txtProfissao.requestFocus();

		} else {
			String create = "insert into clientes (nome,telefone,cpf,profissao,cep,endereco,numero,complemento,bairro,cidade,email) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtCliente.getText());
				pst.setString(2, txtTelefone.getText());
				pst.setString(3, txtCpf.getText());
				pst.setString(4, txtProfissao.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, txtEmail.getText());

				int executa = pst.executeUpdate();

				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");

				} else {
					System.out.println("Erro: Cliente não cadastrado");
				}
				limparCliente();
				((DefaultTableModel) table.getModel()).setRowCount(0);
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "CPF já existente");
				txtCpf.setText(null);
				txtCpf.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	void atualizarCliente() {

		if (txtCliente.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
			txtCliente.requestFocus();

		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CPF");
			txtCpf.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número");
			txtNumero.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CEP");
			txtCep.requestFocus();

		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Telefone");
			txtTelefone.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Endereço");
			txtEndereco.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Bairro");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Cidade");
			txtCidade.requestFocus();

		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o E-mail");
			txtEmail.requestFocus();

		} else if (txtProfissao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a A profissão");
			txtProfissao.requestFocus();

		} else {
			String update = "update clientes set nome = ?,telefone = ?,cpf = ?,profissao = ? ,cep = ? ,endereco = ? ,numero = ? ,complemento = ?,bairro = ? ,cidade = ?,email = ? where IdCli = ?";

			try {

				Connection con = dao.conectar();
				// Preparar a query (instrução SQL)
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtCliente.getText());
				pst.setString(2, txtTelefone.getText());
				pst.setString(3, txtCpf.getText());
				pst.setString(4, txtProfissao.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, txtEmail.getText());
				pst.setString(12, txtIdC.getText());

				int executa = pst.executeUpdate();
				// System.out.println(executa);
				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Atualizado com Sucesso!");
					limparCliente();
					((DefaultTableModel) table.getModel()).setRowCount(0);

				} else {
					System.out.println("Cliente não atualizado");
				}
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "CPF já existente");
				txtCpf.setText(null);
				txtCpf.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void deletarCliente() {

		int confirma = JOptionPane.showConfirmDialog(null, "Cornfima excluir este cliente?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idCli = ?";

			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtIdC.getText());

				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limparCliente();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
					((DefaultTableModel) table.getModel()).setRowCount(0);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	private void pesquisarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {

					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}

			txtEndereco.setText(tipoLogradouro + "" + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limparCliente() {
		txtIdC.setText(null);
		txtCliente.setText(null);
		txtCpf.setText(null);
		txtProfissao.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtTelefone.setText(null);
		txtEmail.setText(null);
		txtBuscaC.setText(null);
		txtCliente.requestFocus();
		btnAdicionar.setEnabled(true);
		btnAtualizar.setEnabled(false);
		btnDeletar.setEnabled(false);
		

	}
}// Fim do Código
