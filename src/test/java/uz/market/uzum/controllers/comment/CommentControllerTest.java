package uz.market.uzum.controllers.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uz.market.uzum.UzumMarketApplication;
import uz.market.uzum.domains.product.Comment;
import uz.market.uzum.dtos.comment.CommentUpdateDTO;
import uz.market.uzum.repositories.ProductRepository;
import uz.market.uzum.services.comment.CommentService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UzumMarketApplication.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void createCommentTest() throws Exception {


        Comment comment = Comment.commentBuilder()
                .rate((byte) 2)
                .text("comment")
                .productId(1)
                .build();

        when(commentService.create(any())).thenReturn(comment);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/comment")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(comment))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("comment"))
                .andReturn();

        String contentString = mvcResult.getResponse().getContentAsString();

        assertThat(contentString).containsIgnoringCase("comment");
        verify(commentService, atLeastOnce()).create(any());

    }

    @Test
    public void getCommentsByProductIdTest() throws Exception {


        when(commentService.getComments(anyInt())).thenReturn(List.of(Comment.commentBuilder().id(1).build()));
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/comment/{productId}", 1)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        Comment[] comments = objectMapper.readValue(contentAsString, Comment[].class);
        assertThat(comments.length == 1);
        verify(commentService, atLeastOnce()).getComments(anyInt());


    }

    @Test
    public void updateCommentTest() throws Exception {
        int commentId = 1;
        CommentUpdateDTO commentUpdateDTO = new CommentUpdateDTO();
        commentUpdateDTO.setText("Updated comment");

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setProductId(1);
        comment.setText("Test comment");

        when(commentService.update(any(CommentUpdateDTO.class), eq(commentId))).thenReturn(comment);

        mockMvc.perform(put("/api/v1/comment/{id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(commentUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(commentId))
                .andExpect(jsonPath("$.text").value(comment.getText()));
    }

    @Test
    @WithMockUser(username = "A", authorities = "A")
    public void deleteCommentTest() throws Exception {

        Comment comment = Comment.commentBuilder().id(1).build();

        when(commentService.delete(comment.getId())).thenReturn(comment);

        MvcResult mvcResult = mockMvc
                .perform(delete("/api/v1/comment/{id}", comment.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(comment.getId())).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        Comment comment1 = objectMapper.readValue(contentAsString, Comment.class);
        assertThat(comment1.getId()).isEqualTo(1);
    }

}
