package br.gov.hucm.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

	private Zip() {
	}

	private static byte[] read(File file) throws Exception {
		byte[] result = null;

		if (file != null && !file.isDirectory()) {
			final long length = file.length();
			result = new byte[(int) length];
			InputStream fi = new FileInputStream(file);
			byte b;
			long count = 0;

			while ((b = (byte) fi.read()) != -1) {
				result[(int) count++] = b;
			}
			fi.close();
		}
		return result;
	}

	/**
	 * @param out
	 *            - Arquivo destino
	 * @param file
	 *            - Arquivo ou Diretório a ser zipado
	 * @param path
	 *            - Caminho do arquivo de destino
	 * @throws Exception
	 */
	private static boolean addToZip(ZipOutputStream out, File file, String path) throws Exception {
		byte data[] = null;
		ZipEntry entry = null;

		if (file != null) {
			if (file.exists()) {
				if (file != null) {
					if (file.isDirectory()) {
						File[] files = file.listFiles();

						for (int cont = 0; cont < files.length; cont++) {
							addToZip(out, files[cont], path);
						}
					} else {
						String name = file.getName();
						entry = new ZipEntry(name);
						out.putNextEntry(entry);
						data = read(file);
						if (data != null && data.length > 0) {
							out.write(data, 0, data.length);
						}

						out.closeEntry();
						out.flush();
					}
				}

				return true;
			}
		}

		return false;
	}

	/**
	 * Comprime um diretório ou arquivo.
	 * 
	 * @param zipName
	 *            - Nome no arquivo zip que será criado.
	 * @param dirName
	 *            - Nome do arquivo ou diretório a ser comprimido.
	 */
	public static boolean zip(String zipName, String dirName) {
		ZipOutputStream out = null;
		FileOutputStream dest = null;
		CheckedOutputStream checksum = null;
		boolean zipado = false;

		try {
			dest = new FileOutputStream(new File(zipName));
			checksum = new CheckedOutputStream(dest, new Adler32());
			out = new ZipOutputStream(new BufferedOutputStream(checksum));
			File dir = new File(dirName);
			String parent = dir.getParent();
			int length = parent.length();
			String substring = parent.substring(0, length);
			zipado = addToZip(out, dir, substring);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error err) {
			err.printStackTrace();
		} finally {
			try {
				if (zipado) {
					out.flush();
					out.finish();
					out.close();
				} else {
					out = null;
				}
			} catch (IOException e) {
				zipado = false;
				e.printStackTrace();
			} catch (Error err) {
				zipado = false;
				err.printStackTrace();
			}

			System.gc();
		}

		return zipado;
	}
	
	public static void zipFiles(String dirImp, String dirTmp, String[] filenames, String outFilename) {
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];
        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dirTmp + "/" + outFilename));
            // Compress the files
            for (int i = 0; i < filenames.length; i++) {
                FileInputStream in = new FileInputStream(dirImp + "/" + filenames[i]);
                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(filenames[i]));
                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // Complete the entry
                out.closeEntry();
                in.close();
            }
            // Complete the ZIP file
            out.close();
        } catch (IOException e) {
        }
    }
}