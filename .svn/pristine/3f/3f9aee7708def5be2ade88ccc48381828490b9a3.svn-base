package cn.proem.suw.interceptor;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.proem.core.entity.User;
import cn.proem.core.model.QueryCondition;
import cn.proem.core.service.QueryService;

/**
 * 菜单拦截
 * 
 * @author Pan Jilong
 */
public class MenuInterceptor extends HandlerInterceptorAdapter
{

    @Resource
    protected QueryService queryService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
//        String url = request.getRequestURI().toString();
        String username = (String) request.getSession().getAttribute("PROFILE_NAME");

        // 过滤空用户和公共资源
        if (username == null)
        {
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("alert('您很久未操作，系统已经自动退出！');");
            out.println("window.open ('" + request.getContextPath() + "/w/login','_top')");
            out.println("</script>");
            out.println("</html>");
            response.setContentType("text/html;charset=utf-8");
            return false;
        }else{
            User user = queryService.getUniqueEntityByProperty("username", username, User.class);
            String sql = "select endtime as \"endtime\" from pdagl_userinfo where userid = '" + user.getId() + "'";
            List<Map<String, Object>> userinfos = queryService.getObjectList(sql, 0, 1, new ArrayList<QueryCondition>());
            
            // 检查用户使用期限
            if(userinfos != null && userinfos.size() > 0 && userinfos.get(0) != null){
                String endtime = (String) userinfos.get(0).get("endtime");
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(currentTime);
                try{
                    if(Integer.parseInt(dateString.replaceAll("-", "")) <= Integer.parseInt(endtime.replaceAll("-", ""))) return true;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("alert('您的账户已经过期，请重新申请！');");
            out.println("window.open ('" + request.getContextPath() + "/w/login','_top')");
            out.println("</script>");
            out.println("</html>");
            response.setContentType("text/html;charset=utf-8");
            return false;
        }
    }

}
