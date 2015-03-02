package pages.hq.activities;

import elements.Button;
import elements.impl.ButtonImpl;

public class FundraisingWidgetPage extends ActivitiesPage{
	Button createFundraisingWidgetButton = new ButtonImpl("//a[contains(text(), 'Create a Fundraising Form')]", "Create a Fundraising Form");

	public AddDonationWidgetPage openAddDonationWidgetPage() {
		createFundraisingWidgetButton.click();
		return new AddDonationWidgetPage();
	}
}
