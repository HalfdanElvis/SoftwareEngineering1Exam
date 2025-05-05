package dtu.example;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepStarted;

public class StepLogger implements EventListener {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, event -> {
            String stepText = event.getTestStep().toString();
            System.out.println("👉 Running step: " + stepText);
        });
    }
}
