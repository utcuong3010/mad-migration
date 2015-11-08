package com.mad.migration.domain;

import java.io.Serializable;
import java.util.Date;

import com.mad.migration.temp.SourceProgramType;

public class MadItemData implements Serializable {
	
	private String programId;
	private String rootId;
	private int programVersion;
	private SourceProgramType programType;
	private String mediaId;
	private String mediaFilePath;
	private int mediaVersion;
	private int state;
	private String md5;
	private Date createdDate;
	
	private Vendor vendor;

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getRootId() {
		return rootId;
	}

	public void setRootId(String rootId) {
		this.rootId = rootId;
	}

	public int getProgramVersion() {
		return programVersion;
	}

	public void setProgramVersion(int programVersion) {
		this.programVersion = programVersion;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	
	public int getMediaVersion() {
		return mediaVersion;
	}

	public void setMediaVersion(int mediaVersion) {
		this.mediaVersion = mediaVersion;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getMediaFilePath() {
		return mediaFilePath;
	}

	public void setMediaFilePath(String mediaFilePath) {
		this.mediaFilePath = mediaFilePath;
	}
	
	

	public SourceProgramType getProgramType() {
		return programType;
	}

	public void setProgramType(SourceProgramType programType) {
		this.programType = programType;
	}

	@Override
	public String toString() {
		return programId + "," + rootId + "," + programVersion + "," + programType + "," + vendor.getVendorKey()
				+ "," + mediaId + "," + mediaFilePath + "," + mediaVersion
				+ "," + state + "," + md5 + "," + createdDate + " \n";
	}
	

}
