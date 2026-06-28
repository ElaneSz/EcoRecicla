package ecorecicla.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NegociacaoTest {

	private Negociacao negociacao;
	private Material material;

	@Before
	public void prepararTeste() {
		material = new Material("PLASTICO", "Garrafa plástica", 10.0, "kg");
		negociacao = new Negociacao("COMPRA", material, 5.0, 100.0);
	}

	@Test
	public void deveIniciarComStatusAberta() {
		assertEquals("ABERTA", negociacao.getStatus());
		assertNotNull(negociacao.getId());
		assertNotNull(negociacao.getDataHora());
	}

	@Test
	public void deveProcessarPagamentoQuandoNegociacaoEstiverAberta() {
		boolean resultado = negociacao.processarPagamento();
		assertTrue(resultado);
		assertEquals("PAGAMENTO_PROCESSADO", negociacao.getStatus());
	}

	@Test
	public void naoDeveProcessarPagamentoDuasVezes() {
		negociacao.processarPagamento();
		boolean resultado = negociacao.processarPagamento();
		assertFalse(resultado);
		assertEquals("PAGAMENTO_PROCESSADO", negociacao.getStatus());
	}

	@Test
	public void deveConfirmarNegociacaoDepoisDoPagamento() {
		negociacao.processarPagamento();
		negociacao.confirmarNegociacao();
		assertEquals("CONFIRMADA", negociacao.getStatus());
	}

	@Test
	public void naoDeveConfirmarNegociacaoAntesDoPagamento() {
		negociacao.confirmarNegociacao();
		assertEquals("ABERTA", negociacao.getStatus());
	}
	
	@Test
	public void naoDeveCancelarNegociacaoConfirmada() {
		negociacao.processarPagamento();
		negociacao.confirmarNegociacao();
		negociacao.cancelar();
		assertEquals("CONFIRMADA", negociacao.getStatus());
	}
	
	@Test
	public void deveCancelarNegociacaoAberta() {
	    negociacao.cancelar();
	    assertEquals("CANCELADA", negociacao.getStatus());
	}

	@Test
	public void deveAcessarEAlterarTipo() {
		assertEquals("COMPRA", negociacao.getTipo());
		negociacao.setTipo("VENDA");
		assertEquals("VENDA", negociacao.getTipo());
	}

	@Test
	public void deveAcessarEAlterarValorTotal() {
		assertEquals(100.0, negociacao.getValorTotal(), 0.0001);
		negociacao.setValorTotal(250.0);
		assertEquals(250.0, negociacao.getValorTotal(), 0.0001);
	}

	@Test
	public void deveAcessarEAlterarMaterial() {
		assertEquals(material, negociacao.getMaterial());

		Material outroMaterial = new Material("METAL", "Lata", 12.0, "kg");
		negociacao.setMaterial(outroMaterial);
		assertEquals(outroMaterial, negociacao.getMaterial());
	}

	@Test
	public void deveAcessarEAlterarQuantidadeKg() {
		assertEquals(5.0, negociacao.getQuantidadeKg(), 0.0001);
		negociacao.setQuantidadeKg(20.0);
		assertEquals(20.0, negociacao.getQuantidadeKg(), 0.0001);
	}

	@Test
	public void deveAlterarStatusManualmente() {
		negociacao.setStatus("CANCELADA");
		assertEquals("CANCELADA", negociacao.getStatus());
	}
}

