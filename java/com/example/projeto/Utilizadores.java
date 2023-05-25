package com.example.projeto;

import java.io.Serializable;

public class Utilizadores implements Serializable {

    private String Nome;
    private String Email;
    private String Password;
    private int Pontuação;

    public Utilizadores() {
        Nome = " ";
        Email = "";
        Password = " ";
        Pontuação = 0;
    }

    public Utilizadores(String username, String Email, String password, int pontuação) {
        this.Nome = username;
        this.Email = Email;
        this.Password = password;
        this.Pontuação = pontuação;
    }

    public String getNome() {
        return Nome;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public int getPontuação(){ return Pontuação; }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public void setPontuação(int Pontuação){this.Pontuação = Pontuação;}

    @Override
    public String toString() {
        return "Nome: "+Nome+"\nEmail: "+Email+"\nPassword: "+Password+"\n\n";
    }

}
