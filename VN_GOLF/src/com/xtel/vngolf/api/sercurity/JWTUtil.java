package com.xtel.vngolf.api.sercurity;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
    static final Long EXPIRATIONTIME = TimeUnit.HOURS.toMillis(12);
    static final String SECRET = "d24fe5af070ee79754bd17bdac84ca8e";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final String WEBSITE = "http://xtel.vn";
    static final String EMAIL = "abc@xtel.vn";
    static final String COMPANY_NAME = "XTEL.,JSC";

    private static Algorithm algorithm = null;
    private static JWTVerifier verifier = null;


    static {
        try {
            algorithm = Algorithm.HMAC512(SECRET);
            verifier = JWT.require(algorithm).withIssuer(COMPANY_NAME).build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public String getSampleToken(String userData) {
        return JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .withIssuer(COMPANY_NAME)
                .withAudience(WEBSITE)
                .withSubject(EMAIL)
                .withClaim("user_data", userData)
                .sign(algorithm);
    }

    private Date getExpired() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MILLISECOND, EXPIRATIONTIME.intValue());
        return calendar.getTime();
    }

    public String encode(String channel, String userData) {
        return JWT.create().withExpiresAt(getExpired())
                .withIssuer(COMPANY_NAME)
                .withAudience(WEBSITE)
                .withSubject(channel)
                .withClaim("user_data", userData)
                .sign(algorithm);
    }

    public String decodeJWT2(String token) {
    	DecodedJWT decodedJWT = decodeJWT(token);
    	if(decodedJWT == null){
    		return null;
    	}
    	String userData = decodedJWT.getClaim("user_data").asString();
    	return userData;
    }
    
    public String decodeJWT2S(String token) {
        DecodedJWT decodedJWT = decodeJWT(token);
        if (decodedJWT == null) {
            return null;
        }
        Date expireDate = decodedJWT.getExpiresAt();
        if (new Date().after(expireDate)) {
            return null;
        }
        return decodedJWT.getClaim("user_data").asString();
    }

    public DecodedJWT decodeJWT(String token) {
        if (token == null || token.trim().length() == 0) {
            return null;
        }
        try {
            return verifier.verify(token.trim());
        } catch (Exception e) {
            logger.debug("[decodeJWT] Exception: " + e.getMessage());
            return null;
        }
    }

    public boolean validToken(DecodedJWT jwt) {
        if (jwt == null)
            return false;
        Date date = jwt.getExpiresAt();
        return new Date().before(date);
    }

    public boolean validToken(String token) {
        return validToken(decodeJWT(token));
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJodHRwOi8ve" +
                "HRlbC52biIsInN1YiI6IkNNUyIsImlzcyI6IlhURUwuLEpTQyIsInVzZXJZGF0YSI6" +
                "IntcInVzZXJfaWRcIjoyLFwidXNlcl9uYW1lXCI6XCJhZG1pblwiLFwiZnVsbF9uYW1lX" +
                "CI6XCJhZG1pblwiLFwiYmlydGhkYXlcIjpudWxsLFwiZW1haWxcIjpcImFiYzEyMzEyMz" +
                "EyQGdtYWlsLmNvbVwiLFwicGhvbmVfbnVtYmVyXCI6XCIwMzUyNzIwODI1XCIsXCJtb2Jp" +
                "bGVfbnVtYmVyXCI6XCIwMjExNTU1NDQ0XCIsXCJzZXhcIjoxLFwiYWRkcmVzc1wiOlwic-G" +
                "7kSAzMiBkdXkgdMOiblwiLFwidW5pdFwiOlwiYWRtaW5cIixcInN0YXR1c1wiOjEsXCJjcmV" +
                "hdGVfdGltZVwiOlwiMzEvMDEvMjAyMCAxNDo1OTo0MVwiLFwiY3JlYXRlX2J5XCI6bnVsbCxc" +
                "InVwZGF0ZV90aW1lXCI6bnVsbCxcInVwZGF0ZV9ieVwiOm51bGwsXCJzY2hlZHVsZV9pZFwiOjF" +
                "9IiwiZXhwIjoxNTgyMDY1Mjg1fQ.LM8tX4drAze2oFcspRohrM-gftKrEb5u9oh9E_IR3ESKsyA9" +
                "uZXuJhHDrBIhngheOrDW1EDDmi4WNhNkFn-bmA";
        JWTUtil jwtUtils = new JWTUtil();
        jwtUtils.validToken(token);
        DecodedJWT jwt = jwtUtils.decodeJWT(token);
        String user_data = jwtUtils.decodeJWT2S(token);
        System.out.println(jwt);
        System.out.println(user_data);
    }
}