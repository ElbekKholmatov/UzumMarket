package uz.market.uzum.mappers.comment;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.market.uzum.domains.product.Comment;
import uz.market.uzum.dtos.comment.CommentCreateDto;
import uz.market.uzum.dtos.comment.CommentUpdateDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-07T10:58:47+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentCreateDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentCreateDto.CommentCreateDtoBuilder commentCreateDto = CommentCreateDto.builder();

        commentCreateDto.text( comment.getText() );
        commentCreateDto.rate( comment.getRate() );
        commentCreateDto.productId( comment.getProductId() );

        return commentCreateDto.build();
    }

    @Override
    public Comment toEntity(CommentCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.commentBuilder();

        comment.text( dto.getText() );
        comment.rate( dto.getRate() );
        comment.productId( dto.getProductId() );

        return comment.build();
    }

    @Override
    public void toUpdateCommentEntity(CommentUpdateDTO dto, Comment comment) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getRate() != null ) {
            comment.setRate( dto.getRate() );
        }
        if ( dto.getText() != null ) {
            comment.setText( dto.getText() );
        }
        if ( dto.getProductId() != null ) {
            comment.setProductId( dto.getProductId() );
        }
    }
}
