package com.example.jaxws.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;

import com.example.jaxws.error.FileNotFound;
import com.sun.xml.ws.developer.StreamingDataHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@MTOM
@WebService(endpointInterface = "com.example.jaxws.service.FileTransferer")
public class FileTransfererImpl implements FileTransferer {

	private static String uploadBasePath = "UploadedFiles";
	private static String downloadBasePath = "DownloadFiles";
	private static String loadedDate = null;

	static {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		loadedDate = sdf.format(now);
	}

	private static Log log = LogFactory.getLog(FileTransfererImpl.class);

	@Override
	public void upload(@XmlMimeType("application/octet-stream") DataHandler dh, String fileName) throws Exception {

		try {
			File file = getUploadFile(fileName);
			file.getParentFile().mkdirs();
			log.info("Start file upload to " + file.getPath());
			streamingUpload(dh, file);
			log.info("End file upload to " + file.getPath());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@XmlMimeType("application/octet-stream")
	public DataHandler download(String fileName) throws FileNotFound, Exception {
		try {
			File file = getDownloadFile(fileName);
			if (!file.exists())
				throw new FileNotFound(file.getPath());
			return new DataHandler(new FileDataSource(file));
		} catch (FileNotFound e) {
			log.error("File Not Found.", e);
			throw e;
		} catch (Exception e) {
			log.error("Error.", e);
			throw e;
		}
	}

	private File getUploadFile(String fileName) {
		Path p = Paths.get(uploadBasePath, loadedDate, fileName);
		return p.toFile();
	}

	private File getDownloadFile(String fileName) {
		Path p = Paths.get(downloadBasePath, fileName);
		return p.toFile();
	}

	private void streamingUpload(DataHandler data, File file) throws IOException {
		try (StreamingDataHandler dh = (StreamingDataHandler) data) {
			dh.moveTo(file);
			dh.close();
		}
	}

}
