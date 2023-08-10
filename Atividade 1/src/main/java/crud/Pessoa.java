package crud;

import java.util.ArrayList;

public class Pessoa {

  private String nome;
  private String cpf;
  private String telefone;
  private String profissao;
  private int idade;
  private ArrayList<String> endereco = new ArrayList<String>();

  public Pessoa(String nome, int idade, String cpf, String telefone, ArrayLis<String> endereco, String profissao) {
    this.nome = nome;
    this.idade = idade;
    this.endereco = endereco;
    this.profissao = profissao;
    this.cpf = cpf;
    this.telefone = telefone;
  }

  // add novo endereço
  public void add(String newEndereco) {
    endereco.add(newEndereco);
  }

  // UPDATE

  public void setEndereco(int indice, String newEndereco) {
    if (indice >= 0 && indice < endereco.size()) {
        endereco.set(indice, novoEndereco);
    } else {
        System.out.println("Inválido.");
    }
  }

  public void setTelefone (String newTelefone) {
    this.telefone = newTelefone;
  }

  public void setIdade (int newIdade) {
    this.idade = newIdade;
  }

  public void setProfissao (String newProfissao) {
    this.profissao = newProfissao;
  }

  // READ

  public String getNome () {
    return this.nome;
  }

  public ArrayList<String> getEndereco () {
    return this.endereco;
  }

  public String getProfissao () {
    return this.profissao;
  }

  public int getIdade () {
    return this.idade;
  }

  public String getCpf () {
    return this.cpf;
  }

  public String getTelefone () {
    return this.telefone;
  }

  // DELETE

  public void removeTelefone () {
    this.telefone = null;
  }

  public void removeEndereco () {
    this.endereco = null;
  }

  public void removeIdade () {
    this.idade = null;
  }

  public void removeProfissao () {
    this.profissao = null;
  }

}
