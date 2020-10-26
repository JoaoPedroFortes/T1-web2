package com.br.ufms.cpcx.jp.T1web2.entity;

import com.br.ufms.cpcx.jp.T1web2.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TB_PEDIDO")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PED_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name="PES_ID")
    private Pessoa pessoa;

    @Column(name = "PED_STATUS")
    private Status status;

    @Column(name="PED_DATA_COMPRA")
    private LocalDate DataCompra;

    @Column(name = "PED_DATA_ENTREGA")
    private LocalDate DataEntrega;

    @Column(name = "PED_PERC_DESC")
    private int percentualDesconto;

}
