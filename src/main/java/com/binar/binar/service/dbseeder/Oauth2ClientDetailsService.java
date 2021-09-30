package com.binar.binar.service.dbseeder;

import com.binar.binar.repository.oauth.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
//@EnableCaching
//public class Oauth2ClientDetailsService implements ClientDetailsService, CacheObjectInterface {
public class Oauth2ClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
//    @Cacheable(value = "oauth_client_id", unless = "#result == null")
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        ClientDetails client = clientRepository.findOneByClientId(s);
        if (null == client) {
            throw new ClientRegistrationException("Client not found");
        }

        return client;
    }

    @CacheEvict("oauth_client_id")
    public void clearCache(String s) {
        System.out.println("ini cache  oauth_client_id=  "+s);
    }

//    @CacheEvict(value = "oauth_client_id", allEntries = true)
//    @Override
//    public void clearCache() {
//    }
}
