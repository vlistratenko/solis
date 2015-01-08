package pages.HQ.Activities;

import objects.Button;

public class SubscribeWidgetPage extends ActivitiesPage{
	
	Button createSubscribeWidgetButton = new Button("//a[contains(text(), 'Create a Sign-up Form')]", "Create a Signup Form");

	public AddSubscribeWidgetPage openAddSubscribeWidgetPage() {
		createSubscribeWidgetButton.click();
		return new AddSubscribeWidgetPage();
	}
}
