/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.BLogCateDTO;
import com.mycompany.yogacenterproject.dto.BlogDTO;
import com.mycompany.yogacenterproject.dto.BlogImgDTO;
import com.mycompany.yogacenterproject.dto.HocVienDTO;
import com.mycompany.yogacenterproject.dto.LopHocIMGDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DBUtils;
import com.mycompany.yogacenterproject.util.DateUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devli
 */
public class BlogDAO {

    Connection conn = null;
    PreparedStatement ps = null;

    ResultSet rs = null;

    public List<BlogDTO> getAllBlogs() {
        List<BlogDTO> listBlog = new ArrayList<>();
        BlogImageDAO daoImg = new BlogImageDAO();
        String sql = "SELECT * FROM [dbo].[blogPost] where [status] = 'true'";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogDTO blogDTO = new BlogDTO();
                String maBlog = rs.getString("maBlog");
                List<BlogImgDTO> blogImgDTO = daoImg.getImageDataByID(maBlog);
                blogDTO.setMaBlog(maBlog);
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setImage(blogImgDTO);
                listBlog.add(blogDTO);
            }
        } catch (Exception e) {
        }
        return listBlog;
    }

    public List<BlogDTO> getAllBlogsByMaHV(String maHV) {
        List<BlogDTO> listBlog = new ArrayList<>();
        BlogImageDAO daoImg = new BlogImageDAO();
        String sql = "SELECT * FROM [dbo].[blogPost] where maHV=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maHV);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogDTO blogDTO = new BlogDTO();
                String maBlog = rs.getString("maBlog");
                List<BlogImgDTO> blogImgDTO = daoImg.getImageDataByID(maBlog);
                blogDTO.setMaBlog(maBlog);
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));

                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setImage(blogImgDTO);
                listBlog.add(blogDTO);
            }
        } catch (Exception e) {
        }
        return listBlog;
    }

    public List<BlogDTO> getAllBlogsUnapprove() {
        List<BlogDTO> listBlog = new ArrayList<>();
        BlogImageDAO daoImg = new BlogImageDAO();
        String sql = "SELECT * FROM [dbo].[blogPost] where status = '0'";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogDTO blogDTO = new BlogDTO();
                String maBlog = rs.getString("maBlog");
                List<BlogImgDTO> blogImgDTO = daoImg.getImageDataByID(maBlog);
                blogDTO.setMaBlog(maBlog);
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setMaTrainer(rs.getString("maTrainer"));
                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setImage(blogImgDTO);
                listBlog.add(blogDTO);
            }
            return listBlog;
        } catch (Exception e) {
        }
        return listBlog;
    }

    public List<BlogDTO> getAllBlogsApprove() {
        List<BlogDTO> listBlog = new ArrayList<>();
        BlogImageDAO daoImg = new BlogImageDAO();
        String sql = "SELECT * FROM [dbo].[blogPost] where status = 'true'";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BlogDTO blogDTO = new BlogDTO();
                String maBlog = rs.getString("maBlog");
                List<BlogImgDTO> blogImgDTO = daoImg.getImageDataByID(maBlog);
                blogDTO.setMaBlog(maBlog);
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setMaTrainer(rs.getString("maTrainer"));
                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setMaCate(rs.getString("maCate"));
                blogDTO.setImage(blogImgDTO);
                listBlog.add(blogDTO);
            }
            return listBlog;
        } catch (Exception e) {
        }
        return listBlog;
    }

//  
    public void insertImageDataFromDatabase(List<byte[]> imageList, BlogImgDTO blogImgDTO) throws SQLException, IOException {

        List<String> listAnh = new ArrayList<>();
        try {
            // Load the JDBC driver (replace "your-driver-class" with the appropriate driver class for your database)

            // Create a connection to the database (replace "your-database-url", "username", and "password" with your actual connection details)
            Connection conn = DBUtils.getConnection();
            String sql = "insert into [dbo].[blogImg]( maAnh,tenAnh ,[image], maBlog)"
                    + "VALUES( ?, ?, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (byte[] imageData : imageList) {

                String AUTO_IMG_ID = String.format(Constants.MA_IMG_FORMAT, (lastIDIndexOfBlogImg() + 1));

                //HOCVIEN CONSTRUCTOR
                String maAnh = AUTO_IMG_ID;
                blogImgDTO.setMaAnh(maAnh);
                ps.setString(1, blogImgDTO.getMaAnh());
                ps.setString(2, blogImgDTO.getTenAnh());
                ps.setBytes(3, imageData);
                ps.setString(4, blogImgDTO.getMaBlog());

                ps.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }

    }

    public String getAuthorNameByID(String maHV) {
        String authorName = null;
        String sql = "SELECT ten FROM [dbo].[hocVien] WHERE maHV = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maHV);
            rs = ps.executeQuery();
            while (rs.next()) {
                authorName = rs.getString("Ten");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return authorName;
    }

    public BlogDTO getBlogAuthor(String maBlog) {
        BlogDTO blogDTO = null;
        String sql = "SELECT b.maBlog, b.tieuDe, b.noiDung, b.ngayTaoPost, b.maHV, h.Ten\n"
                + "FROM blogPost b\n"
                + "INNER JOIN hocVien h ON b.maHV = h.maHV\n"
                + "WHERE b.maBlog = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maBlog);
            rs = ps.executeQuery();
            if (rs.next()) {
                blogDTO = new BlogDTO();
                blogDTO.setMaBlog(rs.getString("maBlog"));
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setStatus(rs.getBoolean("status"));
                // Lấy tên tác giả dựa vào mã HV và gán vào blogDTO
                String tenHV = getAuthorNameByID(rs.getString("maHV"));
                blogDTO.setTenHV(tenHV);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogDTO;
    }

    public BlogDTO getBlogByID(String maBlog) {
        BlogDTO blogDTO = new BlogDTO();
        String sql = "select * from [dbo].[blogPost] where [maBlog] = ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, maBlog);
            rs = ps.executeQuery();
            while (rs.next()) {
                blogDTO = new BlogDTO();
                blogDTO.setMaBlog(rs.getString("maBlog"));
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setMaCate(rs.getString("maCate"));

            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return blogDTO;
    }

    public List<BlogDTO> getLatestPosts() {
        List<BlogDTO> latestPosts = new ArrayList<>();
        String sql = "SELECT TOP 4 * FROM [dbo].[blogPost] ORDER BY [ngayTaoPost] DESC";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                BlogDTO blogDTO = new BlogDTO();
                BlogImageDAO blogImgDAO = new BlogImageDAO();
                blogDTO.setMaBlog(rs.getString("maBlog"));
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setMaCate(rs.getString("maCate"));

                latestPosts.add(blogDTO);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return latestPosts;
    }

    public List<BlogDTO> getBlogByCategoryID(String cid) {
        List<BlogDTO> listBlog = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[blogPost] where [maCate]= ? ";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, cid);
            rs = ps.executeQuery();

            while (rs.next()) {
                BlogDTO blogDTO = new BlogDTO();
                blogDTO.setMaBlog(rs.getString("maBlog"));
                blogDTO.setTitle(rs.getString("tieuDe"));
                blogDTO.setContent(rs.getString("noiDung"));
                blogDTO.setDate(rs.getString("ngayTaoPost"));
                blogDTO.setMaHV(rs.getString("maHV"));
                blogDTO.setStatus(rs.getBoolean("status"));
                blogDTO.setMaCate(rs.getString("maCate"));
                listBlog.add(blogDTO);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
        }
        return listBlog;
    }

    public List<BLogCateDTO> getAllBlogCate() {
        List<BLogCateDTO> listCate = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[blogCategories]";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BLogCateDTO bLogCateDTO = new BLogCateDTO();
                bLogCateDTO.setMaCate(rs.getString("maCate"));
                bLogCateDTO.setTenCate(rs.getString("tenCate"));
                listCate.add(bLogCateDTO);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return listCate;

    }

    public int lastIDIndexOfBlogImg() {
        String sql = "SELECT TOP 1 maAnh FROM [dbo].[blogImg] ORDER BY maAnh DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maAnh").replaceAll("[^0-9]", ""));
                index = numberOnly;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return index;
    }

    public int lastIDIndexOfBlog() {
        String sql = "SELECT TOP 1 maBlog FROM [dbo].[blogPost] ORDER BY maBlog DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maBlog").replaceAll("[^0-9]", ""));
                index = numberOnly;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return index;
    }

    public boolean createBlog(BlogDTO blogDTO) throws SQLException {
        String sql = "INSERT INTO [dbo].[blogPost]([maBlog]\n"
                + "           ,[tieuDe]\n"
                + "           ,[noiDung]\n"
                + "           ,[ngayTaoPost]\n"
                + "           ,[maHV]\n"
                + "           ,[maTrainer]\n"
                + "           ,[maCate]\n"
                + "           ,[status]\n"
                + "           ,[ngayCapNhat]) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, blogDTO.getMaBlog());
            ps.setString(2, blogDTO.getTitle());
            ps.setString(3, blogDTO.getContent());
            ps.setDate(4, blogDTO.getNgayTaoPost());

            ps.setString(5, blogDTO.getMaHV());
            ps.setString(6, blogDTO.getMaTrainer());
            ps.setString(7, null);
            ps.setBoolean(8, false);
            ps.setDate(9, null);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void Delete(String maBlog) {
        String sql = "DELETE FROM [dbo].[blogImg] where maBlog=? "
                + "DELETE FROM [dbo].[blogPost] where maBlog=?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maBlog);
            ps.setString(2, maBlog);
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void approveBlog(String maBlog, String maCate) {
        String sql = "Update [dbo].[blogPost] "
                + "Set [status] = 'true',[maCate]=? "
                + "where maBlog=?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maCate);
            ps.setString(2, maBlog);

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
        System.out.println(dao.getAllBlogsApprove());
//        BlogDTO blogDTO = dao.getBlogByID("B0001");
//        dao.approveBlog("B0003", "BC0001");
//        System.out.println();
//        List<BlogDTO> listB = new ArrayList<>();
//        List<BLogCateDTO> listCate = new ArrayList<>();
//        listCate = dao.getAllBlogCate();
//        listB = dao.getBlogByCategoryID("BC0001");
//        for (BlogDTO o : listB) {
//            System.out.println(o.toString());
//        }
    }

}
