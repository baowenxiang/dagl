package cn.proem.suw.util;

/**
 * 字符串工具类
 * 
 * @author Pan Jilong
 */
public class StringUtil
{

    /**
     * 字符串首字母转为大写
     * 
     * @author Pan Jilong
     * @date 2016年11月25日
     * @param str
     *            字符串
     * @return String
     */
    public static String toFirstChatUpperCase(String str)
    {
        if (str != null && str.length() != 0)
        {
            char[] chats = str.toCharArray();
            chats[0] = String.valueOf(chats[0]).toUpperCase().toCharArray()[0];
            str = String.valueOf(chats);
        }
        return str;
    }

    /**
     * 字符串首字母转为小写
     * 
     * @author Pan Jilong
     * @date 2016年11月25日
     * @param str
     *            字符串
     * @return String
     */
    public static String toFirstChatLowerCase(String str)
    {
        if (str != null && str.length() != 0)
        {
            char[] chats = str.toCharArray();
            chats[0] = String.valueOf(chats[0]).toLowerCase().toCharArray()[0];
            str = String.valueOf(chats);
        }
        return str;
    }

    /**
     * 流程中参数中 "_"转化为 ":", 存入xml
     * 
     * @author Pan Jilong
     * @date 2016年11月27日
     * @param str
     *            str
     * @return String
     */
    public static String underlineTosemi(String str)
    {
        if (str != null && str.length() != 0 && str.contains("_"))
        {
            String s = "";
            char[] chats = str.toCharArray();
            for (char c : chats)
            {
                if ("_".equals(String.valueOf(c)))
                {
                    c = ':';
                }
                s += c;
            }
            str = s;
        }
        return str;
    }

    /**
     * 判断值不为 null, "", "null"
     * 
     * @author Pan Jilong
     * @date 2016年11月27日
     * @param value
     *            value
     * @return 返回true, 则代表值不为null,"","null"
     */
    public static boolean isNotEmpty(Object value)
    {
        if (value == null || "null".equals(String.valueOf(value)) || "".equals(String.valueOf(value)))
        {
            return false;
        }
        return true;
    }

}
