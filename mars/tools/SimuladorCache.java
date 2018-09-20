package mars.tools;



import javax.swing.JComponent;
import mars.cache.*;
import mars.cache.swing.PanelCache;
import mars.cache.swing.InterfaceCache;

public class SimuladorCache extends AbstractMarsToolAndApplication {
	private static String name = "Simulador Cache";
	private static String version = "Vers�o 0.1 (Luiz Henrique)";
	private static String heading = "Simulador Did�tico de Cache";
	

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
		InterfaceCache panel = new InterfaceCache();
		
		
		return panel;
	}

}
