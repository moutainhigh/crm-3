package com.hefei.common.http.client;

import org.apache.log4j.Logger;

import com.hefei.common.exception.ClientException;
import com.hefei.common.returncode.ReturnCode;

public class ClientFactory {

	private static Logger logger = Logger.getLogger(ClientFactory.class);
	
	public static Client client;
	public static ServiceUrlConstants serviceUrlConstants;
	
	public static void init(Class clientClazz, String clientFileName, Class urlConstantsClazz, String urlFileName) throws ClientException{
		
		try {
			if(clientClazz!=null){
				client = (Client) clientClazz.newInstance();
				client.init(clientFileName);
			}else{
				throw new ClientException(ReturnCode.RETURN_CODE_ERROR_CLIENT);
			}
				
			if(urlConstantsClazz!=null){
				serviceUrlConstants = (ServiceUrlConstants) urlConstantsClazz.newInstance();
				serviceUrlConstants.init(urlFileName);
			}else{
				throw new ClientException(ReturnCode.RETURN_CODE_ERROR_CLIENT);
			}	
				
		} catch (InstantiationException e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_CLIENT);
		} catch (IllegalAccessException e) {
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_CLIENT);
		} catch(Exception e){
			logger.error("error", e);
			throw new ClientException(ReturnCode.RETURN_CODE_ERROR_CLIENT);
		}
	}
	
}
