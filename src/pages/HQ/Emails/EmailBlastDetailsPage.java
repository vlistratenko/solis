package pages.HQ.Emails;

import objects.Label;
import pages.HQ.HomePage;
import selenium.CommonUtils;

public class EmailBlastDetailsPage extends HomePage {
	Label openRateLabel = new Label("//div[.='Open rate']/following-sibling::div", "Open rate label");
	Label clikRateLabel = new Label("//div[.='Click rate']/following-sibling::div", "Click rate label");

	public EmailBlastDetailsPage verifyOpenRateStat(Integer openAmount) {
		Integer amountOfPablishedEmails = Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails"));
		String rate = String.valueOf(openAmount*100/amountOfPablishedEmails) + "%";
		for (int i = 1; i <= 30; i++) {
			if (waitConditionBecomesTrue(openRateLabel.getText().equalsIgnoreCase(rate))) {
				break;
			}
		}
		verify(openRateLabel.getText(), rate, "Wrong open rate");
		return this;
	}

	public EmailBlastDetailsPage verifyClickRateStat(Integer clickAmount) {
		Integer amountOfPablishedEmails = Integer.valueOf(CommonUtils.getProperty("amountOfPablishedEmails"));
		String rate = String.valueOf(clickAmount*100/amountOfPablishedEmails) + "%";
		for (int i = 1; i <= 30; i++) {
			if (waitConditionBecomesTrue(clikRateLabel.getText().equalsIgnoreCase(rate))) {
				break;
			}
		}
		return this;
	}
	
	private Boolean waitConditionBecomesTrue(Boolean condition) {
		if (!condition) {
			refresh();
			sleep(30000);
			return false;
		}
		return true;
	}

}
