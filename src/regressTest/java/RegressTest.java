import autum.com.users.UsersApplication;
import autum.com.users.api.error.ErrorResponse;
import autum.com.users.api.request.CreateUserRequest;
import autum.com.users.api.request.UpdateUserRequest;
import autum.com.users.api.response.UserListResponse;
import autum.com.users.api.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UsersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegressTest extends Base {

    @Autowired
    private TestRestTemplate client;
    @LocalServerPort
    private int port;


    //TODO рассмотреть варианты разделения
    @Test
    public void regress() {
        var baseUrl = "http://localhost:" + port + "/api/v1";

        var urlUsersContr = baseUrl + "/users/";
        var urlUsersCreate = urlUsersContr + "create";
        var urlSubscribeContr = baseUrl + "/subscriptions/user/";
        //-----------------------------------CREATE-USERS---------------------------------------------------------------
        var createUserOneRequest = new CreateUserRequest();
        createUserOneRequest.setFirstName("Autum");
        createUserOneRequest.setLastName("Moren");
        createUserOneRequest.setMiddleName("None");
        createUserOneRequest.setMsisdn("79093245647");
        createUserOneRequest.setEmail("autum.moren@world.com");
        createUserOneRequest.setSex("MALE");
        createUserOneRequest.setBirthdayAt(System.currentTimeMillis() - 1577880000000L);

        var createOneResponse = client.postForEntity(urlUsersCreate, createUserOneRequest, Void.class);
        assertEquals(HttpStatus.OK, createOneResponse.getStatusCode());

        var createUserTwoRequest = new CreateUserRequest();
        createUserTwoRequest.setFirstName("Anti-Autum");
        createUserTwoRequest.setLastName("Anti-Moren");
        createUserTwoRequest.setMiddleName("Anti-None");
        createUserTwoRequest.setMsisdn("79097465423");
        createUserTwoRequest.setEmail("anti.autum.moren@world.com");
        createUserTwoRequest.setSex("MALE");
        createUserTwoRequest.setBirthdayAt(System.currentTimeMillis() - 1577880000000L);

        var createTwoResponse = client.postForEntity(urlUsersCreate, createUserTwoRequest, Void.class);
        assertEquals(HttpStatus.OK, createTwoResponse.getStatusCode());
        //-------------------------------------SEARCH-BY-NAME-----------------------------------------------------------
        var name = "autum";
        var urlUsersSearch = urlUsersContr + "search?name=" + name;
        var searchUserResponse = client.getForEntity(urlUsersSearch, UserListResponse.class);

        assertEquals(HttpStatus.OK, searchUserResponse.getStatusCode());

        var searchBodyResponse = searchUserResponse.getBody();

        assertNotNull(searchBodyResponse);
        assertEquals(2, searchBodyResponse.getTotalCount());
        assertEquals(2, searchBodyResponse.getUsers().size());

        var userByIdentifierOne = searchBodyResponse.getUsers().get(0);
        var userByIdentifierTwo = searchBodyResponse.getUsers().get(1);

        assertEquals("Autum", userByIdentifierOne.getFirstName());
        assertEquals("Moren", userByIdentifierOne.getLastName());
        assertNull(userByIdentifierOne.getAvatarUrl());
        assertNotNull(userByIdentifierOne.getIdentifier());
        //--------------------------------------GET-BY-IDENTIFIERS------------------------------------------------------
        var userIdentifierOne = userByIdentifierOne.getIdentifier();
        var userIdentifierTwo = userByIdentifierTwo.getIdentifier();
        var getUserUrlOne = urlUsersContr + userIdentifierOne;
        var getUserUrlTwo = urlUsersContr + userIdentifierTwo;

        var userOneResponse = client.getForEntity(getUserUrlOne, UserResponse.class);
        assertEquals(HttpStatus.OK, userOneResponse.getStatusCode());
        //TODO проверить поля
        var userTwoResponse = client.getForEntity(getUserUrlTwo, UserResponse.class);
        assertEquals(HttpStatus.OK, userTwoResponse.getStatusCode());
        //----------------------------------SUBSCRIBE-ONE-TO-TWO--------------------------------------------------------
        var subscribeUrl = urlSubscribeContr + userIdentifierTwo + "/subscribe?identifier=" + userIdentifierOne;
        var subscribeResponse = client.postForEntity(subscribeUrl, Void.class, Void.class);
        assertEquals(HttpStatus.OK, subscribeResponse.getStatusCode());
        //----------------------------------------CHECK-ONE-SUBSCRIPTION------------------------------------------------
        var getListSubscriptionsUrl = urlSubscribeContr + userIdentifierOne + "/subscription/list";
        var subscriptionsResponse = client.getForEntity(getListSubscriptionsUrl, List.class);

        assertEquals(HttpStatus.OK, subscriptionsResponse.getStatusCode());
        assertNotNull(subscriptionsResponse.getBody());
        assertEquals(1, subscriptionsResponse.getBody().size());

        var subscription = (LinkedHashMap<String, String>) subscriptionsResponse.getBody().get(0);

        assertEquals(userIdentifierTwo, subscription.get("identifier"));
        assertEquals("Anti-Autum", subscription.get("firstName"));
        assertEquals("Anti-Moren", subscription.get("lastName"));
        assertNull(subscription.get("avatarUrl"));
        //-------------------------------------UNSUBSCRIBE-ONE-FROM-TWO-------------------------------------------------
        var unsubscribeUrl = urlSubscribeContr + userIdentifierTwo + "/unsubscribe?identifier=" + userIdentifierOne;
        var unsubscribeResponse = client.postForEntity(unsubscribeUrl, Void.class, Void.class);
        assertEquals(HttpStatus.OK, unsubscribeResponse.getStatusCode());
        //-------------------------------------CHECK-UNSUBSCRIBE-ONE-FROM-TWO-------------------------------------------
        var subscriptionsAfterUnsubscrResponse = client.getForEntity(getListSubscriptionsUrl, List.class);
        assertEquals(HttpStatus.OK, subscriptionsAfterUnsubscrResponse.getStatusCode());
        assertNotNull(subscriptionsAfterUnsubscrResponse.getBody());
        assertEquals(0, subscriptionsAfterUnsubscrResponse.getBody().size());
        //-----------------------------------------USER-TWO-UPDATE----------------------------------------------------------
        var updateUserUrl = urlUsersContr + userIdentifierTwo + "/update";
        var updateRequest = new UpdateUserRequest();
        updateRequest.setAbout("WTF");
        var updateUserResponse = client.postForEntity(updateUserUrl, updateRequest, Void.class);
        assertEquals(HttpStatus.OK, updateUserResponse.getStatusCode());
        //-----------------------------------------CHECK-UPDATE-TWO-----------------------------------------------------
        var userOneUpdatedResponse = client.getForEntity(getUserUrlTwo, UserResponse.class);
        assertEquals(HttpStatus.OK, userOneUpdatedResponse.getStatusCode());
        var updatedBody = userOneUpdatedResponse.getBody();
        assertNotNull(updatedBody);
        assertEquals("WTF", updatedBody.getAbout());
        //-----------------------------------------DELETE-USERS---------------------------------------------------------
        var deleteUserOneResponse = client.exchange(getUserUrlOne, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.OK, deleteUserOneResponse.getStatusCode());
        var deleteUserTwoResponse = client.exchange(getUserUrlTwo, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.OK, deleteUserTwoResponse.getStatusCode());
        //-----------------------------------------CHECK-DELETED--------------------------------------------------------
        var userOneDeletedResponse = client.getForEntity(getUserUrlOne, ErrorResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, userOneDeletedResponse.getStatusCode());
        var deletedOneBody = userOneDeletedResponse.getBody();
        assertNotNull(deletedOneBody);
        var userTwoDeletedResponse = client.getForEntity(getUserUrlTwo, UserResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, userTwoDeletedResponse.getStatusCode());
        var deletedTwoBody = userOneDeletedResponse.getBody();
        assertNotNull(deletedTwoBody);
    }
}