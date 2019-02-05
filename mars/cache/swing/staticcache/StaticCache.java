package mars.cache.swing.staticcache;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import mars.cache.Controller;

public class StaticCache extends JPanel{
	private Cache cache;
	private Controller controller;

	public StaticCache(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		cache = new Cache(controller.getnWay(), controller.getnBlocos(), controller.getnPalavrasBloco(), 50, 0);
		cache.paint(g);
		
		this.setPreferredSize(new Dimension(3000, 2000));
	}
	
	public void setPreferredSize()
	{
		this.setPreferredSize(new Dimension(3000, 2000));
	}


}
