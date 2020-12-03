package application;

import java.util.Arrays;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
	
	public String textStyle = "-fx-font: 20 roboto ;";
	
	public void start(Stage Stage) throws Exception {
		for (int[] a : MESH) {
			Arrays.fill(a, 0);
		}
		
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
		moveOnKeyPress(a);
		
		
		Stage.setScene(scene);
		Stage.show();
	}
	
	private void moveOnKeyPress(Tetromino t) {
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
