package com.mindcanary.domain.client;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.mindcanary.domain.ClientId;
import com.mindcanary.infrastructure.client.ClientIdDaoService;

@Named
public class ClientIdDomainServiceImpl implements ClientIdDomainService {

	@Inject
	private ClientIdDaoService clientIdDaoService;

	@Override
	public List<ClientId> getAll() {
		List<ClientId> clientIds = clientIdDaoService.getAll();
		return clientIds;
	}

	@Override
	public List<ClientId> updateAll(List<ClientId> clientIds) {
		List<ClientId> clientIdReturnList = clientIdDaoService.updateAll(clientIds);
		return clientIdReturnList;
	}

	@Override
	public void deleteById(long id) {
		clientIdDaoService.deleteById(id);
	}

	@Override
	public ClientId getByClientId(String clientId) {
		ClientId clientIdToReturn = clientIdDaoService.getByClientId(clientId);
		return clientIdToReturn;
	}

}
