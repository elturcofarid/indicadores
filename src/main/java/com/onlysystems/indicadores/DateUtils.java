package com.onlysystems.indicadores;

import java.sql.Timestamp;
import java.time.*;
import java.util.HashSet;
import java.util.Set;

public class DateUtils {

    public static int calcularDiasEntreFechas(String dateInicio, String dateFin) {

        int dias = 0;

        try {

            if (!dateInicio.equals("") && !dateFin.equals("")) {

                LocalDate fechaInicio = LocalDate.parse(dateInicio.substring(0,10));
                LocalDate fechaFin = LocalDate.parse(dateFin.substring(0,10));

                Set<LocalDate> festivos = new HashSet<>();
                festivos.add(LocalDate.of(2024, 6, 3)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 6, 10)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 7, 1)); // Ejemplo de festivo el 4 de julio

                festivos.add(LocalDate.of(2024, 8, 7)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 8, 19)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 10, 14)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 11, 4)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 11, 11)); // Ejemplo de festivo el 4 de julio
                festivos.add(LocalDate.of(2024, 12, 25)); // Ejemplo de festivo el 4 de julio

                for (LocalDate fecha = fechaInicio; fecha.isBefore(fechaFin); fecha = fecha.plusDays(1)) {
                    if (validaFinSemana(fecha) && !festivos.contains(fecha)) {
                        dias++;
                    }
                }
            } else {
                dias = -1;
            }
        }catch (Exception e){
            dias = -1;
        }
        return dias;
    }

    private static boolean validaFinSemana(LocalDate fecha){
        boolean res = true;
        if (fecha.getDayOfWeek().ordinal() == 5 || fecha.getDayOfWeek().ordinal() == 6){
            res = false;
        }
        return res;
    }

    public static int csti(String str){
        return str.equals("")?0:Integer.parseInt(str);
    }

    public static String tipoAns(String str){
       return
               switch (str){
                   case "DESARROLLO SW - ANS 1200":  yield "Solicitudes varias";
                   case "DESARROLLO SW - ANS 240":   yield "Consultivos";
                   case "DESARROLLO SW - ANS 480":   yield "despliegue integración";
                   case "DESARROLLO SW - ANS 2400":  yield "auditoria";
                   default: yield "Desarrollo";
               };
    }

    public static String mesFecha(String str){
        return str.equals("")?"":LocalDate.parse(str.substring(0,10)).getMonth().name();
    }

}