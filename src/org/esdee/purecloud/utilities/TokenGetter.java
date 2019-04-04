package org.esdee.purecloud.utilities;
import java.io.IOException;
import java.util.Scanner;
import com.mypurecloud.sdk.v2.ApiClient;
import com.mypurecloud.sdk.v2.ApiException;
import com.mypurecloud.sdk.v2.ApiResponse;
import com.mypurecloud.sdk.v2.connector.ApiClientConnectorProperty;
import com.mypurecloud.sdk.v2.connector.okhttp.OkHttpClientConnectorProvider;
import com.mypurecloud.sdk.v2.extensions.AuthResponse;


public class TokenGetter {
		
	public static String purecloudToken() throws IOException, ApiException {
		String token = "";
		String[] pcParams = ArgumentGetter.PureCloudConn();
		ApiClient apiClient = ApiClient.Builder.standard()
				.withBasePath(pcParams[2])
				.withProperty(ApiClientConnectorProperty.CONNECTOR_PROVIDER, new OkHttpClientConnectorProvider())
				.build();
		
		ApiResponse<AuthResponse> authResponse = apiClient.authorizeClientCredentials(pcParams[0], pcParams[1]);
		
		String response = authResponse.getBody().toString();
		Scanner scanner = new Scanner(response);
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if(line.contains("access_token")) {
				token = line.substring(line.indexOf("=") + 1, line.length());
			}
		}
		scanner.close();
		return token;
	}
	
	public static String herokuToken() {
		String token = "";
		// TODO add code for getting token from heroku app
		return token;
	}
}
