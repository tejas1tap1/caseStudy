package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.UserCredentialsDTO;
import com.beehyv.shoppingcart.dto.UserProfileDTO;
import com.beehyv.shoppingcart.entity.UserCredentials;
import com.beehyv.shoppingcart.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses=UserProfileMapper.class)
public interface UserCredentialsMapper {
    UserCredentialsMapper INSTANCE= Mappers.getMapper(UserCredentialsMapper.class);
    @Mapping(target = "userProfileDTO", source = "userProfile")
    UserCredentialsDTO toUserCredentialsDTO(UserCredentials userCredentials);
    @Mapping(target = "userProfile", source = "userProfileDTO")
    UserCredentials toUserCredentials (UserCredentialsDTO userCredentialsDTO);
}
