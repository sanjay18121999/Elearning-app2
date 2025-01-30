package com.example.demo;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public class XmlToJsonUtility {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(XmlToJsonUtility.class);

    public static String convertXmlToJson(String xmlInput) {
        logger.info("Starting XML to JSON conversion...");

        try {
            // Trim XML to remove unwanted spaces or BOM
            xmlInput = xmlInput.trim();

            // Validate XML structure
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(xmlInput.getBytes());
            builder.parse(input);

            // Convert XML to JSON
            JSONObject jsonObject = XML.toJSONObject(xmlInput);

            // Calculate total match score
            JSONArray matches = jsonObject.getJSONObject("Response")
                                          .getJSONObject("ResultBlock")
                                          .getJSONObject("MatchDetails")
                                          .getJSONArray("Match");

            int totalMatchScore = 0;

            // Safely handle JSONArray and cast its elements to JSONObject
            for (int i = 0; i < matches.length(); i++) {
                JSONObject match = matches.getJSONObject(i);
             
                totalMatchScore += match.getInt("Score");
            }

            // Add MatchSummary.TotalMatchScore to the JSON
            jsonObject.getJSONObject("Response")
                      .getJSONObject("ResultBlock")
                      .put("MatchSummary", new JSONObject().put("TotalMatchScore", totalMatchScore));

            logger.info("XML to JSON conversion successful.");
            return jsonObject.toString(4); // Pretty-print JSON with an indent of 4 spaces
        } catch (Exception e) {
            logger.error("Error occurred during XML to JSON conversion", e);
            throw new RuntimeException("Conversion failed: " + e.getMessage());
        }
    }
}
