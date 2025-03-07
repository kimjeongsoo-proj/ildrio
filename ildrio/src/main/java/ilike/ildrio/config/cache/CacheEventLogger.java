package ilike.ildrio.config.cache;

import lombok.extern.log4j.Log4j2;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Log4j2
public class CacheEventLogger implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(
            CacheEvent<?, ?> cacheEvent) {
        log.info("cache event logger message. getKey: {} / getOldValue: {} / getNewValue:{}", cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}
