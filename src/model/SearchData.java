package model;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchData {
	
	 @SerializedName("exact")
	 public boolean exact;
	 
	 @SerializedName("total")
	 public int total;
	 
	 @SerializedName("status")
	 public int status;

	 public List<Results> results = new ArrayList<Results>();
}
