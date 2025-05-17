package org.example.utils;

import org.example.model.Federacao;
import java.io.*;

public class PersistenciaDados {
    private static final String ARQUIVO_DADOS = "federacao.dat";
    private static final String ARQUIVO_BACKUP = "federacao_backup.dat";

    public static void salvarDados(Federacao federacao) throws IOException {
        // Criar backup do arquivo existente, se houver
        File arquivoAtual = new File(ARQUIVO_DADOS);
        if (arquivoAtual.exists()) {
            File arquivoBackup = new File(ARQUIVO_BACKUP);
            try {
                copyFile(arquivoAtual, arquivoBackup);
            } catch (IOException e) {
                System.err.println("Aviso: Não foi possível criar backup: " + e.getMessage());
            }
        }

        // Salvar dados
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(federacao);
        } catch (IOException e) {
            // Exibir a causa exata da exceção
            String causa = e.getCause() != null ? e.getCause().toString() : e.toString();
            throw new IOException("Erro ao salvar dados: " + causa, e);
        }
    }

    public static Federacao carregarDados() throws IOException, ClassNotFoundException {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) {
            return null; // Retorna null se o arquivo não existir
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
            return (Federacao) ois.readObject();
        } catch (IOException e) {
            // Tentar carregar do backup, se disponível
            File arquivoBackup = new File(ARQUIVO_BACKUP);
            if (arquivoBackup.exists()) {
                try (ObjectInputStream oisBackup = new ObjectInputStream(new FileInputStream(ARQUIVO_BACKUP))) {
                    System.err.println("Aviso: Dados principais corrompidos, carregando backup.");
                    return (Federacao) oisBackup.readObject();
                }
            }
            String causa = e.getCause() != null ? e.getCause().toString() : e.toString();
            throw new IOException("Erro ao carregar dados: " + causa, e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Classe Federacao não encontrada: " + e.getMessage(), e);
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}