/*
 */
package net.linkfluence.jspore.middleware.auth;

import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import net.linkfluence.jspore.Method;
import net.linkfluence.jspore.SporeException;
import net.linkfluence.jspore.middleware.Middleware;

/**
 * ApiKey HTTP auth implementation.
 * 
 * Example for django tastypie :
 * headerName = "AUTHORIZATION"
 * headerValue = "apikey username:hashcode"
 * 
 * @author mb
 */
public class ApiKey extends Middleware {

	private final String headerName; 
    private final String headerValue;
    
    public ApiKey(String headerName, String headerValue){
    	this.headerName = headerName;
    	this.headerValue = headerValue;
    }
    
    @Override
    public void sendRequest(RequestBuilder request, Object body, Method context) throws SporeException {
        if(context.authentication){
        	request.setHeader(this.headerName, this.headerValue);
        }
        next(request, body, context);
    }

    @Override
    public Object receiveRequest(Response response, Object body, Method context) throws SporeException {
        return next(response, body, context);
    }
    
    @Override
    public String getName() {
        return ApiKey.class.getName();
    }
    
}
