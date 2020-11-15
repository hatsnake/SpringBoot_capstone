package com.hatsnake.capstone.domain.sample;

import com.hatsnake.capstone.domain.tourList.TourList;
import com.hatsnake.capstone.domain.tourList.TourListRepository;
import com.hatsnake.capstone.dto.TourListSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XmlParseSample {

    @Autowired
    private TourListRepository tourListRepository;

    @Test
    public void save() {
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

                    TourList tourList = TourList.builder()
                            .cotArtId(getTagValue("COT_ART_ID", eElement))
                            .cotMapPointX(getTagValue("COT_MAP_POINTX", eElement))
                            .cotMapPointY(getTagValue("COT_MAP_POINTY", eElement))
                            .title(getTagValue("TITLE", eElement))
                            .cotAddrNew(getTagValue("COT_ADDR_NEW", eElement))
                            .cotTel(getTagValue("COT_TEL", eElement))
                            .cotHomepage(getTagValue("COT_HOMEPAGE", eElement))
                            .cot24hService(getTagValue("COT_24H_SERVICE", eElement))
                            .wifiUse(getTagValue("WIFI_USE", eElement))
                            .cotCloseDay(getTagValue("COT_CLOSE_DAY", eElement))
                            .cotUseTimeDesc(getTagValue("COT_USE_TIME_DESC", eElement))
                            .cotTroublemanConvenfac(getTagValue("COT_TROUBLEMAN_CONVENFAC", eElement))
                            .build();

                    tourListRepository.save(tourList);
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
