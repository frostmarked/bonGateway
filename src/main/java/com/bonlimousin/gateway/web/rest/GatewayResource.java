package com.bonlimousin.gateway.web.rest;

import com.bonlimousin.gateway.web.rest.vm.RouteVM;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.http.*;
import org.springframework.security.access.annotation.Secured;
import com.bonlimousin.gateway.security.AuthoritiesConstants;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Gateway configuration.
 */
@RestController
@RequestMapping("/api/gateway")
public class GatewayResource {

    private final RouteLocator routeLocator;

    private final DiscoveryClient discoveryClient;
    
    private final CacheManager cacheManager;


    public GatewayResource(RouteLocator routeLocator, DiscoveryClient discoveryClient, CacheManager cacheManager) {
        this.routeLocator = routeLocator;
        this.discoveryClient = discoveryClient;
        this.cacheManager = cacheManager;
    }

    /**
     * {@code GET  /routes} : get the active routes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the list of routes.
     */
    @GetMapping("/routes")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<RouteVM>> activeRoutes() {
        List<Route> routes = routeLocator.getRoutes();
        List<RouteVM> routeVMs = new ArrayList<>();
        routes.forEach(route -> {
            RouteVM routeVM = new RouteVM();
            routeVM.setPath(route.getFullPath());
            routeVM.setServiceId(route.getId());
            routeVM.setServiceInstances(discoveryClient.getInstances(route.getLocation()));
            routeVMs.add(routeVM);
        });
        return ResponseEntity.ok(routeVMs);
    }
    
    /**
     * {@code GET  /cache/clear} : clear caches.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @GetMapping("/cache/clear")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> cacheClear() {
    	cacheManager.getCacheNames().stream()
        	.forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        return ResponseEntity.noContent().build();
    }
}
