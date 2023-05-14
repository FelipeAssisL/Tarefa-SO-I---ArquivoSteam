package controller;

import java.io.IOException;

public interface ISteamController {

	public void exibeConsole(String ano, String mes,String media, String path, String nome)throws IOException;
	public void geraArquivo(String ano, String mes, String path, String nome, String nome_arq) throws IOException;
}
