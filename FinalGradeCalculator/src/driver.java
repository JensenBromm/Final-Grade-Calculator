import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class driver  extends Application{

    public ArrayList<TextField> weightList= new ArrayList<TextField>();
    public ArrayList<TextField> gradeList=new ArrayList<TextField>();

    @Override
    public void start(Stage primaryStage) 
    {
        //Set title of stage
        primaryStage.setTitle("Final Grade Calculator");

        //Create Labels
        Label left=new Label("Percentage Weight");
        Label right=new Label("Grades");

        //Create text area to input grades
        TextField weightText=new TextField("Percentage weight: ");
        weightList.add(weightText);
        
        TextField gradeText=new TextField("Grades: ");
        gradeList.add(gradeText);

        //Create different input buttons
        Button gradeListButton=new Button("List of Grades");
        Button pointsOver=new Button("Points earned / Total");
        Button individual=new Button("Individual Grades");
      
        //Create button to add a new row for inputs
        Button newRow=new Button("Create New Row");

        //Create button to calculate final grades
        Button finalCalc=new Button("Calculate Grade");

        //Create exit button
        Button exit=new Button("EXIT");
            //Set text color and background color
            exit.setTextFill(Color.WHITE);
            exit.setStyle("-fx-background-color: #ff0000; ");
            //When Button clicked exit the program
            exit.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
            
        }));

        //Box to hold different buttons for input
            HBox inputSect=new HBox(100);
            inputSect.getChildren().addAll(pointsOver,individual,gradeListButton);
            inputSect.setAlignment(Pos.CENTER);
        //Vertical box to hold input buttons
            VBox vertInput=new VBox(100);
            vertInput.getChildren().addAll(inputSect);
            vertInput.setAlignment(Pos.CENTER);

        //Add buttons to box
            //Box to hold labels
            HBox horzBox1=new HBox(500);
            horzBox1.getChildren().addAll(left,right);
            horzBox1.setAlignment(Pos.CENTER);

            HBox textBox=new HBox(100);
            textBox.getChildren().addAll(weightText,gradeText);
            textBox.setAlignment(Pos.CENTER);

            HBox horzBox2=new HBox(100);
            horzBox2.getChildren().addAll(newRow,finalCalc);
            horzBox2.setAlignment(Pos.CENTER); //Make the buttons align in the center of the screen

            HBox exitRow=new HBox();
            exitRow.getChildren().add(exit);
            exitRow.setAlignment(Pos.CENTER);//Make the buttons align in the center of the screen

        //Add horizontal boxes to vertical box
            VBox textVBox=new VBox(50);
            textVBox.getChildren().addAll(horzBox1,textBox);
            textVBox.setAlignment(Pos.CENTER);

            VBox vertBox1=new VBox(50);
            vertBox1.getChildren().addAll(horzBox2,exitRow);
            vertBox1.setAlignment(Pos.BOTTOM_CENTER);; //Make the buttons appear at the bottom of the screen in the center


        //Add a new textfield when button is clicked
        newRow.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //Create new row for weighted percentage
                TextField weightNew=new TextField("Percentage weight: ");
                weightList.add(weightNew);
    
                //Create new row for grade inputs
                TextField gradeNew=new TextField("Grades: ");
                gradeList.add(gradeNew);

                //Create new horzintal box
                HBox newBox=new HBox(100);
                newBox.getChildren().addAll(weightNew,gradeNew);
                newBox.setAlignment(Pos.CENTER);

                textVBox.getChildren().add(newBox);

            }
        
        }));

        finalCalc.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               
            }
        
        }));

        VBox bigBox=new VBox(50);
        bigBox.getChildren().addAll(textVBox,vertBox1);
        bigBox.setAlignment(Pos.CENTER);
   
        //Set Layout
        StackPane layout=new StackPane();
        layout.getChildren().addAll(bigBox);

         //Create scene
        Scene inputScene=new Scene(vertInput,1920,1080); //Scene to get input type
        Scene scene=new Scene(layout,1920,1080); //Scene to enter grades

         //Set scene when buttons  are clicked
         gradeListButton.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(scene);
            }
            
        }));
        individual.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(scene);
            }
            
        })); 
        pointsOver.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(scene);
            }
            
        }));

        //Pass in original scene to get input type
            primaryStage.setScene(inputScene);

        //display the stage
            primaryStage.show();
    }
 
    public static void main(String[] args) 
    {
        launch(args);
    }


}
