package ba.unsa.etf.si.tim8.mlmarketing.models;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated May 13, 2016 11:55:21 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Regija generated by hbm2java
 */
@Entity
@Table(name = "regija", catalog = "tim8", uniqueConstraints = @UniqueConstraint(columnNames = "ime"))
public class Regija implements java.io.Serializable {

	private Integer id;
	private String ime;
	private String drzava;
	private Set<Akterprodaje> akterprodajes = new HashSet<Akterprodaje>(0);
	private Set<Faktura> fakturas = new HashSet<Faktura>(0);

	public Regija() {
	}

	public Regija(String ime, String drzava) {
		this.ime = ime;
		this.drzava = drzava;
	}

	public Regija(String ime, String drzava, Set<Akterprodaje> akterprodajes, Set<Faktura> fakturas) {
		this.ime = ime;
		this.drzava = drzava;
		this.akterprodajes = akterprodajes;
		this.fakturas = fakturas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ime", unique = true, nullable = false, length = 90)
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Column(name = "drzava", nullable = false, length = 90)
	public String getDrzava() {
		return this.drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "regija")
	public Set<Akterprodaje> getAkterprodajes() {
		return this.akterprodajes;
	}

	public void setAkterprodajes(Set<Akterprodaje> akterprodajes) {
		this.akterprodajes = akterprodajes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "regija")
	public Set<Faktura> getFakturas() {
		return this.fakturas;
	}

	public void setFakturas(Set<Faktura> fakturas) {
		this.fakturas = fakturas;
	}
	
	@Override
	public String toString() {
		return this.ime;
	}
}
