package com.notion.service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Service
public class QRService {
	public String createMd5(String msg)
	{
		try
		{
			// Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(msg.getBytes());
            
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);
            
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext;
		}
		catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        }
	}
	
	public void createQRcode(String email,String qrText)
	{
		try
		{
			/*Web Path: ../webapps/notion/WEB-INF/classes/static/qr/*/
			String filePath = "src/main/resources/static/qr/"+email+".png";
			int size = 250;
			String fileType = "png";
			File qrFile = new File(filePath);
			
			// Create the ByteMatrix for the QR-Code that encodes the given String
			Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
			// Now with zxing version 3.2.1 you could change border size (white border size to just 1)
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, size, size, hintMap);
			
			// Make the BufferedImage that are to hold the QRCode
			int matrixWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			
			// Paint and save the image using the ByteMatrix
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, qrFile);
			
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
