package mars.cache.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mars.cache.swing.staticcache.*;
import mars.cache.swing.statistic.Statistic;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import mars.cache.Controller;
import javax.swing.border.CompoundBorder;
import java.awt.Component;
import java.awt.SystemColor;

public class InterfaceCache extends JPanel {

	//private JPanel contentPane;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textTempoAcessoCache;
	private JTextField textTempoAcessoMemoria;
	private String[] stringBlocks;
	private JComboBox<String> comboBoxBlocosConjunto;
	private JComboBox<String> comboBoxnPalavrasBloco;
	private JComboBox<String> comboBoxnBlocos;
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
	private int widthTable = 346;
	private int caseTable = 0; 
	private Statistic statisticPanel;
	private JTextArea txtPlacement;
	
	
	/**
	 * Create the panel.
	 */
	public InterfaceCache(Controller controller) {
		this.controller = controller;

		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);*/
		setBounds(100, 100, 800, 580);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 578, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		Line line = new Line();
		line.setBounds(0, 0, 783, 539);
		
		
		
		
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
		radioCacheDados.setText("Data cache");
		
		radioCacheInstrucoes = new JRadioButton();
		radioCacheInstrucoes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonGroup.add(radioCacheInstrucoes);
		radioCacheInstrucoes.setText("Instruction cache");
		
		radioCacheUnificada = new JRadioButton();
		radioCacheUnificada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonGroup.add(radioCacheUnificada);
		radioCacheUnificada.setText("Unified cache");
		
		JSeparator separator2 = new JSeparator();
		separator2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel labelOrganizacaoCache = new JLabel();
		labelOrganizacaoCache.setText("Cache Organization");
		labelOrganizacaoCache.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator3 = new JSeparator();
		separator3.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel labelMapeamento = new JLabel();
		labelMapeamento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelMapeamento.setText("Placement policy");
		
		JLabel lblBlockReplacementPolicy = new JLabel();
		lblBlockReplacementPolicy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBlockReplacementPolicy.setText("Block replacement policy");
		
		JLabel lblSet = new JLabel();
		lblSet.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSet.setText("Set size (blocks)");
		
		comboBoxPoliticaSubstituicao = new JComboBox<String>();
		comboBoxPoliticaSubstituicao.setEnabled(false);
		comboBoxPoliticaSubstituicao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxPoliticaSubstituicao.setModel(new DefaultComboBoxModel(new String[] {"LRU", "LRU Aproximado", "FIFO"}));
		comboBoxPoliticaSubstituicao.setSelectedIndex(0);
		
		comboBoxBlocosConjunto = new JComboBox<String>();
		comboBoxBlocosConjunto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(new String[] {"1"}));
				comboBoxBlocosConjunto.setSelectedIndex(0);
				comboBoxBlocosConjunto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateBlocks();
						updateAddress();
						
					}
				});
				
				JLabel lblNumberOf = new JLabel();
				lblNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblNumberOf.setText("Number of blocks");
				
				JLabel lblCacheBlockSize = new JLabel();
				lblCacheBlockSize.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblCacheBlockSize.setText("Block size (words)");
				
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
						updateBlocks();
						updateSizeCache();
						updateAddress();
						
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
				lblCacheAccessTime.setText("Cache access time (cycles)");
				
				textTempoAcessoCache = new JTextField();
				textTempoAcessoCache.setAlignmentX(Component.LEFT_ALIGNMENT);
				textTempoAcessoCache.setText("10");
				
				JLabel labelTempoAcessoMemoria = new JLabel();
				labelTempoAcessoMemoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
				labelTempoAcessoMemoria.setText("Memory access time (cycles)");
				
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
				
				
				
				
				table = new JTable();
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
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
				
				
				table.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				
				table.setRowHeight(30);
						
				table_1 = new JTable();
				table_1.setShowGrid(false);
				table_1.setModel(new DefaultTableModel(
						new Object[][] {
							{"30 bits", "2 bits"},},
						new String[] {
							"Tag", "ByteOffset"
							}
				));
				table_1.setBackground(SystemColor.menu);
				table_1.setRowSelectionAllowed(false);
				table_1.setRowHeight(30);
				table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				table_1.setEnabled(false);
						
				table_1.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
				table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
							
									
							
							
								
								JLabel lblAddressBits = new JLabel("Address (32 bits)");
								
								labelAddressByteOffset = new JLabel("<html> Main memory addressed by bytes &rarr; 2-bit ByteOffset </html>");
								labelAddressByteOffset.setFont(new Font("Tahoma", Font.PLAIN, 12));
								
								labelAddressBlockOffset = new JLabel("<html> Blocks of "+ 1 +" = 2<sup> " + 0 + " </sup> words &rarr;  There is no block offset </html>");
								labelAddressBlockOffset.setFont(new Font("Tahoma", Font.PLAIN, 12));
								
								labelAddressIndex = new JLabel("<html> Cache with 1 blocks and associativity 1 &rarr; Number of cache sets = 1 = 2 <sup>0</sup> sets &rarr; There is no index </html>");
								labelAddressIndex.setFont(new Font("Tahoma", Font.PLAIN, 12));
								
								labelAddressTag = new JLabel("<html> 32-bit memory address &rarr; Tag of 32 - 0 - 0 - 2 = 30-bit Tag </html>");
								labelAddressTag.setFont(new Font("Tahoma", Font.PLAIN, 12));
								
								txtPlacement = new JTextArea();
								txtPlacement.setText("Direct-mapped");
								txtPlacement.setRows(5);
								txtPlacement.setEditable(false);
								txtPlacement.setColumns(20);
								
								
								
								
								
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
													.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_panelConfiguracoes.createSequentialGroup()
															.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNumberOf, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblSet, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
															.addGap(18)
															.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
																.addComponent(comboBoxnBlocos, 0, 93, Short.MAX_VALUE)
																.addComponent(comboBoxBlocosConjunto, 0, 93, Short.MAX_VALUE)))
														.addGroup(gl_panelConfiguracoes.createSequentialGroup()
															.addComponent(labelMapeamento, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(txtPlacement, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
													.addGap(18)
													.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_panelConfiguracoes.createSequentialGroup()
															.addComponent(labelTamanhoCache, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(textTamanhoCache, 0, 0, Short.MAX_VALUE))
														.addGroup(gl_panelConfiguracoes.createSequentialGroup()
															.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
																.addComponent(lblBlockReplacementPolicy, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblCacheBlockSize, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
																.addComponent(comboBoxnPalavrasBloco, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(comboBoxPoliticaSubstituicao, 0, 84, Short.MAX_VALUE)))))
												.addComponent(separator4, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelTempoAcesso, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
												.addComponent(separator5, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addComponent(lblCacheAccessTime, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
													.addGap(58)
													.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(labelTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
													.addGap(28)
													.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
												.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
												.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE))
											.addContainerGap(37, Short.MAX_VALUE))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addContainerGap()
											.addComponent(separator, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(37, Short.MAX_VALUE))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addContainerGap()
											.addComponent(labelOrganizacaoCache, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(676, Short.MAX_VALUE))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addContainerGap()
											.addComponent(labelTipoArazenamento, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
											.addContainerGap(607, Short.MAX_VALUE))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addGap(320)
											.addComponent(lblAddressBits)
											.addContainerGap(408, Short.MAX_VALUE))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addGap(188)
											.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
												.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
												.addComponent(table, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
											.addContainerGap(276, Short.MAX_VALUE))
										.addGroup(gl_panelConfiguracoes.createSequentialGroup()
											.addContainerGap()
											.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
														.addComponent(labelAddressByteOffset, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
														.addComponent(labelAddressBlockOffset, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)
														.addComponent(labelAddressTag, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE))
													.addContainerGap(296, Short.MAX_VALUE))
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.TRAILING)
														.addComponent(btnSalvar)
														.addComponent(labelAddressIndex, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
													.addGap(58))))
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
											.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNumberOf)
												.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.BASELINE)
													.addComponent(comboBoxnBlocos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(lblCacheBlockSize)
													.addComponent(comboBoxnPalavrasBloco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
											.addGap(6)
											.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblBlockReplacementPolicy)
												.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSet)
												.addComponent(comboBoxBlocosConjunto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addGap(9)
													.addComponent(textTamanhoCache, 0, 16, Short.MAX_VALUE))
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.BASELINE)
														.addComponent(labelMapeamento)
														.addComponent(txtPlacement, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
														.addComponent(labelTamanhoCache))))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(separator4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(labelTempoAcesso)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(separator5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
														.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_panelConfiguracoes.createSequentialGroup()
															.addGap(3)
															.addComponent(labelTempoAcessoMemoria))
														.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_panelConfiguracoes.createSequentialGroup()
													.addGap(9)
													.addComponent(lblCacheAccessTime)))
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
											.addGap(2)
											.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(labelAddressByteOffset, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(labelAddressBlockOffset, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(labelAddressIndex, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(labelAddressTag, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSalvar)
											.addGap(40))
								);
								panelConfiguracoes.setLayout(gl_panelConfiguracoes);
		//tabbedPane.;
		
		//JPanel panel_2 = new JPanel();
		
		//panel_2.setLayout(null);
		
		
		//panel_2.add(line);	
								
		staticCache = new StaticCache(controller);
		staticCache.setBorder(BorderFactory.createLineBorder(Color.black));
		staticCache.setPreferredSize(new Dimension(800, 600));
		scrollPane = new JScrollPane(staticCache, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		tabbedPane.addTab("Cache", null, scrollPane, null);
								
		
		statisticPanel = new Statistic();
		tabbedPane.addTab("Performace", null, statisticPanel, null);
		
	
	
				
		this.setLayout(gl_contentPane);
	}
	
	
	
	private void updateBlocks()
	{
		int nBlocks, setSize;
		int item1 = comboBoxBlocosConjunto.getSelectedIndex();
		int item2 = comboBoxnBlocos.getSelectedIndex();

		
		stringBlocks = new  String[] { "1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};
		String[] newStringBlocks = new String[comboBoxnBlocos.getSelectedIndex()+1];
		for(int i =0; i < comboBoxnBlocos.getSelectedIndex()+1; i++)
		{
			newStringBlocks[i] = stringBlocks[i];
		}

		comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(newStringBlocks));
		if(item1 <= item2)
		{
			comboBoxBlocosConjunto.setSelectedIndex(item1);
		}
		else
		{
			comboBoxBlocosConjunto.setSelectedIndex(item2);
		}
		
		nBlocks = Integer.parseInt((String)comboBoxnBlocos.getSelectedItem());
		setSize = Integer.parseInt((String)comboBoxBlocosConjunto.getSelectedItem());
		
		if(nBlocks == setSize)
		{
			txtPlacement.setText("Direct-mapped");
			comboBoxPoliticaSubstituicao.setEnabled(false);
			
		}
		else if (setSize == 1)
		{
			txtPlacement.setText("Fully Associative");
			comboBoxPoliticaSubstituicao.setEnabled(true);
		}
		else
		{
			txtPlacement.setText("Set-associative");
			comboBoxPoliticaSubstituicao.setEnabled(true);
		}
		
		
		
		
		
		
		
		/*String mapeamento = (String)comboBoxMapeamento.getSelectedItem();
		switch(mapeamento)
		{
			case "Direct-mapped":
				stringBlocks = new  String[] { "1"};
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(stringBlocks));
				break;
			case "Fully Associative":
				stringBlocks = new  String[] {  (String)comboBoxnBlocos.getSelectedItem() };
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
		}*/	
	}
	
	private void updateSizeCache()
	{
		int total;
		
		total = Integer.parseInt((String)comboBoxnPalavrasBloco.getSelectedItem()) * Integer.parseInt((String)comboBoxnBlocos.getSelectedItem()) * 4;
		textTamanhoCache.setText(Integer.toString(total));
		
	}
	
	private void updateAddress()
	{
		
		
		
		int BlockOffsetLenght = (int) (Math.log(Integer.parseInt((String) comboBoxnPalavrasBloco.getSelectedItem()))/Math.log(2));;
		int BlocosConjunto = Integer.parseInt((String) comboBoxBlocosConjunto.getSelectedItem());
		int nBlocos = Integer.parseInt((String) comboBoxnBlocos.getSelectedItem());
		int IndexLenght = (int) (Math.log((nBlocos / BlocosConjunto))/Math.log(2));
		int TagLenght = 32 - IndexLenght - BlockOffsetLenght - 2;

		if(BlockOffsetLenght == 0 && IndexLenght == 0)
			caseTable = 0;
		if(BlockOffsetLenght == 0 && IndexLenght != 0)
			caseTable = 1;
		if(BlockOffsetLenght != 0 && IndexLenght == 0)
			caseTable = 2;
		if(BlockOffsetLenght != 0 && IndexLenght != 0)
			caseTable = 3;
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		
		
		switch(caseTable)
		{
			case 0:
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Tag", "ByteOffset"},
					},
					new String[] {
						"TAG", "ByteOffset"
					}
				));
				
				
				table.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	
				table_1.setModel(new DefaultTableModel(
						new Object[][] {
							{"30 bits", "2 bits"},},
						new String[] {
							"Tag", "ByteOffset"
							}
				));					
				table_1.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80);
				table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
				table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				break;
			case 1:

				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"Tag", "Index", "ByteOffset"},
						},
						new String[] {
							"TAG", "Index", "ByteOffset"
						}
					));
					
					
					table.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80 - 80);
					table.getColumnModel().getColumn(1).setPreferredWidth(80);
					table.getColumnModel().getColumn(2).setPreferredWidth(80);
					
					table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					
		
					table_1.setModel(new DefaultTableModel(
							new Object[][] {
								{TagLenght +" bits", IndexLenght +" bits", "2 bits"},},
							new String[] {
								"Tag", "Index","ByteOffset"
								}
					));					
					table_1.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80 - 80);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					break;
			case 2:

				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"Tag", "BlockOffset", "ByteOffset"},
						},
						new String[] {
							"TAG", "BlockOffset", "ByteOffset"
						}
					));
					
					
					table.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80 - 80);
					table.getColumnModel().getColumn(1).setPreferredWidth(80);
					table.getColumnModel().getColumn(2).setPreferredWidth(80);
					
					table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					
		
					table_1.setModel(new DefaultTableModel(
							new Object[][] {
								{TagLenght +" bits", BlockOffsetLenght +" bits", "2 bits"},},
							new String[] {
								"Tag", "BlockOffset","ByteOffset"
								}
					));					
					table_1.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80 - 80);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
					table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					break;
			case 3:

				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"Tag", "Index", "BlockOffset", "ByteOffset"},
						},
						new String[] {
							"TAG", "Index", "BlockOffset", "ByteOffset"
						}
					));
					
					
					table.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80 - 80 - 70);
					table.getColumnModel().getColumn(1).setPreferredWidth(70);
					table.getColumnModel().getColumn(2).setPreferredWidth(80);
					table.getColumnModel().getColumn(3).setPreferredWidth(80);
					
					table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		
					table_1.setModel(new DefaultTableModel(
							new Object[][] {
								{TagLenght +" bits", IndexLenght +" bits", BlockOffsetLenght +" bits", "2 bits"},},
							new String[] {
								"Tag", "Index", "BlockOffset","ByteOffset"
								}
					));					
					table_1.getColumnModel().getColumn(0).setPreferredWidth(widthTable - 80 - 80 - 70);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(70);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
					table_1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
					break;
			
		}
		//lblTagBits.setText(TagLenght + " bits");
		//lblIndexBits.setText(IndexLenght + " bits");
		//lblBlockOffsetBits.setText( BlockOffsetLenght + " bits");
		updateAddressing();
	}
	
	private void updateAddressing() {
		
	
		//lblAddressByteOffset.setText("<html> Main memory addressed by words &rarr; <font color='gray'> There is no byte offset </font> </html>");
		int BlockOffsetLenght = (int) (Math.log(Integer.parseInt((String) comboBoxnPalavrasBloco.getSelectedItem()))/Math.log(2));;
		int BlocosConjunto = Integer.parseInt((String) comboBoxBlocosConjunto.getSelectedItem());
		int nBlocos = Integer.parseInt((String) comboBoxnBlocos.getSelectedItem());
		int IndexLenght = (int) (Math.log((nBlocos / BlocosConjunto))/Math.log(2));
		int TagLenght = 32 - IndexLenght - BlockOffsetLenght - 2;
		
		
		
		if(BlockOffsetLenght == 0) {
			labelAddressBlockOffset.setText("<html> Blocks of "+ 1 +" = 2<sup> " + 0 + " </sup> words &rarr;  There is no block offset </html>");
		}
		else
		{
			labelAddressBlockOffset.setText("<html> Blocks of "+ BlocosConjunto +" = 2<sup>" + BlockOffsetLenght + "</sup> words &rarr; "+ BlockOffsetLenght + "-bit  BlockOffset</html>");
		}
		if(IndexLenght == 0) {
			labelAddressIndex.setText("<html> Cache with "+ nBlocos + " blocks and associativity "+ BlocosConjunto +" &rarr; Number of cache sets = " + nBlocos +  "/" + BlocosConjunto + " = " + (nBlocos / BlocosConjunto)+ " = 2 <sup>" + IndexLenght + "</sup> sets &rarr; There is no index </html>");
		}
		else
		{
			labelAddressIndex.setText("<html> Cache with "+ nBlocos + " blocks and associativity "+ BlocosConjunto +" &rarr; Number of cache sets = " + nBlocos + "/" + BlocosConjunto + " = " + (nBlocos / BlocosConjunto) + " = 2 <sup>" + IndexLenght + "</sup> sets &rarr; "+ IndexLenght +"-bit Index </html>");
		}
		
		labelAddressTag.setText("<html> 32-bit memory address &rarr; Tag of 32 - "+ 2 +" - " + BlockOffsetLenght + " - " +  IndexLenght +" = " + TagLenght + "-bit Tag </html>");

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
				
		controller.setController(tipoCacheSelecionado, txtPlacement.getText() , comboBoxPoliticaSubstituicao.getSelectedItem().toString(), Integer.parseInt((String) comboBoxBlocosConjunto.getSelectedItem()), Integer.parseInt((String) comboBoxnBlocos.getSelectedItem()),
				Integer.parseInt((String) comboBoxnPalavrasBloco.getSelectedItem()), Integer.parseInt(textTempoAcessoCache.getText()), Integer.parseInt(textTempoAcessoMemoria.getText()));
	
		staticCache = new StaticCache(controller);
		scrollPane = new JScrollPane(staticCache, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		staticCache.setPreferredSize();
		scrollPane.setPreferredSize(new Dimension(1, 1));
		
		statisticPanel.apply(controller.getnBlocos(), controller.getBlocosConjunto(), controller.getnPalavrasBloco(), Integer.parseInt(textTempoAcessoCache.getText()), Integer.parseInt(textTempoAcessoMemoria.getText()) );
			
	}	
	public void update(int address)
	{
		statisticPanel.update(address);
	}

	public int getTipoCache() {
		int tipoCacheSelecionado = 0;
	
		
		
		if(radioCacheDados.isSelected()) {
			tipoCacheSelecionado = 0;
		}
		else if(radioCacheInstrucoes.isSelected() ) {
			tipoCacheSelecionado = 1;
		}
		else if(radioCacheUnificada.isSelected() ) {
			tipoCacheSelecionado = 2;
		}
			return tipoCacheSelecionado;
	}
	
}