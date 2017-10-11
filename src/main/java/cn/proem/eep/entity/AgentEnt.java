//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.06.12 时间 07:27:17 AM GMT+08:00 
//


package cn.proem.eep.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}AgentEntId"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}InstPeopleType" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}InstPeopleName"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}InstUnitCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}PeoplePosition" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agentEntId",
    "instPeopleType",
    "instPeopleName",
    "instUnitCode",
    "peoplePosition"
})
@XmlRootElement(name = "AgentEnt")
public class AgentEnt {

    @XmlElement(name = "AgentEntId", required = true)
    protected String agentEntId;
    @XmlElement(name = "InstPeopleType")
    protected String instPeopleType;
    @XmlElement(name = "InstPeopleName", required = true)
    protected String instPeopleName;
    @XmlElement(name = "InstUnitCode")
    protected String instUnitCode;
    @XmlElement(name = "PeoplePosition")
    protected String peoplePosition;

    /**
     * 获取agentEntId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentEntId() {
        return agentEntId;
    }

    /**
     * 设置agentEntId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentEntId(String value) {
        this.agentEntId = value;
    }

    /**
     * 获取instPeopleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstPeopleType() {
        return instPeopleType;
    }

    /**
     * 设置instPeopleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstPeopleType(String value) {
        this.instPeopleType = value;
    }

    /**
     * 获取instPeopleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstPeopleName() {
        return instPeopleName;
    }

    /**
     * 设置instPeopleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstPeopleName(String value) {
        this.instPeopleName = value;
    }

    /**
     * 获取instUnitCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstUnitCode() {
        return instUnitCode;
    }

    /**
     * 设置instUnitCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstUnitCode(String value) {
        this.instUnitCode = value;
    }

    /**
     * 获取peoplePosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeoplePosition() {
        return peoplePosition;
    }

    /**
     * 设置peoplePosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeoplePosition(String value) {
        this.peoplePosition = value;
    }

}
