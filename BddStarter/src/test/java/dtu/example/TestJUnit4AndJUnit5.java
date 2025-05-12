package dtu.example;
import java.util.ArrayList;
import java.util.List;

import dtu.example.Controller.App;
import dtu.example.Model.Employee;
import dtu.example.Model.SpecialActivity;
import dtu.example.Model.Week;
import dtu.example.Utility.CalendarHelper;
/**
 * A test class to check which tests are run with which framework.
 * If run as a JUnit 5 (Jupiter) test, then both tests are run.
 * If run as a JUnit 4 test, then only the JUnit 4 tests are run.
 *
 * Remove the tests in your own projects.
 */
public class TestJUnit4AndJUnit5 {

	App app = new App();
	
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
		int year = 2025;
		//Act
		boolean isWeekResult = App.isWeek(week, year);
		//Assert
		org.junit.Assert.assertTrue(isWeekResult);
	}

	// B
	@org.junit.Test // JUnit 4
	public void isWeekFailsOnNumberAbove52() {
		//Arrange
		boolean invalidInput = false;
		String week = "352";
		int year = 2025;
		//Act
		try {
			App.isWeek(week, year);
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
		int year = 2025;
		//Act
		try {
			App.isWeek(week, year);
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
		int year = 2025;
		//Act
		try {
			App.isWeek(week, year);
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

	// range() Whitebox tests:
	// A
	@org.junit.Test // JUnit 4
	public void rangeReturnsWeeksBetweenStartAndEndWeekInDifferentYears() {
		//Arrange
		Week startWeek = new Week(2025, 51);
		Week endWeek = new Week(2026, 2);
		//Act
		List<Week> rangeResult = CalendarHelper.range(startWeek, endWeek);
		//Assert
		List<Week> expectedResult = new ArrayList<>();
		expectedResult.add(new Week(2025, 51));
		expectedResult.add(new Week(2025, 52));
		expectedResult.add(new Week(2026, 1));
		expectedResult.add(new Week(2026, 2));

		org.junit.Assert.assertTrue(TestHelper.areWeekRangesEqual(rangeResult, expectedResult));
	}

	// B
	@org.junit.Test // JUnit 4
	public void rangeReturnsWeeksBetweenStartAndEndWeekInSameYear() {
		//Arrange
		Week startWeek = new Week(2025, 48);
		Week endWeek = new Week(2025, 50);
		//Act
		List<Week> rangeResult = CalendarHelper.range(startWeek, endWeek);
		//Assert
		List<Week> expectedResult = new ArrayList<>();
		expectedResult.add(new Week(2025, 48));
		expectedResult.add(new Week(2025, 49));
		expectedResult.add(new Week(2025, 50));

		org.junit.Assert.assertTrue(TestHelper.areWeekRangesEqual(rangeResult, expectedResult));
	}

	// C
	@org.junit.Test // JUnit 4
	public void rangeReturnsWeeksBetweenStartAndEndWeekAreEqual() {
		//Arrange
		Week startWeek = new Week(2025, 48);
		Week endWeek = startWeek;
		//Act
		List<Week> rangeResult = CalendarHelper.range(startWeek, endWeek);
		//Assert
		List<Week> expectedResult = new ArrayList<>();
		expectedResult.add(new Week(2025, 48));

		org.junit.Assert.assertTrue(TestHelper.areWeekRangesEqual(rangeResult, expectedResult));
	}
	
	// D
	@org.junit.Test // JUnit 4
	public void rangeFailsOnEndWeekEarlierThanStartWeek() {
		//Arrange
		boolean invalidRange = false;
		Week startWeek = new Week(2025, 50);
		Week endWeek = new Week(2025, 48);
		//Act
		try {
			CalendarHelper.range(startWeek, endWeek);
		} catch (Throwable e) {
			invalidRange = true;
		}
		
		//Assert
		org.junit.Assert.assertTrue(invalidRange);
	}

	// setSpecialActivityStartAndEndWeek Whitebox test:
	// A
	@org.junit.Test // JUnit 4
	public void setSpecialActivityStartAndEndWeekSuccess() {
		//Arrange
		SpecialActivity specialActivity = new SpecialActivity("test");
		boolean success = true;
		//Act
		try {
			specialActivity.setStartAndEndWeek(1, 2, 3, 4);
		} catch (Exception e) {
			success = false;
		}
		//Assert
		org.junit.Assert.assertTrue(success);
		org.junit.Assert.assertTrue(specialActivity.getStartWeek().getYear() == 1);
		org.junit.Assert.assertTrue(specialActivity.getStartWeek().getWeek() == 2);
		org.junit.Assert.assertTrue(specialActivity.getEndWeek().getYear() == 3);
		org.junit.Assert.assertTrue(specialActivity.getEndWeek().getWeek() == 4);
	}

	// B
	@org.junit.Test // JUnit 4
	public void setSpecialActivityStartAndEndWeekFailure() {
		//Arrange
		SpecialActivity specialActivity = new SpecialActivity("test");
		boolean failure = false;
		//Act
		try {
			specialActivity.setStartAndEndWeek(4, 3, 2, 1);
		} catch (Exception e) {
			failure = true;
		}
		//Assert
		org.junit.Assert.assertTrue(failure);
		org.junit.Assert.assertTrue(specialActivity.getStartWeek() == null);
		org.junit.Assert.assertTrue(specialActivity.getStartWeek() == null);
		org.junit.Assert.assertTrue(specialActivity.getEndWeek() == null);
		org.junit.Assert.assertTrue(specialActivity.getEndWeek() == null);
	}

	// deleteEmployee Whitebox test:
	// A
	@org.junit.Test // JUnit 4
	public void removeEmployeeFromSystemSuccess() {
		//Arrange
		App app = new App();
		app.addEmployee("test");
		boolean success = true;
		//Act
		try {
			app.deleteEmployee("test");
		} catch (Exception e) {
			success = false;
		}
		//Assert
		org.junit.Assert.assertTrue(success);
		org.junit.Assert.assertFalse(app.employeeExists("test"));

	}

	// B
	@org.junit.Test // JUnit 4
	public void removeEmployeeFromSystemFailure() {
		//Arrange
		App app = new App();
		boolean failure = false;
		//Act
		try {
			app.deleteEmployee("test");
		} catch (Exception e) {
			failure = true;
		}
		//Assert
		org.junit.Assert.assertTrue(failure);
		org.junit.Assert.assertFalse(app.employeeExists("test"));

	}

	// deleteProject Whitebox test:
	// A
	@org.junit.Test // JUnit 4
	public void deleteProjectFromSystemSuccess() {
		//Arrange
		App app = new App();
		int id = app.createProject("test");
		boolean success = true;
		//Act
		try {
			app.deleteProject(id);
		} catch (Exception e) {
			success = false;
		}
		try {
			app.getProjectInfo(id);
			success = false;
		} catch (Exception e) {
			success = true;
		}
		//Assert
		org.junit.Assert.assertTrue(success);
	}

	// B
	@org.junit.Test // JUnit 4
	public void deleteProjectFromSystemFailure() {
		//Arrange
		App app = new App();
		boolean failure = false;
		//Act
		try {
			app.deleteProject(25001);
		} catch (Exception e) {
			failure = true;
		}
		try {
			app.getProjectInfo(25001);
			failure = false;
		} catch (Exception e) {
			failure = true;
		}
		//Assert
		org.junit.Assert.assertTrue(failure);
	}

	// Logout
	@org.junit.Test // JUnit 4
	public void logoutSucceds() {
		//Arrange
		App app = new App();
		app.addEmployee("huba");
		app.setSignedInEmployee("huba");
		boolean loggedOut = false;

		//Act
		app.logout();

		// Assert
		try {
			app.getSignedInEmployeeInfo();
		} catch (Throwable e) {
			loggedOut = true;
		}

		org.junit.Assert.assertTrue(loggedOut);
	}

	// checkEmptyString
	@org.junit.Test // JUnit 4
	public void checkEmptyStringSuccess() {
		//Arrange
		App app = new App();
		boolean success = true;
		//Act
		try {
			app.checkEmptyString("");
			success = false;
		} catch (Exception e) {
			success = true;
		}
		//Assert
		org.junit.Assert.assertTrue(success);
	}

}
