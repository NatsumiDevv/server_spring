package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Like;
import com.devPn.socialmedias.model.Message;
import com.devPn.socialmedias.model.response.LikeResponse;
import com.devPn.socialmedias.model.response.MessageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {


    @Mapping(source = "id",target = "id")
    @Mapping(source = "sender.userId",target = "senderId")
    @Mapping(source = "receiver.userId",target = "receiverId")
    @Mapping(source = "room.id",target = "conversationId")
    MessageResponse messageToMessageResponse(Message message);

}
