package com.burea.markdowneditor.controller;

import com.burea.markdowneditor.dto.FileDTO;
import com.burea.markdowneditor.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.burea.markdowneditor.util.Endpoint.FILE;
import static com.burea.markdowneditor.util.Endpoint.VERSION_1;

/**
 * Ricardo Carrasco S
 * 08-11-2020
 **/
@Tag(name = "File", description = "Controllador para los archivos")
@RestController
@RequestMapping(VERSION_1 + FILE)
@Slf4j
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(description = "Crea un archivo")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Error")
    @PostMapping
    public void saveFile(@RequestBody FileDTO request) {
        fileService.saveFile(request);
    }

    @Operation(description = "Listado de archivos creado")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Error")
    @GetMapping
    public List<FileDTO> getFiles() {
        return fileService.getFiles();
    }

    @Operation(description = "Archivo")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Error")
    @GetMapping("/{id}")
    public FileDTO getFile(@PathVariable("id") String id) {
        return fileService.getFile(id);
    }

    @Operation(description = "Elimina un archivo")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Error")
    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable("id") String id) {
        fileService.deleteFile(id);
    }

    @Operation(description = "Modifica un archivo")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "500", description = "Error")
    @PutMapping("/{id}")
    public void updateFile(@PathVariable("id") String id, @RequestBody FileDTO request) {
        fileService.updateFile(id, request);
    }
}
