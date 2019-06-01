import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class RenderPanel extends JPanel
{
   
   @Override
   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.setColor(new Color(0)); //Background = black
      g.fillRect(0, 0, 800, 700);
      
      Snake snake = Snake.snake;
      for (Point point : snake.snakeParts)
      {
         g.setColor(Color.RED);
         g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
      }
   }
}