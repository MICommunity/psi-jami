package psidev.psi.mi.jami.bridges.fetcher;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * Abstract class for fetchers
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/09/13</pre>
 */
public abstract class AbstractCachedFetcher implements CachedFetcher{

    private Cache cache;
    private static CacheManager cacheManager;

    /** Constant <code>EHCACHE_CONFIG_FILE="/service.ehcache.xml"</code> */
    public static final String EHCACHE_CONFIG_FILE = "/service.ehcache.xml";
    private String cacheName;

    /**
     * <p>Constructor for AbstractCachedFetcher.</p>
     *
     * @param cacheName a {@link java.lang.String} object.
     */
    public AbstractCachedFetcher(String cacheName) {
        if (cacheName == null){
            throw new IllegalArgumentException("The name of the cache is mandatory");
        }
        this.cacheName = cacheName;
        initialiseCache();
    }

    /////////////////////////
    // EH CACHE utilities

    /**
     * <p>initialiseCache.</p>
     */
    public void initialiseCache() {
        initialiseCache( EHCACHE_CONFIG_FILE );
    }

    /** {@inheritDoc} */
    public void initialiseCache(String settingsFile) {
        URL url = getClass().getResource( settingsFile );
        cacheManager =  CacheManager.create( url );
        if(! cacheManager.cacheExists(cacheName))
            cacheManager.addCache(cacheName);
        this.cache = cacheManager.getCache(cacheName);
        if( cache == null ) throw new IllegalStateException( "Could not load cache" );
    }


    /** {@inheritDoc} */
    public Object getFromCache( String key ) {
        Object data = null;
        Element element = cache.get( key );
        if( element != null ){
            //if( log.isTraceEnabled() ) log.trace("getting key: "+key);
            data = element.getObjectValue();
        }
        return data;
    }

    /** {@inheritDoc} */
    public void storeInCache( String key, Object data ) {
        //if( log.isTraceEnabled() ) log.trace("storing key: "+key);
        Element element = new Element( key, data );
        cache.put( element );
    }

    /**
     * <p>clearCache.</p>
     */
    public void clearCache() {
        cacheManager.clearAll();
    }

    /**
     * <p>shutDownCache.</p>
     */
    public void shutDownCache() {
        cacheManager.shutdown();
    }
}
