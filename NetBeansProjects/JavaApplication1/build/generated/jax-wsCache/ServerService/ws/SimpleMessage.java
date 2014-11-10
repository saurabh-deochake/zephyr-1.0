
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for simpleMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="simpleMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="from" type="{http://ws.negotiation/}candidate" minOccurs="0"/>
 *         &lt;element name="to" type="{http://ws.negotiation/}candidate" minOccurs="0"/>
 *         &lt;element name="type" type="{http://ws.negotiation/}messageType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "simpleMessage", propOrder = {
    "from",
    "to",
    "type"
})
public class SimpleMessage {

    protected Candidate from;
    protected Candidate to;
    protected MessageType type;

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link Candidate }
     *     
     */
    public Candidate getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link Candidate }
     *     
     */
    public void setFrom(Candidate value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link Candidate }
     *     
     */
    public Candidate getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link Candidate }
     *     
     */
    public void setTo(Candidate value) {
        this.to = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link MessageType }
     *     
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageType }
     *     
     */
    public void setType(MessageType value) {
        this.type = value;
    }

}
