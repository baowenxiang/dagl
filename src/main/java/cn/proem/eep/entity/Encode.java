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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ElecAttribute"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}DigitalAttribute" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EocodeDesc"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}RenderKey"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}EncodeData"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EncodeId" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "elecAttribute",
    "digitalAttribute",
    "eocodeDesc",
    "renderKey",
    "encodeData"
})
@XmlRootElement(name = "Encode")
public class Encode {

    @XmlElement(name = "ElecAttribute", required = true)
    protected ElecAttribute elecAttribute;
    @XmlElement(name = "DigitalAttribute")
    protected DigitalAttribute digitalAttribute;
    @XmlElement(name = "EocodeDesc", required = true, defaultValue = "\u672c\u5c01\u88c5\u5305\u4e2d\u201c\u7f16\u7801\u6570\u636e\u201d\u5143\u7d20\u5b58\u50a8\u7684\u662f\u8ba1\u7b97\u673a\u6587\u4ef6\u4e8c\u8fdb\u5236\u6d41\u7684Base64\u7f16\u7801\uff0c\u6709\u5173Base64\u7f16\u7801\u89c4\u5219\u53c2\u89c1IETF RFC 2045\u591a\u7528\u9014\u90ae\u4ef6\u6269\u5c55\uff08MIME\uff09\u7b2c\u4e00\u90e8\u5206\uff1a\u4e92\u8054\u7f51\u4fe1\u606f\u4f53\u683c\u5f0f\u3002\u5f53\u63d0\u53d6\u548c\u663e\u73b0\u5c01\u88c5\u5728\u7f16\u7801\u6570\u636e\u5143\u7d20\u4e2d\u7684\u8ba1\u7b97\u673a\u6587\u4ef6\u65f6\uff0c\u5e94\u5bf9Base64\u7f16\u7801\u8fdb\u884c\u53cd\u7f16\u7801\uff0c\u5e76\u4f9d\u636e\u5c01\u88c5\u5305\u4e2d\u201c\u53cd\u7f16\u7801\u5173\u952e\u5b57\u201d\u5143\u7d20\u4e2d\u8bb0\u5f55\u7684\u503c\u8fd8\u539f\u8ba1\u7b97\u673a\u6587\u4ef6\u7684\u6269\u5c55\u540d")
    protected String eocodeDesc;
    @XmlElement(name = "RenderKey", required = true)
    protected String renderKey;
    @XmlElement(name = "EncodeData", required = true)
    protected EncodeData encodeData;
    @XmlAttribute(name = "EncodeId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String encodeId;

    /**
     * 获取elecAttribute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ElecAttribute }
     *     
     */
    public ElecAttribute getElecAttribute() {
        return elecAttribute;
    }

    /**
     * 设置elecAttribute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ElecAttribute }
     *     
     */
    public void setElecAttribute(ElecAttribute value) {
        this.elecAttribute = value;
    }

    /**
     * 获取digitalAttribute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DigitalAttribute }
     *     
     */
    public DigitalAttribute getDigitalAttribute() {
        return digitalAttribute;
    }

    /**
     * 设置digitalAttribute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DigitalAttribute }
     *     
     */
    public void setDigitalAttribute(DigitalAttribute value) {
        this.digitalAttribute = value;
    }

    /**
     * 获取eocodeDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEocodeDesc() {
        return eocodeDesc;
    }

    /**
     * 设置eocodeDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEocodeDesc(String value) {
        this.eocodeDesc = value;
    }

    /**
     * 获取renderKey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenderKey() {
        return renderKey;
    }

    /**
     * 设置renderKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenderKey(String value) {
        this.renderKey = value;
    }

    /**
     * 获取encodeData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncodeData }
     *     
     */
    public EncodeData getEncodeData() {
        return encodeData;
    }

    /**
     * 设置encodeData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncodeData }
     *     
     */
    public void setEncodeData(EncodeData value) {
        this.encodeData = value;
    }

    /**
     * 获取encodeId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodeId() {
        return encodeId;
    }

    /**
     * 设置encodeId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodeId(String value) {
        this.encodeId = value;
    }

}
