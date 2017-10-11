package cn.proem.suw.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 文件工具类
 * 
 * @author Pan Jilong
 */
public class FileUtil
{

    /**
     * 生成文件
     * 
     * @author Pan Jilong
     * @date 2016年11月28日
     * @param str
     *            将要写入文件的字符串
     * @param name
     *            name
     */
    public static void createBpmnFile(String str, String name)
    {
        BufferedWriter writer = null;
        try
        {
            File dir = new File(FileUtil.getProcessPath() + "bpmn\\");
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            File file = new File(FileUtil.getProcessPath() + "bpmn\\" + name);
            if (!file.exists())
            {
                file.createNewFile();
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            writer.write(str);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取项目路径
     * 
     * @author Pan Jilong
     * @date 2016年11月28日
     * @return String
     */
    public static String getProcessPath()
    {
        String path = FileUtil.class.getClassLoader().getResource("").getPath().replaceAll("%20", " ");
        String tempdir;
        String[] classPath = path.split("webapps");
        tempdir = classPath[0];
        if (!"/".equals(tempdir.substring(tempdir.length())))
        {
            tempdir += File.separator;
        }
        return tempdir;
    }
}
