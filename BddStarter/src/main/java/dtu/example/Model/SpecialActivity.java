package dtu.example.Model;

public class SpecialActivity{

    private String name;
    private Week startWeek;
    private Week endWeek;

    public SpecialActivity(String name, int startYear, int startWeek, int endYear, int endWeek) {
        this.name = name;
        this.startWeek = new Week(startYear, startWeek);
        this.endWeek = new Week(endYear, endWeek);
    }
    
    public String getName() {
        return name;
    }

    public Week getEndWeek() {
        return endWeek;
    }

    public Week getStartWeek() {
        return startWeek;
    }

    public void setStartAndEndWeek(int startYear, int startWeek, int endYear, int endWeek) {
        this.startWeek = new Week(startYear, startWeek);
        this.endWeek = new Week(endYear, endWeek);
    }

}
