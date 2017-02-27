import java.awt.*;

public abstract class Core {
	
    private static DisplayMode modes[] = {
    	new DisplayMode(1920, 1080, 32, 0),
        new DisplayMode(1920, 1080, 24, 0),
        new DisplayMode(1920, 1080, 16, 0),
    	new DisplayMode(1280, 800, 32, 0),
        new DisplayMode(1280, 800, 24, 0),
        new DisplayMode(1280, 800, 16, 0),
    	new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(640, 480, 16, 0),
    };

    private boolean running;
    protected ScreenManager s;

    //Stop method
    public void stop() {
        running = false;
    }
    
    //call init and gameloop
    public void run() {
        try {
            init();
            gameloop();
        } finally {
            s.restoreScreen();
        }
    }

    //set to full screen
    public void init() {
        s = new ScreenManager();
        DisplayMode dm = s.findFirstCompatibleMode(modes);
        s.setFullScreen(dm);

        Window w = s.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 50));
        w.setBackground(Color.BLACK);
        w.setForeground(Color.WHITE);
        running = true;
    }

    //main game loop
    public void gameloop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;

            update(timePassed);
            Graphics2D g = s.getGraphics();
            draw(g);
            g.dispose();
            s.update();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
                System.err.println("Error when trying to sleep: " + ex);
            }
        }
    }

    //update animation
    public void update(long timePassed) {}

    //draws to the screen
    public abstract void draw(Graphics2D g);
}