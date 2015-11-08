package com.mad.migration.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	//
	// private static final String BIN_PATH = "/usr/bin";
	//
	// /**
	// * reading image
	// *
	// * @param absPath
	// * @return
	// */
	// public static byte[] readFile(String absPath) {
	// InputStream is = null;
	// byte[] data = null;
	// try {
	// LOG.info("Reading file:" + absPath);
	// is = new FileInputStream(new File(absPath));
	// data = IOUtils.toByteArray(is);
	// } catch (Exception e) {
	// LOG.error(e.getMessage(), e);
	// } finally {
	// IOUtils.closeQuietly(is);
	// }
	// return data;
	// }
	//
	// public static String getFileExtension(String filePath) {
	//
	// String extension = "";
	// int sep = filePath.lastIndexOf(File.pathSeparator);
	// String fileName = filePath.substring(sep + 1, filePath.length());
	// if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
	// extension = fileName.substring(fileName.lastIndexOf(".") + 1);
	// }
	//
	// LOG.info("file name {} with extension {}",fileName, extension);
	//
	// return extension;
	// }
	//
	// public static String resizeImage(String base64, int width, int height)
	// throws Exception {
	// Base64 base64Util = new Base64();
	// byte[] imageData = base64Util.decode(base64);
	// IMOperation op = new IMOperation();
	// op.addImage("-");
	// op.resize(width, height);
	// op.strip();
	// op.addImage("-");
	// byte[] output = process(op, imageData);
	// if (output != null) {
	// LOG.info("Resize thumbnail image with size {}x{} successfully", width,
	// height);
	// return new Base64().encodeAsString(output);
	// }
	// return null;
	// }
	//
	// /**
	// * process image using image magic convert util
	// *
	// * @param op
	// * operation need to perform
	// * @param srcImage
	// * byte array contain image data
	// * @return byte array contain image data after convert
	// */
	// private static byte[] process(IMOperation op, byte[] srcImage) {
	// InputStream in = null;
	// ByteArrayOutputStream out = null;
	// ConvertCmd convert = null;
	// try {
	// in = new ByteArrayInputStream(srcImage);
	// out = new ByteArrayOutputStream();
	// Pipe pipeIn = new org.im4java.process.Pipe(in, null);
	// Pipe pipeOut = new Pipe(null, out);
	// convert = new ConvertCmd();
	// convert.setSearchPath(BIN_PATH);
	// convert.setInputProvider(pipeIn);
	// convert.setOutputConsumer(pipeOut);
	// LOG.info("Resizing image with command: {}", convert.getCommand());
	// convert.run(op);
	// return out.toByteArray();
	// } catch (Exception e) {
	// if (convert != null) {
	// LOG.error("Error resizing image. Status: {}", convert.getErrorText(), e);
	// } else {
	// LOG.error("Error resizing image. Status: {}", e);
	// }
	// return null;
	// } finally {
	// IOUtils.closeQuietly(in);
	// IOUtils.closeQuietly(out);
	// }
	// }
	//
	// /***
	// * LOG all item
	// */
	public static void writeLog(String path, String msg) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			Files.write(Paths.get(path), msg.getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {
			logger.error("Can not write file: {} with exeception: {}", path, e);
		}
	}

	public static void createDirectory(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			dir.deleteOnExit();
		}
		dir.mkdirs();
	}
	
	public static void deleteFolder(String path) {
		File folder = new File(path);
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f.getPath());
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}

}
