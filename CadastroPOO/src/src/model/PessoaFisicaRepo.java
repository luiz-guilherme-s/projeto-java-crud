package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();
    
    public void inserir(PessoaFisica pessoa) {
        pessoasFisicas.add(pessoa);
    }
    
    public boolean alterar(PessoaFisica pessoa) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            if (pessoasFisicas.get(i).getId() == pessoa.getId()) {
                pessoasFisicas.set(i, pessoa);
                return true;
            }
        }
        return false;
    }
    
    public boolean excluir(int id) {
        return pessoasFisicas.removeIf(pf -> pf.getId() == id);
    }

    public PessoaFisica obter(int id) {
        return pessoasFisicas.stream()
                       .filter(pf -> pf.getId() == id)
                       .findFirst()
                       .orElse(null);
    }
    
    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }
    
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(pessoasFisicas);
        }
    }
    
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            pessoasFisicas = (ArrayList<PessoaFisica>) in.readObject();
        }
    }
}