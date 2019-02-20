/**
 * 
 */
package com.cloudsuperman.psapp14;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.servlet.http.HttpServletRequest;

import java.net.URL;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.BufferedReader;

/**
 * @author buntu
 *
 */
@Path("/resource")
public class PSApp14 {
	
	@GET
	@Path("/IPAddress")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetIPAddress(@Context HttpServletRequest requestContext) {
		
		//Extract IP Address from HTTP Request
		
		String strIPAddress = requestContext.getRemoteAddr().toString();
		
		//requestContext.getRemoteHost();
		
		String strRemoteHost = requestContext.getRemoteHost();
		
		//Convert IP address to location
		
		//String strURL = "http://extreme-ip-lookup.com/json/" + strRemoteHost;
		
		//String strURL = "http://ip-api.com/json/" + strRemoteHost;
		
		String strURL = "http://ip-api.com/json/71.231.182.224";
		
		URL url = null;
		
		try {
			url = new URL(strURL);		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpURLConnection conn=null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//String strJSON = con.toString();
		
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream())
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder result = new StringBuilder();
	    String line= strRemoteHost;
	    try {
			while ((line = rd.readLine()) != null) {
			     result.append(line);
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
			rd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result.toString();
	}


}
