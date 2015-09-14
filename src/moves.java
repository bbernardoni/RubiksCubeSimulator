import java.awt.Color;
import java.util.Random;
	
public class moves {
	
	private Color temp;
	
	public Color[][] cycle(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, Color[][] c){
		temp = c[x4][y4];
		c[x4][y4] = c[x3][y3];
		c[x3][y3] = c[x2][y2];
		c[x2][y2] = c[x1][y1];
		c[x1][y1] = temp;
		return c;
	}
	
	public Color[][] U(Color[][] c){
		c = cycle(4,0,2,0,3,0,5,0,c);
		c = cycle(4,1,2,1,3,1,5,1,c);
		c = cycle(4,2,2,2,3,2,5,2,c);
		c = cycle(0,0,0,8,0,6,0,4,c);
		c = cycle(0,1,0,7,0,5,0,2,c);
		return c;
	}
	public Color[][] Ui(Color[][] c){
		c = cycle(4,0,5,0,3,0,2,0,c);
		c = cycle(4,1,5,1,3,1,2,1,c);
		c = cycle(4,2,5,2,3,2,2,2,c);
		c = cycle(0,0,0,4,0,6,0,8,c);
		c = cycle(0,1,0,2,0,5,0,7,c);
		return c;
	}
	
	public Color[][] D(Color[][] c){
		c = cycle(4,6,5,6,3,6,2,6,c);
		c = cycle(4,7,5,7,3,7,2,7,c);
		c = cycle(4,8,5,8,3,8,2,8,c);
		c = cycle(1,0,1,4,1,6,1,8,c);
		c = cycle(1,1,1,2,1,5,1,7,c);
		return c;
	}
	public Color[][] Di(Color[][] c){
		c = cycle(4,6,2,6,3,6,5,6,c);
		c = cycle(4,7,2,7,3,7,5,7,c);
		c = cycle(4,8,2,8,3,8,5,8,c);
		c = cycle(1,0,1,8,1,6,1,4,c);
		c = cycle(1,1,1,7,1,5,1,2,c);
		return c;
	}
	
	
	public Color[][] R(Color[][] c){
		c = cycle(4,2,0,8,3,6,1,6,c);
		c = cycle(4,5,0,7,3,3,1,7,c);
		c = cycle(4,8,0,6,3,0,1,8,c);
		c = cycle(5,0,5,2,5,8,5,6,c);
		c = cycle(5,1,5,5,5,7,5,3,c);
		return c;
	}
	public Color[][] Ri(Color[][] c){
		c = cycle(4,2,1,6,3,6,0,8,c);
		c = cycle(4,5,1,7,3,3,0,7,c);
		c = cycle(4,8,1,8,3,0,0,6,c);
		c = cycle(5,0,5,6,5,8,5,2,c);
		c = cycle(5,1,5,3,5,7,5,5,c);
		return c;
	}
	
	public Color[][] L(Color[][] c){
		c = cycle(4,0,1,4,3,8,0,0,c);
		c = cycle(4,3,1,2,3,5,0,2,c);
		c = cycle(4,6,1,0,3,2,0,4,c);
		c = cycle(2,0,2,2,2,8,2,6,c);
		c = cycle(2,1,2,5,2,7,2,3,c);
		return c;
	}
	public Color[][] Li(Color[][] c){
		c = cycle(3,8,1,4,4,0,0,0,c);
		c = cycle(3,5,1,2,4,3,0,2,c);
		c = cycle(3,2,1,0,4,6,0,4,c);
		c = cycle(2,0,2,6,2,8,2,2,c);
		c = cycle(2,1,2,3,2,7,2,5,c);
		return c;
	}
	
	
	public Color[][] F(Color[][] c){
		c = cycle(2,2,0,6,5,6,1,4,c);
		c = cycle(2,5,0,5,5,3,1,5,c);
		c = cycle(2,8,0,4,5,0,1,6,c);
		c = cycle(4,0,4,2,4,8,4,6,c);
		c = cycle(4,1,4,5,4,7,4,3,c);
		return c;
	}
	public Color[][] Fi(Color[][] c){
		c = cycle(5,6,0,6,2,2,1,4,c);
		c = cycle(5,3,0,5,2,5,1,5,c);
		c = cycle(5,0,0,4,2,8,1,6,c);
		c = cycle(4,0,4,6,4,8,4,2,c);
		c = cycle(4,1,4,3,4,7,4,5,c);
		return c;
	}
	
	public Color[][] B(Color[][] c){
		c = cycle(2,0,1,0,5,8,0,8,c);
		c = cycle(2,3,1,1,5,5,0,1,c);
		c = cycle(2,6,1,8,5,2,0,0,c);
		c = cycle(3,0,3,2,3,8,3,6,c);
		c = cycle(3,1,3,5,3,7,3,3,c);
		return c;
	}
	public Color[][] Bi(Color[][] c){
		c = cycle(5,8,1,0,2,0,0,8,c);
		c = cycle(5,5,1,1,2,3,0,1,c);
		c = cycle(5,2,1,8,2,6,0,0,c);
		c = cycle(3,0,3,6,3,8,3,2,c);
		c = cycle(3,1,3,3,3,7,3,5,c);
		return c;
	}
	
	
	public Color[][] M(Color[][] c){
		c = cycle(0,1,4,1,1,5,3,7,c);
		c = cycle(0,3,4,4,1,3,3,4,c);
		c = cycle(0,5,4,7,1,1,3,1,c);
		return c;
	}
	public Color[][] Mi(Color[][] c){
		c = cycle(1,5,4,1,0,1,3,7,c);
		c = cycle(1,3,4,4,0,3,3,4,c);
		c = cycle(1,1,4,7,0,5,3,1,c);
		return c;
	}
	
	public Color[][] E(Color[][] c){
		c = cycle(2,3,4,3,5,3,3,3,c);
		c = cycle(2,4,4,4,5,4,3,4,c);
		c = cycle(2,5,4,5,5,5,3,5,c);
		return c;
	}
	public Color[][] Ei(Color[][] c){
		c = cycle(5,3,4,3,2,3,3,3,c);
		c = cycle(5,4,4,4,2,4,3,4,c);
		c = cycle(5,5,4,5,2,5,3,5,c);
		return c;
	}
	
	public Color[][] S(Color[][] c){
		c = cycle(1,2,2,1,0,7,5,7,c);
		c = cycle(1,3,2,4,0,3,5,4,c);
		c = cycle(1,7,2,7,0,2,5,1,c);
		return c;
	}
	public Color[][] Si(Color[][] c){
		c = cycle(0,7,2,1,1,2,5,7,c);
		c = cycle(0,3,2,4,1,3,5,4,c);
		c = cycle(0,2,2,7,1,7,5,1,c);
		return c;
	}
	
	public Color[][] Scramble(Color[][] c, int sc){
		Random randomGenerator = new Random();
		for (int i = 1; i <= sc; i++){
			switch (randomGenerator.nextInt(11)){
			case 0:
				U(c);
				break;
			case 1:
				Ui(c);
				break;
			case 2:
				R(c);
				break;
			case 3:
				Ri(c);
				break;
			case 4:
				F(c);
				break;
			case 5:
				Fi(c);
				break;
			case 6:
				B(c);
				break;
			case 7:
				Bi(c);
				break;
			case 8:
				L(c);
				break;
			case 9:
				Li(c);
				break;
			case 10:
				D(c);
				break;
			case 11:
				Di(c);
				break;
			}
		}
		return c;
	}
}
