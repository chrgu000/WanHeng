<%@ page language="java" pageEncoding="UTF-8" import="java.io.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.io.FileUtils"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%
	// 临时文件夹路径
	String path = getServletContext().getRealPath("/")+"/uploadstart";
	String path1 = getServletContext().getRealPath("/")+"/uploadshow";
	// 获得磁盘文件条目工厂  
    DiskFileItemFactory factory = new DiskFileItemFactory();  
     // 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，  
     // 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同  
     /** 
      * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，  
      * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的  
      * 然后再将其真正写到 对应目录的硬盘上 
      */  
     factory.setRepository(new File(path));  
     // 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室  
     factory.setSizeThreshold(1024*1024) ;  
       
     // 高水平的API文件上传处理  
     ServletFileUpload upload = new ServletFileUpload(factory);  
     
		try {  
		        // 可以上传多个文件  
		        List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
		          
		        for(FileItem item : list)  
		        {  
		            // 获取表单的属性名字  
		            String name = item.getFieldName();  
		              
		            // 如果获取的 表单信息是普通的 文本 信息  
		            if(item.isFormField())  
		            {                     
		                // 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的  
		                String value = item.getString() ;  
		                request.setAttribute(name, value);  
		            }  
		            else  
		            {  
			            // 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些  
		                /** 
		                 * 以下三步，主要获取 上传文件的名字 
		                 */  
		                // 获取路径名  
		                String value = item.getName() ;  
		                // 索引到最后一个反斜杠  
		                int start = value.lastIndexOf("\\");  
		                // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
		                String filename = value.substring(start+1);  
		                  
		                request.setAttribute(name, filename);  
		                  
		                // 真正写到磁盘上  它抛出的异常 用exception 捕捉  
		                File f = new File(path,filename);
		                String type=f.getPath().substring(f.getPath().length()-3,f.getPath().length());
		                // 手动写的文件 文件流思想
		                OutputStream outImg = new FileOutputStream(f);  
		                  
		                InputStream inImg = item.getInputStream() ;  
		                  
		                int length = 0 ;  
		                byte [] buf = new byte[1024] ;  
		                
		                // in.read(buf) 每次读到的数据存放在   buf 数组中  
		                while( (length = inImg.read(buf) ) != -1)  
		                {  
		                    //在   buf 数组中 取出数据 写到 （输出流）磁盘上  
		                    outImg.write(buf, 0, length);  
		                }  
		                inImg.close();  
		                outImg.close();
		                
		                // 为临时文件取随机名字
		                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
		        		Random r=new Random();
		        		String imgName="";
// 		        		if(type.equals("pdf")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".pdf";
// 		        		}else if(type.equals("ppt")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".ppt";
// 		        		}else if(type.equals("mp3")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".mp3";
// 		        		}else if(type.equals("xls")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".xls";
// 		        		}else if(type.equals("ord")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".word";
// 		        		}else if(type.equals("zip")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".zip";
// 		        		}else if(type.equals("png")){
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".png";
// 		        		}
// 		        		else{
// 		        			imgName=sdf.format(new Date())+r.nextInt(100)+".jpg";
// 		        		}
		        		//复制上面用流获取的文件
		        			imgName=sdf.format(new Date())+r.nextInt(100)+"."+type;
		        		FileUtils.copyFile(f, new File(path+"\\"+imgName));
		        		f.delete();
		        		/** File file = new File(path+"\\"+imgName);
		        		File dir1 = new File(path1);
		        		//System.out.println("复制 " + file + " 文件到" + dir1 + "目录");
		        		FileUtils.copyFileToDirectory(file,dir1);
		        		/**
		        		File file = new File(path+"\\"+imgName);
		                System.out.println("path:"+file.getPath());
						//目标文件夹
						File dir1 = new File(path1);
						//将文件移动到另一个文件目录下
						boolean success = file.renameTo(new File(dir1,file.getName()));
						System.out.println("boolean:"+success);*/
		        		//String realPath1 = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1); 
						//System.out.println("web URL 路径:"+realPath1); 
		        		// 获取项目地址
		        		String outPath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		                // 相应到前台页面
		        		response.getWriter().print("uploadstart/"+imgName);
		            }   
		        }
		 }   
		catch (FileUploadException e) 
		{  
		    e.printStackTrace();  
		}  

%>