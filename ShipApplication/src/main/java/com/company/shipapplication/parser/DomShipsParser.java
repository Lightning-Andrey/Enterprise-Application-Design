package com.company.shipapplication.parser;



import com.company.shipapplication.dto.ShipDTO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class DomShipsParser {

    public ArrayList<ShipDTO> getShips(String documentName) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(documentName);

        Element fleet = document.getDocumentElement();
        ArrayList<ShipDTO> ships = new ArrayList<>();

        if (!"ships".equals(fleet.getTagName())) return null;

        NodeList xmlShips = fleet.getElementsByTagName("ship");
        for (int i = 0; i < xmlShips.getLength(); i++) {
            Element ship = (Element) xmlShips.item(i);
            String type = "";
            int mass = 0;
            int crew = 0;
            NodeList properties = ship.getElementsByTagName("*");
            for (int j = 0; j < properties.getLength(); j++) {
                Element property = (Element) properties.item(j);
                String tagName = property.getTagName();
                String textContent = property.getTextContent();
                switch (tagName) {
                    case "type" -> type = textContent;
                    case "mass" -> mass = Integer.parseInt(textContent);
                    case "crew" -> crew = Integer.parseInt(textContent);
                }
            }
            ships.add(new ShipDTO(type, mass, crew));
        }

        return ships;
    }
}
