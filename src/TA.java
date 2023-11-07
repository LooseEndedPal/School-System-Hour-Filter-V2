public class TA extends People {

    public TA(String title, String firstname, String lastname, String course){
        super(title, firstname, lastname, course);
    }


    @Override
    public int getParticipatingHours(int courseHours){
        int labHours;

        if(courseHours > 4){
            labHours = 2;
        }
        else{
            labHours = 1;
        }
        return labHours;
    }
}