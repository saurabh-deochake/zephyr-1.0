
package negotiation.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="agentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Buyer"/>
 *     &lt;enumeration value="Seller"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "agentType")
@XmlEnum
public enum AgentType {

    @XmlEnumValue("Buyer")
    BUYER("Buyer"),
    @XmlEnumValue("Seller")
    SELLER("Seller");
    private final String value;

    AgentType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AgentType fromValue(String v) {
        for (AgentType c: AgentType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
