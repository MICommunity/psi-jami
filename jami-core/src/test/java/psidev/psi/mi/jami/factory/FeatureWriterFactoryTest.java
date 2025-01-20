package psidev.psi.mi.jami.factory;

import org.junit.Test;
import psidev.psi.mi.jami.datasource.FeatureWriter;
import psidev.psi.mi.jami.datasource.MockFeatureWriter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeatureWriterFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void register_writer_no_give_class(){
        FeatureWriterFactory.getInstance().registerDataSourceWriter(null, Collections.emptyMap());
    }

    @Test
    public void test_missing_output_options() {
        FeatureWriterFactory.getInstance().registerDataSourceWriter(MockFeatureWriter.class, Collections.emptyMap());

        FeatureWriter writer = FeatureWriterFactory.getInstance().getFeatureWriterWith(new HashMap<>());
        assertNull(writer);
    }

    @Test
    public void test_no_supported_options() {
        FeatureWriterFactory.getInstance().registerDataSourceWriter(MockFeatureWriter.class, Collections.emptyMap());

        Map<String, Object> requiredOptions = new HashMap<>();
        requiredOptions.put("output_key", "test");

        FeatureWriter writer = FeatureWriterFactory.getInstance().getFeatureWriterWith(requiredOptions);
        assertNull(writer);
    }

    @Test
    public void test_supported_options() {
        Map<String, Object> possibleOptions = new HashMap<>();
        possibleOptions.put("output_key", "test");

        FeatureWriterFactory.getInstance().registerDataSourceWriter(MockFeatureWriter.class, possibleOptions);

        Map<String, Object> requiredOptions = new HashMap<>();
        requiredOptions.put("output_key", "test");

        FeatureWriter writer = FeatureWriterFactory.getInstance().getFeatureWriterWith(requiredOptions);
        assertNotNull(writer);
    }
}
