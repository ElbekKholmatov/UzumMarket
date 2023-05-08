package uz.market.uzum.mappers.comment;


import lombok.NonNull;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import uz.market.uzum.domains.product.Comment;
import uz.market.uzum.dtos.comment.CommentCreateDto;
import uz.market.uzum.dtos.comment.CommentUpdateDTO;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper COMMENT_MAPPER = Mappers.getMapper(CommentMapper.class);

    CommentCreateDto toDto(Comment comment);

    Comment toEntity(CommentCreateDto dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdateCommentEntity(CommentUpdateDTO dto, @MappingTarget @NonNull Comment comment);
}
