package application;

import javafx.scene.control.Button; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 

public class UImanager {
	
	public static final int MOVE = Main.MOVE;
	public static final int SIZE = Main.SIZE;
	public static int XMAX = Main.XMAX;
	public static int YMAX = Main.YMAX;
	public static int[][] MESH = Main.MESH;
	
	public static String textStyle = "-fx-font: 20 roboto ;";
	
	public void gameMenu(Pane group) throws FileNotFoundException {
        Button b = new Button("button");
        
        Image image = new Image(new FileInputStream("C:\\Users\\Sidhant Karbotkar\\eclipse-workspace\\tetrisOOP\\src\\application\\logo.png"));
        
        ImageView imageview = new ImageView(image);
        
        imageview.setX(80); 
        imageview.setY(80);
         
        imageview.setFitWidth(YMAX/2);
        
        imageview.setPreserveRatio(true);
        
        b.setLayoutX((XMAX+100)/2);
        b.setLayoutY(YMAX/2);
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	Main.game_over=false;
            	Main.generate_tetromino();
            	group.getChildren().removeAll(b,imageview);
            	gameScreen(group);
            } 
        }; 
        b.setOnAction(event);
        group.getChildren().addAll(b,imageview);
	}
	
	public void gameScreen(Pane group) {
			    group.setStyle("-fx-background-color: lightgray"); 
			   
			    for (int i = 0; i <= YMAX;i=i+SIZE) {
						Line gridY = new Line(0,i,XMAX,i);
						gridY.setStrokeWidth(0.5);
						group.getChildren().add(gridY);
				}
			    for (int i = 0; i <= XMAX;i=i+SIZE) {
			    	Line gridX = new Line(i,0,i,YMAX);
			    	gridX.setStrokeWidth(0.5);
			    	group.getChildren().add(gridX);
			    }
			    
				//Line line = new Line(XMAX, 0, XMAX, YMAX);
				//Line line = new Line(SIZE, 0, SIZE, YMAX);
				
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
				
				group.getChildren().addAll(scoretext, level);
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
