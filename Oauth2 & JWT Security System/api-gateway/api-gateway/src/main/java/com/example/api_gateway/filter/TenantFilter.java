package com.example.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class TenantFilter implements GlobalFilter,Ordered{

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
//		String tenantId = exchange.getRequest().getHeaders().getFirst("Tenant-Id");
//		if(tenantId == null) {
//			exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//			return exchange.getResponse().setComplete();
//		}
		return chain.filter(exchange);
	}

	
}
