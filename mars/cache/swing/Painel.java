package mars.cache.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mars.cache.Controller;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Canvas;
import java.awt.Dimension;

public class Painel extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textTempoAcessoCache;
	private JTextField textTempoAcessoMemoria;
	private String[] stringBlocks;
	private JComboBox<String> comboBoxBlocosConjunto;
	private JComboBox<String> comboBoxnPalavrasBloco;
	private JComboBox<String> comboBoxnBlocos;
	private JComboBox<String> comboBoxMapeamento;
	private JComboBox<String> comboBoxPoliticaSubstituicao;
	private JTextArea textTamanhoCache;
	private JLabel lblNewLabel;
	private Controller controller;
	private JRadioButton radioCacheDados;
	private JRadioButton radioCacheInstrucoes;
	private JRadioButton radioCacheUnificada;
	private JTable table;
	private JLabel lblTagBits;
	private JLabel lblIndexBits;
	private JLabel lblBlockOffsetBits;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					Painel frame = new Painel(controller);
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
	public Painel(Controller controller) {
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
		);
		
		JPanel panelConfiguracoes = new JPanel();
		tabbedPane.addTab("Settings", null, panelConfiguracoes, null);
		
		JLabel labelTipoArazenamento = new JLabel();
		labelTipoArazenamento.setText("Cache Storage Type");
		labelTipoArazenamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator = new JSeparator();
		
		radioCacheDados = new JRadioButton();
		radioCacheDados.setSelected(true);
		buttonGroup.add(radioCacheDados);
		radioCacheDados.setText("Data Cache");
		
		radioCacheInstrucoes = new JRadioButton();
		buttonGroup.add(radioCacheInstrucoes);
		radioCacheInstrucoes.setText("Instruction Cache");
		
		radioCacheUnificada = new JRadioButton();
		buttonGroup.add(radioCacheUnificada);
		radioCacheUnificada.setText("Unified Cache");
		
		JSeparator separator2 = new JSeparator();
		
		JLabel labelOrganizacaoCache = new JLabel();
		labelOrganizacaoCache.setText("Cache Organization");
		labelOrganizacaoCache.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator3 = new JSeparator();
		
		JLabel labelMapeamento = new JLabel();
		labelMapeamento.setText("Placement Policy");
		
		JLabel lblBlockReplacementPolicy = new JLabel();
		lblBlockReplacementPolicy.setText("Block Replacement Policy");
		
		JLabel lblSet = new JLabel();
		lblSet.setText("Set size (blocks)");
		
		comboBoxMapeamento = new JComboBox<String>();
		comboBoxMapeamento.setModel(new DefaultComboBoxModel(new String[] {"Direct-mapped", "Fully Associative", "Set-associative"}));
		comboBoxMapeamento.setSelectedIndex(0);
		comboBoxMapeamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBlocks();
				updateAddress();
			}
		});
		
		comboBoxPoliticaSubstituicao = new JComboBox<String>();
		comboBoxPoliticaSubstituicao.setModel(new DefaultComboBoxModel(new String[] {"LRU", "LRU Aproximado", "FIFO"}));
		comboBoxPoliticaSubstituicao.setSelectedIndex(0);
		
		comboBoxBlocosConjunto = new JComboBox<String>();

		comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(new String[] {"1"}));
		comboBoxBlocosConjunto.setSelectedIndex(0);
		comboBoxBlocosConjunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAddress();
			}
		});
		
		JLabel lblNumberOf = new JLabel();
		lblNumberOf.setText("Number of blocks");
		
		JLabel lblCacheBlockSize = new JLabel();
		lblCacheBlockSize.setText("Cache block size ( words)");
		
		JLabel labelTamanhoCache = new JLabel();
		labelTamanhoCache.setText("Cache size (bytes)");
		
		textTamanhoCache = new JTextArea();
		textTamanhoCache.setEditable(false);
		textTamanhoCache.setText("4");
		textTamanhoCache.setRows(5);
		textTamanhoCache.setColumns(20);
		
		comboBoxnPalavrasBloco = new JComboBox<String>();
		comboBoxnPalavrasBloco.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"}));
		comboBoxnPalavrasBloco.setSelectedIndex(0);
		comboBoxnPalavrasBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSizeCache();
				updateAddress();
			}
		});
		
		comboBoxnBlocos = new JComboBox<String>();
		comboBoxnBlocos.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"}));
		comboBoxnBlocos.setSelectedIndex(0);
		comboBoxnBlocos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				updateSizeCache();
				updateAddress();
				updateBlocks();
			}
		});
		
		
		JSeparator separator4 = new JSeparator();
		
		JLabel labelTempoAcesso = new JLabel();
		labelTempoAcesso.setText("Time access");
		labelTempoAcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator5 = new JSeparator();
		
		JLabel lblCacheAccessTime = new JLabel();
		lblCacheAccessTime.setText("Cache Access Time (ms)");
		
		textTempoAcessoCache = new JTextField();
		textTempoAcessoCache.setText("10");
		
		JLabel labelTempoAcessoMemoria = new JLabel();
		labelTempoAcessoMemoria.setText("Memory Access Time (ms)");
		
		textTempoAcessoMemoria = new JTextField();
		textTempoAcessoMemoria.setText("100");
		
		JButton btnSalvar = new JButton("Save");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSalvar();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblAddress = new JLabel();
		lblAddress.setText("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator_2 = new JSeparator();
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tag", "Index", "BlockOffset"},
			},
			new String[] {
				"TAG", "INDEX", "BLOCKOFFSET"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(69);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		table.setRowHeight(30);
		
		JLabel lblAddressBits = new JLabel("Address (32 bits)");
		
		lblTagBits = new JLabel("32 bits");
		lblTagBits.setMaximumSize(new Dimension(40, 14));
		lblTagBits.setMinimumSize(new Dimension(40, 14));
		
		lblIndexBits = new JLabel("0 bits");
		lblIndexBits.setMaximumSize(new Dimension(40, 14));
		lblIndexBits.setMinimumSize(new Dimension(40, 14));
		
		lblBlockOffsetBits = new JLabel("0 bits");
		lblBlockOffsetBits.setMinimumSize(new Dimension(40, 14));
		lblBlockOffsetBits.setMaximumSize(new Dimension(40, 14));
		
	
		
		
		
		GroupLayout gl_panelConfiguracoes = new GroupLayout(panelConfiguracoes);
		gl_panelConfiguracoes.setHorizontalGroup(
			gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(radioCacheDados, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(radioCacheInstrucoes, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(radioCacheUnificada, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelTipoArazenamento, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelOrganizacaoCache, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(labelMapeamento, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(comboBoxMapeamento, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNumberOf, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBlockReplacementPolicy, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelConfiguracoes.createSequentialGroup()
									.addGap(93)
									.addComponent(comboBoxBlocosConjunto, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTamanhoCache, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textTamanhoCache, 0, 0, Short.MAX_VALUE))
								.addGroup(gl_panelConfiguracoes.createSequentialGroup()
									.addGap(42)
									.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblCacheBlockSize, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBoxnBlocos, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBoxnPalavrasBloco, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(separator4, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelTempoAcesso, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator5, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnSalvar)
							.addGroup(gl_panelConfiguracoes.createSequentialGroup()
								.addComponent(lblCacheAccessTime, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addGap(78)
								.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(labelTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addGap(299)
					.addComponent(lblAddressBits)
					.addContainerGap(402, Short.MAX_VALUE))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addGap(211)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(10)
							.addComponent(lblTagBits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(lblIndexBits, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(lblBlockOffsetBits, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(310, Short.MAX_VALUE))
		);
		gl_panelConfiguracoes.setVerticalGroup(
			gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTipoArazenamento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addComponent(radioCacheDados)
						.addComponent(radioCacheInstrucoes)
						.addComponent(radioCacheUnificada))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelOrganizacaoCache)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxMapeamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNumberOf)
							.addComponent(comboBoxnBlocos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelMapeamento))
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxnPalavrasBloco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(lblCacheBlockSize))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(lblBlockReplacementPolicy)))
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(labelTamanhoCache))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(7)
							.addComponent(textTamanhoCache, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(6)
							.addComponent(comboBoxBlocosConjunto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(lblSet)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelTempoAcesso)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCacheAccessTime))
						.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(3)
							.addComponent(labelTempoAcessoMemoria))
						.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAddress)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(lblAddressBits)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTagBits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBlockOffsetBits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIndexBits, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(109)
					.addComponent(btnSalvar)
					.addGap(106))
		);
		panelConfiguracoes.setLayout(gl_panelConfiguracoes);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Addressing", null, panel_1, null);
				
				lblNewLabel = new JLabel("00000000000000000000000000000000");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 37));
				//lblNewLabel.setText("<html>"+ "<font color='red'>" + "000010101000" + "</font>" +  "<font color='blue'>" + "11001100" + "</font>"  + "00111100" + "0000" + "</html>");
				lblNewLabel.setText("<html>"+ "<font color='red'>" + "000010101000" + "</font>" +  "<font color='blue'>" + "11001100" + "</font>"  + "<font color='green'>" +  "00111100" + "</font>" + "<font color='gray'>"+ "0000" +"</font>" + "</html>");
				
				JLabel lblNewLabel_1 = new JLabel("TAG:");
				lblNewLabel_1.setForeground(new Color(255, 0, 0));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
				
				JLabel label_11 = new JLabel("\u00CDNDICE:");
				label_11.setForeground(new Color(0, 0, 255));
				label_11.setFont(new Font("Tahoma", Font.PLAIN, 25));
				
				JLabel label_12 = new JLabel("BLOCKOFFSET:");
				label_12.setForeground(new Color(0, 128, 0));
				label_12.setFont(new Font("Tahoma", Font.PLAIN, 25));
				
				JLabel label_13 = new JLabel("BYTEOFFSET:");
				label_13.setForeground(new Color(128, 128, 128));
				label_13.setFont(new Font("Tahoma", Font.PLAIN, 25));
				
		
				GroupLayout gl_panel_1 = new GroupLayout(panel_1);
				gl_panel_1.setHorizontalGroup(
					gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_1))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(label_11))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addContainerGap()
									.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(64)
									.addComponent(lblNewLabel)))
							.addContainerGap(79, Short.MAX_VALUE))
				);
				gl_panel_1.setVerticalGroup(
					gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1)
							.addGap(46)
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(175, Short.MAX_VALUE))
				);
				panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		Canvas canvas = new Canvas();
		tabbedPane.addTab("New tab", null, canvas, null);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void updateBlocks()
	{
		String mapeamento = (String)comboBoxMapeamento.getSelectedItem();
		switch(mapeamento)
		{
			case "Direct-mapped":
				stringBlocks = new  String[] { "1"};
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(stringBlocks));
				break;
			case "Fully Associative":
				stringBlocks = new  String[] { (String)comboBoxnBlocos.getSelectedItem() };
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(stringBlocks));
				break;
			case "Set-associative":
				stringBlocks = new  String[] { "1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};
				for(int i = 11; i > comboBoxnBlocos.getSelectedIndex(); i--)
				{
					stringBlocks[i] = null;
				}
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(stringBlocks));
				break;
		}
		
	}
	
	private void updateSizeCache()
	{
		int total;
		
		total = Integer.parseInt((String)comboBoxnPalavrasBloco.getSelectedItem()) * Integer.parseInt((String)comboBoxnBlocos.getSelectedItem()) * 4;
		textTamanhoCache.setText(Integer.toString(total));
		
	}
	
	private void updateAddress()
	{
		int nBlocks;
		int blocksSize;
		int setSize;
		int tag, index, blockOffset;
		
		nBlocks = Integer.parseInt((String)comboBoxnBlocos.getSelectedItem());
		blocksSize = Integer.parseInt((String)comboBoxnPalavrasBloco.getSelectedItem());
		setSize = Integer.parseInt((String)comboBoxBlocosConjunto.getSelectedItem());
		
		index = (int) (Math.log(nBlocks/setSize)/Math.log(2));
		blockOffset = ((int) (Math.log(blocksSize)/Math.log(2)));
		tag = 32 - index - blockOffset;
		
		lblTagBits.setText(tag + " bits");
		lblIndexBits.setText(index + " bits");
		lblBlockOffsetBits.setText( blockOffset + " bits");
		
		
		
	}
	
	
	public void updateAddress(int address)
	{
		lblNewLabel.setText("<html>"+ "<font color='red'>" + controller.getAddress().tag(address) + "</font>" +  "<font color='blue'>" + controller.getAddress().index(address) + "</font>"  + "<font color='green'>" + controller.getAddress().blockOffset(address) + "</font>" + "<font color='green'>"+ controller.getAddress().byteOffset(address) +"</font>" + "</html>");
	}
	
	
	private void buttonSalvar() {
		String tipoCacheSelecionado = "";
		
		
		
		if(buttonGroup.getSelection() == radioCacheDados ) {
			tipoCacheSelecionado = "Dados";
		}
		else if(buttonGroup.getSelection() == radioCacheInstrucoes ) {
			tipoCacheSelecionado = "Instrucoes";
		}
		else if(buttonGroup.getSelection() == radioCacheUnificada ) {
			tipoCacheSelecionado = "Unificada";
		}
				
		controller.setController(tipoCacheSelecionado, comboBoxMapeamento.getSelectedItem().toString() , comboBoxPoliticaSubstituicao.getSelectedItem().toString(), Integer.parseInt((String) comboBoxBlocosConjunto.getSelectedItem()), Integer.parseInt((String) comboBoxnBlocos.getSelectedItem()),
				Integer.parseInt((String) comboBoxnPalavrasBloco.getSelectedItem()), Integer.parseInt(textTempoAcessoCache.getText()), Integer.parseInt(textTempoAcessoMemoria.getText()));
	}	
}
