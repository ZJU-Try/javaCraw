package cn.qlq.craw.JsoupCrawJWXT;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 * ��¼��
 * @author liqiang
 *
 */
public class LoginClass {
	/**
	 * ��¼���ص�cookie
	 */
	private Map<String, String> cookies = null;

	/**
	 * ģ���¼��ȡcookie��sessionid
	 * 
	 */
	public void login(DownloadLoginfo downloadLoginfo,String xuehao,String mima) throws Exception {
		String urlLogin = "http://newjwc.tyust.edu.cn/default2.aspx";
		Connection connect = Jsoup.connect(urlLogin);
		connect.timeout(5 * 100000);
		// α������ͷ
		connect.header("Content-Length", "213").header("Content-Type", "application/x-www-form-urlencoded");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh="+xuehao+"&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		// ������֤��
		System.out.println("-----------��������֤��---------");
		Scanner sc = new Scanner(System.in);
		String yzm = sc.next();
		sc.close();
		// Я����½��Ϣ
		connect.data("txtUserName", xuehao).data("__VIEWSTATE", downloadLoginfo.getViewState())
				.data("TextBox2", mima).data("Textbox1", "").data("RadioButtonList1", "")
				.data("Button1", "").data("lbLanguage", "").data("hidPdrs", "").data("hidsc", "")
				.data("txtSecretCode", yzm);
		connect.cookies(downloadLoginfo.getCookies());
		// ����url��ȡ��Ӧ��Ϣ
		Response res = connect.ignoreContentType(true).method(Method.POST).execute();// ִ������
		// ��ȡ���ص�cookie
		this.cookies = res.cookies();
		for (Entry<String, String> entry : cookies.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		System.out.println("---------��ȡ�ĵ�¼֮���ҳ��-----------");
		String body = res.body();// ��ȡ��Ӧ��
		System.out.println(body);
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

}
