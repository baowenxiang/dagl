package cn.proem.suw.web.common.exception;

/**
 * id不存在
 * 
 * @author Denny
 */
public class IdNotExitException extends Throwable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -4643180587559518201L;

    public IdNotExitException()
    {
        super("id不存在");
    }

}
