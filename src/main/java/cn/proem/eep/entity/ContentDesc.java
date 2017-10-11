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
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Title"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}PaTitle" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SubTitle" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}TitleDesc" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}SubWord" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}KeyWord" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}PeopleName" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Summary" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}CategoryNo" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}FileNo" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}ResponsePeople"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}DateE"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}RecdType" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}UrgencyLev" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}MainRecv" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}CC" minOccurs="0"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}Dense"/>
 *         &lt;element ref="{http://www.saac.gov.cn/standards/ERM/encapsulation}CondfidPeriod" minOccurs="0"/>
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
    "title",
    "paTitle",
    "subTitle",
    "titleDesc",
    "subWord",
    "keyWord",
    "peopleName",
    "summary",
    "categoryNo",
    "fileNo",
    "responsePeople",
    "dateE",
    "recdType",
    "urgencyLev",
    "mainRecv",
    "cc",
    "dense",
    "condfidPeriod"
})
@XmlRootElement(name = "ContentDesc")
public class ContentDesc {

    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "PaTitle")
    protected String paTitle;
    @XmlElement(name = "SubTitle")
    protected String subTitle;
    @XmlElement(name = "TitleDesc")
    protected String titleDesc;
    @XmlElement(name = "SubWord")
    protected List<SubWord> subWord;
    @XmlElement(name = "KeyWord")
    protected String keyWord;
    @XmlElement(name = "PeopleName")
    protected String peopleName;
    @XmlElement(name = "Summary")
    protected String summary;
    @XmlElement(name = "CategoryNo")
    protected String categoryNo;
    @XmlElement(name = "FileNo")
    protected String fileNo;
    @XmlElement(name = "ResponsePeople", required = true)
    protected String responsePeople;
    @XmlElement(name = "DateE", required = true)
    protected String dateE;
    @XmlElement(name = "RecdType")
    protected String recdType;
    @XmlElement(name = "UrgencyLev")
    protected String urgencyLev;
    @XmlElement(name = "MainRecv")
    protected String mainRecv;
    @XmlElement(name = "CC")
    protected String cc;
    @XmlElement(name = "Dense", required = true)
    protected String dense;
    @XmlElement(name = "CondfidPeriod")
    protected String condfidPeriod;

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
     * 获取paTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaTitle() {
        return paTitle;
    }

    /**
     * 设置paTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaTitle(String value) {
        this.paTitle = value;
    }

    /**
     * 获取subTitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置subTitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubTitle(String value) {
        this.subTitle = value;
    }

    /**
     * 获取titleDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleDesc() {
        return titleDesc;
    }

    /**
     * 设置titleDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleDesc(String value) {
        this.titleDesc = value;
    }

    /**
     * Gets the value of the subWord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subWord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubWord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubWord }
     * 
     * 
     */
    public List<SubWord> getSubWord() {
        if (subWord == null) {
            subWord = new ArrayList<SubWord>();
        }
        return this.subWord;
    }

    /**
     * 获取keyWord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 设置keyWord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyWord(String value) {
        this.keyWord = value;
    }

    /**
     * 获取peopleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeopleName() {
        return peopleName;
    }

    /**
     * 设置peopleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeopleName(String value) {
        this.peopleName = value;
    }

    /**
     * 获取summary属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置summary属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * 获取categoryNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryNo() {
        return categoryNo;
    }

    /**
     * 设置categoryNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryNo(String value) {
        this.categoryNo = value;
    }

    /**
     * 获取fileNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置fileNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNo(String value) {
        this.fileNo = value;
    }

    /**
     * 获取responsePeople属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsePeople() {
        return responsePeople;
    }

    /**
     * 设置responsePeople属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsePeople(String value) {
        this.responsePeople = value;
    }

    /**
     * 获取dateE属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateE() {
        return dateE;
    }

    /**
     * 设置dateE属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateE(String value) {
        this.dateE = value;
    }

    /**
     * 获取recdType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecdType() {
        return recdType;
    }

    /**
     * 设置recdType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecdType(String value) {
        this.recdType = value;
    }

    /**
     * 获取urgencyLev属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrgencyLev() {
        return urgencyLev;
    }

    /**
     * 设置urgencyLev属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrgencyLev(String value) {
        this.urgencyLev = value;
    }

    /**
     * 获取mainRecv属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainRecv() {
        return mainRecv;
    }

    /**
     * 设置mainRecv属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainRecv(String value) {
        this.mainRecv = value;
    }

    /**
     * 获取cc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCC() {
        return cc;
    }

    /**
     * 设置cc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCC(String value) {
        this.cc = value;
    }

    /**
     * 获取dense属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDense() {
        return dense;
    }

    /**
     * 设置dense属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDense(String value) {
        this.dense = value;
    }

    /**
     * 获取condfidPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondfidPeriod() {
        return condfidPeriod;
    }

    /**
     * 设置condfidPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondfidPeriod(String value) {
        this.condfidPeriod = value;
    }

}
