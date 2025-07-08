package org.example;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeradorSenhas {
    // Criando as variáveis - Definindo os tipos de caracteres.
    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS  = "0123456789";
    private static final String ESPECIAIS = "!@#$%^&*()-_+=<>?";

    // Unindo todas as variáveis em uma única String.
    // Essa variável será usada para gerar os caracteres restantes da senha.
    private static final String TODOS_CARACTERES = MAIUSCULAS + MINUSCULAS + DIGITOS + ESPECIAIS;

    // Função principal para gerar a senha
    // Método que recebe o comprimento da senha como parâmetro
    public static String gerarSenha(int n ) {
        // Verificação mínima
        if (n < 4) {
            throw new IllegalArgumentException("O comprimento da senha deve ser maior.");
        }

        // Gerador de números aleatórios seguros.
        // SecureRandom: é uma versão mais segura do Random, ideal para senhas e criptografia.
        SecureRandom random = new SecureRandom();

        // Lista de caracteres da senha.
        // Usa uma lista de caracteres para adicionar cada parte da senha, que depois será embaralhada
        List<Character> senha = new ArrayList<>();

        // Garante pelo menos um de cada tipo
        senha.add(MAIUSCULAS.charAt(random.nextInt(MAIUSCULAS.length())));
        senha.add(MINUSCULAS.charAt(random.nextInt(MINUSCULAS.length())));
        senha.add(DIGITOS.charAt(random.nextInt(DIGITOS.length())));
        senha.add(ESPECIAIS.charAt(random.nextInt(ESPECIAIS.length())));

        // Preencher o restante com caracteres aleatórios.
        // Como já há 4 caracteres obrigatórios, agora a senha será completada até n usando qualquer caractere válido.
        for (int i = 4; i < n; i++) {
            senha.add(TODOS_CARACTERES.charAt(random.nextInt(TODOS_CARACTERES.length())));
        }

        // Embaralhar a senha para não deixar os primeiros 4 previsíveis
        Collections.shuffle(senha, random);

        // Constrói a string final
        StringBuilder senhaFinal = new StringBuilder();
        for (char c : senha) {
            senhaFinal.append(c);
        }

        return senhaFinal.toString();
    }

    public static void main(String[] args) {
        int tamanho = 12;
        String senhaGerada = gerarSenha(tamanho);
        System.out.println("Senha gerada: " + senhaGerada);
    }
}