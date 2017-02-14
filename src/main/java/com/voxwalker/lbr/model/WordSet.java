package com.voxwalker.lbr.model;

import java.util.Set;

public class WordSet {

	private Set<String> unknown;
	private Set<String> known;
	private Set<String> fresh;
	public Set<String> getUnknown() {
		return unknown;
	}
	public void setUnknown(Set<String> unknown) {
		this.unknown = unknown;
	}
	public Set<String> getKnown() {
		return known;
	}
	public void setKnown(Set<String> known) {
		this.known = known;
	}
	public Set<String> getFresh() {
		return fresh;
	}
	public void setFresh(Set<String> fresh) {
		this.fresh = fresh;
	}
	
	
	
}
