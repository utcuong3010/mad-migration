package com.mad.migration.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.VendorProgram;
import com.mad.migration.job.item.ItemProcessor;
import com.mad.migration.temp.AssetBuilderService;

@Component
public class MadItemProcessor implements ItemProcessor<MadItemData, VendorProgram> {
	

	@Value("${mad.migration.homeDir}")
	private String migrationOutputDir;	
	

	@Autowired
	private AssetBuilderService assetBuilderService;
	
	

	@Override
	public VendorProgram process(MadItemData item) throws Exception {
		
		System.err.println(" process item");
		Thread.sleep(3000);
		return new VendorProgram();
		
		
		
//		VendorProgram vendorProgram =  null;
//		String monitorFailureFile = migrationOutputDir + File.separator 
//				+ item.getVendor().getVendorKey() + "_failure_" + new SimpleDateFormat("dd-M-yyyy").format(new Date()) + ".txt";
//		try {
//			//read file content from tms data
//			byte[] image = FileUtils.readFile(item.getMediaFilePath());
//	        BufferedImage newImage = ImageIO.read(new ByteArrayInputStream(image));
//			String md5 = DigestUtils.md5Hex(image);
//			String extensionFile = FileUtils.getFileExtension(item.getMediaFilePath());
//			if(StringUtils.equals(md5, item.getMd5())) {
//				vendorProgram = storeInAssetManager(image, extensionFile, item.getVendor(), item);
//				//mapping data 
//				vendorProgram.setProgramId(item.getProgramId());
//				vendorProgram.setProgramVersion(item.getProgramVersion());
//				vendorProgram.setVendorKey(item.getVendor().getVendorKey());
//				vendorProgram.setMediaId(item.getMediaId());
//				vendorProgram.setMediaVersion(item.getMediaVersion());
//				//get mediaID
//				if(item.getMediaId() == null) {
//					//TODO: cheatcode
//					vendorProgram.setMediaId(item.getRootId() + "_" + newImage.getWidth() + "_" + newImage.getHeight());
//				}
//										
//				vendorProgram.setImageAspectRatio(ImageUtil.buildAspectRatio(newImage.getWidth(), newImage.getHeight()));
//				vendorProgram.setImageFormat(extensionFile);
//				vendorProgram.setImageOrientation(ImageUtil.getImageOrientation(newImage.getWidth(), newImage.getHeight()).toString());
//				vendorProgram.setMd5Hash(md5);
//				vendorProgram.setMediaHeight(newImage.getHeight());
//				vendorProgram.setMediaWidth(newImage.getWidth());
//				vendorProgram.setMediaAction(0);//add
//				vendorProgram.setMediaState(item.getState());//GOOD
//				vendorProgram.setCreatedDate(item.getCreatedDate());
//							
//			} else {
//				//log item to check manual
//				FileUtils.writeLog(monitorFailureFile , item.toString());		
//				vendorProgram = null;
//			}
//		} catch (Exception ex) {
//			//log item to check manual
//			FileUtils.writeLog(monitorFailureFile, item.toString());	
//			vendorProgram = null;//dont need to write
//		}
//		 	
//		return vendorProgram;
	}
	
	
	
//	private VendorProgram storeInAssetManager(byte[] image,String extensionFile,Vendor vendor, MadItemData item) throws Exception {
//		//store in asset manager
//		VendorProgram vendorProgram = new VendorProgram();
//	
//		SourceProgramType programType = item.getProgramType();
//		String imageBase64 = Base64.encodeBase64String(image);		
//		AssetResponseObject responseObject = assetBuilderService.storeAssetObject(vendor.getContainerName(), imageBase64,
//				extensionFile, programType);
//		
//		if(responseObject != null && responseObject.getErrorMessages().isEmpty()) {
//			vendorProgram.setAssetDirectLink(responseObject.getAssetLink().getAssetDirectLink());
//			vendorProgram.setAssetIdentifier(responseObject.getAssetLink().getAssetIdentifier());
//			vendorProgram.setAssetUniversalLink(responseObject.getAssetLink().getAssetUniversalLink());
//		}
//		//create thumb nail image
//		String thumbnailBase64 = FileUtils.resizeImage(imageBase64, 80, 120);
//		AssetResponseObject thumbRespObject = assetBuilderService.storeAssetObject(vendor.getThumbnailContainerName(), thumbnailBase64, 
//				extensionFile, programType);
//		
//		if(thumbRespObject != null && thumbRespObject.getErrorMessages().isEmpty()) {
//			vendorProgram.setThumbnailAssetLink(thumbRespObject.getAssetLink().getAssetDirectLink());
//		}
//		return vendorProgram;
//	}
	
}
