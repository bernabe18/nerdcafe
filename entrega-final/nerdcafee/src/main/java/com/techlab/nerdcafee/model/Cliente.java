package com.techlab.nerdcafee.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Cliente {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nombre;
private String email;

@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonIgnore
private List<Pedido> pedidos = new ArrayList<>();

}
