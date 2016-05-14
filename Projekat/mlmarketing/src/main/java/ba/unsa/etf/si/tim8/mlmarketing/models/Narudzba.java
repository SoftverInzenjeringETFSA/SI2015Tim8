package ba.unsa.etf.si.tim8.mlmarketing.models;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated May 13, 2016 11:55:21 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Narudzba generated by hbm2java
 */
@Entity
@Table(name = "narudzba", catalog = "tim8")
public class Narudzba implements java.io.Serializable {

	private Integer id;
	private Akterprodaje akterprodaje;
	private Date datum;
	private String status;
	private Set<ProizvodNarudzba> proizvodNarudzbas = new HashSet<ProizvodNarudzba>(0);

	public Narudzba() {
	}

	public Narudzba(Akterprodaje akterprodaje, Date datum, String status) {
		this.akterprodaje = akterprodaje;
		this.datum = datum;
		this.status = status;
	}

	public Narudzba(Akterprodaje akterprodaje, Date datum, String status, Set<ProizvodNarudzba> proizvodNarudzbas) {
		this.akterprodaje = akterprodaje;
		this.datum = datum;
		this.status = status;
		this.proizvodNarudzbas = proizvodNarudzbas;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "akterid", nullable = false)
	public Akterprodaje getAkterprodaje() {
		return this.akterprodaje;
	}

	public void setAkterprodaje(Akterprodaje akterprodaje) {
		this.akterprodaje = akterprodaje;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datum", nullable = false, length = 19)
	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Column(name = "status", nullable = false, length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "narudzba")
	public Set<ProizvodNarudzba> getProizvodNarudzbas() {
		return this.proizvodNarudzbas;
	}

	public void setProizvodNarudzbas(Set<ProizvodNarudzba> proizvodNarudzbas) {
		this.proizvodNarudzbas = proizvodNarudzbas;
	}

}
