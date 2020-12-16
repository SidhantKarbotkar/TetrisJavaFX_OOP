package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
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
	public static Pane group = new Pane();
	public static Scene scene = new Scene(group, XMAX + 150, YMAX);
	
	//GameObjects
	private static Tetromino object;
	private static Tetromino nextObject = Controller.makeRect();
	
	//game units
	public static int score = 0;
	public static int lineno = 0;
	
	public static boolean game_over = true;
	
	public void start(Stage Stage) throws Exception {
		UImanager ui = new UImanager();
		ui.gameMenu(group);
		
		Stage.setScene(scene);
		Stage.show();
	}
	
	public static void generate_tetromino() {
		Tetromino a = nextObject;
		nextObject = Controller.makeRect();
		object = a;
		group.getChildren().addAll(a.a, a.b, a.c, a.d);
		moveOnKeyPress(a);
	}
	
	private static void moveOnKeyPress(Tetromino t) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case RIGHT:
					Controller.MoveRight(t);
					break;
				case DOWN:
					Controller.MoveDown(t);
					//score++;
					break;
				case LEFT:
					Controller.MoveLeft(t);
					break;
				case UP:
					//change pattern
					break;
				default:
				}
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
