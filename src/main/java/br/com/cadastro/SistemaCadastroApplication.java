package br.com.cadastro;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.domain.Cidade;
import br.com.cadastro.domain.Cliente;
import br.com.cadastro.domain.Endereco;
import br.com.cadastro.domain.Estado;
import br.com.cadastro.domain.Pagamento;
import br.com.cadastro.domain.PagamentoComBoleto;
import br.com.cadastro.domain.PagamentoComCartao;
import br.com.cadastro.domain.Pedido;
import br.com.cadastro.domain.Produto;
import br.com.cadastro.domain.enums.EstadoPagamento;
import br.com.cadastro.domain.enums.TipoCliente;
import br.com.cadastro.repositories.CategoriaRepository;
import br.com.cadastro.repositories.CidadeRepository;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.repositories.EnderecoRepository;
import br.com.cadastro.repositories.EstadoRepository;
import br.com.cadastro.repositories.PagamentoRepository;
import br.com.cadastro.repositories.PedidoRepository;
import br.com.cadastro.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaCadastroApplication implements CommandLineRunner {
	
	
	@Autowired
	private CategoriaRepository entityManagerFactory;
	
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
		SpringApplication.run(SistemaCadastroApplication.class, args);
	}

	
	
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00); 
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		entityManagerFactory.saveAll(Arrays.asList(cat1, cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com","36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores","300","Apto 303","Jardim","38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos","105","Sala 800","Centro","38777012", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("19/03/2019 11:01"),cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("19/03/2019 11:30"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/03/2019 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
	}

}
