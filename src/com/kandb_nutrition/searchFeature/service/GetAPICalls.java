package com.kandb_nutrition.searchFeature.service;
import java.util.List;

import com.kandb_nutrition.searchFeature.model.ItemData;
import com.kandb_nutrition.searchFeature.model.SearchData;
import com.kandb_nutrition.searchFeature.model.TypeAHead;

import javafx.collections.ObservableList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/*
 * Created by Kyle Wolff May 8 2015
 */
public interface GetAPICalls {
	
	@GET("/search")
	void searchFoodLimitAndOffset(@Query("q") String searchTerm, @Query("limit") int limit, @Query("offset") int offset, Callback<SearchData> cb);
	
	@GET("/search")
	void searchFoodAllResults(@Query("q") String searchTerm, Callback<SearchData> cb);
	
	@GET("/item/{id}")
	void itemFacts(@Path("id") String itemId, Callback<ItemData> cb);
	
	@GET("/autocomplete")
	void typeAhead(@Query("q") String text, Callback<List<TypeAHead>> cb);

}
