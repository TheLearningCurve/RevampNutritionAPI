package service;
import ItemData;
import SearchData;
import TypeAHead;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/*
 * Created by Kyle Wolff May 8 2015
 */
public interface GetAPICalls {
	
	@GET("/search")
	void searchFood(@Query("q") String searchTerm, @Query("limit") int limit, @Query("offset") int offset, Callback<SearchData> cb);
	
	@GET("/item/{id}")
	void itemFacts(@Path("id") String itemId, Callback<ItemData> cb);
	
	@GET("/autocomplete")
	void typeAhead(@Query("q") String text, Callback<TypeAHead> cb);

}
