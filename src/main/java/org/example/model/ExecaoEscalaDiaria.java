package org.example.model;

/**
 * Classe contendo exceções personalizadas relacionadas à EscalaDiaria.
 * Utilizada para tratar erros específicos do gerenciamento de escalas diárias.
 */
public class ExecaoEscalaDiaria extends RuntimeException {

  /**
   * Exceção lançada quando a data fornecida para a escala é inválida.
   */
  public static class ExcecaoDataInvalida extends Exception {
    /**
     * Construtor que recebe uma mensagem detalhada sobre a exceção.
     * @param mensagem Mensagem descritiva da exceção.
     */
    public ExcecaoDataInvalida(String mensagem) {
      super(mensagem);
    }
  }

  /**
   * Exceção lançada quando já existe uma escala para a data informada.
   */
  public static class ExcecaoEscalaExistente extends Exception {
    /**
     * Construtor que recebe uma mensagem detalhada sobre a exceção.
     * @param mensagem Mensagem descritiva da exceção.
     */
    public ExcecaoEscalaExistente(String mensagem) {
      super(mensagem);
    }
  }

  /**
   * Exceção lançada quando não existem barracas para criar uma escala.
   */
  public static class ExcecaoSemBarracas extends Exception {
    /**
     * Construtor que recebe uma mensagem detalhada sobre a exceção.
     * @param mensagem Mensagem descritiva da exceção.
     */
    public ExcecaoSemBarracas(String mensagem) {
      super(mensagem);
    }
  }

  /**
   * Exceção lançada quando os valores da data fornecida são inválidos (ex: dia, mês, ano incorretos).
   */
  public static class ExcecaoValoresDataInvalidos extends Exception {
    /**
     * Construtor que recebe uma mensagem detalhada sobre a exceção.
     * @param mensagem Mensagem descritiva da exceção.
     */
    public ExcecaoValoresDataInvalidos(String mensagem) {
      super(mensagem);
    }
  }
}
