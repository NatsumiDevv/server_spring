package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.Room;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.response.MessageResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T18:36:27+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageResponse messageToMessageResponse(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setId( message.getId() );
        messageResponse.setSenderId( messageSenderUserId( message ) );
        messageResponse.setReceiverId( messageReceiverUserId( message ) );
        messageResponse.setConversationId( messageRoomId( message ) );
        messageResponse.setContent( message.getContent() );
        messageResponse.setCreatedAt( message.getCreatedAt() );

        return messageResponse;
    }

    private Integer messageSenderUserId(Message message) {
        if ( message == null ) {
            return null;
        }
        User sender = message.getSender();
        if ( sender == null ) {
            return null;
        }
        Integer userId = sender.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Integer messageReceiverUserId(Message message) {
        if ( message == null ) {
            return null;
        }
        User receiver = message.getReceiver();
        if ( receiver == null ) {
            return null;
        }
        Integer userId = receiver.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Integer messageRoomId(Message message) {
        if ( message == null ) {
            return null;
        }
        Room room = message.getRoom();
        if ( room == null ) {
            return null;
        }
        int id = room.getId();
        return id;
    }
}
