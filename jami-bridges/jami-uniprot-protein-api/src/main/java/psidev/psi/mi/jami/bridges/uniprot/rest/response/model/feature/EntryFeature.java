package psidev.psi.mi.jami.bridges.uniprot.rest.response.model.feature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entryFeature", propOrder = {
    "accession",
    "feature"
})
@XmlRootElement(name = "entryFeature")
public class EntryFeature {

    @XmlElement(required = true)
    protected String accession;
    @XmlElement(required = true)
    protected List<Feature> feature;

    public String getAccession() {
        return accession;
    }

    public void setAccession(String value) {
        this.accession = value;
    }

    public List<Feature> getFeature() {
        if (feature == null) {
            feature = new ArrayList<>();
        }
        return this.feature;
    }
}
