package dtu.example;
import java.util.List;
import dtu.example.dto.EmployeeInfo;
import dtu.example.dto.ActivityInfo;

public class EmployeeTestHelper {

    public static boolean employeeIsAssignedActivity(EmployeeInfo employee, String activity) {
        return employee.getActivityInfos().stream().anyMatch(a -> a.getName().equals(activity));
    }

    public static boolean employeeExists(List<EmployeeInfo> employees, String username) {
        return employees.stream().anyMatch(e -> e.getName().equals(username));
    }

    public static boolean employeeIsAssignedSpecialActivity(EmployeeInfo employee, String activity) {
        return employee.getSpecialActivities().stream().anyMatch(a -> a.getName().equals(activity));
    }
}
