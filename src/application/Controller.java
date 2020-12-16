package application;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Controller {
	// Getting numbers and the MESH from Main class
	public static final int MOVE = Main.MOVE;
	public static final int SIZE = Main.SIZE;
	public static int XMAX = Main.XMAX;
	public static int YMAX = Main.YMAX;
	public static int[][] MESH = Main.MESH;
	private static Pane group = Main.group;

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
	
	private static void RemoveRows(Pane pane) {
		ArrayList<Node> rects = new ArrayList<Node>();
		ArrayList<Integer> lines = new ArrayList<Integer>();
		ArrayList<Node> newrects = new ArrayList<Node>();
		int full = 0;
		
		for (int i = 0; i < MESH[0].length; i++) {
			for (int j = 0; j < MESH.length; j++) {
				if (MESH[j][i] == 1) full++;
			}
			if (full == MESH.length) lines.add(i);
			full = 0;
		}
		
		if (lines.size() > 0)
			do {
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle) rects.add(node);
				}
				//score += 50;
//				//lineno++;
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() == lines.get(0) * SIZE) {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						pane.getChildren().remove(node);
					} else
						newrects.add(node);
				}

				for (Node node : newrects) {
					Rectangle a = (Rectangle) node;
					if (a.getY() < lines.get(0) * SIZE) {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
						a.setY(a.getY() + SIZE);
					}
				}
				lines.remove(0);
				rects.clear();
				newrects.clear();
				for (Node node : pane.getChildren()) {
					if (node instanceof Rectangle)
						rects.add(node);
				}
				for (Node node : rects) {
					Rectangle a = (Rectangle) node;
					try {
						MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				rects.clear();
			} while (lines.size() > 0);
	}
	
	public static void MoveDown(Tetromino t) {
		if (t.a.getY() == YMAX - SIZE || t.b.getY() == YMAX - SIZE || t.c.getY() == YMAX - SIZE
				|| t.d.getY() == YMAX - SIZE || moveA(t) || moveB(t) || moveC(t) || moveD(t)) {
			MESH[(int) t.a.getX() / SIZE][(int) t.a.getY() / SIZE] = 1;
			MESH[(int) t.b.getX() / SIZE][(int) t.b.getY() / SIZE] = 1;
			MESH[(int) t.c.getX() / SIZE][(int) t.c.getY() / SIZE] = 1;
			MESH[(int) t.d.getX() / SIZE][(int) t.d.getY() / SIZE] = 1;
			RemoveRows(group);
			Main.generate_tetromino();
		}
		if (t.a.getY() + MOVE < YMAX && t.b.getY() + MOVE < YMAX && t.c.getY() + MOVE < YMAX
				&& t.d.getY() + MOVE < YMAX) {
			int movea = MESH[(int) t.a.getX() / SIZE][((int) t.a.getY() / SIZE) + 1];
			int moveb = MESH[(int) t.b.getX() / SIZE][((int) t.b.getY() / SIZE) + 1];
			int movec = MESH[(int) t.c.getX() / SIZE][((int) t.c.getY() / SIZE) + 1];
			int moved = MESH[(int) t.d.getX() / SIZE][((int) t.d.getY() / SIZE) + 1];
			if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				t.a.setY(t.a.getY() + MOVE);
				t.b.setY(t.b.getY() + MOVE);
				t.c.setY(t.c.getY() + MOVE);
				t.d.setY(t.d.getY() + MOVE);
			}
		}
	}
	
	private static boolean moveA(Tetromino t) {
		return (MESH[(int) t.a.getX() / SIZE][((int) t.a.getY() / SIZE) + 1] == 1);
	}

	private static boolean moveB(Tetromino t) {
		return (MESH[(int) t.b.getX() / SIZE][((int) t.b.getY() / SIZE) + 1] == 1);
	}

	private static boolean moveC(Tetromino t) {
		return (MESH[(int) t.c.getX() / SIZE][((int) t.c.getY() / SIZE) + 1] == 1);
	}

	private static boolean moveD(Tetromino t) {
		return (MESH[(int) t.d.getX() / SIZE][((int) t.d.getY() / SIZE) + 1] == 1);
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