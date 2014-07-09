package tests;


import org.testng.annotations.Optional;

import com.mailosaur.model.EmailData;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import selenium.EmailClient;
import selenium.TestLink;


public class Test5 {
	public static void main(String[] args)  throws Exception {
		EmailClient email = new EmailClient();
		email.mbox = "9414e959";
		EmailData e = email.getEmailsByRecipient("sup2.9414e959@mailosaur.in")[0].html;
		System.err.println(e.toString());
	}
}
