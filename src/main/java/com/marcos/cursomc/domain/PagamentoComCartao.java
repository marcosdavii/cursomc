package com.marcos.cursomc.domain;

import javax.persistence.Entity;

import com.marcos.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParecelas;
	
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParecelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParecelas() {
		return numeroDeParecelas;
	}

	public void setNumeroDeParecelas(Integer numeroDeParecelas) {
		this.numeroDeParecelas = numeroDeParecelas;
	}

}
