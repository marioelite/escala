package br.gov.hucm.manageBeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.gov.hucm.bd.FotoDAO;
import br.gov.hucm.entidades.Foto;
import br.gov.hucm.util.Zip;

@ManagedBean(name = "fotoMB")
@ViewScoped
public class FotoManagedBean {

	private Foto foto = new Foto();
	private FotoDAO fotoDAO = new FotoDAO();
	private FileOutputStream fos;
	private StreamedContent file;

	public FotoManagedBean() {
		listaFotos();
		InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/temp/fotos.zip");
		file = new DefaultStreamedContent(stream, "application/zip", "fotoCandidatos.zip");
	}

	public void salvaFoto() {

		try {
			fotoDAO.save(foto);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			foto = new Foto();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto adicionada", "Foto adicionada"));
		}
	}

	public void removerArquivos(File f) {
        // Se o arquivo passado for um diretório
        if (f.isDirectory()) {
                /* Lista todos os arquivos do diretório em um array 
                   de objetos File */
                File[] files = f.listFiles();
                // Identa a lista (foreach) e deleta um por um
                for (File file : files) {
                        file.delete();
                }
        }
}
	
	public void listaFotos() {

		try {
			ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

			List<Foto> fotos = fotoDAO.listarFotosCandidatos();
			File folder = new File(sContext.getRealPath("/temp"));
			removerArquivos(folder);
			
			if (!folder.exists())
				folder.mkdirs();

			for (Foto f : fotos) {
				String nomeArquivo = f.getNomeFoto() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator + nomeArquivo;
				criaArquivo(f.getFoto(), arquivo);
			}
			Zip.zipFiles(sContext.getRealPath("/temp"), sContext.getRealPath("/temp"), folder.list(), "fotos.zip");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void criaArquivo(byte[] bytes, String arquivo) {

		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void processFileUpload(FileUploadEvent uploadEvent) {

		try {
			foto.setNomeFoto(uploadEvent.getFile().getFileName());
			foto.setFoto(uploadEvent.getFile().getContents());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public StreamedContent getFile() {
		return file;
	}
}