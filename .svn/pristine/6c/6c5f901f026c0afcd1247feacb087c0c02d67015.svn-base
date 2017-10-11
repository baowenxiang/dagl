package cn.proem.dagl.web.fuzzyQuery.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.proem.core.entity.User;
import cn.proem.core.model.ConditionType;
import cn.proem.core.model.FieldType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.core.model.QueryCondition;
import cn.proem.dagl.web.fuzzyQuery.dto.DtoSearchResult;
import cn.proem.dagl.web.fuzzyQuery.service.FuzzyQueryService;
import cn.proem.dagl.web.fuzzyQuery.task.CreateIndexThread;
import cn.proem.dagl.web.message.entity.Message;
import cn.proem.dagl.web.message.service.MessageService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.model.BaseCtrlModel;
import cn.proem.suw.web.common.model.ResultModel;
import cn.proem.suw.web.common.util.DataTransfer;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.docu.entity.DocuAttachment;
import cn.proem.suw.webservice.model.ResponseModel;

@Controller
@RequestMapping("/w/fuzzyQuery")
public class fuzzyQueryController extends BaseCtrlModel {
	
	@Autowired
	private FuzzyQueryService fuzzyQueryService;
	
	@Autowired
    private MessageService msgService;
	
	private Map<String, Message> msgs = new HashMap<String, Message>();
	
	private String INDEX_DIR = getFilePath()+Path.UPLOAD_INDEX_PATH;
				
	private static Analyzer analyzer = null;
    private static Directory directory = null;
	
	@RequestMapping("/init")
	public ModelAndView initMessagePage(){
		ModelAndView view = this.createNormalView("/web/fuzzyQuery/init.vm");
		
		return view;
		
	}
	

	/**
	 * 创建索引
	 * 
	 */
	@ResponseBody
	@RequestMapping("/createIndex")
	public ResultModel<String> createIndex(){
		File fileTo = new File(INDEX_DIR);
		if (!fileTo.exists()) {
			fileTo.mkdirs();
		}
		
        QueryBuilder qb = new QueryBuilder();
        qb.addCondition(new QueryCondition("delFlag",0,ConditionType.EQ,FieldType.INTEGER,null));
        List<Ywgj> listYwgj = fuzzyQueryService.getYwgjList(qb);
        qb = new QueryBuilder();
        qb.addCondition(new QueryCondition("delFlag",0,ConditionType.EQ,FieldType.INTEGER,null));
        List<DocuAttachment> listAttach = fuzzyQueryService.getDocuAttachmentList(qb);
	   
	    // 启动创建索引线程
	    CreateIndexThread createIndex = new CreateIndexThread(listYwgj, listAttach);
	    createIndex.setService(fuzzyQueryService);
	    createIndex.start();
	    ResultModel<String> response =  new ResultModel<String>();
        response.setSuccess(true);
        response.setMsg("执行创建索引 线程号：" + createIndex.getId());
        response.setData(Long.toString(createIndex.getId()));
        return response;
	}
	
	/**
	 * 索引检索
	 */
	@RequestMapping("/searchIndex")
	public ModelAndView searchIndex(@RequestParam("key")String key){
		ModelAndView view = this.createNormalView("/web/fuzzyQuery/details.vm");
		List<DtoSearchResult> results = new ArrayList<DtoSearchResult>();
		List<DtoSearchResult> da_results = new ArrayList<DtoSearchResult>();
		 try{
	            directory = FSDirectory.open(new File(INDEX_DIR));
	            analyzer = new StandardAnalyzer(Version.LUCENE_40);
	            DirectoryReader ireader = DirectoryReader.open(directory);
	            IndexSearcher isearcher = new IndexSearcher(ireader);
	    
//	            QueryParser parser = new QueryParser(Version.LUCENE_40, "content", analyzer);
//	            Query query = parser.parse(key);
	            
	            //多字段查询
	           MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_40,new String[]{"fileName","content"},analyzer);  
	           MultiFieldQueryParser parser_da = new MultiFieldQueryParser(Version.LUCENE_40,new String[]{"DH", "TM", "DALX", "ZRZ", "CWRQ", "BGQX"}, analyzer);
	           Query query = parser.parse(key);
	           Query query_da = parser_da.parse(key);
	           
	            //以下代码对查询结果进行高亮显示
	            // 1.格式化对象，设置前缀和后缀
	            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
	            // 2.关键词对象
	            Scorer scorer = new QueryScorer(query);
	            Scorer scorer_da = new QueryScorer(query_da);
	            
	            // 3. 高亮对象
	            Highlighter highlighter = new Highlighter(formatter, scorer);
	            Highlighter highlighter_da = new Highlighter(formatter, scorer_da);
	            
	            Fragmenter fragmenter = new SimpleFragmenter(150);
	            
	            highlighter.setTextFragmenter(fragmenter);
	            
	            ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
	            ScoreDoc[] hits_da = isearcher.search(query_da, null, 1000).scoreDocs;
	            for (int i = 0; i < hits.length; i++) {
	                Document hitDoc = isearcher.doc(hits[i].doc);
                    System.out.println("===========================");
	                List<IndexableField> fields = hitDoc.getFields();
	                for(IndexableField field: fields){
	                    System.out.println(hitDoc.get("fileName"));
	                    System.out.println("属性名字：" + field.name());
	                    System.out.println("属性值：" + field.stringValue());
	                    System.out.println("===========================");
	                }
//	                System.out.println(hitDoc.get("content"));
//	                System.out.println(hitDoc.get("path"));
	                String titleHightLight = highlighter.getBestFragment(analyzer,"fileName",hitDoc.get("fileName"));
	                String contentHighLight  = highlighter.getBestFragment(analyzer,"content",hitDoc.get("content"));
	               // hitDoc.getField("content").;
	                
	                DtoSearchResult sr = new DtoSearchResult();
	                sr.setFileId(hitDoc.get("fileId"));
	                
	                if(StringUtil.isNotEmpty(titleHightLight) && StringUtil.isNotEmpty(contentHighLight)){
	                	sr.setTitle(titleHightLight);
	                	sr.setContent(contentHighLight);
	                }else if(StringUtil.isEmpty(titleHightLight) && StringUtil.isNotEmpty(contentHighLight)){
	                	sr.setTitle(hitDoc.get("fileName"));
	                	sr.setContent(contentHighLight);
	                }else if(StringUtil.isNotEmpty(titleHightLight) && StringUtil.isEmpty(contentHighLight)){
	                	sr.setTitle(titleHightLight);
	                }
	                
	                sr.setAddress(hitDoc.get("path"));
	                sr.setFileCreateTime(hitDoc.get("uploadTime"));
	                
	                // 档案类型、档号、题名、责任者、成文日期保管期限
	                sr.setDalx(hitDoc.get("DALX"));
	                sr.setDh(hitDoc.get("DH"));
	                sr.setTm(hitDoc.get("TM"));
	                sr.setZrz(hitDoc.get("ZRZ"));
	                sr.setCwrq(hitDoc.get("CWRQ"));
	                sr.setBgqx(hitDoc.get("BGQX"));
	                
	                results.add(sr);
	            }
	            
	            
	            for (int i = 0; i < hits_da.length; i++) {
                    Document hitDoc = isearcher.doc(hits_da[i].doc);
                    String titleHightLight = highlighter.getBestFragment(analyzer,"fileName",hitDoc.get("fileName"));
                    String contentHighLight  = highlighter.getBestFragment(analyzer,"content",hitDoc.get("content"));
                    
                    DtoSearchResult sr = new DtoSearchResult();
                    sr.setFileId(hitDoc.get("fileId"));
                    
                    if(StringUtil.isNotEmpty(titleHightLight) && StringUtil.isNotEmpty(contentHighLight)){
                        sr.setTitle(titleHightLight);
                        sr.setContent(contentHighLight);
                    }else if(StringUtil.isEmpty(titleHightLight) && StringUtil.isNotEmpty(contentHighLight)){
                        sr.setTitle(hitDoc.get("fileName"));
                        sr.setContent(contentHighLight);
                    }else if(StringUtil.isNotEmpty(titleHightLight) && StringUtil.isEmpty(contentHighLight)){
                        sr.setTitle(titleHightLight);
                    }
                    
                    //sr.setAddress(hitDoc.get("path"));
                    //sr.setFileCreateTime(hitDoc.get("uploadTime"));
                    
                    // 档案类型、档号、题名、责任者、成文日期保管期限
                    sr.setDalx(hitDoc.get("DALX"));
                    sr.setDh(hitDoc.get("DH"));
                    sr.setTm(hitDoc.get("TM"));
                    sr.setZrz(hitDoc.get("ZRZ"));
                    sr.setCwrq(hitDoc.get("CWRQ"));
                    sr.setBgqx(hitDoc.get("BGQX"));
                    // 档案更新时间
                    sr.setFileCreateTime(hitDoc.get("UPDATETIME"));
                    da_results.add(sr);
                }
	            ireader.close();
	            directory.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		
		view.addObject("key", key);
		view.addObject("results", results);
		view.addObject("da_results", da_results);
		return view;
		
	}
	
	
	/**
	 * 获取项目指定目录下的所有文件
	 */
	public List<File> getFileList(String dirPath){
		
		List<File> fileList = new ArrayList<File>();
		File file = new File(dirPath);
		File[] files = file.listFiles();
		if(files != null){
			for(File f:files){
				if(f.isDirectory()){
					String path = f.getAbsolutePath();
					fileList.addAll(getFileList(path));
				}
				else
				{
					fileList.add(f);
				}
			}
		}
		return fileList;
	}
	
    /*
     * 获取文件的创建时间
     */
    
    public String getFileCreateTime(String filePath){
    	String createTime = null;
    	 try {  
             Process p = Runtime.getRuntime().exec("cmd /C dir "           
                     + filePath  
                     + "/tc" );  
             InputStream is = p.getInputStream();   
             BufferedReader br = new BufferedReader(new InputStreamReader(is));             
             String line;
             int i = 0;
             while((line = br.readLine()) != null){
            	 i++;
                 if(i == 6){  
                	 createTime = line.substring(0,17);  
                       
                 }                             
              }   
         } catch (IOException e) {  
             e.printStackTrace();  
         }         
    	return createTime;
    }
    
    @RequestMapping(value = "/process")
    @ResponseBody
    public ResultModel<String> getProcess(HttpServletRequest request, String threadid){
        User user = this.getCurrentUser(request);
        ResultModel<String> resultModal = new ResultModel<String>();
        
        int[] process = fuzzyQueryService.getProcess(Long.parseLong(threadid));
        List<String> datas = new ArrayList<String>();
        Message msg = null;
        if(!msgs.containsKey(threadid)){
            msg = new Message();
            msg.setType("CREATEINDEX");
            msg.setSendUser(user);
            msg.setReceUser(user);
            msg.setCreateTime(new Date());
            msg.setIsRead("isRead_0");
            msg.setContent(String.valueOf(process[0]) + "," + String.valueOf(process[1]) + "," + threadid);
        }else{
            msg = msgs.get(threadid);
            msg.setUpdateTime(new Date());
            msg.setIsRead("isRead_0");
            msg.setContent(String.valueOf(process[0]) + "," + String.valueOf(process[1]) + "," + threadid);
        }
        msgs.put(threadid, msg);
        try {
            msgService.saveOrUpdateMessage(msg);
            resultModal.setSuccess(true);
            datas.add(String.valueOf(process[0]));
            datas.add(String.valueOf(process[1]));
        } catch (ServiceException e) {
            resultModal.setSuccess(false);
            resultModal.setMsg(e.getMessage());
        }
        resultModal.setDatas(datas);
        return resultModal;
    }
 
}
