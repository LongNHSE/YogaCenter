/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package EventListener;

import com.mycompany.yogacenterproject.dao.AttendanceDAO;
import com.mycompany.yogacenterproject.dao.LopHocDAO;
import com.mycompany.yogacenterproject.dao.ScheduleDAO;
import com.mycompany.yogacenterproject.dao.TrainerDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Oalskad
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        LopHocDAO lopHocDAO = new LopHocDAO();
        TrainerDAO trainerDAO = new TrainerDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        
        try {
            attendanceDAO.autoUpdateStatusAttendance();
        } catch (SQLException ex) {
            Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        scheduleDAO.checkSchedule();
        
        lopHocDAO.CheckClass();
        trainerDAO.CheckClass();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
