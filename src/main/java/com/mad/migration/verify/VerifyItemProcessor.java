package com.mad.migration.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mad.migration.domain.MadItemData;

@Component
public class VerifyItemProcessor{

	@Autowired
	private JdbcTemplate jdbTemplate;
	
	public Boolean process(MadItemData item) throws Exception {
		boolean verify = true;
		//verify vendor program
		verify = verifyVendorProgram(item);
		if(!verify) return verify;
		//verify 
		verify = verifyVendorMedia(item);
		//verify in media mapping data
		switch (item.getProgramType()) {
		case MOVIE:
			verify =  verifyVendorMovieMediaMapping(item);
			break;
		case SHOW:
			verify =  verifyVendorShowMediaMapping(item);
			break;
		case EPISODE:
			verify =  verifyVendorEpisodeMediaMapping(item);
			break;

		default:
			verify = false;
			break;
		}
		
		return verify;
	}

	private boolean verifyVendorProgram(MadItemData item) {
		String sql = "SELECT count(*) FROM vendor_program where vendorKey=? and programId=? and programVersion=? and programType=? ";
		return jdbTemplate.queryForObject(sql, Integer.class,
				item.getVendor().getVendorKey(),
				item.getProgramId(),
				item.getProgramVersion(),
				item.getProgramType()) > 0 ? true: false;
	}
	
	private boolean verifyVendorMedia(MadItemData item) {
		String sql = "SELECT count(*) FROM vendor_media where "
				+ "vendorKey=? and mediaId=? and versionNumber=? and md5Hash=? ";
		return jdbTemplate.queryForObject(sql, Integer.class,
				item.getVendor().getVendorKey(),
				item.getMediaId(),
				item.getMediaVersion(),
				item.getMd5()) > 0 ? true: false;
	}
	
	private boolean verifyVendorMovieMediaMapping(MadItemData item) {
		String sql = "SELECT count(*) FROM vendor_movie_media_mapping "
				+ " where vendorKey=? and programBaseIdentifier=? and version=? and mediaId=? and mediaVersion=? ";
		return jdbTemplate.queryForObject(sql, Integer.class,
				item.getVendor().getVendorKey(),item.getProgramId(),item.getProgramVersion(),item.getMediaId(),item.getMediaVersion()
				
				) > 0 ? true: false;
		
	}
	private boolean verifyVendorShowMediaMapping(MadItemData item) {
	
		String sql = "SELECT count(*) FROM vendor_show_media_mapping "
				+ " where vendorKey=? and programBaseIdentifier=? and version=? and mediaId=? and mediaVersion=? ";
		return jdbTemplate.queryForObject(sql, Integer.class,
				item.getVendor().getVendorKey(),item.getProgramId(),item.getProgramVersion(),item.getMediaId(),item.getMediaVersion()
				
				) > 0 ? true: false;
	}
	private boolean verifyVendorEpisodeMediaMapping(MadItemData item) {
		String sql = "SELECT count(*) FROM vendor_episode_media_mapping "
				+ " where vendorKey=? and programBaseIdentifier=? and version=? and mediaId=? and mediaVersion=? ";
		return jdbTemplate.queryForObject(sql, Integer.class,
				item.getVendor().getVendorKey(),item.getProgramId(),item.getProgramVersion(),item.getMediaId(),item.getMediaVersion()
				
				) > 0 ? true: false;
	}
}
