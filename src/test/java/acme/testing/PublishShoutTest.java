package acme.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

public class PublishShoutTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (initial)");
		this.signOut();
		
	}
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/publishShout/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positivePublishShout(final String author, final String text, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fill(By.id("author"), author);
		super.fill(By.id("text"), text);
		super.fill(By.id("info"), info);
		super.clickOnSubmitButton("Shout!");
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/publishShout/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativePublishShout(final String author, final String text, final String info) {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "Shout!");
		super.fill(By.id("author"), author);
		super.fill(By.id("text"), text);
		super.fill(By.id("info"), info);
		super.clickOnSubmitButton("Shout!");
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

