package com.example.api_gateway.filter;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RateLimitFilter implements GlobalFilter,Ordered{
	
	private ConcurrentHashMap<String, Integer>
	requestCount = new ConcurrentHashMap<>();

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, 
			GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		String ip = exchange.getRequest().getRemoteAddress()
				.getHostString();
		requestCount.put(ip, requestCount.getOrDefault(ip, 0)+1);
		if (requestCount.get(ip)>100) {
			exchange.getResponse().setStatusCode(HttpStatus
					.TOO_MANY_REQUESTS);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

}
