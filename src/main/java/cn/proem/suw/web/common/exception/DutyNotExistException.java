package cn.proem.suw.web.common.exception;

/**
 * 流程类型不存在异常
 * 
 * @author Pan Jilong
 */
public class DutyNotExistException extends Throwable
{

    private static final long serialVersionUID = 5232071591395932610L;

    public DutyNotExistException()
    {
        super("职务不存在");
    }
}