package dtu.example;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import dtu.example.dto.ProjectInfo;
import dtu.example.Model.WorkData;
import dtu.example.Utility.CalendarHelper;
import dtu.example.dto.ActivityInfo;

public class ProjectTestHelper {

    public static boolean projectExists(List<ProjectInfo> projects, int id, String name) {
        return projects.stream().anyMatch(p -> p.getID() == id && p.getName().equals(name));
    }

    public static boolean projectContainsActivity(ProjectInfo project, String activity) {
        return project.getActivities().stream().anyMatch(a -> a.getName().equals(activity));
    }

    public static float getActivityExpectedHours(ProjectInfo project, String activityName) {
        ActivityInfo activity = project.getActivities().stream().filter(a -> a.getName().equals(activityName)).findAny().orElse(null);
        return activity.getExpectedHours();
    }

    public float getUserLoggedHoursOnDate(ProjectInfo project, String activityName, String username, String dateAsString) throws ParseException {
        Calendar date = CalendarHelper.parseStringAsCalendar(dateAsString);
        ActivityInfo activity = project.getActivities().stream().filter(a -> a.getName().equals(activityName)).findAny().orElse(null);
        for (WorkData workData : activity.getWorkDataList()) {
            if (workData.getEmployee().equals(username) && workData.getDate().equals(date)) {
                return workData.getHours();
            }
        }
        return 0;
    }

    public float getUserTotalLoggedHours(ProjectInfo project, String activityName, String username) {
        ActivityInfo activity = project.getActivities().stream().filter(a -> a.getName().equals(activityName)).findAny().orElse(null);
        float sum = 0;
        for (WorkData workData : activity.getWorkDataList()) {
            if (workData.getEmployee().equals(username)) {
                sum += workData.getHours();
            }
        }
        return sum;
    }
}
