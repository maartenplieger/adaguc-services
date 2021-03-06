package nl.knmi.adaguc.services.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.knmi.adaguc.security.PemX509Tools;
import nl.knmi.adaguc.security.PemX509Tools.X509Info;
import nl.knmi.adaguc.tools.JSONResponse;

@RestController
public class AuthRequestMapper {
	@ResponseBody
	@RequestMapping(
			path="user/getuserinfofromcert",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void getUserInfoFromX509Cert(HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException{
		X509Info userId = (new PemX509Tools()).getUserIdFromCertificate(request);
		JSONResponse jsonResponse = new JSONResponse(request);
		if(userId!=null){
			jsonResponse.setMessage(new JSONObject().put("id",userId.getCN()).put("cn",userId.getCN()).put("uniqueid",userId.getUniqueId()));

		}else{
			jsonResponse.setMessage(new JSONObject().put("error","No user info found in cert"));
		}
		jsonResponse.print(response);

	}
	
	
	
	@RequestMapping(
			path="user/makeuserinforequest",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void makeUserInfoRequest(HttpServletResponse response, HttpServletRequest request) throws JSONException, IOException{
			//String clientId = new AuthenticatorImpl(request).getClientId();
			
	}
}
