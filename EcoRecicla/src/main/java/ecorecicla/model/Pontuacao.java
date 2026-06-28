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
import java.util.ArrayList;
import java.util.List;

public class Pontuacao {
    private int saldoAtual;
    private int totalAcumulado;
    private List<String> extrato;
    private LocalDate dataUltAtual;

    public Pontuacao() {
        this.saldoAtual = 0;
        this.totalAcumulado = 0;
        this.extrato = new ArrayList<>();
        this.dataUltAtual = LocalDate.now();
    }

    public void adicionarPontos(int qtd) {
        if (qtd > 0) {
            this.saldoAtual += qtd;
            this.totalAcumulado += qtd;
            this.dataUltAtual = LocalDate.now();
            extrato.add("[" + dataUltAtual + "] +" + qtd + " pontos. Saldo: " + saldoAtual);
        }
    }

    public boolean debitarPontos(int qtd) {
        if (qtd > 0 && saldoAtual >= qtd) {
            this.saldoAtual -= qtd;
            this.dataUltAtual = LocalDate.now();
            extrato.add("[" + dataUltAtual + "] -" + qtd + " pontos. Saldo: " + saldoAtual);
            return true;
        }
        return false;
    }

    public List<String> consultarExtrato() {
        return new ArrayList<>(extrato);
    }

    public int getSaldoAtual() { return saldoAtual; }
    public int getTotalAcumulado() { return totalAcumulado; }
    public LocalDate getDataUltAtual() { return dataUltAtual; }
}

