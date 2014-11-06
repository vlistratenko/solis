package pages.HQ.Activities;

import objects.Button;

public class SubscribeWidgetPage extends ActivitiesPage{
	
	Button createSubscribeWidgetButton = new Button("//a[contains(text(), 'Create a Signup Widget')]", "Create a Signup Widget");

	public AddSubscribeWidgetPage openAddSubscribeWidgetPage() {
		createSubscribeWidgetButton.click();
		return new AddSubscribeWidgetPage();
	}
}
