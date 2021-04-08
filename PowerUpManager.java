import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PowerUpManager {
    public ArrayList<Powerup> powerups = new ArrayList<>();
    public GameManager manager;
    private final Random rand = new Random();

    public PowerUpManager(GameManager manager)
    {
        this.manager = manager;
    }

    public void spawn()
    {
        if(rand.nextInt(250) == 249)
        {
            Powerup powerup = new Powerup(rand.nextInt(1000), 0);
            System.out.println("Spawned at: " + powerup.x + " | " + powerup.y);
            addPowerup(powerup);
        }
    }

    public void addPowerup(Powerup powerup)
    {
        powerups.add(powerup);
    }

    public void draw(Graphics g)
    {
        for(int i =0; i < powerups.size();i++)
        {
            Powerup powerup = powerups.get(i);
            powerup.move(powerup.x + powerup.velx, powerup.y + powerup.vely);
            powerup.draw(g);
            System.out.println("Powerup " + i + " " + powerup.x + " || " + powerup.y);
        }
    }

    public void checkHit(Rectangle paddle)
    {
        for(int i = 0; i < powerups.size();i++)
        {
            Powerup powerup = powerups.get(i);
            if(paddle.intersects(new Rectangle(powerup.x,powerup.y,30,30)))
            {
                manager.paddleSizeX += powerup.powerUpValue;
                if(powerup.powerUpValue < 0)
                {
                    manager.paddlex -= powerup.powerUpValue /2;
                }
                else if(powerup.powerUpValue > 0)
                {
                    manager.paddlex += powerup.powerUpValue /2;
                }
                powerups.remove(powerup);
            }
            else if(powerup.y > 830)
            {
                powerups.remove(powerup);
            }
        }
    }
}