package psidev.psi.mi.jami.xml.model;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An XML entry is associated with a set of interactions, experiments, source and attributes
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/10/13</pre>
 */
@XmlTransient
public class Entry {

    private ExtendedPsiXmlSource source;
    private List<Annotation> annotations;
    private List<AbstractAvailability> availabilities;
    private boolean hasLoadedFullEntry = false;

    /**
     * <p>Constructor for Entry.</p>
     */
    public Entry(){
    }

    /**
     * <p>Getter for the field <code>source</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource} object.
     */
    public ExtendedPsiXmlSource getSource() {
        return source;
    }

    /**
     * <p>Setter for the field <code>source</code>.</p>
     *
     * @param source a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource} object.
     */
    public void setSource(ExtendedPsiXmlSource source) {
        this.source = source;
    }

    /**
     * <p>Getter for the field <code>availabilities</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<AbstractAvailability> getAvailabilities() {
        if (availabilities == null){
            initialiseAvailabilities();
        }
        return availabilities;
    }

    /**
     * <p>Getter for the field <code>annotations</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Annotation> getAnnotations() {
        if (annotations == null){
            initialiseAnnotations();
        }
        return annotations;
    }

    /**
     * <p>hasLoadedFullEntry.</p>
     *
     * @return a boolean.
     */
    public boolean hasLoadedFullEntry(){
        return this.hasLoadedFullEntry;
    }

    /**
     * <p>Setter for the field <code>hasLoadedFullEntry</code>.</p>
     *
     * @param hasLoadedFullEntry a boolean.
     */
    public void setHasLoadedFullEntry(boolean hasLoadedFullEntry) {
        this.hasLoadedFullEntry = hasLoadedFullEntry;
    }

    /**
     * <p>initialiseAvailabilities.</p>
     */
    protected void initialiseAvailabilities() {
        availabilities = new ArrayList<AbstractAvailability>();
    }

    /**
     * <p>initialiseAnnotations.</p>
     */
    protected void initialiseAnnotations() {
        annotations = new ArrayList<Annotation>();
    }

    /**
     * <p>initialiseAvailabilitiesWith.</p>
     *
     * @param availabilities a {@link java.util.List} object.
     */
    protected void initialiseAvailabilitiesWith(List<AbstractAvailability> availabilities) {
        if (availabilities == null){
            this.availabilities = Collections.EMPTY_LIST;
        }
        else{
           this.availabilities = availabilities;
        }
    }

    /**
     * <p>initialiseAnnotationsWith.</p>
     *
     * @param annotations a {@link java.util.List} object.
     */
    protected void initialiseAnnotationsWith(List<Annotation> annotations) {
        if (annotations == null){
            this.annotations = Collections.EMPTY_LIST;
        }
        else{
            this.annotations = annotations;
        }
    }
}
