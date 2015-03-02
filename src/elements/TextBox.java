package elements;

public interface TextBox extends Element {

	String getText();

	void type(String text);

	void clear();

	String setAttribute(String attName, String attValue);

	void removeAttribute(String string);

	void uploadAssetsImage(String filePath, String imageName);

	String getValue();
}
