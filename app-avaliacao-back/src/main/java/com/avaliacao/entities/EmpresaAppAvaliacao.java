package com.avaliacao.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_empresa_app_avaliacao")
public class EmpresaAppAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "empresa_cnpj")
	private String cnpj;
	
	@Column(name = "empresa_razao_social")
	private String razaoSocial;
	
	@Column(name = "empresa_classificacao_da_atividade")
	private String classificacaoAtividade;
	
	@Column(name = "empresa_classificacao_subcategoria_da_atividade")
	private String classificacaoSubcategoriaAtividade;
	
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getClassificacaoAtividade() {
		return classificacaoAtividade;
	}

	public void setClassificacaoAtividade(String classificacaoAtividade) {
		this.classificacaoAtividade = classificacaoAtividade;
	}

	public String getClassificacaoSubcategoriaAtividade() {
		return classificacaoSubcategoriaAtividade;
	}

	public void setClassificacaoSubcategoriaAtividade(String classificacaoSubcategoriaAtividade) {
		this.classificacaoSubcategoriaAtividade = classificacaoSubcategoriaAtividade;
	}

}
