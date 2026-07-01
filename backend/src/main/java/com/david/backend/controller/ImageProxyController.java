package com.david.backend.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final List<String> ALLOWED_HOSTS = List.of(
            "images.nintendolife.com",
            "images.pushsquare.com",
            "images.purexbox.com");

    @CrossOrigin(origins = { "${app.cors.allowed-origins}" })
    @GetMapping("/proxy")
    public ResponseEntity<byte[]> proxyImage(@RequestParam String url) {
        URI uri = URI.create(url);
        String host = uri.getHost();

        if (host == null || ALLOWED_HOSTS.stream().noneMatch(host::equalsIgnoreCase)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);

        MediaType contentType = response.getHeaders().getContentType();
        if (contentType == null) {
            contentType = MediaType.IMAGE_JPEG;
        }

        return ResponseEntity.ok()
                .contentType(contentType)
                .cacheControl(CacheControl.maxAge(java.time.Duration.ofDays(7)))
                .body(response.getBody());
    }
}
