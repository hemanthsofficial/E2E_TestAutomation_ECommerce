package com.qa.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JSONDataReader {

    public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
        //Converts json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty(jsonFilePath)), StandardCharsets.UTF_8);
        //Converts string to map
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> mapContent =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return mapContent;
    }
}