package model;

import javax.xml.bind.annotation.XmlEnumValue;

public enum AppealStatus 
{
    @XmlEnumValue(value="ungraded")
    UNGRADED,
    @XmlEnumValue(value="graded")
    GRADED,
    @XmlEnumValue(value="composed")
    COMPOSED, 
    @XmlEnumValue(value="deleted")
    DELETED, 
    @XmlEnumValue(value="submitted")
    SUBMITTED,
    @XmlEnumValue(value="acknowledged")
    ACKNOWLEDGED,
    @XmlEnumValue(value="reply")
    REPLY_SENT
}