package com.cgwas.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 图片验证码工具类
 * 
 * @author Lingwh
 * 
 */
public class ValidateCodeImgUtil {
	private int width = 110;// 定义图片的width
	private int height = 40;// 定义图片的height

	private int fontDistance = 20;// 字体间隔
	private int fontHeight = 25;// 字体大小
	private int codeY = 30;// y轴距离

	/**
	 * 获取验证图片
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public String getCode(HttpServletResponse resp) throws IOException {
		// 获取参数
		Properties props = new Properties();
		Resource resource = new ClassPathResource("email.properties");
		int codeCoun = 0;// 定义图片上显示验证码的个数
		String validateCode = "";

		InputStream in;
		try {
			in = resource.getInputStream();
			props.load(in);
			in.close();

			codeCoun = Integer.valueOf((String) props.get("codeCount"))
					.intValue();
			;
			validateCode = (String) props.get("validateCode");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 随机数组
		String[] codeSequence = validateCode.split(",");
		int codeCounNum = codeCoun;
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// 随机产生codeCounNum数字的验证码。
		for (int i = 0; i < codeCounNum; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random
					.nextInt(codeSequence.length)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * fontDistance, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		

		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
		
		return randomCode.toString();
	}

}