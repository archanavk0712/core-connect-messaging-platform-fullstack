package com.dyashin.coreconnectmessagingplatform.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dyashin.coreconnectmessagingplatform.entity.Message;
import com.dyashin.coreconnectmessagingplatform.entity.User;

public interface MessageRepo extends JpaRepository<Message, Integer> {

	@Query("SELECT m FROM Message m WHERE " + " (m.sender = :user1 AND m.receiver = :user2) "
			+ " OR (m.sender = :user2 AND m.receiver= :user1) ORDER BY m.timestamp ASC")
	List<Message> findConversation(User user1, User user2);

}
