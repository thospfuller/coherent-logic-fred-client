package com.coherentlogic.fred.client.webstart.application;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;

import com.coherentlogic.infinispan.cache.providers.AbstractInfinispanCacheServiceProviderFactory;

/**
 * Defines an Infinispan cache manager with the cache mode set to DIST_SYNC (distributed sync) and 
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class InfinispanCacheServiceProviderFactory extends AbstractInfinispanCacheServiceProviderFactory {

    public static final String DEFAULT_FRED_CACHE_NAME = "fredCache";

    public InfinispanCacheServiceProviderFactory() {
        this (DEFAULT_FRED_CACHE_NAME);
    }

    public InfinispanCacheServiceProviderFactory(String defaultCacheName) {
        super(defaultCacheName);
    }

    @Override
    protected GlobalConfiguration defineGlobalConfiguration() {

        return
            new GlobalConfigurationBuilder()
                .transport()
                .defaultTransport()
                .build();
    }

    @Override
    protected Configuration defineConfiguration(Configuration defaultCacheConfiguration) {

        return
            new ConfigurationBuilder()
                .read(defaultCacheConfiguration)
                .jmxStatistics()
                .clustering()
                .cacheMode(CacheMode.DIST_SYNC)
                .l1()
                .lifespan(60000L * 3L) // Three minutes
                .build();
    }
}
