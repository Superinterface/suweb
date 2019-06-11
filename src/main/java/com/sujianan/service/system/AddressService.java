package com.sujianan.service.system;

import com.sujianan.bean.system.District;
import com.sujianan.dao.system.DistrictMapper;
import com.sujianan.util.HttpResponse;
import com.sujianan.util.RST;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	DistrictMapper districtmapper;

	public HttpResponse<Object> getDistrict(District dis) {
		if (dis == null || dis.getPid() == null) {
			dis = new District();
			dis.setPid(1);
		}

		List<District> disList = this.districtmapper.selectByDistrict(dis);
		return new HttpResponse<Object>(RST.CODE_SUCCESS, RST.TEXT_SUCCESS, disList);
	}
}