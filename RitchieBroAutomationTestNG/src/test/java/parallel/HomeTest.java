package parallel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Collection;
import com.factory.DriverFactory;
import com.pages.HomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeTest {
	
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private static String actualTitle = null;
	@Given("user navigats to rbAuction")
	public void user_navigats_to_rb_auction() {
		DriverFactory.getDriver().get("https://www.rbauction.com/");
	}

	@When("user gets the title")
	public void user_gets_the_title() {
		actualTitle = homePage.getTitleOfRBAuction();
		System.out.println(actualTitle);
	}
	

	@Then("title of the page should be {string}")
	public void title_of_the_page_should_be(String expectedTitle) {
	   assertThat(actualTitle, is(expectedTitle));
	}
	
	
	@When("clicks on {string}")
	public void clicks_on(String menuOption) {
	    homePage.clickOnMenuOption(menuOption);
	}

	
	@Then("subcategories should be displayed")
	public void subcategories_should_be_displayed(DataTable menuTable) {
	   Collection<String> actualAllMenuItems = homePage.getListOfActualSubMenuItems();
	   Collection<String> expectedSubBrowseByCategoryList = menuTable.asList();
	   assertThat(expectedSubBrowseByCategoryList, everyItem(isIn(actualAllMenuItems)));
	}


	
}
