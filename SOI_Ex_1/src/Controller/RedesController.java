package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController() {
		super();
	}
	
	public String os() {
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		return os +" - v. " + version + " - arch. " + arch;
	}
	
	public void ip (String os) {
		String comando = null;
		if (os.contains("Windows")) {
			comando = "ipconfig";
		} else if (os.contains("Linux")) {
			comando = "ip a";
		}
		try {
			Process p = Runtime.getRuntime().exec(comando);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				if (linha.contains("Ethernet") || linha.contains("IPv4") || linha.contains("inet ")) {
					System.out.println(linha);
				} 
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ping(String os) {
		String comando = null;
		if (os.contains("Windows")) {
			comando = "ping -n 10 www.google.com";
		} else if (os.contains("Linux")) {
			comando = "ping -c 10 www.google.com";
		}
		try {
			Process p = Runtime.getRuntime().exec(comando);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			int contador = 0;
			double media;
			double soma = 0;
			while (linha != null) {
				if (os.contains("Windows")) {
					if (linha.contains("tempo=")) {
					contador = contador + 1;
					soma = soma + Integer.parseInt(linha.substring((linha.lastIndexOf("tempo=") + 6), linha.indexOf("ms")));
					}
					linha = buffer.readLine();
				} else if (os.contains("Linux")) {
					contador = contador + 1; 
					soma = soma + Integer.parseInt(linha.substring((linha.lastIndexOf("time=") + 5), (linha.indexOf("ms") - 1)));
				}
			}
			media = soma/contador;
			System.out.println("A média de ping está em: " + media + "ms");
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}