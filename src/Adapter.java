import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

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
