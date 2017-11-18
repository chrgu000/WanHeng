package com.kg.serviceImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.PictureDao;
import com.kg.entity.PicVo;
import com.kg.entity.Picture;
import com.kg.entity.StylePic;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.page.PicturePage;
import com.kg.service.PictureService;
import com.kg.util.FileUtil;
import com.kg.util.ResponseUtil;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService {
	@Resource
	private PictureDao dao;

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
				String path1 = picture.getPath();
				String path2 = picture.getMin_path();
				String p1 = url + path1.substring(1, path1.length());
				String p2 = url + path2.substring(1, path2.length());
				FileUtil.deleteFile(p1);
				FileUtil.deleteFile(p2);
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

	public List<Picture> getPictureByPage(Page page) {
		return dao.getPictureByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public Integer getPicVo(PicVo picvo) {
		return dao.getPicVo(picvo);
	}

	public Integer getTotalPicNum(Integer babyId) {
		return dao.getTotalPicNum(babyId);
	}

	public List<Picture> getPicturesByBabyId(Integer babyId) {
		return dao.getPicturesByBabyId(babyId);
	}

	public List<Picture> getPicturesByTypeId(PicVo vo) {
		return dao.getPicturesByTypeId(vo);
	}

	public void addBabyPicture(Picture picture, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			picture.setCreate_time(new Timestamp(System.currentTimeMillis()));
			dao.addBabyPicture(picture);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("上传成功");
		} catch (Exception e) {
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public List<Picture> getStylePictureByPage(PicturePage page) {
		return dao.getStylePictureByPage(page);
	}

	public Integer getStyleRows(PicturePage page) {
		return dao.getStyleRows(page);
	}

	public void deleteStylePicture(String ids, HttpServletResponse response,
			HttpSession session) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		String url = session.getServletContext().getRealPath("");
		try {
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("ids", ids.split(","));
			List<StylePic> stylePics = dao.getStylePictureByIds(map);
			for (StylePic stylePic : stylePics) {
				String path1 = stylePic.getImgUrl();
				String path2 = stylePic.getMin_path();
				String p1 = url + path1.substring(1, path1.length());
				String p2 = url + path2.substring(1, path2.length());
				FileUtil.deleteFile(p1);
			    FileUtil.deleteFile(p2);
			}
			dao.deleteStyleByIds(map);
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

}
