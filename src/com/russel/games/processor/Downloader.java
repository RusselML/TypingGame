package com.russel.games.processor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A downloader may be used to retrieve a file from the web with a given url
 * @author Russel
 * @since 11/30/2021
 */
public class Downloader implements Runnable {
	private String urlLink;
	private File download;
	
	/**
	 * Creates a downloader with a http link and a file object to write the download to 
	 * @param urlLink a string representing the actual url of the file to download
	 * @param download a file object to be written to
	 */
	public Downloader(String urlLink, File download) {
		this.urlLink = urlLink;
		this.download = download;	
	}
	
	
	/**
	 * Downloads a file from the web and writes it to file object of this class.
	 * 
	 * TODO - https://www.baeldung.com/java-download-file
	 */
	@Override
	public void run() {
		try {
			//open the url connection
			URL url = new URL(urlLink);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			
			//setup the buffer anf file output from the connection
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(download);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			int read = 0;
		
			System.out.println("DefaultDictionary.txt isn't downloaded onto your system!");
			System.out.println("Downloading file...");
			
			//write data from the buffered input stream to the file 
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
			}
			 
			//close everything
			bout.close();
			in.close();
			System.out.println("Download complete!");
			
		}catch (IOException e){
			System.out.println("Couldnt download file.");
		}
		
	}

}
