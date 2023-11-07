public class Professor extends People {

    public Professor(String title, String firstname, String lastname, String course){
        super(title, firstname, lastname, course);
    }


    @Override
    public int getParticipatingHours(int courseHours){
        int lectureHours;

        if(courseHours > 4){
            lectureHours = courseHours - 2;
        }
        else{
            lectureHours = courseHours - 1;
        }

        return lectureHours;
    }
}