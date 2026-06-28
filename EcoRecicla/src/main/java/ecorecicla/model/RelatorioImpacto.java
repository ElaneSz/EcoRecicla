/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecorecicla.model;

/**
 *
 * @author selan
 */

import java.time.LocalDate;

public class RelatorioImpacto {
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private double totalRecicladoKg;
    private double co2Evitado;
    private int qtdEntregas;

    private static final double FATOR_CO2 = 2.5;

    public RelatorioImpacto(LocalDate periodoInicio, LocalDate periodoFim) {
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.totalRecicladoKg = 0;
        this.co2Evitado = 0;
        this.qtdEntregas = 0;
    }

    public void calcularImpacto() {
        this.co2Evitado = totalRecicladoKg * FATOR_CO2;
    }

    public String exportarPDF() {
        return "[PDF] Relatório EcoRecicla: "
                + periodoInicio + " a " + periodoFim
                + " | Reciclado: " + totalRecicladoKg + "kg"
                + " | CO2 evitado: " + co2Evitado + "kg"
                + " | Entregas: " + qtdEntregas;
    }

    public String exportarCSV() {
        return "periodoInicio,periodoFim,totalRecicladoKg,co2Evitado,qtdEntregas\n"
                + periodoInicio + "," + periodoFim + ","
                + totalRecicladoKg + "," + co2Evitado + "," + qtdEntregas;
    }

    public LocalDate getPeriodoInicio() { return periodoInicio; }
    public void setPeriodoInicio(LocalDate periodoInicio) { this.periodoInicio = periodoInicio; }
    public LocalDate getPeriodoFim() { return periodoFim; }
    public void setPeriodoFim(LocalDate periodoFim) { this.periodoFim = periodoFim; }
    public double getTotalRecicladoKg() { return totalRecicladoKg; }
    public void setTotalRecicladoKg(double totalRecicladoKg) { this.totalRecicladoKg = totalRecicladoKg; }
    public double getCo2Evitado() { return co2Evitado; }
    public int getQtdEntregas() { return qtdEntregas; }
    public void setQtdEntregas(int qtdEntregas) { this.qtdEntregas = qtdEntregas; }
}

