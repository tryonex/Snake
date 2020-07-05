import java.awt.Point;
import java.util.HashMap;

public class Snake {
	
	
	public HashMap<Integer, Point> snake = new HashMap<Integer, Point>(50,0.75f);
	int dx = 1;
	int dy = 0;
	public static Point n = new Point(10,10); 
	int v = 75;
	int a = 1;
	public long nextMove = System.currentTimeMillis()+(v/(a/10+1));
	
	public Snake(){
		snake.put(0, new Point(1,4));
		snake.put(1, new Point(1,3));
		rebuild();
	}
	
	public void move(){
		Point p = snake.get(snake.size()-1);
		if(p.x +dx == Snake.n.x && p.y+dy == Snake.n.y){
			grow();
			reset();
		}
		if(p.x +dx >= 100){
			snake.put(snake.size()-1, new Point(0, p.y));
		}else if(p.x +dx < 0){
			snake.put(snake.size()-1,  new Point(99, p.y));
		}else if(p.y +dy >= 100){
			snake.put(snake.size()-1, new Point(p.x, 0));
		}else if(p.y +dy < 0){
			snake.put(snake.size()-1,  new Point(p.x, 99));
		}else
			snake.put(snake.size()-1, new Point(p.x+dx, p.y+dy));
		for(int i = 0; i < snake.size()-1; i++){
			if(snake.get(i) != null)
				snake.put(i, snake.get(i+1));
		}
		rebuild();
		nextMove = System.currentTimeMillis()+(v/(a/10+1));
	}
	
	public void grow(){
		Point l = snake.get(snake.size()-1);
		snake.put(snake.size(), new Point(l.x+dx, l.y+dy));
		a++;
	}
	
	public void reset(){
		Snake.n = new Point(5+(int) (Math.round(Math.random() * 90)), 5+(int) (Math.round(Math.random() * 90)));
	}
	
	public void rebuild(){
		int l = 0;
		for(int i = 0; i < snake.size(); i++){
			if(snake.get(i) == null)
				continue;
			else
				l++;
		}
		a = l;
		if(a < 1)
			a = 1;
	}
	
	public int die(int index){
		int b = a;
		for(int i = 0; i < index; i++){
			if(snake.get(i) != null)
				if(i != snake.size()-1)
					snake.put(i, null);
		}
		rebuild();
		return b-a;
	}

}
