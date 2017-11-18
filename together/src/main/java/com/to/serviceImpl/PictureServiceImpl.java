package com.to.serviceImpl;


import com.to.dao.PictureDao;
import com.to.entity.Picture;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.PictureService;
import com.to.util.FileUtil;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	@Resource
	private PictureDao dao;
	@Override
	public void deletePictureById(Picture p,HttpSession session,HttpServletResponse response) {
		String url = session.getServletContext().getRealPath("");
		dao.deletePictureById(p.getId());
		String path1=p.getImgUrl();
		String p1 = url + path1.substring(1, path1.length());
		System.out.println(FileUtil.deleteFile(p1));
		try {
			ResponseUtil.write(response,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deletePicture(String ids, HttpServletResponse response,
			HttpSession session) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		String url = session.getServletContext().getRealPath("");
		try {
			String[] paths = ids.split(",");
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("ids", paths);
			List<Picture> pictures = dao.getPictureByIds(map);
			for (Picture picture : pictures) {
				String path1 = picture.getImgUrl();
				String p1 = url + path1.substring(1, path1.length());
				FileUtil.deleteFile(p1);
			}
			dao.deleteByIds(map);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	@Override
	public List<Picture> getPictureByHouseId(Integer houseId) {
		return dao.getPictureByHouseId(houseId);
	}

	@Override
	public Picture getPictureById(Integer id) {
		Picture p=dao.getPictureById(id);
		return p;
	}


	public List<Picture> getPictureByPage(Page page) {
		return dao.getPictureByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}


}
