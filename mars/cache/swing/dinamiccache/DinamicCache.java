package mars.cache.swing.dinamiccache;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


import mars.cache.Controller;
import mars.cache.statistic.StatisticCache;
import mars.cache.swing.statistic.Statistic;

public class DinamicCache extends JPanel{
	private Cache cache;
	private Controller controller;
	private Statistic statistic;
	private Graphics g;
	private int x;

	public DinamicCache(Controller controller, Statistic statistic) {
		this.controller = controller;
		this.statistic = statistic;
		JButton btnUpdate = new JButton(">");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnUpdate.setBounds(150, 100, 15, 30);
		add(btnUpdate);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.g = g;
		
		x+=1;
		g.drawString(x + "", 10,10);
		
		
		
		
		
		cache = new Cache(controller.getnWay(), controller.getnBlocos(), controller.getnPalavrasBloco(), 50, 0, statistic.getCache());
		cache.paint(g);
		
		this.setPreferredSize(new Dimension(3000, 2000));
	}
	
	public void setPreferredSize()
	{
		this.setPreferredSize(new Dimension(3000, 2000));
	}
	
	public void update()
	{
		x += 1000;
		//cache.repaint();
		//cache.validate();
		this.repaint();
		this.validate();
	}

	public Cache getCache()
	{
		return cache;
	}

}
