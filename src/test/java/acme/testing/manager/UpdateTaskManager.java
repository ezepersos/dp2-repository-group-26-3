package acme.testing.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeTest;

public class UpdateTaskManager extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);

		this.signIn("administrator", "administrator");	
		super.clickOnMenu("Administrator","Populate DB (samples)");
	
	}

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/UpdateTaskManager/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdateTaskManager(final String title, final String executionPeriodInit, final String executionPeriodEnd,
		final String description, final String optionalLink, final int iter) {
		this.signIn("manager", "manager");
		super.navigateHome();
		super.clickOnMenu("Manager", "My tasks");
		super.clickOnListingRecord(iter);
		super.fill(By.id("title"), title);

		super.clickOnSubmitButton("Update");
		super.checkColumnHasValue(iter, 0, title);
		super.checkColumnHasValue(iter, 1, executionPeriodInit);
		super.checkColumnHasValue(iter, 2, executionPeriodEnd);
		super.checkColumnHasValue(iter, 3, description);
	}
	@ParameterizedTest
    @CsvFileSource(resources = "/UpdateTaskManager/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void negativeUpdate(final String title, final String executionPeriodInit, final String executionPeriodEnd,
		final String description, final String optionalLink, final int iter) {
		this.signIn("manager", "manager");
		super.navigateHome();
		super.clickOnMenu("Manager", "My tasks");
		super.clickOnListingRecord(iter);
		super.fill(By.id("executionPeriodInit"), executionPeriodInit);

		super.clickOnSubmitButton("Update");
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


	

	
	
}
