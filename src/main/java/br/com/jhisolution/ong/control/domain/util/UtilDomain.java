package br.com.jhisolution.ong.control.domain.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UtilDomain {

public static byte[] convertBufferedImageToByte(BufferedImage image) {
		
		byte[] imageInByte = null;
		
		try{

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( image, "jpg", baos );
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
					
		} catch(IOException e){
				System.out.println(e.getMessage());
		}		
		
		return imageInByte;
	}

}
