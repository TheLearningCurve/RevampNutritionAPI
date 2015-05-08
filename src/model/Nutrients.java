package model;
import com.google.gson.annotations.SerializedName;


public class Nutrients {

	@SerializedName("value")
	public float value;

	@SerializedName("unit")
	public String unit;

	@SerializedName("name")
	public String name;

}
