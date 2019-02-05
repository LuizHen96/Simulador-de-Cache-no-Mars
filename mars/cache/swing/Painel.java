package mars.cache.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mars.cache.Controller;
import mars.cache.swing.staticcache.StaticCache;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
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
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.SystemColor;

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
	private Controller controller;
	private JRadioButton radioCacheDados;
	private JRadioButton radioCacheInstrucoes;
	private JRadioButton radioCacheUnificada;
	private JTable table;
	private JLabel labelAddressByteOffset;
	private JLabel labelAddressBlockOffset;
	private JLabel labelAddressIndex;
	private JLabel labelAddressTag;
	private StaticCache staticCache;
	private JScrollPane scrollPane;
	private JTable table_1;


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
		radioCacheDados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		radioCacheDados.setSelected(true);
		buttonGroup.add(radioCacheDados);
		radioCacheDados.setText("Data Cache");
		
		radioCacheInstrucoes = new JRadioButton();
		radioCacheInstrucoes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonGroup.add(radioCacheInstrucoes);
		radioCacheInstrucoes.setText("Instruction Cache");
		
		radioCacheUnificada = new JRadioButton();
		radioCacheUnificada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonGroup.add(radioCacheUnificada);
		radioCacheUnificada.setText("Unified Cache");
		
		JSeparator separator2 = new JSeparator();
		separator2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel labelOrganizacaoCache = new JLabel();
		labelOrganizacaoCache.setText("Cache Organization");
		labelOrganizacaoCache.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator3 = new JSeparator();
		separator3.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel labelMapeamento = new JLabel();
		labelMapeamento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelMapeamento.setText("Placement Policy");
		
		JLabel lblBlockReplacementPolicy = new JLabel();
		lblBlockReplacementPolicy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBlockReplacementPolicy.setText("Block Replacement Policy");
		
		JLabel lblSet = new JLabel();
		lblSet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSet.setText("Set size (blocks)");
		
		comboBoxMapeamento = new JComboBox<String>();
		comboBoxMapeamento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxMapeamento.setModel(new DefaultComboBoxModel(new String[] {"Direct-mapped", "Fully Associative", "Set-associative"}));
		comboBoxMapeamento.setSelectedIndex(0);
		comboBoxMapeamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBlocks();
				updateAddress();
			}
		});
		
		comboBoxPoliticaSubstituicao = new JComboBox<String>();
		comboBoxPoliticaSubstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPoliticaSubstituicao.setModel(new DefaultComboBoxModel(new String[] {"LRU", "LRU Aproximado", "FIFO"}));
		comboBoxPoliticaSubstituicao.setSelectedIndex(0);
		
		comboBoxBlocosConjunto = new JComboBox<String>();
		comboBoxBlocosConjunto.setFont(new Font("Tahoma", Font.PLAIN, 11));

		comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(new String[] {"1"}));
		comboBoxBlocosConjunto.setSelectedIndex(0);
		comboBoxBlocosConjunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAddress();
			}
		});
		
		JLabel lblNumberOf = new JLabel();
		lblNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumberOf.setText("Number of blocks");
		
		JLabel lblCacheBlockSize = new JLabel();
		lblCacheBlockSize.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCacheBlockSize.setText("Block size ( words)");
		
		JLabel labelTamanhoCache = new JLabel();
		labelTamanhoCache.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelTamanhoCache.setText("Cache size (bytes)");
		
		textTamanhoCache = new JTextArea();
		textTamanhoCache.setEditable(false);
		textTamanhoCache.setText("4");
		textTamanhoCache.setRows(5);
		textTamanhoCache.setColumns(20);
		
		comboBoxnPalavrasBloco = new JComboBox<String>();
		comboBoxnPalavrasBloco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxnPalavrasBloco.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"}));
		comboBoxnPalavrasBloco.setSelectedIndex(0);
		comboBoxnPalavrasBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSizeCache();
				updateAddress();
			}
		});
		
		comboBoxnBlocos = new JComboBox<String>();
		comboBoxnBlocos.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
		separator4.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel labelTempoAcesso = new JLabel();
		labelTempoAcesso.setText("Access Time");
		labelTempoAcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator5 = new JSeparator();
		separator5.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lblCacheAccessTime = new JLabel();
		lblCacheAccessTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCacheAccessTime.setText("Cache Access Time (ms)");
		
		textTempoAcessoCache = new JTextField();
		textTempoAcessoCache.setAlignmentX(Component.LEFT_ALIGNMENT);
		textTempoAcessoCache.setText("10");
		
		JLabel labelTempoAcessoMemoria = new JLabel();
		labelTempoAcessoMemoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelTempoAcessoMemoria.setText("Memory Access Time (ms)");
		
		textTempoAcessoMemoria = new JTextField();
		textTempoAcessoMemoria.setText("100");
		
		JButton btnSalvar = new JButton("Apply");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSalvar();
				updateAddressing();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblAddress = new JLabel();
		lblAddress.setText("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		int widthTable = 346;
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Tag", "ByteOffset"},
			},
			new String[] {
				"TAG", "ByteOffset"
			}
		));
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		table.setRowHeight(30);
	
		
		JLabel lblAddressBits = new JLabel("Address (32 bits)");
		
		labelAddressByteOffset = new JLabel("<html> Main memory addressed by bytes &rarr; 2-bit ByteOffset </html>");
		labelAddressByteOffset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		labelAddressBlockOffset = new JLabel("<html> Blocks of "+ 1 +" = 2<sup> " + 0 + " </sup> words &rarr;  There is no block offset </html>");
		labelAddressBlockOffset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		labelAddressIndex = new JLabel("<html> Cache with 1 blocks and associativity 1 &rarr; Number of cache sets = 1 = 2 <sup>0</sup> sets &rarr; There is no index </html>");
		labelAddressIndex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		labelAddressTag = new JLabel("<html> 32-bit memory address &rarr; Tag of 32 - 0 - 0 - 2 = 30-bit Tag </html>");
		labelAddressTag.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		table_1 = new JTable();
		table_1.setBackground(SystemColor.menu);
		table_1.setShowGrid(false);
		table_1.setBorder(null);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"30 bits", "2 bits"},
			},
			new String[] {
				"New column2", "New column"
			}
		));
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setRowSelectionAllowed(false);
		table_1.setRowHeight(30);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_1.setEnabled(false);
		
	
		
		
		
		
		GroupLayout gl_panelConfiguracoes = new GroupLayout(panelConfiguracoes);
		gl_panelConfiguracoes.setHorizontalGroup(
			gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(radioCacheDados, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(radioCacheInstrucoes, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(radioCacheUnificada, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(labelMapeamento, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(201)
							.addComponent(lblNumberOf, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBlockReplacementPolicy, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
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
									.addGap(23)
									.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblCacheBlockSize, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
											.addGap(39))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addComponent(comboBoxMapeamento, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
											.addGap(218)))
									.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBoxnBlocos, 0, 59, Short.MAX_VALUE)
										.addComponent(comboBoxnPalavrasBloco, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
						.addComponent(separator4, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelTempoAcesso, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator5, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(lblCacheAccessTime, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(78)
							.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(labelTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addGap(299)
					.addComponent(lblAddressBits)
					.addContainerGap(402, Short.MAX_VALUE))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(labelAddressByteOffset, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(labelAddressBlockOffset, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(labelAddressTag, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 148, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelAddressIndex, GroupLayout.PREFERRED_SIZE, 652, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addGap(23))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelOrganizacaoCache, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(649, Short.MAX_VALUE))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTipoArazenamento, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(580, Short.MAX_VALUE))
				.addGroup(gl_panelConfiguracoes.createSequentialGroup()
					.addGap(188)
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(249, Short.MAX_VALUE))
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
						.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.BASELINE)
							.addComponent(radioCacheInstrucoes)
							.addComponent(radioCacheDados))
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
							.addComponent(lblNumberOf)
							.addComponent(comboBoxnBlocos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxMapeamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(labelMapeamento))
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(lblCacheBlockSize))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(lblBlockReplacementPolicy))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxnPalavrasBloco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
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
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(151)
							.addComponent(btnSalvar))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(6)
							.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(labelAddressByteOffset, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelAddressBlockOffset, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelAddressIndex, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelAddressTag, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(100))
		);
		panelConfiguracoes.setLayout(gl_panelConfiguracoes);
		
		Line line = new Line();
		line.setBounds(0, 0, 783, 539);
		
		
	/*	staticCache = new StaticCache(controller);
		staticCache.setBorder(BorderFactory.createLineBorder(Color.black));
		staticCache.setPreferredSize(new Dimension(2000, 840));
		scrollPane = new JScrollPane(staticCache, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(2000, 840));
		tabbedPane.addTab("Cache", null, scrollPane, null);*/
		//tabbedPane.;
		
		//JPanel panel_2 = new JPanel();
		
		//panel_2.setLayout(null);
		
		
		//panel_2.add(line);	
		
		
		Canvas canvas = new Canvas();
		tabbedPane.addTab("New tab", null, canvas, null);
	
				
		getContentPane().setLayout(gl_contentPane);
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
				String[] newStringBlocks = new String[comboBoxnBlocos.getSelectedIndex()+1];
				for(int i =0; i < comboBoxnBlocos.getSelectedIndex()+1; i++)
				{
					newStringBlocks[i] = stringBlocks[i];
				}
	
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(newStringBlocks));
				comboBoxBlocosConjunto.setSelectedIndex(0);
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
		tag = 32 - index - blockOffset - 2;
		
		//lblTagBits.setText(tag + " bits");
		//lblIndexBits.setText(index + " bits");
		//lblBlockOffsetBits.setText( blockOffset + " bits");
		updateAddressing();
	}
	
	private void updateAddressing() {
		
	
		//lblAddressByteOffset.setText("<html> Main memory addressed by words &rarr; <font color='gray'> There is no byte offset </font> </html>");
		
		if(controller.getAddress().getBlockOffsetLenght() == 0) {
			labelAddressBlockOffset.setText("<html> Blocks of "+ 1 +" = 2<sup> " + 0 + " </sup> words &rarr;  There is no block offset </html>");
		}
		else
		{
			labelAddressBlockOffset.setText("<html> Blocks of "+ controller.getBlocosConjunto() +" = 2<sup>" + controller.getAddress().getBlockOffsetLenght() + "</sup> words &rarr; "+ controller.getAddress().getBlockOffsetLenght() + " bits </html>");
		}
		if(controller.getAddress().getIndexLenght() == 0) {
			labelAddressIndex.setText("<html> Cache with "+ controller.getnBlocos() + " blocks and associativity "+ controller.getnBlocos() +" &rarr; Number of cache sets = " + 1 +  "/" + 1+ " = " + 1+ " = 2 <sup>" + 0 + "</sup> sets &rarr; There is no index </html>");
		}
		else
		{
			labelAddressIndex.setText("<html> Cache with "+ controller.getnBlocos() + " blocks and associativity "+ (controller.getnBlocos()/controller.getBlocosConjunto()) +" &rarr; Number of cache sets = " + 1 +  + 1+ " = " + 1+ " = 2 <sup>" + 0 + "</sup> sets &rarr; There is no index </html>");
		}
		
		labelAddressTag.setText("<html> 32-bit memory address &rarr; Tag of 32 - "+ 0 +" - " + controller.getAddress().getBlockOffsetLenght() + " - " + controller.getAddress().getIndexLenght() +" = " + controller.getAddress().getTagLenght() + " bits </html>");

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
