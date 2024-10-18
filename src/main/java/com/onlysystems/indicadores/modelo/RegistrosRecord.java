package com.onlysystems.indicadores.modelo;

import com.onlysystems.indicadores.DateUtils;
import org.springframework.dao.support.DataAccessUtils;

import java.sql.Timestamp;

/*
public record RegistrosRecord (
        String ticket , String titulo, Timestamp creado, Timestamp last_changed, String fecha_cierre, String cola, String estado, String ans, String propietario , int tiempo_solucion , int tiempo_real_solucion , String estado_final ,
        String actividad , int ticket_maestro , String cliente , String estado_pruebas , String estado_pruebas_clientes , String estado_incidente , String ans_analisado , int relacion_ticket , String motivo_devolucion , String ola_incidente ,
        String resultado_pruebas_produccion , String tipoServicio, boolean cumplido, String proceso, String desarrollador, String tester, String fecha_inicio, int estimacion_desarrollo, int estimacion_pruebas, int estimacion_total, String fecha_real_entrega_des,
        String fecha_real_entrega_pruebas, String fecha_entrega_des, String fecha_entrega_pruebas, int bugs_alto, int bugs_medio, int bugs_bajo, int bugs_reabiertos, int ciclos, int casos_prueba, int duracion, int estimacion, int desviacion, float indicador,
        int estimacion_qa, int desviacion_qa, float indicador_qa, int duracion_qa, boolean isCecula, int dias_solucion, float calidad, float ded, String tipo_ans, String mes, String mesCierre
    )
{

 */

    public record RegistrosRecord(String ticket , String titulo, String creado, String last_changed, String fecha_cierre, String cola, String estado, String ans, String propietario,
                                  String actividad, String ticket_maestro , String cliente ,  String estado_incidente , String ans_analisado , String motivo_devolucion , String ola_incidente,
                                  String tipoServicio, String proceso, String desarrollador, String tester, String fecha_inicio, String fecha_entrega_des, String fecha_entrega_pruebas,
                                  String fecha_real_entrega_des, String fecha_real_entrega_pruebas, int bugs_alto, int bugs_medio, int bugs_bajo, int bugs_reabiertos, int ciclos, int casos_prueba,
                                  int estimacion_desarrollo, int estimacion_pruebas, int duracion, int duracion_qa, String tipo_ans, String mes, String mesCierre, String tiempo_solucion , String tiempo_real_solucion
    ) {

        public RegistrosRecord(String ticket, String titulo, String creado, String last_changed, String fecha_cierre, String cola, String estado, String ans, String propietario,
                               String actividad, String ticket_maestro, String cliente, String estado_incidente, String ans_analisado, String motivo_devolucion, String ola_incidente,
                               String tipoServicio, String proceso, String desarrollador, String tester, String fecha_inicio, String fecha_entrega_des, String fecha_entrega_pruebas,
                               String fecha_real_entrega_des, String fecha_real_entrega_pruebas, String bugs_alto, String bugs_medio, String bugs_bajo, String bugs_reabiertos, String ciclos, String casos_prueba, String tiempo_solucion , String tiempo_real_solucion
                               )
        {

            this(ticket, titulo, creado, last_changed, fecha_cierre, cola, estado, ans, propietario, actividad, ticket_maestro, cliente, estado_incidente, ans_analisado, motivo_devolucion, ola_incidente,
                    tipoServicio, proceso, desarrollador, tester, fecha_inicio, fecha_entrega_des, fecha_entrega_pruebas, fecha_real_entrega_des, fecha_real_entrega_pruebas,
                    DateUtils.csti(bugs_alto), DateUtils.csti(bugs_medio),
                    DateUtils.csti(bugs_bajo),
                    DateUtils.csti(bugs_reabiertos), DateUtils.csti(ciclos), DateUtils.csti(casos_prueba),
                    DateUtils.calcularDiasEntreFechas(fecha_inicio, fecha_entrega_des), DateUtils.calcularDiasEntreFechas(fecha_entrega_des, fecha_entrega_pruebas), DateUtils.calcularDiasEntreFechas(fecha_inicio, fecha_real_entrega_des),
                    DateUtils.calcularDiasEntreFechas(fecha_entrega_des, fecha_real_entrega_pruebas), DateUtils.tipoAns(ans), DateUtils.mesFecha(creado), DateUtils.mesFecha(fecha_cierre) ,  tiempo_solucion ,  tiempo_real_solucion);



        }






    }





