package com.example.apigatewayservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    /**
     * 라우팅 정보 추가
     * http://localhost:8000/first-service/** -> http://localhost:8081/first-service/**
     * http://localhost:8000/second-service/** -> http://localhost:8082/second-service/**
     * 특정 서비스로 라우팅 전 요청 헤더 추가 및 클라이언트로 응답 시 응답 헤더 추가
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/first-service/**")
                                .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                                .addResponseHeader("first-response", "first-response-header"))
                                .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
