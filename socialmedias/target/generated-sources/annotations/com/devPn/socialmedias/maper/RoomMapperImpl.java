package com.devPn.socialmedias.maper;

import com.devPn.socialmedias.model.Room;
import com.devPn.socialmedias.model.response.RoomResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T18:36:27+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomResponse roomToResponse(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponse roomResponse = new RoomResponse();

        roomResponse.setMembers( mapUsersToIds( room.getUsers() ) );
        roomResponse.setId( room.getId() );
        roomResponse.setName( room.getName() );

        return roomResponse;
    }
}
