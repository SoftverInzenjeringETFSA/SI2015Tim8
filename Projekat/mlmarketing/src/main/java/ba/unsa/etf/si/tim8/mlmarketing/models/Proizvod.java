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
 * Proizvod generated by hbm2java
 */
@Entity
@Table(name = "proizvod", catalog = "tim8", uniqueConstraints = @UniqueConstraint(columnNames = "naziv"))
public class Proizvod implements java.io.Serializable {

	private Integer id;
	private String naziv;
	private double nabavnacijena;
	private double prodajnacijena;
	private int kolicina;
	private Set<ProizvodNarudzba> proizvodNarudzbas = new HashSet<ProizvodNarudzba>(0);
	private Set<ProizvodFaktura> proizvodFakturas = new HashSet<ProizvodFaktura>(0);

	public Proizvod() {
	}

	public Proizvod(String naziv, double nabavnacijena, double prodajnacijena, int kolicina) {
		this.naziv = naziv;
		this.nabavnacijena = nabavnacijena;
		this.prodajnacijena = prodajnacijena;
		this.kolicina = kolicina;
	}

	public Proizvod(String naziv, double nabavnacijena, double prodajnacijena, int kolicina,
			Set<ProizvodNarudzba> proizvodNarudzbas, Set<ProizvodFaktura> proizvodFakturas) {
		this.naziv = naziv;
		this.nabavnacijena = nabavnacijena;
		this.prodajnacijena = prodajnacijena;
		this.kolicina = kolicina;
		this.proizvodNarudzbas = proizvodNarudzbas;
		this.proizvodFakturas = proizvodFakturas;
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

	@Column(name = "naziv", unique = true, nullable = false, length = 90)
	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Column(name = "nabavnacijena", nullable = false, precision = 22, scale = 0)
	public double getNabavnacijena() {
		return this.nabavnacijena;
	}

	public void setNabavnacijena(double nabavnacijena) {
		this.nabavnacijena = nabavnacijena;
	}

	@Column(name = "prodajnacijena", nullable = false, precision = 22, scale = 0)
	public double getProdajnacijena() {
		return this.prodajnacijena;
	}

	public void setProdajnacijena(double prodajnacijena) {
		this.prodajnacijena = prodajnacijena;
	}

	@Column(name = "kolicina", nullable = false)
	public int getKolicina() {
		return this.kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proizvod")
	public Set<ProizvodNarudzba> getProizvodNarudzbas() {
		return this.proizvodNarudzbas;
	}

	public void setProizvodNarudzbas(Set<ProizvodNarudzba> proizvodNarudzbas) {
		this.proizvodNarudzbas = proizvodNarudzbas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proizvod")
	public Set<ProizvodFaktura> getProizvodFakturas() {
		return this.proizvodFakturas;
	}

	public void setProizvodFakturas(Set<ProizvodFaktura> proizvodFakturas) {
		this.proizvodFakturas = proizvodFakturas;
	}
	
	@Override
	public String toString() {
		return naziv;
	}

}
