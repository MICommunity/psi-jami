package psidev.psi.mi.jami.bridges.uniprot.rest.response.model.feature;

import psidev.psi.mi.jami.bridges.uniprot.rest.response.model.PropertyType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dbReference", propOrder = {
    "property"
})
public class DbReference {

    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected String type;
    @XmlElement(required = true)
    protected PropertyType property;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PropertyType getProperty() {
        return property;
    }

    public void setProperty(PropertyType property) {
        this.property = property;
    }
}
