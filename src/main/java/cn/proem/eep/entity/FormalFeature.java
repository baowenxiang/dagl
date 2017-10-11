//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.06.12 时间 07:27:17 AM GMT+08:00 
//


package cn.proem.eep.entity;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}RecdGroupType"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}PageNum" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Lang" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Manuscript" minOccurs="0"/>
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
    "recdGroupType",
    "pageNum",
    "lang",
    "manuscript"
})
@XmlRootElement(name = "FormalFeature")
public class FormalFeature {

    @XmlElement(name = "RecdGroupType", required = true, defaultValue = "\u5355\u4ef6")
    protected String recdGroupType;
    @XmlElement(name = "PageNum")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger pageNum;
    @XmlElement(name = "Lang", defaultValue = "\u6c49\u8bed")
    protected String lang;
    @XmlElement(name = "Manuscript")
    protected String manuscript;

    /**
     * 获取recdGroupType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecdGroupType() {
        return recdGroupType;
    }

    /**
     * 设置recdGroupType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecdGroupType(String value) {
        this.recdGroupType = value;
    }

    /**
     * 获取pageNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPageNum() {
        return pageNum;
    }

    /**
     * 设置pageNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPageNum(BigInteger value) {
        this.pageNum = value;
    }

    /**
     * 获取lang属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * 设置lang属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * 获取manuscript属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManuscript() {
        return manuscript;
    }

    /**
     * 设置manuscript属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManuscript(String value) {
        this.manuscript = value;
    }

}
