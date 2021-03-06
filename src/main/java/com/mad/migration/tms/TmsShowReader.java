package com.mad.migration.tms;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.directv.apg.mad.general.domain.SourceProgramType;
import com.mad.migration.domain.MadItemData;
import com.mad.migration.domain.Vendor;
import com.mad.migration.job.JdbcReader;
import com.mad.migration.utils.FileUtils;

@Component
public class TmsShowReader implements JdbcReader<MadItemData> {
	
	private Logger LOG = LoggerFactory.getLogger(TmsShowReader.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${mad.vendor.tms.containerName}")
	private String vendorContainerName;
	
	@Value("${mad.vendor.tms.thumbContainerName}")
	private String vendorThumbContainerName;
	
	@Value("${mad.vendor.tms.vendorKey}")
	private String vendorKey;
	
	
	@Value("${mad.vendor.tms.movie.original.directory}")
	private String originalDirectory;
	
	@Value("${mad.vendor.tms.movie.thumbnail.directory}")
	private String thumbnailDirectory;
	
	private int offset = 0;
	
	public List<MadItemData> read() throws DataAccessException {
	
		List<MadItemData> tmsData =  null;	
	
		try {
			
			String movieSql = "SELECT t1.TMS_ID,t1.TMS_ROOT_ID,t1.version as programVersion,t2.filename,t2.VERSION as mediaVersion,t2.state,t2.md5 ,t2.created_date "
					+ " FROM tms_movie_program_info as t1 "
					+ " INNER JOIN  tms_showcard_banner_image_info as t2 "
					+ " ON t1.TMS_ROOT_ID=t2.TMS_ROOT_ID AND t2.state=0"
					+ " limit 10 offset " + offset;
			
			offset = offset + 10;
			
			tmsData = jdbcTemplate.query(movieSql,new BeanPropertyRowMapper<MadItemData>(){
				
				@Override
				public MadItemData mapRow(ResultSet result, int rowNumber) throws SQLException {
					MadItemData tmsData = new MadItemData();
					Vendor tmsVendor = new Vendor(vendorKey,vendorContainerName,vendorThumbContainerName);
					tmsData.setVendor(tmsVendor);
					//set data
					tmsData.setProgramId(result.getString("TMS_ID"));
					tmsData.setRootId(result.getString("TMS_ROOT_ID"));
					tmsData.setProgramType(SourceProgramType.MOVIE);
					//TODO:need to set full path
					tmsData.setMediaThumbnailFilePath(thumbnailDirectory + File.separator + tmsData.getRootId()+  File.separator + result.getString("filename"));			        
					tmsData.setMediaFilePath(originalDirectory + File.separator + tmsData.getRootId()+  File.separator + result.getString("filename"));					
			        try {
			        	byte[] image = FileUtils.readFile(tmsData.getMediaFilePath());
						BufferedImage newImage = ImageIO.read(new ByteArrayInputStream(image));						
						tmsData.setMediaId(tmsData.getRootId() + "_" + newImage.getWidth() + "_" + newImage.getHeight());
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
			        
					tmsData.setMediaVersion(result.getInt("mediaVersion"));
					tmsData.setProgramVersion(result.getInt("programVersion"));
					tmsData.setState(result.getInt("state"));
					tmsData.setMd5(result.getString("md5"));
					tmsData.setCreatedDate(result.getDate("created_date"));
					
					return tmsData;
				}
			});			
			
		} catch (DataAccessException ex) {
			tmsData = null;
//			LOG.error("Can not read data with offset: {} with exception {}", offset,ex);
		}
		
		LOG.info("Read the record:" + tmsData);
	
		return tmsData;
	}
	
	@Override
	public int totalItems() throws DataAccessException {
		
		String totalItems = "SELECT count(*) "
				+ " FROM tms_movie_program_info as t1 "
				+ " INNER JOIN  tms_movie_poster_image_info as t2 "
				+ " ON t1.TMS_ROOT_ID=t2.TMS_ROOT_ID AND t2.state=0 ";
		
		return jdbcTemplate.queryForObject(totalItems,Integer.class);
				
	}

	
}
