package cn.proem.dagl.web.oaservice.service.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.oaservice.entity.OAFjObj;
import cn.proem.dagl.web.oaservice.entity.OAGwObj;
import cn.proem.dagl.web.oaservice.entity.TGdfjObj;
import cn.proem.dagl.web.oaservice.entity.TGdwjObj;
import cn.proem.dagl.web.oaservice.entity.TYwgjObj;
import cn.proem.dagl.web.oaservice.service.GdfjService;
import cn.proem.dagl.web.oaservice.service.GdwjService;
import cn.proem.dagl.web.oaservice.service.IYwgjService;
import cn.proem.dagl.web.oaservice.service.OagdService;
import cn.proem.dagl.web.preArchive.entity.Ywgj;
import cn.proem.dagl.web.scheduled.dto.Task;
import cn.proem.dagl.web.scheduled.task.OAThread;
import cn.proem.suw.web.common.constant.Path;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.common.util.StringUtil;

@Service
public class OagdServiceImpl implements OagdService {
    @Autowired
    private IYwgjService ywgjService;
    @Autowired
    private GdfjService gdfjService;
    @Autowired
    private GdwjService gdwjService;
    @Resource
    private GeneralDao generalDao;
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Map<Long, OAThread> tasks = new HashMap<Long, OAThread>();

    @Transactional
    @Override
    public Long addOaGdSj(String path, String startDate, String endDate) {
        OAThread oa = new OAThread(sessionFactory, gdwjService, generalDao, ywgjService, gdfjService);
        oa.setOADate(path, startDate, endDate);
        oa.start();
        Long threadid = oa.getId();
        tasks.put(threadid, oa);
        return threadid;
    }

    @Override
    public int[] getProcess(Long threadid) {
        OAThread oa = getThread(threadid);
        int[] process = new int[2];
        process[0] = oa.getCnt();
        process[1] = oa.getAll();
        return process;
    }

    private OAThread getThread(Long threadid) {
        return tasks.get(threadid);
    }
    
    private OAThread removeThread(Long threadid) {
        return tasks.remove(threadid);
    }
}
