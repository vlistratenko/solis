package com.salsalabs.ignite.automation.pages.hq.manage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.JSONException;
import com.salsalabs.ignite.automation.common.CommonUtils;
import com.salsalabs.ignite.automation.common.HttpClient;
import com.salsalabs.ignite.automation.common.SeleneseTestCase;
import com.salsalabs.ignite.automation.common.Supporter;
import com.salsalabs.ignite.automation.elements.Button;
import com.salsalabs.ignite.automation.elements.TextBox;
import com.salsalabs.ignite.automation.elements.impl.ButtonImpl;
import com.salsalabs.ignite.automation.elements.impl.TextBoxImpl;
import com.salsalabs.ignite.automation.pages.donation.DonationsPage;
import com.salsalabs.ignite.automation.pages.hq.HomePage;

public class ImportDonationsPage extends HomePage {
	Button importListOfDonations = new ButtonImpl("//button[contains(text(), 'List')]",
			"Import List of Donations button");
	Button browseTheFileButton = new ButtonImpl("//span[contains(text(), 'File')]/../parent::button",
			"Browse The File Button");
	TextBox importName = new TextBoxImpl("//input[@id='name']", "Import List of Donations button");
	TextBox fileUpload = new TextBoxImpl("//input[@name='fileUpload']", "Input for file upload", false);
	Button doneProgressBar = new ButtonImpl("//div[contains(text(), 'Done')]",
			"Progress bar with done status");
	Button nextButtonSettings = new ButtonImpl("//button[contains(text(), 'Next')]",	"Next - Import Settings");
	Button nextButtonMappingPage = new ButtonImpl("//button[@id='btnSave2']",	"Next - Import Settings");
	Button iAmDone = new ButtonImpl(".//*[@id='btnSave3']",	"I am Done");
	TextBox importComplete = new TextBoxImpl("//p[contains(text(), 'Import')]", "Import List of Donations button");
	private static Supporter supporter = new Supporter();

	public ImportDonationsPage uploadTheImportedFile(String nameImport, String login, String passward,
			String externalId, String ammount , boolean recurring) {
		importListOfDonations.click();
		if (!importName.isDisplayed()) {
			importName.waitElement();
		}
		importName.type(nameImport);
		waitConditionBecomesTrue(browseTheFileButton.isDisplayed(), 5);
		fileUpload.setAttribute("class", "text");
		fileUpload.scrollIntoView();
		try {
			fileUpload.type(generateDonationImportFile(login, passward, externalId , ammount,  recurring));
		} catch (FileNotFoundException e) {
			SeleneseTestCase.logger.error("",e);
		}
		for (int i = 0; i < 5; i++) {
		if(waitConditionBecomesTrue(doneProgressBar.isDisplayed(), 5)){
			break;
			}
		}
		return this;
	}
	
	public ImportDonationsPage proceedtoTheMappingStep(){
		nextButtonSettings.click();
		for (int i = 0; i < 3; i++) {
			if(waitConditionBecomesTrue(nextButtonMappingPage.isDisplayed(), 5)){
				break;
				}
			}
		nextButtonMappingPage.scrollIntoView();
		nextButtonMappingPage.click();
		return this;
		
	}

	public ImportDonationsPage completeImport(){
		for (int i = 0; i < 3; i++) {
			if(waitConditionBecomesTrue(iAmDone.isDisplayed(), 5)){
				break;
				}
			}
		iAmDone.click();
		sleep(2);
		importComplete.waitElement();		
		return this;
	}	
	
	public DonationsPage backToTransactionPage(){
		openDonationsPage() ;
		return new DonationsPage();
			}	

	public String generateDonationImportFile(String login, String passward, String externalId, String ammount,  boolean recurring)
			throws FileNotFoundException {
		prepareSupporterForDonationImport(login, passward, externalId);
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String date = now.format(formatter);
		PrintWriter out = new PrintWriter("donationImport.csv");
		StringBuilder header = new StringBuilder();
		header.append(
				"supporter email address,supporter external id,donation date,donation amount,donation type,was offline,transaction id");
		out.println(header);
		StringBuilder row = new StringBuilder();
		row.append(supporter.getFinalEMAIL()).append(",");
		row.append(externalId).append(",");
		row.append(date).append(",");
		row.append(ammount).append(",");
		if(recurring){
			row.append("Recurring").append(",");	
		}else{
			row.append("OneTime").append(",");	
		}
		row.append("false").append(",");
		row.append(CommonUtils.getRandomNumericValueFixedLength(5));
		out.println(row);
		out.close();
		return new File("donationImport.csv").getAbsolutePath();
	}

	public String generateTheImportedFile() {

		return new File("").getAbsolutePath();
	}

	public static void prepareSupporterForDonationImport(String login, String passward, String externalId) {

		supporter.setFinalEMAIL(CommonUtils.getUnicName() + ".74580786@mailosaur.in");
		try {
			new HttpClient(SeleneseTestCase.USED_ENVIRONMENT.getBaseTestUrl()).login(login, passward)
					.createSupporter(supporter.getSupporterJSONWithExternalId(supporter.getFinalEMAIL(), externalId));
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | URISyntaxException
				| IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			ClientProtocolException, JSONException, URISyntaxException, IOException {
		ImportDonationsPage test = new ImportDonationsPage();
		test.generateDonationImportFile("qb.74580786@mailosaur.in", "Gnusmas_1", "23341133");
		// test.prepareSupporter();

	}*/

}
