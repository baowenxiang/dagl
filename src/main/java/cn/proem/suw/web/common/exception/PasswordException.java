package cn.proem.suw.web.common.exception;

/**
 * 两次输入密码不一致
 * 
 * @author Denny
 */
public class PasswordException extends Throwable
{

    private static final long serialVersionUID = -3758539781190936587L;

    public PasswordException()
    {
        super("两次输入密码不一致");
    }

}