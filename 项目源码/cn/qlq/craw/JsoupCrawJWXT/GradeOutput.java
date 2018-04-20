package cn.qlq.craw.JsoupCrawJWXT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * �ռ��ɼ�������ɼ�
 * 
 * @author liqiang
 *
 */
@SuppressWarnings("all")
public class GradeOutput {
	/**
	 * �ֻ��ɼ��ļ���
	 */
	private List<Map<String, Object>> datas;

	public GradeOutput() {
		this.datas = new ArrayList<Map<String, Object>>();
	}

	/**
	 * �ռ��ɼ�
	 * 
	 * @param html
	 * @return
	 */
	public String collectGrade(String html) {
		// ����html
		Document document = Jsoup.parse(html);
		// ��ȡ�ɼ����
		Element table = document.select("#Datagrid1").first();
		// ѡ�������ͷ֮���Ԫ��
		Elements trs = table.select("tr:gt(0)");
		for (Element ele : trs) {
			Map result = new LinkedHashMap();
			Elements ele0 = ele.select("td:eq(0)");// �ҵ�ѧ��
			result.put("xuenian", ele0.text());
			Elements ele1 = ele.select("td:eq(1)");// �ҵ�ѧ��
			result.put("xueqi", ele1.text());
			Elements ele3 = ele.select("td:eq(3)");// �ҵ��γ�����
			result.put("kecheng", ele3.text());
			Elements ele8 = ele.select("td:eq(8)");// �ҵ��ɼ�
			result.put("chengji", ele8.text());
			this.datas.add(result);
		}
		return null;
	}

	/**
	 * ����ɼ���htmlҳ��
	 */
	public void outPutGrade() {
		if (this.datas == null || this.datas.size() == 0) {
			return;
		}
		System.out.println("-------��������ȡ���ĳɼ�--------");
		for (Map result : datas) {

			System.out.println(result.get("xuenian") + "\t" + result.get("xueqi") + "\t" + result.get("kecheng") + "\t"
					+ result.get("chengji") + "\t");
		}

	}

	/**
	 * ��������е����ݣ�д����html���߱������ݿ�
	 * 
	 * @throws IOException
	 */
	public void outputDatas2Html() throws IOException {
		if (datas != null && datas.size() > 0) {
			// ��ȡ�ļ��洢λ��
			String directory = ResourcesUtil.getValue("path", "file");
			
			File file = new File(directory+"\\gradeOut.html");
			// ����ļ������ھʹ����ļ�
			if (!file.exists()) {
				file.createNewFile();
			}
			// ����FileWriter�������ļ��������Ϣ(�˹��췽�����Խ���file������Ҳ���Խ���fileName����)
			FileWriter fileWriter = new FileWriter(file);
			// ��ʼд������
			fileWriter.write("<html>");
			fileWriter.write("<head>");
			fileWriter.write("<title>xxx�ɼ���</title>");
			fileWriter
					.write("<style>table{width:100%;table-layout: fixed;word-break: break-all; word-wrap: break-word;}"
							+ "table td{border:1px solid black;width:300px}</style>");
			fileWriter.write("</head>");
			fileWriter.write("<body>");
			fileWriter.write("<table cellpadding='0' cellspacing='0' style='text-align:center;'>");
			fileWriter.write(
					"<tr style='background-color:#95caca;font-size:20px'><td>ѧ��</td><td>ѧ��</td><td>�γ�����</td><td>�ɼ�</td></tr>");

			for (Map<String, Object> data : datas) {
				String xuenian = (String) data.get("xuenian");
				String xueqi = (String) data.get("xueqi");
				String kecheng = (String) data.get("kecheng");
				String chengji = (String) data.get("chengji");
				fileWriter.write("<tr>");
				fileWriter.write("<td>" + xuenian + "</td>");
				fileWriter.write("<td>" + xueqi + "</td>");
				fileWriter.write("<td>" + kecheng + "</td>");
				fileWriter.write("<td>" + chengji + "</td>");
				fileWriter.write("</tr>");

			}
			fileWriter.write("</table>");
			fileWriter.write("</body>");
			fileWriter.write("</html>");
			System.out.println("�ļ��Ѿ������:"+directory);
			// �ر��ļ���
			fileWriter.close();
		}
	}

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}
