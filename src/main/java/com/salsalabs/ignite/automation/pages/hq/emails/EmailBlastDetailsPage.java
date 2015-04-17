package com.salsalabs.ignite.automation.pages.hq.emails;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.PropertyName;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.elements.Label;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.LabelImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class EmailBlastDetailsPage extends HomePage {
	Label deliveryRateLabel = new LabelImpl("//div[.='Delivery rate']/following-sibling::div", "Delivery rate label");
	Label openRateLabel = new LabelImpl("//div[.='Open rate']/following-sibling::div", "Open rate label");
	Label clikRateLabel = new LabelImpl("//div[.='Click rate']/following-sibling::div", "Click rate label");
	Label unsubLabel = new LabelImpl("//div[.='Unsubscribe rate']/following-sibling::div", "Unsubscribe rate label");
	Label hardBounceLabel = new LabelImpl("//insight-linear-stat[@text='Hard bounces']/descendant::span[2]", "Hard bounce label");
	Table splitTable = new TableImpl("//div[@ng-show='metricGroups.length > 1']/table", "Split Test Results");
	
	private String getRate(Integer amount, Integer published){
		boolean round = amount * 100 % published != 0;
		return String.valueOf(new BigDecimal(amount.doubleValue() * 100.0 / published.doubleValue()).setScale(round ? 2 : 0, RoundingMode.HALF_UP)) + "%";
	}
	
	private EmailBlastDetailsPage verifyRateStat(Label label, Integer amount, Integer published, Integer hardBounceAmount) {
		Integer amountOfPablishedEmails = published - hardBounceAmount;
		String rate = getRate(amount, amountOfPablishedEmails);
		return verifyRateStat(label, rate);
	}
	
	private EmailBlastDetailsPage verifyRateStat(Label label, String value) {
		for (int i = 1; i <= 5; i++) {
			if (waitConditionBecomesTrue(label.getText().equalsIgnoreCase(value))) {
				break;
			}
		}
		verifier.verifyEquals(label.getText(), value, "Wrong rate", true);
		return this;
	}

	public EmailBlastDetailsPage verifyDeliveryRateStat(Integer published, Integer hardBounceAmount) {
		Integer amountOfPablishedEmails = Integer.valueOf(CommonUtils.getProperty(PropertyName.AMOUNT_OF_PUBLISHED_EMAILS));
		Integer deliveryAmount = amountOfPablishedEmails - hardBounceAmount;
		return verifyRateStat(deliveryRateLabel, deliveryAmount, published, 0);
	}
	
	public EmailBlastDetailsPage verifyOpenRateStat(Integer openAmount, Integer published, Integer hardBounceAmount) {
		return verifyRateStat(openRateLabel, openAmount, published, hardBounceAmount);
	}

	public EmailBlastDetailsPage verifyClickRateStat(Integer clickAmount, Integer published, Integer hardBounceAmount) {
		return verifyRateStat(clikRateLabel, clickAmount, published, hardBounceAmount);
	}
	
	public EmailBlastDetailsPage verifyUnsubRateStat(Integer unsubAmount, Integer published, Integer hardBounceAmount) {
		return verifyRateStat(unsubLabel, unsubAmount, published, hardBounceAmount);
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
		return verifyRateStat(hardBounceLabel, hardBounceAmount.toString());
	}
	
	public EmailBlastDetailsPage verifySplitTestResult(Integer splitAmount, Integer openAmount, Integer clickAmount, Integer unsubAmount, Integer published, Integer hardBounceAmount) {
		Integer amountOfPublishedEmails = (published - hardBounceAmount)/splitAmount;
		String clickRate = getRate(clickAmount / splitAmount, amountOfPublishedEmails);
		String openRate = getRate(openAmount / splitAmount, amountOfPublishedEmails);
		String unsubRate = getRate(unsubAmount / splitAmount, amountOfPublishedEmails);
		String targets = String.valueOf(amountOfPublishedEmails);
		for (int i = 1; i <= splitAmount; i++) {
			SeleneseTestCase.logger.info("Verify Split Test Result");
			verifier.verifyEquals(splitTable.getCellValue(i, 3), openRate, "Wrong rate", true);
			verifier.verifyEquals(splitTable.getCellValue(i, 4), clickRate, "Wrong rate", true);
			verifier.verifyEquals(splitTable.getCellValue(i, 6), unsubRate, "Wrong rate", true);
			verifier.verifyEquals(splitTable.getCellValue(i, 7), targets, "Wrong rate", true);
		}
		return this;
	}

}
