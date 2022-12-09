package views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import models.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Produtos extends JDialog {
	private JTextField txtBarcode;
	private JTextField txtCodigo;
	private JTextField txtFornecedor;
	private JTable table;
	private JTextField txtId;
	private JTextField txtProduto;
	private JTextField txtCusto;
	private JTextField txtLucro;
	private JTextField txtFabricante;
	private JTextField txtEstoque;
	private JTextField txtEstoquemin;
	private JTextField txtLocal;
	private JTextArea txtaDescricao;
	private JComboBox cboUnidade;
	private JDateChooser dateEntrada;
	private JDateChooser dateValidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produtos dialog = new Produtos();
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
	public Produtos() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtBarcode.requestFocus();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/favicon.png")));
		setTitle("Produtos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 809, 530);
		getContentPane().setLayout(null);

		dateEntrada = new JDateChooser();
		dateEntrada.setEnabled(false);
		dateEntrada.setBounds(398, 210, 148, 20);
		getContentPane().add(dateEntrada);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(21, 11, 64, 64);
		getContentPane().add(lblNewLabel);

		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Leitor de código de barras
				// Evento ao pressionar uma tecla especifíca
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pesquisarProdutoBarcode();

				}
			}
		});
		txtBarcode.setBounds(108, 39, 208, 20);
		getContentPane().add(txtBarcode);
		txtBarcode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Código");
		lblNewLabel_1.setBounds(21, 101, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(108, 98, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProduto();
			}
		});
		btnPesquisar.setBounds(204, 97, 112, 23);
		getContentPane().add(btnPesquisar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(338, 11, 445, 188);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarFornecedor();
			}
		});
		txtFornecedor.setBounds(10, 34, 120, 20);
		panel.add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Produtos.class.getResource("/img/search.png")));
		lblNewLabel_2.setBounds(140, 32, 24, 24);
		panel.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 425, 112);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] { { "", "" }, { null, null }, { null, null }, },
				new String[] { "ID", "Fornecedor" }));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_3 = new JLabel("Id");
		lblNewLabel_3.setBounds(253, 37, 46, 14);
		panel.add(lblNewLabel_3);

		txtId = new JTextField();
		txtId.setBounds(309, 34, 86, 20);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Produto");
		lblNewLabel_4.setBounds(21, 138, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtProduto = new JTextField();
		txtProduto.setBounds(108, 135, 208, 20);
		getContentPane().add(txtProduto);
		txtProduto.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Descriçao");
		lblNewLabel_5.setBounds(21, 174, 77, 14);
		getContentPane().add(lblNewLabel_5);

		txtaDescricao = new JTextArea();
		txtaDescricao.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, new Color(0, 0, 0)));
		txtaDescricao.setBounds(108, 169, 208, 64);
		getContentPane().add(txtaDescricao);

		JLabel lblNewLabel_6 = new JLabel("Entrada");
		lblNewLabel_6.setBounds(348, 210, 98, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Validade");
		lblNewLabel_7.setBounds(556, 210, 86, 14);
		getContentPane().add(lblNewLabel_7);

		dateValidade = new JDateChooser();
		dateValidade.setBounds(635, 210, 148, 20);
		getContentPane().add(dateValidade);

		JLabel lblNewLabel_8 = new JLabel("Custo");
		lblNewLabel_8.setBounds(348, 257, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtCusto = new JTextField();
		txtCusto.setBounds(401, 254, 105, 20);
		getContentPane().add(txtCusto);
		txtCusto.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Lucro");
		lblNewLabel_9.setBounds(546, 257, 46, 14);
		getContentPane().add(lblNewLabel_9);

		txtLucro = new JTextField();
		txtLucro.setBounds(620, 254, 86, 20);
		getContentPane().add(txtLucro);
		txtLucro.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("%");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_10.setBounds(716, 257, 46, 14);
		getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Fabricante");
		lblNewLabel_11.setBounds(21, 294, 64, 14);
		getContentPane().add(lblNewLabel_11);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(108, 291, 208, 20);
		getContentPane().add(txtFabricante);
		txtFabricante.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Estoque");
		lblNewLabel_12.setBounds(21, 337, 46, 14);
		getContentPane().add(lblNewLabel_12);

		txtEstoque = new JTextField();
		txtEstoque.setBounds(108, 334, 40, 20);
		getContentPane().add(txtEstoque);
		txtEstoque.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Estoque Mínimo");
		lblNewLabel_13.setBounds(164, 337, 103, 14);
		getContentPane().add(lblNewLabel_13);

		txtEstoquemin = new JTextField();
		txtEstoquemin.setBounds(266, 334, 40, 20);
		getContentPane().add(txtEstoquemin);
		txtEstoquemin.setColumns(10);

		cboUnidade = new JComboBox();
		cboUnidade
				.setModel(new DefaultComboBoxModel(new String[] { "", "UN", "PC", "CX", "KG", "M", "Cm", "mm", "g" }));
		cboUnidade.setBounds(95, 385, 53, 22);
		getContentPane().add(cboUnidade);

		JLabel lblNewLabel_14 = new JLabel("Unidade");
		lblNewLabel_14.setBounds(21, 389, 46, 14);
		getContentPane().add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Localização");
		lblNewLabel_15.setBounds(180, 389, 70, 14);
		getContentPane().add(lblNewLabel_15);

		txtLocal = new JTextField();
		txtLocal.setBounds(260, 386, 86, 20);
		getContentPane().add(txtLocal);
		txtLocal.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar Produto");
		btnAdicionar.setIcon(new ImageIcon(Produtos.class.getResource("/img/createP.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirProduto();
			}

		});
		btnAdicionar.setBounds(398, 411, 64, 64);
		getContentPane().add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.setIcon(new ImageIcon(Produtos.class.getResource("/img/boxupdate.png")));
		btnAlterar.setToolTipText("Alterar Produto");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarProduto();
			}
		});
		btnAlterar.setBounds(526, 411, 64, 64);
		getContentPane().add(btnAlterar);

		btnDeletar = new JButton("");
		btnDeletar.setEnabled(false);
		btnDeletar.setContentAreaFilled(false);
		btnDeletar.setBorderPainted(false);
		btnDeletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeletar.setToolTipText("Deletar Produto");
		btnDeletar.setIcon(new ImageIcon(Produtos.class.getResource("/img/deleteP.png")));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletarProduto();
			}
		});
		btnDeletar.setBounds(658, 411, 64, 64);
		getContentPane().add(btnDeletar);

		// Uso da tecla <Enter> junto com um botão
		getRootPane().setDefaultButton(btnPesquisar);

		// uso da biblioteca atx2k para restringir o máximo de caracteres
		// Barcode
		RestrictedTextField barcode = new RestrictedTextField(txtBarcode);
		barcode.setOnlyNums(true);
		barcode.setLimit(255);

		// Código
		RestrictedTextField codigo = new RestrictedTextField(txtCodigo);
		barcode.setOnlyNums(true);

		// Produto
		RestrictedTextField produto = new RestrictedTextField(txtProduto);
		produto.setAcceptSpace(true);
		produto.setLimit(50);

		// Fabricante
		RestrictedTextField fabricante = new RestrictedTextField(txtFabricante);
		fabricante.setAcceptSpace(true);
		fabricante.setLimit(50);

		// Estoque
		RestrictedTextField estoque = new RestrictedTextField(txtEstoque);
		estoque.setLimit(5);
		estoque.setOnlyNums(true);

		// Estoque minímo
		RestrictedTextField estoquemin = new RestrictedTextField(txtEstoquemin);
		estoquemin.setOnlyNums(true);
		estoquemin.setLimit(5);

		// Localização
		RestrictedTextField localizacao = new RestrictedTextField(txtLocal);
		localizacao.setAcceptSpace(true);
		localizacao.setLimit(50);

		// Custo
		RestrictedTextField custo = new RestrictedTextField(txtCusto);
		custo.setOnlyNums(true);
		// custo.setLimit();

		// Lucro
		RestrictedTextField lucro = new RestrictedTextField(txtLucro);
		lucro.setOnlyNums(true);
		lucro.setLimit(50);

	}// Fim do Construtor

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnDeletar;
	private JButton btnPesquisar;

	private void pesquisarFornecedor() {
		String read3 = "select idFor as ID, fantasia as Fornecedor from Fornecedores where fantasia like ?";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read3);
			pst.setString(1, txtFornecedor.getText() + "%");
			ResultSet rs = pst.executeQuery();

			// Uso da biblioteca rs2xml para "popular" a tabela
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisarProduto() {

		// System.out.println("Teste");
		String read = "select * from produtos where codigo = ?";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtCodigo.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				txtBarcode.setText(rs.getString(2));
				txtProduto.setText(rs.getString(3));
				txtaDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
				txtEstoque.setText(rs.getString(8));
				txtEstoquemin.setText(rs.getString(9));
				cboUnidade.setSelectedItem(rs.getString(10));
				txtLocal.setText(rs.getString(11));
				txtId.setText(rs.getString(14));

				btnDeletar.setEnabled(true);
				btnAlterar.setEnabled(true);
				btnAdicionar.setEnabled(false);
				dateEntrada.setEnabled(false);
				dateValidade.setEnabled(false);
				btnPesquisar.setEnabled(false);
				txtCodigo.setEnabled(false);
				txtBarcode.setEnabled(false);
				txtId.setEnabled(false);
				txtFornecedor.setEnabled(false);

				// Formatação da data recebida pelo MYSQL
				// JCalendar - formatação para exibição
				String setarData = rs.getString(6);
				// apoio a lógica
				// System.out.println(setarData);

				Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
				dateEntrada.setDate(dataFormatada);

				String setarData2 = rs.getString(7);
				Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarData2);
				dateValidade.setDate(dataFormatada2);

				txtCusto.setText(rs.getString(13));
				txtLucro.setText(rs.getString(14));

			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				btnAdicionar.setEnabled(true);
				// dateEntrada.setEnabled(true);
				dateValidade.setEnabled(true);
				txtCodigo.setEnabled(false);
				btnPesquisar.setEnabled(false);

			}
			con.close();

		} catch (Exception e) {
			System.out.println();
		}

	}

	private void pesquisarProdutoBarcode() {
		// System.out.println("Teste");
		String read = "select * from produtos where barcode = ?";

		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtBarcode.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				txtCodigo.setText(rs.getString(1));
				txtProduto.setText(rs.getString(3));
				txtaDescricao.setText(rs.getString(4));
				txtFabricante.setText(rs.getString(5));
				txtEstoque.setText(rs.getString(8));
				txtEstoquemin.setText(rs.getString(9));
				cboUnidade.setSelectedItem(rs.getString(10));
				txtLocal.setText(rs.getString(11));
				txtId.setText(rs.getString(14));

				btnDeletar.setEnabled(true);
				btnAlterar.setEnabled(true);
				btnAdicionar.setEnabled(false);
				dateEntrada.setEnabled(false);
				dateValidade.setEnabled(false);
				btnPesquisar.setEnabled(false);
				txtCodigo.setEnabled(false);
				txtBarcode.setEnabled(false);
				txtId.setEnabled(false);
				txtFornecedor.setEnabled(false);

				// Formatação da data recebida pelo MYSQL
				// JCalendar - formatação para exibição
				String setarData = rs.getString(6);
				// apoio a lógica
				// System.out.println(setarData);

				Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd").parse(setarData);
				dateEntrada.setDate(dataFormatada);

				String setarData2 = rs.getString(7);
				Date dataFormatada2 = new SimpleDateFormat("yyyy-MM-dd").parse(setarData2);
				dateValidade.setDate(dataFormatada2);

				txtCusto.setText(rs.getString(13));
				txtLucro.setText(rs.getString(14));

			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");

				btnAdicionar.setEnabled(true);
				txtBarcode.setEnabled(false);
				dateValidade.setEnabled(true);
				txtCodigo.setEnabled(false);
				btnPesquisar.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void inserirProduto() {
		// Validação
		if (txtBarcode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o código de barras");
			txtBarcode.requestFocus();

		} else if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Produto");
			txtProduto.requestFocus();

		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Fabricante");
			txtFabricante.requestFocus();

		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a quantidade do estoque");
			txtEstoque.requestFocus();

		} else if (txtEstoquemin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a quantidade mínima do estoque");
			txtEstoquemin.requestFocus();


		} else if (dateValidade.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Digite a data de Validade");
			dateValidade.requestFocus();

		} else if (txtLocal.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Localização");
			txtLocal.requestFocus();

		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o valor");
			txtCusto.requestFocus();

		} else if (txtLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o lucro desejado");
			txtLucro.requestFocus();

		} else if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do Fornecedor");
		}

		String insert = "insert into produtos (barcode,produto,descricao,fabricante,dataval,estoque,estoquemin,unidade,localizacao,custo,lucro,idFor) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(insert);
			pst.setString(1, txtBarcode.getText());
			pst.setString(2, txtProduto.getText());
			pst.setString(3, txtaDescricao.getText());
			pst.setString(4, txtFabricante.getText());
			// Formatar o valor do JCalendar para inserção correta no banco
			SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
			String dataFormatada = formatador.format(dateValidade.getDate());
			pst.setString(5, dataFormatada);
			pst.setString(6, txtEstoque.getText());
			pst.setString(7, txtEstoquemin.getText());
			pst.setString(8, cboUnidade.getSelectedItem().toString());
			pst.setString(9, txtLocal.getText());
			pst.setString(10, txtCusto.getText());
			pst.setString(11, txtLucro.getText());
			pst.setString(12, txtId.getText());

			int confirma = pst.executeUpdate();

			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Produto casatrado com sucesso");
				limparProdutos();

			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");

			}
			

			con.close();

		} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
			JOptionPane.showMessageDialog(null, "Código de barras já existente");
			txtBarcode.setText(null);
			txtBarcode.requestFocus();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void atualizarProduto() {
		if (txtBarcode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o código de barras");
			txtBarcode.requestFocus();

		} else if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Produto");
			txtProduto.requestFocus();

		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Fabricante");
			txtFabricante.requestFocus();

		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a quantidade do estoque");
			txtEstoque.requestFocus();

		} else if (txtEstoquemin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a quantidade mínima do estoque");
			txtEstoquemin.requestFocus();

		} else if (dateValidade.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Digite a data de Validade");
			dateValidade.requestFocus();

		} else if (txtLocal.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite a Localização");
			txtLocal.requestFocus();

		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o valor");
			txtCusto.requestFocus();

		} else if (txtLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o lucro desejado");
			txtLucro.requestFocus();

		} else if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do Fornecedor");

		} else {
			String update = "update produtos set produto = ?, descricao = ?, fabricante = ?, estoque = ?, estoquemin = ?, unidade = ?, localizacao = ?, custo = ?, lucro = ? where codigo = ?";

			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtProduto.getText());
				pst.setString(2, txtaDescricao.getText());
				pst.setString(3, txtFabricante.getText());
				pst.setString(4, txtEstoque.getText());
				pst.setString(5, txtEstoquemin.getText());
				pst.setString(6, cboUnidade.getSelectedItem().toString());
				pst.setString(7, txtLocal.getText());
				pst.setString(8, txtCusto.getText());
				pst.setString(9, txtLucro.getText());
				pst.setString(10, txtCodigo.getText());

				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
					limparProdutos();

				} else {
					JOptionPane.showMessageDialog(null, "Produto não atualizado");

				}


				con.close();

			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Código de barras já existente");
				txtBarcode.setText(null);
				txtBarcode.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void deletarProduto() {

		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Produto?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from produtos where codigo = ?";

			try {
				Connection con = dao.conectar();

				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtCodigo.getText());

				// executar o comando sql e confirmar a exclusão
				int confirmaExcluir = pst.executeUpdate();
				if (confirmaExcluir == 1) {
					limparProdutos();
					JOptionPane.showMessageDialog(null, "Produto excluído com sucesso");

				}
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	/**
	 * Usado para setar os campos do formulário com os dados da tabela
	 */
	private void setarCampos() {
		int setar = table.getSelectedRow();

		txtId.setText(table.getModel().getValueAt(setar, 0).toString());

	}

	private void limparProdutos() {
		txtCodigo.setText(null);
		txtBarcode.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
		txtEstoque.setText(null);
		txtaDescricao.setText(null);
		txtEstoquemin.setText(null);
		txtProduto.setText(null);
		cboUnidade.setSelectedItem("");
		dateEntrada.setDate(null);
		dateValidade.setDate(null);
		txtFabricante.setText(null);
		txtId.setText(null);
		txtLocal.setText(null);
		txtFornecedor.setText(null);
		((DefaultTableModel) table.getModel()).setRowCount(0);
		dateEntrada.setDate(null);
		dateValidade.setDate(null);
		txtBarcode.setEnabled(true);
		txtCodigo.setEnabled(true);
		txtFornecedor.setEnabled(true);
		txtId.setEnabled(true);
		btnAdicionar.setEnabled(true);
		btnAlterar.setEnabled(false);
		btnDeletar.setEnabled(false);
		btnPesquisar.setEnabled(true);
		dateValidade.setEnabled(true);

	}

}// Fim do Código
