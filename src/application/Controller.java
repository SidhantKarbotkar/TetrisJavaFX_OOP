package application;

import javafx.scene.shape.Rectangle;

public class Controller {
	// Getting numbers and the MESH from Main class
	public static final int MOVE = Main.MOVE;
	public static final int SIZE = Main.SIZE;
	public static int XMAX = Main.XMAX;
	public static int YMAX = Main.YMAX;
	public static int[][] MESH = Main.MESH;

	public static void MoveRight(Tetromino t) {
		if (t.a.getX() + MOVE <= XMAX - SIZE && t.b.getX() + MOVE <= XMAX - SIZE
				&& t.c.getX() + MOVE <= XMAX - SIZE && t.d.getX() + MOVE <= XMAX - SIZE) {
			int movea = MESH[((int) t.a.getX() / SIZE) + 1][((int) t.a.getY() / SIZE)];
			int moveb = MESH[((int) t.b.getX() / SIZE) + 1][((int) t.b.getY() / SIZE)];
			int movec = MESH[((int) t.c.getX() / SIZE) + 1][((int) t.c.getY() / SIZE)];
			int moved = MESH[((int) t.d.getX() / SIZE) + 1][((int) t.d.getY() / SIZE)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				t.a.setX(t.a.getX() + MOVE);
				t.b.setX(t.b.getX() + MOVE);
				t.c.setX(t.c.getX() + MOVE);
				t.d.setX(t.d.getX() + MOVE);
			}
		}
	}

	public static void MoveLeft(Tetromino t) {
		if (t.a.getX() - MOVE >= 0 && t.b.getX() - MOVE >= 0 && t.c.getX() - MOVE >= 0
				&& t.d.getX() - MOVE >= 0) {
			int movea = MESH[((int) t.a.getX() / SIZE) - 1][((int) t.a.getY() / SIZE)];
			int moveb = MESH[((int) t.b.getX() / SIZE) - 1][((int) t.b.getY() / SIZE)];
			int movec = MESH[((int) t.c.getX() / SIZE) - 1][((int) t.c.getY() / SIZE)];
			int moved = MESH[((int) t.d.getX() / SIZE) - 1][((int) t.d.getY() / SIZE)];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				t.a.setX(t.a.getX() - MOVE);
				t.b.setX(t.b.getX() - MOVE);
				t.c.setX(t.c.getX() - MOVE);
				t.d.setX(t.d.getX() - MOVE);
			}
		}
	}

	public static Tetromino makeRect() {
		int block = (int) (Math.random() * 100);
		String name;
		Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
				d = new Rectangle(SIZE-1, SIZE-1);
		if (block < 15) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			name = "j";
		} else if (block < 30) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2 - SIZE);
			b.setY(SIZE);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			d.setY(SIZE);
			name = "l";
		} else if (block < 45) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 - SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2);
			d.setY(SIZE);
			name = "o";
		} else if (block < 60) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 - SIZE);
			d.setY(SIZE);
			name = "s";
		} else if (block < 75) { 
			a.setX(XMAX / 2 - SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE);
			name = "t";
		} else if (block < 90) { 
			a.setX(XMAX / 2 + SIZE);
			b.setX(XMAX / 2);
			c.setX(XMAX / 2 + SIZE);
			c.setY(SIZE);
			d.setX(XMAX / 2 + SIZE + SIZE);
			d.setY(SIZE);
			name = "z";
		} else { 
			a.setX(XMAX / 2 - SIZE - SIZE);
			b.setX(XMAX / 2 - SIZE);
			c.setX(XMAX / 2);
			d.setX(XMAX / 2 + SIZE);
			name = "i";
		}
		return new Tetromino(a, b, c, d, name);
	}
}