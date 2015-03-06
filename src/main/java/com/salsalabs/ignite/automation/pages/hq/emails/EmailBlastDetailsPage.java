package com.salsalabs.ignite.automation.pages.hq.emails;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class EmailBlastDetailsPage extends HomePage {
	Label openRateLabel = new LabelImpl("//div[.='Open rate']/following-sibling::div", "Open rate label");
	Label clikRateLabel = new LabelImpl("//div[.='Click rate']/following-sibling::div", "Click rate label");
	Label hardBounceLabel = new LabelImpl("//insight-linear-stat[@text='Hard bounces']/descendant::span[2]", "Hard bounce label");

	public EmailBlastDetailsPage verifyOpenRateStat(Integer openAmount) {
		Integer amountOfPablishedEmails = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS));
		String rate = String.valueOf(openAmount*100/amountOfPablishedEmails) + "%";
		for (int i = 1; i <= 30; i++) {
			if (waitConditionBecomesTrue(openRateLabel.getText().equalsIgnoreCase(rate))) {
				break;
			}
		}
		verifier.verifyEquals(openRateLabel.getText(), rate, "Wrong open rate");
		return this;
	}

	public EmailBlastDetailsPage verifyClickRateStat(Integer clickAmount) {
		Integer amountOfPablishedEmails = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS));
		String rate = String.valueOf(clickAmount*100/amountOfPablishedEmails) + "%";
		for (int i = 1; i <= 30; i++) {
			if (waitConditionBecomesTrue(clikRateLabel.getText().equalsIgnoreCase(rate))) {
				break;
			}
		}
		return this;
	}
	
	private boolean waitConditionBecomesTrue(boolean condition) {
		if (!condition) {
			refresh();
			sleep(30);
			return false;
		}
		return true;
	}

	public EmailBlastDetailsPage verifyHardBouncesStat(Integer hardBounceAmount) {
		if (hardBounceAmount == 0) {
			return this;
		}
		for (int i = 1; i <= 30; i++) {
			if (waitConditionBecomesTrue(hardBounceLabel.getText().equalsIgnoreCase( hardBounceAmount.toString()))) {
				break;
			}
		}
		verifier.verifyEquals(hardBounceLabel.getText(), hardBounceAmount.toString(), "Wrong amount of hard bounces", true);
		return this;		
	}

}
