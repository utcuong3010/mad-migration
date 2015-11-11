package com.mad.migration.job;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.directv.apg.mad.asset.service.AssetBuilderService;
import com.directv.apg.mad.general.domain.SourceProgramType;
import com.directv.mad.asset.client.domain.AssetResponseObject;
import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.domain.VendorProgram;
import com.mad.migration.exception.BusinessException;
import com.mad.migration.exception.ErrorException;
import com.mad.migration.job.item.ItemProcessor;
import com.mad.migration.utils.FileUtils;
import com.mad.migration.utils.ImageUtil;

@Component(value="madItemProcessor")
public class MadItemProcessor implements ItemProcessor<MadItemData, VendorProgram> {
	

	@Value("${mad.migration.homeDir}")
	private String migrationOutputDir;	
	

	@Autowired
	private AssetBuilderService assetBuilderService;
	
	

	@Override
	public VendorProgram process(MadItemData item) throws ErrorException,BusinessException {
		
		
		VendorProgram vendorProgram =  null;
		try {
			//read file content from tms data
			byte[] image = FileUtils.readFile(item.getMediaFilePath());
	        BufferedImage newImage = ImageIO.read(new ByteArrayInputStream(image));
			String md5 = DigestUtils.md5Hex(image);
			String extensionFile = FileUtils.getFileExtension(item.getMediaFilePath());
			if(StringUtils.equals(md5, item.getMd5())) {
				vendorProgram = storeInAssetManager(image, extensionFile, item.getVendor(), item);
				//mapping data 
				vendorProgram.setProgramId(item.getProgramId());
				vendorProgram.setProgramVersion(item.getProgramVersion());
				vendorProgram.setVendorKey(item.getVendor().getVendorKey());
				vendorProgram.setMediaId(item.getMediaId());
				vendorProgram.setMediaVersion(item.getMediaVersion());
				//get mediaID
				if(item.getMediaId() == null) {
					//TODO: cheatcode
					vendorProgram.setMediaId(item.getRootId() + "_" + newImage.getWidth() + "_" + newImage.getHeight());
				}
										
				vendorProgram.setImageAspectRatio(ImageUtil.buildAspectRatio(newImage.getWidth(), newImage.getHeight()));
				vendorProgram.setImageFormat(extensionFile);
				vendorProgram.setImageOrientation(ImageUtil.getImageOrientation(newImage.getWidth(), newImage.getHeight()).toString());
				vendorProgram.setMd5Hash(md5);
				vendorProgram.setMediaHeight(newImage.getHeight());
				vendorProgram.setMediaWidth(newImage.getWidth());
				vendorProgram.setMediaAction(0);//add
				vendorProgram.setMediaState(item.getState());//GOOD
				vendorProgram.setCreatedDate(item.getCreatedDate());
							
			} else {

				throw new BusinessException(String.format("Source md5 {} is difference with disk md5 {} ",item.getMd5() ,md5));
			}
		} catch (Exception ex) {
			//log item to check manual
			throw new ErrorException(ex.getMessage());			
		}
		 	
		return vendorProgram;
		
	}
	
	
	
	private VendorProgram storeInAssetManager(byte[] image,String extensionFile,Vendor vendor, MadItemData item) throws Exception {
		//store in asset manager
		VendorProgram vendorProgram = new VendorProgram();
	
		SourceProgramType programType = item.getProgramType();
		String imageBase64 = Base64.encodeBase64String(image);		
		AssetResponseObject responseObject = assetBuilderService.storeAssetObject(vendor.getContainerName(), imageBase64,
				extensionFile, programType);
		
		if(responseObject != null && responseObject.getErrorMessages().isEmpty()) {
			vendorProgram.setAssetDirectLink(responseObject.getAssetLink().getAssetDirectLink());
			vendorProgram.setAssetIdentifier(responseObject.getAssetLink().getAssetIdentifier());
			vendorProgram.setAssetUniversalLink(responseObject.getAssetLink().getAssetUniversalLink());
		}
		//create thumb nail image
		String thumbnailBase64 = FileUtils.resizeImage(imageBase64, 80, 120);
		AssetResponseObject thumbRespObject = assetBuilderService.storeAssetObject(vendor.getThumbnailContainerName(), thumbnailBase64, 
				extensionFile, programType);
		
		if(thumbRespObject != null && thumbRespObject.getErrorMessages().isEmpty()) {
			vendorProgram.setThumbnailAssetLink(thumbRespObject.getAssetLink().getAssetDirectLink());
		}
		return vendorProgram;
	}
	
}
