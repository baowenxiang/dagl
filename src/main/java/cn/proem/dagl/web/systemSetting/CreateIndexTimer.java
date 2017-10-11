package cn.proem.dagl.web.systemSetting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.fuzzyQuery.service.FuzzyQueryService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.util.DataTransfer;
import cn.proem.suw.web.docu.entity.DocuAttachment;
//@Component
public class CreateIndexTimer extends BaseCtrlModel {
	
	@Autowired
	private FuzzyQueryService fuzzyQueryService;
	
	//private String DATA_DIR = getFilePath()+Path.UPLOAD_DOCU_ATTACHMENT_PATH;
	private String INDEX_DIR = getFilePath()+Path.UPLOAD_INDEX_PATH;
				
	private static Analyzer analyzer = null;
    private static Directory directory = null;
    private static IndexWriter indexWriter = null;
    
//	Cron表达式是一个字符串，字符串以5或6个空格隔开，分为6或7个域，每一个域代表一个含义，Cron有如下两种语法格式：
//	Seconds Minutes Hours DayofMonth Month DayofWeek Year或 
//	Seconds Minutes Hours DayofMonth Month DayofWeek
//    @Scheduled(cron="0 */30 * * * ?")
    public void createIndex(){
    	System.out.println("in");
		//获取所有源文件
		QueryBuilder qb = new QueryBuilder();
		qb.addCondition(new QueryCondition("delFlag",0,ConditionType.EQ,FieldType.INTEGER,null));
		List<Ywgj> listYwgj = fuzzyQueryService.getYwgjList(qb);
		List<DocuAttachment> listAttach = fuzzyQueryService.getDocuAttachmentList(qb);
		//存放索引目录
		File indexFile = new File(INDEX_DIR);
        if (!indexFile.exists()) {
            indexFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String content = "";
		String filePath = "";
		String absolutePath = "";
		File file = null;
		
		 try{
			    analyzer = new StandardAnalyzer(Version.LUCENE_40);
	            //analyzer = new IK_CAnalyzer();
	            //analyzer = new PaodingAnalyzer();
	            directory = FSDirectory.open(new File(INDEX_DIR));
	            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
	            indexWriter = new IndexWriter(directory, config);
	            //创建之前删除所有索引
	            indexWriter.deleteAll();
	            Document document = null;
	          //原文挂接建立索引
	            for(Ywgj ywgj:listYwgj){
	    			filePath = ywgj.getWjdz();
	    			absolutePath = getFilePath()+filePath;
	    			file = new File(absolutePath);
	    			if(file.exists()){
	                	content = DataTransfer.file2String(file);
	                	document = new Document();
	                    document.add(new StringField("fileId",ywgj.getId(),Store.YES));
	                    document.add(new TextField("fileName", ywgj.getWjm(), Store.YES));
	                    document.add(new TextField("content", content, Store.YES));
	                    document.add(new TextField("path", file.getPath(), Store.YES));
	                    document.add(new TextField("uploadTime", sdf.format(ywgj.getCreateTime()), Store.YES));
	                    indexWriter.addDocument(document);
	                    indexWriter.commit();
	                    content = "";
	            
	    			}
	            }
	            
	            //管养接口附件建立索引
	    		for(DocuAttachment docuAttachment:listAttach){
	    			filePath = docuAttachment.getFjnr();
	    			absolutePath = getFilePath()+filePath;
	    			file = new File(absolutePath);
	    			if(file.exists()){
	    				
	    			    content = DataTransfer.file2String(file);
	    			
	                    document = new Document();
	                    document.add(new StringField("fileId",docuAttachment.getId(),Store.YES));
	                    document.add(new TextField("fileName", docuAttachment.getFjmc(), Store.YES));
	                    document.add(new TextField("content", content, Store.YES));
	                    document.add(new TextField("path", file.getPath(), Store.YES));
	                    //document.add(new TextField("uploadTime", sdf.format(ywgj.getCreateTime()), Store.YES));
	                    indexWriter.addDocument(document);
	                    indexWriter.commit();
	                    content = "";

	    	            }
	    			} 

	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	        	try {
					indexWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}      	
    
    /**
     * 更新index
     */
//    @Scheduled(cron="0 */5 * * * ?")
    public void updateIndex(){
    	//获取所有源文件
		QueryBuilder qb = new QueryBuilder();
		qb.addCondition(new QueryCondition("delFlag",0,ConditionType.EQ,FieldType.INTEGER,null));
		List<Ywgj> listYwgj = fuzzyQueryService.getYwgjList(qb);
		List<DocuAttachment> listAttach = fuzzyQueryService.getDocuAttachmentList(qb);
		//存放索引目录
		File indexFile = new File(INDEX_DIR);
        if (!indexFile.exists()) {
            indexFile.mkdirs();
            createIndex();
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String content = "";
		String filePath = "";
		String absolutePath = "";
		File file = null;
		for(Ywgj ywgj:listYwgj){
			filePath = ywgj.getWjdz();
			absolutePath = getFilePath()+filePath;
			file = new File(absolutePath);
			if(file.exists()){
			
			content = DataTransfer.file2String(file);
			 try{
				 
                analyzer = new StandardAnalyzer(Version.LUCENE_40);
                //analyzer = new IK_CAnalyzer();
                //analyzer = new PaodingAnalyzer();
                directory = FSDirectory.open(new File(INDEX_DIR));
                IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
                indexWriter = new IndexWriter(directory, config);
                Document document = new Document();
                document.add(new StringField("fileId",ywgj.getId(),Store.YES));
                document.add(new TextField("fileName", ywgj.getWjm(), Store.YES));
                document.add(new TextField("content", content, Store.YES));
                document.add(new TextField("path", file.getPath(), Store.YES));
                document.add(new TextField("uploadTime", sdf.format(ywgj.getCreateTime()), Store.YES));
                //indexWriter.addDocument(document);
                Term term = new Term("fileId",ywgj.getId());
                indexWriter.updateDocument(term, document);
                indexWriter.commit();
                indexWriter.close();
	      
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	            content = "";
			}    
			
		}
		
		for(DocuAttachment docuAttachment:listAttach){
			filePath = docuAttachment.getFjnr();
			absolutePath = getFilePath()+filePath;
			file = new File(absolutePath);
			if(file.exists()){
				
			content = DataTransfer.file2String(file);
			 try{
				 
                analyzer = new StandardAnalyzer(Version.LUCENE_40);
                //analyzer = new IK_CAnalyzer();
                //analyzer = new PaodingAnalyzer();
                directory = FSDirectory.open(new File(INDEX_DIR));
                IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
                indexWriter = new IndexWriter(directory, config);
                Document document = new Document();
                document.add(new StringField("fileId",docuAttachment.getId(),Store.YES));
                document.add(new TextField("fileName", docuAttachment.getFjmc(), Store.YES));
                document.add(new TextField("content", content, Store.YES));
                document.add(new TextField("path", file.getPath(), Store.YES));
                //document.add(new TextField("uploadTime", sdf.format(ywgj.getCreateTime()), Store.YES));
                //indexWriter.addDocument(document);
                Term term = new Term("fileId",docuAttachment.getId());
                indexWriter.updateDocument(term, document);
                indexWriter.commit();
                indexWriter.close();
	                
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	            content = "";
			} 
			
		}
    	
    }

}
