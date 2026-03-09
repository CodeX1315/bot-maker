package com.botmaker.assignment.mapper;

import com.botmaker.assignment.dto.RegisterRequest;
import com.botmaker.assignment.dto.UserResponse;
import com.botmaker.assignment.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(RegisterRequest request);
    UserResponse toDto(UserEntity user);
}