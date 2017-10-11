package cn.proem.eep.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbUtil {
	
	/**
	 * @Method: convertToxml 
	 * @Description: JaveBean转换成xml
	 * @param obj JavaBean对象
	 * @return String
	 * @throws
	 */
	public static String convertToxml(Object obj){
		return convertToXml(obj, "UTF-8");
	}
	
	/**
	 * 
	 * @Method: convertToXml 
	 * @Description: JaveBean转换成xml
	 * @param obj JaveBean对象
	 * @param encoding 编码方式
	 * @return String 
	 * @throws
	 */
	public static String convertToXml(Object obj, String encoding){
		String result = null;
		try{
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Method: converyToJavaBean 
	 * @Description: TODO
	 * @param @param xml
	 * @param @param c
	 * @param @return
	 * @return T
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c){
		T t = null;
		try{
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	
}
