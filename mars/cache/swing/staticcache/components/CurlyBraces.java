package mars.cache.swing.staticcache.components;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class CurlyBraces extends JPanel {

	
	public static final void paintComponent(Graphics g, int x, int y, int k) {
		Graphics2D g2 = (Graphics2D) g;
		
		CubicCurve2D shape = new CubicCurve2D.Float();
		CubicCurve2D shape2 = new CubicCurve2D.Float();

		//y = y - k/16;
		//shape.setCurve(0+x, 2+k/8+y, 0+x, 0+y, 6+k/2+x, 3+k/8+y, 6+k/2+x, 1+k/16+y);
		//shape2.setCurve(6+k/2+x, 1+k/16+y, 6+k/2+x, 3+k/8+y, 12+k+x, 0+y, 12+k+x, 2+k/8+y);
		
		shape.setCurve(x, y, x, y-k/16, x+k/2+8, y+k/16, x + k/2+8, y - k/32);
		shape2.setCurve(x+k/2+8, y-k/32, x+k/2+8, y+k/16, x+k, y-k/16, x+k+15, y);

		
		
		
		g2.draw(shape);
		g2.draw(shape2);

	}
}
