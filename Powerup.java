import java.awt.*;
import java.util.Random;

public class Powerup {

    public int powerUpValue;
    public int velx = 0, vely = 4;
    public int x,y;
    private final Random r = new Random();
    private final Color c = new Color(r.nextInt(245),r.nextInt(245),r.nextInt(245));
    public Powerup(int x,int y)
    {
        this.x = x;
        this.y = y;
        this.powerUpValue = this.getPowerUpValue();
    }

    public void move(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g)
    {
        g.setColor(c);
        g.drawOval(x,y,30,30);
        g.fillOval(x,y,30,30);
    }

    public int getPowerUpValue()
    {
        return r.nextInt(3) == 2 ? -15 : 15;
    }
}