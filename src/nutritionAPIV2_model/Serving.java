package nutritionAPIV2_model;
import com.google.gson.annotations.SerializedName;

/*
 * Created by Kyle Wolff May 8 2015
 */
public class Serving {

	@SerializedName("qty")
	public int quantity;
	
	@SerializedName("uom")
	public String uom;
	
	@SerializedName("per_container")
	public int perContainer;
}
