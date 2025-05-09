package dtu.example;


/**
 * A test class to check which tests are run with which framework.
 * If run as a JUnit 5 (Jupiter) test, then both tests are run.
 * If run as a JUnit 4 test, then only the JUnit 4 tests are run.
 *
 * Remove the tests in your own projects.
 */
public class TestJUnit4AndJUnit5 {

	
	
	@org.junit.Before // JUnit 4 (JUnit 5 uses @org.junit.jupiter.api.BeforeEach)
	public void setUp() {
		System.out.println("setUp runs before a JUnit 4 tests");
	}

	@org.junit.After // JUnit 4 (JUnit 5 uses @org.junit.jupiter.api.AfterEach)
	public void tearDown() {
		System.out.println("tearDown runs after a JUnit 4 tests");
	}

	@org.junit.Test // JUnit 4
	public void junit4Test() {
		System.out.println("JUnit 4");
		org.junit.Assert.assertTrue(true); // JUnit 4
	}
	
	@org.junit.jupiter.api.Test // Junit 5
	public void junit5Test() {
		System.out.println("JUnit 5");
		org.junit.jupiter.api.Assertions.assertTrue(true); // JUnit 5
	}

	// isWeek() Whitebox tests; OPDATER SÅ YEARS GIVER MENING.
	// A
	@org.junit.Test // JUnit 4
	public void isWeekReturnsTrueOnWeekNumber() {
		//Arrange
		String week = "1";
		//Act
		boolean isWeekResult = App.isWeek(week, 2025);
		//Assert
		org.junit.Assert.assertTrue(isWeekResult);
	}

	// B
	@org.junit.Test // JUnit 4
	public void isWeekFailsOnNumberAbove52() {
		//Arrange
		boolean invalidInput = false;
		String week = "352";
		//Act
		try {
			App.isWeek(week, 2025);
		} catch (IllegalArgumentException e) {
			invalidInput = true;
		}
		
		//Assert
		org.junit.Assert.assertTrue(invalidInput);
	}

	// C
	@org.junit.Test // JUnit 4
	public void isWeekFailsOnNegativeInteger() {
		//Arrange
		boolean invalidInput = false;
		String week = "-1";
		//Act
		try {
			App.isWeek(week, 2025);
		} catch (IllegalArgumentException e) {
			invalidInput = true;
		}
		
		//Assert
		org.junit.Assert.assertTrue(invalidInput);
	}
	
	// D
	@org.junit.Test // JUnit 4
	public void isWeekFailsOnNonInteger() {
		//Arrange
		boolean invalidInput = false;
		String week = "Hello?";
		//Act
		try {
			App.isWeek(week, 2025);
		} catch (IllegalArgumentException e) {
			invalidInput = true;
		}
		//Assert
		org.junit.Assert.assertTrue(invalidInput);
	}

	// isPositiveInt() Whitebox test:
	// A
	@org.junit.Test // JUnit 4
	public void isPositiveIntReturnsTrueOnPositiveInteger() {
		//Arrange
		String positiveInt = "3252";
		//Act
		boolean isPositiveIntResult = App.isPositiveInt(positiveInt);
		//Assert
		org.junit.Assert.assertTrue(isPositiveIntResult);
	}

	// B
	@org.junit.Test // JUnit 4
	public void isPositiveIntFailsOnNotPositiveInteger() {
		//Arrange
		boolean invalidInput = false;
		String notPositiveInt = "0";
		//Act
		try {
			App.isPositiveInt(notPositiveInt);
		} catch (IllegalArgumentException e) {
			invalidInput = true;
		}
		//Assert
		org.junit.Assert.assertTrue(invalidInput);
	}

	// C
	@org.junit.Test // JUnit 4
	public void isPositiveIntFailsOnNonInteger() {
		//Arrange
		boolean invalidInput = false;
		String notPositiveInt = "Hello?";
		//Act
		try {
			App.isPositiveInt(notPositiveInt);
		} catch (IllegalArgumentException e) {
			invalidInput = true;
		}
		//Assert
		org.junit.Assert.assertTrue(invalidInput);
	}

	App app = new App();
	// legalUsername() Whitebox tests:
	// A
	@org.junit.Test // JUnit 4
	public void legalUsernameReturnsTrueOnValidUsername() {
		//Arrange
		String username = "huba";
		//Act
		boolean legalUsernameResult = app.legalUsername(username);
		//Assert
		org.junit.Assert.assertTrue(legalUsernameResult);
	}

	// B
	@org.junit.Test // JUnit 4
	public void legalUsernameFailsOnEmptyString() {
		//Arrange
		boolean invalidUsername = false;
		String username = "";
		//Act
		try {
			app.legalUsername(username);
		} catch (Exception e) {
			invalidUsername = true;
		}
		//Assert
		org.junit.Assert.assertTrue(invalidUsername);
	}

	// C
	@org.junit.Test // JUnit 4
	public void legalUsernameFailsOnStringContainingSpace() {
		//Arrange
		boolean invalidUsername = false;
		String username = "Bo V";
		//Act
		try {
			app.legalUsername(username);
		} catch (Exception e) {
			invalidUsername = true;
		}
		//Assert
		org.junit.Assert.assertTrue(invalidUsername);
	}

	// D
	@org.junit.Test // JUnit 4
	public void legalUsernameFailsOnStringWithMoreThan4Characters() {
		//Arrange
		boolean invalidUsername = false;
		String username = "Jesper Larsen";
		//Act
		try {
			app.legalUsername(username);
		} catch (Exception e) {
			invalidUsername = true;
		}
		//Assert
		org.junit.Assert.assertTrue(invalidUsername);
	}

	// legalUsername() Whitebox tests:
	// A
	@org.junit.Test // JUnit 4
	public void stringToEmployeeReturnsTrueOnExistingEmployee() {
		//Arrange
		String username = "huba";
		Employee e = new Employee(username);
		if (!app.employeeExists(username)) {
			app.addEmployee(username);
		}
		//Act
		Employee e2 = app.stringToEmployee(username);
		//Assert
		org.junit.Assert.assertTrue(e.getUsername().equals(e2.getUsername()));
	}

	// B
	@org.junit.Test // JUnit 4
	public void stringToEmployeeReturnsNullOnNoEmployeeFound() {
		//Arrange
		String username = "huba";
		if (app.employeeExists(username)) {
			app.deleteEmployee(username);
		}
		Employee e2 = null;
		//Act
		
		try {
			e2 = app.stringToEmployee(username);
		} catch (Exception e) {
			 
		}
		//Assert
		org.junit.Assert.assertTrue(e2 == null);
	}
}
