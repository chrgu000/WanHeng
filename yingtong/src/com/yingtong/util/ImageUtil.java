package com.yingtong.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImageUtil {
	private static final int WIDTH = 80;
	private static final int HEIGHT = 30;

	public static Map<String, Object> createImage() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(0, 0, WIDTH, HEIGHT);
//		StringBuffer number =new StringBuffer("");
		String number="";
		number=r.nextInt(99999)+"";
//		for(int i=0;i<3;i++){
//			number.append(getCode());
//		}
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font(null, Font.ITALIC, 24));
		g.drawString(number.toString(), 5, 25);
		for (int i = 0; i < 5; i++) {
			g
					.setColor(new Color(r.nextInt(255), r.nextInt(255), r
							.nextInt(255)));
			g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r
					.nextInt(HEIGHT));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", number.toString());
		map.put("image", image);
		return map;
	}

	public  static String  getCode(){
	String code = "";
    Random random = new Random();
    String[] rBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f" };
    int r1 = random.nextInt(3) + 11; // ����11��14֮��������
    String str_r1 = rBase[r1];
    int r2;
    if (r1 == 13) {
        r2 = random.nextInt(7); // ����0��7֮��������
    } else {
        r2 = random.nextInt(16); // ����0��16֮��������
    }
    String str_r2 = rBase[r2];
    int r3 = random.nextInt(6) + 10; // ����10��16֮��������
    String str_r3 = rBase[r3];
    int r4;
    if (r3 == 10) {
        r4 = random.nextInt(15) + 1; // ����1��16֮��������
    } else if (r3 == 15) {
        r4 = random.nextInt(15); // ����0��15֮��������
    } else {
        r4 = random.nextInt(16); // ����0��16֮��������
    }
    String str_r4 = rBase[r4];
    byte[] bytes = new byte[2];
    String str_r12 = str_r1 + str_r2;
    int tempLow = Integer.parseInt(str_r12, 16);
    bytes[0] = (byte) tempLow;
    String str_r34 = str_r3 + str_r4;
    int tempHigh = Integer.parseInt(str_r34, 16);
    bytes[1] = (byte) tempHigh;
    code = new String(bytes);
    return code;
	}
	
}
