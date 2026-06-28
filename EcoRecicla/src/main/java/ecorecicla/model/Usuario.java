/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecorecicla.model;

/**
 *
 * @author selan
 */

public abstract class Usuario {
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String telefone;
    private String usuario;
    private String senha;

    public Usuario(String nomeCompleto, String cpf, String email,
                   String telefone, String usuario, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.usuario = usuario;
        this.senha = senha;
    }

    public boolean login(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    public void logout() {
        System.out.println("Usuário " + this.usuario + " deslogado.");
    }

    public void atualizarDados(String novoEmail, String novoTelefone) {
        this.email = novoEmail;
        this.telefone = novoTelefone;
    }

    public boolean alterarSenha(String senhaAtual, String novaSenha) {
        if (this.senha.equals(senhaAtual)) {
            this.senha = novaSenha;
            return true;
        }
        return false;
    }

    public Usuario getDados() { return this; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String n) { this.nomeCompleto = n; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String t) { this.telefone = t; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String u) { this.usuario = u; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
