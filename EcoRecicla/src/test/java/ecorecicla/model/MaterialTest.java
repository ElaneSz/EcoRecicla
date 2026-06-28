package ecorecicla.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaterialTest {

	private Material material;

	@Before
	public void prepararTeste() {
		material = new Material("PLASTICO", "Garrafa plástica", 10.0, "kg");
	}

	@Test
	public void deveCalcularPontosQuandoPesoForPositivo() {
		double pesoKg = 2.0;
		int pontosCalculados = material.calcularPontosPorKg(pesoKg);
		assertEquals(20, pontosCalculados);
	}

	@Test
	public void deveArredondarPontosParaBaixo() {
		double pesoKg = 2.59;
		int pontosCalculados = material.calcularPontosPorKg(pesoKg);
		assertEquals(25, pontosCalculados);
	}

	@Test
	public void deveRetornarZeroQuandoPesoForZero() {
		double pesoKg = 0.0;
		int pontosCalculados = material.calcularPontosPorKg(pesoKg);
		assertEquals(0, pontosCalculados);
	}

	@Test
	public void deveRetornarZeroQuandoPesoForNegativo() {
		double pesoKg = -2.0;
		int pontosCalculados = material.calcularPontosPorKg(pesoKg);
		assertEquals(0, pontosCalculados);
	}

	@Test
	public void deveValidarTipoIgnorandoMaiusculasEMinusculas() {
		material.setTipo("plastico");
		boolean resultado = material.validarTipo();
		assertTrue(resultado);
	}

	@Test
	public void naoDeveValidarTipoInvalidoOuNulo() {
		material.setTipo("MADEIRA");
		boolean resultadoTipoInvalido = material.validarTipo();
		assertFalse(resultadoTipoInvalido);
		material.setTipo(null);
		boolean resultadoTipoNulo = material.validarTipo();
		assertFalse(resultadoTipoNulo);
	}

	@Test
	public void deveAcessarEAlterarNome() {
		assertEquals("Garrafa plástica", material.getNome());
		material.setNome("Pote plástico");
		assertEquals("Pote plástico", material.getNome());
	}

	@Test
	public void deveAcessarTipo() {
		assertEquals("PLASTICO", material.getTipo());
	}

	@Test
	public void deveAcessarEAlterarFatorPontos() {
		assertEquals(10.0, material.getFatorPontos(), 0.0001);
		material.setFatorPontos(15.0);
		assertEquals(15.0, material.getFatorPontos(), 0.0001);
	}

	@Test
	public void deveAcessarEAlterarUnidadeMedida() {
		assertEquals("kg", material.getUnidadeMedida());
		material.setUnidadeMedida("un");
		assertEquals("un", material.getUnidadeMedida());
	} 
}
