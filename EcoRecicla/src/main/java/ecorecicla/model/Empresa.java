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

public class Empresa extends Usuario {
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String contaBancaria;
    private String ramo;
    private List<Negociacao> negociacoes;

    public Empresa(String nomeCompleto, String cpf, String email, String telefone,
                   String usuario, String senha, String razaoSocial,
                   String cnpj, String endereco, String contaBancaria, String ramo) {
        super(nomeCompleto, cpf, email, telefone, usuario, senha);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contaBancaria = contaBancaria;
        this.ramo = ramo;
        this.negociacoes = new ArrayList<>();
    }

    public List<Negociacao> buscarMaterial(String tipoMaterial) {
        List<Negociacao> resultado = new ArrayList<>();
        for (Negociacao n : negociacoes) {
            if (n.getMaterial() != null &&
                n.getMaterial().getTipo().equalsIgnoreCase(tipoMaterial)) {
                resultado.add(n);
            }
        }
        return resultado;
    }

    public Negociacao realizarOferta(Material material, double quantidadeKg, double valor) {
        Negociacao negociacao = new Negociacao("COMPRA", material, quantidadeKg, valor);
        negociacoes.add(negociacao);
        return negociacao;
    }

    public List<Negociacao> consultarHistorico() { return new ArrayList<>(negociacoes); }

    public RelatorioImpacto gerarRelatorio(LocalDate inicio, LocalDate fim) {
        RelatorioImpacto relatorio = new RelatorioImpacto(inicio, fim);
        relatorio.calcularImpacto();
        return relatorio;
    }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(String c) { this.contaBancaria = c; }
    public String getRamo() { return ramo; }
    public void setRamo(String ramo) { this.ramo = ramo; }
}

