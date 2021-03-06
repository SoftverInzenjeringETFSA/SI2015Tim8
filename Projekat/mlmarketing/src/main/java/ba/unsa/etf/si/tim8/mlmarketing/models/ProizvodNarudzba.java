package ba.unsa.etf.si.tim8.mlmarketing.models;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated May 13, 2016 11:55:21 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProizvodNarudzba generated by hbm2java
 */
@Entity
@Table(name = "proizvod_narudzba", catalog = "tim8")
public class ProizvodNarudzba implements java.io.Serializable {

	private Integer id;
	private Narudzba narudzba;
	private Proizvod proizvod;
	private int kolicina;

	public ProizvodNarudzba() {
	}

	public ProizvodNarudzba(Narudzba narudzba, Proizvod proizvod, int kolicina) {
		this.narudzba = narudzba;
		this.proizvod = proizvod;
		this.kolicina = kolicina;
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
	@JoinColumn(name = "narudzbaid", nullable = false)
	public Narudzba getNarudzba() {
		return this.narudzba;
	}

	public void setNarudzba(Narudzba narudzba) {
		this.narudzba = narudzba;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proizvodid", nullable = false)
	public Proizvod getProizvod() {
		return this.proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}

	@Column(name = "kolicina", nullable = false)
	public int getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

}
