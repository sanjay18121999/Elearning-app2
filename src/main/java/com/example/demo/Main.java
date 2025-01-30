package com.example.demo;

public class Main {
	public static void main(String[] args) {
        try {
            String xmlInput = """
                <?xml version="1.0" encoding="UTF-8"?>
                <Response>
                    <ResultBlock>
                        <ErrorWarnings>
                            <Errors errorCount="0" />
                            <Warnings warningCount="1">
                                <Warning>
                                    <Number>102001</Number>
                                    <Message>Minor mismatch in address</Message>
                                    <Values>
                                        <Value>Bellandur</Value>
                                        <Value>Bangalore</Value>
                                    </Values>
                                </Warning>
                            </Warnings>
                        </ErrorWarnings>
                        <MatchDetails>
                            <Match>
                                <Entity>John</Entity>
                                <MatchType>Exact</MatchType>
                                <Score>35</Score>
                            </Match>
                            <Match>
                                <Entity>Doe</Entity>
                                <MatchType>Exact</MatchType>
                                <Score>50</Score>
                            </Match>
                        </MatchDetails>
                        <API>
                            <RetStatus>SUCCESS</RetStatus>
                            <ErrorMessage />
                            <SysErrorCode />
                            <SysErrorMessage />
                        </API>
                    </ResultBlock>
                </Response>
            """;

            String jsonOutput = XmlToJsonUtility.convertXmlToJson(xmlInput);
            System.out.println("Converted JSON Output:\n" + jsonOutput);
        } catch (Exception e) {
            System.err.println("Conversion failed: " + e.getMessage());
        }
    }
}


