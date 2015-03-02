package pages.hq.activities;

import elements.Button;
import elements.impl.ButtonImpl;

public class SubscribeWidgetPage extends ActivitiesPage{
	
	Button createSubscribeWidgetButton = new ButtonImpl("//a[contains(text(), 'Create a Sign-up Form')]", "Create a Signup Form");

	public AddSubscribeWidgetPage openAddSubscribeWidgetPage() {
		createSubscribeWidgetButton.click();
		return new AddSubscribeWidgetPage();
	}
}
