//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.06.12 时间 07:27:17 AM GMT+08:00 
//


package cn.proem.eep.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncaPkgType"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncaPkgTypeDesc"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncaPkgCreateTime"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncaPkgCreator"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncaContent"/>
 *           &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ModifiedEncaContent"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="EEPVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}gYear" fixed="2009" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "encaPkgType",
    "encaPkgTypeDesc",
    "encaPkgCreateTime",
    "encaPkgCreator",
    "encaContent",
    "modifiedEncaContent"
})
@XmlRootElement(name = "SignedObj")
public class SignedObj {

    @XmlElement(name = "EncaPkgType", required = true, defaultValue = "\u539f\u59cb\u578b")
    protected String encaPkgType;
    @XmlElement(name = "EncaPkgTypeDesc", required = true, defaultValue = "\u672c\u5c01\u88c5\u5305\u5305\u542b\u7535\u5b50\u6587\u4ef6\u6570\u636e\u53ca\u5176\u5143\u6570\u636e\uff0c\u539f\u59cb\u5c01\u88c5\uff0c\u672a\u7ecf\u4fee\u6539")
    protected String encaPkgTypeDesc;
    @XmlElement(name = "EncaPkgCreateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar encaPkgCreateTime;
    @XmlElement(name = "EncaPkgCreator", required = true)
    protected String encaPkgCreator;
    @XmlElement(name = "EncaContent")
    protected EncaContent encaContent;
    @XmlElement(name = "ModifiedEncaContent")
    protected ModifiedEncaContent modifiedEncaContent;
    @XmlAttribute(name = "EEPVersion", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar eepVersion;

    /**
     * 获取encaPkgType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncaPkgType() {
        return encaPkgType;
    }

    /**
     * 设置encaPkgType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncaPkgType(String value) {
        this.encaPkgType = value;
    }

    /**
     * 获取encaPkgTypeDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncaPkgTypeDesc() {
        return encaPkgTypeDesc;
    }

    /**
     * 设置encaPkgTypeDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncaPkgTypeDesc(String value) {
        this.encaPkgTypeDesc = value;
    }

    /**
     * 获取encaPkgCreateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEncaPkgCreateTime() {
        return encaPkgCreateTime;
    }

    /**
     * 设置encaPkgCreateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEncaPkgCreateTime(XMLGregorianCalendar value) {
        this.encaPkgCreateTime = value;
    }

    /**
     * 获取encaPkgCreator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncaPkgCreator() {
        return encaPkgCreator;
    }

    /**
     * 设置encaPkgCreator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncaPkgCreator(String value) {
        this.encaPkgCreator = value;
    }

    /**
     * 获取encaContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncaContent }
     *     
     */
    public EncaContent getEncaContent() {
        return encaContent;
    }

    /**
     * 设置encaContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncaContent }
     *     
     */
    public void setEncaContent(EncaContent value) {
        this.encaContent = value;
    }

    /**
     * 获取modifiedEncaContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ModifiedEncaContent }
     *     
     */
    public ModifiedEncaContent getModifiedEncaContent() {
        return modifiedEncaContent;
    }

    /**
     * 设置modifiedEncaContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ModifiedEncaContent }
     *     
     */
    public void setModifiedEncaContent(ModifiedEncaContent value) {
        this.modifiedEncaContent = value;
    }

    /**
     * 获取eepVersion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEEPVersion() {
        return eepVersion;
    }

    /**
     * 设置eepVersion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEEPVersion(XMLGregorianCalendar value) {
        this.eepVersion = value;
    }

}
