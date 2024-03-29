package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.VariableParameter;
import psidev.psi.mi.jami.model.VariableParameterValue;
import psidev.psi.mi.jami.model.VariableParameterValueSet;
import psidev.psi.mi.jami.utils.comparator.experiment.VariableParameterValueSetComparator;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.AbstractXmlIdReference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * XML 3.0 implementation of variable parameter value set
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/05/14</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlVariableParameterValueSet implements VariableParameterValueSet,FileSourceContext,Locatable {

    private PsiXmlLocator sourceLocator;

    @XmlLocation
    @XmlTransient
    private Locator locator;
    private JAXBVariableValueRefList jaxbVariableValueRefList;

    /**
     * <p>Constructor for XmlVariableParameterValueSet.</p>
     */
    public XmlVariableParameterValueSet(){
        initialiseVatiableParameterValuesSet();
    }

    /**
     * <p>initialiseVatiableParameterValuesSet.</p>
     */
    protected void initialiseVatiableParameterValuesSet(){
        this.jaxbVariableValueRefList = new JAXBVariableValueRefList();
    }

    /**
     * <p>size.</p>
     *
     * @return a int.
     */
    public int size() {
        return jaxbVariableValueRefList.variableParameterValues.size();
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return jaxbVariableValueRefList.variableParameterValues.isEmpty();
    }

    /** {@inheritDoc} */
    public boolean contains(Object o) {
        return jaxbVariableValueRefList.variableParameterValues.contains(o);
    }

    /**
     * <p>iterator.</p>
     *
     * @return a {@link java.util.Iterator} object.
     */
    public Iterator<VariableParameterValue> iterator() {
        return jaxbVariableValueRefList.variableParameterValues.iterator();
    }

    /**
     * <p>toArray.</p>
     *
     * @return an array of {@link java.lang.Object} objects.
     */
    public Object[] toArray() {
        return jaxbVariableValueRefList.variableParameterValues.toArray();
    }

    /**
     * <p>toArray.</p>
     *
     * @param ts an array of T objects.
     * @param <T> a T object.
     * @return an array of T objects.
     */
    public <T> T[] toArray(T[] ts) {
        return jaxbVariableValueRefList.variableParameterValues.toArray(ts);
    }

    /**
     * <p>add.</p>
     *
     * @param variableParameterValue a {@link psidev.psi.mi.jami.model.VariableParameterValue} object.
     * @return a boolean.
     */
    public boolean add(VariableParameterValue variableParameterValue) {
        return jaxbVariableValueRefList.variableParameterValues.add(variableParameterValue);
    }

    /** {@inheritDoc} */
    public boolean remove(Object o) {
        return jaxbVariableValueRefList.variableParameterValues.remove(o);
    }

    /** {@inheritDoc} */
    public boolean containsAll(Collection<?> objects) {
        return jaxbVariableValueRefList.variableParameterValues.containsAll(objects);
    }

    /** {@inheritDoc} */
    public boolean addAll(Collection<? extends VariableParameterValue> variableParameterValues) {
        return this.jaxbVariableValueRefList.variableParameterValues.addAll(variableParameterValues);
    }

    /** {@inheritDoc} */
    public boolean retainAll(Collection<?> objects) {
        return jaxbVariableValueRefList.variableParameterValues.retainAll(objects);
    }

    /** {@inheritDoc} */
    public boolean removeAll(Collection<?> objects) {
        return jaxbVariableValueRefList.variableParameterValues.removeAll(objects);
    }

    /**
     * <p>clear.</p>
     */
    public void clear() {
        jaxbVariableValueRefList.variableParameterValues.clear();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof VariableParameterValueSet)){
            return false;
        }

        return VariableParameterValueSetComparator.areEquals(this, (VariableParameterValueSet) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return VariableParameterValueSetComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /**
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /**
     * <p>getJAXBVariableValueRefs.</p>
     *
     * @return a {@link java.util.Set} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "variableValueRef", type = Integer.class, required = true)
    public Set<Integer> getJAXBVariableValueRefs() {
        return this.jaxbVariableValueRefList;
    }

    //////////////////////////////////////////////////////////////
    /**
     * The variable value ref list used by JAXB to populate variable value refs
     */
    private class JAXBVariableValueRefList extends HashSet<Integer>{
        private Set<VariableParameterValue> variableParameterValues;

        public JAXBVariableValueRefList(){
            super();
            variableParameterValues = new HashSet<VariableParameterValue>();
        }

        @Override
        public boolean add(Integer val) {
            if (val == null){
                return false;
            }
            return variableParameterValues.add(new VariableParameterValueRef(val));
        }

        @Override
        public boolean addAll(Collection<? extends Integer> c) {
            if (c == null){
                return false;
            }
            boolean added = false;

            for (Integer a : c){
                if (add(a)){
                    added = true;
                }
            }
            return added;
        }

        @Override
        public String toString() {
            return "Variable parameter values: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }

        ////////////////////////////////////////////////////// classes
        /**
         * Variable value ref for variable parameter value
         */
        private class VariableParameterValueRef extends AbstractXmlIdReference implements VariableParameterValue {
            private final Logger logger = Logger.getLogger("XmlVariableParameterValueSet");
            private VariableParameterValue delegate;

            public VariableParameterValueRef(int ref) {
                super(ref);
            }

            public boolean resolve(PsiXmlIdCache parsedObjects) {
                if (parsedObjects.containsVariableParameter(this.ref)){
                    VariableParameterValue obj = parsedObjects.getVariableParameterValue(this.ref);
                    if (obj != null){
                        variableParameterValues.remove(this);
                        variableParameterValues.add(obj);
                        return true;
                    }

                }
                return false;
            }

            @Override
            public String toString() {
                return "Variable parameter value Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
            }

            public FileSourceLocator getSourceLocator() {
                return sourceLocator;
            }

            public void setSourceLocator(FileSourceLocator locator) {
                throw new UnsupportedOperationException("Cannot set the source locator of a variable parameter value ref");
            }

            @Override
            public String getValue() {
                logger.log(Level.WARNING, "The variable parameter value reference "+ref+" is not resolved. Some default properties will be initialised by default");
                if (this.delegate == null){
                    initialiseParameterValueDelegate();
                }
                return this.delegate.getValue();
            }

            @Override
            public Integer getOrder() {
                logger.log(Level.WARNING, "The variable parameter value reference "+ref+" is not resolved. Some default properties will be initialised by default");
                if (this.delegate == null){
                    initialiseParameterValueDelegate();
                }
                return this.delegate.getOrder();
            }

            @Override
            public VariableParameter getVariableParameter() {
                logger.log(Level.WARNING, "The variable parameter value reference "+ref+" is not resolved. Some default properties will be initialised by default");
                if (this.delegate == null){
                    initialiseParameterValueDelegate();
                }
                return this.delegate.getVariableParameter();
            }

            private void initialiseParameterValueDelegate() {
                this.delegate = new XmlVariableParameterValue();
            }
        }
    }
}
