package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * GraphView.java
 * Author: Daniel Tranfaglia
 * 
 * Purpose: 
 */

public class GraphView extends Canvas
{
	private GraphicsContext graphics;
	
	
	public GraphView (double width, double height)
	{
		super(width, height);
		this.graphics = this.getGraphicsContext2D();
		drawFrame();
	}
	
	
	
	
	public void drawFrame ()
	{
		graphics.setFill(Color.WHITE);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	
	
} // class GraphView
