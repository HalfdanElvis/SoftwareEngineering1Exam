package dtu.example;
import java.util.List;
import dtu.example.DTO.EmployeeInfo;
import dtu.example.DTO.ActivityInfo;

public class EmployeeTestHelper {

    public static boolean employeeIsAssignedActivity(EmployeeInfo employee, String activity) {
        return employee.getActivityInfos().stream().anyMatch(a -> a.getName().equals(activity));
    }

    public static boolean employeeIsAssignedSpecialActivity(EmployeeInfo employee, String activity) {
        return employee.getSpecialActivities().stream().anyMatch(a -> a.getName().equals(activity));
    }
}
