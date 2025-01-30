package psidev.psi.mi.jami.factory;

import psidev.psi.mi.jami.datasource.FeatureWriter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A factory for FeatureWriter
 */
public class FeatureWriterFactory {

    private static final FeatureWriterFactory instance = new FeatureWriterFactory();
    private static final Logger logger = Logger.getLogger("FeatureWriterFactory");

    private final Map<Class<? extends FeatureWriter>, Map<String, Object>> registeredWriters;

    private FeatureWriterFactory(){
        registeredWriters = new ConcurrentHashMap<>();
    }

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link FeatureWriterFactory} object.
     */
    public static FeatureWriterFactory getInstance() {
        return instance;
    }

    /**
     * Register a datasource writer with options in this factory
     *
     * @param dataSourceClass : dataSource class to register
     * @param supportedOptions : options supported by this dataSource
     */
    public void registerDataSourceWriter(Class<? extends FeatureWriter> dataSourceClass, Map<String,Object> supportedOptions){
        if (dataSourceClass == null){
            throw new IllegalArgumentException("Cannot register a FeatureWriter without a featureDataSourceWriterClass");
        }

        registeredWriters.put(dataSourceClass, supportedOptions != null ? supportedOptions : new ConcurrentHashMap<>());
    }

    /**
     * Remove the featureDataSourceWriter from this factory
     *
     * @param dataSourceClass : the dataSource class
     */
    public void removeDataSourceWriter(Class<? extends FeatureWriter> dataSourceClass){
        registeredWriters.remove(dataSourceClass);
    }

    /**
     * Clear all the registered writers from this factory
     */
    public void clearRegisteredDataSourceWriters(){
        registeredWriters.clear();
    }

    /**
     * <p>getFeatureWriterWith</p>
     *
     * @param requiredOptions : options for initialising writer
     * @return the registered FeatureWriter supporting this options, null if nothing could be found
     */
    public FeatureWriter getFeatureWriterWith(Map<String,Object> requiredOptions) {

        for (Map.Entry<Class<? extends FeatureWriter>, Map<String, Object>> entry : registeredWriters.entrySet()){
            // we check for a DataSource that can be used with the given options
            if (areSupportedOptions(entry.getValue(), requiredOptions)){
                try {
                    return instantiateNewWriter(entry.getKey(), requiredOptions);
                } catch (IllegalAccessException e) {
                    logger.log(Level.SEVERE, "We cannot instantiate feature writer of type " + entry.getKey() + " with the given options.", e);
                } catch (InstantiationException e) {
                    logger.log(Level.SEVERE, "We cannot instantiate feature writer of type " + entry.getKey() + " with the given options.", e);
                }  catch (Exception e) {
                    logger.log(Level.WARNING, "We cannot instantiate feature writer of type " + entry.getKey() + " with the given options.", e);
                }
            }
        }

        return null;
    }

    private FeatureWriter instantiateNewWriter(Class<? extends FeatureWriter> dataSourceClass, Map<String, Object> options) throws IllegalAccessException, InstantiationException {
        FeatureWriter dataSource = dataSourceClass.newInstance();
        dataSource.initialiseContext(options);

        return dataSource;
    }

    /**
     * By default, all the options in the given options should be in the supportedOptions of this
     * registeredDataSourceWriter.
     * @param supportedOptions options supported
     * @param options that are required
     * @return true if the options are supported false otherwise
     */
    private boolean areSupportedOptions(Map<String, Object> supportedOptions, Map<String, Object> options) {
        // no required options
        if (options == null || options.isEmpty()) {
            return true;
        }

        // check if required options are supported
        for (Map.Entry<String, Object> entry : options.entrySet()) {
            if (supportedOptions.containsKey(entry.getKey())) {
                Object result = supportedOptions.get(entry.getKey());
                if ( result != null && !result.equals(entry.getValue())) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }
}
