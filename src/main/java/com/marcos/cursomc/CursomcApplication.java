package com.marcos.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcos.cursomc.domain.Categoria;
import com.marcos.cursomc.domain.Cidade;
import com.marcos.cursomc.domain.Cliente;
import com.marcos.cursomc.domain.Endereco;
import com.marcos.cursomc.domain.Estado;
import com.marcos.cursomc.domain.Pagamento;
import com.marcos.cursomc.domain.PagamentoComBoleto;
import com.marcos.cursomc.domain.PagamentoComCartao;
import com.marcos.cursomc.domain.Pedido;
import com.marcos.cursomc.domain.Produto;
import com.marcos.cursomc.domain.enums.EstadoPagamento;
import com.marcos.cursomc.domain.enums.TipoCliente;
import com.marcos.cursomc.repositories.CategoriaRepository;
import com.marcos.cursomc.repositories.CidadeRepository;
import com.marcos.cursomc.repositories.ClienteRepository;
import com.marcos.cursomc.repositories.EnderecoRepository;
import com.marcos.cursomc.repositories.EstadoRepository;
import com.marcos.cursomc.repositories.PagamentoRepository;
import com.marcos.cursomc.repositories.PedidoRepository;
import com.marcos.cursomc.repositories.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}	
		
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategoria().addAll(Arrays.asList(cat1));
		p2.getCategoria().addAll(Arrays.asList(cat1, cat2));
		p3.getCategoria().addAll(Arrays.asList(cat1));
			
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandi", est1);
		Cidade c2 = new Cidade(null,"Sao Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "42342342342342", TipoCliente.PESSOAFISIC);
		
		cli1.getTelefones().addAll(Arrays.asList("42423434534", "343534533"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "3434545", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Apto 420", "Centro", "324234234", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:37"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		}
	}

