//Imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import java.util.ArrayList;


public class App extends Application{

    //List
    static ArrayList<People> people = new ArrayList<People>();

    @Override
    public void start(Stage stage){
        stage.setTitle("School System Hour Filter V2");
        
        //Combobox
        ComboBox <String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("Professor", "Student", "TA");
        dropdown.setValue("Professor");

        //Grids
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 10, 10, 10));
        grid2.setVgap(5);
        grid2.setHgap(5);

        GridPane primePane = new GridPane();
        primePane.add(grid, 0, 0);
        primePane.add(grid2, 0, 1);

        //Text Fields
        TextField fnameText = new TextField();
        TextField lnameText = new TextField();
        TextField cnameText = new TextField();
        TextField choursText = new TextField();

        //Button
        Button addButton = new Button("Add");

        //Adding to grid
        grid.add(new Label("Status"), 0, 0);
        grid.add(new Label("First Name"), 1, 0);
        grid.add(new Label("Last Name"), 2, 0);
        grid.add(new Label("Course Hours"), 3, 0);
        grid.add(new Label("Course Name "), 4, 0);
        grid.add(dropdown, 0, 1);
        grid.add(fnameText, 1, 1);
        grid.add(lnameText, 2, 1);
        grid.add(choursText, 3, 1);
        grid.add(cnameText, 4, 1);
        grid.add(addButton, 0, 2);

        //Button events
        addButton.setOnMousePressed((MouseEvent) ->{

            try {

                if(filterList(fnameText.getText(), lnameText.getText(), Integer.parseInt(choursText.getText()), dropdown.getValue(), cnameText.getText())){
                    createPerson(dropdown.getValue(), fnameText.getText(), lnameText.getText(), cnameText.getText(), Integer.parseInt(choursText.getText()));
                }
                grid2.getChildren().clear();

                for(int count = 0; count < people.size(); count++){
                    grid2.add(new Label(people.get(count).getTitle()),0, (count + 3));
                    grid2.add(new Label(people.get(count).getFirstName()),1, (count + 3));
                    grid2.add(new Label(people.get(count).getLastName()),2, (count + 3));
                    grid2.add(new Label(Integer.toString(people.get(count).getTotalHours())),3, (count + 3));
                    grid2.add(new Label(stringCourses(count)), 4, (count + 3));
                }
                
            } catch (Exception e) {
                Alert err = new Alert(AlertType.ERROR);
                err.setTitle("Error");
                err.setContentText("Please insert a number for hours");
                err.showAndWait();
                fnameText.setText("");
                lnameText.setText("");
                cnameText.setText("");
                choursText.setText("");
            }
        });

        //Initailizing the scene
        Scene scene = new Scene(primePane, 800, 400);
        stage.setScene(scene);
        stage.show();


    }

    //Filters list and adds hours to existing items
    public static boolean filterList(String fname, String lname, int hours, String profession, String course){
        
        if(people.size() == 0){
            return true;
        }

        for(int count = 0; count < people.size(); count++){
            if(people.get(count).getFirstName().equalsIgnoreCase(fname) && people.get(count).getLastName().equalsIgnoreCase(lname) && people.get(count).getTitle().equalsIgnoreCase(profession)){
                people.get(count).addHours(hours);
                people.get(count).addCourse(course);
                return false;
            }
        }

        return true;
    }

    //Creates person object baed on profession
    public void createPerson(String profession, String fname, String lname, String cname, int hours){
        People person;
        switch (profession) {
            case "Professor":
                person = new Professor(profession, fname, lname, cname);
                break;
            case "TA":
                person = new TA(profession, fname, lname, cname);
                break;
            case "Student":
                person = new Student(profession, fname, lname, cname);
                break;
            default:
                person = null;
        }
        person.addHours(hours);
        people.add(person);
    }

    //Adds all courses together into one string for output
    public static String stringCourses(int index){
        String allCourse = people.get(index).getAllCourse().get(0);

        for(int count = 1; count < people.get(index).getAllCourse().size(); count++){
            if(allCourse.indexOf(people.get(index).getAllCourse().get(count)) < 0){
                allCourse += " " + people.get(index).getAllCourse().get(count);
            }
            else{
                people.get(index).getAllCourse().remove(count);
            }
        }

        return allCourse;
    }

    //Main
    public static void main(String[] args){
        launch(args);
    }
}

