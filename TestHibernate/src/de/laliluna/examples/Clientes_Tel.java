package de.laliluna.examples;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "CLIENTE_TEL")
@Table(name = "CLIENTE_TEL", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Clientes_Tel {

	@Id
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "NUMERO", nullable = false)
	private String numero;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Clientes_Tel [id=" + id + ", numero=" + numero + "]";
	}

}
