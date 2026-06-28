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

public class Recompensa {
    private String nome;
    private String descricao;
    private int custoEmPontos;
    private int estoque;
    private LocalDate validade;

    public Recompensa(String nome, String descricao, int custoEmPontos,
                      int estoque, LocalDate validade) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoEmPontos = custoEmPontos;
        this.estoque = estoque;
        this.validade = validade;
    }

    public boolean verificarDisponibilidade() {
        return estoque > 0 && LocalDate.now().isBefore(validade);
    }

    public boolean resgatar(Pontuacao pontuacao) {
        if (verificarDisponibilidade() && pontuacao.debitarPontos(custoEmPontos)) {
            this.estoque--;
            return true;
        }
        return false;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getCustoEmPontos() { return custoEmPontos; }
    public void setCustoEmPontos(int custoEmPontos) { this.custoEmPontos = custoEmPontos; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
}

