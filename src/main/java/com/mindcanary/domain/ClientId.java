package com.mindcanary.domain;

public class ClientId {
	private long id;

	private String clientId;

	private long role;

	public ClientId() {
	}

	public ClientId(long id, String clientId, long role) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof ClientId)) {
			return false;
		}

		ClientId clientId = (ClientId) o;

		return clientId.getClientId().equals(this.getClientId()) && clientId.getRole() == this.getRole();
	}

	// Idea from effective Java : Item 9
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + clientId.hashCode();
		return result;
	}
}
