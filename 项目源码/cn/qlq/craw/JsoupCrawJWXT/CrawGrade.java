package cn.qlq.craw.JsoupCrawJWXT;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * ��ȡ�ɼ�����
 * 
 * @author liqiang
 *
 */
public class CrawGrade {
	
	private String viewState;
	/**
	 * ȫ�ֻ�ȡviewstate�ĺ���
	 * @param html
	 * @return
	 */
	public  String getViewState(String html){
		Document document = Jsoup.parse(html);
		Element ele = document.select("input[name='__VIEWSTATE']").first();
		String value = ele.attr("value");
		this.viewState = value;
		// ��ȡ��viewState
		return value;
	}

	/**
	 * ����������ҳ��
	 * 
	 * */
	
	public String crawGradeLastPage(Map<String,String> cookies,String viewStata,String xuehao) throws IOException{
		String urlLogin = "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh="+xuehao+"&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613";
		Connection connect = Jsoup.connect(urlLogin);
		connect.timeout(5 * 100000);
		// α������ͷ
		connect.header("Content-Length", "74642").header("Content-Type", "application/x-www-form-urlencoded");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh=201420020123&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		// Я����½��Ϣ
		connect.data("xh","201420020123")
			.data("xm", viewStata)
			.data("hidLanguage", "")
			.data("gnmkdm", "N121613");
		//����cookie
		connect.cookies(cookies);
		
		Document document = connect.post();
		System.out.println("-----------�����ĳɼ�����һ��ҳ��--------------");
		String html = document.toString();
		System.out.println(html);
		// ���»�ȡ��viewState
		this.getViewState(html);
		return html;

		
	}
	
	/**
	 * ��ȡ�ɼ�ҳ��
	 */
	public String crawGrade(String xuenian,String xueqi,Map<String,String> cookies,String viewStata,String xuehao) throws IOException{
		String urlLogin = "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh="+xuehao+"&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613";
		Connection connect = Jsoup.connect(urlLogin);
		connect.timeout(5 * 100000);
		// α������ͷ
		connect.header("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.header("Accept-Encoding", "gzip, deflate");
		connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
		connect.header("Content-Length", "74642").header("Content-Type", "application/x-www-form-urlencoded");
		connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/xscjcx.aspx?xh=201420020123&xm=%C7%C7%C0%FB%C7%BF&gnmkdm=N121613");
		connect.header("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		
		// Я����½��Ϣ
		connect.data("__EVENTTARGET","")
		.data("__EVENTARGUMENT", "")
		.data("__VIEWSTATE", this.viewState)
		.data("hidLanguage","")
		.data("ddlXN", xuenian)
		.data("ddlXQ", xueqi)
		.data("btn_xn", "")
		.data("ddl_kcxz", "");
		
		connect.cookies(cookies);
		
		Document document = connect.post();
		System.out.println("-----------�����ĳɼ���ҳ��--------------");
		String html = document.toString();
		//����viewstate
		this.getViewState(html);
		System.out.println(html);
		return html;
	}

	public void setViewState(String viewState) {
		this.viewState = viewState;
	}
	
	
	
}
