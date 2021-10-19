package ar.edu.unq.desapp.grupoD022021.backenddesappapi.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
public class CachingConfig {


    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cryptoassets");
    }
}