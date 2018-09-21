package mars.tools;



import java.util.Observable;

import javax.swing.JComponent;
import mars.cache.*;
import mars.cache.swing.PanelCache;
import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.Memory;
import mars.mips.hardware.MemoryAccessNotice;
import mars.cache.swing.InterfaceCache;

public class SimuladorCache extends AbstractMarsToolAndApplication {
	private static String name = "Simulador Cache";
	private static String version = "Versão 0.1 (Luiz Henrique)";
	private static String heading = "Simulador Didático de Cache";
	private Controller controller;
	private int memoryAccessCount;
	private InterfaceCache panel;
	

	protected SimuladorCache(String title, String heading) {
		super(title, heading);
	}
	public SimuladorCache()
	{
		super(heading + ", " + version, heading);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected JComponent buildMainDisplayArea() {
		Controller controller = new Controller();
		panel = new InterfaceCache(controller);
		
		
		return panel;
	}
	
	protected void addAsObserver() {
		addAsObserver(Memory.textBaseAddress, Memory.textLimitAddress);
	}
	
	protected void processMIPSUpdate(Observable resource, AccessNotice accessNotice) {
		
		 MemoryAccessNotice notice = (MemoryAccessNotice) accessNotice;
		 memoryAccessCount ++;
		 
		 panel.updateAddress(notice.getAddress());
		 	 
		 
	}
	

}
