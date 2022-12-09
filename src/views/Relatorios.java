package views;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Relatorios extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
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
	public Relatorios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorios.class.getResource("/img/favicon.png")));
		setResizable(false);
		setModal(true);
		setTitle("Relatórios");
		setBounds(100, 100, 734, 467);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("");
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setToolTipText("Reposição");
		btnNewButton.setIcon(new ImageIcon(Relatorios.class.getResource("/img/reposicao.png")));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reposicaoEstoque();
			}
		});
		btnNewButton.setBounds(62, 50, 128, 128);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setToolTipText("Clientes");
		btnNewButton_1.setIcon(new ImageIcon(Relatorios.class.getResource("/img/clientes.png")));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnNewButton_1.setBounds(306, 50, 128, 128);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setIcon(new ImageIcon(Relatorios.class.getResource("/img/expired.png")));
		btnNewButton_2.setToolTipText("Produtos Vencidos");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtosVencidos();
			}
		});
		btnNewButton_2.setBounds(536, 50, 128, 128);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setToolTipText("Inventário");
		btnNewButton_3.setIcon(new ImageIcon(Relatorios.class.getResource("/img/inventory.png")));
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventario();
			}
		});
		btnNewButton_3.setBounds(62, 255, 128, 128);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(Relatorios.class.getResource("/img/pricing.png")));
		btnNewButton_4.setToolTipText("Preço de Vendas");
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				precoVenda();
			}
		});
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setBounds(318, 255, 128, 128);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setIcon(new ImageIcon(Relatorios.class.getResource("/img/products.png")));
		btnNewButton_5.setToolTipText("Produtos");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtos();
			}
		});
		btnNewButton_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_5.setBounds(536, 255, 128, 128);
		getContentPane().add(btnNewButton_5);

	}// Fim do construtor

	DAO dao = new DAO();

	private void reposicaoEstoque() {
		// System.out.println("Teste");

		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("reposicao.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// document.add (adicionar parágrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposição de estoque"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			// Cabeçalho da tabela
			PdfPTable tabela = new PdfPTable(5); // 5 colunas
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Validade"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Estoque mínimo"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);
			// Acessar o banco de dados
			String relReposicao = "select codigo,produto,date_format(dataval,'%d/%m/%Y'), estoque, estoquemin from produtos where estoque < estoquemin";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relReposicao);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco (obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("reposicao.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void relatorioClientes() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Clientes cadastrados"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			String relClientes = "select nome,telefone,cpf,email from clientes";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relClientes);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void produtosVencidos() {
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("produtosvencidos.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Produtos Vencidos"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(5);
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Localização"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Data de Validade"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Dias Vencidos"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);
			// Acessar o banco de dados
			String relVencidos = "select codigo, produto, localizacao, date_format (dataval,'%d/%m/%Y'), datediff(dataval, curdate()) from produtos where datediff(dataval, curdate()) < 0;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relVencidos);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
					tabela.addCell(rs.getString(5));
				}				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			//Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("produtosvencidos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void inventario() {
		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("inventario.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Inventário"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(1);
			PdfPCell col1 = new PdfPCell(new Paragraph("Total"));
			tabela.addCell(col1);
			// Acessar o banco de dados
			String relInventario = "select sum(estoque * custo) from produtos";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relInventario);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("inventario.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	private void precoVenda() {
		// System.out.println("Teste");

		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("precovenda.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// document.add (adicionar parágrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Preço de Vendas"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			// Cabeçalho da tabela
			PdfPTable tabela = new PdfPTable(4); // 4 colunas
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Custo"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Venda"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			String relVenda = "select codigo, produto, custo, (custo + (custo * lucro)/100) from produtos;";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relVenda);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco (obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("precovenda.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	private void produtos() {
		// System.out.println("Teste");

		// criar objeto para construir a página pdf
		Document document = new Document(PageSize.A4.rotate(), 30f, 30f, 20f, 0f);
		// gerar o documento pdf
		try {
			// cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("produtos.pdf"));
			document.open();
			// gerar o conteúdo do documento
			Date data = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			// document.add (adicionar parágrafo)
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Produtos e Fornecedores"));
			document.add(new Paragraph(" "));
			// ... Demais conteúdos (imagem, tabela, gráfico, etc)
			// Cabeçalho da tabela
			PdfPTable tabela = new PdfPTable(4); // 5 colunas
			PdfPCell col1 = new PdfPCell(new Paragraph("Código"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Valor"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Fornecedor"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			String relProduto = "select produtos.codigo, produtos.produto, produtos.custo,Fornecedores.fantasia from produtos inner join Fornecedores on produtos.idFor = Fornecedores.idFor";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(relProduto);
				ResultSet rs = pst.executeQuery();
				// enquanto houver dados na tabela do banco (obter o valor)
				while (rs.next()) {
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
					tabela.addCell(rs.getString(4));
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			// Adicionar a tabela ao documento pdf
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { // executa o código independente do resultado OK ou não
			document.close();
		}

		// abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("produtos.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	

}// Fim do código
