package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultAlias;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A container for aliases, shortname and fullname
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ComplexNamesContainer extends NamesContainer {

    private Alias recommendedName;
    private Alias systematicName;

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Complex Names: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }

    /**
     * <p>Getter for the field <code>recommendedName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRecommendedName() {
        return this.recommendedName != null ? this.recommendedName.getName() : null;
    }

    /**
     * <p>Setter for the field <code>recommendedName</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setRecommendedName(String name) {
        ComplexAliasList complexAliasList = (ComplexAliasList)getAliases();

        // add new recommended name if not null
        if (name != null){

            CvTerm recommendedName = CvTermUtils.createComplexRecommendedName();
            // first remove old recommended name if not null
            if (this.recommendedName != null){
                complexAliasList.removeOnly(this.recommendedName);
            }
            this.recommendedName = new DefaultAlias(recommendedName, name);
            complexAliasList.addOnly(this.recommendedName);
        }
        // remove all recommended name if the collection is not empty
        else if (!complexAliasList.isEmpty()) {
            AliasUtils.removeAllAliasesWithType(complexAliasList, Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
            recommendedName = null;
        }
    }

    /**
     * <p>Getter for the field <code>systematicName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSystematicName() {
        return this.systematicName != null ? this.systematicName.getName() : null;
    }

    /**
     * <p>Setter for the field <code>systematicName</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setSystematicName(String name) {
        ComplexAliasList complexAliasList = (ComplexAliasList)getAliases();

        // add new systematic name if not null
        if (name != null){

            CvTerm systematicName = CvTermUtils.createComplexSystematicName();
            // first remove systematic name  if not null
            if (this.systematicName != null){
                complexAliasList.removeOnly(this.systematicName);
            }
            this.systematicName = new DefaultAlias(systematicName, name);
            complexAliasList.addOnly(this.systematicName);
        }
        // remove all systematic name  if the collection is not empty
        else if (!complexAliasList.isEmpty()) {
            AliasUtils.removeAllAliasesWithType(complexAliasList, Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
            systematicName = null;
        }
    }

    private void processAddedAliasEvent(Alias added) {
        if (recommendedName == null && AliasUtils.doesAliasHaveType(added, Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME)){
            recommendedName = added;
        }
        else if (systematicName == null && AliasUtils.doesAliasHaveType(added, Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME)){
            systematicName = added;
        }
    }

    private void processRemovedAliasEvent(Alias removed) {
        if (recommendedName != null && recommendedName.equals(removed)){
            recommendedName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        }
        else if (systematicName != null && systematicName.equals(removed)){
            systematicName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        }
    }

    private void clearPropertiesLinkedToAliases() {
        this.recommendedName = null;
        this.systematicName = null;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAliases(){
        super.initialiseAliasesWith(new ComplexAliasList());
    }

    private class ComplexAliasList extends AbstractListHavingProperties<Alias> {
        public ComplexAliasList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Alias added) {
            processAddedAliasEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Alias removed) {
            processRemovedAliasEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToAliases();
        }
    }
}
