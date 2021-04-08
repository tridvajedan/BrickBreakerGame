import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    public int brickWidth,brickHeight;
    public ArrayList<Rectangle> bricks = new ArrayList<>();
    public ArrayList<Rectangle> startingBricks = new ArrayList<>();
    public int brickCount;
    public GameManager manager;
    public MapGenerator(int rows,int collumns,GameManager manager)
    {
        this.manager = manager;
        brickWidth = 350/rows;
        brickHeight = 350/collumns;
        for(int i = 0; i < collumns; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                Rectangle brick = new Rectangle(i*brickWidth+80,j*brickHeight+50,brickWidth,brickHeight);
                System.out.println(brick.x +" " + brick.y +" " + brick.width+" " + brick.height+" ");
                System.out.println("---------------------------");
                bricks.add(brick);
            }
        }
        startingBricks = bricks;
        brickCount = bricks.size();
    }

    public void draw(Graphics g2) {
        Color backgroundColor = new Color(15, 114, 114);
        Graphics2D g = (Graphics2D) g2;
        for (Rectangle brick : bricks)
        {
            g.setColor(Color.black);
            g.fillRect(brick.x,brick.y,brick.width,brick.height);
            g.setStroke(new BasicStroke(3));
            g.setColor(backgroundColor);
            g.drawRect(brick.x,brick.y,brick.width,brick.height);
        }
    }

    public HitType checkHit(Rectangle circle) {
        for(int i = 0; i < bricks.size();i++)
        {
            if(circle.intersects(bricks.get(i))) {
                bricks.remove(bricks.get(i));
                return HitType.HIT;
            }
        }
        brickCount = bricks.size();
        return HitType.NOT_HIT;
    }

    public void reset()
    {
        bricks = startingBricks;
    }
}