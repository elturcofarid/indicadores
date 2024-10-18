package com.onlysystems.indicadores;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class NegocioUtil {



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

    public static int diferenciaInt(int v, int f){
        var dif = 999;
        if (v != -1 && f != -1){
            dif = v -f;
        }
        return dif;
    }

    public static boolean isCelula(String titulo){
       return
               titulo.toUpperCase().contains("SPRINT") ||
                       titulo.toUpperCase().contains("HU")  ||
                       titulo.toUpperCase().contains("[TUYA]") ||
                       titulo.toUpperCase().contains("P.MITI")  ||
                       titulo.toUpperCase().contains("BCS")  ;
    }

    public static float convertirToFloat(float numero){
        return Math.round(numero * 100) / 100f;
    }


    public static boolean iscumplido(int v, int f){
        return v >= 0 && f>=0;
    }

}