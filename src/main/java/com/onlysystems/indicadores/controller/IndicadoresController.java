package com.onlysystems.indicadores.controller;

import com.google.gson.Gson;
import com.itextpdf.html2pdf.HtmlConverter;
import com.onlysystems.indicadores.LeerArchivoCvs;
import com.onlysystems.indicadores.modelo.RegistrosModel;
import com.onlysystems.indicadores.modelo.TickectClientes;
import com.onlysystems.indicadores.service.IndicadoresServices;
import org.aspectj.util.UtilClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class IndicadoresController {

    Gson gson = new Gson();


    @Autowired
    private IndicadoresServices indicadoresServices;

    private LeerArchivoCvs leerArchivoCvs = new LeerArchivoCvs();



        @GetMapping("/hello")
        public String sayHello() {
            List<RegistrosModel> registro = leerArchivoCvs.readCSV("archivo.csv");
            System.out.println("Servicio:::: " + indicadoresServices.consultarDatosxfechas(registro));
            return "Hello, World!";
        }

    @GetMapping("/cuenta")
    public String sayHello2() {
        System.out.println("Servicio:::: " + indicadoresServices.consultarDatos());
        return "Hello, cuenta!";
    }


    @GetMapping("/consultarReqXClientes")
    public String consultaRequerimientosClientes() {
        Map<String, Long> lista = indicadoresServices.consultaRequerimientosClientesAll();
        List<TickectClientes> tickets = new ArrayList<>();
        lista.forEach((clave, valor) -> {
            tickets.add(new TickectClientes(clave,valor.intValue()));
        });
        return gson.toJson(tickets);
    }



    }


