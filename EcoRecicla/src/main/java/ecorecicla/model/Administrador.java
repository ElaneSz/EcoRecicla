/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ecorecicla.model;

/**
 *
 * @author selan
 */

import java.util.List;

public class Administrador extends Usuario {
    private String nivelAcesso;
    private String cargo;

    public Administrador(String nomeCompleto, String cpf, String email, String telefone,
                         String usuario, String senha, String nivelAcesso, String cargo) {
        super(nomeCompleto, cpf, email, telefone, usuario, senha);
        this.nivelAcesso = nivelAcesso;
        this.cargo = cargo;
    }

    public void gerenciarUsuarios() {
        System.out.println("Administrador " + getNomeCompleto() + " gerenciando usuários.");
    }

    public void gerenciarConteudo() {
        System.out.println("Administrador gerenciando conteúdo da plataforma.");
    }

    public void visualizarRelatorios(List<RelatorioImpacto> relatorios) {
        System.out.println("Total de relatórios disponíveis: " + relatorios.size());
    }

    public void moderarNegociacoes(List<Negociacao> negociacoes) {
        System.out.println("Moderando " + negociacoes.size() + " negociações.");
    }

    public void configurarSistema(String parametro, String valor) {
        System.out.println("Parâmetro '" + parametro + "' definido como '" + valor + "'.");
    }

    public String getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(String nivelAcesso) { this.nivelAcesso = nivelAcesso; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}

