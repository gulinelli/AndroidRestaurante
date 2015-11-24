package com.example.gulin_000.restaurantegulinelli;

/**
 * Created by gulin_000 on 24/11/2015.
 */
public class Item {

    protected String nome;
    protected double valor;
    protected String imagem;


    /* ----------- CONSTRUTOR ---------*/
    public Item(){}

    public Item(String nome, double valor, String imagem){
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
    }
	/* ----------------------------------*/

	/* ----------- GETTERS AND SETTERS ---------*/

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public String getImagem(){
        return imagem;
    }

    public void setImagem(String imagem){
        this.imagem = imagem;
    }

    /* -------------------------------------*/

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "nome= " + nome +", valor= " + valor + ", imagem= " + imagem;
    }
}
