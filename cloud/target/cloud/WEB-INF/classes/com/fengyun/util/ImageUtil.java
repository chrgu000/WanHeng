package com.fengyun.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.fengyun.entity.Teacher;

public class ImageUtil {
	
	public static void saveImageOnline(Teacher teacher,HttpSession session){
		changeBase64(teacher);
		String imgFileUrl="/usr/memory/cgwas/cloud/images/";
		if(teacher.getHead_pic_url()!=null&&teacher.getHead_pic_url().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(teacher.getHead_pic_url(), imgFilePath);
			if(b){
				String head_pic_url=imgFilePath.substring(11, imgFilePath.length());
				teacher.setHead_pic_url(head_pic_url);
			}
		}
		if(teacher.getTeacher_certification()!=null&&teacher.getTeacher_certification().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(teacher.getTeacher_certification(), imgFilePath);
			if(b){
				String teacher_certification=imgFilePath.substring(11, imgFilePath.length());
				teacher.setTeacher_certification(teacher_certification);
			}
		}
		if(teacher.getAcademic_certificate()!=null&&teacher.getAcademic_certificate().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(teacher.getAcademic_certificate(), imgFilePath);
			if(b){
				String academic_certificate=imgFilePath.substring(11, imgFilePath.length());;
				teacher.setAcademic_certificate(academic_certificate);
			}
		}
		if(teacher.getOther_certificate()!=null&&teacher.getOther_certificate().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";

			boolean b=Base64Image.GenerateImage(teacher.getOther_certificate(), imgFilePath);
			if(b){
				String other_certificate=imgFilePath.substring(11, imgFilePath.length());
				teacher.setOther_certificate(other_certificate);
			}	
		}
		if(teacher.getComposition()!=null&&teacher.getComposition().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
            boolean b=Base64Image.GenerateImage(teacher.getComposition(), imgFilePath);
			if(b){
				String composition=imgFilePath.substring(11, imgFilePath.length());
				teacher.setComposition(composition);
			}	
		}
		
	}
	public static void saveImage(Teacher teacher,HttpSession session){
		changeBase64(teacher);
		String imgFileUrl=session.getServletContext().getRealPath("/")+ "uploadstart/images/";
		if(teacher.getHead_pic_url()!=null&&teacher.getHead_pic_url().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(teacher.getHead_pic_url(), imgFilePath);
			if(b){
				String head_pic_url="/"+imgFilePath.substring(imgFilePath.lastIndexOf("/")-18, imgFilePath.length());
				teacher.setHead_pic_url(head_pic_url);
			}
		}
		if(teacher.getTeacher_certification()!=null&&teacher.getTeacher_certification().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(teacher.getTeacher_certification(), imgFilePath);
			if(b){
				String teacher_certification="/"+imgFilePath.substring(imgFilePath.lastIndexOf("/")-18, imgFilePath.length());
				teacher.setTeacher_certification(teacher_certification);
			}
		}
		if(teacher.getAcademic_certificate()!=null&&teacher.getAcademic_certificate().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
			boolean b=Base64Image.GenerateImage(teacher.getAcademic_certificate(), imgFilePath);
			if(b){
				String academic_certificate="/"+imgFilePath.substring(imgFilePath.lastIndexOf("/")-18, imgFilePath.length());
				teacher.setAcademic_certificate(academic_certificate);
			}
		}
		if(teacher.getOther_certificate()!=null&&teacher.getOther_certificate().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";

			boolean b=Base64Image.GenerateImage(teacher.getOther_certificate(), imgFilePath);
			if(b){
				String other_certificate="/"+imgFilePath.substring(imgFilePath.lastIndexOf("/")-18, imgFilePath.length());
				teacher.setOther_certificate(other_certificate);
			}	
		}
		if(teacher.getComposition()!=null&&teacher.getComposition().length()>50){
			String imgFilePath=imgFileUrl+(new Random().nextInt(1000)+""+System.currentTimeMillis()+new Random().nextInt(1000))+".jpg";
            boolean b=Base64Image.GenerateImage(teacher.getComposition(), imgFilePath);
			if(b){
				String composition="/"+imgFilePath.substring(imgFilePath.lastIndexOf("/")-18, imgFilePath.length());
				teacher.setComposition(composition);
			}	
		}
		
	}
	public static String changeBase64(String data){
		if(data!=null&&data.length()>50){
			data=data.substring(data.indexOf(",")+1);
		}
		return GenerateImage(data);
	}
	public static String GenerateImage(String imgStr)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return "";  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String filename = new Date().getTime() + ""
					+ (new Random().nextInt(900) + 100) + ".jpg";
			final File src = new File(
					"/usr/memory/cgwas/cloud/images", filename);
            OutputStream out = new FileOutputStream(src);      
            out.write(b);  
            out.flush();  
            out.close();  
            return "cgwas/cloud/images/"+src.getName();  
        }   
        catch (Exception e)   
        {  e.printStackTrace();
            return "";  
        }  
    }  
	private static  void changeBase64(Teacher teacher){
		if(teacher.getHead_pic_url()!=null&&teacher.getHead_pic_url().length()>50){
			teacher.setHead_pic_url(teacher.getHead_pic_url().substring(teacher.getHead_pic_url().indexOf(",")+1));
		}
		if(teacher.getTeacher_certification()!=null&&teacher.getTeacher_certification().length()>50){
			teacher.setTeacher_certification(teacher.getTeacher_certification().substring(teacher.getTeacher_certification().indexOf(",")+1));
		}
		if(teacher.getAcademic_certificate()!=null&&teacher.getAcademic_certificate().length()>50){
			teacher.setAcademic_certificate(teacher.getAcademic_certificate().substring(teacher.getAcademic_certificate().indexOf(",")+1));
		}
		if(teacher.getOther_certificate()!=null&&teacher.getOther_certificate().length()>50){
			teacher.setOther_certificate(teacher.getOther_certificate().substring(teacher.getOther_certificate().indexOf(",")+1));
		}
		if(teacher.getComposition()!=null&&teacher.getComposition().length()>50){
			teacher.setComposition(teacher.getComposition().substring(teacher.getComposition().indexOf(",")+1));
		}
	}
}
