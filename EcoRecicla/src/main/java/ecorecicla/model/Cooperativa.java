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

public class Cooperativa extends Usuario {
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String contaBancaria;
    private List<Material> materiaisAceitos;
    private List<PontoDeColeta> pontosDeColeta;
    private List<Negociacao> negociacoes;

    public Cooperativa(String nomeCompleto, String cpf, String email, String telefone,
                       String usuario, String senha, String razaoSocial,
                       String cnpj, String endereco, String contaBancaria) {
        super(nomeCompleto, cpf, email, telefone, usuario, senha);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contaBancaria = contaBancaria;
        this.materiaisAceitos = new ArrayList<>();
        this.pontosDeColeta = new ArrayList<>();
        this.negociacoes = new ArrayList<>();
    }

    public boolean adicionarPontoColeta(PontoDeColeta ponto) {
        if (ponto != null && !pontosDeColeta.contains(ponto)) {
            return pontosDeColeta.add(ponto);
        }
        return false;
    }

    public void registrarRecebimento(EntregaMaterial entrega) {
        if (entrega != null) entrega.setStatus("RECEBIDA_COOPERATIVA");
    }

    public RelatorioImpacto gerarRelatorio(LocalDate inicio, LocalDate fim) {
        RelatorioImpacto relatorio = new RelatorioImpacto(inicio, fim);
        relatorio.calcularImpacto();
        return relatorio;
    }

    public List<Negociacao> acessarMarketplace() { return new ArrayList<>(negociacoes); }

    public void adicionarNegociacao(Negociacao negociacao) {
        if (negociacao != null) negociacoes.add(negociacao);
    }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(String c) { this.contaBancaria = c; }
    public List<Material> getMateriaisAceitos() { return new ArrayList<>(materiaisAceitos); }
    public void adicionarMaterial(Material m) { materiaisAceitos.add(m); }
    public List<PontoDeColeta> getPontosDeColeta() { return new ArrayList<>(pontosDeColeta); }
}

