import java.awt.*;
import java.util.Random;

public class Brick extends Rectangle {
    public int x,y,width,height;
    public Color color;
    public boolean lucky;
    public Brick(int x, int y, int width, int height,boolean lucky) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lucky = lucky;
        if(lucky == true) color = new Color(234, 241, 27, 183);
        else color = new Color(0,0,0);
    }

    public static boolean getLuckyValue()
    {
        Random r = new Random();
        return r.nextInt(3) == 2;
    }
}
