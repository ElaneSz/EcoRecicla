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

public class PontoDeColeta {
    private String nome;
    private String endereco;
    private double latitude;
    private double longitude;
    private String horario;
    private List<Material> materiaisAceitos;
    private boolean disponivel;

    public PontoDeColeta(String nome, String endereco, double latitude,
                         double longitude, String horario) {
        this.nome = nome;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.horario = horario;
        this.materiaisAceitos = new ArrayList<>();
        this.disponivel = true;
    }

    public double calcularDistancia(double latRef, double lonRef) {
        final int R = 6371;
        double dLat = Math.toRadians(latRef - this.latitude);
        double dLon = Math.toRadians(lonRef - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(this.latitude))
                * Math.cos(Math.toRadians(latRef))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public boolean verificarDisponibilidade() { return this.disponivel; }

    public List<Material> listarMateriais() { return new ArrayList<>(materiaisAceitos); }

    public boolean adicionarMaterial(Material material) {
        if (material != null && !materiaisAceitos.contains(material)) {
            return materiaisAceitos.add(material);
        }
        return false;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}

