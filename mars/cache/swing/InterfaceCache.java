package mars.cache.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import mars.cache.Controller;

public class InterfaceCache extends JPanel {

	//private JPanel contentPane;
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
	
	
	/**
	 * Create the panel.
	 */
	public InterfaceCache(Controller controller) {
		this.controller = controller;
		
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);*/
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
		);
		
		JPanel panelConfiguracoes = new JPanel();
		tabbedPane.addTab("Configura\u00E7\u00F5es", null, panelConfiguracoes, null);
		
		JLabel labelTipoArazenamento = new JLabel();
		labelTipoArazenamento.setText("Tipo de Armazenamento da Cache");
		labelTipoArazenamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator = new JSeparator();
		
		radioCacheDados = new JRadioButton();
		radioCacheDados.setSelected(true);
		buttonGroup.add(radioCacheDados);
		radioCacheDados.setText("Cache de Dados");
		
		radioCacheInstrucoes = new JRadioButton();
		buttonGroup.add(radioCacheInstrucoes);
		radioCacheInstrucoes.setText("Cache de Instru\u00E7\u00F5es");
		
		radioCacheUnificada = new JRadioButton();
		buttonGroup.add(radioCacheUnificada);
		radioCacheUnificada.setText("Cache Unificada");
		
		JSeparator separator2 = new JSeparator();
		
		JLabel labelOrganizacaoCache = new JLabel();
		labelOrganizacaoCache.setText("Organiza\u00E7\u00E3o da Cache");
		labelOrganizacaoCache.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator3 = new JSeparator();
		
		JLabel labelMapeamento = new JLabel();
		labelMapeamento.setText("Mapeamento");
		
		JLabel label_3 = new JLabel();
		label_3.setText("Pol\u00EDtica de Substitui\u00E7\u00E3o");
		
		JLabel label_4 = new JLabel();
		label_4.setText("Blocos por Conjunto");
		
		comboBoxBlocosConjunto = new JComboBox<String>();
		comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(new String[] {"1"}));
		comboBoxBlocosConjunto.setSelectedIndex(0);
		
		
		comboBoxMapeamento = new JComboBox<String>();
		comboBoxMapeamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBlocks((String)comboBoxMapeamento.getSelectedItem());
			}
		});
		comboBoxMapeamento.setModel(new DefaultComboBoxModel(new String[] {"Direto", "Associativo", "Conjunto Associativo"}));
		comboBoxMapeamento.setSelectedIndex(0);

		
		comboBoxPoliticaSubstituicao = new JComboBox<String>();
		comboBoxPoliticaSubstituicao.setModel(new DefaultComboBoxModel(new String[] {"LRU", "LRU Aproximado", "FIFO"}));
		comboBoxPoliticaSubstituicao.setSelectedIndex(0);
		
		
		JLabel label_5 = new JLabel();
		label_5.setText("N\u00FAmero de Blocos");
		
		JLabel label_6 = new JLabel();
		label_6.setText("N\u00FAmero de Palavras por Bloco");
		
		JLabel labelTamanhoCache = new JLabel();
		labelTamanhoCache.setText("Tamanho da Cache (Bytes)");
		
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
			}
		});
		
		comboBoxnBlocos = new JComboBox<String>();
		comboBoxnBlocos.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"}));
		comboBoxnBlocos.setSelectedIndex(0);
		comboBoxnBlocos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				updateSizeCache();
			}
		});
		
		
		JSeparator separator4 = new JSeparator();
		
		JLabel labelTempoAcesso = new JLabel();
		labelTempoAcesso.setText("Tempo de Acesso");
		labelTempoAcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JSeparator separator5 = new JSeparator();
		
		JLabel label_9 = new JLabel();
		label_9.setText("Tempo Acesso Cache (ms)");
		
		textTempoAcessoCache = new JTextField();
		textTempoAcessoCache.setText("10");
		
		JLabel labelTempoAcessoMemoria = new JLabel();
		labelTempoAcessoMemoria.setText("Tempo de Acesso a Mem\u00F3ria (ms)");
		
		textTempoAcessoMemoria = new JTextField();
		textTempoAcessoMemoria.setText("100");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonSalvar();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		
		
		
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
						.addComponent(labelTipoArazenamento, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelOrganizacaoCache, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addComponent(labelMapeamento, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(comboBoxMapeamento, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(98)
							.addComponent(comboBoxnBlocos, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelConfiguracoes.createSequentialGroup()
									.addGap(42)
									.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addComponent(comboBoxnPalavrasBloco, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelConfiguracoes.createSequentialGroup()
									.addGap(97)
									.addComponent(comboBoxBlocosConjunto, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(labelTamanhoCache, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textTamanhoCache, 0, 0, Short.MAX_VALUE))))
						.addComponent(separator4, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelTempoAcesso, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator5, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnSalvar)
							.addGroup(gl_panelConfiguracoes.createSequentialGroup()
								.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addGap(78)
								.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(labelTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxMapeamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxnBlocos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(labelMapeamento))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(label_5)))
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxPoliticaSubstituicao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxnPalavrasBloco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(label_3))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(label_6)))
					.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(label_4))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(9)
							.addComponent(labelTamanhoCache))
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panelConfiguracoes.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelConfiguracoes.createSequentialGroup()
									.addGap(1)
									.addComponent(textTamanhoCache, 0, 0, Short.MAX_VALUE))
								.addComponent(comboBoxBlocosConjunto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
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
							.addComponent(label_9))
						.addComponent(textTempoAcessoCache, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfiguracoes.createSequentialGroup()
							.addGap(3)
							.addComponent(labelTempoAcessoMemoria))
						.addComponent(textTempoAcessoMemoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(159)
					.addComponent(btnSalvar)
					.addGap(106))
		);
		panelConfiguracoes.setLayout(gl_panelConfiguracoes);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Endere\u00E7amento", null, panel_1, null);
				
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
		this.setLayout(gl_contentPane);
	}
	
	private void updateBlocks(String mapeamento)
	{
		switch(mapeamento)
		{
			case "Direto":
				stringBlocks = new  String[] { "1"};
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(stringBlocks));
				break;
			case "Associativo":
				stringBlocks = new  String[] { "2048" };
				comboBoxBlocosConjunto.setModel(new DefaultComboBoxModel(stringBlocks));
				break;
			case "Conjunto Associativo":
				stringBlocks = new  String[] { "1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};
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
			tipoCacheSelecionado = "Instrucoes";
		}
				
		
		controller.setController(tipoCacheSelecionado, comboBoxMapeamento.getSelectedItem().toString() , comboBoxPoliticaSubstituicao.getSelectedItem().toString(), Integer.parseInt((String) comboBoxBlocosConjunto.getSelectedItem()), Integer.parseInt((String) comboBoxnBlocos.getSelectedItem()),
				Integer.parseInt((String) comboBoxnPalavrasBloco.getSelectedItem()), Integer.parseInt(textTempoAcessoCache.getText()), Integer.parseInt(textTempoAcessoMemoria.getText()));
		lblNewLabel.setText("AEHOOO");
	}	
}
