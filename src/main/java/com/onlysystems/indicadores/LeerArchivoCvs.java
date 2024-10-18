package com.onlysystems.indicadores;

import com.onlysystems.indicadores.modelo.RegistrosModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LeerArchivoCvs {

/*
        public List<RegistrosModel> readCSV(String filePath) {
            List<RegistrosModel> registroList = new ArrayList<>();

            try (Reader reader = new FileReader(filePath);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {



                for (CSVRecord csvRecord : csvParser) {
                    RegistrosModel registro = new RegistrosModel();
                    // Acceder a los valores de cada columna por su índice
                    registro.setTicket(Integer.parseInt(csvRecord.get(1)));
                    String titulo = csvRecord.get(3);
                    registro.setTitulo(titulo);

                    boolean isCecula= false;
                    if (titulo.toUpperCase().contains("SPRINT") || titulo.toUpperCase().contains("HU")  || titulo.toUpperCase().contains("[TUYA]") || titulo.toUpperCase().contains("P.MITI")  || titulo.toUpperCase().contains("BCS")){
                        isCecula=true;
                    }

                    registro.setCecula(isCecula);

                    registro.setCreado(Timestamp.valueOf(csvRecord.get(4)));

                    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Timestamp.valueOf(csvRecord.get(4)).getTime()), ZoneId.systemDefault());

                    // Obtener el mes
                    int mes = dateTime.getMonthValue(); // 1 = enero, 12 = diciembre
                    Month mesEnum = dateTime.getMonth(); // También puedes obtener el enum Month

                        registro.setMes(mesEnum.name());

                    registro.setLast_changed(Timestamp.valueOf(csvRecord.get(5)));
                    registro.setFecha_cierre(csvRecord.get(6));

                    //LocalDateTime dateTimeCierre = LocalDateTime.ofInstant(Instant.ofEpochMilli(Timestamp.valueOf(csvRecord.get(6)).getTime()), ZoneId.systemDefault());


                    System.out.println(" que pasa tio::: " + csvRecord.get(6));
                    if (!csvRecord.get(6).isEmpty()) {
                        Timestamp timestamp = Timestamp.valueOf(csvRecord.get(6));

                        // Convertir el Timestamp a LocalDateTime
                        LocalDateTime dateTimeCierre = timestamp.toLocalDateTime();
                        Month mesEnumCIerre = dateTimeCierre.getMonth(); // También puedes obtener el enum Month

                        registro.setMesCierre(mesEnumCIerre.name());
                    }

                    registro.setCola(csvRecord.get(7));
                    registro.setEstado(csvRecord.get(8));
                    String ans = csvRecord.get(13);
                    registro.setAns(ans);
                        if (ans.equals("DESARROLLO SW - ANS 1200") )
                        {
                            registro.setTipo_ans("Solicitudes varias");
                        }
                           else if (ans.equals("DESARROLLO SW - ANS 240")) {
                            registro.setTipo_ans("Consultivos");
                        } else if (ans.equals("DESARROLLO SW - ANS 480")) {
                            registro.setTipo_ans("despliegue integración");
                        } else if (ans.equals("DESARROLLO SW - ANS 2400")){
                            registro.setTipo_ans("auditoria");
                        }else {
                            registro.setTipo_ans("Desarrollo");
                        }
                    registro.setTipoServicio(csvRecord.get(14));
                    registro.setPropietario(csvRecord.get(15));
                    //tiempo en el que debio ser resuelto
                    int s1, s2 =0;
                    s1 = Integer.parseInt(csvRecord.get(21));
                    s2 = Integer.parseInt(csvRecord.get(39));

                    registro.setTiempo_solucion(s1);
                    registro.setTiempo_real_solucion(s2);

                    if (s1 < 0 || s2 < 0){
                        registro.setCumplido(false);
                    }else{
                        registro.setCumplido(true);
                    }


                    registro.setEstado_final(csvRecord.get(48));
                    registro.setProceso(csvRecord.get(56));
                    registro.setActividad(csvRecord.get(57));
                    registro.setTicket_maestro(csvRecord.get(58)!=null?0:Integer.parseInt(csvRecord.get(58)));
                    registro.setCliente(csvRecord.get(74));
                    registro.setEstado_incidente(csvRecord.get(128));
                    registro.setAns_analisado(csvRecord.get(130));
                    registro.setMotivo_devolucion(csvRecord.get(146));

                    registro.setEstimacion_desarrollo(csvRecord.get(153)!=null?0:Integer.parseInt(csvRecord.get(153)));
                    registro.setEstimacion_pruebas(csvRecord.get(154).equals("")?0:Integer.parseInt(csvRecord.get(154)));
                    registro.setEstimacion_total(csvRecord.get(155)!=null?0:Integer.parseInt(csvRecord.get(155)));


                    LocalDate fechaInicio = null;
                    if (csvRecord.get(156).length()>4){
                        fechaInicio = LocalDate.parse(csvRecord.get(156));
                    }


                    registro.setFecha_inicio(csvRecord.get(156));

                    LocalDate fechaEntrega = null;
                    if (csvRecord.get(160).length()>4){
                        fechaEntrega = LocalDate.parse(csvRecord.get(160));
                    }


                    registro.setFecha_entrega_des(csvRecord.get(160));

                    LocalDate fechaEntregaQa = null;
                    if (csvRecord.get(161).length()>4){
                        fechaEntregaQa = LocalDate.parse(csvRecord.get(161));
                    }
                    registro.setFecha_entrega_pruebas(csvRecord.get(161));

                    int casoPruebas = csvRecord.get(164).equals("")?0:Integer.parseInt(csvRecord.get(164));
                    int bugAlto = csvRecord.get(165).equals("")?0:Integer.parseInt(csvRecord.get(165));
                    int bugMedio = csvRecord.get(166).equals("")?0:Integer.parseInt(csvRecord.get(166));
                    int bugReabierto = csvRecord.get(169).equals("")?0:Integer.parseInt(csvRecord.get(169));
                    int bugBajo = csvRecord.get(167).equals("")?0:Integer.parseInt(csvRecord.get(167));

                    registro.setCasos_prueba(casoPruebas);
                    registro.setBugs_alto(bugAlto);
                    registro.setBugs_medio(bugMedio);
                    registro.setBugs_bajo(bugBajo);
                    registro.setBugs_reabiertos(bugMedio);

                    int ciclos = csvRecord.get(168).equals("")?0:Integer.parseInt(csvRecord.get(168));

                    registro.setCiclos(ciclos);
                    registro.setCalidad(convertir(1.00f - ((float) ((bugAlto*2) + (bugBajo*0.5) + (bugMedio*1) +(bugReabierto*2) ) / (float) casoPruebas)) * 100.00f);


                    registro.setOla_incidente(csvRecord.get(256));

                    LocalDate fechaReal = null;
                    if (csvRecord.get(302).length()>6){
                        fechaReal = LocalDate.parse(csvRecord.get(302).substring(0,10));
                    }
                    if (fechaReal!= null)
                    registro.setFecha_real_entrega_des(csvRecord.get(302).length()<10?"":csvRecord.get(302).substring(0,10));

                    int duracion = 0;
                    if (fechaInicio != null && fechaReal != null)
                    {
                        duracion = DateUtils.calcularDiasEntreFechas(fechaInicio,fechaReal);
                        registro.setDuracion(duracion);
                    }


                    int estimacion = 0;
                    if(fechaInicio != null && fechaEntrega != null){
                        estimacion = DateUtils.calcularDiasEntreFechas(fechaInicio,fechaEntrega);
                        registro.setEstimacion(estimacion);
                    }

                    int desviacion = 0;
                    if(fechaEntrega != null && fechaReal != null){
                         desviacion = DateUtils.calcularDiasEntreFechas(fechaEntrega,fechaReal);
                        registro.setDesviacion(desviacion);
                    }


                    if (duracion > 0 ){
                        registro.setIndicador(convertir( 100.00f - ( (float) desviacion / (float) duracion ) * 100.00f)) ;
                    }else if ((fechaEntrega != null && fechaReal != null) && duracion == 0 &&  DateUtils.calcularDiasEntreFechas(fechaEntrega,fechaReal) == 0){
                        registro.setIndicador(100);
                    }

                    //desvio estimacion desarrollo
                    if (estimacion > 0 )
                        registro.setDed((100*desviacion)/estimacion);


                    LocalDate fechaRealQa = null;
                    if (csvRecord.get(303).length()>6){
                        fechaRealQa = LocalDate.parse(csvRecord.get(303).substring(0,10));
                    }
                    registro.setFecha_real_entrega_pruebas(csvRecord.get(303).length()<10?"":csvRecord.get(303).substring(0,10));

                    int estimacionQa=0;
                    if(fechaEntrega!=null && fechaEntregaQa!=null) {
                        estimacionQa = DateUtils.calcularDiasEntreFechas(fechaEntrega, fechaEntregaQa);
                        registro.setEstimacion_qa(estimacionQa);
                    }

                    //duracion de pruebas de Qa fecha entrega real de desarrollo menos fecha entrega de QA
                    int duracionQa = 0 ;
                    if (fechaReal !=null && fechaRealQa != null){
                        duracionQa = DateUtils.calcularDiasEntreFechas(fechaReal, fechaRealQa);
                        registro.setDuracion_qa(duracionQa);
                    }


                    int desviacionQa=0;
                    if(fechaRealQa != null && fechaEntregaQa != null){
                        desviacionQa = duracionQa - estimacionQa;
                        registro.setDesviacion_qa(desviacionQa);
                    }






                    if (duracionQa > 0  ){
                        registro.setIndicador_qa(convertir(100.00f - ( (float) desviacionQa / (float) duracionQa ) * 100.00f)); ;
                    }


                    registro.setDesarrollador(csvRecord.get(305));
                    registro.setTester(csvRecord.get(306));
                    registroList.add(registro);

                    int dias=0;
                    LocalDate f_sol = null;
                   LocalDate f_crea = LocalDate.parse(csvRecord.get(4).substring(0,10));

                   if (csvRecord.get(6).length()>6) {
                       f_sol = LocalDate.parse(csvRecord.get(6).substring(0, 10));
                       dias =  DateUtils.calcularDiasEntreFechas(f_crea, f_sol);
                   }else{
                       dias = -1;
                   }
                   registro.setDias_solucion(dias);





                }



                System.out.println(DateUtils.calcularDiasEntreFechas(LocalDate.now().plusDays(-3),LocalDate.now().plusDays(5)));

                System.out.println("Lectura de archivo CSV completada.");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return registroList;
        }


        private float convertir(float numero){
            return Math.round(numero * 100) / 100f;
        }

 */

}
