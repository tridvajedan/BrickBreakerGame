import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PowerUpManager {
    public ArrayList<Powerup> powerups = new ArrayList<>();
    public GameManager manager;

    public PowerUpManager(GameManager manager)
    {
        this.manager = manager;
    }

    public void spawn()
    {
        Random r = new Random();
        if(r.nextInt(50) == 49)
        {
            System.out.println("Spawned");
            Powerup powerup = new Powerup(500,800);
            addPowerup(powerup);
        }
    }

    public void addPowerup(Powerup powerup)
    {
        powerups.add(powerup);
    }
    public void removePowerup(Powerup powerup)
    {
        powerups.remove(powerup);
    }

    public void draw(Graphics g)
    {
        for(Powerup powerup : powerups)
        {
            powerup.move(powerup.x + powerup.velx, powerup.y + powerup.vely);
            powerup.draw(g);
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
                manager.paddlex += powerup.powerUpValue;
                powerups.remove(powerup);
            }
        }
    }
}
