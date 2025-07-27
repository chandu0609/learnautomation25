package parallel;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.comparesEqualTo;

import com.factory.DriverFactory;
import com.pages.HomePage;
import com.pages.SearchResultPage;
import com.util.SharedContainer;
import com.util.Util;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchResultsTest {
	
   
    private SharedContainer shareContent;
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private SearchResultPage searchPage;
    
    public SearchResultsTest(SharedContainer shareContent) {
    	this.shareContent = shareContent;
    }

    @Given("user has navigated to rbAuction")
	public void user_has_navigated_to_rb_auction() {
	  DriverFactory.getDriver().get("https://www.rbauction.com");
	}
    
    @When("User Searches for {string}")
	public void user_searches_for(String equipmentName) {
	    searchPage = homePage.searchEquipment(equipmentName);
	}
    
	@Then("Capture the number of results")
	public void capture_the_number_of_results() {
		String resultsCountString = searchPage.getResultsCount();
		Integer resultCount = Util.extractIntegerFromString(resultsCountString);
		this.shareContent.sharedValues.put("FordF150AllCount", resultCount);
	}

	@Then("Verifies that the first result on the page has the word {string} in it")
	public void verifies_that_the_first_result_on_the_page_has_the_word_in_it(String searchedEquipment) {
		String firstResultFullValue = searchPage.getFirstResultItem();
		assertThat(firstResultFullValue, 
				anyOf(containsString(searchedEquipment), 
						containsString("Ford F150"))); //Added "Ford F150" assertion because sometimes it bring this as result on top
	}


	@When("User Applies the “Year” filter to be from “{int}” to current year")
	public void user_applies_the_year_filter_to_be_from_to_current_year(Integer fromYear) throws InterruptedException {
		searchPage.setMinYear(fromYear.toString());
		Integer currentYr = Util.getCurrentYear();
		searchPage.setMaxYear(currentYr.toString());
	}

	@Then("Verifies the number of results returned is different")
	public void verifies_the_number_of_results_returned_is_different() throws InterruptedException {
		String resultsFilteredCount = searchPage.getResultsCount();
		Integer resultFilterCount = Util.extractIntegerFromString(resultsFilteredCount);
		Integer resultFullCount = (Integer) this.shareContent.sharedValues.get("FordF150AllCount");
		System.out.println("Ford F-150 2010 to current year url - " + DriverFactory.getDriver().getCurrentUrl());
		assertThat(resultFilterCount, not(comparesEqualTo(resultFullCount)));
		
		
	}

}
