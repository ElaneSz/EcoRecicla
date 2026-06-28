/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecorecicla.util;

/**
 *
 * @author selan
 */

import ecorecicla.model.Usuario;

public class Validador {

    public boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        int soma = 0;
        for (int i = 0; i < 9; i++)
            soma += (cpf.charAt(i) - '0') * (10 - i);
        int r1 = 11 - (soma % 11);
        if (r1 >= 10) r1 = 0;
        if (r1 != (cpf.charAt(9) - '0')) return false;

        soma = 0;
        for (int i = 0; i < 10; i++)
            soma += (cpf.charAt(i) - '0') * (11 - i);
        int r2 = 11 - (soma % 11);
        if (r2 >= 10) r2 = 0;
        return r2 == (cpf.charAt(10) - '0');
    }

    public boolean validarCNPJ(String cnpj) {
        if (cnpj == null) return false;
        cnpj = cnpj.replaceAll("[^0-9]", "");
        if (cnpj.length() != 14) return false;
        if (cnpj.matches("(\\d)\\1{13}")) return false;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 12; i++)
            soma += (cnpj.charAt(i) - '0') * pesos1[i];
        int r1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
        if (r1 != (cnpj.charAt(12) - '0')) return false;

        soma = 0;
        for (int i = 0; i < 13; i++)
            soma += (cnpj.charAt(i) - '0') * pesos2[i];
        int r2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
        return r2 == (cnpj.charAt(13) - '0');
    }

    public boolean autenticar(String usuario, String senha) {
        return usuario != null && !usuario.isEmpty()
                && senha != null && senha.length() >= 6;
    }

    public boolean validarLogin(String usuario, String senha, Usuario obj) {
        if (obj == null) return false;
        return obj.login(usuario, senha);
    }

    public boolean validarGeolocalizacao(double lat, double lon) {
        return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
    }
}

