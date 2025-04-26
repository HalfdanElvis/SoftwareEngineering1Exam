package dtu.example;

import java.util.Stack;
/*
    This is the minimal implementation that passes the current tests.
    Running the code coverage tool will confirm 100% branch coverage
    for the RpnCalculator.

    Additional tests are required to handle more complex scenarios.
    For instance, given the following scenario
        Scenario: Error occurs when there are not enough operands on the stack
            Given the stack has no operands
            When I press plus
            Then an error should occur saying "Not enough operands for operation"
    This scenario would necessitate implementing the corresponding validation
    in the "plus" operation.
 */
public class RpnCalculator {
    private final Stack<Double> stack = new Stack<>();

    public void input(double number) {
        stack.push(number);
    }

    public void plus() {
        double operand2 = stack.pop();
        double operand1 = stack.pop();
        stack.push(operand1 + operand2);
    }
    public double getResult() {
        return stack.peek();
    }
}