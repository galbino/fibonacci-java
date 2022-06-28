package com.argyle.fibonacci;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class FibonacciController {

    private AsyncCache<Integer, BigInteger> cache;

    FibonacciController() {
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .buildAsync();
    }
    @GetMapping("/fib")
    public BigInteger getFibonacci(@RequestParam(value = "n") int n) {
        if (n < 0) {
            return new BigInteger("0");
        }
        else if (n == 1) {
            return new BigInteger("1");
        }
        CompletableFuture<BigInteger> future = new CompletableFuture<>();
        CompletableFuture<BigInteger> prior = this.cache.asMap().putIfAbsent(n, future);
        if (prior != null) {
            return prior.join();
        }
        BigInteger result = getFibonacci(n-1).add(getFibonacci(n-2));
        future.complete(result);
        return result;
    }
}
