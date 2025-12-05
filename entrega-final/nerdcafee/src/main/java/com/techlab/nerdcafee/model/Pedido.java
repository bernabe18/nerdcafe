package com.techlab.nerdcafee.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Pedido {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cliente_id")
private Cliente cliente;

@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemPedido> items = new ArrayList<>();

private LocalDateTime fecha;
private Double total;

}
