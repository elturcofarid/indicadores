package com.onlysystems.indicadores.service;

import com.onlysystems.indicadores.NegocioUtil;
import com.onlysystems.indicadores.modelo.RegistrosModel;
import com.onlysystems.indicadores.modelo.RegistrosRecord;
import com.onlysystems.indicadores.modelo.TickectClientes;
import com.onlysystems.indicadores.repository.IndicadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndicadoresServicesImpl implements IndicadoresServices{

    @Autowired
    private IndicadoresRepository indicadoresRepository;

    @Override
    public String consultarDatosxfechas(List<RegistrosRecord> registro){
        indicadoresRepository.saveAll(mapperRecordToEntity(registro));
        return "esto es el servicio";
    }

    @Override
    public Map<String, Long> consultaRequerimientosClientesAll(){

        List<RegistrosModel> lista = indicadoresRepository.findAll().stream().filter(
                registro -> registro.getTipoServicio().equals("SERVICIO INTERNO")
        ).collect(Collectors.toList());

        return lista.stream()
                .collect(Collectors.groupingBy(RegistrosModel::getCliente, Collectors.counting()));
    }

    @Override
    public int consultarDatos(){
        return indicadoresRepository.countByTipoServicio("SERVICIO INTERNO");
    }


    private List<RegistrosModel> mapperRecordToEntity(List<RegistrosRecord> records){
        List<RegistrosModel> models = new ArrayList<>();

        for (RegistrosRecord _record : records){
            models.add(convertir(_record));
        }

        return models;

    }


    private RegistrosModel convertir (RegistrosRecord registrosRecord){
        var model = new RegistrosModel();
            model.setTicket(registrosRecord.ticket());
            model.setActividad(registrosRecord.actividad());
            model.setTitulo(registrosRecord.titulo());
            model.setAns(registrosRecord.ans());
            model.setAns_analisado(registrosRecord.ans_analisado());
            model.setBugs_alto(registrosRecord.bugs_alto());
            model.setBugs_bajo(registrosRecord.bugs_bajo());
            model.setBugs_medio(registrosRecord.bugs_medio());
            model.setBugs_reabiertos(registrosRecord.bugs_reabiertos());
            model.setCasos_prueba(registrosRecord.casos_prueba());
            model.setCiclos(registrosRecord.ciclos());
            model.setCola(registrosRecord.cola());
            model.setCliente(registrosRecord.cliente());
            model.setCreado(registrosRecord.creado());
            model.setDesarrollador(registrosRecord.desarrollador());
            model.setDuracion(registrosRecord.duracion());
            model.setDuracion_qa(registrosRecord.duracion_qa());
            model.setFecha_inicio(registrosRecord.fecha_inicio());
            model.setFecha_cierre(registrosRecord.fecha_cierre());
            model.setFecha_entrega_des(registrosRecord.fecha_entrega_des());
            model.setFecha_entrega_pruebas(registrosRecord.fecha_entrega_pruebas());
            model.setFecha_real_entrega_des(registrosRecord.fecha_real_entrega_des());
            model.setFecha_real_entrega_pruebas(registrosRecord.fecha_real_entrega_pruebas());
            model.setLast_changed(registrosRecord.last_changed());
            model.setPropietario(registrosRecord.propietario());
            model.setProceso(registrosRecord.proceso());
            model.setTester(registrosRecord.tester());
            model.setEstimacion_desarrollo(registrosRecord.estimacion_desarrollo());
            model.setEstimacion_pruebas(registrosRecord.estimacion_pruebas());
            model.setTipoServicio(registrosRecord.tipoServicio());
            model.setTipo_ans(registrosRecord.tipo_ans());
            model.setMotivo_devolucion(registrosRecord.motivo_devolucion());
            model.setMes(registrosRecord.mes());
            model.setMesCierre(registrosRecord.mesCierre());

            var desviacion = NegocioUtil.diferenciaInt(registrosRecord.estimacion_desarrollo() ,registrosRecord.duracion());
            model.setDesviacion(desviacion);
            model.setDesviacion_qa(NegocioUtil.diferenciaInt(registrosRecord.estimacion_pruebas() ,registrosRecord.duracion_qa()));

            model.setCecula(NegocioUtil.isCelula(registrosRecord.titulo()));

            model.setEstado(registrosRecord.estado());
            model.setEstado_incidente(registrosRecord.estado_incidente());


            model.setIndicador(desviacion<0?
                    NegocioUtil.convertirToFloat(100.00f + ( (float) desviacion / (float) registrosRecord.duracion() ) * 100.00f):
                    desviacion==999?NegocioUtil.convertirToFloat(999):NegocioUtil.convertirToFloat(100));


            model.setCalidad(!registrosRecord.fecha_real_entrega_pruebas().equals("")?
                    NegocioUtil.convertirToFloat( 1.00f - ((float) ((registrosRecord.bugs_alto()*2) + (registrosRecord.bugs_bajo()*0.5) +
                            (registrosRecord.bugs_medio()*1) +(registrosRecord.bugs_reabiertos()*2) ) / (float) registrosRecord.casos_prueba())) * 100.00f :
                    999 );

            model.setCumplido(NegocioUtil.iscumplido(Integer.parseInt(registrosRecord.tiempo_solucion()), Integer.parseInt(registrosRecord.tiempo_real_solucion())));

        return model;
    }

}
