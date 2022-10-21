package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Agenda {

	private JFrame frmAgendaDeContatos;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda window = new Agenda();
					window.frmAgendaDeContatos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Agenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgendaDeContatos = new JFrame();
		frmAgendaDeContatos.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		frmAgendaDeContatos
				.setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda.class.getResource("/img/favicon.png")));
		frmAgendaDeContatos.setResizable(false);
		frmAgendaDeContatos.setTitle("Agenda de Contatos");
		frmAgendaDeContatos.setBounds(100, 100, 450, 300);
		frmAgendaDeContatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendaDeContatos.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(355, 33, 11, 14);
		frmAgendaDeContatos.getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(376, 30, 36, 20);
		frmAgendaDeContatos.getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(10, 33, 36, 14);
		frmAgendaDeContatos.getContentPane().add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(42, 30, 240, 20);
		frmAgendaDeContatos.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fone");
		lblNewLabel_2.setBounds(10, 67, 46, 14);
		frmAgendaDeContatos.getContentPane().add(lblNewLabel_2);

		txtFone = new JTextField();
		txtFone.setBounds(42, 61, 129, 20);
		frmAgendaDeContatos.getContentPane().add(txtFone);
		txtFone.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 92, 46, 14);
		frmAgendaDeContatos.getContentPane().add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setBounds(42, 89, 267, 20);
		frmAgendaDeContatos.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		btnCreate = new JButton("");
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarContato();
			}
		});
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setContentAreaFilled(false);
		btnCreate.setBorderPainted(false);
		btnCreate.setToolTipText("Adicionar Contato");
		btnCreate.setIcon(new ImageIcon(Agenda.class.getResource("/img/add (2).png")));
		btnCreate.setBounds(158, 186, 64, 64);
		frmAgendaDeContatos.getContentPane().add(btnCreate);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarContato();
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(Agenda.class.getResource("/img/edit.png")));
		btnUpdate.setToolTipText("Bot\u00E3o Editar");
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setBounds(84, 186, 64, 64);
		frmAgendaDeContatos.getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setToolTipText("Bot\u00E3o para Deletar");
		btnDelete.setIcon(new ImageIcon(Agenda.class.getResource("/img/delete (2).png")));
		btnDelete.setBounds(10, 186, 64, 64);
		frmAgendaDeContatos.getContentPane().add(btnDelete);

		btnRead = new JButton("");
		btnRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRead.setContentAreaFilled(false);
		btnRead.setBorderPainted(false);
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarContato();
			}
		});
		btnRead.setIcon(new ImageIcon(Agenda.class.getResource("/img/326690_magnify_search_zoom_icon.png")));
		btnRead.setToolTipText("Bot\u00E3o Pesquisar");
		btnRead.setBounds(292, 11, 53, 57);
		frmAgendaDeContatos.getContentPane().add(btnRead);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
		lblStatus.setBounds(365, 202, 48, 48);
		frmAgendaDeContatos.getContentPane().add(lblStatus);

		// USO DA BLIBIOTECA ATXY2K para restringir o máximo de caracteres
		RestrictedTextField nome = new RestrictedTextField(txtNome);
		nome.setOnlyText(true);
		nome.setLimit(50);
		nome.setAcceptSpace(true);

		RestrictedTextField fone = new RestrictedTextField(txtFone);

		fone.setLimit(15);

		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(50);
	}
	// FIM DO CONSTRUTOR

	// Criar um objeto para acessar o método conectar() da classe DAO
	DAO dao = new DAO();
	private JLabel lblStatus;
	private JButton btnCreate;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnRead;

	/**
	 * Metodo responsavel por verificar o status de conexão com o banco
	 */

	private void status() {
		// System.out.println("teste - Janela Ativada");

		// uso da classe connection(JDBC) para estabelecer a conexão

		try {
			Connection con = dao.conectar();
			if (con == null) {
				// System.out.println("Erro de Conexão!!");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dboff.png")));
			}

			else {
				// System.out.println("Banco Conectado!");
				lblStatus.setIcon(new ImageIcon(Agenda.class.getResource("/img/dbon.png")));
			}

			// nunca esquecer de fechar a conexão
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}// FIM DO STATUS

	/**
	 * Metodo responsavel pela pesquisa(select) de um contato no banco
	 */

	private void pesquisarContato() {

		// Validação
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do contato!");
			txtNome.requestFocus();
		} else {

			// Iniciar com a instrução SQL

			// ? é um parametro a ser substituido
			String read = "select * from contatos where contato = ?";
			try {

				// Estabelecer a conexão ("Abrir a porta da geladeira")
				Connection con = dao.conectar();

				// Preparar o código SQL para execução
				PreparedStatement pst = con.prepareStatement(read);

				// A linha abaixo o ? peo conteudo da caixa de texto txtNome
				pst.setString(1, txtNome.getText());

				// Obter os dados do contato (resultado)
				ResultSet rs = pst.executeQuery();

				// verificar se existe um contato cadastrado
				// rs.next significa: ter um contato correspondente ao nome pesquisado

				if (rs.next()) {
					// setar as caixas de texto com o resultado da pesquisa
					txtId.setText(rs.getString(1));
					txtFone.setText(rs.getString(3));
					txtEmail.setText(rs.getString(5));

					// HABILITAR BOTÕES ALTERAR E EXCLUIR
					btnUpdate.setEnabled(true);
					btnDelete.setEnabled(true);

				} else {
					JOptionPane.showMessageDialog(null, "Contato Inexistente");
					// setar campos e botões (UX)
					txtFone.setText(null);
					txtEmail.setText(null);
					txtFone.requestFocus();
					btnCreate.setEnabled(true);
				}

				// fecha conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Metodo responsável por adicionar um contato
	 */

	void adicionarContato() {
		// validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");

			// Fazer o cursor ir até o local do nome
			txtNome.requestFocus();

		} else if (txtFone.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Preencha o Telefone!");

			// Fazer o cursor ir até o local do telefone
			txtFone.requestFocus();
		} else {

			String create = "insert into contatos (contato,telefone,email) value (?,?,?)";

			try {
				// Abrir conexão
				Connection con = dao.conectar();

				// Preparar a query (substituição de parametros)
				PreparedStatement pst = con.prepareStatement(create);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());

				// Executar a query e confirmar a inserção no banco
				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");
					limpar();
				}

				else {

				}

				// System.out.println(confirma);

				// Encerrar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	/**
	 * Metodo para editar um contato
	 */

	private void alterarContato() {
		// System.out.println("teste bt");

		// validação
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!");

			// Fazer o cursor ir até o local do nome
			txtNome.requestFocus();

		} else if (txtFone.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Preencha o Telefone!");

			// Fazer o cursor ir até o local do telefone
			txtFone.requestFocus();

		} else {

			// logica principal
			String update = "update contatos set contato = ?, telefone = ?, email = ? where id = ?";

			try {

				// abrir a conexão
				Connection con = dao.conectar();

				// preparar a query
				PreparedStatement pst = con.prepareStatement(update);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtEmail.getText());
				pst.setString(4, txtId.getText());

				// Executar a query e confirmar a alteração no banco
				int confirma = pst.executeUpdate();

				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");

					limpar();
				}

				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Metodo usado para excluir um Contato
	 */
	private void excluirContato() {
		// System.out.println("Teste Botão excluir");

		// validação (Confirmação)
		int confirma = JOptionPane.showConfirmDialog(null, "COnfirma a exclusão deste contato?", "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION);

		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from contatos where id = ?;";

			try {
				// abrir a conexão
				Connection con = dao.conectar();

				// preparar a query
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				
				//executar o comando SQL e confirmar a exclusão
				
				int confirmaExcluir = pst.executeUpdate();
				
				if (confirmaExcluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Contato excluido com sucesso!");
				}
				
				//encerrar a conexão
				con.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Metodo para limpar e setar os botões
	 */

	private void limpar() {
		txtId.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEmail.setText(null);
		txtNome.requestFocus();
		btnCreate.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnDelete.setEnabled(false);
		btnRead.setEnabled(true);
	}

} // FIM DO CÓDIGO
