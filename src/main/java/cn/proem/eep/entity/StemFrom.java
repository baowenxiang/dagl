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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ArchiveName" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ArchiveCode" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}FondName" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ArchiveFromUnit"/>
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
    "archiveName",
    "archiveCode",
    "fondName",
    "archiveFromUnit"
})
@XmlRootElement(name = "StemFrom")
public class StemFrom {

    @XmlElement(name = "ArchiveName")
    protected String archiveName;
    @XmlElement(name = "ArchiveCode")
    protected String archiveCode;
    @XmlElement(name = "FondName")
    protected String fondName;
    @XmlElement(name = "ArchiveFromUnit", required = true)
    protected String archiveFromUnit;

    /**
     * 获取archiveName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchiveName() {
        return archiveName;
    }

    /**
     * 设置archiveName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchiveName(String value) {
        this.archiveName = value;
    }

    /**
     * 获取archiveCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchiveCode() {
        return archiveCode;
    }

    /**
     * 设置archiveCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchiveCode(String value) {
        this.archiveCode = value;
    }

    /**
     * 获取fondName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFondName() {
        return fondName;
    }

    /**
     * 设置fondName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFondName(String value) {
        this.fondName = value;
    }

    /**
     * 获取archiveFromUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArchiveFromUnit() {
        return archiveFromUnit;
    }

    /**
     * 设置archiveFromUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArchiveFromUnit(String value) {
        this.archiveFromUnit = value;
    }

}
