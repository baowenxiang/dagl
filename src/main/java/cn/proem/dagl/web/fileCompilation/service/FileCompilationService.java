package cn.proem.dagl.web.fileCompilation.service;

import java.util.List;

import cn.proem.core.model.DataGrid;
import cn.proem.core.model.QueryBuilder;
import cn.proem.dagl.web.fileCompilation.entity.CompileResult;
import cn.proem.dagl.web.fileCompilation.entity.CompileResultMiddle;
import cn.proem.dagl.web.preArchive.entity.Zlsj;
import cn.proem.suw.web.common.exception.ServiceException;

/**
 *档案编研服务层
 * @author lenovo
 *
 */
public interface FileCompilationService {
	
	/**
	 * @Description 保存编研成果
	 * @MethodName saveCompileResult
	 * @author bao
	 * @date 2017年6月5日
	 * @param compileResult
	 * @return
	 * @throws ServiceException String
	 */
	public String saveCompileResult(CompileResult compileResult) throws ServiceException;
	
	/**
	 * @Description 保存编研中间类 
	 * @MethodName saveCompileResultMiddle
	 * @author bao
	 * @date 2017年6月5日
	 * @param compileResultMiddle
	 * @return
	 * @throws ServiceException String
	 */
	public String saveCompileResultMiddle(CompileResultMiddle compileResultMiddle) throws ServiceException;
	
	/**
	 * @Description 分页获得编研结果
	 * @MethodName getCompileResultGrid
	 * @author bao
	 * @date 2017年6月6日
	 * @param queryBuilder
	 * @param nowPage
	 * @param pageSize
	 * @return DataGrid<CompileResult>
	 */
	public DataGrid<CompileResult> getCompileResultGrid(QueryBuilder queryBuilder, int nowPage,int pageSize);
	
	public List<CompileResult> getCompileResults(QueryBuilder queryBuilder);
	
	public DataGrid<CompileResultMiddle> getCompileResultMiddleGrid(QueryBuilder queryBuilder, int nowPage,int pageSize);
	
	/**
	 * @Description 逻辑删除编研
	 * @MethodName deleteCompileResult
	 * @author bao
	 * @date 2017年6月6日
	 * @param compileResultId
	 * @throws ServiceException void
	 */
	public void deleteCompileResult(String compileResultId) throws ServiceException;

}
