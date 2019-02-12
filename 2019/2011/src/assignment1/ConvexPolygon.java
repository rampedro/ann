package assignment1;

/**
 * Pedram Ahadinejad 211438736 Section M 2011
 * 
 * The class ConvexPolygon extends SimplePolygon.
 * 
 * the ConvexPolygon which is inherited from SimplePolygon calls the Super or
 * its parent (SimplePolygon)
 * 
 * @author Andy Mirzaian
 */
public class ConvexPolygon extends SimplePolygon {
	static int size;

	public ConvexPolygon(int size) {
		super(size);
	}

	public boolean isCon() {
		boolean isCon = true;
		double a = 0;
		for (int i = 0; i < getSize() - 2; i++) {
			double s = SimplePolygon.delta(getVertex(i), getVertex(i + 1), getVertex(i + 2));
			if (s > 0) {
				s = 1;
			} else {
				s = -1;
			}

			a += s;
		}

		if ((Math.abs(a) == getSize() - 2) && isSimple()) {
			isCon = true;
		} else {
			isCon = false;
		}

		return isCon;
	}

}