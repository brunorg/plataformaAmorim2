package br.com.muranodesign.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "blog")
@XmlRootElement
public class Blog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idblog")
	 private int Idblog;
	 
	 @Column(name = "titulo")
	 private String titulo;
	 
	 @Column(name = "Descricao")
	 private String Descricao;
	 
	 @Column(name = "data")
	 @Temporal(TemporalType.DATE)
	 private Date data;
	 
	 @Column(name = "imagem")
	 private String imagem;
	 
	 @OneToOne
	 private Oficina oficina;
	 
	 @OneToOne
	 private AnoEstudo anoEstudo;

	public int getIdblog() {
		return Idblog;
	}

	public void setIdblog(int idblog) {
		Idblog = idblog;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public AnoEstudo getAnoEstudo() {
		return anoEstudo;
	}

	public void setAnoEstudo(AnoEstudo anoEstudo) {
		this.anoEstudo = anoEstudo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}
