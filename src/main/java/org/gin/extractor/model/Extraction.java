package org.gin.extractor.model;

import java.util.*;

public class Extraction {
	private int id;
	private String atlas;
	private List<String> anatcon;
	private List<String> boldcon;
	private List<String> corranatcon;
	private List<String> corrboldcon;
	private List<String> roi;	
	private List<String> bisroi;
	private List<String> roiAsym;
	private List<Integer> laterality;
	private String custom;
	private String customname;
	private Boolean asymmetry;   
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getAtlas() {
		return this.atlas;
	}

	public void setAtlas(String atlas) {
		this.atlas = atlas;
	}

	public List<String> getanatcon() {
		return this.anatcon;
	}

	public void setanatcon(List<String> contrasts) {
		this.anatcon = contrasts;
	}
	
	public List<String> getboldcon() {
		return this.boldcon;
	}

	public void setboldcon(List<String> contrasts) {
		this.boldcon = contrasts;
	}
	
	public List<String> getcorranatcon() {
		return this.corranatcon;
	}

	public void setcorranatcon(List<String> contrasts) {
		this.corranatcon = contrasts;
	}
	
	public List<String> getcorrboldcon() {
		return this.corrboldcon;
	}

	public void setcorrboldcon(List<String> contrasts) {
		this.corrboldcon = contrasts;
	}

	public List<String> getRoi() {
		return this.roi;
	}

	public void setRoi(List<String> roi) {
		this.roi = roi;
	}	
	
	public List<String> getbisroi() {
		return this.bisroi;
	}

	public void setbisroi(List<String> bisroi) {
		this.bisroi = bisroi;
	}	
	
	public List<String> getRoiAsym() {
		return this.roiAsym;
	}

	public void setRoiAsym(List<String> roiAsym) {
		this.roiAsym = roiAsym;
	}	
	
	public List<Integer> getLaterality() {
		return this.laterality;
	}
	
	public void setLaterality(List<Integer> lat) {
		this.laterality = lat;
	}
	
	public String getCustom() {
		return this.custom;
	}
	
	public void setCustom(String c) {
		this.custom = c;
	}
	
	public String getCustomname() {
		return this.customname;
	}
	
	public void setCustomname(String c) {
		this.customname = c;
	}
	
	public Boolean getAsymmetry() {
		return this.asymmetry;
	}
	
	public void setAsymmetry(Boolean a) {
		this.asymmetry = a;
	}
}
