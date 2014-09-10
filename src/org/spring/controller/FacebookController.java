package org.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.junit.rules.Verifier;
import org.scribe.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.Token;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/social/facebook")
@Controller
public class FacebookController {

	private static final String SCOPE = "email,offline_access,user_about_me,user_birthday,read_friendlists";
	private static final String REDIRECT_URI = "http://waleteros.myappdemo.net:8080/Spring-Facebook/social/facebook/callback";
	//private static final String REDIRECT_URI = "http://localhost:2014/Spring-Facebook/social/facebook/callback";
	private static final String CLIENT_ID = "747533195289067";
	private static final String APP_SECRET = "89c027a7cb05922aacb83b42159ae5d2";
	private static final String DIALOG_OAUTH = "https://www.facebook.com/dialog/oauth";
	private static final String ACCESS_TOKEN = "https://graph.facebook.com/oauth/access_token";
	private String applicationHost="http://waleteros.myappdemo.net:8080/Spring-Facebook/social/facebook";
	private FacebookConnectionFactory facebookConnectionFactory;
	
	  public FacebookController()
	  {
	    facebookConnectionFactory =
	      new FacebookConnectionFactory(CLIENT_ID, APP_SECRET);
	  }
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void signin(HttpServletRequest request, HttpServletResponse response)
               throws Exception {
		try {
			System.out.println("in singin");
			//TODO: if already have a valid access token, no need to redirect, just login
			response.sendRedirect(DIALOG_OAUTH+"?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&scope="+SCOPE);
		} catch (Exception e) {
			System.out.println("in catch");
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/callback", params = "code", method = RequestMethod.GET)
	@ResponseBody
	public String accessCode(@RequestParam("code") String code,
                                HttpServletRequest request,HttpSession session,
                                HttpServletResponse response) throws Exception {
		try {
			response.setContentType("text/html");
			System.out.println("code = "+code);
			
			//getUrl(code);
			
			//AccessGrant accessGrant = getAccessGrant(code);
			OAuth2Operations oauthOperations =facebookConnectionFactory.getOAuthOperations();
			System.out.println("Request for grant access");
			AccessGrant accessGrant=oauthOperations.exchangeForAccess(code,
					      applicationHost + "/callback", null);
			
			System.out.println("Access granted");
			//String facebookUserId = getFacebookUserId(accessGrant);
			
			Connection<Facebook> connection =facebookConnectionFactory.createConnection(accessGrant);
			ConnectionKey connectionKey = connection.getKey();
			String facebookUserId=connectionKey.getProviderUserId();
			session.setAttribute("facebookUserId",facebookUserId );
			
			/*String responseString = ACCESS_TOKEN+"?client_id="+CLIENT_ID+"&redirect_uri="+REDIRECT_URI+"&code="+code+"&client_secret="+APP_SECRET;
			response.getWriter().write(responseString);
			System.out.println("responseString = "+responseString);
			response.flushBuffer();*/
			return facebookUserId;

		} catch (Exception e) {
			e.printStackTrace();
			return ""+e;
		}
		
	}
	private AccessGrant getAccessGrant(String authorizationCode) {
		System.out.println("in get aceess grant");
		  OAuth2Operations oauthOperations =
		      facebookConnectionFactory.getOAuthOperations();
		  System.out.println("12"+oauthOperations.toString());
		  AccessGrant accessGrant=oauthOperations.exchangeForAccess(authorizationCode,
			      applicationHost + "/callback", null);
		  return accessGrant;
	}
	
	private String getFacebookUserId(AccessGrant accessGrant) {
		  Connection<Facebook> connection =
		      facebookConnectionFactory.createConnection(accessGrant);
		  ConnectionKey connectionKey = connection.getKey();
		  return connectionKey.getProviderUserId();
	}

	@RequestMapping(value = "/callback", params = "error_reason", method = RequestMethod.GET)
	@ResponseBody
	public void error(@RequestParam("error_reason") String errorReason,
                           @RequestParam("error") String error,
                           @RequestParam("error_description") String description,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, description);
			System.out.println(errorReason);
			System.out.println(error);
			System.out.println(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getUrl(String code)
	{
		try {

		java.net.URI uri = new URIBuilder(ACCESS_TOKEN)
		.setParameter("client_id", CLIENT_ID)
		.setParameter("redirect_uri", REDIRECT_URI)
		.setParameter("code",code)
		.setParameter("client_secret",APP_SECRET)
		.build();

		HttpGet httpget = new HttpGet(uri);

		System.out.println("Executing request " + httpget.getRequestLine()+httpget);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	{
<<<<<<< HEAD
		/*(function() {

  var app = angular.module("waleteros", ["ngRoute","ui.bootstrap","ngCookies"]);

  app.config(function($routeProvider) {
    $routeProvider
		.when("/",{
			templateUrl:"views/login.html",
			controller:"LoginCtrl"
		})  
		.when("/forgotpin",{
			templateUrl:"views/forgotpin.html",
			controller:"ForgotPinCtrl"
		})
		.otherwise({
			redirectTo: "/"
		});
	}).run(function($rootScope) {
		$rootScope.username = '';
	});
}());*/
	
=======
	
		(function(){

	var app = angular.module("waleterosAdmin");
		
	var HeaderCtrl=function($scope,$cookieStore,$cookies,$rootScope)
	{
		//alert($scope.test);
		//alert($scope.test);
		$scope.cookval = $cookieStore.get('username');
		$rootScope.test="Test";
		alert('in header ctrl rootScope is assigned = '+$rootScope.test);
		alert('in header ctrl username = '+$rootScope.test1)
		//$scope.cookval = $cookies.username;
		if ($scope.cookval == null|| $scope.cookval == '' || $scope.cookval == undefined){
			window.location.href="/waleteros/admin.html"
		}
		$scope.username=$scope.cookval;
		
		$scope.logout=function()
		{	
			//$cookieStore.put('username','');
			//alert($cookieStore.remove('username'))
			$cookieStore.remove('username');
			//delete $cookies["username"];
			//$cookies.username = '';
			//$cookieStore.put('username', 'undefined');
			window.location.href="/waleteros/admin.html"
		}
	};
	
	app.controller("HeaderCtrl", HeaderCtrl);

}());
>>>>>>> f63aebf28ae5da804f33eba742c7305dbcd79e77
	
	
	}

}