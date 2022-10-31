/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author kurtm
 */
public class RoleDB {
    
    public List<Role> getRoles() throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM role";
        
        try {
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt(1);
                String roleName = rs.getString(2);
                Role role = new Role(roleId, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return roles;
    }

    public Role getRole(String roleString) throws Exception{
        Role role = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM role WHERE role_name=?";
        System.out.println(roleString + "Works here");
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, roleString);
            rs = ps.executeQuery();
            if (rs.next()) {
                int roleId = rs.getInt(1);
                String roleName = rs.getString(2);
                role = new Role(roleId, roleName);
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return role;
    }
}
