package searchFeature.nutritionAPIV2_model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/*
 * Create by Kyle Wolff May 8 2015
 */
public class Label {
	
	public Serving serving;

	@SerializedName("nutrients")
	public List<Nutrients> nutrients;
}