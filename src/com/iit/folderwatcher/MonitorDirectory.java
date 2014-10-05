package com.iit.folderwatcher;
 
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.iitk.javaMailSender.*;
 
public class MonitorDirectory {
 
  public static void main(String[] args) throws IOException,
      InterruptedException {
 
    Path faxFolder = Paths.get("/home/krishna/Downloads");
    WatchService watchService = FileSystems.getDefault().newWatchService();
    faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
 
    boolean valid = true;
    do {
      WatchKey watchKey = watchService.take();
 
      for (WatchEvent<?> event : watchKey.pollEvents()) {
        WatchEvent.Kind kind = event.kind();
        if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
          String fileName = event.context().toString();
          SendMailTLS messagesend =new  SendMailTLS(); 
           messagesend.send(" hey check out my new download "+fileName + "at " + faxFolder);
          
          System.out.println("File Created:" + fileName);
        }
      }
      valid = watchKey.reset();
 
    } while (valid);
 
  }
}