package com.example.UserAccountService.filters;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class JSONFilter implements GlobalFilter {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                // TODO Auto-generated method stub

                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                System.out.println(authHeader);
                // prints the authheader with correct token after the Authorization is successful

                if(authHeader == null || !authHeader.startsWith("Bearer ")) {

                        String errorMessage = "Missing Authorization Header or Authorization Header contains invalid token";

                        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
                        DataBuffer buffer = bufferFactory.wrap(errorMessage.getBytes());
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().writeWith(Flux.just(buffer));
                        // returns exchange value depends on token


                }

                String token = authHeader.split(" ")[1];
                try {
                        Jwts.parser()
                        .setSigningKey("stackroute")
                        .parseClaimsJws(token)
                        .getBody();
                        //Token Verification Successful
                }catch(Exception e) {
                        String errorMessage = "Token Verification Failed";
                        //Token Verification Failed
                        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
                        DataBuffer buffer = bufferFactory.wrap(errorMessage.getBytes());
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().writeWith(Flux.just(buffer));
                        //returns exchange based on token value
                }

                return chain.filter(exchange);
        }

}