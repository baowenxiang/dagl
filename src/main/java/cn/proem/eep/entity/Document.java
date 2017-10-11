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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}DocumentId"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}RecdNo" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}StateOnMainOrAttament" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Title" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}DocData" maxOccurs="unbounded"/>
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
    "documentId",
    "recdNo",
    "stateOnMainOrAttament",
    "title",
    "docData"
})
@XmlRootElement(name = "Document")
public class Document {

    @XmlElement(name = "DocumentId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String documentId;
    @XmlElement(name = "RecdNo")
    protected String recdNo;
    @XmlElement(name = "StateOnMainOrAttament")
    protected String stateOnMainOrAttament;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "DocData", required = true)
    protected List<DocData> docData;

    /**
     * 获取documentId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentId() {
        return documentId;
    }

    /**
     * 设置documentId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentId(String value) {
        this.documentId = value;
    }

    /**
     * 获取recdNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecdNo() {
        return recdNo;
    }

    /**
     * 设置recdNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecdNo(String value) {
        this.recdNo = value;
    }

    /**
     * 获取stateOnMainOrAttament属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateOnMainOrAttament() {
        return stateOnMainOrAttament;
    }

    /**
     * 设置stateOnMainOrAttament属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateOnMainOrAttament(String value) {
        this.stateOnMainOrAttament = value;
    }

    /**
     * 获取title属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置title属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the docData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocData }
     * 
     * 
     */
    public List<DocData> getDocData() {
        if (docData == null) {
            docData = new ArrayList<DocData>();
        }
        return this.docData;
    }

}
