package de.laliluna.examples;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "AGR_PARAMETROS")
@Table(name = "AGR_PARAMETROS", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class Parametros {

	@Id
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "INSUMO", nullable = false)
	private String insumo;

	@Column(name = "COLUMNA", nullable = false)
	private String columna;

	@Column(name = "TIPO", nullable = false)
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "ID_METODO")
	private Metodos metodo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}

	public String getColumna() {
		return columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Metodos getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodos metodo) {
		this.metodo = metodo;
	}

	@Override
	public String toString() {
		return "Parametros [id=" + id + ", nombre=" + nombre + "]";
	}

}
