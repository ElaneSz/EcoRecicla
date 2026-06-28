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

public class Negociacao {
    private String id;
    private String tipo;
    private double valorTotal;
    private String status;
    private LocalDateTime dataHora;
    private Material material;
    private double quantidadeKg;

    public Negociacao(String tipo, Material material, double quantidadeKg, double valorTotal) {
        this.id = UUID.randomUUID().toString();
        this.tipo = tipo;
        this.material = material;
        this.quantidadeKg = quantidadeKg;
        this.valorTotal = valorTotal;
        this.status = "ABERTA";
        this.dataHora = LocalDateTime.now();
    }

    public boolean processarPagamento() {
        if ("ABERTA".equals(status)) {
            this.status = "PAGAMENTO_PROCESSADO";
            return true;
        }
        return false;
    }

    public void confirmarNegociacao() {
        if ("PAGAMENTO_PROCESSADO".equals(status)) {
            this.status = "CONFIRMADA";
        }
    }

    public void cancelar() {
        if (!"CONFIRMADA".equals(status)) {
            this.status = "CANCELADA";
        }
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public double getQuantidadeKg() { return quantidadeKg; }
    public void setQuantidadeKg(double quantidadeKg) { this.quantidadeKg = quantidadeKg; }
}

