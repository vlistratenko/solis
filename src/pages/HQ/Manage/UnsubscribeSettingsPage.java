package pages.HQ.Manage;

import objects.Button;
import objects.TextBox;

public class UnsubscribeSettingsPage extends ManagePage {

	TextBox introductoryTextField = new TextBox("//textarea[@id='emailUnsubscribeText']", "Introductory Text ", false);
	Button unsubscribeLink = new Button("//a[contains(@href, 'unsubscribe')]", "Unsubscribe", true);
}
