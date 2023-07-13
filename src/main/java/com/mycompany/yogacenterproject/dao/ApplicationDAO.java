/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.yogacenterproject.dao;

import com.mycompany.yogacenterproject.dto.ApplicationDTO;
import com.mycompany.yogacenterproject.dto.TrainerDTO;
import com.mycompany.yogacenterproject.util.Constants;
import com.mycompany.yogacenterproject.util.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Oalskad
 */
public class ApplicationDAO {

    public void updateStatus(String maDon) {

        String sql = "UPDATE [dbo].[application] SET [status] = 'Approved(y)' WHERE maDon= ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maDon);

            int rowsUpdated = ps.executeUpdate();
//            System.out.println(rowsUpdated + " rows updated. Status set to false for past dates.");
        } catch (SQLException e) {
        }
    }
    public void updateStatusApprove(String maDon) {

        String sql = "UPDATE [dbo].[application] SET [status] = 'Approved' WHERE maDon= ? ";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maDon);

            int rowsUpdated = ps.executeUpdate();
//            System.out.println(rowsUpdated + " rows updated. Status set to false for past dates.");
        } catch (SQLException e) {
        }
    }


    public void create(ApplicationDTO application) throws SQLException {
        String sql = "INSERT INTO application (maDon, maHV, maTrainer, maLopHoc, maApplicationType, Date, status,noiDung) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, application.getMaDon());
            ps.setString(2, application.getMaHV());
            ps.setString(3, application.getMaTrainer());
            ps.setString(4, application.getMaLopHoc());
            ps.setString(5, application.getMaApplicationType());
            ps.setDate(6, application.getDate());
            ps.setString(7, application.getStatus());
            ps.setString(8, application.getNoiDung());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ApplicationDTO search(String maDon) throws SQLException {
        ApplicationDTO application = null;
        String sql = "SELECT * FROM application WHERE maDon = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maDon);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                application = new ApplicationDTO();
                application.setMaDon(rs.getString("maDon"));
                application.setMaHV(rs.getString("maHV"));
                application.setMaTrainer(rs.getString("maTrainer"));
                application.setMaLopHoc(rs.getString("maLopHoc"));
                application.setMaApplicationType(rs.getString("maApplicationType"));
                application.setDate(rs.getDate("Date"));
                application.setStatus(rs.getString("status"));
                application.setNoiDung(rs.getString("noiDung"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return application;
    }

    public List<ApplicationDTO> getAllApplications() throws SQLException {
        List<ApplicationDTO> applications = new ArrayList<>();
        String sql = "SELECT * FROM application\n"
                + "inner join applicationType on applicationType.maApplicationType =application.maApplicationType\n"
                + "where status != 'Pending' ";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                ApplicationDTO application = createApplicationFromResultSet(resultSet);
                applications.add(application);
            }
        }
        return applications;
    }

    public List<ApplicationDTO> getAllApplicationsUnapprove() throws SQLException {
        List<ApplicationDTO> applications = new ArrayList<>();
        String sql = "SELECT * FROM application\n"
                + "inner join applicationType on applicationType.maApplicationType =application.maApplicationType\n"
                + "where status = 'Pending' ";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                ApplicationDTO application = createApplicationFromResultSet(resultSet);
                applications.add(application);
            }
        }
        return applications;
    }

    public String getApplicationFromTrainee(String maLoaiLopHoc, String maHocVien) throws SQLException {
        List<ApplicationDTO> applications = new ArrayList<>();
        LoaiLopHocDAO loaiLopHocDAO = new LoaiLopHocDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        String maLopHoc = null;
        String sql = "SELECT * FROM application\n"
                + "inner join applicationType on applicationType.maApplicationType =application.maApplicationType\n"
                + "where status = 'Approved(n)' and application.maApplicationType='TYPE0002' and maHV=? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maHocVien);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                ApplicationDTO application = createApplicationFromResultSet(resultSet);

                String input = application.getNoiDung();
                Pattern pattern = Pattern.compile("\\b(LOP\\d+)\\b");
                Matcher matcher = pattern.matcher(input);

                if (matcher.find()) {
                    String extracted = matcher.group(1);
                    maLopHoc = extracted;
                    System.out.println(lopHocDAO.searchClassById(maLopHoc));
                    if (lopHocDAO.searchClassById(maLopHoc).getMaLoaiLopHoc().equals(maLoaiLopHoc)) {
                        return application.getMaDon();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ApplicationDTO createApplicationFromResultSet(ResultSet resultSet) throws SQLException {
        String maDon = resultSet.getString("maDon");
        String maHV = resultSet.getString("maHV");
        String maTrainer = resultSet.getString("maTrainer");
        String maLopHoc = resultSet.getString("maLopHoc");
        String maApplicationType = resultSet.getString("maApplicationType");
        Date date = resultSet.getDate("Date");
        String status = resultSet.getString("status");

        ApplicationDTO applicationDTO = new ApplicationDTO(maDon, maHV, maTrainer, maLopHoc, maApplicationType, date, status);
        applicationDTO.setApplicationType(resultSet.getString("tenApplication"));
        applicationDTO.setNoiDung(resultSet.getString("noiDung"));
        return applicationDTO;

    }

    public int lastIDIndexOfBlog() {
        String sql = "SELECT TOP 1 maDon FROM [dbo].[application] ORDER BY maDon DESC";
        int index = 0;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                int numberOnly = Integer.parseInt(rs.getString("maDon").replaceAll("[^0-9]", ""));
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

    public static void main(String[] args) throws SQLException {
        ApplicationDAO applicationDAO = new ApplicationDAO();
        LocalDate currentDate = LocalDate.now();
        TrainerDAO trainerDAO = new TrainerDAO();
//         TrainerDTO trainerDTO = trainerDAO.searchTrainerById("TR0003");
        String AUTO_APPLICATION_ID = String.format(Constants.MA_APPLICATION_FORMAT, (applicationDAO.lastIDIndexOfBlog() + 1));

        //HOCVIEN CONSTRUCTOR
        String maApp = AUTO_APPLICATION_ID;
        ApplicationDTO applicationDTO = new ApplicationDTO();

        applicationDTO.setMaLopHoc("LOP0006");
        applicationDTO.setNoiDung("Trainer " + "TR0005" + " reserve class " + "LOP0006");
        applicationDTO.setMaApplicationType("TYPE0004");
        applicationDTO.setDate(Date.valueOf(currentDate));
        applicationDTO.setMaTrainer("TR0005");
        applicationDTO.setMaDon(maApp);
        applicationDTO.setStatus("Pending");
        applicationDAO.create(applicationDTO);
//        applicationDAO.updateStatus("AP0004");
//        String input = "Reserve class LOP0013";
//        Pattern pattern = Pattern.compile("\\b(LOP\\d+)\\b");
//        Matcher matcher = pattern.matcher(input);
//
//        if (matcher.find()) {
//            String extracted = matcher.group(1);
//            System.out.println(extracted);
//        }
    }

}
