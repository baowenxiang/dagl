package cn.proem.suw.web.common.exception;

/**
 * 字典代码为空异常
 * 
 * @author Pan Jilong
 */
public class NullDeptException extends Throwable
{

    private static final long serialVersionUID = -8282171384902516635L;

    public NullDeptException()
    {
        super("部门不能为空");
    }

}