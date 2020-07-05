import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8150908027913223239L;
	JFrame f = new JFrame("Test");
	Snake s = new Snake();
	
	public Frame(){
		f.setSize(new Dimension(1020,1040));
		f.add(this);
		f.addKeyListener(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		
		Thread t = new Thread(){
			public void run(){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {}
				s.reset();
				int a = 0;
				while(true){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {}
					if(System.currentTimeMillis() > s.nextMove)
						s.move();
					if(a++ > 1000/60){
						repaint();
						a = 0;
					}
				}
				
			}
		};
		t.setDaemon(true);
		t.start();
		f.setVisible(true);
	}
	


	public static void main(String[] args){
		Frame f  = new Frame();
	}
	
	@Override
	public void paintComponent(Graphics gs){
		Graphics2D g = (Graphics2D) gs;
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("Score: "+((int)s.a*100-200), 10, 50);
		for(int i = 0; i < s.snake.size(); i++){
			if(s.snake.get(i) != null){
				g.setColor(new Color(0, 140-80*(i-(s.snake.size()-s.a))/(s.a), 0));
				Point p = s.snake.get(i);
				g.fillRect(10*p.x, 10*p.y, 10, 10);
			}
		}
		g.setColor(Color.RED);
		
		g.fillRect(10*Snake.n.x, 10*Snake.n.y, 10, 10);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'w' /*&& s.dy != 1*/){
			s.dy = -1;
			s.dx = 0;
		}
		if(e.getKeyChar() == 's' /*&& s.dy != -1*/){
			s.dy = 1;
			s.dx = 0;
		}if(e.getKeyChar() == 'a'/* && s.dx != 1*/){
			s.dy = 0;
			s.dx = -1;
		}if(e.getKeyChar() == 'd' /*&& s.dx != -1*/){
			s.dy = 0;
			s.dx = 1;
		}
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e){}
	
	@Override
	public void keyReleased(KeyEvent e){}
}
