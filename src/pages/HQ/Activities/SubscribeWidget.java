package pages.HQ.Activities;

import objects.Browser;
import objects.Button;
import objects.Label;
import objects.SelectBox;
import objects.TextBox;
import pages.HQ.LoginPage;
import selenium.CommonUtils;

public class SubscribeWidget extends Browser{

	TextBox personEmailField = new TextBox("//input[@name='PersonContact@Email@Value']", "Email", true);
	TextBox personFNameField = new TextBox("//input[@name='PersonCensus@FirstName']", "First name", true);
	TextBox personLNameField = new TextBox("//input[@name='PersonCensus@LastName']", "Last name", true);
	TextBox personCityField = new TextBox("//input[@name='Address@Home@City']", "City", true);
	TextBox personZipField = new TextBox("//input[@name='Address@Home@Zip']", "Zip", true);
	SelectBox personStatesSelectBox = new SelectBox("//select[@name='Address@Home@State']", "States");
	Button subscribeButton = new Button("//input[@value='Subscribe!']", "Subscribe", true);
	
	Label subscriptionIsSccessMessage = new Label("//div[.='Thanks for signing up!']", "Subscription is success");

	public SubscribeWidget() {
		deletecoockies();
		refresh();
	}
	
	public AddSubscribeWidgetPage backToSubscribegWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty("currentWindowHandle"));
		return new AddSubscribeWidgetPage();
	}
	
	public LoginPage backToLoginPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty("currentWindowHandle"));
		return new LoginPage();
	}

	public SubscribeWidget fillSubscribeWidget(String personEmail,
			String personFName,
			String personLName,
			String personCity,
			String personZip) {
		personEmailField.type(personEmail);
		personFNameField.type(personFName);
		personLNameField.type(personLName);
		personCityField.type(personCity);
		personZipField.type(personZip);
		personStatesSelectBox.selectByIndex(Integer.parseInt(CommonUtils.getRandomValueFromTo(1, 50, 0)));
		subscribeButton.click();
		return this;
		
	}
	
	public SubscribeWidget verifySubscriptionIsSuccesses() {
		for (int i = 0; i < 10; i++) {
			if (waitConditionBecomesTrue(subscriptionIsSccessMessage.isDisplayed(), 10000)) {
				break;
			}
		}
		verify(subscriptionIsSccessMessage.isDisplayed(), true, "Message that subscription success, is not displayed");
		return this;
	}
}
