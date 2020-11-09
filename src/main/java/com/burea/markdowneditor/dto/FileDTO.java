package com.burea.markdowneditor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DTO del Archivo
 * Ricardo Carrasco S
 * 08-11-2020
 **/
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    @Schema(description = "Identificador del archivo")
    private String id;
    @Schema(description = "Texto del archivo")
    private String data;
    @Schema(description = "Nombre del archivo")
    private String name;
    @Schema(description = "Fecha de creación del archivo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private Date date;
}
