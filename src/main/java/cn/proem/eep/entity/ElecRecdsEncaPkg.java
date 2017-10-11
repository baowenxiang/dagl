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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncaPkgFormatDesc"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Version"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignedObj"/>
 *         &lt;sequence minOccurs="0">
 *           &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ElecsignatureBlock"/>
 *           &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}LockSignature"/>
 *         &lt;/sequence>
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
    "encaPkgFormatDesc",
    "version",
    "signedObj",
    "elecsignatureBlock",
    "lockSignature"
})
@XmlRootElement(name = "ElecRecdsEncaPkg")
public class ElecRecdsEncaPkg {

    @XmlElement(name = "EncaPkgFormatDesc", required = true, defaultValue = "\u672cEEP\u6839\u636e\u4e2d\u534e\u4eba\u6c11\u5171\u548c\u56fd\u6863\u6848\u884c\u4e1a\u6807\u51c6DA/T 48-2009\u300a\u57fa\u4e8eXML\u7684\u7535\u5b50\u6587\u4ef6\u5c01\u88c5\u89c4\u8303\u300b\u751f\u6210")
    protected String encaPkgFormatDesc;
    @XmlElement(name = "Version", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar version;
    @XmlElement(name = "SignedObj", required = true)
    protected SignedObj signedObj;
    @XmlElement(name = "ElecsignatureBlock")
    protected ElecsignatureBlock elecsignatureBlock;
    @XmlElement(name = "LockSignature")
    protected LockSignature lockSignature;

    /**
     * 获取encaPkgFormatDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncaPkgFormatDesc() {
        return encaPkgFormatDesc;
    }

    /**
     * 设置encaPkgFormatDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncaPkgFormatDesc(String value) {
        this.encaPkgFormatDesc = value;
    }

    /**
     * 获取version属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVersion(XMLGregorianCalendar value) {
        this.version = value;
    }

    /**
     * 获取signedObj属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SignedObj }
     *     
     */
    public SignedObj getSignedObj() {
        return signedObj;
    }

    /**
     * 设置signedObj属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SignedObj }
     *     
     */
    public void setSignedObj(SignedObj value) {
        this.signedObj = value;
    }

    /**
     * 获取elecsignatureBlock属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ElecsignatureBlock }
     *     
     */
    public ElecsignatureBlock getElecsignatureBlock() {
        return elecsignatureBlock;
    }

    /**
     * 设置elecsignatureBlock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ElecsignatureBlock }
     *     
     */
    public void setElecsignatureBlock(ElecsignatureBlock value) {
        this.elecsignatureBlock = value;
    }

    /**
     * 获取lockSignature属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LockSignature }
     *     
     */
    public LockSignature getLockSignature() {
        return lockSignature;
    }

    /**
     * 设置lockSignature属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LockSignature }
     *     
     */
    public void setLockSignature(LockSignature value) {
        this.lockSignature = value;
    }

}
