/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecorecicla.model;

/**
 *
 * @author selan
 */

import java.util.ArrayList;
import java.util.List;

public class Cidadao extends Usuario {
    private String endereco;
    private String dataNasc;
    private Pontuacao pontuacao;
    private List<EntregaMaterial> historicoEntregas;

    public Cidadao(String nomeCompleto, String cpf, String email, String telefone, String usuario, String senha, String endereco, String dataNasc) {
        super(nomeCompleto, cpf, email, telefone, usuario, senha);
        this.endereco = endereco;
        this.dataNasc = dataNasc;
        this.pontuacao = new Pontuacao();
        this.historicoEntregas = new ArrayList<>();
    }

    public EntregaMaterial registrarEntrega(PontoDeColeta pontoColeta,
                                            Material material, double pesoKg) {
        if (pontoColeta == null || material == null || pesoKg <= 0) return null;
        EntregaMaterial entrega = new EntregaMaterial(pesoKg, material, pontoColeta);
        entrega.confirmarEntrega();
        pontuacao.adicionarPontos(entrega.getPontosGerados());
        historicoEntregas.add(entrega);
        return entrega;
    }

    public boolean resgatarRecompensa(Recompensa recompensa) {
        if (recompensa == null) return false;
        return recompensa.resgatar(pontuacao);
    }

    public int consultarPontos() { return pontuacao.getSaldoAtual(); }

    public List<PontoDeColeta> buscarPontoColeta(List<PontoDeColeta> todos, Material material) {
        List<PontoDeColeta> resultado = new ArrayList<>();
        for (PontoDeColeta p : todos) {
            if (p.verificarDisponibilidade() && p.listarMateriais().contains(material)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getDataNasc() { return dataNasc; }
    public void setDataNasc(String dataNasc) { this.dataNasc = dataNasc; }
    public Pontuacao getPontuacao() { return pontuacao; }
    public List<EntregaMaterial> getHistoricoEntregas() { return new ArrayList<>(historicoEntregas); }
}

