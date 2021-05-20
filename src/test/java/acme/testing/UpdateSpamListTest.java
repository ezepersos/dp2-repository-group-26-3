package acme.testing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.framework.testing.AbstractTest;

public class UpdateSpamListTest extends AbstractTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);

		this.signIn("administrator", "administrator");
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Populate DB (initial)"));
	}

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/UpdateSpamList/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdate(final String threshold, final String lista) {
		this.UpdateSpamWordList(threshold, lista);
	super.click(By.linkText("Administrator"));
	super.click(By.linkText("Spam filter"));

	
		assert super.exists(By.id("lista"));
	}

	// Ancillary methods ------------------------------------------------------

	protected void signIn(final String username, final String password) {
		super.navigateHome();
		super.click(By.linkText("Sign in"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.click(By.id("remember$proxy"));
		super.submit(By.className("btn-primary"));
	}

	protected void signOut() {
		super.navigateHome();
		super.submit(By.linkText("Sign out"));
	}
	protected void UpdateSpamWordList(final String threshold, final String lista) {
		super.navigateHome();
		super.click(By.linkText("Administrator"));
		super.click(By.linkText("Spam filter"));
		super.fill(By.id("threshold"), threshold);
		super.fill(By.id("lista"), lista);
	
		super.submit(By.className("btn-primary"));
	}
	

	
	
}
