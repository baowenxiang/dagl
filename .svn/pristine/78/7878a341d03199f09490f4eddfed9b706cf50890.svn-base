package cn.proem.dagl.web.scheduled;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.proem.core.dao.GeneralDao;
import cn.proem.core.model.Order;
import cn.proem.core.model.OrderType;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.scheduled.entity.ScheduledTask;
import cn.proem.dagl.web.scheduled.task.HttpTask;
import cn.proem.dagl.web.scheduled.task.impl.HttpTaskImpl;
import cn.proem.suw.web.common.model.BaseCtrlModel;

@Component
public class ScheduledTasks extends BaseCtrlModel {
    
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    //开启Session
    private Session s = null;
    
    
    //@Scheduled(cron="0 */1 * * * ?")
	public void Hour() {
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		List<ScheduledTask> tasks = getSchedule("1");
		for(ScheduledTask task: tasks){
		    HttpTask createindex = new HttpTaskImpl(task.getUrl());
		    try {
                System.out.println(time + " " + task.getTaskname() + " executed :" +createindex.execute());
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
    
    /**
     * @Method: getCurrentSession 
     * @Description: 获得连接
     * @return
     */
    private Session getCurrentSession()
    {
       
        return sessionFactory.openSession();
       
    }
    
    /**
     * @Method: getSchedule 
     * @Description: TODO
     * @param executetime
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<ScheduledTask> getSchedule(String executetime){
        s = sessionFactory.openSession();
        Criteria criteria = getCurrentSession().createCriteria(ScheduledTask.class);
        criteria.add(Restrictions.eq("executetime", executetime));
        List<ScheduledTask> schedule = criteria.list();
        s.close();
        return schedule;
    }
    
    
    //@Scheduled(cron="0 0 1 * * ?")
    public void OneHour() throws URISyntaxException, IOException{
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        List<ScheduledTask> tasks = getSchedule("1");
        for(ScheduledTask task: tasks){
            HttpTask createindex = new HttpTaskImpl(task.getUrl());
            try {
                System.out.println(task.getTaskname() + " executed at " + time + " : " +createindex.execute());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //@Scheduled(cron="0 0 2 * * ?")
    public void TwoHour() throws URISyntaxException, IOException{
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        List<ScheduledTask> tasks = getSchedule("2");
        for(ScheduledTask task: tasks){
            HttpTask createindex = new HttpTaskImpl(task.getUrl());
            try {
                System.out.println(task.getTaskname() + " executed at " + time + " : " +createindex.execute());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //@Scheduled(cron="0 0 3 * * ?")
    public void ThreeHour() throws URISyntaxException, IOException{
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        List<ScheduledTask> tasks = getSchedule("3");
        for(ScheduledTask task: tasks){
            HttpTask createindex = new HttpTaskImpl(task.getUrl());
            try {
                System.out.println(task.getTaskname() + " executed at " + time + " : " +createindex.execute());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
   //@Scheduled(cron="0 0 4 * * ?")
    public void FourHour() {
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        List<ScheduledTask> tasks = getSchedule("4");
        for(ScheduledTask task: tasks){
            HttpTask createindex = new HttpTaskImpl(task.getUrl());
            try {
                System.out.println(task.getTaskname() + " executed at " + time + " : " +createindex.execute());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //@Scheduled(cron="0 0 5 * * ?")
    public void FiveHour() {
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        List<ScheduledTask> tasks = getSchedule("5");
        for(ScheduledTask task: tasks){
            HttpTask createindex = new HttpTaskImpl(task.getUrl());
            try {
                System.out.println(task.getTaskname() + " executed at " + time + " : " +createindex.execute());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
