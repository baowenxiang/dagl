package cn.proem.dagl.web.oaservice.service;

public interface OagdService {
	public Long addOaGdSj(String path,String startDate,String endDate);

    int[] getProcess(Long threadid);
}
