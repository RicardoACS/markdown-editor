package com.burea.markdowneditor.service.impl;

import com.burea.markdowneditor.dto.FileDTO;
import com.burea.markdowneditor.error.NotFoundException;
import com.burea.markdowneditor.error.ServiceException;
import com.burea.markdowneditor.service.FileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.burea.markdowneditor.util.FileDTOToJsonObjectConverter.jsonObjectFileDTO;

/**
 * Ricardo Carrasco S
 * 08-11-2020
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private static FileWriter file;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");

    @Override
    public void saveFile(FileDTO request) {
        log.info("[Service] Se guardará el archivo: {} en servidor", request.getName());
        try {
            createFolder();
            if (request.getId() == null) {
                request.setId(UUID.randomUUID().toString());
            }
            file = new FileWriter(String.format("files/%s;%s;%s.json", request.getId(), request.getName(), format.parse(format.format(request.getDate())).getTime()));
            file.write(jsonObjectFileDTO(request).toString());
            log.info("[Service] Se ha creado con éxito el archivo");
        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al crear el archivo: ", e);
            throw new ServiceException("Ha ocurrido un error al crear el archivo");
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                log.error("[Service] Ha ocurrido un error al cerrar el archivo: ", e);
                throw new ServiceException("Ha ocurrido un error al crear el archivo");
            }
        }

    }

    @Override
    public List<FileDTO> getFiles() {
        log.info("[Service] Se obtendrán todos los archivos");
        try {
            List<FileDTO> files = new ArrayList<>();
            File file = new File("files");

            if (!file.exists()) {
                return new ArrayList<>();
            }

            for (String f : file.list()) {
                String[] name = f.split(";");
                files.add(FileDTO.builder()
                        .id(name[0])
                        .name(name[1])
                        .date(new Date(Long.parseLong(name[2].replace(".json", ""))))
                        .build());
            }
            return files;

        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al listar los archivos: ", e);
            throw new ServiceException("Ha ocurrido un error al listar todos los archivos");
        }
    }

    @Override
    public FileDTO getFile(String id) {
        log.info("[Service] Se buscará el archivo: {}", id);
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd@HH:mm:ss").create();

            File file = getFileData(id);

            if (file != null) {
                return gson.fromJson(Files.readAllLines(file.getAbsoluteFile().toPath()).get(0), FileDTO.class);
            }

            throw new NotFoundException("No se ha encontrado el archivo");

        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al buscar el archivo: ", e);
            throw new ServiceException("Ha ocurrido un error al buscar el archivo");
        }
    }

    @Override
    public void updateFile(String id, FileDTO request) {
        log.info("[Service] Se modificará el archivo: {}", id);
        try {
            deleteFile(id);
            request.setId(id);
            saveFile(request);
        } catch (Exception e){
            log.error("Ha ocurrido un error al modificar el archivo");
            throw new ServiceException("Ha ocurrido un error al modificar el archivo");
        }
    }

    @Override
    public void deleteFile(String id) {
        log.info("[Service] Se borrará el archivo: {}", id);
        try {
            File file = getFileData(id);

            if (file != null) {
                Files.deleteIfExists(Paths.get(file.getPath()));
                file.delete();
                log.info("[Service] Archivo eliminado con éxito");
            }

        } catch (Exception e) {
            log.error("[Service] Ha ocurrido un error al eliminar el archivo: ", e);
            throw new ServiceException("Ha ocurrido un error al eliminar el archivo");
        }
    }

    private void createFolder() {
        File folder = new File("files");
        if (!folder.exists()) {
            folder.mkdir();
            log.info("[Service] Carpeta creada con éxito");
        }
    }

    private File getFileData(String id) {
        try {
            File file = new File("files");

            if (!file.exists()) {
                return null;
            }

            for (File f : file.listFiles()) {
                if (f.getName().contains(id)) {
                    return f;
                }
            }

            return null;
        } catch (Exception e) {
            log.error("[Service] Error al buscar archivo");
            throw new ServiceException("Error al buscar archivo");
        }
    }
}
