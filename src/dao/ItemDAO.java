/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ItemDTO;
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
public class ItemDAO {

    public static Vector<ItemDTO> getItemTable() throws SQLException, ClassNotFoundException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        Vector itemListVector = new Vector<>();
        try {
            con = MyConnection.openConnection();
            if (con != null) {
                st = con.createStatement();
                rs = st.executeQuery("Select * From ITEMS");
                while (rs.next()) {
                    String itemCode = rs.getString(1);
                    String itemName = rs.getString(2);
                    String supCode = rs.getString(3);
                    String unit = rs.getString(4);
                    int price = rs.getInt(5);
                    boolean suppling = rs.getBoolean(6);

                    itemListVector.add(new ItemDTO(itemCode, itemName, supCode, unit, price, suppling));

                }
            }

            return itemListVector;
        } catch (SQLException ex) {
            System.out.println("Error at getItemTable() in ItemDAO: " + ex.getMessage());
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
        return null;
    }

    public static Vector<String> getSupCode() throws ClassNotFoundException {
        try {
            Connection con = MyConnection.openConnection();
            PreparedStatement ps = con.prepareCall("SELECT supCode From Items");
            ResultSet rs = ps.executeQuery();
            Vector<String> supCodeList = new Vector<>();
            try {
                con = MyConnection.openConnection();
                if (con != null) {

                    while (rs.next()) {
                        supCodeList.add(rs.getString(1));
                    }
                    return supCodeList;
                }
            } catch (Exception e) {
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean addItem(String itemCode, String itemName, String supCode, String unit, int price, boolean supplying) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MyConnection.openConnection();
            if (con != null) {
                String sql = "INSERT INTO ITEMS (itemCode, itemName, supCode, unit, price, supplying) VALUES(?, ?, ?, ?, ?, ?)";

                ps = con.prepareCall(sql);
                ps.setString(1, itemCode);
                ps.setString(2, itemName);
                ps.setString(3, supCode);
                ps.setString(4, unit);
                ps.setInt(5, price);
                ps.setBoolean(6, supplying);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return false;
    }

    public static boolean updateItem(String itemCode, String itemName, String supCode, String unit, int price, boolean supplying) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MyConnection.openConnection();
            if (con != null) {
                String sql = "UPDATE ITEMS "
                        + "SET itemName = ? , supCode = ? , unit = ?, price = ?, supplying = ?"
                        + " WHERE itemCode = ?";
                ps = con.prepareCall(sql);
                ps.setString(1, itemName);
                ps.setString(2, supCode);
                ps.setString(3, unit);
                ps.setInt(4, price);
                ps.setBoolean(5, supplying);
                ps.setString(6, itemCode);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }

    public static boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = MyConnection.openConnection();
            if (con != null) {
                String sql = "DELETE FROM ITEMS WHERE itemCode = ?";
                ps = con.prepareCall(sql);
                ps.setString(1, itemCode);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return false;
    }
}
