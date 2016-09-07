package br.gov.hucm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.gov.hucm.bd.FotoDAO;
import br.gov.hucm.entidades.Foto;

public class ExportarFoto {

	public static void main(String[] args) {
		
		FotoDAO dao = new FotoDAO();
		List<Foto> fotos = dao.listarFotosCandidatos();

		try {
			FileOutputStream fos = new FileOutputStream(fotos.iterator().next().getFoto().toString());
			ZipOutputStream zos = new ZipOutputStream(fos);

			zipBytes(fotos.iterator().next().getNomeFoto(), fotos.iterator().next().getFoto());

			zos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static byte[] addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

		System.out.println("Writing '" + fileName + "' to zip file");

		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
		return bytes;
	}
	
	public static byte[] zipBytes(String filename, byte[] input) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(filename);
		entry.setSize(input.length);
		zos.putNextEntry(entry);
		zos.write(input);
		zos.closeEntry();
		zos.close();
		return baos.toByteArray();
	}
	
	//Constantes
	   static final int TAMANHO_BUFFER = 4096; // 4kb
	   
	   //método para compactar arquivo
	   public static void compactarParaZip(String arqSaida,String arqEntrada)
	throws IOException{
	       int cont;
	       byte[] dados = new byte[TAMANHO_BUFFER];
	                   
	       BufferedInputStream origem = null;
	       FileInputStream streamDeEntrada = null;
	       FileOutputStream destino = null;
	       ZipOutputStream saida = null;
	       ZipEntry entry = null;
	              
	       try {
	            destino = new FileOutputStream(new File(arqSaida));
	            saida = new ZipOutputStream(new BufferedOutputStream(destino));
	            File file = new File(arqEntrada);
	            streamDeEntrada = new FileInputStream(file);
	            origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
	            entry = new ZipEntry(file.getName());
	            saida.putNextEntry(entry);
	                       
	            while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
	                saida.write(dados, 0, cont);
	            }
	            origem.close();
	            saida.close();
	        } catch(IOException e) {
	            throw new IOException(e.getMessage());
	        }
	   }

}