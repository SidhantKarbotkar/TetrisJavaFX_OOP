package application;

import javafx.scene.control.Button; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
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
		Button playbtn = new Button("PLAY");
        playbtn.setLayoutX((XMAX+100)/2);
        playbtn.setLayoutY((YMAX/2)+50);
        playbtn.setScaleX(2);
        playbtn.setScaleY(2);
        
        Button leaderb = new Button("Leaderboard");
        leaderb.setLayoutX((XMAX+60)/2);
        leaderb.setLayoutY((YMAX/2)+100);
        
        Text name = new Text("Enter Your Name");
        name.setStyle(textStyle);
		name.setFill(Color.WHITE);
		name.setY((YMAX/2)-10);
		name.setX(XMAX/2);
        
        TextField tname = new TextField();
		tname.setLayoutX((XMAX)/2);
		tname.setLayoutY(YMAX/2);
		
		Text error = new Text("Enter Name");
        error.setStyle("-fx-font: 10 roboto ;");
		error.setFill(Color.RED);
		error.setY((YMAX/2)+15);
		error.setX((XMAX/2)+150);
        
        Image image = new Image(new FileInputStream("C:\\Users\\Sidhant Karbotkar\\eclipse-workspace\\tetrisOOP\\src\\application\\logo.png"));
        ImageView imageview = new ImageView(image);
        imageview.setX(30); 
        imageview.setY(120);
        imageview.setFitWidth(400);
        imageview.setPreserveRatio(true);
        
        Image bg = new Image(new FileInputStream("C:\\Users\\Sidhant Karbotkar\\eclipse-workspace\\tetrisOOP\\src\\application\\background.png"));
        ImageView bgview = new ImageView(bg);
        bgview.setFitWidth(XMAX + 150);
        bgview.setFitHeight(YMAX);
        
        group.setStyle("-fx-background-color: black");
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            	if(!tname.getText().isEmpty()) {
	            	Main.game_over=false;
	            	Main.generate_tetromino();
	            	group.getChildren().removeAll(bgview,imageview,playbtn,leaderb,tname,name,error);
	            	gameScreen(group);
            	}else group.getChildren().add(error);
            }
        };
        playbtn.setOnAction(event);
        
        EventHandler<ActionEvent> eventLeader = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e)
            {
            	Main.generate_tetromino();
            	group.getChildren().removeAll(imageview,playbtn,leaderb,tname,name,error);
            	try {
					showleaderBoard(group);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }
        }; 
        leaderb.setOnAction(eventLeader);
        group.getChildren().addAll(bgview,imageview,playbtn,leaderb,tname,name);
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
	
	public void showleaderBoard(Pane group) throws FileNotFoundException {
		Image lb = new Image(new FileInputStream("C:\\Users\\Sidhant Karbotkar\\eclipse-workspace\\tetrisOOP\\src\\application\\leaderboard.png"));
        ImageView lbview = new ImageView(lb);
        lbview.setFitWidth(XMAX+100);
        lbview.setFitHeight(YMAX-30);
        lbview.setX(30);
        lbview.setY(10);
        group.getChildren().addAll(lbview);        
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
