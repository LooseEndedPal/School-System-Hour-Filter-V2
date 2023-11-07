import java.util.ArrayList;

abstract public class People implements Hours {

    private String title, firstName, lastName;
    private ArrayList<String> courses = new ArrayList<String>();
    private int hours = 0;

    public People(String title, String firstName, String lastName, String course){
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses.add(course);
    }

    abstract public int getParticipatingHours(int courseHours);

    public void addHours(int hours){
        this.hours += getParticipatingHours(hours);
    }

    public void addCourse(String course){
        this.courses.add(course);
    }

    public ArrayList<String> getAllCourse(){
        return courses;
    }

    public int getTotalHours(){
        return hours;
    }

    public String getTitle(){
        return title;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

}