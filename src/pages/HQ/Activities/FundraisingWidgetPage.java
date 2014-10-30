package pages.HQ.Activities;

import objects.Button;

public class FundraisingWidgetPage extends ActivitiesPage{
	Button createFundraisingWidgetButton = new Button("//a[contains(text(), 'Create a Fundraising Widget')]", "Create a Fundraising Widget");

	public AddDonationWidgetPage openAddDonationWidgetPage() {
		createFundraisingWidgetButton.click();
		return new AddDonationWidgetPage();
	}
}
