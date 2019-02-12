package assignment1;

import java.awt.geom.Point2D;
import java.util.Scanner;

/**
 * 
 * * Pedram Ahadinejad 211438736 Section M 2011
 * 
 * The class SimplePolygon implements the Polygon interface.
 * 
 * It is intended to be further extended by ConvexPolygon.
 * 
 * @author Andy Mirzaian
 */
public class SimplePolygon implements Polygon {

	/********* protected fields ************************/

	protected int n; // number of vertices of the polygon
	protected Point2D.Double[] vertices; // vertices[0..n-1] around the polygon boundary

	/********* protected constructors ******************/

	/**
	 * constructor used in the static factory method getNewPoly()
	 * 
	 * @param size number of edges (also vertices) of the polygon
	 */

	protected SimplePolygon(int size) {
		this.n = size;
		vertices = new Point2D.Double[size];

	}

	/** default no-parameter constructor */
	protected SimplePolygon() {
		getAllSizeX();

	}

	protected SimplePolygon(String here) {
		getAllSizeY();

	}

	/********* public getters & toString ***************/

	/**
	 * static factory method constructs and returns an unverified simple-polygon,
	 * initialised according to user provided input data. Runs in O(n) time.
	 * 
	 * @return an unverified simple-polygon instance
	 */
	public static SimplePolygon getNewPoly() {
		int size;
		Scanner input = new Scanner(System.in);
		size = input.nextInt();
		SimplePolygon p = new ConvexPolygon(size);

		for (int i = 0; i < size; i++) {
			Point2D.Double point = new Point2D.Double(input.nextDouble(), input.nextDouble());
			p.vertices[i] = point;
		}

		return p;
	}

	/**
	 * 
	 * @return n, the number of edges (equivalently, vertices) of the polygon.
	 */
	public int getSize() {
		return n;
	}

	public int[] getAllSizeX() {

		int[] x = new int[n];

		for (int i = 0; i < n; i++) {
			int xx = (int) vertices[i].getX();

			x[i] = xx;
		}
		return x;

	}

	public int[] getAllSizeY() {

		int[] y = new int[n];

		for (int i = 0; i < n; i++) {
			int yy = (int) vertices[i].getY();

			y[i] = yy;
		}
		return y;

	}

	/**
	 * 
	 * @param i index of the vertex.
	 * @return the i-th vertex of the polygon.
	 * @throws IndexOutOfBoundsException if {@code i < 0 || i >= n }.
	 */

	public Point2D.Double getVertex(int i) throws IndexOutOfBoundsException {
		Point2D.Double vertix = null;
		try {
			vertix = vertices[i];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error:IndexOutOfBoundsException");
		}
		return vertix;
	}

	/**
	 * @return a String representation of the polygon in O(n) time.
	 */
	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < getSize(); i++) {
			s += "( x:" + getVertex(i).x + ", y: " + getVertex(i).y + ")";
		}

		return s;
	}

	/************** utilities *********************/

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c three input points.
	 * @return twice the signed area of the oriented triangle (a,b,c). Runs in O(1)
	 *         time.
	 */
	public static double delta(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
		double det = 0;
		double ax = a.getX();
		double bx = b.getX();
		double cx = c.getX();
		double ay = a.getY();
		double by = b.getY();
		double cy = c.getY();
		det = ((ax * by) + (ay * cx) + (bx * cy)) - (cx * by) - (cy * ax) - (bx * ay);

		return det;
	}

	/**
	 * @param a
	 * @param b end points of line-segment (a,b).
	 * @param c
	 * @param d end points of line-segment (c,d).
	 * @return true if closed line-segments (a,b) and (c,d) are disjoint. Runs in
	 *         O(1) time.
	 */
	public static boolean disjointSegments(Point2D.Double a, Point2D.Double b, Point2D.Double c, Point2D.Double d) {
		boolean isDis = true;

		// (a,b,c) l1
		// (a,b,d) l2
		// (c,d,a) p1
		// (c,d,b) p2

		double l1 = delta(a, b, c);
		double l2 = delta(a, b, d);
		double p1 = delta(c, d, a);
		double p2 = delta(c, d, b);

		double l1l2 = l1 * l2;
		double p1p2 = p1 * p2;

		if ((l1l2 < 0 && p1p2 < 0)) {
			isDis = false;
		} else if (l1l2 == p1p2 && l1l2 == 0) {
			isDis = false;
		}

		return isDis;
	}

	/**
	 * @param i
	 * @param j indices of two edges of the polygon.
	 * @return true if the i-th and j-th edges of the polygon are disjoint. Runs in
	 *         O(1) time.
	 * @throws IndexOutOfBoundsException if i or j are outside the index range
	 *                                   [0..n-1].
	 */
	public boolean disjointEdges(int i, int j) throws IndexOutOfBoundsException {
		return disjointSegments(getVertex(i - 1), getVertex(i), getVertex(j - 1), getVertex(j % n));
	}

	/**
	 * This method verifies whether the claimed "simple-polygon" is indeed simple.
	 * 
	 * @return true if the polygon is simple. Runs in O(n^2) time.
	 */
	public boolean isSimple() {
		boolean isSimple = true;

		for (int i = 1; i <= n - 2; i++) {
			for (int j = i + 2; j < i + 3; j++) {
				isSimple = isSimple && disjointEdges(i, j);
			}
		}

		return isSimple;
	}

	/************ perimeter & area ***************/

	/**
	 * 
	 * @return the sum of the edge lengths of the polygon. Runs in O(n) time.
	 */

	public double length(Point2D.Double a, Point2D.Double b) {
		double distance = 0;
		double temp = 0;
		temp = Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getX() - b.getX(), 2);
		distance = Math.sqrt(temp);
		return distance;
	}

	public double perimeter() {
		double per = 0;
		int i = -1;
		boolean exit = true;
		;
		for (i = 0; i < n && exit;) {
			if (i == (n - 1)) {
				i = 0;
				exit = (i == -1);
				per += length(getVertex(n - 1), getVertex(i));
			} else {

				per += length(getVertex(i), getVertex(i + 1));
				i++;
			}
		}
		return per;
	}

	/**
	 * 
	 * @return area of the polygon interior. Runs in O(n) time not counting the
	 *         simplicity test.
	 * @throws NonSimplePolygonException if the polygon is non-simple.
	 */
	public double area() throws NonSimplePolygonException {
		double area = 0;
		try {
			if (isSimple()) {
				for (int a = 0; a < getSize() - 2; a++) {
					area += delta(getVertex(0), getVertex(a + 1), getVertex(a + 2));
				}
				System.out.println("This Polygon is SSsiiMmmPleeeee");

			} else {
				throw new NonSimplePolygonException();
			}
		} catch (NonSimplePolygonException e) {
			System.out.println("This Polygon is **NOT** SSsiiMmmPleeeee");
		}

		return Math.abs(area / 2);
	}

	public Point2D.Double maxPoint() {

		Point2D.Double point = null;
		double xMax = vertices[2].x;
		double yMax = vertices[2].x;
		for (int i = 0; i < getSize(); i++) {
			if (xMax < vertices[i].x) {
				xMax = vertices[i].x;
				yMax = vertices[i].y;
			}
		}
		point = new Point2D.Double(xMax + 100, yMax + 100);

		return point;
	}

	public boolean contains(Point2D.Double p) {
		boolean isIn = false;
		int counter = 0;

		for (int a = 0; a <= getSize() - 1; a++) {
			if (a == getSize() - 1) {
				isIn = !disjointSegments(p, maxPoint(), vertices[a], vertices[(a + 1) % getSize()]);
//				System.out.println("checinkg "+"("+ vertices[a].getX() +"," +vertices[a].getY() +")" +" and "+ "("+vertices[(a+1)%getSize()].getX() +"," + vertices[(a+1)%getSize()].getY()+")" +" with "+ p + " and " + "("+ maxPoint().getX() + ","+maxPoint().getY() + ")");
			} else {
				isIn = !disjointSegments(p, maxPoint(), vertices[a], vertices[(a + 1)]);
//			System.out.println("checinkg "+"("+ vertices[a].getX() +"," + vertices[a].getY() +")" +" and "+ "("+vertices[(a+1)%getSize()].getX() +"," + vertices[(a+1)%getSize()].getY()+")" +" with "+ p + " and " + "("+ maxPoint().getX() + ","+maxPoint().getY() + ")");
			}
			if (isIn) {
				counter++;
			}

		}

		if (counter % 2 == 0) {
			isIn = false;
		} else {
			isIn = true;
		}

		return isIn;

	}
}