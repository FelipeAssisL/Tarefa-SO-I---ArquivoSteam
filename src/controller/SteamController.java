package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.edu.fateczl.filaobj.Fila;

public class SteamController implements ISteamController{

	public SteamController() {
		super();
	}

	@Override
	public void exibeConsole(String ano, String mes, String media, String path, String nome) throws IOException {
		File arq = new File(path, nome);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			String nomeJogo="";
			String mediaJogo="";
			while(linha != null) {
				if(linha.contains(ano) && linha.contains(mes)){
					nomeJogo = linha.split(",") [0].trim();
					mediaJogo = linha.split(",")[3].trim();
					double mediaJogoInt = Double.valueOf(mediaJogo);
					double mediaInt = Double.valueOf(media);
					if(mediaJogoInt>=mediaInt) {
						System.out.println("Jogo: "+ nomeJogo + " | " + "Média de jogadores no mês "+ mes +": " + mediaJogo);
					}
				}
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Inexistente");
		}					
	}

	@Override
	public void geraArquivo(String ano, String mes, String path, String nome, String nome_arq) throws IOException { 
		File dir = new File(path); 
		File arq = new File(path, nome_arq + ".csv"); 
		if(dir.exists() && dir.isDirectory()) { 
		boolean existe = false; 
		if(arq.exists()) { 
		existe = true; 
		} 
		String conteudo = geraCSV(ano, mes, path, nome); 
		FileWriter fileWriter = new FileWriter(arq, existe); 
		PrintWriter print = new PrintWriter(fileWriter); 
		print.write(conteudo); 
		print.flush(); 
		print.close();
		fileWriter.close(); 
		} else { 
		throw new IOException("Diretório Inválido"); } 
		}

	private String geraCSV(String ano, String mes, String path, String nome) {
		StringBuffer buffer = new StringBuffer(); 
		String linha = ""; 
		Fila fila_2 = new Fila(); 
		try {
			fila_2 = ExtrairSteam(ano, mes, path, nome);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		while (!fila_2.isEmpty()) { 
			try {
				linha = (String) fila_2.remove();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			buffer.append(linha+"\r\n"); 
		} 
		return buffer.toString(); 
		}


	public Fila ExtrairSteam(String ano, String mes, String path, String nome) throws IOException{ 
		File arq = new File(path, nome); 
		Fila fila = new Fila(); 
		if(arq.exists() && arq.isFile()) { 
			FileInputStream fluxo = new FileInputStream(arq); 
			InputStreamReader leitor = new InputStreamReader(fluxo); 
			BufferedReader buffer = new BufferedReader(leitor); 
			String linha = buffer.readLine(); 
		while(linha != null) { 
			if(linha.contains(ano) && linha.contains(mes)) { 
				String[] vetor = linha.split(","); 
				String x = "Nome do jogo: "+vetor[0]+" | Média de jogadores ativos: "+vetor[3]; fila.insert(x); 
			} 
			linha = buffer.readLine(); 
		}
		buffer.close(); 
		leitor.close(); 
		fluxo.close(); 
		} else { 
			throw new IOException("Arquivo Inválido"); 
		} 
		return fila; 
	}
}
