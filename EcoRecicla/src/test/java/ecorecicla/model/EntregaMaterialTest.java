package ecorecicla.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntregaMaterialTest {

	private Material material;
	private PontoDeColeta ponto;
	private EntregaMaterial entrega;

	@Before
	public void prepararTeste() {
		material = new Material("PLASTICO", "Garrafa plástica", 10.0, "kg");
		ponto = new PontoDeColeta("Ponto Central", "Rua A", -26.3, -48.8, "08:00-18:00");
		entrega = new EntregaMaterial(2.5, material, ponto);
	}

	@Test
	public void deveIniciarPendenteESemPontos() {
		assertEquals("PENDENTE", entrega.getStatus());
		assertEquals(0, entrega.getPontosGerados());
		assertNotNull(entrega.getId());
		assertNotNull(entrega.getDataHora());
	}

	@Test
	public void deveConfirmarEntregaECalcularPontos() {
		entrega.confirmarEntrega();

		assertEquals("CONFIRMADA", entrega.getStatus());
		assertEquals(25, entrega.getPontosGerados());
	}

	@Test
	public void deveCalcularPontosComOMaterial() {
		int pontos = entrega.calcularPontos(null);

		assertEquals(25, pontos);
	}

	@Test
	public void deveCalcularPontosComEstrategiaPersonalizada() {
		CalculadoraPontos calculadora = new CalculadoraPontos() {
			@Override
			public int calcular(Material material, double pesoKg) {
				return (int) (pesoKg * 20);
			}

			@Override
			public double getPontosPorKg() {
				return 20.0;
			}
		};

		int pontos = entrega.calcularPontos(calculadora);

		assertEquals(50, pontos);
	}

	@Test
	public void deveRetornarZeroQuandoMaterialForNulo() {
		entrega.setMaterial(null);

		int pontos = entrega.calcularPontos(null);

		assertEquals(0, pontos);
	}

	@Test
	public void deveGerarProtocoloNoFormatoEsperado() {
		String protocolo = entrega.gerarProtocolo();

		assertNotNull(protocolo);
		assertTrue(protocolo.matches("ECO-[A-F0-9]{8}-\\d{4}-\\d{2}-\\d{2}"));
	}

	@Test
	public void deveAcessarEAlterarPesoKg() {
		assertEquals(2.5, entrega.getPesoKg(), 0.0001);
		entrega.setPesoKg(5.0);
		assertEquals(5.0, entrega.getPesoKg(), 0.0001);
	}

	@Test
	public void deveAlterarStatusManualmente() {
		entrega.setStatus("RECEBIDA_COOPERATIVA");
		assertEquals("RECEBIDA_COOPERATIVA", entrega.getStatus());
	}

	@Test
	public void deveAcessarMaterial() {
		assertEquals(material, entrega.getMaterial());
	}

	@Test
	public void deveAcessarEAlterarPontoDeColeta() {
		assertEquals(ponto, entrega.getPontoDeColeta());

		PontoDeColeta outroPonto = new PontoDeColeta("Outro", "Rua C", -26.5, -49.0, "07:00-17:00");
		entrega.setPontoDeColeta(outroPonto);
		assertEquals(outroPonto, entrega.getPontoDeColeta());
	}
}
