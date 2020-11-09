package com.burea.markdowneditor.util;

import com.burea.markdowneditor.dto.FileDTO;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Ricardo Carrasco S
 * 08-11-2020
 **/
public class FileDTOToJsonObjectConverter {

    private static SimpleDateFormat formatSave = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");

    private FileDTOToJsonObjectConverter() {

    }

    public static JSONObject jsonObjectFileDTO(FileDTO request) {
        JSONObject objectFile = new JSONObject();
        objectFile.put("id", request.getId());
        objectFile.put("name", request.getName());
        objectFile.put("date", formatSave.format(request.getDate()));
        objectFile.put("data", request.getData());
        
        return objectFile;
    }

}
