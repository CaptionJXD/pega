package com.lms.ctaa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Path.Node;
import javax.websocket.Decoder.Text;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import net.sf.json.JSONObject;

public class XmlUtils {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder builder = null;
	private static String url = System.getProperty("user.dir") + "/src/com/cuslink/signature/";

	public static void main(String[] args) {
		try {
			String templateFile = "CULS0001_JGIRP1_CULS01__170703185148076260.YCCG";
			String simple = "YCCG";
			String bzEncode = "ABN";

			// 读取xml，获取发送者，接受者
			// String senderId = readXML("sender_id");
			// String receiverId = readXML("receiver_id");
			// 格式化年月日
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
			String date = sdf.format(new Date());
			// System.out.println(date + " " + senderId + " " + receiverId);
			// 文件名=(发送者+接受者+年月日时分秒).业务拼音简写
			String newFileName = url + date + "." + simple;
			readAndWriteNewXML(bzEncode, newFileName);
			//updateXML(new File(newFileName), bzEncode, new AbnDepartureHead(), new ArrayList<AbnDepartureBody>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * dom读取XML
	 */
	public static String readXML(String nodeName) {
		try {
			builder = factory.newDocumentBuilder();
			File f = new File(url + "CULS0001_JGIRP1_CULS01__170703185148076260.YCCG");
			Document doc = builder.parse(f);
			Element rootEle = doc.getDocumentElement();// 根节点Signature
			NodeList nlist = rootEle.getElementsByTagName(nodeName);
			if (nlist == null || nlist.getLength() < 1)
				return null;
			return nlist.item(0).getTextContent();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 读取模板，更新业务编码，写入新文件
	 * 
	 * @param bzEncode
	 * @param newFileName
	 * @return
	 */
	public static String readAndWriteNewXML(String bzEncode, String newFileName) {
		String xmlStr = "";
		try {
			File f = new File(url + "CULS0001_JGIRP1_CULS01__170703185148076260.YCCG");
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader read = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(read);
			String more;
			while ((more = br.readLine()) != null) {
				xmlStr += more;
			}
			xmlStr = xmlStr.replace(":{bz_encode}", bzEncode);

			File newFile = new File(newFileName);
			newFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(xmlStr.getBytes("utf-8"));
		} catch (Exception e) {
		}
		return xmlStr;
	}

	public static void writeXML(Document doc, String newFileName) {
		try {
			doc.normalize();
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			PrintWriter pw = new PrintWriter(new FileOutputStream(newFileName));
			StreamResult sr = new StreamResult(pw);
			tf.transform(source, sr);
		} catch (Exception e) {
		}
	}

//	public static void updateXML(File newFile, String bzEncode, AbnDepartureHead abnHead,
//			List<AbnDepartureBody> bodyList) {
//		try {
//			builder = factory.newDocumentBuilder();
//			Document doc = builder.parse(newFile);
//			Element root = doc.getDocumentElement();
//
//			// 格式化年月日
//			Node n = root.getElementsByTagName("send_time").item(0);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//			String date = sdf.format(new Date());
//			n.setTextContent(date);
//
//			// 创建表头
//			Element head = doc.createElement("EE_ABN_DEPARTURE_HEAD");
//			JSONObject jsonHead = JSONObject.fromObject(new AbnDepartureHead());
//			Text headText = doc.createTextNode(jsonHead.toString());
//			head.appendChild(headText);
//			// 添加表头
//			doc.getElementsByTagName(bzEncode).item(0).appendChild(head);
//
//			// 遍历表体集合
//			for (AbnDepartureBody abnDepartureBody : bodyList) {
//				// 创建表体
//				Element body = doc.createElement("EE_ABN_DEPARTURE_BODY");
//				Object jsonBody = JSONObject.fromObject(abnDepartureBody);
//				Text bodyText = doc.createTextNode(jsonBody.toString());
//				body.appendChild(bodyText);
//				// 添加表体
//				doc.getElementsByTagName(bzEncode).item(0).appendChild(body);
//			}
//
//			// Node root = doc.getDocumentElement();
//			// NodeList nodes = root.getChildNodes();
//			// for (int i = 0; i < nodes.getLength(); i++) {
//			// Node n = nodes.item(i);
//			// n.setTextContent(n.getTextContent() + "update");
//			// }
//
//			writeXML(doc, newFile.getPath());
//		} catch (Exception e) {
//		}
//	}
}
