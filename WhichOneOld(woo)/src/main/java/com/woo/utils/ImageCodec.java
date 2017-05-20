package com.woo.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageCodec {

	private static final int IMG_WIDTH = 1024;
	private static final int IMG_HEIGHT = 768;

	public static byte[] resizeImageConvert(File image) {
		byte[] resizeFile = null;
		if (image == null) {
			return null;
		}
		try {
			BufferedImage originalImage = ImageIO.read(image);
//			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			// BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			// ImageIO.write(resizeImageJpg, "jpg", resizeFile);

			if (originalImage.getWidth() != IMG_WIDTH || originalImage.getHeight() != IMG_HEIGHT) {
				return null;
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			ImageIO.write(originalImage, "jpg", baos);
//			ImageIO.write(resizeImageHintJpg, "jpg", new File("c:\\Emre\\Temp\\" + image.getName() + "i.jpg"));
			baos.flush();
			resizeFile = baos.toByteArray();
			baos.close();
		} catch (Exception e) {
			LogMessage.logx("Image Converter Fail! Filename:" + image.getName());
			e.printStackTrace();
		}

		return resizeFile;

	}

	public static void main(String[] args) {
		resizeImageConvert(new File("c:\\Emre\\Temp\\a.jpg"));
	}

	// private static BufferedImage resizeImage(BufferedImage originalImage, int
	// type) {
	// BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
	// type);
	// Graphics2D g = resizedImage.createGraphics();
	// g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	// g.dispose();
	//
	// return resizedImage;
	// }

	private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		boolean change = false;

		if (width > IMG_WIDTH) {
			width = IMG_WIDTH;
			change = true;
		}
		if (height > IMG_HEIGHT) {
			height = IMG_HEIGHT;
			change = true;
		}
		if (!change) {
			return null;
		}
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

}
