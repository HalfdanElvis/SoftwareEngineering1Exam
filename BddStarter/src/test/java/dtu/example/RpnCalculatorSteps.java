package dtu.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RpnCalculatorSteps {
    private RpnCalculator calculator;

    @Given("the RPN Calculator is initialized")
    public void theRpnCalculatorIsInitialized() {
        calculator = new RpnCalculator();
    }

    @When("I input the number {double}")
    public void iInputTheNumber(Double number) {
        calculator.input(number);
    }

    @When("I press plus")
    public void iPressPlus() {
        calculator.plus();
    }

    @Then("the result should be {double}")
    public void theResultShouldBe(Double expectedResult) {
        assertEquals(expectedResult, calculator.getResult());
    }

    @When("I request the result")
    public void iRequestTheResult() {
        calculator.getResult(); /* Attempt to get the result from the calculator */
    }
}