package ecorecicla.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ecorecicla.model.Cidadao;

public class ValidadorTest {

    private Validador validador;
    private Cidadao usuario;

    @Before
    public void prepararTeste() {
        validador = new Validador();
        usuario = new Cidadao
        		("Gabriela de Oliveira", 
        		"52998224725", 
        		"gabi@email.com", 
        		"47999999999", 
        		"gabi", 
        		"123456", 
        		"Rua A", 
        		"03/11/2005");
    }

    @Test
    public void deveValidarCPFValidoSemMascara() {
        assertTrue(validador.validarCPF("52998224725"));
    }

    @Test
    public void deveValidarCPFValidoComMascara() {
        assertTrue(validador.validarCPF("529.982.247-25"));
    }

    @Test
    public void naoDeveValidarCPFNulo() {
        assertFalse(validador.validarCPF(null));
    }

    @Test
    public void naoDeveValidarCPFComMenosDigitos() {
        assertFalse(validador.validarCPF("5299822472"));
    }

    @Test
    public void naoDeveValidarCPFComMaisDigitos() {
        assertFalse(validador.validarCPF("529982247255"));
    }

    @Test
    public void naoDeveValidarCPFComTodosOsDigitosIguais() {
        assertFalse(validador.validarCPF("11111111111"));
    }

    @Test
    public void naoDeveValidarCPFComPrimeiroDigitoVerificadorIncorreto() {
        assertFalse(validador.validarCPF("52998224735"));
    }

    @Test
    public void naoDeveValidarCPFComSegundoDigitoVerificadorIncorreto() {
        assertFalse(validador.validarCPF("52998224724"));
    }

    @Test
    public void deveValidarCNPJValidoSemMascara() {
        assertTrue(validador.validarCNPJ("11222333000181"));
    }

    @Test
    public void deveValidarCNPJValidoComMascara() {
        assertTrue(validador.validarCNPJ("11.222.333/0001-81"));
    }

    @Test
    public void naoDeveValidarCNPJNulo() {
        assertFalse(validador.validarCNPJ(null));
    }

    @Test
    public void naoDeveValidarCNPJComMenosDigitos() {
        assertFalse(validador.validarCNPJ("1122233300018"));
    }

    @Test
    public void naoDeveValidarCNPJComMaisDigitos() {
        assertFalse(validador.validarCNPJ("112223330001811"));
    }

    @Test
    public void naoDeveValidarCNPJComTodosOsDigitosIguais() {
        assertFalse(validador.validarCNPJ("11111111111111"));
    }

    @Test
    public void naoDeveValidarCNPJComPrimeiroDigitoVerificadorIncorreto() {
        assertFalse(validador.validarCNPJ("11222333000191"));
    }

    @Test
    public void naoDeveValidarCNPJComSegundoDigitoVerificadorIncorreto() {
        assertFalse(validador.validarCNPJ("11222333000182"));
    }

    @Test
    public void deveAutenticarComUsuarioESenhaValidos() {
        assertTrue(validador.autenticar("gabi", "123456"));
    }

    @Test
    public void naoDeveAutenticarComUsuarioNuloOuVazio() {
        assertFalse(validador.autenticar(null, "123456"));
        assertFalse(validador.autenticar("", "123456"));
    }

    @Test
    public void naoDeveAutenticarComSenhaNula() {
        assertFalse(validador.autenticar("gabi", null));
    }

    @Test
    public void naoDeveAutenticarComSenhaCurta() {
        assertFalse(validador.autenticar("gabi", "12345"));
    }

    @Test
    public void deveValidarLoginComCredenciaisCorretas() {
        assertTrue(validador.validarLogin("gabi", "123456", usuario));
    }

    @Test
    public void naoDeveValidarLoginComCredenciaisIncorretas() {
        assertFalse(validador.validarLogin("gabi", "789012", usuario));
    }

    @Test
    public void naoDeveValidarLoginComUsuarioNulo() {
        assertFalse(validador.validarLogin("gabi", "123456", null));
    }

    @Test
    public void deveValidarCoordenadasDentroDosLimites() {
        assertTrue(validador.validarGeolocalizacao(0, 0));
        assertTrue(validador.validarGeolocalizacao(-90, -180));
        assertTrue(validador.validarGeolocalizacao(90, 180));
    }

    @Test
    public void naoDeveValidarCoordenadasForaDosLimites() {
        assertFalse(validador.validarGeolocalizacao(-90.1, 0));
        assertFalse(validador.validarGeolocalizacao(90.1, 0));
        assertFalse(validador.validarGeolocalizacao(0, -180.1));
        assertFalse(validador.validarGeolocalizacao(0, 180.1));
    }
}

