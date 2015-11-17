package com.mad.migration.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mad.migration.domain.VendorProgram;
import com.mad.migration.job.item.ItemWriter;

@Component
@Transactional
public class MadItemWriter implements ItemWriter<VendorProgram> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private String INSERT_VENDOR_PROGRAM = "INSERT INTO vendor_program(vendorKey,programId,programVersion,rootId,programType,createdDate,fileId,recordId)"
			+ " VALUES (?,?,?,?,?,?,?,?) ";
	
	private String INSERT_VENDOR_MEDIA = "INSERT INTO vendor_media(vendorKey,mediaId,versionNumber,assetDirectLink,assetIdentifier,"
			+ "assetUniversalLink,thumbnailAssetLink,mediaWidth,mediaHeight,md5Hash,"
			+ "mediaState,mediaAction,createdDate,imageAspectRatio,imageOrientation,imageType,imageFormat,imageCategory) "
			+ "VALUE(?,?,?,?,?,"
			+ "?,?,?,?,?,"
			+ "?,?,?,?,?,?,?,?)";
	
	private String INSERT_MOVIE_MEDIA_MAPPING = "INSERT INTO vendor_movie_media_mapping (vendorKey,programBaseIdentifier,sourceProgramId,version,mediaId,mediaVersion,imageCategory,createdDate,gmsId,imageDeleted)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private String INSERT_SHOW_MEDIA_MAPPING = "INSERT INTO vendor_show_media_mapping (vendorKey,programBaseIdentifier,sourceProgramId,version,mediaId,mediaVersion,imageCategory,createdDate,gmsId,imageDeleted)"
			+ " VALUES (?,?,?,?,?,?,?,?)";
	
	private String INSERT_EPISODE_MEDIA_MAPPING = "INSERT INTO vendor_episode_media_mapping (vendorKey,programBaseIdentifier,version,mediaId,mediaVersion,imageCategory,createdDate,gmsId,imageDeleted)"
			+ " VALUES (?,?,?,?,?,?,?,?,?)";
	
	@Override
	public void write(List<? extends VendorProgram> items) throws Exception {
	
	try {
		
		for (VendorProgram program: items) {
			//save program
			
			jdbcTemplate.update(INSERT_VENDOR_PROGRAM,
					program.getVendorKey(),program.getProgramId(),program.getProgramVersion(),program.getRootId(),program.getProgramType().toString(),program.getCreatedDate(),0,0);
			
			//save media
			jdbcTemplate.update(INSERT_VENDOR_MEDIA,program.getVendorKey(),program.getMediaId(),program.getMediaVersion(),program.getAssetDirectLink(),program.getAssetIdentifier(),
					program.getAssetUniversalLink(),program.getThumbnailAssetLink(),program.getMediaWidth(),program.getMediaHeight(),program.getMd5Hash(),
					program.getMediaState(),program.getMediaAction(),program.getCreatedDate(),program.getImageAspectRatio(),program.getImageOrientation(),program.getImageType(),program.getImageFormat(),program.getImageCategory());
			
			//save mapping base on which type
			switch (program.getProgramType()) {
			case MOVIE:
								
				jdbcTemplate.update(INSERT_MOVIE_MEDIA_MAPPING,program.getVendorKey(), program.getProgramId(),program.getProgramId(),program.getProgramVersion(),program.getMediaId(),program.getMediaVersion(),program.getImageCategory(),program.getCreatedDate(),"",0);
				
				break;
			case SHOW:
				jdbcTemplate.update(INSERT_SHOW_MEDIA_MAPPING,program.getVendorKey(), program.getProgramId(),program.getProgramId(),program.getProgramVersion(),program.getMediaId(),program.getMediaVersion(),program.getImageCategory(),program.getCreatedDate());
				
				break;
			case EPISODE:
				
				jdbcTemplate.update(INSERT_EPISODE_MEDIA_MAPPING,program.getVendorKey(), program.getProgramId(),program.getProgramId(),program.getProgramVersion(),program.getMediaId(),program.getMediaVersion(),program.getImageCategory(),program.getCreatedDate());
				break;
			default:
				break;
			}
			
			
		}
	} catch(Exception ex) {
		throw ex;
	}
		
	}
}
