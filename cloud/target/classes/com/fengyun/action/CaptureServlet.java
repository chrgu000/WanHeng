package com.fengyun.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;
import com.fengyun.util.ImageUtil;

/**
 * Servlet implementation class Capture
 */
public class CaptureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaptureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		FileItemFactory factory = new DiskFileItemFactory();   
		ServletFileUpload upload = new ServletFileUpload(factory);
		//upload.setSizeMax(262144);//256KB
		List files = null;
		try 
		{
			files = upload.parseRequest(request);
		}
		catch (FileUploadException e) 
		{  
		   e.printStackTrace();
		   
		}
		FileItem imgFile = null;
		Iterator fileItr = files.iterator();
		while (fileItr.hasNext()) 
		{
			imgFile = (FileItem) fileItr.next();
			InputStream stream = imgFile.getInputStream();			
			byte[] data = new byte[(int)imgFile.getSize()];//128k
			stream.read(data);//实际读取的大小
			stream.close();
			StringBuffer base64Data = new StringBuffer();
			BASE64Encoder encoder = new BASE64Encoder();
			base64Data = base64Data.append(encoder.encode(data));
			String base64="data:image/jpg;base64,"+base64Data.toString();
			String url = ImageUtil.changeBase64(base64);
			response.getWriter().println(url);
		}
		}
 	

}
