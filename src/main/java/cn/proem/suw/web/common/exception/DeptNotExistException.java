package cn.proem.suw.web.common.exception;

/**
 * 部门不存在异常
 * 
 * @author Pan Jilong
 */
public class DeptNotExistException extends Throwable
{

    private static final long serialVersionUID = 5232071591395932610L;

    public DeptNotExistException()
    {
        super("部门不存在");
    }
}