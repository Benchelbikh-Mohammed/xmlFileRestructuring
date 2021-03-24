import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.w3c.dom.Text;

import models.Country;
import models.Religion;

public class TpMondial {

	private Map<String,Religion> religions;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	public TpMondial()
	{
		solution();
		xmlFileCreation();
	
	}
	
	
	private void xmlFileCreation() {
		try {
			documentBuilderFactory=DocumentBuilderFactory.newInstance();
			documentBuilder=documentBuilderFactory.newDocumentBuilder();
			Document document=documentBuilder.newDocument();
			
			Element root = document.createElement("religions");
			document.appendChild(root);
			
			for (Religion r:religions.values()) {
				Element religion = document.createElement("religion");
				root.appendChild(religion);
				Element nameReligion = document.createElement("name");
				Text nameText = document.createTextNode(r.getName());
				nameReligion.appendChild(nameText);
				religion.appendChild(nameReligion);
				Element countries = document.createElement("countries");
				religion.appendChild(countries);
				for (Country c : r.getCountries()) {
					Element country = document.createElement("country");
					Text countryText = document.createTextNode(c.getName());
					
					country.appendChild(countryText);
					
					countries.appendChild(country);
				}
			}
			
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 5);
			Transformer transformer=transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource=new DOMSource(document);
			StreamResult streamResult=new StreamResult(new File("database/outputMondial.xml"));
			transformer.transform(domSource, streamResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void solution()
	{
		religions=new HashMap<String, Religion>();
		try {
			documentBuilderFactory=DocumentBuilderFactory.newInstance();
			documentBuilder=documentBuilderFactory.newDocumentBuilder();
			Document document=documentBuilder.parse(
					new File("database/mondial-3.0.xml"));
			NodeList liste=document.getElementsByTagName("country");
			//System.out.println(liste.getLength());
			for (int i = 0; i < liste.getLength(); 
					i++) {
				Element element= (Element)liste.item(i);
				NodeList rs=element.getElementsByTagName("religions");
				float max=-1;
				String maxR="";
				for (int j = 0; j < rs.getLength(); 
						j++) 
				{
					Element currentReligion =  (Element) rs.item(j);
					float percentage =Float.parseFloat(currentReligion.getAttribute("percentage"));
					if(max<percentage)
					{
						max=percentage;
						maxR=currentReligion.getTextContent().trim().toLowerCase();
					}
					Religion religion=new Religion();
					religion.setName(currentReligion.getTextContent().trim().toLowerCase());
					
					if(religions.get(religion.getName())==null)
						religions.put(religion.getName(), religion);
				}
				if(religions.get(maxR)!=null)
					religions.get(maxR).getCountries().add(new Country(element.getAttribute("name")));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

		new TpMondial();
	}

}
