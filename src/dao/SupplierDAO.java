/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.SupplierDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import utils.MyConnection;

/**
 *
 * @author TruongTN
 */
public class SupplierDAO {

    public static Vector loadSupplierList() throws SQLException, ClassNotFoundException {
        Vector supplierListVector = new Vector<>();

        try (Connection con = MyConnection.openConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery("Select * From SUPPLIERS");) {
            while (rs.next()) {
                String supCode = rs.getString(1).trim();
                String supName = rs.getString(2).trim();
                String address = rs.getString(3).trim();
                boolean colloborating = rs.getBoolean(4);

                Vector v = new Vector();
                v.add(supCode);
                v.add(supName);
                v.add(address);
                v.add(colloborating);

                supplierListVector.add(v);
            }
            return supplierListVector;
        }
    }

    public static Vector<String> loadCodeSupplier() throws SQLException, ClassNotFoundException {
        Connection con = null;
         Statement st = null;
         ResultSet rs = null;
        Vector codeSupplierList = new Vector();
        try {
            con = MyConnection.openConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT supCode FROM SUPPLIERS");
            if (con != null) {
                while (rs.next()) {
                    String supCode = rs.getString(1);

                    codeSupplierList.add(supCode);
                }  
            }
            return codeSupplierList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static Vector loadCodeAndNameOfSuppliers() throws SQLException, ClassNotFoundException {
        Connection con = MyConnection.openConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT supCode, supName FROM SUPPLIERS");
        Vector<SupplierDTO> codeAndNameOfSupplierList = new Vector();

        try {
            while (rs.next()) {
                String supCode = rs.getString(1);
                String supName = rs.getString(2);

                codeAndNameOfSupplierList.add(new SupplierDTO(supCode, supName));
            }
            return codeAndNameOfSupplierList;
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean addNewSupplier(String supCode, String supName, String address, boolean colloborating) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MyConnection.openConnection();

            if (con != null) {
                String sql = "Insert INTO SUPPLIERS (supCode, supName, address, colloborating) VALUES(?, ?, ?, ?)";

                ps = con.prepareCall(sql);
                ps.setString(1, supCode);
                ps.setString(2, supName);
                ps.setString(3, address);
                ps.setBoolean(4, colloborating);
                int row = ps.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;

    }

    public static boolean updateSupplier(String supCode, String supName, String address, boolean colloborating) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MyConnection.openConnection();
            if (con != null) {
                String sql = "UPDATE SUPPLIERS SET supName = ?, address = ?, colloborating = ? WHERE supCode = ?";
                ps = con.prepareCall(sql);
                ps.setString(1, supName);
                ps.setString(2, address);
                ps.setBoolean(3, colloborating);
                ps.setString(4, supCode);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static boolean deleteSupplier(String supCode) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MyConnection.openConnection();
            if (con != null) {
                String sql = "DELETE FROM SUPPLIERS WHERE supCode = ?";
                ps = con.prepareCall(sql);
                ps.setString(1, supCode);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
