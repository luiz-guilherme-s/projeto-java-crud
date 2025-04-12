package model;

public class PessoaJuridica extends Pessoa {
    
    private static final long serialVersionUID = 1L;
    
    private String cnpj;
    
    public PessoaJuridica() {}
    
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    @Override
    public void exibir() {
        System.out.println("PJ - ID: " + getId() + ", Nome: " + getNome() + 
                         ", CNPJ: " + cnpj);
    }
    
    // Getters e Setters
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}