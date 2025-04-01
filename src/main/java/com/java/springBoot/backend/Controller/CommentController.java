package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Model.Comment;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Request.CreateCommentRequest;
import com.java.springBoot.backend.Response.MessageResponse;
import com.java.springBoot.backend.Service.CommentService;
import com.java.springBoot.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest request, @RequestHeader("Authorization") String jwt) throws Exception {

        Users user = userService.findUserProfileByJwt(jwt);
        Comment createdComment = commentService.createComment(request.getIssueId(), user.getId(), request.getContent());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String jwt) throws Exception {

        Users user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());

        return new ResponseEntity<>(new MessageResponse("Comment deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception {
        List<Comment> comments = commentService.findCommentsByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
