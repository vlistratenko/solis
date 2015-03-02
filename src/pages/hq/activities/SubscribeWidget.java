package pages.hq.activities;

import core.util.Browser;
import core.util.CommonUtils;
import core.util.PropertyName;
import elements.Button;
import elements.Label;
import elements.SelectBox;
import elements.TextBox;
import elements.impl.ButtonImpl;
import elements.impl.LabelImpl;
import elements.impl.SelectBoxImpl;
import elements.impl.TextBoxImpl;
import pages.hq.LoginPage;

public class SubscribeWidget extends Browser{

	TextBox personEmailField = new TextBoxImpl("//input[@name='PersonContact@Email@Value']", "Email", true);
	TextBox personFNameField = new TextBoxImpl("//input[@name='PersonCensus@FirstName']", "First name", true);
	TextBox personLNameField = new TextBoxImpl("//input[@name='PersonCensus@LastName']", "Last name", true);
	TextBox personCityField = new TextBoxImpl("//input[@name='Address@Home@City']", "City", true);
	TextBox personZipField = new TextBoxImpl("//input[@name='Address@Home@Zip']", "Zip", true);
	SelectBox personStatesSelectBox = new SelectBoxImpl("//select[@name='Address@Home@State']", "States");
	Button subscribeButton = new ButtonImpl("//input[@value='Subscribe!']", "Subscribe", true);
	
	Label subscriptionIsSccessMessage = new LabelImpl("//div[.='Thanks for signing up!']", "Subscription is success");

	public SubscribeWidget() {
		deletecoockies();
		refresh();
	}
	
	public AddSubscribeWidgetPage backToSubscribegWidgetPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
		return new AddSubscribeWidgetPage();
	}
	
	public LoginPage backToLoginPage() {
		closeWindow();
		switchToWindow(CommonUtils.getProperty(PropertyName.CURRENT_WINDOW_HANDLE));
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
		verifier.verifyEquals(subscriptionIsSccessMessage.isDisplayed(), true, "Message that subscription success, is not displayed");
		return this;
	}
}
