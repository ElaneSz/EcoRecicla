/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecorecicla.model;

/**
 *
 * @author selan
 */

import java.util.Arrays;
import java.util.List;

public class Material {
    private String tipo;
    private String nome;
    private double fatorPontos;
    private String unidadeMedida;

    private static final List<String> TIPOS_VALIDOS =
        Arrays.asList("PLASTICO", "PAPEL", "VIDRO", "METAL", "ELETRONICO", "ORGANICO");

    public Material(String tipo, String nome, double fatorPontos, String unidadeMedida) {
        this.tipo = tipo;
        this.nome = nome;
        this.fatorPontos = fatorPontos;
        this.unidadeMedida = unidadeMedida;
    }

    public int calcularPontosPorKg(double pesoKg) {
        if (pesoKg <= 0) return 0;
        return (int) Math.floor(pesoKg * fatorPontos);
    }

    public boolean validarTipo() {
        return tipo != null && TIPOS_VALIDOS.contains(tipo.toUpperCase());
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getFatorPontos() { return fatorPontos; }
    public void setFatorPontos(double fatorPontos) { this.fatorPontos = fatorPontos; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
}

