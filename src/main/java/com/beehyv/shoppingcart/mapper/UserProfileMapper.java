package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.UserProfileDTO;
import com.beehyv.shoppingcart.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfileDTO toUserProfileDTO(UserProfile userProfile);
    UserProfile toUserProfile(UserProfileDTO userProfileDTO);

}
