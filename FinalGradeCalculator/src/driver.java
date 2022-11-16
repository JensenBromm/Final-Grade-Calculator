import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class driver  extends Application{

    public ArrayList<TextField> weightList= new ArrayList<TextField>();
    public ArrayList<TextField> gradeList=new ArrayList<TextField>();
    
    public double pointsOverFinalGrade;
    public double individualFinalGrade;
    public double listFinalGrade;

    public TextField finalGradField;

    public int inputMethod; //1 for list, 2 for individual, 3 for points over total

    @Override
    public void start(Stage primaryStage) 
    {
        //Set title of stage
        primaryStage.setTitle("Final Grade Calculator");

        //Create Labels
        Label title=new Label("Final Grade Calculator");
        title.setTextFill(Color.WHITE);
        title.setFont(new Font("Arial", 30));
        Label left=new Label("Percentage Weight");
        Label right=new Label("Grades");
        left.setTextFill(Color.WHITE);
        right.setTextFill(Color.WHITE);
        

        //Create text area to input grades
        TextField weightText=new TextField("Percentage weight: ");
        weightList.add(weightText);
        
        TextField gradeText=new TextField("Grades: ");
        gradeList.add(gradeText);

        //Create different input buttons
        Button gradeListButton=new Button("List of Grades");
            gradeListButton.setStyle("-fx-background-color: #ffef99;");  
        Button pointsOver=new Button("Points earned / Total");
            pointsOver.setStyle("-fx-background-color: #ffef99;");
        Button individual=new Button("Individual Grades");
            individual.setStyle("-fx-background-color: #ffef99;");
      
        //Create button to add a new row for inputs
        Button newRow=new Button("Create New Row");
            newRow.setStyle("-fx-background-color: #ffef99;");

        //Create button to calculate final grades
        Button finalCalc=new Button("Calculate Grade");
            finalCalc.setStyle("-fx-background-color: #ffef99;");

        //Create go Back Button
        Button goBack=new Button("Go Back");
            goBack.setTextFill(Color.WHITE);
            goBack.setStyle("-fx-background-color: #4169e1;");
        //Create exit button
        Button exit=new Button("EXIT");
            //Set text color and background color
            exit.setTextFill(Color.WHITE);
            exit.setStyle("-fx-background-color: #ff0000;");
            //When Button clicked exit the program
            exit.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
            
        }));

        //Vertical box to hold input buttons
            HBox hInput=new HBox(50);
            hInput.getChildren().addAll(pointsOver,individual,gradeListButton);
            hInput.setAlignment(Pos.CENTER);

            VBox vertInput=new VBox(100);
            vertInput.getChildren().addAll(title,hInput);
            vertInput.setAlignment(Pos.CENTER);

        //Add buttons to box
            //Box to hold labels and text fields
            VBox weightBox=new VBox(50);
            weightBox.getChildren().addAll(left,weightText);
            weightBox.setAlignment(Pos.CENTER);

            VBox gradeBox=new VBox(50);
            gradeBox.getChildren().addAll(right,gradeText);
            gradeBox.setAlignment(Pos.CENTER);

            HBox textBox=new HBox(100);
            textBox.getChildren().addAll(weightBox,gradeBox);
            textBox.setAlignment(Pos.CENTER);

            HBox horzBox2=new HBox(100);
            horzBox2.getChildren().addAll(newRow,finalCalc);
            horzBox2.setAlignment(Pos.CENTER); //Make the buttons align in the center of the screen

            HBox exitRow=new HBox(50);
            exitRow.getChildren().addAll(goBack,exit);
            exitRow.setAlignment(Pos.CENTER);//Make the buttons align in the center of the screen

        //Add horizontal boxes to vertical box
            VBox textVBox=new VBox(50);
            textVBox.getChildren().addAll(textBox);
            textVBox.setAlignment(Pos.CENTER);

            VBox vertBox1=new VBox(50);
            vertBox1.getChildren().addAll(horzBox2,exitRow);
            vertBox1.setAlignment(Pos.BOTTOM_CENTER);; //Make the buttons appear at the bottom of the screen in the center


        //Add a new textfield when button is clicked
        newRow.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                TextField weightNew;
                TextField gradeNew;
                if(inputMethod==3)
                {
                    
                    weightNew=new TextField("Points Earned: ");
                    weightList.add(weightNew);
        
                    //Create new row for grade inputs
                    gradeNew=new TextField("Total Points: ");
                    gradeList.add(gradeNew);
                }
                else if(inputMethod==2)
                {
                    
                    weightNew=new TextField("Percentage weight: ");
                    weightList.add(weightNew);
        
                    //Create new row for grade inputs
                    gradeNew=new TextField("Grade: ");
                    gradeList.add(gradeNew);
                }
                //Create new row for weighted percentage
                else 
                {
                    
                   weightNew=new TextField("Percentage weight: ");
                    weightList.add(weightNew);
        
                    //Create new row for grade inputs
                    gradeNew=new TextField("Grades: ");
                    gradeList.add(gradeNew);
                }
                

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
               if(inputMethod==1) //List of grades
               {
                    listFinalGrade=0;
                    String txt;
                    double grade=0;
                    double sum=0;
                    double avg=0;
                    int count=0;

                    //go through list of weights and get the percentage and list
                    for(int i=0;i<weightList.size();i++)// Weight list and Grade List are the same length
                    {
                        //Get weight from WeightList
                        txt=weightList.get(i).getText();
                        txt=txt.substring(txt.indexOf(":")+1); //Gets rid of percentage weight text at start
                        txt=txt.trim(); //precaution to get rid of any empty space
                        double weight=Double.parseDouble(txt);
                        weight /=100; //Gets decimal value for weight

                        //Go through grade list
                        txt=gradeList.get(i).getText();
                        txt=txt.substring(txt.indexOf(":")+1); //get rid of "Grades: "
                        txt=txt.trim(); //precaution to get rid of empty spaces

                       
                        count=0;
                        sum=0;
                        avg=0;
                        String parse;
                        while(txt.indexOf(" ")!=-1)
                        {
                            int index= txt.indexOf(" ");
                            parse=txt.substring(0, index);
                            parse=parse.trim();
                            grade=Double.parseDouble(parse);
                            sum+=grade;
                            count++;
                            txt=txt.substring(index).trim();
                        }
                        grade=Double.parseDouble(txt);
                        sum+=grade;
                        count++;

                        //Calculate average
                        avg=sum/count;

                        listFinalGrade+=avg*weight;
                    }

                    if(listFinalGrade>100)
                    {
                        listFinalGrade=100;
                    }
                    else if (listFinalGrade<0)
                    {
                        listFinalGrade=0;
                    }
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    finalGradField=new TextField("Your Final Grade is: "+numberFormat.format(listFinalGrade));
                    HBox newBox=new HBox(100);
                    newBox.getChildren().add(finalGradField);
                    newBox.setAlignment(Pos.CENTER);
                    textVBox.getChildren().add(newBox);

               }
               else if (inputMethod==2)
               {
                    individualFinalGrade=0;
                    String txt;
                    double grade=0;
                    //go through list of weights and get the percentage and individual grades
                    for(int i=0;i<weightList.size();i++)// Weight list and Grade List are the same length
                    {
                        txt=weightList.get(i).getText();
                        txt=txt.substring(txt.indexOf(":")+1); //Gets rid of percentage weight text at start
                        txt=txt.trim(); //precaution to get rid of any empty space
                        System.out.println("Weight is "+txt);
                        double weight=Double.parseDouble(txt);
                        weight /=100; //Gets decimal value for weight

                        txt=gradeList.get(i).getText();
                        txt=txt.substring(txt.indexOf(":")+1);
                        txt=txt.trim(); //Gets the grade from textfield
                        System.out.println("Grade is "+txt);

                        grade=Double.parseDouble(txt); //changes string to double
                        //System.out.println(grade);
                        System.out.println("Before "+individualFinalGrade);
                        individualFinalGrade += grade*weight; //Add weighted grade to final grade
                        System.out.println("After "+individualFinalGrade);
                    }


                    
                    if(individualFinalGrade>100)
                    {
                        individualFinalGrade=100;
                    }
                    else if (individualFinalGrade<0)
                    {
                        individualFinalGrade=0;
                    }
                    
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    finalGradField=new TextField("Your Final Grade is: "+numberFormat.format(individualFinalGrade));
                    HBox newBox=new HBox(100);
                    newBox.getChildren().add(finalGradField);
                    newBox.setAlignment(Pos.CENTER);
                    textVBox.getChildren().add(newBox);
               }
               else if(inputMethod==3) //Points over total
               {    
                    pointsOverFinalGrade=0;
                    String txt;
                    double totalPoints=0;
                    double pointsEarned=0; 
                       //go through list of weights and get the percentage and fraction
                    for(int i=0;i<weightList.size();i++)// Weight list and Grade List are the same length
                    {
                        txt=weightList.get(i).getText();
                        txt=txt.substring(txt.indexOf(":")+1); //Gets rid of percentage weight text at start
                        //System.out.println(txt);
                        txt=txt.trim(); //precaution to get rid of any empty space
                        double tempEarned=Double.parseDouble(txt);
                        pointsEarned+=tempEarned;

                        txt=gradeList.get(i).getText();
                        txt=txt.substring(txt.indexOf(":")+1); //Gets rid of percentage weight text at start
                        //System.out.println(txt);
                        txt=txt.trim(); //precaution to get rid of any empty space
                        double tempTotal=Double.parseDouble(txt);
                        totalPoints+=tempTotal;
                    }

                    pointsOverFinalGrade=(pointsEarned/totalPoints)*100;

                    if(pointsOverFinalGrade>100)
                    {
                        pointsOverFinalGrade=100;
                    }
                    else if (pointsOverFinalGrade<0)
                    {
                        pointsOverFinalGrade=0;
                    }
                    DecimalFormat numberFormat = new DecimalFormat("#.00");
                    finalGradField=new TextField("Your Final Grade is: "+numberFormat.format(pointsOverFinalGrade));
                    HBox newBox=new HBox(100);
                    newBox.getChildren().add(finalGradField);
                    newBox.setAlignment(Pos.CENTER);
                    textVBox.getChildren().add(newBox);
               }
            }
        }));


        VBox bigBox=new VBox(50);
        bigBox.getChildren().addAll(textVBox,vertBox1);
        bigBox.setAlignment(Pos.CENTER);
       
        

        HBox finalBox=new HBox(50);
        finalBox.getChildren().addAll(bigBox);
        finalBox.setAlignment(Pos.CENTER);
   
        //Set Layout

        StackPane layout=new StackPane();
        layout.getChildren().addAll(finalBox);
        ScrollPane s=new ScrollPane(layout);
        s.setStyle("-fx-background: #031632; -fx-border-color: #000000;");
        s.setFitToWidth(true);
        s.setFitToHeight(true);

        StackPane inputLayout=new StackPane();
        inputLayout.getChildren().addAll(vertInput);
        inputLayout.setStyle("-fx-background: #031632; -fx-border-color: #000000;");

        //Create scene
        Scene inputScene=new Scene(inputLayout,1920,1080); //Scene to get input type
        Scene scene=new Scene(s,1920,1080); //Scene to enter grades

         //Set scene when buttons  are clicked
         gradeListButton.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                newRow.setText("Create New Row");
                inputMethod=1;
                right.setText("Grades (Seperate by spaces)");
                primaryStage.setScene(scene);
            }
            
        }));
        individual.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                newRow.setText("Create New Grade");
                inputMethod=2;
                gradeText.setText("Grade: ");
                primaryStage.setScene(scene);
            }
            
        })); 
        pointsOver.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                newRow.setText("Create New Row");
                left.setText("Points Earned");
                right.setText("Total Points Avalible");
                weightText.setText("Points Earned: ");
                gradeText.setText("Total Points: ");
                inputMethod=3;
                primaryStage.setScene(scene);
            }
            
        }));

        goBack.setOnMouseClicked((new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                textVBox.getChildren().remove(1, textVBox.getChildren().size());
                left.setText("Percentage Weight");
                right.setText("Grades");
                weightList.get(0).setText("Percentage Weight: ");
                gradeList.get(0).setText("Grades: ");
                for(int i=1;i<weightList.size();i++)
                {
                    weightList.remove(i);
                    gradeList.remove(i);
                }
                pointsOverFinalGrade=0;
                individualFinalGrade=0;
                listFinalGrade=0;
                primaryStage.setScene(inputScene);

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
