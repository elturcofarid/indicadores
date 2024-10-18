package com.onlysystems.indicadores.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "registro")
@Getter
@Setter
public class RegistrosModel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // o GenerationType.AUTO según tu base de datos
    private Long id;

    private String ticket ;
    private String titulo;
    private String creado;
    private String last_changed;
    private String fecha_cierre;
    private String cola;
    private String estado;
    private String ans;
    private String propietario ;
    private int tiempo_solucion ;
    private int tiempo_real_solucion ;
    private String estado_final ;
    private String actividad ;
    private int ticket_maestro ;
    private String cliente ;
    private String estado_pruebas ;
    private String estado_pruebas_clientes ;
    private String estado_incidente ;
    private String ans_analisado ;
    private int relacion_ticket ;
    private String motivo_devolucion ;
    private String ola_incidente ;
    private String resultado_pruebas_produccion ;
    private String tipoServicio;
    private boolean cumplido;
    private String proceso;
    private String desarrollador;
    private String tester;
    private String fecha_inicio;

    private int estimacion_desarrollo;
    private int     estimacion_pruebas;
    private int estimacion_total;
    private String fecha_real_entrega_des;
    private String fecha_real_entrega_pruebas;
    private String fecha_entrega_des;
    private String fecha_entrega_pruebas;
    private int bugs_alto;
    private int bugs_medio;
    private int bugs_bajo;
    private int bugs_reabiertos;
    private int ciclos;
    private int casos_prueba;
    private int duracion;
    private int estimacion;
    @Column(name = "desviacion", nullable = true)
    private int desviacion;
    private float indicador;

    private int estimacion_qa;
    private int desviacion_qa;
    private float indicador_qa;
    private int duracion_qa;

    @Column(name = "is_celula")
    private boolean isCecula;

    private int dias_solucion;
    private float calidad;

    @Column(name = "desviacion_estimacion_desarrollo")
    private float ded;

    private String tipo_ans;

    private String mes;

    private String mesCierre;


}
