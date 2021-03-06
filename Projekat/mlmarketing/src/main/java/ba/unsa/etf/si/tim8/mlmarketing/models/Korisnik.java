package ba.unsa.etf.si.tim8.mlmarketing.models;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated May 13, 2016 11:55:21 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Korisnik generated by hbm2java
 */
@Entity
@Table(name = "korisnik", catalog = "tim8", uniqueConstraints = @UniqueConstraint(columnNames = "Username"))
public class Korisnik implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private String adresa;
	private String email;
	private String telefon;
	private String tip;

	public Korisnik() {
	}

	public Korisnik(String username, String password, String ime, String prezime, String adresa, String email,
			String telefon, String tip) {
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.email = email;
		this.telefon = telefon;
		this.tip = tip;
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

	@Column(name = "Username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Ime", nullable = false, length = 45)
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Column(name = "Prezime", nullable = false, length = 45)
	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Column(name = "Adresa", nullable = false, length = 45)
	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Column(name = "Email", nullable = false, length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Telefon", nullable = false, length = 45)
	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Column(name = "Tip", nullable = false, length = 45)
	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

}
