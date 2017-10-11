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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BusEntId"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}AgentEntId"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}RecdId"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BusState"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BusBehavior"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BehaviorTime"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BehaviorReason" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BehaviorDesc" minOccurs="0"/>
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
    "busEntId",
    "agentEntId",
    "recdId",
    "busState",
    "busBehavior",
    "behaviorTime",
    "behaviorReason",
    "behaviorDesc"
})
@XmlRootElement(name = "BusEnt")
public class BusEnt {

    @XmlElement(name = "BusEntId", required = true)
    protected String busEntId;
    @XmlElement(name = "AgentEntId", required = true)
    protected String agentEntId;
    @XmlElement(name = "RecdId", required = true)
    protected String recdId;
    @XmlElement(name = "BusState", required = true)
    protected String busState;
    @XmlElement(name = "BusBehavior", required = true)
    protected String busBehavior;
    @XmlElement(name = "BehaviorTime", required = true)
    protected String behaviorTime;
    @XmlElement(name = "BehaviorReason")
    protected String behaviorReason;
    @XmlElement(name = "BehaviorDesc")
    protected String behaviorDesc;

    /**
     * 获取busEntId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusEntId() {
        return busEntId;
    }

    /**
     * 设置busEntId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusEntId(String value) {
        this.busEntId = value;
    }

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
     * 获取recdId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecdId() {
        return recdId;
    }

    /**
     * 设置recdId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecdId(String value) {
        this.recdId = value;
    }

    /**
     * 获取busState属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusState() {
        return busState;
    }

    /**
     * 设置busState属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusState(String value) {
        this.busState = value;
    }

    /**
     * 获取busBehavior属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusBehavior() {
        return busBehavior;
    }

    /**
     * 设置busBehavior属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusBehavior(String value) {
        this.busBehavior = value;
    }

    /**
     * 获取behaviorTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBehaviorTime() {
        return behaviorTime;
    }

    /**
     * 设置behaviorTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBehaviorTime(String value) {
        this.behaviorTime = value;
    }

    /**
     * 获取behaviorReason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBehaviorReason() {
        return behaviorReason;
    }

    /**
     * 设置behaviorReason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBehaviorReason(String value) {
        this.behaviorReason = value;
    }

    /**
     * 获取behaviorDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBehaviorDesc() {
        return behaviorDesc;
    }

    /**
     * 设置behaviorDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBehaviorDesc(String value) {
        this.behaviorDesc = value;
    }

}
