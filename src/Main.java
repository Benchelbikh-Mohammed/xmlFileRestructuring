import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import models.Religion;

public class Main {

	DocumentBuilderFactory documentBuilderFactory;
	DocumentBuilder documentBuilder;
	public Main()
	{
		Mylist religions=new Mylist();
		religions.add(new Religion("islam"));
		religions.add(new Religion("islam"));
		for (Iterator iterator = religions.iterator(); iterator.hasNext();) {
			Religion religion = (Religion) iterator.next();
			System.out.println(religion.getName());
			
		}
		//lecture();
	}
//	public static void main(String[] args) {
//		new Main();
//		
//	}
	private void lecture()
	{
		documentBuilderFactory=DocumentBuilderFactory.newInstance();
		try {
			documentBuilder=documentBuilderFactory.newDocumentBuilder();
			Document document =documentBuilder.parse(new File("database/output.xml"));
			Element racine =document.getDocumentElement();
			for (int i = 0; i < racine.getChildNodes().getLength(); i++) {
				Node n = racine.getChildNodes().item(i);
				if(n.getNodeType()==Node.ELEMENT_NODE)
					System.out.println("value = " +n.getTextContent());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void creation()
	{
		documentBuilderFactory=DocumentBuilderFactory.newInstance();
		try {
			documentBuilder=documentBuilderFactory.newDocumentBuilder();
			Document document= documentBuilder.newDocument();
			Element html=document.createElement("A");
			Element head=document.createElement("B");
			Element body=document.createElement("C");
			document.appendChild(html);
			html.appendChild(head);
			html.appendChild(body);
			
			head.setAttribute("name", "H");
			
			Attr attr = document.createAttribute("id");
			attr.setValue("b1");
			body.setAttributeNode(attr);
			
			Text text=document.createTextNode("Bonjour");
			body.appendChild(text);
			
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", new Integer(5));
			Transformer transformer=transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource=new DOMSource(document);
			StreamResult streamResult=new StreamResult(new File("database/output.xml"));
			transformer.transform(domSource, streamResult);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
