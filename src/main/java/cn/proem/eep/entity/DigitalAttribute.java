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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}DigitalFormat" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ScanRatio"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ScanClrMode"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ScanCompressSchema" minOccurs="0"/>
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
    "digitalFormat",
    "scanRatio",
    "scanClrMode",
    "scanCompressSchema"
})
@XmlRootElement(name = "DigitalAttribute")
public class DigitalAttribute {

    @XmlElement(name = "DigitalFormat")
    protected String digitalFormat;
    @XmlElement(name = "ScanRatio", required = true)
    protected String scanRatio;
    @XmlElement(name = "ScanClrMode", required = true)
    protected String scanClrMode;
    @XmlElement(name = "ScanCompressSchema")
    protected String scanCompressSchema;

    /**
     * 获取digitalFormat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitalFormat() {
        return digitalFormat;
    }

    /**
     * 设置digitalFormat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitalFormat(String value) {
        this.digitalFormat = value;
    }

    /**
     * 获取scanRatio属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanRatio() {
        return scanRatio;
    }

    /**
     * 设置scanRatio属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanRatio(String value) {
        this.scanRatio = value;
    }

    /**
     * 获取scanClrMode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanClrMode() {
        return scanClrMode;
    }

    /**
     * 设置scanClrMode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanClrMode(String value) {
        this.scanClrMode = value;
    }

    /**
     * 获取scanCompressSchema属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanCompressSchema() {
        return scanCompressSchema;
    }

    /**
     * 设置scanCompressSchema属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanCompressSchema(String value) {
        this.scanCompressSchema = value;
    }

}
