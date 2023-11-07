public class Student extends People {

    public Student(String title, String firstname, String lastname, String course){
        super(title, firstname, lastname, course);
    }


    @Override
    public int getParticipatingHours(int courseHours){
        return courseHours;
    }
}