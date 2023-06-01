package com.example.lab7.Entitys;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "fecha")
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "usuarios_id",nullable = false)
    private Usuario usuariosId;

    @ManyToOne
    @JoinColumn(name = "creditos_id")
    private Credito creditosId;
}
