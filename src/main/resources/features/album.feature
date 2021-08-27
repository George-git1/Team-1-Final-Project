@webpage
Feature: Demo album create
  I want to use this template for my feature file

  Scenario: Title of your scenario
		Given I am on "http://127.0.0.1:5500/static/index.html"
		When I click Album menu
		And I click on create
		And I enter my username
		And I enter my password
		And I click create
		Then I should see New user created successfully!