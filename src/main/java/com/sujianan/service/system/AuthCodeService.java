package com.sujianan.service.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.sujianan.bean.user.User;
import com.sujianan.util.DefaultUtil;
import com.sujianan.util.HttpResponse;

@Service
public class AuthCodeService {

	/* 宽度 */
	private static final int WIDTH = 140;
	/* 高度 */
	private static final int HEIGHT = 30;
	/* 生成验证码的个数 */
	private static final int COUNT = 6;
	/* 干扰线条数 */
	private static final int LINE_ROW = 4;
	/* 输出的基本码表，如果使用中文，则使用utf-8的码表，类似 \ue234 ，而且应该使用常用字，避免出现偏僻字 */
	// 去除 l 1 避免混淆
	private static final char[] BASECODE = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '2', '3', '4',
			'5', '6', '7', '8', '9' };
	/* 随机数发生器 */
	private static Random random;
	private static BufferedImage img;

	public byte[] createAuthCodeImg(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = null;
		ByteArrayOutputStream  out = null;
		OutputStream resout = null;
		try {
			session = request.getSession();
			out = new ByteArrayOutputStream ();
			this.random = new Random();
			this.img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = img.getGraphics();

			setBackground(graphics);
			drawBorder(graphics);
			drawDot(graphics);
			drawLine(graphics);
			
			String code = drawString(graphics);
			
			session.setAttribute("authCode", code);
			
			ImageIO.write(img, "png", out);
			response.setContentType("image/png");
			response.setHeader("Expires", "-1");
			response.setHeader("Cache-Control", "no-cache");
			response.setStatus(200);
			resout = response.getOutputStream();
			resout.write(out.toByteArray());
			resout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) out.close();
				if(resout != null) resout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return out.toByteArray();
	}

	// 写字
	public static String drawString(Graphics graphics) {
		StringBuffer sb = new StringBuffer();
		Font font = new Font("宋体", Font.BOLD, 18);
		graphics.setFont(font);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < COUNT; i++) {
			String ch = String.valueOf(BASECODE[random.nextInt(BASECODE.length)]);
			sb.append(ch);

			// 设置位置
			int x = i * 20 + random.nextInt(12) + 10;
			int y = random.nextInt(HEIGHT / 3) + 12;

			// 旋转字体
			double theta = Math.PI / 180 * random.nextInt(20);
			// rotate(graphics, theta);

			graphics.drawString(ch, x, y);

			// 恢复。。
			// rotate(graphics, -theta);
		}

		System.out.println("\n\t==========验证码：" + sb.toString() + "==========");
		return sb.toString();
	}

	// 旋转
	public static void rotate(Graphics graphics, double theta) {
		((Graphics2D) graphics).rotate(theta);
	}

	// 画随机线条
	public static void drawLine(Graphics graphics) {
		for (int i = 0; i < LINE_ROW; i++) {
			int x1 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			int x2 = random.nextInt(WIDTH);
			int y2 = random.nextInt(HEIGHT);
			setRandomColor(graphics);
			graphics.drawLine(x1, y1, x2, y2);
		}
	}

	// 画斑点
	public static void drawDot(Graphics graphics) {
		graphics.setColor(Color.red);
		for (int i = 0; i < WIDTH; i++) {
			int x = i;
			int y = random.nextInt(HEIGHT);
			int r = random.nextInt(2);
			// graphics.fillOval(x, y, r, r);
			graphics.drawOval(x, y, r, r);
		}
	}

	// 画边框
	public static void drawBorder(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	}

	// 设置背景
	public static void setBackground(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);// 填充背景色
	}

	// 设置随机的画笔颜色
	public static void setRandomColor(Graphics g) {
		g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
	}

	public static void main(String[] args) throws IOException {
		File f = new File("e:\\a.png");
		f.createNewFile();
		OutputStream out = new FileOutputStream(f);

		random = new Random();
		img = new BufferedImage(AuthCodeService.WIDTH, AuthCodeService.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = img.getGraphics();

		setBackground(graphics);
		drawBorder(graphics);
		drawDot(graphics);
		drawLine(graphics);
		drawString(graphics);

		ImageIO.write(img, "png", out);
		out.close();

	}

}
