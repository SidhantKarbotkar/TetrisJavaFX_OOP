package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	//size of the block will be 25
	public static final int MOVE = 25;
	public static final int SIZE = 25;
	
	//the well size
	public static int XMAX = SIZE * 12;
	public static int YMAX = SIZE * 24;
	public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE];
	
	//camera
	private static Pane group = new Pane();
	private static Scene scene = new Scene(group, XMAX + 150, YMAX);
	
	//GameObjects
	private static Tetromino object;
	private static Tetromino nextObject = Controller.makeRect();
	
	//game units
	public static int score = 0;
	public static int lineno = 0 ;
	
	public String textStyle = "-fx-font: 20 roboto ;";
	private boolean remove;
	
	public void start(Stage Stage) throws Exception {
		//ui
		//line between well and UI
		Canvas canvas = new Canvas(XMAX + 150, YMAX);
	    group.getChildren().add(canvas);
	    
	    group.setStyle("-fx-background-color: skyblue");
		
		Line line = new Line(XMAX, 0, XMAX, YMAX);
		
		//High-score
		Text scoretext = new Text("Score: ");
		scoretext.setStyle(textStyle);
		//scoretext.setFill(Color.WHITE);
		scoretext.setY(50);
		scoretext.setX(XMAX + 5);
		
		//Level
		Text level = new Text("Lines: ");
		level.setStyle(textStyle);
		level.setY(100);
		level.setX(XMAX + 5);
		level.setFill(Color.GREEN);
		
		group.getChildren().addAll(scoretext, line, level);
		
		Tetromino a = nextObject;
		object = a;
		nextObject = Controller.makeRect();
		group.getChildren().addAll(a.a, a.b, a.c, a.d);
		//move
		moveOnKeyPress(a);

		Stage.setScene(scene);
		Stage.show();
	}
	
	private void RemoveRows(Pane pane) {
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
				score += 50;
				lineno++;
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
	
	private void moveOnKeyPress(Tetromino t) {
		/*for (int i = 0; i < MESH[0].length; i++) {
			for (int j = 0; j < MESH.length; j++) {
				String g = String.valueOf(MESH[j][i]);
				Text grid = new Text(g);
				grid.setY(i*SIZE+(SIZE/2));
				grid.setX(j*SIZE+(SIZE/2));
				group.getChildren().addAll(grid);
			}
		}*/
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case RIGHT:
					Controller.MoveRight(t);
					break;
				case DOWN:
					MoveDown(t);
					score++;
					break;
				case LEFT:
					Controller.MoveLeft(t);
					break;
				case UP:
					//change pattern
					break;
				}
			}
		});
	}
	
	private void MoveDown(Tetromino t) {
		if (t.a.getY() == YMAX - SIZE || t.b.getY() == YMAX - SIZE || t.c.getY() == YMAX - SIZE
				|| t.d.getY() == YMAX - SIZE || moveA(t) || moveB(t) || moveC(t) || moveD(t)) {
			MESH[(int) t.a.getX() / SIZE][(int) t.a.getY() / SIZE] = 1;
			MESH[(int) t.b.getX() / SIZE][(int) t.b.getY() / SIZE] = 1;
			MESH[(int) t.c.getX() / SIZE][(int) t.c.getY() / SIZE] = 1;
			MESH[(int) t.d.getX() / SIZE][(int) t.d.getY() / SIZE] = 1;
			RemoveRows(group);

			Tetromino a = nextObject;
			nextObject = Controller.makeRect();
			object = a;
			group.getChildren().addAll(a.a, a.b, a.c, a.d);
			moveOnKeyPress(a);
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
	
	private boolean moveA(Tetromino t) {
		return (MESH[(int) t.a.getX() / SIZE][((int) t.a.getY() / SIZE) + 1] == 1);
	}

	private boolean moveB(Tetromino t) {
		return (MESH[(int) t.b.getX() / SIZE][((int) t.b.getY() / SIZE) + 1] == 1);
	}

	private boolean moveC(Tetromino t) {
		return (MESH[(int) t.c.getX() / SIZE][((int) t.c.getY() / SIZE) + 1] == 1);
	}

	private boolean moveD(Tetromino t) {
		return (MESH[(int) t.d.getX() / SIZE][((int) t.d.getY() / SIZE) + 1] == 1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
