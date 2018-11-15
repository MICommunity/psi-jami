package psidev.psi.mi.jami.bridges.uniprot;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.CachedFetcher;
import psidev.psi.mi.jami.model.Gene;

import java.net.URL;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 14/05/13

 */
public class CachedUniprotGeneFetcher
        extends UniprotGeneFetcher
        implements CachedFetcher {

    private final Logger log = Logger.getLogger(CachedUniprotGeneFetcher.class.getName());

    private Cache cache;
    private static CacheManager cacheManager;

    /** Constant <code>EHCACHE_CONFIG_FILE="/service.ehcache.xml"</code> */
    public static final String EHCACHE_CONFIG_FILE = "/service.ehcache.xml";
    /** Constant <code>CACHE_NAME="uniprot-gene-service-cache"</code> */
    public static final String CACHE_NAME = "uniprot-gene-service-cache";

    /**
     * <p>Constructor for CachedUniprotGeneFetcher.</p>
     */
    public CachedUniprotGeneFetcher() {
        super();
        initialiseCache();
    }


    /**
     * <p>getByIdentifier.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<Gene> getByIdentifier(String identifier)
            throws BridgeFailedException{

        final String key = "getGenesByIdentifier#"+identifier;
        Object data = getFromCache( key );
        if( data == null) {
            data = super.fetchByIdentifier(identifier );
            storeInCache(key , data);
        }
        return (Collection<Gene> )data;
    }

    /**
     * <p>getByIdentifier.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     * @param taxID a int.
     * @return a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public Collection<Gene> getByIdentifier(String identifier , int taxID)
            throws BridgeFailedException{

        final String key = "getGenesByIdentifier#"+identifier+"#"+taxID;
        Object data = getFromCache( key );
        if( data == null) {
            data = super.fetchByIdentifier(identifier , taxID);
            storeInCache(key , data);
        }
        return (Collection<Gene> )data;
    }

    /////////////////////////
    // EH CACHE utilities

    /** {@inheritDoc} */
    public Object getFromCache( String key ) {
        Object data = null;
        Element element = cache.get( key );
        if( element != null ){
            data = element.getObjectValue();
        }
        return data;
    }

    /** {@inheritDoc} */
    public void storeInCache( String key, Object data ) {
        Element element = new Element( key, data );
        cache.put( element );
    }

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
        if(! cacheManager.cacheExists(CACHE_NAME))
            cacheManager.addCache(CACHE_NAME);
        this.cache = cacheManager.getCache( CACHE_NAME );
        if( cache == null ) throw new IllegalStateException( "Could not load cache" );
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
