package com.fengyun.entity;

import java.io.Serializable;

public class Callback implements Serializable {
	private String domain;
	private String app;
	private String stream;
	private String uri;
	private String duration;
	private String start_time;
	private String stop_time;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	@Override
	public String toString() {
		return "Callback [domain=" + domain + ", app=" + app + ", stream="
				+ stream + ", uri=" + uri + ", duration=" + duration
				+ ", start_time=" + start_time + ", stop_time=" + stop_time
				+ "]";
	}

}
