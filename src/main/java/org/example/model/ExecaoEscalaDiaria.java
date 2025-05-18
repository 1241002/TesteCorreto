package org.example.model;

// Exceções personalizadas para EscalaDiaria
public class ExecaoEscalaDiaria extends RuntimeException {

  public static class ExcecaoDataInvalida extends Exception {
    public ExcecaoDataInvalida(String mensagem) {
      super(mensagem);
    }
  }

  public static class ExcecaoEscalaExistente extends Exception {
    public ExcecaoEscalaExistente(String mensagem) {
      super(mensagem);
    }
  }

  public static class ExcecaoSemBarracas extends Exception {
    public ExcecaoSemBarracas(String mensagem) {
      super(mensagem);
    }
  }

  public static class ExcecaoValoresDataInvalidos extends Exception {
    public ExcecaoValoresDataInvalidos(String mensagem) {
      super(mensagem);
    }
  }
}
