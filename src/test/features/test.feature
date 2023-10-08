# Guardian page link: https://www.theguardian.com/tone/news are valid

  @newsValidation
  Feature: Guardian News validation

    Check if the first news from Guardian page is valid. Default comparison source is Google.

    Background:
      Given I have "TITLE" of the first news from Guardian news page

    Scenario: Check if news exists in other sources
      And I have opened the default comparison site
      When I search for news title on a comparison site
      Then I should see at least 2 matching result(s)


    @dateCheck
    Scenario: Check of there are similar news in few days range
      Given I have "DATE" of the first news from Guardian news page
      And Date for the first news from Guardian news page is today
      And I have opened the default comparison site
      When I search for news title on a comparison site
      Then I should see at least 1 matching result(s)
      And I should see results with similar publication date as for Guardian news

