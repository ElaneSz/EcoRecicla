/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecorecicla.model;

/**
 *
 * @author selan
 */

import java.time.LocalDateTime;
import java.util.UUID;

public class EntregaMaterial {
    private String id;
    private LocalDateTime dataHora;
    private double pesoKg;
    private String status;
    private int pontosGerados;
    private Material material;
    private PontoDeColeta pontoDeColeta;

    public EntregaMaterial(double pesoKg, Material material, PontoDeColeta pontoDeColeta) {
        this.id = UUID.randomUUID().toString();
        this.dataHora = LocalDateTime.now();
        this.pesoKg = pesoKg;
        this.material = material;
        this.pontoDeColeta = pontoDeColeta;
        this.status = "PENDENTE";
        this.pontosGerados = 0;
    }

    public void confirmarEntrega() {
        this.status = "CONFIRMADA";
        this.pontosGerados = calcularPontos(null);
    }

    public int calcularPontos(CalculadoraPontos calc) {
        if (calc != null) return calc.calcular(material, pesoKg);
        return material != null ? material.calcularPontosPorKg(pesoKg) : 0;
    }

    public String gerarProtocolo() {
        return "ECO-" + id.substring(0, 8).toUpperCase()
                + "-" + dataHora.toLocalDate().toString();
    }

    public String getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getPontosGerados() { return pontosGerados; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public PontoDeColeta getPontoDeColeta() { return pontoDeColeta; }
    public void setPontoDeColeta(PontoDeColeta p) { this.pontoDeColeta = p; }
}

