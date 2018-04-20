package cn.qlq.craw.JsoupCrawJWXT;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * url��ȡͼƬ���ұ��浽����
 * 
 * @author liqiang
 *
 */
public class DownloadLoginfo {
	/**
	 * ��һ�η��ʻ�ȡ��cookie(�鿴���־ͷ���һ��cookie:ASP.NET_SessionId)
	 */
	private  Map<String, String> cookies = null;
	/**
	 * __viewstate	����ϵͳ������֤����Ϣ
	 */
	private  String viewState = null;
	
	public DownloadLoginfo() {
		this.cookies = new HashMap<String,String>();;
		this.viewState = "";
	}

	/**
	 * ��ȡ��¼��Ϣ
	 * ��Ҫ���Ƿ���һ����ҳ�棬��ȡһ��__viewstate��cookie
	 */
	public  void getLogInfo() throws Exception {
		String urlLogin = "http://newjwc.tyust.edu.cn/";
		Connection connect = Jsoup.connect(urlLogin);
		// α������ͷ
		connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
				"gzip, deflate");
		connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
		connect.header("Content-Length", "213").header("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
				.header("X-Requested-With", "XMLHttpRequest");

		// ����url��ȡ��Ӧ��Ϣ
		Response res = connect.ignoreContentType(true).method(Method.POST).execute();// ִ������
		// ��ȡ���ص�cookie
		this.cookies = res.cookies();
		for (Entry<String, String> entry : cookies.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		// ��ȡ��Ӧ��
		String body = res.body();

		// �������淽����ȡ__viewstate
		this.getViewState(body);// ��ȡviewState
		//����������֤��Ĺ�����������֤��
		JsoupDoloadPicture.downloadImg("http://newjwc.tyust.edu.cn/CheckCode.aspx", cookies);;
	}

	/**
	 * ��ȡviewstate
	 * 
	 * @return
	 */
	public  String getViewState(String htmlContent) {
		Document document = Jsoup.parse(htmlContent);
		Element ele = document.select("input[name='__VIEWSTATE']").first();
		String value = ele.attr("value");
		// ��ȡ��viewState
		this.viewState = value;
		return value;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public String getViewState() {
		return viewState;
	}

	public void setViewState(String viewState) {
		this.viewState = viewState;
	}


	
}
