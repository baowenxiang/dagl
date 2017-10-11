//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.06.12 时间 07:27:17 AM GMT+08:00 
//


package cn.proem.eep.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignatureId"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignatureRole"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignatureTime" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignaturePeople" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignatureResult"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}CertiBlock" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SignatureAlgoId"/>
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
    "signatureId",
    "signatureRole",
    "signatureTime",
    "signaturePeople",
    "signatureResult",
    "certiBlock",
    "signatureAlgoId"
})
@XmlRootElement(name = "ElecSignature")
public class ElecSignature {

    @XmlElement(name = "SignatureId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String signatureId;
    @XmlElement(name = "SignatureRole", required = true)
    protected String signatureRole;
    @XmlElement(name = "SignatureTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signatureTime;
    @XmlElement(name = "SignaturePeople")
    protected String signaturePeople;
    @XmlElement(name = "SignatureResult", required = true)
    protected byte[] signatureResult;
    @XmlElement(name = "CertiBlock", required = true)
    protected List<CertiBlock> certiBlock;
    @XmlElement(name = "SignatureAlgoId", required = true)
    protected String signatureAlgoId;

    /**
     * 获取signatureId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureId() {
        return signatureId;
    }

    /**
     * 设置signatureId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureId(String value) {
        this.signatureId = value;
    }

    /**
     * 获取signatureRole属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureRole() {
        return signatureRole;
    }

    /**
     * 设置signatureRole属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureRole(String value) {
        this.signatureRole = value;
    }

    /**
     * 获取signatureTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSignatureTime() {
        return signatureTime;
    }

    /**
     * 设置signatureTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSignatureTime(XMLGregorianCalendar value) {
        this.signatureTime = value;
    }

    /**
     * 获取signaturePeople属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignaturePeople() {
        return signaturePeople;
    }

    /**
     * 设置signaturePeople属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignaturePeople(String value) {
        this.signaturePeople = value;
    }

    /**
     * 获取signatureResult属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSignatureResult() {
        return signatureResult;
    }

    /**
     * 设置signatureResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSignatureResult(byte[] value) {
        this.signatureResult = value;
    }

    /**
     * Gets the value of the certiBlock property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certiBlock property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertiBlock().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertiBlock }
     * 
     * 
     */
    public List<CertiBlock> getCertiBlock() {
        if (certiBlock == null) {
            certiBlock = new ArrayList<CertiBlock>();
        }
        return this.certiBlock;
    }

    /**
     * 获取signatureAlgoId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureAlgoId() {
        return signatureAlgoId;
    }

    /**
     * 设置signatureAlgoId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureAlgoId(String value) {
        this.signatureAlgoId = value;
    }

}
