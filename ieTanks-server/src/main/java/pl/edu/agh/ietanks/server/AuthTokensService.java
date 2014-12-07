package pl.edu.agh.ietanks.server;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthTokensService {
    private static final String tokenSecret = "aghtwoietanks";
    private static final JWSHeader jwtHeader = new JWSHeader(JWSAlgorithm.HS256);

    private Map<String, String> tokensToUsersDic = new HashMap<String, String>();

    public String generateToken(String host, String sub, String userId) throws JOSEException {
        JWTClaimsSet claim = new JWTClaimsSet();
        claim.setSubject(sub);
        claim.setIssuer(host);
        claim.setExpirationTime(DateTime.now().plusDays(1).toDate());

        JWSSigner signer = new MACSigner(tokenSecret);
        SignedJWT jwt = new SignedJWT(jwtHeader, claim);
        jwt.sign(signer);

        String generatedToken = jwt.serialize();
        tokensToUsersDic.put(generatedToken, userId);

        return generatedToken;
    }
}
