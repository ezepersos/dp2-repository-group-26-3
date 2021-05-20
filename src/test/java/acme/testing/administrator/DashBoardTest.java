
package acme.testing.administrator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.testing.AcmeTest;

public class DashBoardTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);
		this.signIn("administrator", "administrator");
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Populate DB (initial)"));

		this.signOut();


		//Add new user
		//Create 3 task with this user

		//Iniciar como admin
		//Ver el dashboard como admin

		//Registrarse como usuario 

		//Añadir una task como ese usuario

		//Iniciar como admin y ver el dashboard

	}

	// Test cases -------------------------------------------------------------

	/**
	 * 
	 * Caso positivo:
	 * En el que el dashboard está vacío al no haber tasks.
	 */
	
	@Test
	@Order(10)
	protected void assertInitialConditions() {
		this.signIn("administrator", "administrator");
		super.clickAndGo(By.linkText("Administrator"));
		super.clickAndGo(By.linkText("Dashboard"));
		assert super.driver.findElement(By.cssSelector("tr:nth-child(1) > td")).getText().equals("0");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(2) > td")).getText().equals("0");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(3) > td")).getText().equals("0");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(4) > td")).getText().equals("0");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(5) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(6) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(7) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(8) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(9) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(10) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(11) > td")).getText().equals("0.00");
		assert super.driver.findElement(By.cssSelector("tr:nth-child(12) > td")).getText().equals("0.00");
		
	}
	
	
	/**
	 * 
	 * Caso Negativo:
	 * 
	 * Si es usuario anonimo no debería ver el dashboard
	 * 
	 */
	
	@Test
	@Order(20)
	protected void assertDashboardForAnonymousIsForbidden() {
		super.navigateHome();
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/dashboard/show");
		super.checkPanicExists();
		
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

	

}
