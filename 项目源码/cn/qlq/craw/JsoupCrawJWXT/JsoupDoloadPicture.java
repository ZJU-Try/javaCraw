package cn.qlq.craw.JsoupCrawJWXT;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Jsoup����ͼƬ�����浽����
 * 
 * @author liqiang
 *
 */
public class JsoupDoloadPicture {

	/**
	 * ����cookie������֤��ͼƬ
	 * 
	 * @param url
	 * @param cookies
	 * @throws IOException
	 */
	public static void downloadImg(String url, Map<String, String> cookies) throws IOException {
		// TODO Auto-generated method stub
		Connection connect = Jsoup.connect(url);
		connect.cookies(cookies);// Я��cookies��ȡͼƬ
		connect.timeout(5 * 10000);
		Connection.Response response = connect.ignoreContentType(true).execute();
		byte[] img = response.bodyAsBytes();
		System.out.println(img.length);
		// ��ȡ�ļ��洢λ��
		String directory = ResourcesUtil.getValue("path", "file");
		savaImage(img, directory, "yzm.png");
	}

	public static void savaImage(byte[] img, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		File dir = new File(filePath);
		try {
			// �ж��ļ�Ŀ¼�Ƿ����
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdir();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(img);
			System.out.println("��֤���Ѿ����ص�:"+filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}