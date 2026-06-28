package ecorecicla.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

public class PontuacaoTest {

    private Pontuacao pontuacao;

   
    @Before
    public void prepararTeste() {
        pontuacao = new Pontuacao();
    }

    @Test
    public void deveIniciarComValoresZerados() {
        assertEquals(0, pontuacao.getSaldoAtual());
        assertEquals(0, pontuacao.getTotalAcumulado());
        assertTrue(pontuacao.consultarExtrato().isEmpty());
    }

    @Test
    public void deveAdicionarPontosAoSaldoETotalAcumulado() {
        pontuacao.adicionarPontos(100);

        assertEquals(100, pontuacao.getSaldoAtual());
        assertEquals(100, pontuacao.getTotalAcumulado());
        assertEquals(1, pontuacao.consultarExtrato().size()); //qtd de registros no extrato
    }

    @Test
    public void naoDeveAdicionarQuantidadeInvalidaDePontos() {
        pontuacao.adicionarPontos(0);
        pontuacao.adicionarPontos(-50);

        assertEquals(0, pontuacao.getSaldoAtual());
        assertEquals(0, pontuacao.getTotalAcumulado());
        assertTrue(pontuacao.consultarExtrato().isEmpty());
    }

    @Test
    public void deveDebitarPontosQuandoHouverSaldoSuficiente() {
        pontuacao.adicionarPontos(100);

        boolean resultado = pontuacao.debitarPontos(40);

        assertTrue(resultado);
        assertEquals(60, pontuacao.getSaldoAtual());
        assertEquals(100, pontuacao.getTotalAcumulado());
        assertEquals(2, pontuacao.consultarExtrato().size());
    }

    @Test
    public void naoDeveDebitarPontosQuandoSaldoForInsuficiente() {
        pontuacao.adicionarPontos(20);

        boolean resultado = pontuacao.debitarPontos(30);

        assertFalse(resultado);
        assertEquals(20, pontuacao.getSaldoAtual());
        assertEquals(20, pontuacao.getTotalAcumulado());

 		/*somente o valor inicial deve estar no extrato, 
        pois o débito não foi realizado*/
  
        assertEquals(1, pontuacao.consultarExtrato().size());
    }

    @Test
    public void naoDeveDebitarQuantidadeInvalidaDePontos() {
        pontuacao.adicionarPontos(50);

        boolean resultadoZero = pontuacao.debitarPontos(0);
        boolean resultadoNegativo = pontuacao.debitarPontos(-20);

        assertFalse(resultadoZero);
        assertFalse(resultadoNegativo);
        assertEquals(50, pontuacao.getSaldoAtual());
        assertEquals(50, pontuacao.getTotalAcumulado());

        /*somente o valor inicial deve estar no extrato, 
        pois o débito não foi realizado*/
        
        assertEquals(1, pontuacao.consultarExtrato().size());
    }

    @Test
    public void deveRetornarDataDaUltimaAtualizacao() {
        assertEquals(LocalDate.now(), pontuacao.getDataUltAtual());

        pontuacao.adicionarPontos(10);
        assertEquals(LocalDate.now(), pontuacao.getDataUltAtual());
    }
}

