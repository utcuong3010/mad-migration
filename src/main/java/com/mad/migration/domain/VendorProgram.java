package com.mad.migration.domain;


import com.mad.migration.temp.SourceProgramType;

public class VendorProgram extends VendorMedia{

	
	private String programId;
	private int programVersion;
	private SourceProgramType programType;
	private String rootId;
	
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public int getProgramVersion() {
		return programVersion;
	}
	public void setProgramVersion(int programVersion) {
		this.programVersion = programVersion;
	}
	public String getRootId() {
		return rootId;
	}
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}
	public SourceProgramType getProgramType() {
		return programType;
	}
	public void setProgramType(SourceProgramType programType) {
		this.programType = programType;
	}
	

}
