package com.patel.APIgateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.patel.APIgateway.service.ImpJwtService;

import jakarta.ws.rs.core.HttpHeaders;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private ImpJwtService jwt;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	public static class Config {
        
    }

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorization header");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeader != null && authHeader.startsWith("Bearer ")) {
				
					try {
						boolean valid = jwt.validateToken(authHeader.substring(7));
						
						if(!valid) {
							throw new RuntimeException("Username or password is not correct.");
						}
						
						exchange.getRequest().mutate().headers(header -> header.set(HttpHeaders.AUTHORIZATION, authHeader));
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Username or password is not correct.");
					}
				}
			}
			return chain.filter(exchange);
		};
	}
}
