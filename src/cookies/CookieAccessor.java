package cookies ;

import java.io.IOException;
import java.net.*;
import java.util.*;

import javax.swing.JTextField;

public class CookieAccessor {

	//private JTextField textField = new JTextField("    ");


	// Cookie Manager
	private CookieManager manager;

	// URL
	private URL url;

	// URL connection
	private URLConnection connection;

	public CookieAccessor() {
		manager = new CookieManager();
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(manager);
		try {
			url = new URL("http://royal.cs.mtholyoke.edu");
			connection = url.openConnection();
			connection.getContent();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * Get cookies for a url from cookie store
	 */
	public List<HttpCookie> getCookiesUsingCookieHandler() {
		try {

			System.out.println("I am in the get Cookie method");
			CookieStore cookieJar = manager.getCookieStore();

			List<HttpCookie> cookies = cookieJar.getCookies();
			System.out.println("the cookie is:" + cookies.size());

//			for (HttpCookie cookie : cookies) {
//				//textField.setText(cookie.toString());
//				System.out.println("CookieHandler retrieved cookie: " + cookie);
//			}
			
			return cookies ;
			
		} catch (Exception e) {

			//textField.setText(textField.getText()
				//	+ "Unable to get cookie using CookieHandler");

			System.out.println("Unable to get cookie using CookieHandler");
			e.printStackTrace();
			
			return null ;
		}
		
	
	}

	/**
	 * Set cookie in cookie store
	 */
	public void setCookieUsingCookieHandler(String cookieKey, String cookieValue ) {
		try {
			
			CookieStore cookieJar = manager.getCookieStore();

			// create cookie
			HttpCookie cookie = new HttpCookie(cookieKey, cookieValue);

			System.out.println("cookie " + cookie);

			cookieJar.add(url.toURI(), cookie);
			System.out.println("Added cookie using cookie handler");
			System.out.println("url " + url.toURI());

			System.out.println("get cookies");

			// textField= new JTextField("Added cookie using cookie handler");
			//textField.setText(textField.getText()
					//+ "added cookie using cookie handler");
			// cookieApplet.add(textField);
		} catch (Exception e) {
			//textField.setText(textField.getText()
				//	+ "Unable to set cookie using CookieHandler");

			System.out.println("Unable to set cookie using CookieHandler");
			e.printStackTrace();
		}
	}

}
