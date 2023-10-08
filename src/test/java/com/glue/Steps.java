package com.glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pop.GoogleResultsPage;
import pop.GoogleSearchPage;
import pop.GuardianLandingPage;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



public class Steps{

    GuardianLandingPage guardianLandingPage;
    GoogleSearchPage searchPage;
    GoogleResultsPage resultsPage;

    String newsTitle;
    String newsDate;

    /***
     * Opens Guardian page and get the given parameter.
     * Available params: TITLE and DATE
     * @param newsParameter
     */
    @Given("^I have \"(.*)\" of the first news from Guardian news page$")
    public void iHaveTITLEOfTheFirstNewsFromGuardianNewsPage(String newsParameter) {
        guardianLandingPage = new GuardianLandingPage(StepsBase.driver);
        guardianLandingPage.openPage();
        if (newsParameter.toUpperCase().equals("TITLE")){
            newsTitle = guardianLandingPage.getFirstNewsTitle();
        }else if (newsParameter.toUpperCase().equals("DATE")){
            newsDate = guardianLandingPage.getFirstNewsDate();
        }
    }

    /***
     * Use 'I have "TITLE" of the first news from Guardian news page' before!
     */
    @When("I search for news title on a comparison site")
    public void iSearchForNewsTitleOnAComparisonSite() {
        searchPage.search(newsTitle);
    }

    /***
     * Matching results comparison is done with results count!
     * @param count
     */
    @Then("I should see at least {int} matching result\\(s)")
    public void iShouldSeeAtLeastMatchingResultS(int count) {
        resultsPage = new GoogleResultsPage(StepsBase.driver);

        Assert.assertTrue(resultsPage.getResultsCount() >= count,
                "Result count is "+resultsPage.getResultsCount()+", but should be at least "+count);
    }

    /***
     * Opens google search page
     */
    @And("I have opened the default comparison site")
    public void iHaveOpenedTheDefaultComparisonSite() {
        searchPage = new GoogleSearchPage(StepsBase.driver);
        searchPage.openPage();
    }

    /***
     * Similiar publication date - 6 days ago is max
     */
    @And("I should see results with similar publication date as for Guardian news")
    public void iShouldSeeSimiliarPublicationDateForResultAsForGuardianNews() {
        Assert.assertTrue(resultsPage.dateIsPresent(), "There are no similar dates for results different than Guardian pages");
    }

    /***
     * Compares Guardian news date with local time date
     */
    @And("Date for the first news from Guardian news page is today")
    public void dateForTheFirstNewsFromGuardianNewsPageIsToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String newsDate = guardianLandingPage.getFirstNewsDate();

        Assert.assertTrue(dtf.format(now).equals(newsDate),
                "Wrong date! Current: "+dtf.format(now)+", News date: "+newsDate);
    }
}
