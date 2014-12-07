package pl.edu.agh.ietanks.server;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import java.io.IOException;
import java.util.Map;

@RestController
public class AuthController {
    private static final String googleClientSecret = "UiyPaGZURKbzQPsjKama9bKb";
    private static final String grantType = "authorization_code";
    private static final String googleAccessTokenUrl = "https://accounts.google.com/o/oauth2/token";
    private static final String googlePeopleApiUrl = "https://www.googleapis.com/plus/v1/people/me/openIdConnect";
    private static final String authHeaderKey = "Authorization";
    private static final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/auth/google", method = RequestMethod.POST)
    public void loginWithGoogle(@RequestBody AuthResult authResult) throws Exception {
        MultivaluedMap<String, String> accessData = new MultivaluedMapImpl();
        accessData.add("client_id", authResult.clientId);
        accessData.add("redirect_uri", authResult.redirectUri);
        accessData.add("client_secret", googleClientSecret);
        accessData.add("code", authResult.code);
        accessData.add("grant_type", grantType);

        Client client = new Client();
        ClientResponse response;

        response = client.resource(googleAccessTokenUrl).entity(accessData).post(ClientResponse.class);
        accessData.clear();

        String accessToken = (String) getResponseEntity(response).get("access_token");
        response = client.resource(googlePeopleApiUrl).header(authHeaderKey, String.format("Bearer %s", accessToken)).get(ClientResponse.class);
        Map<String, Object> userInfo = getResponseEntity(response);

        // TODO: Processing user data
    }

    private Map<String, Object> getResponseEntity(ClientResponse response) throws JsonParseException, JsonMappingException, ClientHandlerException, UniformInterfaceException, IOException {
        return mapper.readValue(response.getEntity(String.class),
                new TypeReference<Map<String, Object>>() {
                });
    }
}
