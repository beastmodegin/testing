package com.fdmgroup.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class XMLDownloader {

	public static void downloadXML(String urlString,String file) throws IOException{
		
		URL url=new URL(urlString);
		ReadableByteChannel readableByteChannel=Channels.newChannel(url.openStream());
		FileOutputStream fileOutputStream=new FileOutputStream(file);
		FileChannel fileChannel=fileOutputStream.getChannel();
		fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		fileOutputStream.close();
		readableByteChannel.close();
	}
}
