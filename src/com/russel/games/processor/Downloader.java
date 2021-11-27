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
 *
 */
public class Downloader implements Runnable {
	private String link;
	private File download;
	
	/**
	 * Creates a downloader with a http link and a file object to write the download to 
	 * @param link
	 * @param download
	 */
	public Downloader(String link, File download) {
		this.link = link;
		this.download = download;
		
	}
	
	//https://www.youtube.com/watch?v=rd6m-6l2xQQ
	/**
	 * Downloads a file from the web and writes it to file object of this class.
	 */
	@Override
	public void run() {
		try {
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			double fileSize = (double)http.getContentLength();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(download);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			double downloaded = 0.0;
			int read = 0;
			double percentDownloaded = 0.0;
			
			System.out.println("DefaultDictionary.txt isn't downloaded onto your system!");
			System.out.println("Downloading file...");
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
				downloaded += read;
				//percentDownloaded = (downloaded*100)/fileSize;
				//String percent = String.format("%.2f", percentDownloaded);
				//System.out.println("Downloaded..." + percent + "%");
			}
			
			bout.close();
			in.close();
			System.out.println("Download complete!");
			
		}catch (IOException e){
			System.out.println("Couldnt download file.");
		}
		
	}

}
