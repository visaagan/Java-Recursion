package ProgrammingExam1;
import java.awt.Color;
import java.awt.List;
import java.util.ArrayList; 

public class PE1 {
	public static void main(String[] args) {
		
		/* A canvas should be created for drawing.
		 * All the shapes should be drawn on the canvas.
		 * The bottom left coordinate of the canvas is (0,0)
		 * The top right coordinates of the canvas is (1, 1) 
		 * The input parameter to Draw() constructor is the 
		 * title of the canvas.
		 */
       // Draw blankCanvas = new Draw("Programmign Exam 1");
        
        /* To draw a point, point function is called with proper parameters: 
         * point(x_coordinate_of_point, y_coordinate_of_point)
         */
        //blankCanvas.point(0.7, 0.7);
        
        /* To draw a circle, circle function is called with proper parameters: 
         * circle(x_coordinate_of_center, y_coordinate_of_center, radius)
         */
        //blankCanvas.circle(0.5, 0.5, 0.5);

        /* To draw a square, square function is called with proper parameters: 
         * square(x_coordinate_of_center, y_coordinate_center, sides_half_length)
        */
        //blankCanvas.square(0.5,  0.5, 0.4);
        
        /*
         * To change the color of the pen, setPenColor is used with three numbers that are in [0, 255] range.
         * All the colors can be made as a combination of red, green and blue. 
         * The parameters show the richness of red, green and blue of a color. 
         * For example setPenColor(new Color(0, 0, 0) sets the color of the pen
         * to black and setPenColor(new Color(255, 255, 255) sets the color to 
         * white.
         */
       //blankCanvas.setPenColor(new Color(150, 150, 150));
         
         /* To draw a line, line function is called 
          * with proper parameters: 
          * line(x_coordinate_of_center, y_coordinate_center, sides_half_length)
         */
        //blankCanvas.line(0.0, 0.5, 1, 0.5);
         
         
     
//        Point p1 = new Point(0.1, 0.1);
//        Point p2 = new Point(0.9, 0.1);
//        Point p3 = new Point(0.5, 0.9);
//        
//         ArrayList<String> points = new ArrayList<String>();         
//         points = PE1.drillPoints(p1, p2, p3, 5, blankCanvas, points);
//         System.out.println(points);
        
//        	String str = nestedCircle (0.51232321, 0.3123213, 0.13213213, 0.1232132132111, blankCanvas, "");
//        	System.out.println(str);
        
        	//String str = squares(0.5, 0.5, 0.2, 3, blankCanvas);
        	//System.out.println(str);
         
	}


	/**
	 * This method draws a number of circles that share the same center, as long as the radius is positive.
	 * @param x is the x-coordinate of the circles
	 * @param y is the y-coordinate of the circles. 
	 * @param radius is the radius of a circle. 
	 * 		The function is called with the radius that is cut to two decimal points. 
	 * 		For example 0.39876543210 must be cut to 0.39
	 * @param diff is the difference between the radius of a circle and its immediate inner circle.
	 * @param page is the canvas on which the circles are drawn.
	 * @param radiusList is an accumulated list of the radius of the circles that were drawn. 
	 * @return a list of all the circles' radius that were drawn. 
	 */
	public static String nestedCircle (double x, double y, double radius, double diff,  Draw page, String radiusList) {
	    
	    //Base case 
		if((Math.round(radius * 100.0) / 100.0 <= 0.0)) {
			 return "0.0]";
		}
		
		//Base Case when RadiusList is empty
		if(radiusList.isEmpty()) {
			
			//Adds opening bracket to list
			radiusList += "[";
			
			//Recursive Call
			return radiusList + nestedCircle(x, y, radius, diff, page, radiusList);
		}
		
		else {
			page.circle(x, y, radius);
			//Rounds to two decimal places, separating coordinates by comma's
	        radiusList = Math.round(radius * 100.0) / 100.0 + ", ";
	        
	        //Recursive Call
			return radiusList + nestedCircle(x, y, radius-diff, diff, page, radiusList);
		}
		
		
	
	}
	/**
	 * This method recursively draws 4 squares, whose center falls on a corner of 
	 * previously drawn square. The side of the square is half as much as the side of the 
	 * square that is drawn in previous round. 
	 * @param x is the x-coordinate of the square
	 * @param y is the y-coordinate of the square
	 * @param halfLength is half the size of the square's side
	 * @param order is the number of the rounds by which a set of squares is drawn
	 * @param page is the canvas on which the squares are drawn.
	 * @return a list of the center of smallest squares that are drawn.. 
	 * 		The coordinates should be cut to one decimal point. For example:
	 * 		 0.39876543210 is cut to 0.3 
	 */
	public static String squares (double x, double y, double halfLength, int order, Draw page) {
		
		//Base Case
		if(order == 1) {
			
			//Draws squares
			page.square(x, y, halfLength);

			//Returns coordinates of the smallest squares cut to one decimal point 
			return "[" + Math.round(x * 10.0) / 10.0 + ", " + Math.round(y * 10.0) / 10.0 + "]";
		}
		
		else {
			
			//Draws squares
			page.square(x, y, halfLength);
			
			//Will consist of coordinates after recursive calls
			String smallestSquareCoords = "";
			
			//Recursive call on upper right squares
			smallestSquareCoords += squares(x + halfLength, y + halfLength, halfLength / 2, order - 1, page);
			
			//Recursive call on bottom right squares
			smallestSquareCoords += squares(x + halfLength, y - halfLength, halfLength / 2, order - 1, page);
			
			//Recursive call on upper left squares
			smallestSquareCoords += squares(x - halfLength, y + halfLength, halfLength / 2, order - 1, page);

			//Recursive call on bottom left squares
			smallestSquareCoords += squares(x - halfLength, y - halfLength, halfLength / 2, order - 1, page);
			
			//Returns coordinates of the smallest squares
			return smallestSquareCoords;
		}
	}
	
	/**
	 * This method specifies which coordinates should be drilled. It also draw the 
	 * horizontal line of each triangle. No duplicate point should be added to the output. 
	 * @param p1 is one of the vertex of the triangle
	 * @param p2 is the second vertex of the triangle
	 * @param p3 is the third vertex of the triangle
	 * @param order is the number of times a nested triangle should be drawn. 
	 * 			<code> order >= 0 </code>, however if it is zero, nothing should be drawn
	 * @param page is the canvas on which this method draws.
	 * @param array is the list of the points that should be drilled. To add to this list point.toString() must be added.
	 * @return an array that contains all the points that should be drilled. this method should not have any duplicate points in it. 
	 */
	public static ArrayList<String> drillPoints(Point p1, Point p2, Point p3, int order, Draw page, ArrayList<String> array) {
		// your code goes here. Task 3
		if(order == 0) {
			return array;
		}
		
		if(order == 1) {
			//bottom left point
			page.point(p1.x,p1.y);
			
			//bottom right point
			page.point(p2.x,p2.y);
			
			//top point
			page.point(p3.x,p3.y);
			
			//Adds coordinates to the ArrayList
			array.add(p1.toString());
			array.add(p2.toString());
			array.add(p3.toString());
		
			//Returns list of coordinates of vertices
			return array;
		}
		
		else {
			
			//bottom left point
			page.point(p1.x,p1.y);
			
			//bottom right point
			page.point(p2.x,p2.y);
			
			//top point
			page.point(p3.x,p3.y);
			
			//Draws a line between the first two points
			page.line(p1.x,p1.y, p2.x,p2.y);
		
			
			//Adds p1, p2, p3 to array list
			array.add(p1.toString());
			array.add(p2.toString());
			array.add(p3.toString());
			

			//Creates new points considering midpoints of p1, p2, p3
			Point p4 = new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	        Point p5 = new Point((p2.x + p4.x) / 2, (p2.y + p3.y) / 2);
	        Point p6 = new Point((p1.x + p3.x) / 2, (p1.y + p3.y) / 2);
	        
	        //Draws the line for the new points
			page.line(p6.x,p6.y, p5.x,p5.y);

	        
	         //Recursive calls
			 drillPoints(p1, p4, p6, order - 1, page, array);
			 drillPoints(p4, p2, p5, order - 1, page, array);
			 drillPoints(p6, p5, p3, order - 1, page, array);
			 
			 //Returns list of coordinates of vertices
			 return array;

		}
		
	}

}
/**
 * This class creates a point. 
 *
 */
class Point {
	double x; 
	double y; 
	/**
	 * This is the constructor that builds a point
	 * @param x is the x-coordinate of the point
	 * @param y is the y-coordinate of the point
	 */
	public Point(double x, double y) {
		//Constructors
		this.x = x;
		this.y = y;
	}
	/**
	 * This method returns the mid point of a line, 
	 * whose two ends are given.
	 * @param p1 is one end of the line
	 * @param p2 is the other end of the line
	 * @return the mid point of the line. Both the 
	 * coordinates are cut to two decimal points. 
	 * e.g. 0.37654 is cut to 0.37
	 */
	public static Point midpoint(Point p1, Point p2) {
		
		//Midpoints of x and y
		double midpointX = (p1.x + p2.x) / 2 ;
	    double midpointY = (p1.y + p2.y) / 2 ;
	  
	    //Rounds both variables to two decimal places
	    midpointX = Math.round(midpointX * 100.0) / 100.0;
	    midpointY = Math.round(midpointY * 100.0) / 100.0;

	    //Returns new midpoints
		return new Point(midpointX, midpointY);
		
	}
	@Override
	/**
	 * This method returns the coordinate of this object as a string.
	 */
	public String toString() {
		return "["+this.x + ", "+ this.y +"]";
	}
	
}
