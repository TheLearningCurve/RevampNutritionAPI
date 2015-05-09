import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/*
 * Created by Kyle Wolff May 8 2015
 */
public class Adapter {

	GetAPICalls getapicalls;
	
	RequestInterceptor requestInterceptor = new RequestInterceptor() {

		  @Override
		  public void intercept(RequestFacade request) {

		    request.addHeader("X-APP-ID", Config.appId);
		    request.addHeader("X-APP-KEY", Config.appKey);

		  }
		};

		RestAdapter restAdapter = new RestAdapter.Builder()
		  .setEndpoint(Config.BASE_URL)
		  .setRequestInterceptor(requestInterceptor)
		  .build();
		
	
	public void searchForFood() {
		
		getapicalls = restAdapter.create(GetAPICalls.class);
		getapicalls.searchFood(QueryVariables.searchTerm, 50, 0, new Callback<SearchData>() {

			@Override
			public void success(SearchData searchData, Response response)
			{
							
			}

			@Override
			public void failure(RetrofitError retrofitError)
			{
				System.out.println(retrofitError.getResponse().getReason());	
			}
		});
	}
	
	public void getItem() {
		
		getapicalls.itemFacts(QueryVariables.itemId, new Callback<ItemData>() {

			@Override
			public void success(ItemData itemData, Response response)
			{
							
			}

			@Override
			public void failure(RetrofitError retrofitError)
			{
				System.out.println(retrofitError.getResponse().getReason());	
			}
		});	
	}
	
	public void typeAhead() {
		
		getapicalls.typeAhead(QueryVariables.text, new Callback<TypeAHead>() {

			@Override
			public void success(TypeAHead typeAhead, Response response)
			{
							
			}

			@Override
			public void failure(RetrofitError retrofitError)
			{
				System.out.println(retrofitError.getResponse().getReason());	
			}
		});
		
	}
}
