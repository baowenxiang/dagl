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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}RecdEntityBlock"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}BusEntBlock"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}AgentEntBlock"/>
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
    "recdEntityBlock",
    "busEntBlock",
    "agentEntBlock"
})
@XmlRootElement(name = "EncaContent")
public class EncaContent {

    @XmlElement(name = "RecdEntityBlock", required = true)
    protected RecdEntityBlock recdEntityBlock;
    @XmlElement(name = "BusEntBlock", required = true)
    protected BusEntBlock busEntBlock;
    @XmlElement(name = "AgentEntBlock", required = true)
    protected AgentEntBlock agentEntBlock;

    /**
     * 获取recdEntityBlock属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RecdEntityBlock }
     *     
     */
    public RecdEntityBlock getRecdEntityBlock() {
        return recdEntityBlock;
    }

    /**
     * 设置recdEntityBlock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RecdEntityBlock }
     *     
     */
    public void setRecdEntityBlock(RecdEntityBlock value) {
        this.recdEntityBlock = value;
    }

    /**
     * 获取busEntBlock属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusEntBlock }
     *     
     */
    public BusEntBlock getBusEntBlock() {
        return busEntBlock;
    }

    /**
     * 设置busEntBlock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusEntBlock }
     *     
     */
    public void setBusEntBlock(BusEntBlock value) {
        this.busEntBlock = value;
    }

    /**
     * 获取agentEntBlock属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AgentEntBlock }
     *     
     */
    public AgentEntBlock getAgentEntBlock() {
        return agentEntBlock;
    }

    /**
     * 设置agentEntBlock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AgentEntBlock }
     *     
     */
    public void setAgentEntBlock(AgentEntBlock value) {
        this.agentEntBlock = value;
    }

}
