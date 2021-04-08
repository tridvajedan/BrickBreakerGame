
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameManager extends JPanel implements ActionListener, KeyListener {

    public int ballx,bally,velx,vely ;
    public int ballsizex,ballsizey;
    public int paddlex,paddley;
    public int paddleSizeX = 150,paddleSizeY = 25;
    public int paddlevelx,paddlevely;
    public Color firecolor = new Color(203, 92, 92);
    public boolean canFlame = true;
    public boolean isOnFire = false;
    public boolean running;
    public MapGenerator generator;
    public PowerUpManager powerUpManager;
    public Color backgroundColor = new Color(15, 114, 114);
    private ScheduledExecutorService fireCooldown;
    private ScheduledExecutorService onFireCooldown;
    private Runnable countdown;
    private Runnable flaming;
    private Timer timer;

    public GameManager()
    {
        fireCooldown = Executors.newScheduledThreadPool(1);
        onFireCooldown = Executors.newScheduledThreadPool(1);
        countdown = new Runnable() {
            @Override
            public void run() {
                canFlame = true;
            }
        };
        flaming = new Runnable() {
            @Override
            public void run() {
                isOnFire = false;
            }
        };
        generator = new MapGenerator(3,7,this);
        running = true;
        powerUpManager = new PowerUpManager(this);
    //    powerUpManager.addPowerup(new Powerup(500,this.getHeight()));
        paddlevelx = 15;paddlevely = 15;
        ballx = 505;bally = 670;velx = 5;vely = 5;
        ballsizex = 40;ballsizey = 40;
        paddlex = 450;paddley = 725;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(5,this);
        fireCooldown.schedule(countdown,10, TimeUnit.SECONDS);
        timer.start();
    }

    public void paintComponent(Graphics g)
    {
        if(!running)
        {
            g.setColor(Color.white);
            g.drawString("You Won! Press Backspace to reset!", getWidth()/2,getHeight()/2);
        }
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.drawRect(0,0,this.getWidth(),this.getHeight());
        g.fillRect(0,0,getWidth(),getHeight());

        powerUpManager.draw(g);
        generator.draw(g);

        g.setColor(Color.white);
        g.drawString("Can flame " + Boolean.toString(canFlame),20,20);

        g.setColor(Color.darkGray);
        g.drawRect(paddlex,paddley,paddleSizeX,paddleSizeY);
        g.fillRect(paddlex,paddley,paddleSizeX,paddleSizeY);

        if(isOnFire)
        {
            g.setColor(firecolor);
        }
        else {
            g.setColor(Color.YELLOW);
        }
        g.drawOval(ballx,bally,ballsizex,ballsizey);
        g.fillOval(ballx,bally,ballsizex,ballsizey);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            powerUpManager.checkHit(new Rectangle(paddlex,paddley,paddleSizeX,paddleSizeY));
            Random r = new Random();
            HitType isHit = generator.checkHit(new Rectangle(ballx,bally,ballsizex,ballsizey));
            if(isHit == HitType.HIT)
            {
                if(isOnFire) {
                    velx = -velx;
                    vely = vely;
                }
                else
                {
                    velx = -velx;
                    vely = -vely;
                }
            }
            if(generator.brickCount == 0)
            {
                running = false;
            }
            timer.start();
            if (new Rectangle(ballx, bally, ballsizex, ballsizey).intersects(new Rectangle(paddlex, paddley, paddleSizeX, paddleSizeY))) {
                vely = -vely;
                velx = (ballx - paddlex) / 10 -5;
                if(velx > 5)
                {
                    velx = 5;
                }
                else if(velx < -5)
                {
                    velx = -5;
                }
                System.out.println("vel x " + velx);
            }

            ballx += velx;
            bally += vely;

            if (ballx < 0) {
                velx = -velx;
            }
            if (bally < 0) {
                vely = -vely;
            }
            if (ballx > getWidth() - 40) {
                velx = -velx;
            }
            if (bally > paddley) {
                System.out.println("Game Over!");
                running = false;
            }
       //     powerUpManager.spawn();
            this.repaint();
        }
    }

    public void reset()
    {
        running = true;
        paddlevelx = 15;paddlevely = 15;
        ballx = 505;bally = 670;velx = 5;vely = 5;
        ballsizex = 40;ballsizey = 40;
        paddlex = 450;paddley = 725;
        generator.reset();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(running) {
            char key = e.getKeyChar();
            if (key == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (paddlex > 0) {
                    paddlex -= paddlevelx;
                }
            } else if (key == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (paddlex + 150 < getWidth()) {
                    paddlex += paddlevelx;
                }
            }
            else if(key == 'f' && canFlame)
            {
                canFlame = false;
                fireCooldown.schedule(countdown,20,TimeUnit.SECONDS);
                onFireCooldown.schedule(flaming,5,TimeUnit.SECONDS);
                isOnFire = true;
            }
     /*       else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            {
                reset();
            }*/
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
