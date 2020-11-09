package com.burea.markdowneditor.service;

import com.burea.markdowneditor.dto.FileDTO;

import java.util.List;

/**
 * Ricardo Carrasco S
 * 08-11-2020
 **/
public interface FileService {

    /**
     * Método para guardar un archivo en servidor
     * @param request Datos del archivo
     */
    void saveFile(FileDTO request);

    /**
     * Obtiene todos los archivos existentes
     * @return listado de archivos
     */
    List<FileDTO> getFiles();

    /**
     * Obtiene un archivo
     * @param id identificador del archivo
     * @return datos del archivo
     */
    FileDTO getFile(String id);

    /**
     * Método para modificar el archivo
     * @param id Identificador del archivo
     * @param request Datos del archivo
     */
    void updateFile(String id, FileDTO request);

    /**
     * Elimina el archivo del servidor
     * @param id Identificador del archivo
     */
    void deleteFile(String id);
}
