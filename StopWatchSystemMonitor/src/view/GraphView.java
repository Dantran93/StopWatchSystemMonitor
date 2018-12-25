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
	
	
	
	/**
	 * GraphView Constructor (double, double)
	 * 
	 * Purpose:
	 */
	
	public GraphView (double width, double height)
	{
		super(width, height);
		this.graphics = this.getGraphicsContext2D();
		drawFrame(false);
	} // GraphView Constructor (double, double)
	
	
	
	/**
	 * drawFrame()
	 * 
	 * Purpose:
	 */
	
	public void drawFrame (boolean appLaunched)
	{
		if (!appLaunched)
		{
			graphics.setFill(Color.WHITE);
			graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
		else
		{
			
		}
	} // drawFrame()
	
} // class GraphView
