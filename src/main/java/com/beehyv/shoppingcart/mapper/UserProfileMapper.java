package com.beehyv.shoppingcart.mapper;

import com.beehyv.shoppingcart.dto.UserProfileDTO;
import com.beehyv.shoppingcart.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);
    @Mapping(target = "addresses", source = "addresses")
    UserProfileDTO toUserProfileDTO(UserProfile userProfile);
    @Mapping(target = "addresses", source = "addresses")
    UserProfile toUserProfile(UserProfileDTO userProfileDTO);

}
