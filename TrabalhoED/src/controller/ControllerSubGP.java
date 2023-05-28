package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.juliarafaela.ListaInt.Lista;
import model.Grupos;

public class ControllerSubGP implements ActionListener {
	 private JTextField    textFieldSubarea;
	 private  JTextArea   textAreaCS;
	 private  JTextField textFieldNOMEGP;
	private  JTextField textFieldTEMAGP;
	private  JTextField textFieldAREA;
	private  JTextField textFieldDATA;
	 private JTextField  textFieldCOD;
	private  JTextArea  TextAreaNomeAlunosGP;
	private  JTextArea  textAreaRAGP;
	
	
	
	public ControllerSubGP(JTextField textFieldSubarea, JTextArea textAreaCS, JTextField textFieldNOMEGP,
			JTextField textFieldTEMAGP, JTextField textFieldAREA, JTextField textFieldDATA, JTextField textFieldCOD,
			JTextArea textAreaNomeAlunosGP, JTextArea textAreaRAGP) {
		super();
		this.textFieldSubarea = textFieldSubarea;
		this.textAreaCS = textAreaCS;
		this.textFieldNOMEGP = textFieldNOMEGP;
		this.textFieldTEMAGP = textFieldTEMAGP;
		this.textFieldAREA = textFieldAREA;
		this.textFieldDATA = textFieldDATA;
		this.textFieldCOD = textFieldCOD;
		TextAreaNomeAlunosGP = textAreaNomeAlunosGP;
		this.textAreaRAGP = textAreaRAGP;
	}



	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Pesquisar")) {
		
				try {
					Consulta();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
	}
		
	}



	private void Consulta() throws IOException {
		Grupos grupo=new Grupos();
		grupo.subarea=textFieldSubarea.getText();
		grupo.nome=textFieldNOMEGP.getText();
		grupo.tema=textFieldTEMAGP.getText();
		grupo.area=textFieldAREA.getText();
		grupo.data=textFieldDATA.getText();
		grupo.codigo=textFieldCOD.getText();
		grupo.alunos=TextAreaNomeAlunosGP.getText();
		grupo.Ra=textAreaRAGP.getText();
		
		grupo=buscaGrupo(grupo);
		
		if(grupo.codigo!= null) {
			textAreaCS.setText("Codigo: "+ grupo.codigo+" - nome: "+grupo.nome +"- tema "+ grupo.tema+ "- area "+ grupo.area+ "- subarea"+ grupo.subarea+ "-data "+ grupo.data+ "- alunos "+ grupo.alunos+ "- Ra "+ grupo.Ra);
		}else {
			textAreaCS.setText("Grupo não encontrado");
		}
		
	}



	private Grupos buscaGrupo(Grupos grupo) throws IOException {
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[0].equals(grupo.subarea)) {
					 grupo.nome=vetLinha[1];
					 grupo.tema=vetLinha[2];
					 grupo.area=vetLinha[3];
					 grupo.data=vetLinha[5];
					 grupo.codigo=vetLinha[6];
					 grupo.alunos=vetLinha[7];
					 grupo.Ra=vetLinha[8];
					 
					 
					 break;
				 }
				 linha=buffer.readLine();
				 
	}           
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
		 return grupo;
	}
}