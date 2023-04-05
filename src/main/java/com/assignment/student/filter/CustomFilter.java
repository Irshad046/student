package com.assignment.student.filter;

import com.assignment.student.util.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.trace("Method name is : "+request.getMethod());
        if(Constants.GET.equalsIgnoreCase(request.getMethod())){
            log.trace("This is the point where we have intercepted the GET request and based upon API, we can get RequestParams or PathVariables");
            log.trace("If there are issues with length, regex or anything, we can throw a runtime exception from this point to break the execution");
        }
        filterChain.doFilter(request, response);


    }
}
