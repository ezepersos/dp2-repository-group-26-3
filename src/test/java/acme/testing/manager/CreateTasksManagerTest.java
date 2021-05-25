package acme.testing.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeTest;

public class CreateTasksManagerTest extends AcmeTest {





	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (samples)");
		this.signOut();
		
	}
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/createTasksManager/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCreateTasks(final String title, final String executionPeriodInit, final String executionPeriodEnd,
		final String description, final String optionalLink, final int iter) {
		this.signIn("manager", "manager");
		super.clickAndGo(By.linkText("Manager"));
		super.clickAndGo(By.linkText("Create Task"));
		super.fill(By.id("title"), title);
		super.fill(By.id("executionPeriodInit"), executionPeriodInit);
		super.fill(By.id("executionPeriodEnd"), executionPeriodEnd);
		super.fill(By.id("description"), description);
		super.fill(By.id("optionalLink"), optionalLink);
		super.clickOnSubmitButton("Create task");
		
	
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/createTaskManager/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeCreateTasks(final String title, final String executionPeriodInit, final String executionPeriodEnd,
		final String description, final String optionalLink, final int iter) {
		this.signIn("manager", "manager");
		super.clickAndGo(By.linkText("Manager"));
		super.clickAndGo(By.linkText("Create Task"));
		super.fill(By.id("title"), title);
		super.fill(By.id("executionPeriodInit"), executionPeriodInit);
		super.fill(By.id("executionPeriodEnd"), executionPeriodEnd);
		super.fill(By.id("description"), description);
		super.fill(By.id("optionalLink"), optionalLink);
		super.clickOnSubmitButton("Create task");
		super.checkErrorsExist();
	
		
		
	}
		
		
		
	

	// Ancillary methods ------------------------------------------------------

	
	protected void signIn(final String username, final String password) {
		super.navigateHome();
		super.clickAndGo(By.linkText("Sign in"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.clickAndGo(By.id("remember$proxy"));
		super.clickOnSubmitButton("Sign in");
		
	}

	protected void signOut() {
		super.navigateHome();
		super.clickAndGo(By.linkText("Sign out"));
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email) {
		super.navigateHome();
		super.clickAndGo(By.linkText("Sign in"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.fill(By.id("confirmation"), password);
		super.fill(By.id("identity.name"), name);
		super.fill(By.id("identity.surname"), surname);
		super.fill(By.id("identity.email"), email);
		super.clickAndGo(By.id("accept$proxy"));
		super.clickOnSubmitButton("Sign up");
	}

}

