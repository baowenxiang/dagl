package cn.proem.dagl.web.fuzzyQuery.task;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import cn.proem.dagl.web.fuzzyQuery.service.FuzzyQueryService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.docu.entity.DocuAttachment;

public class CreateIndexThread extends Thread {
    /**
     * @return the all
     */
    public int getAll() {
        return all;
    }

    /**
     * @return the cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * @param all the all to set
     */
    public void setAll(int all) {
        this.all = all;
    }

    /**
     * @param cnt the cnt to set
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    private List<Ywgj> listYwgj;
    private List<DocuAttachment> listAttach;
    public static String FILE_DOC = "doc";
    public static String FILE_DOCX = "docx";
    public static String FILE_XLS = "xls";
    public static String FILE_XLSX = "xlsx";
    public static String FILE_TXT = "txt";
    public static String FILE_PDF = "pdf";
    public FuzzyQueryService service;
    private int all;
    private int cnt;
    
    public CreateIndexThread(List<Ywgj> listYwgj, List<DocuAttachment> listAttach){
        this.listYwgj = listYwgj;
        this.listAttach = listAttach;
        this.all = listYwgj.size() + listAttach.size();
        this.cnt = 0;
    }
    
    public void setService(FuzzyQueryService service) {
        this.service = service;
    }
    
    private String readFile(String path){
        try{
            if(path.endsWith(FILE_DOCX)){
                return ReadFile.readWord2007(path);
            }else if(path.endsWith(FILE_DOC)){
                return ReadFile.readWord(path);
            }else if(path.endsWith(FILE_XLS)){
                return ReadFile.ReadExcel(path);
            }else if(path.endsWith(FILE_XLSX)){
                return ReadFile.readExcel2007(path);
            }else if(path.endsWith(FILE_TXT)){
                return ReadFile.readTxt(path);
            }else if(path.endsWith(FILE_PDF)){
                return ReadFile.readPdf(path);
            }else {
                System.out.println("CreateIndexThread 非文本文件 " + path);
                return "";
            }
        }catch(Exception e){
            System.out.println("CreateIndexThread 005 发生错误 " + path);
            e.printStackTrace();
        }
        return "";
    }
    
    private void addProps(Document doc, Map<String, Object> props){
        for(String key : props.keySet()){
            if(props.get(key) != null) doc.add(new TextField(key, (String) props.get(key), Store.YES));
        }
    }
    
    public void run() {
        service.addThread(this);
        System.out.println("=================索引创建开始=====================");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 分析器
        StandardAnalyzer analyzer = null;
        IndexWriter indexWriter = null;
        Document document = null;
        IndexWriterConfig config = null;
        try {
            analyzer = new StandardAnalyzer(Version.LUCENE_40);
            // analyzer = new IK_CAnalyzer();
            // analyzer = new PaodingAnalyzer();
            FSDirectory directory = FSDirectory.open(new File(ConfigReader.readIndexPath()));
            config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
            indexWriter = new IndexWriter(directory, config);
            indexWriter.deleteAll();
            // 原文挂接建立索引
            for (Ywgj ywgj : listYwgj) {
                String filePath = ywgj.getWjdz();
                String absolutePath = ConfigReader.readAppHome() + filePath;
                System.out.println(absolutePath);
                document = new Document();
                try {
                    document.add(new TextField("content", readFile(absolutePath), Store.YES));
                    document.add(new TextField("fileId", ywgj.getId(), Store.YES));
                    document.add(new TextField("fileName", ywgj.getWjm(), Store.YES));
                    document.add(new TextField("uploadTime", formatter.format(ywgj.getCreateTime()), Store.YES));
                    Map<String, Object> props = service.findRecord(ywgj.getZlsj());
                    if(props != null){
                        addProps(document, props);
                    }
                    indexWriter.addDocument(document);
                } catch (Exception e) {
                    System.out.println("CreateIndexThread 001 发生错误");
                    e.printStackTrace();
                    continue;
                } finally{
                    this.cnt = this.cnt + 1;
                }
                
            }

            // 管养接口附件建立索引
            for (DocuAttachment docuAttachment : listAttach) {
                String filePath = docuAttachment.getFjnr();
                String absolutePath = ConfigReader.readAppHome() + filePath;
                try {
                document = new Document();
                document.add(new TextField("content", readFile(absolutePath), Store.YES));
                document.add(new TextField("fileId", docuAttachment.getId(), Store.YES));
                document.add(new TextField("fileName", docuAttachment.getFjmc(), Store.YES));
                indexWriter.addDocument(document);
                }catch(Exception e){
                    System.out.println("CreateIndexThread 004 发生错误");
                    e.printStackTrace();
                    continue;
                }finally{
                    this.cnt = this.cnt + 1;
                }
            }
            indexWriter.commit();
        } catch (Exception e) {
            System.out.println("CreateIndexThread 002 发生错误");
            e.printStackTrace();
        } finally {
            try {
                indexWriter.close();
            } catch (IOException e) {
                System.out.println("CreateIndexThread 003 发生错误");
                e.printStackTrace();
            }
        }

        System.out.println("=================索引创建结束=====================");
    }
}
