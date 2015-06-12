package nutritionAPIV2_service;
import org.omg.CORBA.portable.UnknownException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ErrorHandling implements ErrorHandler {
	
		@Override
		public Throwable handleError(RetrofitError cause) {
			
			Response r = cause.getResponse();
			
		    	if (r != null && r.getStatus() == 401 || r.getStatus() == 403 || 
		    			r.getStatus() == 500 || r.getStatus() == 400) {
		    		return new UnknownException(cause);
		    	}
		    			    	
		    return cause;
		  }
}
