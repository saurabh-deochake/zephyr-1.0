
package negotiation.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for status.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="status">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="Searching"/>
 *     &lt;enumeration value="Matched"/>
 *     &lt;enumeration value="Busy"/>
 *     &lt;enumeration value="Successful"/>
 *     &lt;enumeration value="Failed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "status")
@XmlEnum
public enum Status {

    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Searching")
    SEARCHING("Searching"),
    @XmlEnumValue("Matched")
    MATCHED("Matched"),
    @XmlEnumValue("Busy")
    BUSY("Busy"),
    @XmlEnumValue("Successful")
    SUCCESSFUL("Successful"),
    @XmlEnumValue("Failed")
    FAILED("Failed");
    private final String value;

    Status(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Status fromValue(String v) {
        for (Status c: Status.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
