
package negotiation.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="issues" type="{http://ws.negotiation/}issue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="replyId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "id",
    "issues",
    "replyId",
    "to",
    "type"
})
public class SimpleMessage {

    protected Candidate from;
    protected int id;
    @XmlElement(nillable = true)
    protected List<Issue> issues;
    protected int replyId;
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
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the issues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Issue }
     * 
     * 
     */
    public List<Issue> getIssues() {
        if (issues == null) {
            issues = new ArrayList<Issue>();
        }
        return this.issues;
    }

    /**
     * Gets the value of the replyId property.
     * 
     */
    public int getReplyId() {
        return replyId;
    }

    /**
     * Sets the value of the replyId property.
     * 
     */
    public void setReplyId(int value) {
        this.replyId = value;
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
