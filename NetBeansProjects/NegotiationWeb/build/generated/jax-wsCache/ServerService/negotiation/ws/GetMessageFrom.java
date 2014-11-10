
package negotiation.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMessageFrom complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMessageFrom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toAgent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageFrom", propOrder = {
    "fromAgent",
    "fromProduct",
    "toAgent",
    "toProduct"
})
public class GetMessageFrom {

    protected String fromAgent;
    protected String fromProduct;
    protected String toAgent;
    protected String toProduct;

    /**
     * Gets the value of the fromAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromAgent() {
        return fromAgent;
    }

    /**
     * Sets the value of the fromAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromAgent(String value) {
        this.fromAgent = value;
    }

    /**
     * Gets the value of the fromProduct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromProduct() {
        return fromProduct;
    }

    /**
     * Sets the value of the fromProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromProduct(String value) {
        this.fromProduct = value;
    }

    /**
     * Gets the value of the toAgent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToAgent() {
        return toAgent;
    }

    /**
     * Sets the value of the toAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToAgent(String value) {
        this.toAgent = value;
    }

    /**
     * Gets the value of the toProduct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToProduct() {
        return toProduct;
    }

    /**
     * Sets the value of the toProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToProduct(String value) {
        this.toProduct = value;
    }

}
