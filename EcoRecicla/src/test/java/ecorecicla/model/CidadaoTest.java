package ecorecicla.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class CidadaoTest {

    private Cidadao cidadao;
    private Material material;
    private PontoDeColeta ponto;

    @Before
    public void prepararTeste() {
        cidadao = new Cidadao
        		("Gabriela de Oliveira", 
        				"52998224725", 
        				"gabi@email.com", 
        				"47999999999", 
        				"gabi", 
        				"123456", 
        				"Rua A", 
        				"03/11/2005");
        
        material = new Material("PLASTICO", "Garrafa plástica", 10.0, "kg");
        ponto = new PontoDeColeta("Ponto Central", "Rua B", -26.3, -48.8, "08:00-18:00");
        ponto.adicionarMaterial(material);
    }

    @Test
    public void deveIniciarSemPontosEComHistoricoVazio() {
        assertEquals(0, cidadao.consultarPontos());
        assertTrue(cidadao.getHistoricoEntregas().isEmpty());
    }

    @Test
    public void deveRegistrarEntregaValida() {
        EntregaMaterial entrega = cidadao.registrarEntrega(ponto, material, 2.0);

        assertNotNull(entrega);
        assertEquals("CONFIRMADA", entrega.getStatus());
        assertEquals(20, entrega.getPontosGerados());
        assertEquals(20, cidadao.consultarPontos());
        assertEquals(1, cidadao.getHistoricoEntregas().size());
    }

    @Test
    public void naoDeveRegistrarEntregaComDadosInvalidos() {
        assertNull(cidadao.registrarEntrega(null, material, 2.0));
        assertNull(cidadao.registrarEntrega(ponto, null, 2.0));
        assertNull(cidadao.registrarEntrega(ponto, material, 0.0));
        assertNull(cidadao.registrarEntrega(ponto, material, -1.0));
        assertEquals(0, cidadao.consultarPontos());
        assertTrue(cidadao.getHistoricoEntregas().isEmpty());
    }

    @Test
    public void deveResgatarRecompensaComPontosSuficientes() {
        cidadao.getPontuacao().adicionarPontos(100);
        Recompensa recompensa = new Recompensa("Cupom", "Desconto", 40, 2, LocalDate.now().plusDays(5));

        boolean resultado = cidadao.resgatarRecompensa(recompensa);

        assertTrue(resultado);
        assertEquals(60, cidadao.consultarPontos());
        assertEquals(1, recompensa.getEstoque());
    }

    @Test
    public void naoDeveResgatarRecompensaNula() {
        boolean resultado = cidadao.resgatarRecompensa(null);

        assertFalse(resultado);
        assertEquals(0, cidadao.consultarPontos());
    }

    @Test
    public void deveBuscarSomentePontosDisponiveisQueAceitamOMaterial() {
        PontoDeColeta indisponivel = new PontoDeColeta("Indisponível", "Rua C", -26.4, -48.9, "08:00-18:00");
        indisponivel.adicionarMaterial(material);
        indisponivel.setDisponivel(false);

        PontoDeColeta outroMaterial = new PontoDeColeta("Outro material", "Rua D", -26.5, -49.0, "08:00-18:00");
        outroMaterial.adicionarMaterial(new Material("VIDRO", "Garrafa de vidro", 8.0, "kg"));

        List<PontoDeColeta> resultado = cidadao.buscarPontoColeta(Arrays.asList(ponto, indisponivel, outroMaterial), material);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(ponto));
    }

    @Test
    public void deveAcessarEAlterarEndereco() {
        assertEquals("Rua A", cidadao.getEndereco());
        cidadao.setEndereco("Rua Nova, 200");
        assertEquals("Rua Nova, 200", cidadao.getEndereco());
    }

    @Test
    public void deveAcessarEAlterarDataNascimento() {
        assertEquals("03/11/2005", cidadao.getDataNasc());
        cidadao.setDataNasc("15/05/2000");
        assertEquals("15/05/2000", cidadao.getDataNasc());
    }
}

