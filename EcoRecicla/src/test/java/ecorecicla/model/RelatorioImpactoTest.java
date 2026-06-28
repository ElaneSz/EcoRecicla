package ecorecicla.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class RelatorioImpactoTest {

    private LocalDate inicio;
    private LocalDate fim;
    private RelatorioImpacto relatorio;

    @Before
    public void prepararTeste() {
        inicio = LocalDate.of(2026, 1, 1);
        fim = LocalDate.of(2026, 1, 31);
        relatorio = new RelatorioImpacto(inicio, fim);
    }

    @Test
    public void deveIniciarComValoresZerados() {
        assertEquals(inicio, relatorio.getPeriodoInicio());
        assertEquals(fim, relatorio.getPeriodoFim());
        assertEquals(0.0, relatorio.getTotalRecicladoKg(), 0.0001);
        assertEquals(0.0, relatorio.getCo2Evitado(), 0.0001);
        assertEquals(0, relatorio.getQtdEntregas());
    }

    @Test
    public void deveCalcularImpactoDeCO2() {
        relatorio.setTotalRecicladoKg(10.0);

        relatorio.calcularImpacto();

        assertEquals(25.0, relatorio.getCo2Evitado(), 0.0001);
    }

    @Test
    public void deveManterImpactoZeroSemMaterialReciclado() {
        relatorio.calcularImpacto();

        assertEquals(0.0, relatorio.getCo2Evitado(), 0.0001);
    }

    @Test
    public void deveExportarPDFComOsDadosDoRelatorio() {
        relatorio.setTotalRecicladoKg(8.0);
        relatorio.setQtdEntregas(3);
        relatorio.calcularImpacto();

        String pdf = relatorio.exportarPDF();

        assertTrue(pdf.contains("2026-01-01 a 2026-01-31"));
        assertTrue(pdf.contains("Reciclado: 8.0kg"));
        assertTrue(pdf.contains("CO2 evitado: 20.0kg"));
        assertTrue(pdf.contains("Entregas: 3"));
    }

    @Test
    public void deveExportarCSVComCabecalhoEDados() {
        relatorio.setTotalRecicladoKg(4.0);
        relatorio.setQtdEntregas(2);
        relatorio.calcularImpacto();

        String csv = relatorio.exportarCSV();

        assertTrue(csv.startsWith("periodoInicio,periodoFim,totalRecicladoKg,co2Evitado,qtdEntregas"));
        assertTrue(csv.contains("2026-01-01,2026-01-31,4.0,10.0,2"));
    }

    @Test
    public void deveAtualizarDadosDoRelatorio() {
        LocalDate novoInicio = LocalDate.of(2026, 2, 1);
        LocalDate novoFim = LocalDate.of(2026, 2, 28);

        relatorio.setPeriodoInicio(novoInicio);
        relatorio.setPeriodoFim(novoFim);
        relatorio.setTotalRecicladoKg(12.5);
        relatorio.setQtdEntregas(7);

        assertEquals(novoInicio, relatorio.getPeriodoInicio());
        assertEquals(novoFim, relatorio.getPeriodoFim());
        assertEquals(12.5, relatorio.getTotalRecicladoKg(), 0.0001);
        assertEquals(7, relatorio.getQtdEntregas());
    }
}

