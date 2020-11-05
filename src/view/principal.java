package view;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.LivroDAO;
import javax.swing.DefaultComboBoxModel;

public class principal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		JPanel painelLista = new JPanel();
		tabbedPane.addTab("Lista", null, painelLista, null);
		painelLista.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { "Nome", "Autor", "Editora" }, },
				new String[] { "New Column", "New column", "New column" }));
		
		painelLista.add(table);

		JPanel painelLivro = new JPanel();
		tabbedPane.addTab("Adicionar livro", null, painelLivro, null);
		painelLivro.setLayout(null);

		textField = new JTextField();
		textField.setBounds(77, 22, 96, 20);
		painelLivro.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(241, 22, 96, 20);
		painelLivro.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(241, 76, 96, 20);
		painelLivro.add(textField_3);
		textField_3.setColumns(10);

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

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"oi", "teste"}));
		comboBox.setBounds(77, 75, 96, 22);
		painelLivro.add(comboBox);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(161, 159, 89, 23);
		painelLivro.add(btnAdicionar);

		JPanel painelAutor = new JPanel();
		tabbedPane.addTab("Adicionar Autor", null, painelAutor, null);

		JLabel lblNome_1 = new JLabel("Nome");
		painelAutor.add(lblNome_1);

		textField_1 = new JTextField();
		painelAutor.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		painelAutor.add(btnNewButton);

		JPanel painelEditora = new JPanel();
		tabbedPane.addTab("Adicionar Editora", null, painelEditora, null);
		painelEditora.setLayout(null);

		JLabel lblNome_2 = new JLabel("Nome");
		lblNome_2.setBounds(153, 9, 28, 14);
		painelEditora.add(lblNome_2);

		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(153, 51, 48, 14);
		painelEditora.add(lblUrl);

		textField_4 = new JTextField();
		textField_4.setBounds(191, 6, 96, 20);
		painelEditora.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(191, 48, 96, 20);
		painelEditora.add(textField_5);
		textField_5.setColumns(10);

		JButton btnAdicionar_1 = new JButton("Adicionar");
		btnAdicionar_1.setBounds(153, 145, 89, 23);
		painelEditora.add(btnAdicionar_1);
	}
}
