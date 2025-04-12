package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
    
    public void inserir(PessoaJuridica pessoa) {
        pessoasJuridicas.add(pessoa);
    }
    
    public boolean alterar(PessoaJuridica pessoa) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            if (pessoasJuridicas.get(i).getId() == pessoa.getId()) {
                pessoasJuridicas.set(i, pessoa);
                return true;
            }
        }
        return false;
    }
    
    public boolean excluir(int id) {
        return pessoasJuridicas.removeIf(pj -> pj.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        return pessoasJuridicas.stream()
                         .filter(pj -> pj.getId() == id)
                         .findFirst()
                         .orElse(null);
    }
    
    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }
    
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(pessoasJuridicas);
        }
    }
    
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            pessoasJuridicas = (ArrayList<PessoaJuridica>) in.readObject();
        }
    }
}