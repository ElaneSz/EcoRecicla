package ecorecicla.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class RecompensaTest {

    private Pontuacao pontuacao;
    private Recompensa recompensa;

    @Before
    public void prepararTeste() {
        pontuacao = new Pontuacao();
        recompensa = new Recompensa("Cupom", "Desconto de 10%", 50, 2, LocalDate.now().plusDays(10));
    }

    @Test
    public void deveEstarDisponivelComEstoqueEValidadeFutura() {
        assertTrue(recompensa.verificarDisponibilidade());
    }

    @Test
    public void naoDeveEstarDisponivelSemEstoque() {
        recompensa.setEstoque(0);

        assertFalse(recompensa.verificarDisponibilidade());
    }

    @Test
    public void naoDeveEstarDisponivelQuandoEstiverVencida() {
        recompensa.setValidade(LocalDate.now().minusDays(1));

        assertFalse(recompensa.verificarDisponibilidade());
    }

    @Test
    public void deveResgatarComPontosSuficientes() {
        pontuacao.adicionarPontos(100);

        boolean resultado = recompensa.resgatar(pontuacao);

        assertTrue(resultado);
        assertEquals(50, pontuacao.getSaldoAtual());
        assertEquals(1, recompensa.getEstoque());
    }

    @Test
    public void naoDeveResgatarComPontosInsuficientes() {
        pontuacao.adicionarPontos(30);

        boolean resultado = recompensa.resgatar(pontuacao);

        assertFalse(resultado);
        assertEquals(30, pontuacao.getSaldoAtual());
        assertEquals(2, recompensa.getEstoque());
    }

    @Test
    public void naoDeveDebitarPontosQuandoRecompensaEstiverIndisponivel() {
        pontuacao.adicionarPontos(100);
        recompensa.setValidade(LocalDate.now().minusDays(1));

        boolean resultado = recompensa.resgatar(pontuacao);

        assertFalse(resultado);
        assertEquals(100, pontuacao.getSaldoAtual());
        assertEquals(2, recompensa.getEstoque());
    }

    @Test
    public void deveAcessarEAlterarNome() {
        assertEquals("Cupom", recompensa.getNome());
        recompensa.setNome("Vale-presente");
        assertEquals("Vale-presente", recompensa.getNome());
    }

    @Test
    public void deveAcessarEAlterarDescricao() {
        assertEquals("Desconto de 10%", recompensa.getDescricao());
        recompensa.setDescricao("Desconto de 20%");
        assertEquals("Desconto de 20%", recompensa.getDescricao());
    }

    @Test
    public void deveAcessarEAlterarCustoEmPontos() {
        assertEquals(50, recompensa.getCustoEmPontos());
        recompensa.setCustoEmPontos(80);
        assertEquals(80, recompensa.getCustoEmPontos());
    }

    @Test
    public void deveAcessarEstoque() {
        assertEquals(2, recompensa.getEstoque());
    }

    @Test
    public void deveAcessarEAlterarValidade() {
        LocalDate novaValidade = LocalDate.now().plusDays(60);
        recompensa.setValidade(novaValidade);
        assertEquals(novaValidade, recompensa.getValidade());
    }
}

