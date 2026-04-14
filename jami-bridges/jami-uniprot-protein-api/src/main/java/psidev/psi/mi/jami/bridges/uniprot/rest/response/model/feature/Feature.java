package psidev.psi.mi.jami.bridges.uniprot.rest.response.model.feature;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "feature", propOrder = {
    "dbReference"
})
public class Feature {

    @XmlAttribute(required = true)
    protected String type;
    @XmlElement(required = true)
    protected List<DbReference> dbReference;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DbReference> getDbReference() {
        if (dbReference == null) {
            dbReference = new ArrayList<>();
        }
        return this.dbReference;
    }
}
