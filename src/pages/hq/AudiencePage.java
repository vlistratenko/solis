package pages.hq;

import elements.Button;
import elements.impl.ButtonImpl;
import pages.hq.segments.SegmentsPage;
import pages.hq.supporters.SupportersPage;

public class AudiencePage extends HomePage{

	Button supportersButton = new ButtonImpl("//a[text()='Supporters']", "Supporters");
	Button segmentsButton = new ButtonImpl("//a[text()='Segments']", "Segments");
	
	public SupportersPage openSupportersPage() {
		
		supportersButton.click();
		return new SupportersPage();
	}
	
	public SegmentsPage openSegmentsPage() {
		
		segmentsButton.click();
		return new SegmentsPage();
	}
	
	public AudiencePage verifyURL() {
			
		verifier.verifyTrue(getLocation().contains("audience"), "Current URL is not contains Audience");
		return this;
	}
}
