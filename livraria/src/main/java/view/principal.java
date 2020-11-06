package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ControllerAutor;
import controller.ControllerEditora;
import controller.ControllerLivros;
import model.Autor;
import model.Editora;
import model.Livro;

public class principal {

	private JFrame frame;
	private JTextField inputNomeLivro;
	private JTextField inputISBNLivro;
	private JTextField inputPrecoLivro;
	private JTextField inputNomeAutor;
	private JTextField inputNomeEditora;
	private JTextField inputUrlEditora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal window = new principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public principal() {
		initialize();

	}

	ControllerAutor ctrlAutor = new ControllerAutor();
	ControllerLivros ctrlLivro = new ControllerLivros();
	ControllerEditora ctrlEditora = new ControllerEditora();
	private JTable table;
	private JTextField inputNomeCompletoAutor;
	private JTextField inputIsbnLivroDeletar;
	private JTextField inputIsbnAlterar;
	private JTextField fieldNomeAlterar;
	private JTextField fieldPrecoAlterar;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				createListTable();
			}
		});
		frame.setBounds(100, 100, 540, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		JPanel painelLista = new JPanel();
		tabbedPane.addTab("Lista", null, painelLista, null);
		painelLista.setLayout(null);

		JButton btnShowData = new JButton("Update");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = ctrlLivro.listagemLivros();
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				tm.setRowCount(0);
				try {
					while (rs.next()) {
						Object o[] = { rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getInt(4) };
						tm.addRow(o);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnShowData.setBounds(359, 240, 150, 23);
		painelLista.add(btnShowData);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 499, 229);
		painelLista.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		JPanel painelLivro = new JPanel();
		tabbedPane.addTab("Livros", null, painelLivro, null);
		painelLivro.setLayout(null);

		inputNomeLivro = new JTextField();
		inputNomeLivro.setBounds(77, 22, 96, 20);
		painelLivro.add(inputNomeLivro);
		inputNomeLivro.setColumns(10);

		inputISBNLivro = new JTextField();
		inputISBNLivro.setBounds(241, 22, 96, 20);
		painelLivro.add(inputISBNLivro);
		inputISBNLivro.setColumns(10);

		inputPrecoLivro = new JTextField();
		inputPrecoLivro.setBounds(241, 76, 96, 20);
		painelLivro.add(inputPrecoLivro);
		inputPrecoLivro.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(77, 11, 48, 14);
		painelLivro.add(lblNome);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(241, 11, 48, 14);
		painelLivro.add(lblIsbn);

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(77, 64, 48, 14);
		painelLivro.add(lblEditora);

		JLabel lblPreo = new JLabel("Preço");
		lblPreo.setBounds(241, 64, 48, 14);
		painelLivro.add(lblPreo);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel());
		configuraComboBox(comboBox);

		comboBox.setBounds(77, 75, 96, 22);
		painelLivro.add(comboBox);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Livro l = new Livro();
				String nomeEditoraComboBox = (String) comboBox.getSelectedItem();
				ctrlLivro.cruzamentoEditoraId(l, nomeEditoraComboBox);

				l.setTitulo(inputNomeLivro.getText());
				l.setIsbn(inputISBNLivro.getText());
				l.setPreço(Double.parseDouble(inputPrecoLivro.getText()));

				ctrlLivro.adicionaLivro(l);
				JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
				inputNomeLivro.setText(null);
				inputPrecoLivro.setText(null);
				inputISBNLivro.setText(null);
			}
		});
		btnAdicionar.setBounds(161, 159, 89, 23);
		painelLivro.add(btnAdicionar);

		JPanel painelAutor = new JPanel();
		tabbedPane.addTab("Autores", null, painelAutor, null);
		painelAutor.setLayout(null);

		JLabel labelNomeCompletoAutor = new JLabel("Nome Completo");
		labelNomeCompletoAutor.setBounds(55, 83, 79, 14);
		painelAutor.add(labelNomeCompletoAutor);

		inputNomeAutor = new JTextField();
		inputNomeAutor.setBounds(141, 31, 125, 20);
		painelAutor.add(inputNomeAutor);
		inputNomeAutor.setColumns(10);

		JButton botaoAdicionaAutor = new JButton("Adicionar");
		botaoAdicionaAutor.setBounds(158, 142, 79, 23);
		botaoAdicionaAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Autor a = new Autor();
				a.setNome(inputNomeAutor.getText());
				a.setNomeCompleto(inputNomeCompletoAutor.getText());

				ctrlAutor.adiciona(a);
				JOptionPane.showMessageDialog(null, "Autor adicionado com sucesso!");
				inputNomeAutor.setText(null);
				inputNomeCompletoAutor.setText(null);
			}
		});
		painelAutor.add(botaoAdicionaAutor);

		inputNomeCompletoAutor = new JTextField();
		inputNomeCompletoAutor.setColumns(10);
		inputNomeCompletoAutor.setBounds(141, 80, 125, 20);
		painelAutor.add(inputNomeCompletoAutor);

		JLabel labelNomeAutor = new JLabel("Nome");
		labelNomeAutor.setBounds(106, 34, 28, 14);
		painelAutor.add(labelNomeAutor);

		JPanel painelEditora = new JPanel();
		tabbedPane.addTab("Editoras", null, painelEditora, null);
		painelEditora.setLayout(null);

		JLabel labelNomeEditora = new JLabel("Nome");
		labelNomeEditora.setBounds(153, 9, 28, 14);
		painelEditora.add(labelNomeEditora);

		JLabel labelUrlEditora = new JLabel("URL");
		labelUrlEditora.setBounds(153, 51, 48, 14);
		painelEditora.add(labelUrlEditora);

		inputNomeEditora = new JTextField();
		inputNomeEditora.setBounds(191, 6, 96, 20);
		painelEditora.add(inputNomeEditora);
		inputNomeEditora.setColumns(10);

		inputUrlEditora = new JTextField();
		inputUrlEditora.setBounds(191, 48, 96, 20);
		painelEditora.add(inputUrlEditora);
		inputUrlEditora.setColumns(10);

		JButton botaoAdicionaEditora = new JButton("Adicionar");
		botaoAdicionaEditora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editora edi = new Editora();
				edi.setNome(inputNomeEditora.getText());
				edi.setUrl(inputUrlEditora.getText());

				ctrlEditora.adiciona(edi);
				JOptionPane.showMessageDialog(null, "Editora adicionada com sucesso!");
				inputNomeEditora.setText(null);
				inputUrlEditora.setText(null);
				configuraComboBox(comboBox);
			}
		});
		botaoAdicionaEditora.setBounds(153, 145, 89, 23);
		painelEditora.add(botaoAdicionaEditora);

		JPanel Excluir = new JPanel();
		tabbedPane.addTab("Deletar", null, Excluir, null);
		Excluir.setLayout(null);

		inputIsbnLivroDeletar = new JTextField();
		inputIsbnLivroDeletar.setBounds(129, 60, 176, 20);
		Excluir.add(inputIsbnLivroDeletar);
		inputIsbnLivroDeletar.setColumns(10);

		JLabel lblDigiteOIsbn = new JLabel("Digite o ISBN do livro que deseja remover");
		lblDigiteOIsbn.setBounds(119, 29, 202, 27);
		Excluir.add(lblDigiteOIsbn);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o livro?") == 0) {
					ctrlLivro.remove(inputIsbnLivroDeletar.getText());
					JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
					inputIsbnLivroDeletar.setText(null);
				}
				;
			}
		});
		btnDeletar.setBounds(174, 148, 89, 23);
		Excluir.add(btnDeletar);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Alterar", null, panel, null);
		panel.setLayout(null);
		
		inputIsbnAlterar = new JTextField();
		inputIsbnAlterar.setBounds(194, 64, 96, 20);
		panel.add(inputIsbnAlterar);
		inputIsbnAlterar.setColumns(10);
		
		JLabel lblDigiteOIsbn_1 = new JLabel("Digite o ISBN do livro que deseja alterar");
		lblDigiteOIsbn_1.setBounds(142, 27, 217, 14);
		panel.add(lblDigiteOIsbn_1);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(306, 186, 96, 22);
		panel.add(comboBox_1);
		
		
		JButton btnGo = new JButton("Go!");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = ctrlLivro.seleciona(inputIsbnAlterar.getText());
				try {
					while(rs.next()) {
						fieldNomeAlterar.setText(rs.getString(1));
						fieldPrecoAlterar.setText(Double.toString(rs.getDouble(2)));
						configuraComboBox(comboBox_1);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnGo.setBounds(194, 112, 89, 23);
		panel.add(btnGo);
		
		fieldNomeAlterar = new JTextField();
		fieldNomeAlterar.setBounds(39, 187, 96, 20);
		panel.add(fieldNomeAlterar);
		fieldNomeAlterar.setColumns(10);
		
		fieldPrecoAlterar = new JTextField();
		fieldPrecoAlterar.setBounds(169, 187, 96, 20);
		panel.add(fieldPrecoAlterar);
		fieldPrecoAlterar.setColumns(10);
		

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = fieldNomeAlterar.getText();
				String s2 = fieldPrecoAlterar.getText();
				Integer s3 = ctrlLivro.retornaId((String)comboBox_1.getSelectedItem());
				String s4 = inputIsbnAlterar.getText();
				
				try {
					ctrlLivro.altera(s1, s2, s3, s4);
					JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");
					fieldNomeAlterar.setText(null);
					fieldPrecoAlterar.setText(null);
					inputIsbnAlterar.setText(null);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				}
		});
		btnAlterar.setBounds(430, 186, 89, 23);
		panel.add(btnAlterar);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(64, 162, 48, 14);
		panel.add(lblNome_1);
		
		JLabel lblPreo_1 = new JLabel("Preço");
		lblPreo_1.setBounds(194, 162, 48, 14);
		panel.add(lblPreo_1);
		
		JLabel lblEditora_1 = new JLabel("Editora");
		lblEditora_1.setBounds(334, 162, 48, 14);
		panel.add(lblEditora_1);
	}

	private void configuraComboBox(JComboBox comboBox) {
		try {
			comboBox.removeAllItems();
			ResultSet rs = ctrlEditora.listagemEditoras();
			while (rs.next()) {
				String abc = rs.getString(2);
				comboBox.addItem(abc);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			e.printStackTrace();
		}

	}

	private void createListTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Titulo");
		model.addColumn("Preço");
		model.addColumn("ISBN");
		model.addColumn("Id Editora");
		ResultSet rs = ctrlLivro.listagemLivros();

		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getInt(4) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setModel(model);
	}
}
