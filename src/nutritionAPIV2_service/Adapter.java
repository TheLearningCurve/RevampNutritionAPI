package nutritionAPIV2_service;
import java.util.List;

import javax.swing.SwingWorker;

import nutritionAPIV2_controllers.SearchController;
import nutritionAPIV2_model.ItemData;
import nutritionAPIV2_model.Nutrients;
import nutritionAPIV2_model.Results;
import nutritionAPIV2_model.SearchData;
import nutritionAPIV2_model.TypeAHead;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/*
 * Created by Kyle Wolff May 8 2015
 */
public class Adapter {

	GetAPICalls getapicalls;
	
	/*
	 * RequestInterceptor is to add the headers to each call that is made
	 */
	RequestInterceptor requestInterceptor = new RequestInterceptor() {

		  @Override
		  public void intercept(RequestFacade request) {

		    request.addHeader("X-APP-ID", Config.appId);
		    request.addHeader("X-APP-KEY", Config.appKey);

		  }
		};

		/*
		 * RestAdapter is the class through which your API interfaces are turned into callable objects
		 */
		RestAdapter restAdapter = new RestAdapter.Builder()
		  .setEndpoint(Config.BASE_URL)
		  .setLogLevel(RestAdapter.LogLevel.FULL)
		  .setRequestInterceptor(requestInterceptor)
		  .setErrorHandler(new ErrorHandling())
		  .build();
		
	/*
	 * These are the methods we call in order to make the retrofit @Get calls
	 */
	public void searchForFood() {
		
		getapicalls = restAdapter.create(GetAPICalls.class);
		getapicalls.searchFood(QueryVariables.searchTerm, 50, 0, new Callback<SearchData>() {

			@Override
			public void success(SearchData searchData, Response response) {
				
				
			}
			
			@Override
			public void failure(RetrofitError retrofitError) {

				System.out.println(retrofitError.getMessage());
				
			}
		});
	}
	
	public void getItem() {
		
		getapicalls = restAdapter.create(GetAPICalls.class);
		getapicalls.itemFacts(QueryVariables.itemId, new Callback<ItemData>() {

			@Override
			public void success(ItemData itemData, Response response)
			{
				if(itemData.label.nutrients != null)
				{
					for(Nutrients n : itemData.label.nutrients)
					{
						System.out.println(n.name + ": " + n.value + n.unit);
					}
				}
				else
				{
					System.out.println(itemData.label.nutrients.size());
				}
			}

			@Override
			public void failure(RetrofitError retrofitError)
			{
				System.out.println(retrofitError.getMessage());	
			}
		});	
	}
	
	public void typeAhead() {
		
		getapicalls = restAdapter.create(GetAPICalls.class);
		getapicalls.typeAhead(QueryVariables.text, new Callback<List<TypeAHead>>() {

			@Override
			public void success(List<TypeAHead> typeAhead, Response response) {

				for(TypeAHead h : typeAhead)
				{
					SearchController.results(h.text);
				}			
			}
			
			@Override
			public void failure(RetrofitError retrofitError)
			{
				System.out.println(retrofitError.getMessage());	
			}
		});
		
	}
}
