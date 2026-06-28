package ecorecicla.model;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PontoDeColetaTest {

    private PontoDeColeta ponto;
    private Material material;

    @Before
    public void prepararTeste() {
        ponto = new PontoDeColeta("Ponto Central", "Rua A", -26.3044, -48.8487, "08:00-18:00");
        material = new Material("PLASTICO", "Garrafa plástica", 10.0, "kg");
    }

    @Test
    public void deveIniciarDisponivelESemMateriais() {
        assertTrue(ponto.verificarDisponibilidade());
        assertTrue(ponto.listarMateriais().isEmpty());
    }

    @Test
    public void deveAdicionarMaterialValido() {
        boolean resultado = ponto.adicionarMaterial(material);

        assertTrue(resultado);
        assertEquals(1, ponto.listarMateriais().size());
        assertTrue(ponto.listarMateriais().contains(material));
    }

    @Test
    public void naoDeveAdicionarMaterialNulo() {
        boolean resultado = ponto.adicionarMaterial(null);

        assertFalse(resultado);
        assertTrue(ponto.listarMateriais().isEmpty());
    }

    @Test
    public void naoDeveAdicionarOMesmoMaterialDuasVezes() {
        assertTrue(ponto.adicionarMaterial(material));
        boolean resultado = ponto.adicionarMaterial(material);

        assertFalse(resultado);
        assertEquals(1, ponto.listarMateriais().size());
    }

    @Test
    public void alteracaoNaListaRetornadaNaoDeveAlterarListaOriginal() {
        ponto.adicionarMaterial(material);
        List<Material> copia = ponto.listarMateriais();
        copia.clear();

        assertEquals(1, ponto.listarMateriais().size());
    }

    @Test
    public void deveCalcularDistanciaZeroParaAsMesmasCoordenadas() {
        double distancia = ponto.calcularDistancia(-26.3044, -48.8487);

        assertEquals(0.0, distancia, 0.0001);
    }

    @Test
    public void deveCalcularDistanciaPositivaEntreCoordenadasDiferentes() {
        // Florianópolis, a partir de Joinville (-26.3044, -48.8487)
        double distancia = ponto.calcularDistancia(-27.5954, -48.5480);

        assertTrue(distancia > 0);
        assertTrue(distancia > 100 && distancia < 200);
    }

    @Test
    public void deveAcessarEAlterarNome() {
        assertEquals("Ponto Central", ponto.getNome());
        ponto.setNome("Novo Ponto");
        assertEquals("Novo Ponto", ponto.getNome());
    }

    @Test
    public void deveAcessarEAlterarEndereco() {
        assertEquals("Rua A", ponto.getEndereco());
        ponto.setEndereco("Rua B");
        assertEquals("Rua B", ponto.getEndereco());
    }

    @Test
    public void deveAcessarEAlterarLatitudeELongitude() {
        assertEquals(-26.3044, ponto.getLatitude(), 0.0001);
        ponto.setLatitude(-27.0);
        assertEquals(-27.0, ponto.getLatitude(), 0.0001);

        assertEquals(-48.8487, ponto.getLongitude(), 0.0001);
        ponto.setLongitude(-49.0);
        assertEquals(-49.0, ponto.getLongitude(), 0.0001);
    }

    @Test
    public void deveAcessarEAlterarHorario() {
        assertEquals("08:00-18:00", ponto.getHorario());
        ponto.setHorario("09:00-19:00");
        assertEquals("09:00-19:00", ponto.getHorario());
    }

    @Test
    public void deveAcessarEAlterarDisponibilidade() {
        assertTrue(ponto.isDisponivel());
        ponto.setDisponivel(false);
        assertFalse(ponto.isDisponivel());
    }
}

