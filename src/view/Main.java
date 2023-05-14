package view;

import controller.SteamController;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ISteamController;

public class Main {

	public static void main(String[] args) {

		ISteamController arqCont = new SteamController();
		String dirTemp = "C:\\TEMP";
		String nome = "SteamCharts.csv";
		String ano = JOptionPane.showInputDialog("Informe o ano");
		String mes = JOptionPane.showInputDialog("Informe o mês");
		String media = JOptionPane.showInputDialog("Informe a média de jogadores");
		String nome_arq = "Steam" + "-"+ano+"-"+mes+".csv";

		try {
			arqCont.exibeConsole(ano, mes, media, dirTemp, nome);
			arqCont.geraArquivo(ano, mes, dirTemp, nome, nome_arq);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
