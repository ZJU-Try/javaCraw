package cn.qlq.craw.JsoupCrawJWXT;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


/**
 * ���ĳ��������
 * 
 * @author liqiang
 *
 */
public class MainClass {

	public static void main(String[] args) {
		
		//����ѧ�ź�����		
		System.out.print("��������Ҫ��ѯѧ��:");
		Scanner sc = new Scanner(System.in);
		String xuehao = sc.next();
	    System.out.print("����������:");
	    //String password = sc.next();
	    Console con = System.console();
	    String password= new String(con.readPassword());// ��Ϊ��ȡ�����ַ�����,������Ҫ��new
	    
		// 1.������ҳ����ȡ��֤����viewstate
		try {
			DownloadLoginfo downloadLoginfo = new DownloadLoginfo();
			LoginClass loginClass = new LoginClass();
			GradeOutput gradeOutput = new GradeOutput();
			downloadLoginfo.getLogInfo();
			// 2.��¼
			loginClass.login(downloadLoginfo,xuehao,password);
			for (Entry<String, String> entry : loginClass.getCookies().entrySet()) {
				System.out.println("key:" + entry.getKey() + ";value" + entry.getValue());
			}
			CrawGrade crawGrade = new CrawGrade();
			// ��ȡ�ɼ�����һ��ҳ��
			crawGrade.crawGradeLastPage(downloadLoginfo.getCookies(), downloadLoginfo.getViewState(),xuehao);
			List<String> condition = geneQueryCondition();
			for (String xuenian : condition) {
				String html_content = crawGrade.crawGrade(xuenian, "2", downloadLoginfo.getCookies(),
						// ��ȡ�ɼ�ҳ��
						downloadLoginfo.getViewState(),xuehao);
				gradeOutput.collectGrade(html_content);

			}
			gradeOutput.outputDatas2Html();
		} catch (IOException e) {
			System.out.println("�޷�����ѧУ������");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������Ҫ��ѯ����ݺ�ѧ��
	 * 
	 * @return
	 */
	public static List<String> geneQueryCondition() {
		List<String> condition = new ArrayList<String>();
		condition.add("2014-2015");
		condition.add("2015-2016");
		condition.add("2016-2017");
		condition.add("2017-2018");
		return condition;
	}

}
