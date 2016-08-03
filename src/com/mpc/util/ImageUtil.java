package com.mpc.util;

import com.mpc.util.gif.AnimatedGifEncoder;
import com.mpc.util.gif.GifDecoder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;

import java.awt.Image;

/**
 *  
 * 이미지 관련 유틸리티
 *  
 * @author hongmin.an
 *
 */
public class ImageUtil {

	/** Constant DEFAULT_THUMB_WIDTH. */
	private static final int DEFAULT_THUMB_WIDTH = 130;

	/** Constant DEFAULT_THUMB_HEIGHT. */
	private static final int DEFAULT_THUMB_HEIGHT = 100;

	/**
	 * 디폴트크기로 섬네일 이미지를 생성한다.
	 * 
	 * @param loadFile
	 *            String
	 * @param saveFile
	 *            String
	 * @return int[]
	 * @throws IOException
	 */
	public static int[] createThumbnail(String loadFile, String saveFile)
			throws IOException {
		return createThumbnail(loadFile, saveFile, DEFAULT_THUMB_WIDTH,
				DEFAULT_THUMB_HEIGHT);
	}

	/**
	 * 섬네일 이미지를 생성한다.
	 * 
	 * @param loadFile
	 *            String
	 * @param saveFile
	 *            String
	 * @param thumbWidth
	 *            int
	 * @param thumbHeight
	 *            int
	 * @return 원본이미지의 폭,높이를 리턴한다.
	 * @throws IOException
	 */
	public static int[] createThumbnail(String loadFile, String saveFile,
			int thumbWidth, int thumbHeight) throws IOException {
		int[] rect = new int[2];
		File save = new File(saveFile);
		String fileExt = loadFile.substring(loadFile.lastIndexOf(".") + 1,
				loadFile.length());

		int iWidth = 0;
		int iHeight = 0;
		InputStream inputStream = null;
		BufferedImage im = null;

		try {
			// 간혹 24bit bmp에서 문제를 일으킴.
			inputStream = new FileInputStream(loadFile);
			im = ImageIO.read(inputStream);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (Exception e) {
				}
		}

		if (im == null) {
			// 간혹 jpeg에서 문제를 일으킴.
			RenderedOp rOp = JAI.create("fileload", loadFile);
			im = rOp.getAsBufferedImage();
		}

		rect[0] = im.getWidth();
		rect[1] = im.getHeight();

		double width = rect[0];
		double height = rect[1];

		if (width > thumbWidth) {
			height = rect[1] * ((thumbWidth * 1.0) / (rect[0]));
			width = thumbWidth;
			if (height > thumbHeight) {
				width = width * ((thumbHeight * 1.0) / height);
			}
		}

		if (height > thumbHeight) {
			height = thumbHeight;
			width = rect[0] * ((thumbHeight * 1.0) / rect[1]);
			if (width > thumbWidth) {
				height = height * (thumbWidth / width);
			}
		}
		iWidth = (int) width;
		iHeight = (int) height;

		if (fileExt.toLowerCase().equals("gif")) {
			gif(loadFile, saveFile, iWidth, iHeight);
		} else {
			Image thumbnail = im.getScaledInstance(iWidth, iHeight,
					Image.SCALE_SMOOTH);
			BufferedImage thumb = new BufferedImage(iWidth, iHeight,
					BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB, TYPE_INT_RGB
			Graphics2D g2 = thumb.createGraphics();
			g2.setColor(Color.white);
			g2.fillRect(0, 0, iWidth, iHeight);
			g2.drawImage(thumbnail, 0, 0, iWidth, iHeight, null);
			ImageIO.write(thumb, fileExt, save);
		}

		return rect;
	}

	/**
	 * Gif.
	 * 
	 * @param loadFile
	 *            String
	 * @param saveFile
	 *            String
	 * @param thumbWidth
	 *            int
	 * @param thumbHeight
	 *            int
	 * @throws IOException
	 */
	public static void gif(String loadFile, String saveFile, int thumbWidth,
			int thumbHeight) throws IOException {
		FileOutputStream out = new FileOutputStream(saveFile);
		GifDecoder d = new GifDecoder();
		AnimatedGifEncoder e = new AnimatedGifEncoder();

		d.read(loadFile);
		int n = d.getFrameCount();
		int loopCount = d.getLoopCount();
		e.setRepeat(loopCount);
		e.start(out);
		for (int i = 0; i < n; i++) {
			BufferedImage frame = d.getFrame(i); // frame i
			int t = d.getDelay(i); // display duration of frame in milliseconds
			e.setDelay(t); // 1 frame per sec
			e.addFrame(frame.getSubimage(0, 0, thumbWidth, thumbHeight));
		}
		e.finish();
		out.flush();
		out.close();
	}
}
