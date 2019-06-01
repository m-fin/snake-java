import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener
{
   public static Snake snake;
   public JFrame jframe;
   public RenderPanel renderPanel;
   public Toolkit toolkit;
   public Timer timer = new Timer(20, this);
   
   public ArrayList<Point> snakeParts = new ArrayList<Point>();
   public int ticks = 0, direction = DOWN, score;
   
   public Point head, cherry;

   public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
   
   public Random random;
   
   public boolean over = false;
   
   public Dimension dim;
   
   public Snake()
   {
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      toolkit = Toolkit.getDefaultToolkit();
      jframe = new JFrame("Snake");
      jframe.setVisible(true);
      jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
      jframe.setSize(800, 700); //JFrame size
      jframe.add(renderPanel = new RenderPanel());
      jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      head = new Point(0, 0);
      random = new Random();
      cherry = new Point(dim.width / SCALE, dim.height / SCALE);
      
      timer.start();
   }
   
   public static void main(String[] args)
   {
      snake = new Snake();
   }
   
   @Override
   public void actionPerformed(ActionEvent args)
   {
      renderPanel.repaint();
      ticks++;
      
      if (ticks % 10 == 0 && head != null && over != true)
      {
         if (direction == UP)
         {
            if (head.y - 1 > 0)
               snakeParts.add(new Point(head.x, head.y - 1));
            else
               over = true;
         }
         if (direction == DOWN)
         {
            if (head.y + 1 < dim.height / SCALE)
               snakeParts.add(new Point(head.x, head.y + 1));
            else
               over = true;
         }
         if (direction == LEFT)
         {
            if (head.x - 1 > 0)
               snakeParts.add(new Point(head.x - 1, head.y));
            else
               over = true;
         }
         if (direction == RIGHT)
         {
            if (head.x + 1 < dim.width / SCALE)
               snakeParts.add(new Point(head.x + 1, head.y));
            else
               over = true;   
         }
         snakeParts.remove(0);
         if (cherry != null)
         {
            if (head.equals(cherry))
            {
               score++;
               cherry.setLocation(dim.width / SCALE, dim.height / SCALE);
            }
         }
      }
   }
}
