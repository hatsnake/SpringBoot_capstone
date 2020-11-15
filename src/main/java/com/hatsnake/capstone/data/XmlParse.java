package com.hatsnake.capstone.data;

import com.hatsnake.capstone.domain.posts.PostsRepository;
import com.hatsnake.capstone.domain.tourList.TourListRepository;
import com.hatsnake.capstone.dto.PostsSaveRequestDto;
import com.hatsnake.capstone.dto.TourListSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParse {

    @Autowired
    private TourListRepository tourListRepository;

	public void save(TourListSaveRequestDto requestDto) {
		try {
			String url = "https://www.visitseoul.net/file_save/OPENAPI/OPEN_API_kr.xml";
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			// DOM Tree가 XML 문서의 구조대로 완성될 수 있게 한다.
	        doc.getDocumentElement().normalize();
	        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	        NodeList nList = doc.getElementsByTagName("Table1");

	        System.out.println("파싱할 리스트 수 : "+nList.getLength());

	        for(int temp = 0; temp < nList.getLength(); temp++) {
	        	Node nNode = nList.item(temp);

	        	if(nNode.getNodeType() == Node.ELEMENT_NODE) {

	        		Element eElement = (Element) nNode;
	        		System.out.println("====================================");
	        		System.out.println((temp+1)+"번 자료");
	        		System.out.println("구분번호 : "+getTagValue("COT_ART_ID", eElement));
	        		System.out.println("x좌표 : "+getTagValue("COT_MAP_POINTX", eElement));
	        		System.out.println("y좌표 : "+getTagValue("COT_MAP_POINTY", eElement));
	        		System.out.println("타이틀 : "+getTagValue("TITLE", eElement));
	        		System.out.println("위치주소 : "+getTagValue("COT_ADDR_NEW", eElement));
	        		System.out.println("전화번호 : "+getTagValue("COT_TEL", eElement));
	        		System.out.println("홈페이지 : "+getTagValue("COT_HOMEPAGE", eElement));
	        		System.out.println("24시간운영여부 : "+getTagValue("COT_24H_SERVICE", eElement));
	        		System.out.println("와이파이여부 : "+getTagValue("WIFI_USE", eElement));
	        		System.out.println("휴무일 : "+getTagValue("COT_CLOSE_DAY", eElement));
	        		System.out.println("운영시간 : "+getTagValue("COT_USE_TIME_DESC", eElement));
	        		System.out.println("장애인 편의시설 : "+getTagValue("COT_TROUBLEMAN_CONVENFAC", eElement));
	        		System.out.println("====================================");
	        		tourListRepository.save(requestDto.toEntity());
	        	}
	        }

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        Node nValue = (Node) nlList.item(0);
        if(nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }

}
