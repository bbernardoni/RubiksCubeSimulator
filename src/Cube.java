import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Cube extends Core implements KeyListener{
	public static void main(String[] args){
		new Cube().run();
	}
	
	String cs = "Black";
	boolean nv = true;
	boolean tm = true;
	int sc = 50;
	int screen = 1;
	long Time = 0;
	int Moves = 0;
	long Ready = 0;
	long done = 0;
	static Color W = Color.WHITE;		static Color Y = Color.YELLOW;
	static Color R = Color.RED;			static Color O = new Color(255,140,0);
	static Color G = Color.GREEN;		static Color B = Color.BLUE;
	static Color C = Color.BLACK;		static Color A = Color.GRAY;
	static Color K = Color.PINK;		static Color N = new Color(139,69,19);
	static Color P = new Color(128,0,128);
	Color cc[]={W,Y,G,O,R,B};
	String scheme[]={"White","Yellow","Green","Orange","Red","Blue"};
	boolean sel[]={true,false,false,false,false,false};
	int sl = 0;
	Color c[][]=new Color[6][9];
	Color d[]= new Color[9];
	
	//init calls super init
	public void init(){
        super.init();
        Window w = s.getFullScreenWindow();
        w.setFocusTraversalKeysEnabled(false);
        w.addKeyListener(this);
        if ((new File("options.txt")).exists() == false){
        	try {
        	    BufferedWriter out = new BufferedWriter(new FileWriter("options.txt"));
        	    out.write("WYGORB\ntrue\ntrue\n50\nBlack");
        	    out.close();
        	} catch (IOException e) {}
        }
        try {
		    BufferedReader in = new BufferedReader(new FileReader("options.txt"));
		    String str;
		    int i = 1;
		    while ((str = in.readLine()) != null) {
		    	switch(i){
		    	case 1:
		    		for (int j=0; j <= 5; j++){
		    			char ch = str.charAt(j);
		    			switch(ch){
		    			case 'C':
		    	        	scheme[j] = "Black";
		    	        	cc[j] = C;
		    	        	break;
		    	    	case 'R':
		    	    		scheme[j] = "Red";
		    	    		cc[j] = R;
		    	        	break;
		    	    	case 'O':
		    	    		scheme[j] = "Orange";
		    	    		cc[j] = O;
		    	        	break;
		    	    	case 'Y':
		    	    		scheme[j] = "Yellow";
		    	    		cc[j] = Y;
		    	        	break;
		    	    	case 'G':
		    	    		scheme[j] = "Green";
		    	    		cc[j] = G;
		    	        	break;
		    	    	case 'B':
		    	    		scheme[j] = "Blue";
		    	    		cc[j] = B;
		    	        	break;
		    	    	case 'P':
		    	    		scheme[j] = "Purple";
		    	    		cc[j] = P;
		    	        	break;
		    	    	case 'W':
		    	    		scheme[j] = "White";
		    	    		cc[j] = W;
		    	        	break;
		    	    	case 'A':
		    	    		scheme[j] = "Gray";
		    	    		cc[j] = A;
		    	        	break;
		    	    	case 'N':
		    	    		scheme[j] = "Brown";
		    	    		cc[j] = N;
		    	        	break;
		    	    	case 'K':
		    	    		scheme[j] = "Pink";
		    	    		cc[j] = K;
		    	        	break;
		    			}	
		    		}
		    		break;
		    	case 2:
		    		nv = Boolean.parseBoolean(str);
		    		break;
		    	case 3:
		    		tm = Boolean.parseBoolean(str);
		    		break;
		    	case 4:
		    		sc = Integer.parseInt(str);
		    		break;
		    	case 5:
		    		Window wi = s.getFullScreenWindow();
		    		wi.setForeground(W);
			    	if (str.equals("Black")){
			        	cs = "Black";
			        	wi.setBackground(C);
			    	} else if (str.equals("Red")){
			    		cs = "Red";
			        	wi.setBackground(R);
			    	} else if (str.equals("Orange")){
			    		cs = "Orange";
			        	wi.setBackground(O);
			    	} else if (str.equals("Yellow")){
			    		cs = "Yellow";
			        	wi.setBackground(Y);
			    	} else if (str.equals("Green")){
			        	cs = "Green";
			        	wi.setBackground(G);
			    	} else if (str.equals("Blue")){
			    		cs = "Blue";
			        	wi.setBackground(B);
			    	} else if (str.equals("Purple")){
			    		cs = "Purple";
			        	wi.setBackground(P);
			    	} else if (str.equals("White")){
			    		cs = "White";
			        	wi.setBackground(W);
			        	wi.setForeground(C);
			    	} else if (str.equals("Gray")){
			    		cs = "Gray";
			        	wi.setBackground(A);
			    	} else if (str.equals("Brown")){
			    		cs = "Brown";
			        	wi.setBackground(N);
			    	} else if (str.equals("Pink")){
			    		cs = "Pink";
			        	wi.setBackground(K);
			    	}
		    		break;
		    	}
		    i++;
		    }
		    in.close();
		} catch (IOException e) {}
        Arrays.fill(c[0], cc[0]);
        Arrays.fill(c[1], cc[1]);
        Arrays.fill(c[2], cc[2]);
        Arrays.fill(c[3], cc[3]);
        Arrays.fill(c[4], cc[4]);
        Arrays.fill(c[5], cc[5]);
        if ((new File("pause.txt")).exists()){
	        try {
			    BufferedReader in = new BufferedReader(new FileReader("pause.txt"));
			    String str;
			    int i = 1;
			    while ((str = in.readLine()) != null) {
			    	switch(i){
			    	case 1:
			    		Time = Integer.parseInt(str);
			    		Ready = System.currentTimeMillis() - Time;
			    		break;
			    	case 2:
			    		Moves = Integer.parseInt(str);
			    		break;
			    	}
			    i++;
			    }
			    in.close();
			    (new File("pause.txt")).delete();
			} catch (IOException e) {}
        }
        if ((new File("pausestate.txt")).exists()){
	        try {
	        	FileInputStream fis = new FileInputStream("pausestate.txt");
	        	ObjectInputStream in = new ObjectInputStream(fis);
	        	c = (Color[][])in.readObject();
	        	in.close();
	        	(new File("pausestate.txt")).delete();
	        }catch (Exception e) {}
        }
    }
	
	public void stop() {
		try {
    	    BufferedWriter out = new BufferedWriter(new FileWriter("options.txt"));
    	    String ccc = "";
    	    for (int i=0;i<=5;i++){
    	    	String str = scheme[i];
	    	    if (str == "Black"){
		        	ccc += "C";
		    	} else if (str == "Red"){
		    		ccc += "R";
		    	} else if (str == "Orange"){
		    		ccc += "O";
		    	} else if (str == "Yellow"){
		    		ccc += "Y";
		    	} else if (str == "Green"){
		    		ccc += "G";
		    	} else if (str == "Blue"){
		    		ccc += "B";
		    	} else if (str == "Purple"){
		    		ccc += "P";
		    	} else if (str == "White"){
		    		ccc += "W";
		    	} else if (str == "Gray"){
		    		ccc += "A";
		    	} else if (str == "Brown"){
		    		ccc += "N";
		    	} else if (str == "Pink"){
		    		ccc += "K";
		    	}
    	    }
    	    out.write(""+ccc+"\n"+nv+"\n"+tm+"\n"+sc+"\n"+cs);
    	    out.close();
    	} catch (IOException e) {}
    	if (Ready != 0 && Ready != 1){
    		try {
        	    BufferedWriter out = new BufferedWriter(new FileWriter("pause.txt"));
        	    out.write(""+Time+"\n"+Moves);
        	    out.close();
        		FileOutputStream fos = new FileOutputStream("pausestate.txt");
        		ObjectOutputStream outs = new ObjectOutputStream(fos);
        		outs.writeObject(c);
        		outs.flush();
        		outs.close();
        	} catch (IOException e) {}
    	}
		super.stop();
	}
	
	//draw polygon
	public void poly(Graphics2D g, int x[], int y[], Color c){
		g.setColor(c);
		g.fillPolygon(x, y, 4);
	}
	
    //draw method
    public synchronized void draw(Graphics2D g) {
	        Window w = s.getFullScreenWindow();
	        g.clearRect(0,0,s.getWidth(),s.getHeight());
	        g.setColor(w.getBackground());
	        g.fillRect(0, 0, s.getWidth(), s.getHeight());
	    switch(screen){
	    case 0:
	        poly(g, new int[]{631,711,647,561}, new int[]{122,163,197,161}, c[0][0]);
	        poly(g, new int[]{712,649,731,787}, new int[]{164,198,233,202}, c[0][1]);
	        poly(g, new int[]{559,645,581,487}, new int[]{162,198,233,202}, c[0][2]);
	        poly(g, new int[]{647,583,674,729}, new int[]{199,234,264,234}, c[0][3]);
	        poly(g, new int[]{486,402,506,579}, new int[]{203,250,274,234}, c[0][4]);
	        poly(g, new int[]{581,509,613,672}, new int[]{235,274,297,265}, c[0][5]);
	        poly(g, new int[]{674,616,719,771}, new int[]{266,297,320,298}, c[0][6]);
	        poly(g, new int[]{731,676,773,825}, new int[]{235,265,297,274}, c[0][7]);
	        poly(g, new int[]{788,733,828,879}, new int[]{203,234,273,251}, c[0][8]);
	        
	        poly(g, new int[]{631,711,647,561}, new int[]{678,637,603,639}, c[1][0]);
	        poly(g, new int[]{712,649,731,787}, new int[]{636,602,567,598}, c[1][1]);
	        poly(g, new int[]{559,645,581,487}, new int[]{638,602,567,598}, c[1][2]);
	        poly(g, new int[]{647,583,674,729}, new int[]{601,566,536,566}, c[1][3]);
	        poly(g, new int[]{486,402,506,579}, new int[]{597,550,526,566}, c[1][4]);
	        poly(g, new int[]{581,509,613,672}, new int[]{565,526,503,535}, c[1][5]);
	        poly(g, new int[]{674,616,719,771}, new int[]{534,503,480,502}, c[1][6]);
	        poly(g, new int[]{731,676,773,825}, new int[]{565,535,503,526}, c[1][7]);
	        poly(g, new int[]{788,733,828,879}, new int[]{597,566,527,549}, c[1][8]);
	        
	        poly(g, new int[]{101,101,199,199}, new int[]{251,349,349,251}, c[2][0]);
	        poly(g, new int[]{201,201,299,299}, new int[]{251,349,349,251}, c[2][1]);
	        poly(g, new int[]{301,301,399,399}, new int[]{251,349,349,251}, c[2][2]);
	        poly(g, new int[]{101,101,199,199}, new int[]{351,449,449,351}, c[2][3]);
	        poly(g, new int[]{201,201,299,299}, new int[]{351,449,449,351}, c[2][4]);
	        poly(g, new int[]{301,301,399,399}, new int[]{351,449,449,351}, c[2][5]);
	        poly(g, new int[]{101,101,199,199}, new int[]{451,549,549,451}, c[2][6]);
	        poly(g, new int[]{201,201,299,299}, new int[]{451,549,549,451}, c[2][7]);
	        poly(g, new int[]{301,301,399,399}, new int[]{451,549,549,451}, c[2][8]);
	        
	        poly(g, new int[]{881,881,979,979}, new int[]{251,349,349,251}, c[3][0]);
	        poly(g, new int[]{981,981,1079,1079}, new int[]{251,349,349,251}, c[3][1]);
	        poly(g, new int[]{1081,1081,1179,1179}, new int[]{251,349,349,251}, c[3][2]);
	        poly(g, new int[]{881,881,979,979}, new int[]{351,449,449,351}, c[3][3]);
	        poly(g, new int[]{981,981,1079,1079}, new int[]{351,449,449,351}, c[3][4]);
	        poly(g, new int[]{1081,1081,1179,1179}, new int[]{351,449,449,351}, c[3][5]);
	        poly(g, new int[]{881,881,979,979}, new int[]{451,549,549,451}, c[3][6]);
	        poly(g, new int[]{981,981,1079,1079}, new int[]{451,549,549,451}, c[3][7]);
	        poly(g, new int[]{1081,1081,1179,1179}, new int[]{451,549,549,451}, c[3][8]);
	        
	        poly(g, new int[]{401,505,505,401}, new int[]{252,276,357,350}, c[4][0]);
	        poly(g, new int[]{507,613,613,507}, new int[]{276,299,365,358}, c[4][1]);
	        poly(g, new int[]{615,720,720,615}, new int[]{299,322,373,365}, c[4][2]);
	        poly(g, new int[]{401,505,505,401}, new int[]{352,359,442,450}, c[4][3]);
	        poly(g, new int[]{507,613,613,507}, new int[]{360,367,435,442}, c[4][4]);
	        poly(g, new int[]{615,720,720,615}, new int[]{367,375,427,435}, c[4][5]);
	        poly(g, new int[]{401,505,505,401}, new int[]{452,444,524,548}, c[4][6]);
	        poly(g, new int[]{507,613,613,507}, new int[]{444,437,501,524}, c[4][7]);
	        poly(g, new int[]{615,720,720,615}, new int[]{437,429,478,501}, c[4][8]);
	        
	        poly(g, new int[]{722,772,772,722}, new int[]{321,300,366,373}, c[5][0]);
	        poly(g, new int[]{774,826,826,774}, new int[]{299,276,358,365}, c[5][1]);
	        poly(g, new int[]{828,879,879,828}, new int[]{275,253,350,358}, c[5][2]);
	        poly(g, new int[]{722,772,772,722}, new int[]{375,368,434,427}, c[5][3]);
	        poly(g, new int[]{774,826,826,774}, new int[]{367,360,442,434}, c[5][4]);
	        poly(g, new int[]{828,879,879,828}, new int[]{360,352,449,442}, c[5][5]);
	        poly(g, new int[]{722,772,772,722}, new int[]{429,436,500,479}, c[5][6]);
	        poly(g, new int[]{774,826,826,774}, new int[]{436,444,524,501}, c[5][7]);
	        poly(g, new int[]{828,879,879,828}, new int[]{444,451,547,525}, c[5][8]);
	        
	        g.setColor(w.getForeground());
	        Time = System.currentTimeMillis() - Ready;
	        if (tm == true){
		        g.drawString("Moves: " + Moves, 20, 780);
		        if (done!=0){
		        	g.drawString("Time: " + (done/(float)1000), 900, 780);
		        }
		        else if (Ready != 0 && Ready != 1){
		        	g.drawString("Time: " + (Time/(float)1000), 900, 780);	
		        }else{
		        	g.drawString("Time: 0.000", 900, 780);
		        }
	        }
	        if (nv == true){
		        g.drawString("Esc - Quit", 10, 50);
		        g.drawString("Home - Home", 900, 50);
		        g.drawString("Ins - Instructions", 400, 50);
	        }
	        break;
    	case 1:
    		g.setColor(w.getForeground());
    		g.drawString("Esc - Quit", 10, 50);
    		g.setFont(new Font("Arial", Font.PLAIN, 115));
    		g.drawString("Rubik's Cube Simulator", 30, 300);
    		g.setFont(new Font("Arial", Font.PLAIN, 30));
    		g.drawString("Press Letter For Choice", 500, 780);
    		g.setFont(new Font("Arial", Font.PLAIN, 50));
    		g.drawString("BY: Bennett Bernardoni", 375, 400);
    		g.drawString("S - Start", 30, 700);
    		g.drawString("I - Instructions", 250, 700);
    		g.drawString("O - Options", 590, 700);
    		g.drawString("H - High Scores", 880, 700);
    		break;
    	case 2:
    		g.setColor(w.getForeground());
    		if (nv == true){
    			g.drawString("Esc - Quit", 10, 50);
	    		g.drawString("S - Start", 530, 50);
	    		g.drawString("H - Home", 1050, 50);
    		}
    		g.drawString("Turn the cube by typing, buttons are assigned as follows.", 5, 200);
    		g.drawString("Turn", 20, 300);
    		g.drawString("Key", 170, 300);
    		g.drawString("Turn", 320, 300);
    		g.drawString("Key", 470, 300);
    		g.drawString("Turn", 620, 300);
    		g.drawString("Key", 770, 300);
    		g.drawString("Turn", 920, 300);
    		g.drawString("Key", 1070, 300);
    		g.drawString("U", 20, 350);
    		g.drawString("J", 170, 350);
    		g.drawString("U'", 320, 350);
    		g.drawString("F", 470, 350);
    		g.drawString("D", 620, 350);
    		g.drawString("S", 770, 350);
    		g.drawString("D'", 920, 350);
    		g.drawString("L", 1070, 350);
    		g.drawString("R", 20, 400);
    		g.drawString("I", 170, 400);
    		g.drawString("R'", 320, 400);
    		g.drawString("K", 470, 400);
    		g.drawString("L", 620, 400);
    		g.drawString("D", 770, 400);
    		g.drawString("L'", 920, 400);
    		g.drawString("E", 1070, 400);
    		g.drawString("F", 20, 450);
    		g.drawString("H", 170, 450);
    		g.drawString("F'", 320, 450);
    		g.drawString("G", 470, 450);
    		g.drawString("B", 620, 450);
    		g.drawString("W", 770, 450);
    		g.drawString("B'", 920, 450);
    		g.drawString("O", 1070, 450);
    		g.drawString("x", 20, 500);
    		g.drawString("Y", 170, 500);
    		g.drawString("x'", 320, 500);
    		g.drawString("N", 470, 500);
    		g.drawString("y", 620, 500);
    		g.drawString(";", 770, 500);
    		g.drawString("y'", 920, 500);
    		g.drawString("A", 1070, 500);
    		g.drawString("z", 20, 550);
    		g.drawString("P", 170, 550);
    		g.drawString("z'", 320, 550);
    		g.drawString("Q", 470, 550);
    		g.drawString("M", 620, 550);
    		g.drawString("B", 770, 550);
    		g.drawString("M'", 920, 550);
    		g.drawString("T", 1070, 550);
    		g.drawString("Additional commands:", 20, 600);
    		g.drawString("Space - Scramble                            Backspace - Solve", 20, 650);
    		break;
    	case 3:
    		g.setColor(w.getForeground());
    		if (nv == true){
	    		g.drawString("Esc - Quit", 10, 50);
	    		g.drawString("H - Home", 1050, 50);
    		}
    		g.drawString("B - Change Background Color - Current Color: " + cs, 5, 150);
    		g.drawString("N - Toggle Naviation Titles - Showing = " + nv, 5, 200);
    		g.drawString("T - Toggle Time and Moves - Showing = " + tm, 5, 250);
    		g.drawString("up & down-Adjust Scramble Length-Current Length: " + sc , 5, 300);
    		g.drawString("S - Set Color Scheme - See List Below", 5, 350);
    		g.drawString("U "+scheme[0]+"|L "+scheme[2]+"|F "+scheme[4]+"|R "+scheme[5]+"|B "+scheme[3]+"|D "+scheme[1], 5, 400);
    		g.drawString("Del - Reset To Default Options", 5, 550);
    		break;
    	case 4:
    		g.setColor(w.getForeground());
    		if (nv == true){
	    		g.drawString("Esc - Quit", 10, 50);
	    		g.drawString("H - Home", 1050, 50);
    		}
    		g.drawString("Del - Delete all times", 400, 780);
    		g.drawString("Top Ten Times:", 450, 150);
    		try {
    		    BufferedReader in = new BufferedReader(new FileReader("times.txt"));
    		    String str;
    		    Vector<String> v = new Vector<String>();
    		    while ((str = in.readLine()) != null) {
    		    	v.add(str);
    		    }
    		    in.close();
    		    Collections.sort(v);
    		    for(int i=0; i <= 9 && v.size() > i; i++){
        			g.drawString("" + v.get(i), 600, (i*50)+230);
        		}
    		} catch (IOException e) {}
    		break;
    	case 5:
    		g.setColor(w.getForeground());
    		if (nv == true){
	    		g.drawString("Esc - Quit", 10, 50);
	    		g.drawString("Back Space - Options", 780, 50);
    		}
    		g.drawString("B - Black", 550, 200);
    		g.drawString("R - Red", 550, 250);
    		g.drawString("O - Orange", 550, 300);
    		g.drawString("Y - Yellow", 550, 350);
    		g.drawString("G - Green", 550, 400);
    		g.drawString("L - Blue", 550, 450);
    		g.drawString("P - Purple", 550, 500);
    		g.drawString("W - White", 550, 550);
    		g.drawString("A - Gray", 550, 600);
    		g.drawString("N - Brown", 550, 650);
    		g.drawString("K - Pink", 550, 700);
    		break;
    	case 6:
    		g.setColor(w.getForeground());
    		if (nv == true){
	    		g.drawString("Esc - Quit", 10, 50);
	    		g.drawString("Back Space - Options", 780, 50);
    		}
    		g.drawString("Press up and down to choose which face to change", 50, 150);
    		g.drawString("B - Black", 800, 250);
    		g.drawString("R - Red", 800, 300);
    		g.drawString("O - Orange", 800, 350);
    		g.drawString("Y - Yellow", 800, 400);
    		g.drawString("G - Green", 800, 450);
    		g.drawString("L - Blue", 800, 500);
    		g.drawString("P - Purple", 800, 550);
    		g.drawString("W - White", 800, 600);
    		g.drawString("A - Gray", 800, 650);
    		g.drawString("N - Brown", 800, 700);
    		g.drawString("K - Pink", 800, 750);
    		g.drawString("U - " + scheme[0] + " - Selected = " + sel[0], 50, 250);
    		g.drawString("D - " + scheme[1] + " - Selected = " + sel[1], 50, 300);
    		g.drawString("L - " + scheme[2] + " - Selected = " + sel[2], 50, 350);
    		g.drawString("B - " + scheme[3] + " - Selected = " + sel[3], 50, 400);
    		g.drawString("F - " + scheme[4] + " - Selected = " + sel[4], 50, 450);
    		g.drawString("R - " + scheme[5] + " - Selected = " + sel[5], 50, 500);
    		g.setFont(new Font("Arial", Font.PLAIN, 30));
    		g.drawString("Warning changing color scheme resets current solve", 250, 790);
    		g.setFont(new Font("Arial", Font.PLAIN, 50));
    		break;
    	}
    }
    
    public void start() {
    	if (Ready == 1){
    		Ready = System.currentTimeMillis();
    	}
    }
    
     //key pressed
    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
    	switch (screen){
    	case 0:
	        moves m = new moves();
	        switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	        case KeyEvent.VK_J:
	        	c = m.U(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_F:
	        	c = m.Ui(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_S:
	        	c = m.D(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_L:
	        	c = m.Di(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_I:
	        	c = m.R(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_K:
	        	c = m.Ri(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_D:
	        	c = m.L(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_E:
	        	c = m.Li(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_H:
	        	c = m.F(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_G:
	        	c = m.Fi(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_W:
	        	c = m.B(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_O:
	        	c = m.Bi(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_B:
	        	c = m.M(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_T:
	        	c = m.Mi(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_SEMICOLON:
	        	c = m.U(c);
	        	c = m.Ei(c);
	        	c = m.Di(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_A:
	        	c = m.Ui(c);
	        	c = m.E(c);
	        	c = m.D(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_Y:
	        	c = m.R(c);
	        	c = m.Mi(c);
	        	c = m.Li(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_N:
	        	c = m.Ri(c);
	        	c = m.M(c);
	        	c = m.L(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_P:
	        	c = m.F(c);
	        	c = m.S(c);
	        	c = m.Bi(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_Q:
	        	c = m.Fi(c);
	        	c = m.Si(c);
	        	c = m.B(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_U:
	        	c = m.R(c);
	        	c = m.Mi(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_M:
	        	c = m.Ri(c);
	        	c = m.M(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_V:
	        	c = m.L(c);
	        	c = m.M(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_R:
	        	c = m.Li(c);
	        	c = m.Mi(c);
	        	Moves++;
	        	start();
	        	break;
	        case KeyEvent.VK_SPACE:
	        	c = m.Scramble(c, sc);
	        	Moves = 0;
	        	Ready = 1;
	        	done = 0;
	        	break;
	        case KeyEvent.VK_BACK_SPACE:
	        	Arrays.fill(c[0], cc[0]);
		        Arrays.fill(c[1], cc[1]);
		        Arrays.fill(c[2], cc[2]);
		        Arrays.fill(c[3], cc[3]);
		        Arrays.fill(c[4], cc[4]);
		        Arrays.fill(c[5], cc[5]);
	        	Moves = 0;
	        	Ready = 0;
	        	done = 0;
	        	break;
	        case KeyEvent.VK_HOME:
	        	screen = 1;
	        	break;
	        case KeyEvent.VK_INSERT:
	        	screen = 2;
	        	break;
	        }
	        int y = 0;
	        for (int x=0;x<=5;x++){
	        	Arrays.fill(d, c[x][1]);
		        if(Arrays.equals(c[x],d)==true)
		        	y++;
	        }
	        if(y==6 && Ready!=0){
	        	done = Time;
	        	Ready = 0;
	        	float time;
	    		time = Time/(float)1000;
	    		try{
	    		    FileWriter fstream = new FileWriter("times.txt",true);
	    		    BufferedWriter out = new BufferedWriter(fstream);
	    		    out.write(""+time+"\n");
	    		    out.close();
	    		}catch (Exception ex){}
	        }
	        break;
    	case 1:
    		switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	        case KeyEvent.VK_S:
	        	screen = 0;
	        	if (Ready != 0 && Ready != 1){
	        		Ready = System.currentTimeMillis() - Time;
	        	}
	        	break;
	        case KeyEvent.VK_SPACE:
	        	screen = 0;
	        	if (Ready != 0 && Ready != 1){
	        		Ready = System.currentTimeMillis() - Time;
	        	}
	        	break;
	        case KeyEvent.VK_I:
	        	screen = 2;
	        	break;
	        case KeyEvent.VK_O:
	        	screen = 3;
	        	break;
	        case KeyEvent.VK_H:
	        	screen = 4;
	        	break;
    		}
    		break;
    	case 2:
    		switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	        case KeyEvent.VK_S:
	        	screen = 0;
	        	if (Ready != 0 && Ready != 1){
	        		Ready = System.currentTimeMillis() - Time;
	        	}
	        	break;
	        case KeyEvent.VK_H:
	        	screen = 1;
	        	break;
	        case KeyEvent.VK_BACK_SPACE:
	        	screen = 1;
	        	break;
    		}
    		break;
    	case 3:
    		switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	        case KeyEvent.VK_H:
	        	screen = 1;
	        	break;
	        case KeyEvent.VK_BACK_SPACE:
	        	screen = 1;
	        	break;
	        case KeyEvent.VK_B:
	        	screen = 5;
	        	break;
	        case KeyEvent.VK_N:
	        	nv = !nv;
	        	break;
	        case KeyEvent.VK_T:
	        	tm = !tm;
	        	break;
	        case KeyEvent.VK_UP:
	        	sc++;
	        	break;
	        case KeyEvent.VK_DOWN:
	        	if (sc > 30)
	        		sc--;
	        	break;
	        case KeyEvent.VK_S:
	        	screen = 6;
	        	break;
	        case KeyEvent.VK_DELETE:
	        	Window wi = s.getFullScreenWindow();
	        	nv = true;
	        	tm = true;
	        	sc = 50;
	        	cs = "Black";
	        	wi.setBackground(C);
	        	wi.setForeground(W);
	        	cc[0]=W;			cc[1]=Y;			cc[2]=G;			cc[3]=O;			cc[4]=R;			cc[5]=B;
	        	scheme[0]="White";	scheme[1]="Yellow";	scheme[2]="Green";	scheme[3]="Orange";	scheme[4]="Red";	scheme[5]="Blue";
	        	break;
    		}
    		break;
    	case 4:
    		switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	        case KeyEvent.VK_H:
	        	screen = 1;
	        	break;
	        case KeyEvent.VK_BACK_SPACE:
	        	screen = 1;
	        	break;
	        case KeyEvent.VK_DELETE:
	        	try{
	        		FileOutputStream erasor = new FileOutputStream("times.txt");
	        		erasor.write(null);
	        		erasor.close();
	    		}catch (Exception ex){}
	        	break;
    		}
    		break;
    	case 5:
    		Window wi = s.getFullScreenWindow();
    		wi.setForeground(W);
    		switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	    	case KeyEvent.VK_BACK_SPACE:
	        	screen = 3;
	        	break;
	    	case KeyEvent.VK_B:
	        	cs = "Black";
	        	wi.setBackground(C);
	        	break;
	    	case KeyEvent.VK_R:
	    		cs = "Red";
	        	wi.setBackground(R);
	        	break;
	    	case KeyEvent.VK_O:
	    		cs = "Orange";
	        	wi.setBackground(O);
	        	break;
	    	case KeyEvent.VK_Y:
	    		cs = "Yellow";
	        	wi.setBackground(Y);
	        	break;
	    	case KeyEvent.VK_G:
	        	cs = "Green";
	        	wi.setBackground(G);
	        	break;
	    	case KeyEvent.VK_L:
	    		cs = "Blue";
	        	wi.setBackground(B);
	        	break;
	    	case KeyEvent.VK_P:
	    		cs = "Purple";
	        	wi.setBackground(P);
	        	break;
	    	case KeyEvent.VK_W:
	    		cs = "White";
	        	wi.setBackground(W);
	        	wi.setForeground(C);
	        	break;
	    	case KeyEvent.VK_A:
	    		cs = "Gray";
	        	wi.setBackground(A);
	        	break;
	    	case KeyEvent.VK_N:
	    		cs = "Brown";
	        	wi.setBackground(N);
	        	break;
	    	case KeyEvent.VK_K:
	    		cs = "Pink";
	        	wi.setBackground(K);
	        	break;
			}
    		break;
    	case 6:
    		switch (keyCode){
	        case KeyEvent.VK_ESCAPE:
	        	stop();
	        	break;
	    	case KeyEvent.VK_BACK_SPACE:
	        	screen = 3;
	        	break;
	    	case KeyEvent.VK_DOWN:
	        	if (sl != 5){
	        		sel[sl]=false;
	        		sl++;
	        		sel[sl]=true;
	        	}
	        	break;
	    	case KeyEvent.VK_UP:
	    		if (sl != 0){
	    			sel[sl]=false;
	    			sl--;
	    			sel[sl]=true;
	        	}
	        	break;
	    	case KeyEvent.VK_B:
	        	scheme[sl] = "Black";
	        	cc[sl] = C;
	        	break;
	    	case KeyEvent.VK_R:
	    		scheme[sl] = "Red";
	    		cc[sl] = R;
	        	break;
	    	case KeyEvent.VK_O:
	    		scheme[sl] = "Orange";
	    		cc[sl] = O;
	        	break;
	    	case KeyEvent.VK_Y:
	    		scheme[sl] = "Yellow";
	    		cc[sl] = Y;
	        	break;
	    	case KeyEvent.VK_G:
	    		scheme[sl] = "Green";
	    		cc[sl] = G;
	        	break;
	    	case KeyEvent.VK_L:
	    		scheme[sl] = "Blue";
	    		cc[sl] = B;
	        	break;
	    	case KeyEvent.VK_P:
	    		scheme[sl] = "Purple";
	    		cc[sl] = P;
	        	break;
	    	case KeyEvent.VK_W:
	    		scheme[sl] = "White";
	    		cc[sl] = W;
	        	break;
	    	case KeyEvent.VK_A:
	    		scheme[sl] = "Gray";
	    		cc[sl] = A;
	        	break;
	    	case KeyEvent.VK_N:
	    		scheme[sl] = "Brown";
	    		cc[sl] = N;
	        	break;
	    	case KeyEvent.VK_K:
	    		scheme[sl] = "Pink";
	    		cc[sl] = K;
	        	break;
			}
    		Arrays.fill(c[0], cc[0]);
	        Arrays.fill(c[1], cc[1]);
	        Arrays.fill(c[2], cc[2]);
	        Arrays.fill(c[3], cc[3]);
	        Arrays.fill(c[4], cc[4]);
	        Arrays.fill(c[5], cc[5]);
	        Moves = 0;
	        Ready = 0;
	        done = 0;
    		break;
    	}
        e.consume();
    }
    
    public void keyReleased(KeyEvent e) {e.consume();}
    public void keyTyped(KeyEvent e) {e.consume();}
}