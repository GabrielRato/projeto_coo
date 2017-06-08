package model.business;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/////////////////RESUAMAO?////////////////////////
/*
 * temos a funcao SetDocEmail(string s); que salva o email desde que a box esteja salva com true
 * SetDocSenha(string s); que salva a senha desde que a box esteja salva com true;
 * GetDocEmail(); que retorna um email salvo desde que a box esteja salva com true;
 * GetDocSenha(); que retorna uma senha sava desde que a box estaja salva com true;
 * SaveBoxState();
 *
 */


public class Persistency {
	
	 public void SetDocEmail(String s){
		boolean Estadodabox=GetBoxState();
		if(Estadodabox){
			try{
				FileWriter arqEmail=new FileWriter("Email.txt");
				PrintWriter gravaArq=new PrintWriter(arqEmail);
				gravaArq.printf(s);
				gravaArq.close();
				arqEmail.close();
			}
			catch(Exception e){
				//nao usa;
			}
		
		}
		
	}
	 public void SetDocSenha(String s){
		boolean Estadodabox=GetBoxState();
		if(Estadodabox){
			try{
				FileWriter arqSenha=new FileWriter("PIN.txt");
				PrintWriter gravaArq=new PrintWriter(arqSenha);
				gravaArq.printf(s);
				gravaArq.close();
				arqSenha.close();
			}
			catch(Exception e){
				//nao usa;
			}
		
		}
		
	}
	public String GetDocEmail() {
		boolean Estadodabox=GetBoxState();
		try{
			FileReader arqEmail =new FileReader("Email.txt");
			BufferedReader lerArq = new BufferedReader(arqEmail);
			String s=lerArq.readLine();
			lerArq.close();
			arqEmail.close();
			if(Estadodabox){
				return s;
			}
			return "";
		}
		catch(Exception e){
			return "";
		}
				
	}
	public   String GetDocSenha() {
		boolean Estadodabox=GetBoxState();
		try{
			FileReader arqSenha =new FileReader("PIN.txt");
			BufferedReader lerArq = new BufferedReader(arqSenha);
			String s=lerArq.readLine();
			lerArq.close();
			arqSenha.close();
			if(Estadodabox){
				return s;
			}
			return "";
		}
		catch(Exception e){
			return "";
		}
				
	}
	 public void SaveBoxState(boolean estate){
		try{
			FileWriter arqEmail=new FileWriter("Box.txt");
			PrintWriter gravaArq=new PrintWriter(arqEmail);
			if(estate){
				gravaArq.printf("T");
			}
			else gravaArq.printf("F");
			gravaArq.close();
			arqEmail.close();
		}
		catch(Exception e){
			//nao usa;
		}
	}
	public  boolean GetBoxState() {
		try{
			FileReader arqSenha =new FileReader("Box.txt");
			BufferedReader lerArq = new BufferedReader(arqSenha);
			String s=lerArq.readLine();
			lerArq.close();
			arqSenha.close();
			if(s.equals("T")){
				return true;
			}
			return false;
		}
		catch(Exception e){
			return false;
		}
				
	}
	
}
