package dtu.example.Model;

public class SpecialActivity{

    private String name;
    private Week startWeek;
    private Week endWeek;

    public SpecialActivity(String name) {
        this.name = name;
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
        Week sWeek = new Week(startYear, startWeek);
        Week eWeek = new Week(endYear, endWeek);
        if (!eWeek.isGreaterOrEqual(sWeek)) {
            throw new IllegalArgumentException("End week/year must be greater or equal than start week/year");
        }
        this.startWeek = sWeek;
        this.endWeek = eWeek;
    }

}
