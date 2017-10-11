package cn.proem.suw.web.common.exception;

/**
 * 用户名重复异常
 * 
 * @author Denny
 */
public class ExistUsernameException extends Throwable
{

    private static final long serialVersionUID = -3758539781190936587L;

    public ExistUsernameException()
    {
        super("用户名已存在");
    }

}