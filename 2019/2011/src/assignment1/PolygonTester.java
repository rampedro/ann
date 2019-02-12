package assignment1;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * 
 * * Pedram Ahadinejad 211438736 Section M 2011
 * 
 * PolygonTester should enable a thorough testing of the polygon hierarchy.
 * 
 * It should provide an easy to read input-output recording of the test cases.
 * 
 * The student should also submit these recorded test results in TestIO.txt file
 * as part of Assignment1.
 * 
 * @author Andy Mirzaian
 */
public class PolygonTester {

	// TODO: place additional test-helper methods here if you like

	public static void main(String[] args) {

		SimplePolygon sp = SimplePolygon.getNewPoly();
		String vertices = sp.toString();

		System.out.println("____________s.t.a.r.t___________");

		System.out.println(" The Vertices are : " + vertices);
		double area = 0;
		try {
			area = sp.area();
		} catch (NonSimplePolygonException e) {

		}

		System.out.println("What is the Area : " + area);
		System.out.println("What is The Premiter: " + sp.perimeter());

		System.out.println("Is this Polygon Convex: " + ((ConvexPolygon) sp).isCon());

		System.out.println(
				"____________If the Poly is Simple Enter XY of a Point to check if it is in this Polygon___________");

		Scanner input = new Scanner(System.in);
		System.out.println("Does this Polygon Contain point this point: "
				+ sp.contains(new Point2D.Double(input.nextDouble() - 0.5, input.nextDouble() + 0.5)));

	}
}
