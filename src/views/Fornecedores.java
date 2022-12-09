package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class Fornecedores extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JButton btnPesquisa;
	private JTextField txtCnpj;
	private JTextField txtIe;
	private JTextField txtSite;
	private JTextField txtFone;
	private JTextField txtWhats;
	private JTextField txtContato;
	private JTextField txtEmail;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCreate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Fornecedores dialog = new Fornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Fornecedores() {
		setTitle("Fornecedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedores.class.getResource("/img/favicon.png")));
		setBounds(100, 100, 758, 540);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(5, 187, 46, 14);
		contentPanel.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(29, 184, 115, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		btnPesquisa = new JButton("");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedor();
			}
		});
		btnPesquisa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisa.setToolTipText("Pesquisar");
		btnPesquisa.setBorderPainted(false);
		btnPesquisa.setContentAreaFilled(false);
		btnPesquisa.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/search_icon.png")));
		btnPesquisa.setBounds(154, 165, 64, 64);
		contentPanel.add(btnPesquisa);

		JLabel lblNewLabel_1 = new JLabel("CNPJ");
		lblNewLabel_1.setBounds(228, 187, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtCnpj = new JTextField();
		txtCnpj.setBounds(284, 184, 124, 20);
		contentPanel.add(txtCnpj);
		txtCnpj.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("IE");
		lblNewLabel_2.setBounds(464, 187, 46, 14);
		contentPanel.add(lblNewLabel_2);

		txtIe = new JTextField();
		txtIe.setBounds(547, 184, 86, 20);
		contentPanel.add(txtIe);
		txtIe.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(616, 99, 46, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Razão Social");
		lblNewLabel_4.setBounds(5, 244, 70, 14);
		contentPanel.add(lblNewLabel_4);

		txtRs = new JTextField();
		txtRs.setBounds(85, 241, 86, 20);
		contentPanel.add(txtRs);
		txtRs.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Nome Fantasia");
		lblNewLabel_5.setBounds(203, 244, 107, 14);
		contentPanel.add(lblNewLabel_5);

		txtFantasia = new JTextField();
		txtFantasia.setBounds(299, 241, 138, 20);
		contentPanel.add(txtFantasia);
		txtFantasia.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Site");
		lblNewLabel_6.setBounds(10, 269, 46, 14);
		contentPanel.add(lblNewLabel_6);

		txtSite = new JTextField();
		txtSite.setBounds(47, 266, 146, 20);
		contentPanel.add(txtSite);
		txtSite.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Fone");
		lblNewLabel_7.setBounds(203, 269, 46, 14);
		contentPanel.add(lblNewLabel_7);

		txtFone = new JTextField();
		txtFone.setBounds(254, 266, 116, 20);
		contentPanel.add(txtFone);
		txtFone.setColumns(10);

		txtWhats = new JTextField();
		txtWhats.setBounds(464, 263, 153, 20);
		contentPanel.add(txtWhats);
		txtWhats.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("WhatsApp");
		lblNewLabel_8.setBounds(380, 269, 79, 14);
		contentPanel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Contato");
		lblNewLabel_9.setBounds(464, 244, 46, 14);
		contentPanel.add(lblNewLabel_9);

		txtContato = new JTextField();
		txtContato.setBounds(536, 238, 144, 20);
		contentPanel.add(txtContato);
		txtContato.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("E-Mail");
		lblNewLabel_10.setBounds(10, 305, 46, 14);
		contentPanel.add(lblNewLabel_10);

		txtEmail = new JTextField();
		txtEmail.setBounds(57, 302, 199, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("CEP");
		lblNewLabel_11.setBounds(284, 305, 46, 14);
		contentPanel.add(lblNewLabel_11);

		txtCep = new JTextField();
		txtCep.setBounds(351, 302, 86, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);

		JButton btnNewButton_1 = new JButton("Buscar CEP");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(462, 294, 130, 23);
		contentPanel.add(btnNewButton_1);

		JLabel lblNewLabel_12 = new JLabel("Endereço");
		lblNewLabel_12.setBounds(10, 330, 46, 14);
		contentPanel.add(lblNewLabel_12);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(85, 327, 199, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Número");
		lblNewLabel_13.setBounds(294, 330, 46, 14);
		contentPanel.add(lblNewLabel_13);

		txtNumero = new JTextField();
		txtNumero.setBounds(365, 330, 124, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(614, 327, 86, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Complemento");
		lblNewLabel_14.setBounds(499, 330, 79, 14);
		contentPanel.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Bairro");
		lblNewLabel_15.setBounds(10, 366, 46, 14);
		contentPanel.add(lblNewLabel_15);

		txtBairro = new JTextField();
		txtBairro.setBounds(64, 363, 86, 20);
		contentPanel.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Cidade");
		lblNewLabel_16.setBounds(185, 366, 46, 14);
		contentPanel.add(lblNewLabel_16);

		txtCidade = new JTextField();
		txtCidade.setBounds(254, 363, 116, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("UF");
		lblNewLabel_17.setBounds(380, 366, 46, 14);
		contentPanel.add(lblNewLabel_17);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] { "", "MG", "SP", "RJ" }));
		cboUf.setBounds(446, 358, 64, 22);
		contentPanel.add(cboUf);
		// getContentPane().add(cboUf);

		JLabel lblNewLabel_18 = new JLabel("Observação");
		lblNewLabel_18.setBounds(10, 457, 79, 14);
		contentPanel.add(lblNewLabel_18);

		txtObs = new JTextField();
		txtObs.setBounds(95, 436, 262, 56);
		contentPanel.add(txtObs);
		txtObs.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarFornecedor();
			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setToolTipText("Adicionar");
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/icon-add.png")));
		btnCreate.setBounds(429, 428, 64, 64);
		contentPanel.add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarFornecedor();
			}
		});
		btnUpdate.setToolTipText("Atualizar");
		btnUpdate.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/icon-atualizar.png")));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBounds(514, 428, 64, 64);
		contentPanel.add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarFornecedor();
			}
		});
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/icon-excluir.png")));
		btnDelete.setToolTipText("Excluir");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(598, 428, 64, 64);
		contentPanel.add(btnDelete);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnPesquisa);

		// uso da biblioteca atx2k para restringir o máximo de caracteres
		// nome Fantasia
		RestrictedTextField fornecedor = new RestrictedTextField(txtFantasia);
		fornecedor.setOnlyText(true);
		fornecedor.setAcceptSpace(true);
		fornecedor.setLimit(50);

		// Razao Social
		RestrictedTextField razao = new RestrictedTextField(txtRs);
		razao.setOnlyText(true);
		razao.setAcceptSpace(true);
		razao.setLimit(50);

		// CNPJ
		RestrictedTextField cnpj = new RestrictedTextField(txtCnpj);
		cnpj.setOnlyNums(true);
		cnpj.setLimit(20);

		// IE
		RestrictedTextField ie = new RestrictedTextField(txtIe);
		ie.setOnlyNums(true);
		ie.setLimit(20);

		// CEP
		RestrictedTextField cep = new RestrictedTextField(txtCep);
		cep.setOnlyNums(true);
		cep.setLimit(10);

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

		// Contato
		RestrictedTextField contato = new RestrictedTextField(txtContato);
		contato.setAcceptSpace(true);
		contato.setLimit(30);

		// fone
		RestrictedTextField fone = new RestrictedTextField(txtFone);
		fone.setOnlyNums(true);
		fone.setAcceptSpace(true);
		fone.setLimit(15);

		// Whats
		RestrictedTextField whats = new RestrictedTextField(txtWhats);
		whats.setOnlyNums(true);
		whats.setAcceptSpace(true);
		whats.setLimit(15);

		// Email
		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(50);

		// Obs
		RestrictedTextField obs = new RestrictedTextField(txtObs);
		obs.setLimit(250);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 722, 86);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarFornecedor();
			}
		});
		txtPesquisar.setBounds(154, 11, 86, 20);
		contentPanel.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel("Fornecedor");
		lblNewLabel_19.setBounds(29, 14, 79, 14);
		contentPanel.add(lblNewLabel_19);
	}

	// Objeto
	DAO dao = new DAO();
	private JTextField txtRs;
	private JTextField txtFantasia;
	private JComboBox cboUf;
	private JTextField txtObs;
	private JTable table;
	private JTextField txtPesquisar;

	private void pesquisarFornecedor() {

		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID");
			txtId.requestFocus();

		} else {
			String read = "select * from Fornecedores where idFor = ?";

			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(read);

				pst.setString(1, txtId.getText());

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {

					txtRs.setText(rs.getString(2));
					txtFantasia.setText(rs.getString(3));
					txtCnpj.setText(rs.getString(4));
					txtIe.setText(rs.getString(5));
					txtCep.setText(rs.getString(6));
					txtEndereco.setText(rs.getString(7));
					txtNumero.setText(rs.getString(8));
					txtComplemento.setText(rs.getString(9));
					txtBairro.setText(rs.getString(10));
					txtCidade.setText(rs.getString(11));
					cboUf.setSelectedItem(rs.getString(12));
					txtContato.setText(rs.getString(13));
					txtFone.setText(rs.getString(14));
					txtWhats.setText(rs.getString(15));
					txtEmail.setText(rs.getString(16));
					txtSite.setText(rs.getString(17));
					txtObs.setText(rs.getString(18));

					//// habilitar botões atualizar e deletar
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);
					btnCreate.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor inexistente");
					// Setar campos e botões (UX)
					limparFornecedores();
				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// Fim Pesquisar Fornecedor

	private void cadastrarFornecedor() {
		// Validação
		if (txtRs.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão Social");
			txtRs.requestFocus();
		} else if (txtFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome Fantasia");
			txtFantasia.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número");
			txtNumero.requestFocus();

		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CNPJ");
			txtCnpj.requestFocus();

		} else if (txtIe.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o IE");
			txtIe.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CEP");
			txtCep.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Endereço");
			txtEndereco.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Bairro");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Cidade");
			txtCidade.requestFocus();

		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato");
			txtContato.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Telefone");
			txtFone.requestFocus();

		} else if (txtWhats.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o WhatsApp");
			txtWhats.requestFocus();

		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Email");
			txtEmail.requestFocus();

		} else {
			String create = "insert into Fornecedores (razaoSocial, fantasia, cnpj, ie, cep, endereco, numero, complemento, bairro, cidade, uf, nomeContato, fone, whatsapp, email, site, obs)	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?)";

			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(create);

				// Preparar a query

				pst.setString(1, txtRs.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtCnpj.getText());
				pst.setString(4, txtIe.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, cboUf.getSelectedItem().toString());
				pst.setString(12, txtContato.getText());
				pst.setString(13, txtFone.getText());
				pst.setString(14, txtWhats.getText());
				pst.setString(15, txtEmail.getText());
				pst.setString(16, txtSite.getText());
				pst.setString(17, txtObs.getText());

				int executa = pst.executeUpdate();

				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");

				} else {
					System.out.println("Erro: Fornecedor não cadastrado");
				}
				limparFornecedores();
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado: IE ou CNPJ já existente");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}// Fim cadastrar fornecedores

	private void atualizarFornecedor() {
		if (txtRs.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a razão Social");
			txtRs.requestFocus();
		} else if (txtFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome Fantasia");
			txtFantasia.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o número");
			txtNumero.requestFocus();

		} else if (txtCnpj.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CNPJ");
			txtCnpj.requestFocus();

		} else if (txtIe.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o IE");
			txtIe.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o CEP");
			txtCep.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Endereço");
			txtEndereco.requestFocus();

		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Bairro");
			txtBairro.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Cidade");
			txtCidade.requestFocus();

		} else if (txtContato.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato");
			txtContato.requestFocus();

		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Telefone");
			txtFone.requestFocus();

		} else if (txtWhats.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o WhatsApp");
			txtWhats.requestFocus();

		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Email");
			txtEmail.requestFocus();

		} else {

			String update = "update Fornecedores set razaoSocial = ?, fantasia = ?, cnpj = ? , ie = ?, cep = ?,endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, nomeContato = ?, fone = ?, whatsapp = ?, email = ?, site = ?, obs = ? where idFor = ?";

			try {
				// Abrir a conexão
				Connection con = dao.conectar();
				// Preparar a query (instrução SQL)
				PreparedStatement pst = con.prepareStatement(update);
				// pst.setString(1, txtId.getText());
				pst.setString(1, txtRs.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtCnpj.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtCep.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, txtNumero.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, txtBairro.getText());
				pst.setString(10, txtCidade.getText());
				pst.setString(11, cboUf.getSelectedItem().toString());
				pst.setString(12, txtContato.getText());
				pst.setString(13, txtFone.getText());
				pst.setString(14, txtWhats.getText());
				pst.setString(15, txtEmail.getText());
				pst.setString(16, txtSite.getText());
				pst.setString(17, txtObs.getText());
				pst.setString(18, txtId.getText());

				// Executar a query e confirmar as alterações no banco
				int executa = pst.executeUpdate();
				// System.out.println(executa);
				if (executa == 1) {
					JOptionPane.showMessageDialog(null, "Fornecedor Atualizado com Sucesso!");
					limparFornecedores();
				} else {
					JOptionPane.showMessageDialog(null, "Erro: Fornecedor não atualizado!");
				}
				((DefaultTableModel) table.getModel()).setRowCount(0);
				// Encerrar a conexão
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Fornecedor não cadastrado: IE ou CNPJ já existente");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();

			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	private void deletarFornecedor() {
		// System.out.println("teste do botão excluir");
		// validação (confirmação)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Fornecedor ?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from Fornecedores where idFor = ?";
			try {
				// abrir a conexão
				Connection con = dao.conectar();
				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				// executar o comando sql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limparFornecedores();
					JOptionPane.showMessageDialog(null, "fornecedor excluído com sucesso");
					((DefaultTableModel) table.getModel()).setRowCount(0);

				}
				// encerrar a conexão
				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Fornecedor não excluído devido a existência de produtos no estoque");
				txtCnpj.setText(null);
				txtCnpj.requestFocus();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}// Fim cadastrar fornecedores

	/**
	 * método para pesquisa avançada do fornecedor usando filtro
	 */
	private void buscarFornecedor() {
		String read2 = "select idFor as ID, fantasia as Fornecedor, fone, nomeContato from Fornecedores where fantasia like ?";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();

			// Uso da biblioteca rs2xml para popular a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buscarCep() {
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
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
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

	private void limparFornecedores() {
		txtId.setText(null);
		txtRs.setText(null);
		txtFantasia.setText(null);
		txtCnpj.setText(null);
		txtIe.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtContato.setText(null);
		txtFone.setText(null);
		txtWhats.setText(null);
		txtEmail.setText(null);
		txtSite.setText(null);
		txtObs.setText(null);
		txtPesquisar.setText(null);
		txtId.requestFocus();
		cboUf.setSelectedItem("");

	}
}// Fim do Código
