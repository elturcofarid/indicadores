package com.onlysystems.indicadores;

import com.onlysystems.indicadores.DateUtils;
import com.onlysystems.indicadores.modelo.RegistrosModel;
import com.onlysystems.indicadores.modelo.RegistrosRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class LeerArchivoCvsRecord {


        public List<RegistrosRecord> readCSV(String filePath) {
            List<RegistrosRecord> registroList = new ArrayList<>();

            try (Reader reader = new FileReader(filePath);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

                for (CSVRecord csvRecord : csvParser) {
                    RegistrosRecord registro = new RegistrosRecord(
                            csvRecord.get(1),csvRecord.get(3),csvRecord.get(4),csvRecord.get(5),csvRecord.get(6),csvRecord.get(7),csvRecord.get(8),csvRecord.get(13),csvRecord.get(15),
                            csvRecord.get(57), csvRecord.get(58),csvRecord.get(74),csvRecord.get(128), csvRecord.get(130), csvRecord.get(146), csvRecord.get(256),
                            csvRecord.get(14),csvRecord.get(56),csvRecord.get(305), csvRecord.get(306),csvRecord.get(156), csvRecord.get(160), csvRecord.get(161),
                            csvRecord.get(302),csvRecord.get(303),csvRecord.get(165),csvRecord.get(166),csvRecord.get(167),csvRecord.get(169), csvRecord.get(168),  csvRecord.get(164),
                            csvRecord.get(21), csvRecord.get(39)
                    );



/*



                    LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Timestamp.valueOf(csvRecord.get(4)).getTime()), ZoneId.systemDefault());

                    // Obtener el mes
                    int mes = dateTime.getMonthValue(); // 1 = enero, 12 = diciembre
                    Month mesEnum = dateTime.getMonth(); // También puedes obtener el enum Month

                        registro.setMes(mesEnum.name());



                    System.out.println(" que pasa tio::: " + csvRecord.get(6));
                    if (!csvRecord.get(6).isEmpty()) {
                        Timestamp timestamp = Timestamp.valueOf(csvRecord.get(6));

                        // Convertir el Timestamp a LocalDateTime
                        LocalDateTime dateTimeCierre = timestamp.toLocalDateTime();
                        Month mesEnumCIerre = dateTimeCierre.getMonth(); // También puedes obtener el enum Month

                        registro.setMesCierre(mesEnumCIerre.name());
                    }



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



                    registro.setCalidad(convertir(1.00f - ((float) ((bugAlto*2) + (bugBajo*0.5) + (bugMedio*1) +(bugReabierto*2) ) / (float) casoPruebas)) * 100.00f);

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



                    int desviacionQa=0;
                    if(fechaRealQa != null && fechaEntregaQa != null){
                        desviacionQa = duracionQa - estimacionQa;
                        registro.setDesviacion_qa(desviacionQa);
                    }




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


 */

                    registroList.add(registro);
                }





                //System.out.println(DateUtils.calcularDiasEntreFechas(LocalDate.now().plusDays(-3),LocalDate.now().plusDays(5)));

                System.out.println("Lectura de archivo CSV completada.");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return registroList;
        }


        private float convertir(float numero){
            return Math.round(numero * 100) / 100f;
        }

}
