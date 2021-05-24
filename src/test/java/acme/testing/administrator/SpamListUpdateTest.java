package acme.testing.administrator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeTest;

public class SpamListUpdateTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8080", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(true);

		this.signIn("administrator", "administrator");	
		super.clickOnMenu("Administrator","Populate DB (initial)");
	
	}

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/UpdateSpamList/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveUpdate(final String threshold, final String lista) {
		this.signIn("administrator", "administrator");

		this.UpdateSpamWordList(threshold, lista);
		super.clickOnMenu("Administrator", "Spam filter");


	
		assert super.exists(By.id("lista"));
	}
	@ParameterizedTest
    @CsvFileSource(resources = "/UpdateSpamList/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void negativeUpdate(final String threshold, final String lista) {
		this.signIn("administrator", "administrator");
		this.UpdateSpamWordList(threshold, lista);
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
		super.clickOnSubmitButton("Sign out");
	}
	protected void UpdateSpamWordList(final String threshold, final String lista) {

		super.navigateHome();
		super.clickOnMenu("Administrator", "Spam filter");
		super.fill(By.id("threshold"), threshold);
		super.fill(By.id("lista"), lista);
	
		super.clickOnSubmitButton("update");
	}
	
	

	
	
}