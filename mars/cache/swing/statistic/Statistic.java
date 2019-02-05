package mars.cache.swing.statistic;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mars.cache.statistic.Cache;
import mars.cache.Address;

public class Statistic extends JPanel {
	private JTextField textMemoryAccess;
	private JTextField textHitCount;
	private JTextField textMissCount;
	private JTable table;
	private JProgressBar progressBar;
	private Cache cache;
	private int cacheAccessTime;
	private int memoryAccessTime;
	
	private long memoryAccess;
	private long hitCount;
	private long missCount;
	private String[][] lines = new String[5][];
	private int nLines;
	private Address address1;
	private int caseApply = 0;
	private JTextField txtAverageMemory;
	private JTextField txtMissesperInstruction;
	private int nPalavrasBloco;
	
	
	
	
	
	public Statistic() {
		cache = new Cache(1, 1, 1);
		
		nPalavrasBloco = 1;
		
		int BlockOffsetLenght = (int) (Math.log(4)/Math.log(2));		
		int IndexLenght = (int) (Math.log((4 / 2))/Math.log(2));
		int TagLenght = 32 - IndexLenght - BlockOffsetLenght - 2;
		
		address1 = new Address(TagLenght,  IndexLenght, BlockOffsetLenght, 2);
		
		setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 28, 700, 2);
		separator.setAlignmentX(0.0f);
		add(separator);
		
		JLabel lblMemoryAccess = new JLabel("Memory Access Count");
		lblMemoryAccess.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemoryAccess.setBounds(10, 47, 125, 14);
		add(lblMemoryAccess);
		
		textMemoryAccess = new JTextField();
		textMemoryAccess.setEditable(false);
		textMemoryAccess.setText("0");
		textMemoryAccess.setBounds(145, 44, 120, 20);
		add(textMemoryAccess);
		textMemoryAccess.setColumns(10);
		
		JLabel lblCacheHitCount = new JLabel("Cache Hit Count");
		lblCacheHitCount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCacheHitCount.setBounds(10, 72, 113, 14);
		add(lblCacheHitCount);
		
		textHitCount = new JTextField();
		textHitCount.setText("0");
		textHitCount.setEditable(false);
		textHitCount.setBounds(145, 69, 120, 20);
		textHitCount.setColumns(10);
		add(textHitCount);
		
		JLabel lblCacheMissCount = new JLabel("Cache Miss Count");
		lblCacheMissCount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCacheMissCount.setBounds(10, 97, 113, 14);
		add(lblCacheMissCount);
		
		textMissCount = new JTextField();
		textMissCount.setText("0");
		textMissCount.setEditable(false);
		textMissCount.setBounds(145, 94, 120, 20);
		textMissCount.setColumns(10);
		add(textMissCount);
		
		JLabel lblCacheMissRate = new JLabel("Cache Miss Rate");
		lblCacheMissRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCacheMissRate.setBounds(10, 122, 113, 14);
		add(lblCacheMissRate);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(115, 119, 150, 20);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setString("0,0%");
		add(progressBar);
		
		JLabel lblCachePerformace = new JLabel("Cache Performace");
		lblCachePerformace.setBounds(10, 11, 113, 14);
		lblCachePerformace.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblCachePerformace);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 147, 700, 146);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null,  null , null, null},
				},
				new String[] {
					"Address", "Byte offset", "Block number" , "Tag",  "Hit/Miss", "Block replaced"
				}
		 ));
		scrollPane.setViewportView(table);
		
		JLabel lblAverageMemoryAccess = new JLabel("Average memory access time");
		lblAverageMemoryAccess.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAverageMemoryAccess.setBounds(306, 47, 150, 14);
		add(lblAverageMemoryAccess);
		
		JLabel lblMissesPerInstruction = new JLabel("Misses per instruction");
		lblMissesPerInstruction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMissesPerInstruction.setBounds(306, 72, 150, 14);
		add(lblMissesPerInstruction);
		
		txtAverageMemory = new JTextField();
		txtAverageMemory.setText("0");
		txtAverageMemory.setEditable(false);
		txtAverageMemory.setColumns(10);
		txtAverageMemory.setBounds(470, 44, 120, 20);
		add(txtAverageMemory);
		
		txtMissesperInstruction = new JTextField();
		txtMissesperInstruction.setText("0");
		txtMissesperInstruction.setEditable(false);
		txtMissesperInstruction.setColumns(10);
		txtMissesperInstruction.setBounds(470, 69, 120, 20);
		add(txtMissesperInstruction);
		
		
		cacheAccessTime = 10;
		memoryAccessTime = 100;
	}
	
	public void apply(int nBlocos, int associatividade, int nPalavrasBloco, int cacheAccessTime, int memoryAccessTime)
	{
		this.cacheAccessTime = cacheAccessTime;
		this.memoryAccessTime = memoryAccessTime;
		this.nPalavrasBloco = nPalavrasBloco;
		
		
		cache = new Cache(nBlocos, associatividade, nPalavrasBloco);
		
		int BlockOffsetLenght = (int) (Math.log(nPalavrasBloco)/Math.log(2));		
		int IndexLenght = (int) (Math.log((nBlocos / associatividade))/Math.log(2));
		int TagLenght = 32 - IndexLenght - BlockOffsetLenght - 2;
		
		address1 = new Address(TagLenght,  IndexLenght, BlockOffsetLenght, 2);
		
		if(BlockOffsetLenght == 0 && IndexLenght == 0)
		{
			caseApply = 0;
		}
		else if(BlockOffsetLenght != 0 && IndexLenght == 0)
		{
			caseApply = 1;
		}
		else if(BlockOffsetLenght == 0 && IndexLenght != 0)
		{
			caseApply = 2;
		}
		else if(BlockOffsetLenght != 0 && IndexLenght != 0)
		{
			caseApply = 3;
		}
		
		switch(caseApply) {
			case 0:
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null},
						},
						new String[] {
							"Address", "Byte offset", "Block number", "Tag",  "Hit/Miss", "Block replaced"
						}
				 ));
				break;
			case 1:
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null, null},
						},
						new String[] {
							"Address", "Byte offset", "Block offset", "Block number", "Tag",  "Hit/Miss", "Block replaced"
						}
				 ));
				break;
			case 2:
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null, null},
						},
						new String[] {
							"Address", "Byte offset","Block number",  "Index", "Tag",  "Hit/Miss", "Block replaced"
						}
				));
			
				break;
			
			case 3:
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null ,null, null},
						},
						new String[] {
							"Address", "Byte offset", "Block offset", "Block number", "Index", "Tag",  "Hit/Miss", "Block replaced"
						}
					));
				break;
				
				
				
		}
			
		
		
		progressBar.setString("0,0%");
		memoryAccess = hitCount = missCount = 0;
		textMemoryAccess.setText(memoryAccess + "");
		textHitCount.setText(hitCount+"");
		textMissCount.setText(hitCount+"");
		progressBar.setValue(0);
		lines = new String[0][];
		nLines = 0;
		

		
		
		
		
	}
	
	public void update(int address)
	{
		int result;
		String hitMiss;
		memoryAccess++;
		result = cache.buscaInsereCache('R', address);
		
		if(result == 0)
		{
			hitCount++;
			hitMiss = "Hit";
		}
		else
		{
			missCount++;
			hitMiss = "Miss";
		}
		long value = (missCount*100/memoryAccess);
		double value2 = (double)missCount*100.0/(double)memoryAccess;
		String stringProgessBar = String.format("%.1f%%", value2);
		
		progressBar.setValue(Math.toIntExact(value));
		progressBar.setString(stringProgessBar);
		//String[] line;
		String[][] aux;
		
		switch(caseApply)
		{
			case 0:
				String[] line0 = {address + "", address1.byteOffset(address), address1.blockNumber(address, nPalavrasBloco ), address1.tag(address), hitMiss, cache.getBlocoSubstituido()};
				aux = new String[nLines][6];
				
				for(int i =0; i< nLines; i++)
					aux[i] = lines[i];
								
				lines = new String[nLines+1][6];
				
				for(int i=0; i< nLines; i++)
				{
					lines[i] = aux[i];
				}
				
				lines[nLines] = line0;
				nLines++;
				
				table.setModel(new DefaultTableModel(
						lines,
						new String[] {
							"Address", "Byte offset","Block number",  "Tag", "Hit/Miss", "Block replaced"
						}
					));
				break;
			case 1:
				String[] line1 = {address + "", address1.byteOffset(address), address1.blockOffset(address),  address1.blockNumber(address, nPalavrasBloco), address1.tag(address), hitMiss, cache.getBlocoSubstituido()};
				aux = new String[nLines][7];
				
				for(int i =0; i< nLines; i++)
					aux[i] = lines[i];
								
				lines = new String[nLines+1][7];
				
				for(int i=0; i< nLines; i++)
				{
					lines[i] = aux[i];
				}
				
				lines[nLines] = line1;
				nLines++;
				
				table.setModel(new DefaultTableModel(
						lines,
						new String[] {
							"Address", "Byte offset", "Block offset", "Block number", "Tag", "Hit/Miss", "Block replaced"
						}
					));
				break;
			case 2:
				String[] line2 = {address + "", address1.byteOffset(address),  address1.blockNumber(address, nPalavrasBloco), address1.index(address), address1.tag(address), hitMiss, cache.getBlocoSubstituido()};

				aux = new String[nLines][7];
				
				for(int i =0; i< nLines; i++)
					aux[i] = lines[i];
								
				lines = new String[nLines+1][7];
				
				for(int i=0; i< nLines; i++)
				{
					lines[i] = aux[i];
				}
				
				lines[nLines] = line2;
				nLines++;
				
				table.setModel(new DefaultTableModel(
						lines,
						new String[] {
							"Address", "Byte offset", "Block number", "Index", "Tag", "Hit/Miss", "Block replaced"
						}
					));
				break;
			case 3:
				String[] line3 = {address + "", address1.byteOffset(address), address1.blockOffset(address),  address1.blockNumber(address, nPalavrasBloco), address1.index(address), address1.tag(address), hitMiss, cache.getBlocoSubstituido()};
				aux = new String[nLines][8];
				
				for(int i =0; i< nLines; i++)
					aux[i] = lines[i];
								
				lines = new String[nLines+1][8];
				
				for(int i=0; i< nLines; i++)
				{
					lines[i] = aux[i];
				}
				
				lines[nLines] = line3;
				nLines++;
				
				table.setModel(new DefaultTableModel(
						lines,
						new String[] {
							"Address", "Byte offset", "Block offset", "Block number", "Index", "Tag", "Hit/Miss", "Block replaced"
						}
					));
				break;
				
		}
		
		
		//break;
		/*String[] line = {address + "", address1.byteOffset(address), address1.blockOffset(address), address1.index(address), hitMiss};
		String[][] aux = new String[nLines][5];
		
		for(int i =0; i< nLines; i++)
			aux[i] = lines[i];
						
		lines = new String[nLines+1][5];
		
		for(int i=0; i< nLines; i++)
		{
			lines[i] = aux[i];
		}
		
		lines[nLines] = line;
		nLines++;
		
		
		
		table.setModel(new DefaultTableModel(
				lines,
				new String[] {
					"Address", "Byte offset", "Block offset", "Index", "Hit/Miss"
				}
			));
		*/
		textMemoryAccess.setText(memoryAccess+"");;
		textHitCount.setText(hitCount+"");
		textMissCount.setText(missCount+"");
		
		double averageMemory = (double)cacheAccessTime + (double)missCount/(double)memoryAccess*(double)memoryAccessTime;
		double missesInstruction = (double)missCount/(double)memoryAccess ;
		String stringMisses = String.format("%.2f", missesInstruction);
		String stringAverage = String.format("%.2f", averageMemory);
		
		txtAverageMemory.setText(stringAverage + "");
		txtMissesperInstruction.setText(stringMisses); 
		
		
	}
}
