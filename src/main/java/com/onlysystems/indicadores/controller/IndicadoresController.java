package com.onlysystems.indicadores.controller;

import com.google.gson.Gson;
import com.onlysystems.indicadores.modelo.TickectClientes;
import com.onlysystems.indicadores.service.IndicadoresServices;
import com.onlysystems.indicadores.LeerArchivoCvsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class IndicadoresController {

    Gson gson = new Gson();


    @Autowired
    private IndicadoresServices indicadoresServices;

    private LeerArchivoCvsRecord leerArchivoCvs = new LeerArchivoCvsRecord();



        @GetMapping("/hello")
        public String sayHello() {
            System.out.println("Servicio:::: " + indicadoresServices.consultarDatosxfechas(leerArchivoCvs.readCSV("archivo.csv")));
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


