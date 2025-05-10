package dtu.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dtu.example.Controller.App;
import dtu.example.Model.DateServer;

public class MockYearHolder {
    DateServer dateServer = mock(DateServer.class);

    public MockYearHolder(App app) {
        app.setDateServer(dateServer);
    }

    public void setYear(int year) {
        when(this.dateServer.getYear()).thenReturn(year);
    }
}
