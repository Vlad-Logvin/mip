package by.logvin.mip.security.jwt;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionHandling {

    public void handleJwtException(String message, Integer statusCode, HttpServletResponse response)
            throws IOException {
        if (!response.containsHeader("Handled")) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream()
                    .println("{ \"errorMessage\": \"" + message + "\", " + "\"errorCode\": " + statusCode + " }");
            response.addHeader("Handled", "TRUE");
        }
    }
}
