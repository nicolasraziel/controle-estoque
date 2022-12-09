package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.sql.Date;
import java.text.DateFormat;
import java.util.Date;
import java.awt.Font;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblStatus;
	JButton btnRelatorios;
	JLabel lblUsuario;
	JButton btnUsuarios;
	JPanel panelUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// Inserir data no rodapé
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblStatus.setText(formatador.format(data));
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/favicon.png")));
		setTitle("Controle de Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(Main.class.getResource("/img/users.png")));
		btnUsuarios.setToolTipText("Usuários");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBounds(40, 11, 128, 128);
		contentPane.add(btnUsuarios);

		JButton btnFornecedores = new JButton("");
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores fornecedores = new Fornecedores();
				fornecedores.setVisible(true);
			}
		});
		btnFornecedores.setIcon(new ImageIcon(Main.class.getResource("/img/supliers.png")));
		btnFornecedores.setToolTipText("Fornecedores");
		btnFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFornecedores.setBounds(40, 161, 128, 128);
		contentPane.add(btnFornecedores);

		JButton btnProdutos = new JButton("");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produtos = new Produtos();
				produtos.setVisible(true);
			}
		});
		btnProdutos.setIcon(new ImageIcon(Main.class.getResource("/img/products.png")));
		btnProdutos.setToolTipText("Produtos");
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setBounds(236, 161, 128, 128);
		contentPane.add(btnProdutos);

		JButton btnClientes = new JButton("");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setIcon(new ImageIcon(Main.class.getResource("/img/clientes.png")));
		btnClientes.setToolTipText("Clientes");
		btnClientes.setBounds(236, 11, 128, 128);
		contentPane.add(btnClientes);

		btnRelatorios = new JButton("");
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios.setEnabled(false);
		btnRelatorios.setIcon(new ImageIcon(Main.class.getResource("/img/report.png")));
		btnRelatorios.setToolTipText("Relatórios");
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setBounds(442, 11, 128, 128);
		contentPane.add(btnRelatorios);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);

			}
		});
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setIcon(new ImageIcon(Main.class.getResource("/img/about.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(442, 161, 128, 128);
		contentPane.add(btnSobre);

		panelUsuarios = new JPanel();
		panelUsuarios.setBackground(SystemColor.textHighlight);
		panelUsuarios.setBounds(0, 315, 606, 62);
		contentPane.add(panelUsuarios);
		panelUsuarios.setLayout(null);

		lblStatus = new JLabel("Data ");
		lblStatus.setForeground(SystemColor.info);
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblStatus.setBounds(0, 0, 289, 62);
		panelUsuarios.add(lblStatus);

		JLabel lblNewLabel = new JLabel("Usuário:");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(280, 25, 95, 14);
		panelUsuarios.add(lblNewLabel);

		lblUsuario = new JLabel("");
		lblUsuario.setForeground(SystemColor.info);
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUsuario.setBounds(335, 25, 193, 14);
		panelUsuarios.add(lblUsuario);
	}// Fim do Construtor
}// Fim do Código
