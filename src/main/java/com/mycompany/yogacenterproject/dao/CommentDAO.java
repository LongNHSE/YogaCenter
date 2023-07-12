/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.CommentDTO;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devli
 */
public class CommentDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<CommentDTO> getAllComments(){
        List<CommentDTO> listCmt = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[blogComment]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                CommentDTO cmtDTO = new CommentDTO();
                BlogDTO blogDTO = new BlogDTO();
                cmtDTO.setCommentID(rs.getString("commentID"));
                cmtDTO.setCommentContent(rs.getString("commentContent"));
                blogDTO.setMaBlog(rs.getString("maBlog"));
                cmtDTO.setBlogID(blogDTO);
                cmtDTO.setCreateDate(rs.getDate("dateCreated"));
                listCmt.add(cmtDTO);
            }
        } catch (Exception e) {
        }
        return listCmt;
    }
    
        public List<CommentDTO> getAllCommentsByBlogID(String bid){
        List<CommentDTO> listCmt = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[blogComment] where [maBlog] = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bid);
            rs = ps.executeQuery();
            while(rs.next()){
                CommentDTO cmtDTO = new CommentDTO();
                BlogDTO blogDTO = new BlogDTO();
                cmtDTO.setCommentID(rs.getString("commentID"));
                cmtDTO.setCommentContent(rs.getString("commentContent"));
                blogDTO.setMaBlog(rs.getString("maBlog"));
                cmtDTO.setBlogID(blogDTO);
                cmtDTO.setCreateDate(rs.getDate("dateCreated"));
                listCmt.add(cmtDTO);
            }
        } catch (Exception e) {
        }
        return listCmt;
    }
    
    public static void main(String[] args) {
    // Create an instance of your DAO class
    CommentDAO dao = new CommentDAO();

    // Call the getAllComments function
    List<CommentDTO> comments = dao.getAllCommentsByBlogID("B0002");

    // Display the comments
    for (CommentDTO comment : comments) {
        System.out.println("Comment ID: " + comment.getCommentID());
        System.out.println("Comment Content: " + comment.getCommentContent());
        System.out.println("Blog ID: " + comment.getBlogID().getMaBlog());
        System.out.println("Created Date: " + comment.getCreateDate());
        System.out.println("------------------------------------");
    }
}

}
