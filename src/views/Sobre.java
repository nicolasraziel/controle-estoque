package views;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;


public class Sobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		getContentPane().setForeground(SystemColor.textHighlight);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/favicon.png")));
		setBounds(150, 150, 511, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("@Autor Nicolas Raziel");
		lblNewLabel_1.setBounds(20, 236, 219, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnGitHub = new JButton("");
		btnGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/nicolasraziel");
			}
		});
		btnGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/img/git.png")));
		btnGitHub.setToolTipText("GitHub");
		btnGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGitHub.setBounds(27, 167, 64, 64);
		getContentPane().add(btnGitHub);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Sobre.class.getResource("/img/MIT.png")));
		lblNewLabel_3.setBounds(342, 167, 70, 70);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Copyright (c) <2022>");
		lblNewLabel_4.setBounds(308, 242, 143, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Me chamo Nicolas,");
		lblNewLabel_5.setBounds(10, 58, 475, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Este é um projeto de Estoque com Java e MYSQL ");
		lblNewLabel_6.setBounds(10, 83, 475, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Do curso de T.I do Senac Tatuapé.");
		lblNewLabel_7.setBounds(10, 108, 475, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel = new JLabel("Projeto de Estoque ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 475, 14);
		getContentPane().add(lblNewLabel);
	}//Fim Construtor
	
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}


