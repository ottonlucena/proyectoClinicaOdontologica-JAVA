package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Data
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;

    public Domicilio(String calle, int i, String localidad, String provincia) {
    }

    public Domicilio(String calle, Integer numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Domicilio() {
    }
}
