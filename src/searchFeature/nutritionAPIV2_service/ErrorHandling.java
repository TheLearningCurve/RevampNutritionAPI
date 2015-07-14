package searchFeature.nutritionAPIV2_service;
import org.omg.CORBA.portable.UnknownException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ErrorHandling implements ErrorHandler {
	
		@Override
		public Throwable handleError(RetrofitError cause) {
			
			
		    			    	
		    return cause;
		  }
}
