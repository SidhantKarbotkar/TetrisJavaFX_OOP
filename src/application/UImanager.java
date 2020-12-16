package application;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class UImanager {
	
	public static final int MOVE = Main.MOVE;
	public static final int SIZE = Main.SIZE;
	public static int XMAX = Main.XMAX;
	public static int YMAX = Main.YMAX;
	public static int[][] MESH = Main.MESH;
	
	public static String textStyle = "-fx-font: 20 roboto ;";
	
	public void gameMenu(Pane group) {
		gameScreen(group);
	}
	
	public void gameScreen(Pane group) {
				//ui
		
				/*javafx.scene.control.Button playbtn;
			    playbtn = new javafx.scene.control.Button("play");
			    group.getChildren().add(playbtn);
			    
			    //playbtn.setScaleY(YMAX/2);
			    //playbtn.setScaleX((XMAX+150)/2);
			    
			    playbtn.setLayoutX((XMAX+150)/2);
			    playbtn.setLayoutY(YMAX/2);*/
			    
				//line between well and UI
				//Canvas canvas = new Canvas(XMAX + 150, YMAX);
				//canvas.setStyle("-fx-background-color: skyblue");
			    //group.getChildren().add(canvas);
			    group.setStyle("-fx-background-color: skyblue"); 
			    
			    for (int i = 0; i <= XMAX;i=i+SIZE) {
					for (int j = 0; j <= YMAX;j=j+SIZE) {
						Line grid = new Line(i,j,i,j);
						group.getChildren().add(grid);
					}
				}
			    
				Line line = new Line(XMAX+2, 0, XMAX+2, YMAX);
				
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
	}
	
	public void drawMaesh(Pane group) {
		for (int i = 0; i < MESH[0].length; i++) {
			for (int j = 0; j < MESH.length; j++) {
				String g = String.valueOf(MESH[j][i]);
				Text grid = new Text(g);
				grid.setY(i*SIZE+(SIZE/2));
				grid.setX(j*SIZE+(SIZE/2));
				group.getChildren().addAll(grid);
			}
		}
	}
}
