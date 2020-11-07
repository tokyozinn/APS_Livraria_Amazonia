package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ControllerAutor;
import controller.ControllerEditora;
import controller.ControllerLivros;
import model.Autor;
import model.Editora;
import model.Livro;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

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
	private JTable tabelaListagemLivros;
	private JTextField inputNomeCompletoAutor;
	private JTextField inputIsbnLivroDeletar;
	private JTextField inputIsbnAlterar;
	private JTextField fieldNomeAlterar;
	private JTextField fieldPrecoAlterar;
	private JTable tabelaListagemAutores;
	private JTextField inputIdAutorAlterar;
	private JTextField inputNomeAutorAlterar;
	private JTextField inputNomeCompletoAutorAlterar;
	private JTextField inputIdAutorRemover;
	private JTextField inputIdEditoraAlterar;
	private JTextField inputNomeEditoraAlterar;
	private JTextField inputUrlEditoraAlterar;
	private JTextField inputIdEditoraRemover;
	private JTable tabelaListagemEditoras;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				createListTable();
				createListAuthors();
			}
		});

		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel initializationPanel = new JPanel();
		frame.getContentPane().add(initializationPanel, "name_77455465556800");
		initializationPanel.setLayout(null);

		JLabel bookPic = new JLabel("");
		bookPic.setBounds(125, 75, 550, 250);
		bookPic.setIcon(new ImageIcon("src/main/resources/unnamed.png"));
		initializationPanel.add(bookPic);

		JLabel lblProjetoApsSip = new JLabel("Projeto APS SI4P12");
		lblProjetoApsSip.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjetoApsSip.setFont(new Font("Arial", Font.BOLD, 15));
		lblProjetoApsSip.setBounds(325, 350, 150, 30);
		initializationPanel.add(lblProjetoApsSip);

		final JPanel adicionarLivro = new JPanel();
		frame.getContentPane().add(adicionarLivro, "name_77455523079500");
		adicionarLivro.setLayout(null);

		inputNomeLivro = new JTextField();
		inputNomeLivro.setBounds(100, 100, 200, 30);
		adicionarLivro.add(inputNomeLivro);
		inputNomeLivro.setColumns(10);

		inputISBNLivro = new JTextField();
		inputISBNLivro.setBounds(500, 100, 200, 30);
		adicionarLivro.add(inputISBNLivro);
		inputISBNLivro.setColumns(10);

		inputPrecoLivro = new JTextField();
		inputPrecoLivro.setBounds(500, 200, 200, 30);
		adicionarLivro.add(inputPrecoLivro);
		inputPrecoLivro.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(174, 75, 48, 14);
		adicionarLivro.add(lblNome);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setBounds(586, 75, 48, 14);
		adicionarLivro.add(lblIsbn);

		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditora.setBounds(174, 178, 48, 14);
		adicionarLivro.add(lblEditora);

		JLabel lblPreo = new JLabel("Preço");
		lblPreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreo.setBounds(586, 178, 48, 14);
		adicionarLivro.add(lblPreo);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel());
		configuraComboBox(comboBox);

		comboBox.setBounds(100, 200, 200, 30);
		adicionarLivro.add(comboBox);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
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
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Erro: " + err.getMessage());
				}
			}
		});
		btnAdicionar.setBounds(350, 300, 100, 30);
		adicionarLivro.add(btnAdicionar);

		JLabel lblAdicionarUmNovo_1 = new JLabel("Adicionar um novo livro");
		lblAdicionarUmNovo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarUmNovo_1.setBounds(300, 30, 200, 20);
		adicionarLivro.add(lblAdicionarUmNovo_1);

		final JPanel adicionaAutor = new JPanel();
		frame.getContentPane().add(adicionaAutor, "name_77455572755700");
		adicionaAutor.setLayout(null);

		JLabel labelNomeCompletoAutor = new JLabel("Nome Completo:");
		labelNomeCompletoAutor.setHorizontalAlignment(SwingConstants.CENTER);
		labelNomeCompletoAutor.setBounds(193, 175, 100, 20);
		adicionaAutor.add(labelNomeCompletoAutor);

		inputNomeAutor = new JTextField();
		inputNomeAutor.setBounds(300, 100, 200, 30);
		adicionaAutor.add(inputNomeAutor);
		inputNomeAutor.setColumns(10);

		JButton botaoAdicionaAutor = new JButton("Adicionar");
		botaoAdicionaAutor.setBounds(350, 275, 100, 30);
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
		adicionaAutor.add(botaoAdicionaAutor);

		inputNomeCompletoAutor = new JTextField();
		inputNomeCompletoAutor.setColumns(10);
		inputNomeCompletoAutor.setBounds(300, 170, 200, 30);
		adicionaAutor.add(inputNomeCompletoAutor);

		JLabel labelNomeAutor = new JLabel("Nome:");
		labelNomeAutor.setHorizontalAlignment(SwingConstants.CENTER);
		labelNomeAutor.setBounds(235, 105, 40, 20);
		adicionaAutor.add(labelNomeAutor);

		JLabel lblAdicionarUmNovo = new JLabel("Adicionar um novo Autor");
		lblAdicionarUmNovo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarUmNovo.setBounds(300, 30, 200, 20);
		adicionaAutor.add(lblAdicionarUmNovo);

		final JPanel adicionaEditora = new JPanel();
		frame.getContentPane().add(adicionaEditora, "name_77455628685400");
		adicionaEditora.setLayout(null);

		JLabel labelNomeEditora = new JLabel("Nome:");
		labelNomeEditora.setHorizontalAlignment(SwingConstants.CENTER);
		labelNomeEditora.setBounds(233, 108, 45, 14);
		adicionaEditora.add(labelNomeEditora);

		JLabel labelUrlEditora = new JLabel("URL:");
		labelUrlEditora.setHorizontalAlignment(SwingConstants.CENTER);
		labelUrlEditora.setBounds(242, 178, 36, 14);
		adicionaEditora.add(labelUrlEditora);

		inputNomeEditora = new JTextField();
		inputNomeEditora.setBounds(300, 100, 200, 30);
		adicionaEditora.add(inputNomeEditora);
		inputNomeEditora.setColumns(10);

		inputUrlEditora = new JTextField();
		inputUrlEditora.setBounds(300, 170, 200, 30);
		adicionaEditora.add(inputUrlEditora);
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
		botaoAdicionaEditora.setBounds(350, 275, 100, 30);
		adicionaEditora.add(botaoAdicionaEditora);

		JLabel lblAdicionarUmaNova = new JLabel("Adicionar uma nova Editora");
		lblAdicionarUmaNova.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarUmaNova.setBounds(300, 30, 200, 20);
		adicionaEditora.add(lblAdicionarUmaNova);

		final JPanel removerLivro = new JPanel();
		frame.getContentPane().add(removerLivro, "name_77455676502600");
		removerLivro.setLayout(null);

		inputIsbnLivroDeletar = new JTextField();
		inputIsbnLivroDeletar.setBounds(300, 150, 200, 30);
		removerLivro.add(inputIsbnLivroDeletar);
		inputIsbnLivroDeletar.setColumns(10);

		JLabel lblDigiteOIsbn = new JLabel("Digite o ISBN do livro que deseja remover");
		lblDigiteOIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteOIsbn.setBounds(275, 115, 250, 20);
		removerLivro.add(lblDigiteOIsbn);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Arial", Font.BOLD, 15));
		btnDeletar.setForeground(Color.RED);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o livro?") == 0) {
					try {
						ctrlLivro.remove(inputIsbnLivroDeletar.getText());
						JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
						inputIsbnLivroDeletar.setText(null);
					} catch (Exception e1) {
						e1.getMessage();
					}

				}
				;
			}
		});
		btnDeletar.setBounds(350, 250, 100, 30);
		removerLivro.add(btnDeletar);

		JLabel lblRemoverUmLivro = new JLabel("Remover um Livro");
		lblRemoverUmLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoverUmLivro.setBounds(340, 30, 120, 20);
		removerLivro.add(lblRemoverUmLivro);

		final JPanel alterarLivro = new JPanel();
		frame.getContentPane().add(alterarLivro, "name_77455755055500");
		alterarLivro.setLayout(null);

		inputIsbnAlterar = new JTextField();
		inputIsbnAlterar.setBounds(300, 100, 200, 30);
		alterarLivro.add(inputIsbnAlterar);
		inputIsbnAlterar.setColumns(10);

		JLabel lblDigiteOIsbn_1 = new JLabel("Digite o ISBN do livro que deseja alterar:");
		lblDigiteOIsbn_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteOIsbn_1.setBounds(21, 105, 250, 20);
		alterarLivro.add(lblDigiteOIsbn_1);

		final JComboBox comboBoxAlteraLivro = new JComboBox();
		comboBoxAlteraLivro.setBounds(550, 200, 200, 30);
		alterarLivro.add(comboBoxAlteraLivro);

		JButton botaoOkLivroAlterar = new JButton("Ok");
		botaoOkLivroAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = ctrlLivro.seleciona(inputIsbnAlterar.getText());
				try {
					if (rs.next()) {
						fieldNomeAlterar.setText(rs.getString(1));
						fieldPrecoAlterar.setText(Double.toString(rs.getDouble(2)));
						configuraComboBox(comboBoxAlteraLivro);
					} else {
						JOptionPane.showMessageDialog(null, "Livro não encontrado");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}
			}
		});
		botaoOkLivroAlterar.setBounds(575, 100, 100, 30);
		alterarLivro.add(botaoOkLivroAlterar);

		fieldNomeAlterar = new JTextField();
		fieldNomeAlterar.setBounds(50, 200, 200, 30);
		alterarLivro.add(fieldNomeAlterar);
		fieldNomeAlterar.setColumns(10);

		fieldPrecoAlterar = new JTextField();
		fieldPrecoAlterar.setBounds(300, 200, 200, 30);
		alterarLivro.add(fieldPrecoAlterar);
		fieldPrecoAlterar.setColumns(10);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = fieldNomeAlterar.getText();
				String s2 = fieldPrecoAlterar.getText();
				Integer s3 = ctrlLivro.retornaId((String) comboBoxAlteraLivro.getSelectedItem());
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
		btnAlterar.setBounds(350, 300, 100, 30);
		alterarLivro.add(btnAlterar);

		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome_1.setBounds(125, 175, 48, 14);
		alterarLivro.add(lblNome_1);

		JLabel lblPreo_1 = new JLabel("Preço");
		lblPreo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreo_1.setBounds(380, 175, 48, 14);
		alterarLivro.add(lblPreo_1);

		JLabel lblEditora_1 = new JLabel("Editora");
		lblEditora_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditora_1.setBounds(630, 175, 48, 14);
		alterarLivro.add(lblEditora_1);

		JLabel lblAlterarUmLivro = new JLabel("Alterar um livro");
		lblAlterarUmLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarUmLivro.setBounds(300, 30, 200, 20);
		alterarLivro.add(lblAlterarUmLivro);

		final JPanel alteraAutor = new JPanel();
		frame.getContentPane().add(alteraAutor, "name_82333271296500");
		alteraAutor.setLayout(null);

		inputIdAutorAlterar = new JTextField();
		inputIdAutorAlterar.setBounds(300, 100, 200, 30);
		alteraAutor.add(inputIdAutorAlterar);
		inputIdAutorAlterar.setColumns(10);

		JLabel lblDigiteONmero = new JLabel("Digite o ID do autor que deseja alterar:");
		lblDigiteONmero.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteONmero.setBounds(0, 105, 290, 20);
		alteraAutor.add(lblDigiteONmero);

		JButton botarOkAutorAlterar = new JButton("Ok");
		botarOkAutorAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = ctrlAutor.seleciona(Integer.parseInt(inputIdAutorAlterar.getText()));
				try {
					if (rs.next()) {
						inputNomeAutorAlterar.setText(rs.getString(2));
						inputNomeCompletoAutorAlterar.setText(rs.getString(3));
					} else {
						JOptionPane.showMessageDialog(null, "Autor não encontrado");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}
			}
		});

		botarOkAutorAlterar.setBounds(575, 100, 100, 30);
		alteraAutor.add(botarOkAutorAlterar);

		inputNomeAutorAlterar = new JTextField();
		inputNomeAutorAlterar.setBounds(100, 200, 200, 30);
		alteraAutor.add(inputNomeAutorAlterar);
		inputNomeAutorAlterar.setColumns(10);

		inputNomeCompletoAutorAlterar = new JTextField();
		inputNomeCompletoAutorAlterar.setColumns(10);
		inputNomeCompletoAutorAlterar.setBounds(500, 200, 200, 30);
		alteraAutor.add(inputNomeCompletoAutorAlterar);

		JButton botaoAlterarAutor = new JButton("Alterar");
		botaoAlterarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = inputNomeAutorAlterar.getText();
				String s2 = inputNomeCompletoAutorAlterar.getText();
				Integer i = Integer.parseInt(inputIdAutorAlterar.getText());

				try {
					ctrlAutor.altera(s1, s2, i);
					JOptionPane.showMessageDialog(null, "Autor alterado com sucesso!");
					inputIdAutorAlterar.setText(null);
					inputNomeAutorAlterar.setText(null);
					inputNomeCompletoAutorAlterar.setText(null);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}

		});
		botaoAlterarAutor.setBounds(350, 300, 100, 30);
		alteraAutor.add(botaoAlterarAutor);

		JLabel lblAlterarUmAutor = new JLabel("Alterar um Autor");
		lblAlterarUmAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarUmAutor.setBounds(350, 30, 100, 20);
		alteraAutor.add(lblAlterarUmAutor);

		JLabel lblNome_2 = new JLabel("Nome");
		lblNome_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome_2.setBounds(165, 179, 48, 14);
		alteraAutor.add(lblNome_2);

		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCompleto.setBounds(553, 179, 100, 14);
		alteraAutor.add(lblNomeCompleto);

		final JPanel removeAutor = new JPanel();
		frame.getContentPane().add(removeAutor, "name_82340885339000");
		removeAutor.setLayout(null);

		inputIdAutorRemover = new JTextField();
		inputIdAutorRemover.setBounds(300, 150, 200, 30);
		removeAutor.add(inputIdAutorRemover);
		inputIdAutorRemover.setColumns(10);

		JLabel lblDigiteONmero_1 = new JLabel("Digite o número de ID do autor que deseja remover");
		lblDigiteONmero_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteONmero_1.setBounds(250, 100, 300, 20);
		removeAutor.add(lblDigiteONmero_1);

		JButton botaoDeletarAutor = new JButton("Deletar");
		botaoDeletarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o autor?") == 0) {
					try {
						ctrlAutor.remove(Integer.parseInt(inputIdAutorRemover.getText()));
						JOptionPane.showMessageDialog(null, "Autor removido com sucesso!");
						inputIdAutorRemover.setText(null);
					} catch (Exception e1) {
						e1.getMessage();
					}

				};
			}
		});
		botaoDeletarAutor.setFont(new Font("Arial", Font.BOLD, 15));
		botaoDeletarAutor.setForeground(Color.RED);
		botaoDeletarAutor.setBounds(350, 250, 100, 30);
		removeAutor.add(botaoDeletarAutor);

		JLabel lblRemoverUmAuttor = new JLabel("Remover um Autor");
		lblRemoverUmAuttor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoverUmAuttor.setBounds(340, 30, 120, 20);
		removeAutor.add(lblRemoverUmAuttor);

		final JPanel alteraEditora = new JPanel();
		frame.getContentPane().add(alteraEditora, "name_82369918733100");
		alteraEditora.setLayout(null);

		inputIdEditoraAlterar = new JTextField();
		inputIdEditoraAlterar.setColumns(10);
		inputIdEditoraAlterar.setBounds(300, 100, 200, 30);
		alteraEditora.add(inputIdEditoraAlterar);

		JLabel lblDigiteONmero_3 = new JLabel("Digite o ID da editora que deseja alterar:");
		lblDigiteONmero_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteONmero_3.setBounds(0, 105, 304, 20);
		alteraEditora.add(lblDigiteONmero_3);

		JButton botaoOkEditoraAlterar = new JButton("Ok");
		botaoOkEditoraAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ResultSet rs = ctrlEditora.seleciona(Integer.parseInt(inputIdEditoraAlterar.getText()));
				try {
					if (rs.next()) {
						inputNomeEditoraAlterar.setText(rs.getString(2));
						inputUrlEditoraAlterar.setText(rs.getString(3));
					} else {
						JOptionPane.showMessageDialog(null, "Autor não encontrado");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}
			}

		});
		botaoOkEditoraAlterar.setBounds(575, 100, 100, 30);
		alteraEditora.add(botaoOkEditoraAlterar);

		inputNomeEditoraAlterar = new JTextField();
		inputNomeEditoraAlterar.setColumns(10);
		inputNomeEditoraAlterar.setBounds(100, 200, 200, 30);
		alteraEditora.add(inputNomeEditoraAlterar);

		inputUrlEditoraAlterar = new JTextField();
		inputUrlEditoraAlterar.setColumns(10);
		inputUrlEditoraAlterar.setBounds(500, 200, 200, 30);
		alteraEditora.add(inputUrlEditoraAlterar);

		JButton botaoEditoraAlterar = new JButton("Alterar");
		botaoEditoraAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = inputNomeEditoraAlterar.getText();
				String s2 = inputUrlEditoraAlterar.getText();
				Integer i = Integer.parseInt(inputIdEditoraAlterar.getText());

				try {
					ctrlEditora.altera(s1, s2, i);
					JOptionPane.showMessageDialog(null, "Dados da editora alterados com sucesso!");
					inputIdEditoraAlterar.setText(null);
					inputNomeEditoraAlterar.setText(null);
					inputUrlEditoraAlterar.setText(null);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}

			}
		});
		botaoEditoraAlterar.setBounds(350, 300, 100, 30);
		alteraEditora.add(botaoEditoraAlterar);

		JLabel lblAlterarUmaEditora = new JLabel("Alterar uma Editora");
		lblAlterarUmaEditora.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarUmaEditora.setBounds(340, 30, 120, 20);
		alteraEditora.add(lblAlterarUmaEditora);

		JLabel lblNome_3 = new JLabel("Nome");
		lblNome_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome_3.setBounds(167, 180, 48, 14);
		alteraEditora.add(lblNome_3);

		JLabel lblUrl = new JLabel("URL");
		lblUrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblUrl.setBounds(575, 180, 48, 14);
		alteraEditora.add(lblUrl);

		final JPanel removeEditora = new JPanel();
		frame.getContentPane().add(removeEditora, "name_82463645492900");
		removeEditora.setLayout(null);

		inputIdEditoraRemover = new JTextField();
		inputIdEditoraRemover.setBounds(300, 150, 200, 30);
		removeEditora.add(inputIdEditoraRemover);
		inputIdEditoraRemover.setColumns(10);

		JLabel lblDigiteONmero_2 = new JLabel("Digite o número de ID da editora que deseja remover");
		lblDigiteONmero_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteONmero_2.setBounds(250, 100, 300, 20);
		removeEditora.add(lblDigiteONmero_2);

		JButton botaoEditoraDeletar = new JButton("Deletar");
		botaoEditoraDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar a editora?") == 0) {
					try {
						ctrlEditora.remove(Integer.parseInt(inputIdEditoraRemover.getText()));
						inputIdEditoraRemover.setText(null);
					} catch (Exception e1) {
						e1.getMessage();
					}

				};
			}
		});
		botaoEditoraDeletar.setFont(new Font("Arial", Font.BOLD, 15));
		botaoEditoraDeletar.setForeground(Color.RED);
		botaoEditoraDeletar.setBounds(350, 250, 100, 30);
		removeEditora.add(botaoEditoraDeletar);

		JLabel lblRemoverUmaEditora = new JLabel("Remover uma Editora");
		lblRemoverUmaEditora.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoverUmaEditora.setBounds(325, 30, 150, 20);
		removeEditora.add(lblRemoverUmaEditora);

		final JPanel listagemLivros = new JPanel();
		frame.getContentPane().add(listagemLivros, "name_77455821168700");
		listagemLivros.setLayout(null);

		final JPanel listagemEditoras = new JPanel();
		frame.getContentPane().add(listagemEditoras, "name_82677825720700");
		listagemEditoras.setLayout(null);

		JScrollPane scrollPaneEditora = new JScrollPane();
		scrollPaneEditora.setBounds(125, 25, 550, 250);
		listagemEditoras.add(scrollPaneEditora);

		tabelaListagemEditoras = new JTable();
		scrollPaneEditora.setColumnHeaderView(tabelaListagemEditoras);
		scrollPaneEditora.setViewportView(tabelaListagemEditoras);

		JButton btnShowData = new JButton("Update");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Fui clicado, mas não faço nada ainda\n VAI MENGÃO!!!!");
			}
		});
		btnShowData.setBounds(515, 315, 100, 30);
		listagemLivros.add(btnShowData);

		JScrollPane scrollPaneLivros = new JScrollPane();
		scrollPaneLivros.setBounds(125, 25, 550, 250);
		listagemLivros.add(scrollPaneLivros);

		tabelaListagemLivros = new JTable();
		scrollPaneLivros.setViewportView(tabelaListagemLivros);

		final JPanel listagemAutores = new JPanel();
		frame.getContentPane().add(listagemAutores, "name_80075796613800");
		listagemAutores.setLayout(null);

		JScrollPane scrollPaneAutor = new JScrollPane();
		scrollPaneAutor.setBounds(125, 25, 550, 250);
		listagemAutores.add(scrollPaneAutor);

		tabelaListagemAutores = new JTable();
		scrollPaneAutor.setViewportView(tabelaListagemAutores);
		initializationPanel.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mainMenu = new JMenu("File");
		menuBar.add(mainMenu);

		JMenuItem mainMenuInicio = new JMenuItem("Início");
		mainMenuInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				initializationPanel.setVisible(true);
			}
		});
		mainMenu.add(mainMenuInicio);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mainMenu.add(mntmClose);

		JMenu menuLivros = new JMenu("Livros");
		menuBar.add(menuLivros);

		JMenuItem menuLivroAdiciona = new JMenuItem("Adicionar");
		menuLivroAdiciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				adicionarLivro.setVisible(true);
			}
		});

		JMenuItem menuLivrosLista = new JMenuItem("Listagem");
		menuLivrosLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				listagemLivros.setVisible(true);
				createListTable();
			}
		});
		menuLivros.add(menuLivrosLista);
		menuLivros.add(menuLivroAdiciona);

		JMenuItem menuLivroAltera = new JMenuItem("Alterar");
		menuLivroAltera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				alterarLivro.setVisible(true);
			}
		});
		menuLivros.add(menuLivroAltera);

		JMenuItem menuLivroRemove = new JMenuItem("Remover");
		menuLivroRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				removerLivro.setVisible(true);
			}
		});
		menuLivros.add(menuLivroRemove);

		JMenu menuAutor = new JMenu("Autores");
		menuBar.add(menuAutor);

		JMenuItem mntmListagem = new JMenuItem("Listagem");
		mntmListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				listagemAutores.setVisible(true);
				createListAuthors();
			}
		});
		menuAutor.add(mntmListagem);

		JMenuItem menuAutorAdiciona = new JMenuItem("Adicionar");
		menuAutorAdiciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				adicionaAutor.setVisible(true);
			}
		});
		menuAutor.add(menuAutorAdiciona);

		JMenuItem menuAutorAltera = new JMenuItem("Alterar");
		menuAutorAltera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				alteraAutor.setVisible(true);
			}
		});
		menuAutor.add(menuAutorAltera);

		JMenuItem menuAutorRemove = new JMenuItem("Remover");
		menuAutorRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				removeAutor.setVisible(true);
			}
		});
		menuAutor.add(menuAutorRemove);

		JMenu menuEditora = new JMenu("Editoras");
		menuBar.add(menuEditora);

		JMenuItem menuEditoraListagem = new JMenuItem("Listagem");
		menuEditoraListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				listagemEditoras.setVisible(true);
				createListEditors();
			}
		});
		menuEditora.add(menuEditoraListagem);

		JMenuItem menuEditoraAdiciona = new JMenuItem("Adiciona");
		menuEditoraAdiciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				adicionaEditora.setVisible(true);
			}
		});
		menuEditora.add(menuEditoraAdiciona);

		JMenuItem menuEditoraAltera = new JMenuItem("Alterar");
		menuEditoraAltera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				alteraEditora.setVisible(true);
			}
		});
		menuEditora.add(menuEditoraAltera);

		JMenuItem menuEditoraRemove = new JMenuItem("Remover");
		menuEditoraRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disablePanels(frame.getContentPane());
				removeEditora.setVisible(true);
			}
		});
		menuEditora.add(menuEditoraRemove);

		disablePanels(frame.getContentPane());

	}

	// **** Método que popula a combobox das Editoras ****

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

	// ***************************************************

	// **** Métodos de criação das tabelas *****

	private void createListTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Titulo");
		model.addColumn("Preço");
		model.addColumn("ISBN");
		model.addColumn("Editora");
		ResultSet rs = ctrlLivro.listagemLivros();

		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getString(4) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tabelaListagemLivros.setModel(model);
	}

	private void createListAuthors() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nome");
		model.addColumn("Nome Completo");
		model.addColumn("Id do Autor");
		ResultSet rs = ctrlAutor.listaAutores();

		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(2), rs.getString(3), rs.getInt(1) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tabelaListagemAutores.setModel(model);
	}

	private void createListEditors() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nome");
		model.addColumn("URL");
		model.addColumn("ID da Editora");
		ResultSet rs = ctrlEditora.listagemEditoras();

		try {
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString(2), rs.getString(3), rs.getInt(1) });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tabelaListagemEditoras.setModel(model);
	}

	// *******************************************

	// ** Método para ocultar panels que não estão em uso**

	private void disablePanels(Container frame) {
		Component[] components = frame.getComponents();

		if (components.length > 0) {
			for (Component component : components) {
				((JPanel) component).setVisible(false);
			}
		}
	}

	// *******************************************
}
