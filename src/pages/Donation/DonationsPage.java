package pages.Donation;

import objects.Table;
import pages.HQ.HomePage;


public class DonationsPage extends HomePage{

	Table donationsTable = new Table("//*[.='Transaction Date']/ancestor::table", "Table with Donations");
	
	public DonationsPage verifyDonationRecordInTable(String amount, String status, Boolean type, String source, String supporter) {
		for (int i = 0; i < 15; i++) {
			if (waitConditionBecomesTrueWithRefersh(donationsTable.isValueExists(source)>0, 60000)) {
				break;
			}
		}
		verify(donationsTable.isValueExists(source)>0, true, "Donation item is not found");
		
		String dType;
		if (!type) {
			dType = "One-time";
		}else{
			dType = "Recurring";
		}
		verify(donationsTable.getCallValue(1, "Amount"), amount, "Wrong amount", false);
		//verify(donationsTable.getCallValue(1, "Transaction Date"), expected, message, false);
		for (int i = 0; i < 15; i++) {
			waitConditionBecomesTrueWithRefersh(donationsTable.getCallValue(1, "Status").equalsIgnoreCase(status) , 30000);
		}
		verify(donationsTable.getCallValue(1, "Status"), status, "Wrong status", false);
		verify(donationsTable.getCallValue(1, "Type"), dType, "Wrong type", false);
		verify(donationsTable.getCallValue(1, "Source"), source, "Wrong source", false);
		verify(donationsTable.getCallValue(1, "Supporter"), supporter, "Wrong supporter", false);
		return new DonationsPage();
	}

	public DonationsDetailsPage openDonation(String widgetName) {
		donationsTable.clickInCell(
				donationsTable.getRowsNumberByValue(widgetName), 
				donationsTable.getColumnNumberByHeader("Source"),
				"span");
		return new DonationsDetailsPage();
	}

}
