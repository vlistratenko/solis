package com.salsalabs.ignite.automation.pages.donation;

import java.util.ArrayList;
import java.util.List;

import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.Table;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TableImpl;
import com.salsalabs.ignite.automation.pages.hq.HomePage;


public class DonationsPage extends HomePage{

	Table donationsTable = new TableImpl("//*[.='Transaction Date']/ancestor::table", "Table with Donations");
	Button createDonationFormBtn = new ButtonImpl("//a[contains(@href, '/#/activities/widget/create?type=FUNDRAISE')]", "Add Donation Form");
	
	public DonationsPage verifyDonationRecordInTable(String amount, String status, boolean type, String source, String supporter) {	
		for (int i = 0; i < 15; i++) {
			if (waitConditionBecomesTrueWithRefersh(donationsTable.isValueExists(source)>0, 60)) {
				break;
			}
		}
		verifier.verifyTrue(donationsTable.isValueExists(source)>0, "Donation item with source " + source + "is not found");
		
		String dType;
		if (!type) {
			dType = "One-time";
		}else{
			dType = "Recurring";
		}
		verifier.verifyEquals(donationsTable.getCellValueUsingAllHeadersMethod(1, "Amount"), amount, "Wrong amount", false);
		for (int i = 0; i < 15; i++) {
			if (waitConditionBecomesTrueWithRefersh(donationsTable.getCellValueUsingAllHeadersMethod(1, "Status").equalsIgnoreCase(status) , 3000)) {
				break;
			}
		}
		verifier.verifyEquals(donationsTable.getCellValueUsingAllHeadersMethod(1, "Status"), status, "Wrong status");
		verifier.verifyEquals(donationsTable.getCellValueUsingAllHeadersMethod(1, "Type"), dType, "Wrong type");
		verifier.verifyTrue(source.contains(donationsTable.getCellValueUsingAllHeadersMethod(1, "Source")), "Wrong source " + donationsTable.getCellValue(1, "Source"));
		verifier.verifyEquals(donationsTable.getCellValueUsingAllHeadersMethod(1, "Supporter"), supporter, "Wrong supporter");
		return new DonationsPage();
	}
	
	public DonationsPage verifyDonationAmmountAndFee(String amount, String fee) {	
		verifier.verifyEquals(donationsTable.getCellValueUsingAllHeadersMethod(1, "Amount"), amount, "Wrong amount", false);
		verifier.verifyEquals(donationsTable.getCellValueUsingAllHeadersMethod(1, "Fees Paid By Supporter"), fee, "Wrong amount", false);
		
		return new DonationsPage();
	}

	public DonationsDetailsPage openDonation(String widgetName) {
		donationsTable.clickInCell(
				donationsTable.getRowsNumberByValue(widgetName), 
				donationsTable.getColumnNumberByHeaderUsingGetAllHeadersMethod("Source"),
				"/span");
		return new DonationsDetailsPage();
	}

}
