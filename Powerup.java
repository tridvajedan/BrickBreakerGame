import java.awt.*;
import java.util.Random;

public class Powerup {

    public int powerUpValue = 15;
    public int velx = 0, vely = 3;
    public int x,y;
    public Powerup(int x,int y)
    {
        this.x = x;
        this.y = y;
        powerUpValue = getPowerUpValue();
    }

    public void move(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.drawOval(x,y,30,30);
        g.fillOval(x,y,30,30);
    }

    public int getPowerUpValue()
    {
        Random r = new Random();
        int value = 0;
        if(r.nextInt(3) == 2)
        {
            value = -15;
        }
        else
        {
            value = 15;
        }
        return value;
    }
}
