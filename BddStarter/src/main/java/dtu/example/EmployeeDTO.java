package dtu.example;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private String username;
    private boolean peak = false;
    private List<ActivityDTO> activityDTOs = new ArrayList<>();

    public EmployeeDTO(String username, boolean peak, List<ActivityDTO> activityDTOs){
        this.username = username;
        this.peak = peak; 
        this.activityDTOs = activityDTOs;
    }

}
