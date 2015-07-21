package searchFeature.nutritionAPIV2_model;

import com.google.gson.annotations.SerializedName;

public class Metric {
	
	@SerializedName("qty")
	public float qty;
	
	@SerializedName("uom")
	public String uom;
}
