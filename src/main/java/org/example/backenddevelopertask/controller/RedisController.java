package org.example.backenddevelopertask.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.backenddevelopertask.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache/user")
@RequiredArgsConstructor
public class RedisController {
    private final RedisService redisService;

    @PostMapping
    public ResponseEntity<String> cacheUser(@RequestBody CacheRequest request) {
        redisService.cacheUser(request.getEmail(), request.getFullName());
        return ResponseEntity.ok("User cached for 10 minutes");
    }

    @GetMapping("/{email}")
    public ResponseEntity<String> getUser(@PathVariable String email) {
        String fullName = redisService.getUser(email);
        if (fullName == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fullName);
    }

    @Data
    public static class CacheRequest {
        private String email;
        private String fullName;
    }
}
