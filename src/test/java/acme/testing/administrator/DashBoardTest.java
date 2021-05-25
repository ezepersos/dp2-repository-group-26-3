
package acme.testing.administrator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeTest;

public class DashBoardTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.autoPausing = false;
		super.beforeAll();
		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);
		this.signIn("administrator", "administrator");
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Populate DB (initial)"));

		this.signOut();
	}

	// Test cases -------------------------------------------------------------

	/**
	 * 
	 * Caso positivo:
	 * En el que el dashboard está vacío al no haber tasks.
	 * Se comprueban las funcionalidades del service del dashboard en caso de que esté vacío
	 */

	@Test
	@Order(10)
	protected void assertInitialConditions() {
		this.signIn("administrator", "administrator");
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Dashboard"));
		Assertions.assertEquals("0", super.locateOne(By.cssSelector("tr:nth-child(1) > td")).getText());
		Assertions.assertEquals("0", super.locateOne(By.cssSelector("tr:nth-child(2) > td")).getText());
		Assertions.assertEquals("0", super.locateOne(By.cssSelector("tr:nth-child(3) > td")).getText());
		Assertions.assertEquals("0", super.locateOne(By.cssSelector("tr:nth-child(4) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(5) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(6) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(7) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(8) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(9) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(10) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(11) > td")).getText());
		Assertions.assertEquals("0.00", super.locateOne(By.cssSelector("tr:nth-child(12) > td")).getText());

	}

	/**
	 * 
	 * Caso positivo:
	 * En el que el dashboard está completo y con datos al contener tareas
	 * Se comprueban que las funcionalidades que hay en el service devuelvan el 
	 * número correcto.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/dashboard/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveDashBoardList(final String totalNumberOfPublicTasks, final String totalNumberOfPrivateTasks, final String totalNumberOfFinishedTasks, final String totalNumberOfNonFinishedTasks, final String AverageTaskExecutionPeriods,
		final String DeviationTaskExecutionPeriods, final String MinimumTaskExecutionPeriods, final String MaximumTaskExecutionPeriods, final String MaximumTaskWorkloads, final String MinimumTaskWorkload, final String DeviationTaskWorkloads,
		final String AverageTaskWorkloads) {
		this.signIn("administrator", "administrator");
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Populate DB (samples)"));
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Dashboard"));
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(1) > td")).getText(), totalNumberOfPublicTasks);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(2) > td")).getText(), totalNumberOfPrivateTasks);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(3) > td")).getText(), totalNumberOfFinishedTasks);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(4) > td")).getText(), totalNumberOfNonFinishedTasks);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(5) > td")).getText(), AverageTaskExecutionPeriods);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(6) > td")).getText(), DeviationTaskExecutionPeriods);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(7) > td")).getText(), MinimumTaskExecutionPeriods);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(8) > td")).getText(), MaximumTaskExecutionPeriods);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(9) > td")).getText(), MaximumTaskWorkloads);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(10) > td")).getText(), MinimumTaskWorkload);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(11) > td")).getText(), DeviationTaskWorkloads);
		Assertions.assertEquals(super.locateOne(By.cssSelector("tr:nth-child(12) > td")).getText(), AverageTaskWorkloads);
	}

	/**
	 * 
	 * Caso Negativo:
	 * 
	 * El usuario manager no debería ver el dashboard
	 * 
	 */

	@Test
	@Order(30)
	protected void assertDashboardForManagerIsForbidden() {
		super.navigateHome();
		this.signIn("manager", "manager");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkErrorsExist();

	}

	/**
	 * 
	 * Caso Negativo:
	 * 
	 * El usuario anonimo no debería ver el dashboard
	 * 
	 */

	@Test
	@Order(30)
	protected void assertDashboardForAnonymousIsForbidden() {
		super.navigateHome();
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkErrorsExist();

	}

		/**
	 * 
	 * Caso Negativo:
	 * 
	 * El usuario autenticado no debería ver el dashboard
	 * 
	 */

	@Test
	@Order(30)
	protected void assertDashboardForAuthenticatedIsForbidden() {
		super.navigateHome();
		this.signUp("username", "password", "name", "surname", "email@example.com");
		this.signIn("username", "password");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
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
		super.clickAndGo(By.linkText("Sign up"));
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
