package com.onlysystems.indicadores.service;

import com.onlysystems.indicadores.modelo.RegistrosModel;
import com.onlysystems.indicadores.modelo.RegistrosRecord;
import com.onlysystems.indicadores.modelo.TickectClientes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface IndicadoresServices {

    String consultarDatosxfechas(List<RegistrosRecord> registro);

    Map<String, Long> consultaRequerimientosClientesAll();

    int consultarDatos();
}
