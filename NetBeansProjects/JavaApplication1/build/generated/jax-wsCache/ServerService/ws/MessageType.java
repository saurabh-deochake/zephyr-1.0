
package ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for messageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="messageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Hello"/>
 *     &lt;enumeration value="Send"/>
 *     &lt;enumeration value="Accept"/>
 *     &lt;enumeration value="Reject"/>
 *     &lt;enumeration value="Finalize"/>
 *     &lt;enumeration value="NULL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "messageType")
@XmlEnum
public enum MessageType {

    @XmlEnumValue("Hello")
    HELLO("Hello"),
    @XmlEnumValue("Send")
    SEND("Send"),
    @XmlEnumValue("Accept")
    ACCEPT("Accept"),
    @XmlEnumValue("Reject")
    REJECT("Reject"),
    @XmlEnumValue("Finalize")
    FINALIZE("Finalize"),
    NULL("NULL");
    private final String value;

    MessageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessageType fromValue(String v) {
        for (MessageType c: MessageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
