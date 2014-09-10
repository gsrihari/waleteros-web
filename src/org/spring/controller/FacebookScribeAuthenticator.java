/*package org.spring.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class FacebookScribeAuthenticator {
 
	  public static final String STATE = "state";
	  private String applicationHost="http://localhost:2014/Spring-Facebook";

	  
	  @Autowired
	  private FacebookConnectionFactory connectionFactoryLocator;=new FacebookConnectionFactory(clientId, clientSecret);
	  
	  
	  @RequestMapping("/auth/facebook")
	  public RedirectView startAuthentication(HttpSession session)
	      throws Exception {
	    String state = UUID.randomUUID().toString();
	    session.setAttribute(STATE, state);
	   
	    OAuth2Operations oauthOperations =
	    		connectionFactoryLocator.getOAuthOperations();
	    OAuth2Parameters params = new OAuth2Parameters();
	    params.setRedirectUri(applicationHost + "/auth/facebook/callback");
	    params.setState(state);
	   
	    String authorizeUrl = oauthOperations.buildAuthorizeUrl(
	        GrantType.AUTHORIZATION_CODE, params);
	    return new RedirectView(authorizeUrl);
	  }
	  
	  @RequestMapping("/auth/facebook/callback")
	  public RedirectView callBack(@RequestParam("code") String code,
	                               @RequestParam("state") String state,
	                               HttpSession session) {
	    String stateFromSession = (String) session.getAttribute(STATE);
	    session.removeAttribute(STATE);
	    if (!state.equals(stateFromSession)) {
	      return new RedirectView("/login");
	    }
	   
	    AccessGrant accessGrant = getAccessGrant(code);
	   
	    String facebookUserId = getFacebookUserId(accessGrant);
	    session.setAttribute("facebookUserId", facebookUserId);
	    System.out.println("fb id = "+facebookUserId +" -- ");
	    return new RedirectView("/logged-in");
	  }
	  
	  private AccessGrant getAccessGrant(String authorizationCode) {
		  OAuth2Operations oauthOperations =
				  connectionFactoryLocator.getOAuthOperations();
		  return oauthOperations.exchangeForAccess(authorizationCode,
		      applicationHost + "/auth/facebook/callback", null);
	}
	  
	  private String getFacebookUserId(AccessGrant accessGrant) {
		  Connection<Facebook> connection =
				  connectionFactoryLocator.createConnection(accessGrant);
		  ConnectionKey connectionKey = connection.getKey();
		  return connectionKey.getProviderUserId();
	}
      
}
*/