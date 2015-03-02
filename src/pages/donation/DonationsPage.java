package pages.donation;

import elements.Table;
import elements.impl.TableImpl;
import pages.hq.HomePage;


public class DonationsPage extends HomePage{

	Table donationsTable = new TableImpl("//*[.='Transaction Date']/ancestor::table", "Table with Donations");
	
	public DonationsPage verifyDonationRecordInTable(String amount, String status, boolean type, String source, String supporter) {
		for (int i = 0; i < 15; i++) {
			if (waitConditionBecomesTrueWithRefersh(donationsTable.isValueExists(source)>0, 60000)) {
				break;
			}
		}
		verifier.verifyEquals(donationsTable.isValueExists(source)>0, true, "Donation item with source " + source + "is not found");
		
		String dType;
		if (!type) {
			dType = "One-time";
		}else{
			dType = "Recurring";
		}
		verifier.verifyEquals(donationsTable.getCellValue(1, "Amount"), amount, "Wrong amount", false);
		for (int i = 0; i < 15; i++) {
			if (waitConditionBecomesTrueWithRefersh(donationsTable.getCellValue(1, "Status").equalsIgnoreCase(status) , 30000)) {
				break;
			}
		}
		verifier.verifyEquals(donationsTable.getCellValue(1, "Status"), status, "Wrong status", false);
		verifier.verifyEquals(donationsTable.getCellValue(1, "Type"), dType, "Wrong type", false);
		verifier.verifyEquals(source.contains(donationsTable.getCellValue(1, "Source")), true, "Wrong source " + donationsTable.getCellValue(1, "Source"), false);
		verifier.verifyEquals(donationsTable.getCellValue(1, "Supporter"), supporter, "Wrong supporter", false);
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
