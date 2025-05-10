package dtu.example;
import java.util.List;
import java.util.stream.Stream;

import dtu.example.DTO.ProjectInfo;
import dtu.example.DTO.ActivityInfo;

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
}
