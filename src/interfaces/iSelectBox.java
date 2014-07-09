package interfaces;

public interface iSelectBox extends iTextBox, iElement{

	void selectByLabel(String value);
	void selectByIndex(int index);
	void highlight();
	
}
