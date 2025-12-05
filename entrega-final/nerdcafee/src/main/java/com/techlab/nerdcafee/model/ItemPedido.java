package com.techlab.nerdcafee.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ItemPedido {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private Producto producto;

@ManyToOne
@JoinColumn(name = "pedido_id")
@JsonIgnore
private Pedido pedido;

private Integer cantidad;
}
