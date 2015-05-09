
public class QueryVariables {

	public static String searchTerm;
	public static String itemId;
	public static String text;
	
	public static String getSearchTerm() {
		return searchTerm;
	}
	public static void setSearchTerm(String searchTerm) {
		QueryVariables.searchTerm = searchTerm;
	}
	public static String getItemId() {
		return itemId;
	}
	public static void setItemId(String itemId) {
		QueryVariables.itemId = itemId;
	}
	public static String getText() {
		return text;
	}
	public static void setText(String text) {
		QueryVariables.text = text;
	}
}
