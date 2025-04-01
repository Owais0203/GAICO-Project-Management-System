package com.java.springBoot.backend.Service;

import com.java.springBoot.backend.Model.Comment;
import com.java.springBoot.backend.Model.Issue;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Repository.CommentRepository;
import com.java.springBoot.backend.Repository.IssueRepository;
import com.java.springBoot.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<Users> userOptional = userRepository.findById(userId);

        if (issueOptional.isEmpty() || userOptional.isEmpty()) {
            throw new Exception("Issue or User not found!!!");
        }

        Issue issue = issueOptional.get();
        Users user = userOptional.get();

        Comment newComment = new Comment();

        newComment.setIssue(issue);
        newComment.setUser(user);
        newComment.setCreatedDateTime(LocalDateTime.now());
        newComment.setContent(content);

        Comment savedComment = commentRepository.save(newComment);

        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<Users> userOptional = userRepository.findById(userId);

        if (commentOptional.isEmpty() || userOptional.isEmpty()) {
            throw new Exception("Comment or User not found!!!");
        }

        Comment comment = commentOptional.get();
        Users user = userOptional.get();

        if (comment.getUser().equals(user)) {
            commentRepository.delete(comment);
        } else {
            throw new Exception("User is not authorized to delete this comment!!!");
        }
    }

    @Override
    public List<Comment> findCommentsByIssueId(Long issueId) throws Exception {
        return commentRepository.findByIssueId(issueId);
    }
}
