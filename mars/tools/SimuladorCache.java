package mars.tools;



import java.util.Observable;

import javax.swing.JComponent;

import mars.Globals;
import mars.ProcessingException;
import mars.ProgramStatement;
import mars.cache.*;
import mars.cache.swing.PanelCache;
import mars.cache.swing.statistic.Statistic;
import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.AddressErrorException;
import mars.mips.hardware.Memory;
import mars.mips.hardware.MemoryAccessNotice;
import mars.mips.hardware.RegisterFile;
import mars.mips.instructions.BasicInstruction;
import mars.mips.instructions.BasicInstructionFormat;
import mars.cache.swing.InterfaceCache;

public class SimuladorCache extends AbstractMarsToolAndApplication {
	private static String name = "Cache Simulator";
	private static String version = "Version 0.1 (Luiz Henrique)";
	private static String heading = "Cache Simulator";
	private Controller controller;
	private int memoryAccessCount;
	private InterfaceCache panel;
	private int lastAddress;
	private String[] listMemoryInstrucions = {"lw", "ll", "lwl", "lwr", "lb", "lh", "lhu", "lbu", "lwc1", "ldc1",
											  "sw", "sc", "swl", "swr", "sh", "swc1", "sdc1", "sb", "sh"};
	
	private Statistic panel2 = new Statistic();

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
		panel.setBounds(0,0, 800, 540);
		
		
		return panel;
	}
	
	protected void addAsObserver() {
		addAsObserver(Memory.textBaseAddress, Memory.textLimitAddress);
	}
	
	protected void processMIPSUpdate(Observable resource, AccessNotice notice) {
		
		 if (!notice.accessIsFromMIPS()) return;
			if (notice.getAccessType() != AccessNotice.READ) return;
			MemoryAccessNotice m = (MemoryAccessNotice) notice;
			int a = m.getAddress();
			if (a == lastAddress) return;
			lastAddress = a;
			
			if(panel.getTipoCache() == 1 ||  panel.getTipoCache() == 2)
			{
				panel.update(m.getAddress());
			}
			if(panel.getTipoCache() == 0 ||  panel.getTipoCache() == 2)
			{
				try {
					ProgramStatement stmt = Memory.getInstance().getStatement(a);
					BasicInstruction instr = (BasicInstruction) stmt.getInstruction();
					
					String instrName = instr.getName();
					int index = listMemoryInstrucions.length;
					for(int i =0; i<listMemoryInstrucions.length; i++)
					{
						
						if(instrName.equals(listMemoryInstrucions[i]))
						{
							index = i;
						}
					}
					int operands[];
					int address;
					if(index != listMemoryInstrucions.length)
					{
						switch(listMemoryInstrucions[index])
						{
							case "lw":
								operands = stmt.getOperands();
								address = RegisterFile.getValue(operands[2]) + operands[1];
								panel.update(address);
								break;
							case "lwl":
								operands = stmt.getOperands();
								address = RegisterFile.getValue(operands[2]) + operands[1];
								panel.update(address);
								break;
							case "swl":
								operands = stmt.getOperands();
								address = RegisterFile.getValue(operands[2]) + operands[1];
								panel.update(address);
								break;
			                    
							case "lwr":
								operands = stmt.getOperands();
								address = RegisterFile.getValue(operands[2]) + operands[1];
								panel.update(address);
								break;
							case "swr":
								operands = stmt.getOperands();
								address = RegisterFile.getValue(operands[2]) + operands[1];
								panel.update(address);
								break;
							case "lb":
								operands = stmt.getOperands();
								address = RegisterFile.getValue(operands[2])
	                                    + (operands[1] << 16 >> 16)<< 24 >> 24;
								panel.update(address);
								break;
							case "sb":
								operands = stmt.getOperands();
								address =  RegisterFile.getValue(operands[2])
	                                    + (operands[1] << 16 >> 16);
								break;
			                    
						}
					}
				} catch (AddressErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	 
			}		
			
		 
	}
	
	protected void reset() {
		controller = new Controller();
		panel = new InterfaceCache(controller);
		panel.setBounds(0,0, 800, 540);
		
	}
	
	

}
