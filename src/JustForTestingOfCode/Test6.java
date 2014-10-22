package JustForTestingOfCode;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import com.mailosaur.exception.MailosaurException;
import com.mailosaur.model.Email;
import selenium.EmailClient;



public class Test6 {
	 public static void main(String[] args) throws MailosaurException, IOException, NoSuchAlgorithmException, KeyManagementException {

		 ArrayList<Email> e = new EmailClient().getEmailsBySubject("Some descriptive and catchy subject would go here");
		 
		 for (int i = 0; i < e.size()-1; i++) {
			 new EmailClient().openEmail(e.get(i));
			
		 }
		 
		 for (int i = 0; i < 3; i++) {
			 new EmailClient().clickLinkByText(e.get(i), "Link");
		 }
		 
		 
		 
	 }

}
