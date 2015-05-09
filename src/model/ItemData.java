package model;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/*
 * Create by Kyle Wolff May 8 2015
 */
public class ItemData {
	
	 @SerializedName("name")
	 public String name;
	 
	 @SerializedName("type")
	 public int type;
	 
	 @SerializedName("ingredient_statement")
	 public String ingredientStatement; // may be null
	 
	 public List<Brand> brand = new ArrayList<Brand>();
	 
	 public List<Images> images = new ArrayList<Images>();
	 
	 public List<Label> label = new ArrayList<Label>();
	 
	 public List<Nutrients> nutrients = new ArrayList<Nutrients>();

	 
}
