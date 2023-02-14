package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp){
        //pego tudo o que tem no bootcamp.getConteudos e adicionando no conteudos inscritos
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        //adicionar o deve no bootcamp com a palavra reservada 'this'
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        //Optional serve para resolver a questão do returno nulo
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        //se o conteudo existir eu pego o primeiro
        if (conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            //depois que eu coloquei no conteudos concluidos eu tiro de conteudos inscritos
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não esta matriculado em nenhum conteudo!");
        }
    }

    public double calcularTotalXP() {
        return  this.conteudosConcluidos
                .stream()
                .mapToDouble(conteudo -> conteudo.calcularXP())
                .sum();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return nome.equals(dev.nome) && conteudosInscritos.equals(dev.conteudosInscritos) && conteudosConcluidos.equals(dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
