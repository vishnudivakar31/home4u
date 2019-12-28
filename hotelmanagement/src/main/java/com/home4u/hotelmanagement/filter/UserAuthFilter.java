package com.home4u.hotelmanagement.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home4u.hotelmanagement.models.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.home4u.hotelmanagement.utils.Constants.APPUSER_TAG;
import static com.home4u.hotelmanagement.utils.Constants.HEADER_NAME;

@Component
public class UserAuthFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(UserAuthFilter.class);

    @Value("${usermanagement.url}")
    private String userManagementUrl;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getHeader(HEADER_NAME);
        headers.set(HEADER_NAME, token);
        HttpEntity entity = new HttpEntity(headers);
        try {
            ResponseEntity<Map> userMap = template.exchange(userManagementUrl, HttpMethod.GET, entity, Map.class, new HashMap<String, String>());
            request.setAttribute(APPUSER_TAG, userMap.getBody());
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            ((HttpServletResponse) servletResponse).sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
