package cn.proem.suw.web.docu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.proem.core.dao.GeneralDao;
import cn.proem.dagl.web.preArchive.entity.Ywgj;

import cn.proem.suw.web.common.exception.ServiceException;
import cn.proem.suw.web.common.util.ConfigReader;
import cn.proem.suw.web.common.util.StringUtil;
import cn.proem.suw.web.docu.entity.DocWDetail;
import cn.proem.suw.web.docu.entity.DocuAttachment;
import cn.proem.suw.web.docu.entity.DocuDetail;
import cn.proem.suw.web.docu.entity.Info_Docu_Detail;
import cn.proem.suw.web.docu.service.DocuService;

@Service
public class DocuServiceImpl implements DocuService {

	@Autowired
	private GeneralDao generalDao;
	
	@Override
	@Transactional
	public void importDocu(DocuDetail docuDetail) throws ServiceException {
		
		if (StringUtil.isEmpty(docuDetail.getId())) {
			throw new ServiceException("ID为空");
		}
		if (StringUtil.isEmpty(docuDetail.getGdnd())) {
			throw new ServiceException("归档年度为空");
		}
		if (StringUtil.isEmpty(docuDetail.getTm())) {
			throw new ServiceException("题名为空");
		}
		if (StringUtil.isEmpty(docuDetail.getCwrq())) {
			throw new ServiceException("成文日期为空");
		}
		if (StringUtil.isEmpty(docuDetail.getZrz())) {
			throw new ServiceException("责任者为空");
		}
		if (StringUtil.isEmpty(docuDetail.getXgjg())) {
			throw new ServiceException("相关部门为空");
		}
		if (StringUtil.isEmpty(docuDetail.getXbjg())) {
			throw new ServiceException("业务档案类型为空");
		}
		
		if (generalDao.findById(docuDetail.getId(), DocuDetail.class) != null) {
			if(docuDetail.getDelFlag() == 1){
				docuDetail.setDelFlag(0);//保证有被逻辑删除后同id文档再次上传可以查得到
			}
			generalDao.update(docuDetail);
			if("ON".equals(ConfigReader.readGYAUTO())){
			    Info_Docu_Detail info = generalDao.findById(docuDetail.getId(), Info_Docu_Detail.class);
			    if(info != null){
			        info.toInfo(docuDetail);
	                info.setDelFlag(Integer.toString(0));//保证有被逻辑删除后同id文档再次上传可以查得到
	                generalDao.update(info);
			    }else{
			        info = new Info_Docu_Detail();
	                info.toInfo(docuDetail);
	                generalDao.save(info);
			    }
			}
		} else {
			generalDao.save(docuDetail);
			if("ON".equals(ConfigReader.readGYAUTO())){
                Info_Docu_Detail info = new Info_Docu_Detail();
                info.toInfo(docuDetail);
                generalDao.save(info);
            }
		}
	}

	@Override
	@Transactional
	public void importAtta(DocuAttachment docuAttachment) throws ServiceException {
		if (StringUtil.isEmpty(docuAttachment.getId())) {
			throw new ServiceException("ID为空");
		}
		if (StringUtil.isEmpty(docuAttachment.getYsjid())) {
			throw new ServiceException("目录ID为空");
		}
		if (StringUtil.isEmpty(docuAttachment.getFjmc())) {
			throw new ServiceException("附件名称为空");
		}
		if (StringUtil.isEmpty(docuAttachment.getFjnr())) {
			throw new ServiceException("附件内容为空");
		}
		if (StringUtil.isEmpty(docuAttachment.getFjhz())) {
			throw new ServiceException("附件后缀为空");
		}
		if (generalDao.findById(docuAttachment.getId(), DocuAttachment.class) != null) {
			if(docuAttachment.getDelFlag() == 1){
				docuAttachment.setDelFlag(0);//保证有被逻辑删除后同id文档再次上传可以查得到
				
			}
			if("ON".equals(ConfigReader.readGYAUTO())){
			    Ywgj ywgj = generalDao.findById(docuAttachment.getId(), Ywgj.class);
			    if(ywgj != null){
			        ywgj.setDelFlag(0);
	                generalDao.update(ywgj);
			    }else{
			        ywgj = new Ywgj();
	                ywgj.setId(docuAttachment.getId());
	                ywgj.setWjdz(docuAttachment.getFjnr());
	                ywgj.setWjm(docuAttachment.getFjmc());
	                ywgj.setWjlx(docuAttachment.getFjhz());
	                ywgj.setZlsj(docuAttachment.getYsjid());
	                generalDao.save(ywgj);
			    }
                
            }
			generalDao.update(docuAttachment);
		} else {
		    generalDao.save(docuAttachment);
            if("ON".equals(ConfigReader.readGYAUTO())){
                Ywgj ywgj = new Ywgj();
                ywgj.setId(docuAttachment.getId());
                ywgj.setWjdz(docuAttachment.getFjnr());
                ywgj.setWjm(docuAttachment.getFjmc());
                ywgj.setWjlx(docuAttachment.getFjhz());
                ywgj.setZlsj(docuAttachment.getYsjid());
                generalDao.save(ywgj);
            }
		}
	}

    @Override
    public void importWorkFlow(DocWDetail docWDetail) throws ServiceException {
        if (StringUtil.isEmpty(docWDetail.getId())) {
            throw new ServiceException("ID为空");
        }
        if (StringUtil.isEmpty(docWDetail.getYsjid())) {
            throw new ServiceException("目录ID为空");
        }
        if (StringUtil.isEmpty(docWDetail.getYwbsf())) {
            throw new ServiceException("业务标识符为空");
        }
        if (StringUtil.isEmpty(docWDetail.getJgrymc())) {
            throw new ServiceException("机构人员标识符为空");
        }
        if (StringUtil.isEmpty(docWDetail.getWjbsf())) {
            throw new ServiceException("文件标识符为空");
        }
        if (StringUtil.isEmpty(docWDetail.getYwzt())) {
            throw new ServiceException("业务状态为空");
        }
        
        if (!("计划任务".equals(docWDetail.getYwzt()) || "历史行为".equals(docWDetail.getYwzt()))) {
            throw new ServiceException("业务状态只能设置为 '历史行为' 或者 '计划任务'");
        }
        
        if (StringUtil.isEmpty(docWDetail.getYwzt())) {
            throw new ServiceException("业务状态为空");
        }
        
        if (StringUtil.isEmpty(docWDetail.getYwxw())) {
            throw new ServiceException("业务行为为空");
        }
        if (StringUtil.isEmpty(docWDetail.getXwsj())) {
            throw new ServiceException("行为时间为空");
        }
        if (docWDetail.getDelFlag() == null) {
            docWDetail.setDelFlag(0);
        }
        
        if (generalDao.findById(docWDetail.getId(), DocWDetail.class) != null) {
            generalDao.update(docWDetail);
        } else {
            generalDao.save(docWDetail);
        }
        
    }

}
