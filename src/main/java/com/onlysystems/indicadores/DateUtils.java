package com.onlysystems.indicadores;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DateUtils {

    public static int calcularDiasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {

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

            int dias=0;

        for (LocalDate fecha = fechaInicio; fecha.isBefore(fechaFin); fecha = fecha.plusDays(1)) {
            if (validaFinSemana(fecha) && !festivos.contains(fecha) ) {
                dias++;
            }
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

}