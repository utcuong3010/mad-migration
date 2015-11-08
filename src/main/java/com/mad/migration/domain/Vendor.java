package com.mad.migration.domain;

import java.io.Serializable;

public class Vendor implements Serializable{
	
	private String vendorKey;
	private String containerName;
	private String thumbnailContainerName;
	
	
	public Vendor(String vendorKey, String containerName, String thumbnailContainerName) {
		super();
		this.vendorKey = vendorKey;
		this.containerName = containerName;
		this.thumbnailContainerName = thumbnailContainerName;
	}
	
	
	public String getVendorKey() {
		return vendorKey;
	}
	public void setVendorKey(String vendorKey) {
		this.vendorKey = vendorKey;
	}
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	public String getThumbnailContainerName() {
		return thumbnailContainerName;
	}
	public void setThumbnailContainerName(String thumbnailContainerName) {
		this.thumbnailContainerName = thumbnailContainerName;
	}
	
	

}
