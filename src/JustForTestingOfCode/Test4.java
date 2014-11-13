package JustForTestingOfCode;



import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import objects.Supporter;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import selenium.HttpClient;

public class Test4 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, JSONException {
		try {
			System.err.println(
					new HttpClient().
					login("20141106123247.4441b2bf@mailosaur.in", "!QAZ2wsx").
					createSupporter(new Supporter().getSupporterJSON("bla61@bla.bla"))
							);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
}
